package com.easybuy.easybuy.TicketUtils;

import com.easybuy.easybuy.DTO.TicketDTO;
import com.easybuy.easybuy.DTO.TicketProductDTO;
import com.easybuy.easybuy.models.Ticket;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.easybuy.easybuy.models.TicketProduct;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.core.io.FileSystemResource;


public class PDFExporter {
    private Ticket ticket;

    public PDFExporter(Ticket ticket) {
        this.ticket = ticket;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);


        cell.setPhrase(new Phrase("QUANTITY", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PRODUCT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("AMOUNT", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (TicketProduct ticketProduct : ticket.getTicketProducts() ) {
            table.addCell(String.valueOf(ticketProduct.getQuantity()));
            table.addCell(String.valueOf(ticketProduct.getProduct().getName()));
            table.addCell(String.valueOf(ticketProduct.getPrice()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(10);
        font.setColor(Color.BLUE);
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(20);
        font1.setColor(Color.BLUE);

        Paragraph p = new Paragraph("EASY-BUY TICKET", font1);
        Paragraph t = new Paragraph("TICKET NUMBER: "+ticket.getNumber(), font);
        t.setAlignment(Paragraph.ALIGN_RIGHT);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(t);
        document.add(p);


        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(90f);
        table.setWidths(new float[] {2.0f, 4.5f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

    public void exportToRoot() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("./ticket-"+ticket.getNumber()+".pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(10);
        font.setColor(Color.BLUE);
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(20);
        font1.setColor(Color.BLUE);

        Paragraph p = new Paragraph("EASY-BUY TICKET", font1);
        Paragraph t = new Paragraph("TICKET NUMBER: "+ticket.getNumber(), font);
        t.setAlignment(Paragraph.ALIGN_RIGHT);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(t);
        document.add(p);


        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(90f);
        table.setWidths(new float[] {2.0f, 4.5f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
