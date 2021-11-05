/*
 * $Id: HelloHtml.java 3373 2008-05-12 16:21:24Z xlv $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */

package com.lowagie.examples.html;

import com.lowagie.text.*;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfTestBase;
import com.lowagie.text.pdf.PdfWriter;
import org.junit.Test;

import java.io.*;

/**
 *
 * @author donz
 */

public class CJKHTML2PDFTest {

	/**
	 * test
	 * 
	 */
	@Test
	public void main() throws Exception {

		BaseFont baseFontChinese = BaseFont.createFont("src/test/resources/yahei-font/msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		// step 1: creation of a document-object
		File inputHtml = new File("src/test/resources/a.html");
		BufferedReader reader = new BufferedReader(new FileReader(inputHtml));
		StringBuilder buffer = new StringBuilder();
		String keyWord = null;
		while ((keyWord = reader.readLine()) != null) {
			buffer.append(keyWord);
		}
		Document document = new Document(PageSize.A4.rotate());

//		File file = new File("D://hahaha.pdf");
		DocWriter docWriter = PdfWriter.getInstance(document, PdfTestBase.getOutputStream("html2pdf.pdf"));

		document.open();
//		Phrase myPhrase  = new Phrase();
//		myPhrase.add(new Phrase("中文", FontFactory.getFont("Microsoft YaHei", 20)));
//		document.add(myPhrase);

		HTMLWorker worker = new HTMLWorker(document);
//		BaseFont baseFontChinese = BaseFont.createFont("src/main/resources/font/msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		worker.parse(new StringReader(buffer.toString()),baseFontChinese);

		document.close();
		docWriter.close();
	}
}