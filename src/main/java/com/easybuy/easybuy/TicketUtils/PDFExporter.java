package com.easybuy.easybuy.TicketUtils;

import com.easybuy.easybuy.models.PurchaseOrder;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;

import com.easybuy.easybuy.models.PurchaseOrderProduct;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class PDFExporter {
    private PurchaseOrder purchaseOrder;

    public PDFExporter(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);


        cell.setPhrase(new Phrase("QUANT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PRODUCT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("AMOUNT", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (PurchaseOrderProduct purchaseOrderProduct : purchaseOrder.getTicketProducts() ) {
            table.addCell(String.valueOf(purchaseOrderProduct.getQuantity()));
            table.addCell(String.valueOf(purchaseOrderProduct.getProduct().getName()));
            table.addCell(NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(purchaseOrderProduct.getPrice()));

        }

    }

    private void writeTableFooter(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("TOTAL", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase(NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(purchaseOrder.getAmount()) , font));
        table.addCell(cell);
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        builDocument(document);

    }

    public void exportToRoot() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("./testPdf.pdf"));
        builDocument(document);



    }

    public void builDocument(Document document){
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(10);
        font.setColor(Color.BLUE);
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(20);
        font1.setColor(Color.BLUE);

        Paragraph p = new Paragraph("EASY-BUY TICKET", font1);
        Paragraph t = new Paragraph("TICKET NUMBER: "+ purchaseOrder.getNumber(), font);
        t.setAlignment(Paragraph.ALIGN_RIGHT);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(t);
        document.add(p);


        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(90f);
        table.setWidths(new float[] {1.0f, 5.5f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);
        writeTableFooter(table);

        document.add(table);

        document.close();
    }
}

