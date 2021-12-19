package com.example.demo.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    //test
    public void export(HttpServletResponse response) throws IOException {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font fontTitle = FontFactory.getFont("assets/arial.ttf", BaseFont.IDENTITY_H, 18, Font.BOLDITALIC);

            Paragraph paragraph = new Paragraph("uytrertyhj", fontTitle);
            paragraph.setAlignment(Element.ALIGN_CENTER);

            Font fontParagraph = FontFactory.getFont("assets/arial.ttf", BaseFont.IDENTITY_H, 15);

            Paragraph secondParagraph = new Paragraph("sdfghj", fontParagraph);
            secondParagraph.setAlignment(Element.ALIGN_RIGHT);

            document.add(paragraph);
            document.add(secondParagraph);
        }
    }
}
