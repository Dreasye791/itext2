package com.lowagie.text.pdf.parser;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.lowagie.text.pdf.PdfReader;


public class PdfTextExtractorTest {

	
	@Test(expected=IOException.class)
	public void testPageExceeded() throws Exception {
		getString("HelloWorldMeta.pdf", 5);
	}
	@Test(expected=IOException.class)
	public void testInvalidPageNumber() throws Exception {
		getString("HelloWorldMeta.pdf", 0);
	}
	
	@Test
	public void testHelloWorld() throws Exception {
		String result = getString("HelloWorldMeta.pdf", 1);
		Assert.assertEquals("Hello World", result);

	}
	
	@Test
	public void testConcatenate() throws Exception {
		String result = getString("Concatenate.pdf", 5);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.contains("2. This is chapter 2"));
	}
	
	@Test
	public void testConcatenateWatermark() throws Exception {
		String result = getString("Concatenate-acroforms-watermark.pdf", 5);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.contains("2. This is chapter 2"));
		Assert.assertTrue(result.contains("Hello World watermark-concatenate"));
	}
	
	@Test
	public void test1() throws Exception {		
		String result = getString("yaxiststar.pdf", 9);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.contains("Remember to CATCH IT, BIN IT, KILL IT."));		
	}
	
	private String getString(String fileName, int pageNumber) throws Exception {
		return getString(new File("src/test/resources", fileName), pageNumber);
	}
	private String getString(File file, int pageNumber) throws Exception {
		byte[] pdfBytes = readDocument(file);
		final PdfReader pdfReader = new PdfReader(pdfBytes);
		
		return new PdfTextExtractor(pdfReader).getTextFromPage( pageNumber);		
	}
	
	protected static byte[] readDocument(final File file) throws IOException {

		try (ByteArrayOutputStream fileBytes = new ByteArrayOutputStream();
				InputStream inputStream = new FileInputStream(file)) {
			final byte[] buffer = new byte[8192];
			while (true) {
				final int bytesRead = inputStream.read(buffer);
				if (bytesRead == -1) {
					break;
				}
				fileBytes.write(buffer, 0, bytesRead);
			}
			return fileBytes.toByteArray();
		}

	}
}
