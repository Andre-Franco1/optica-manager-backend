package com.optica.manager.domain.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

import org.openpdf.text.Chunk;
import org.openpdf.text.Document;
import org.openpdf.text.DocumentException;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.Image;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;
import org.openpdf.text.Rectangle;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.pdf.draw.DottedLineSeparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.mappers.ServiceOrderMapper;
import com.optica.manager.domain.repositories.SaleRepository;
import com.optica.manager.dto.SaleItemPdfDTO;
import com.optica.manager.dto.ServiceOrderPdfDTO;

import jakarta.transaction.Transactional;

@Service
public class ServiceOrderPdfService {

    @Autowired
    private SaleRepository saleRepository;

    @Transactional
    public byte[] generate(Long saleId) {

        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        ServiceOrderPdfDTO serviceOrderDTO = ServiceOrderMapper.toServiceOrderDto(sale);

        return generatePdf(serviceOrderDTO);
    }

    public byte[] generatePdf(ServiceOrderPdfDTO serviceOrderDTO) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter.getInstance(document, baos);

            document.open();

            addSectionHeader(document, serviceOrderDTO, "CLIENTE");
            addClientSection(document, serviceOrderDTO);
            addProductsDescriptionTable(document, serviceOrderDTO);

            addSectionDivider(document);

            addSectionHeader(document, serviceOrderDTO, "LOJA");
            addSaleInfo(document, serviceOrderDTO);
            addPrescriptionTable(document, serviceOrderDTO);
            addProductsDescriptionTable(document, serviceOrderDTO);
            addPaymentTable(document);

            addSectionDivider(document);

            addSectionHeader(document, serviceOrderDTO, "LABORATÓRIO");
            addSaleInfo(document, serviceOrderDTO);
            addPrescriptionTable(document, serviceOrderDTO);
            addProductsGridTable(document, serviceOrderDTO);

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private void addClientSection(Document document, ServiceOrderPdfDTO serviceOrderDTO) throws Exception {

        PdfPTable innerTable = new PdfPTable(1);
        innerTable.setWidthPercentage(100);

        Font smallFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
        Font smallBoldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = serviceOrderDTO.estimatedDeliveryDate().format(formatter);

        // ---------- 1ª row: informações da venda ----------
        PdfPTable saleInfoRow = new PdfPTable(3);
        saleInfoRow.setWidthPercentage(100);
        saleInfoRow.setWidths(new float[] { 2, 2, 2 });

        saleInfoRow.addCell(noBorderCell("P/ O DIA: " + formattedDate, smallBoldFont));
        saleInfoRow.addCell(
                noBorderCell(
                        String.format("%07d", serviceOrderDTO.clientId()) + "   " + serviceOrderDTO.clientName(),
                        smallBoldFont));
        saleInfoRow.addCell(noBorderCell("VENDEDOR: ALESSANDRO FRANCO", smallFont));

        PdfPCell saleInfoCell = new PdfPCell(saleInfoRow);
        saleInfoCell.setBorderWidth(1f);
        saleInfoCell.setBorderColor(Color.BLACK);
        saleInfoCell.setPadding(2f);

        innerTable.addCell(saleInfoCell);

        // ---------- 2ª row: informações adicionais ----------
        PdfPTable extraInfoRow = new PdfPTable(3);
        extraInfoRow.setWidthPercentage(100);
        extraInfoRow.setWidths(new float[] { 2, 2, 2 });

        extraInfoRow.addCell(noBorderCell("FINANCEIRO: RECEBIDO", smallBoldFont));
        extraInfoRow.addCell(noBorderCell("SINAL: 2.320,00   SALDO: 0,00", smallFont));
        extraInfoRow.addCell(noBorderCell("ENTREGA: AGUARDANDO", smallBoldFont));

        PdfPCell extraInfoCell = new PdfPCell(extraInfoRow);
        extraInfoCell.setBorder(PdfPCell.NO_BORDER);

        innerTable.addCell(extraInfoCell);

        // ---------- frame externo ----------
        PdfPCell frameCell = new PdfPCell(innerTable);
        frameCell.setBorderWidth(1f);
        frameCell.setBorderColor(Color.BLACK);
        frameCell.setPadding(0f);

        PdfPTable outerTable = new PdfPTable(1);
        outerTable.setWidthPercentage(100);
        outerTable.setSpacingBefore(0f); // controla proximidade do header
        outerTable.addCell(frameCell);

        document.add(outerTable);
    }

    private void addSaleInfo(Document document, ServiceOrderPdfDTO serviceOrderDTO) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedEstimatedDate = serviceOrderDTO.estimatedDeliveryDate().format(formatter);
        String formattedIssueDate = serviceOrderDTO.issueDate().format(formatter);

        PdfPTable secondInnerTable = new PdfPTable(3);
        secondInnerTable.setWidthPercentage(100);
        secondInnerTable.setWidths(new float[] { 4, 4, 4 });

        Font smallFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);

        // ROW 1
        secondInnerTable.addCell(noBorderCell("DATA: " + formattedIssueDate, smallFont));
        secondInnerTable.addCell(noBorderCell("OPERADOR: " + serviceOrderDTO.userName(), smallFont));
        secondInnerTable.addCell(noBorderCell("VENDEDOR: ALESSANDRO FRANCO", smallFont));

        // ROW 2
        secondInnerTable.addCell(noBorderCell(
                "CLIENTE: " + String.format("%07d", serviceOrderDTO.clientId()) + " " + serviceOrderDTO.clientName().toUpperCase(), boldFont));
        secondInnerTable.addCell(noBorderCell("CPF: " + serviceOrderDTO.clientCpf(), smallFont));
        secondInnerTable.addCell(noBorderCell("DATA NASC.: 20/02/1984", smallFont));

        // ROW 3
        secondInnerTable.addCell(noBorderCell("P/ O DIA: " + formattedEstimatedDate, boldFont));
        secondInnerTable.addCell(noBorderCell("FINANCEIRO: RECEBIDO", boldFont));
        secondInnerTable.addCell(noBorderCell("STATUS: " + serviceOrderDTO.saleStatus(), boldFont));

        PdfPCell secondFrameCell = new PdfPCell(secondInnerTable);
        secondFrameCell.setBorderWidth(1f);
        secondFrameCell.setBorderColor(java.awt.Color.BLACK);
        secondFrameCell.setPadding(3f);

        PdfPTable secondOuterTable = new PdfPTable(1);
        secondOuterTable.setWidthPercentage(100);
        secondOuterTable.setSpacingBefore(6f);

        secondOuterTable.addCell(secondFrameCell);

        document.add(secondOuterTable);
    }

    private void addPrescriptionTable(Document document, ServiceOrderPdfDTO serviceOrderDTO)
            throws Exception {

        PdfPTable table = new PdfPTable(15);
        table.setWidths(new float[] { 2, 2, 2, 2, 3, 4, 2, 2, 2, 2, 2, 3, 4, 2, 2 });
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        // Row 1
        table.addCell(headerCellPrescription("LONGE"));
        table.addCell(headerCellPrescription("ESF"));
        table.addCell(headerCellPrescription("CIL"));
        table.addCell(headerCellPrescription("EIXO"));
        table.addCell(headerCellPrescription("DNP/Curva"));
        table.addCell(headerCellPrescription("DNP/Diâmetro"));
        table.addCell(headerCellPrescription("ALT"));

        table.addCell(headerCellPrescription("PERTO"));
        table.addCell(headerCellPrescription("ESF"));
        table.addCell(headerCellPrescription("CIL"));
        table.addCell(headerCellPrescription("EIXO"));
        table.addCell(headerCellPrescription("DNP/Curva"));
        table.addCell(headerCellPrescription("DNP/Diâmetro"));
        table.addCell(headerCellPrescription("ALT"));
        table.addCell(headerCellPrescription("AD"));

        // Row 2
        table.addCell(valueCellPrescription("OD"));
        table.addCell(valueCellPrescription("+0,00"));
        table.addCell(valueCellPrescription("-1,25"));
        table.addCell(valueCellPrescription("90"));
        table.addCell(valueCellPrescription("0,00"));
        table.addCell(valueCellPrescription(""));
        table.addCell(valueCellPrescription(""));

        table.addCell(valueCellPrescription("OD"));
        table.addCell(valueCellPrescription("+3,00"));
        table.addCell(valueCellPrescription("-1,25"));
        table.addCell(valueCellPrescription("90"));
        table.addCell(valueCellPrescription("0,00"));
        table.addCell(valueCellPrescription(""));
        table.addCell(valueCellPrescription(""));
        table.addCell(valueCellPrescription("3,00"));

        // Row 3
        table.addCell(valueCellPrescription("OE"));
        table.addCell(valueCellPrescription("+0,00"));
        table.addCell(valueCellPrescription("-1,00"));
        table.addCell(valueCellPrescription("90"));
        table.addCell(valueCellPrescription("0,00"));
        table.addCell(valueCellPrescription(""));
        table.addCell(valueCellPrescription(""));

        table.addCell(valueCellPrescription("OE"));
        table.addCell(valueCellPrescription("+3,00"));
        table.addCell(valueCellPrescription("-1,00"));
        table.addCell(valueCellPrescription("90"));
        table.addCell(valueCellPrescription("0,00"));
        table.addCell(valueCellPrescription(""));
        table.addCell(valueCellPrescription(""));
        table.addCell(valueCellPrescription("3,00"));

        document.add(table);
    }

    private void addSectionHeader(Document document, ServiceOrderPdfDTO serviceOrderDTO, String sectionName) throws Exception {

        // ---------- tabela interna (conteúdo do frame principal) ----------
        PdfPTable innerTable = new PdfPTable(1); // 1 coluna, cada row será uma sub-tabela
        innerTable.setSpacingBefore(3f);
        innerTable.setWidthPercentage(100);

        // ---------- 1ª row: header principal ----------
        PdfPTable headerRow = new PdfPTable(4);
        headerRow.setWidthPercentage(100);
        headerRow.setWidths(new float[] { 1, 3, 2, 1 });

        Image logo = Image.getInstance(serviceOrderDTO.unitLogoPath());
        logo.scaleToFit(40, 40);
        logo.setAlignment(Element.ALIGN_CENTER);

        PdfPCell logoCell = new PdfPCell(logo);
        logoCell.setBorder(PdfPCell.NO_BORDER);
        logoCell.setPadding(0.6f);
        logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerRow.addCell(logoCell);

        Font companyFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font addressFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

        Paragraph companyName = new Paragraph(serviceOrderDTO.unitName().toUpperCase(), companyFont);
        companyName.setSpacingAfter(2f);

        Paragraph address = new Paragraph(serviceOrderDTO.unitAddressInfo(), addressFont);
        address.setSpacingBefore(0f);

        PdfPCell companyCell = new PdfPCell();
        companyCell.addElement(companyName);
        companyCell.addElement(address);

        companyCell.setBorder(PdfPCell.NO_BORDER);
        companyCell.setPadding(0f); // importante para não criar espaço extra

        headerRow.addCell(companyCell);

        PdfPCell osCell = noBorderCell("OS Nº " + String.format("%07d", serviceOrderDTO.osNumber()));
        osCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        osCell.setPaddingTop(5f);
        headerRow.addCell(osCell);

        Font whiteFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);

        PdfPTable halfTable = new PdfPTable(1);
        halfTable.setWidthPercentage(100);
        halfTable.setWidths(new float[] { 1 });

        // ---- parte superior preta ----
        PdfPCell blackCell = new PdfPCell(new Phrase(sectionName, whiteFont));
        blackCell.setBackgroundColor(Color.BLACK);
        blackCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        blackCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        blackCell.setBorder(PdfPCell.NO_BORDER);
        blackCell.setFixedHeight(20f); // metade da altura desejada

        halfTable.addCell(blackCell);

        // ---- parte inferior vazia ----
        PdfPCell emptyCell = new PdfPCell(new Phrase(""));
        emptyCell.setBorder(PdfPCell.NO_BORDER);
        blackCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        blackCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        emptyCell.setFixedHeight(20f);

        halfTable.addCell(emptyCell);

        PdfPCell sectionTitleCell = new PdfPCell(halfTable);
        sectionTitleCell.setBorder(PdfPCell.NO_BORDER);

        headerRow.addCell(sectionTitleCell);

        // adicionar headerRow como célula na innerTable
        PdfPCell headerCell = new PdfPCell(headerRow);
        headerCell.setBorder(PdfPCell.NO_BORDER);
        innerTable.addCell(headerCell);

        // ---------- célula “container” com borda ----------
        PdfPCell frameCell = new PdfPCell(innerTable);
        frameCell.setBorderWidth(1f);
        frameCell.setBorderColor(java.awt.Color.BLACK);
        // frameCell.setPadding(3f);

        // ---------- tabela externa de 1 coluna ----------
        PdfPTable outerTable = new PdfPTable(1);
        outerTable.setSpacingBefore(8f);
        outerTable.setWidthPercentage(100);
        outerTable.addCell(frameCell);

        // adicionar ao documento
        document.add(outerTable);
    }

    private void addPaymentTable(Document document) throws Exception {

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 8);

        PdfPTable mainTable = new PdfPTable(3);
        mainTable.setWidthPercentage(100);
        mainTable.setWidths(new float[] { 4, 4, 3 });
        mainTable.setSpacingBefore(8f);
        mainTable.setSpacingAfter(8f);

        // =========================
        // COLUNA 1
        // =========================
        PdfPTable col1 = createInstallmentColumn(headerFont, valueFont,
                new String[][] {
                        { "1/10", "10/03/2026", "R$ 232,00" },
                        { "2/10", "10/04/2026", "R$ 232,00" },
                        { "3/10", "10/05/2026", "R$ 232,00" },
                        { "4/10", "10/06/2026", "R$ 232,00" },
                        { "5/10", "10/07/2026", "R$ 232,00" }

                });

        PdfPCell col1Cell = new PdfPCell(col1);
        col1Cell.setBorderWidthLeft(1f);
        col1Cell.setBorderWidthRight(1f);
        col1Cell.setBorderWidthTop(1f);
        col1Cell.setBorderWidthBottom(1f);

        mainTable.addCell(col1Cell);

        // =========================
        // COLUNA 2
        // =========================
        PdfPTable col2 = createInstallmentColumn(headerFont, valueFont,
                new String[][] {
                        { "6/10", "10/08/2026", "R$ 232,00" },
                        { "7/10", "10/09/2026", "R$ 232,00" },
                        { "8/10", "10/10/2026", "R$ 232,00" },
                        { "9/10", "10/11/2026", "R$ 232,00" },
                        { "10/10", "10/12/2026", "R$ 232,00" }
                });

        PdfPCell col2Cell = new PdfPCell(col2);
        col2Cell.setBorderWidthLeft(0f); // evita duplicação
        col2Cell.setBorderWidthRight(1f);
        col2Cell.setBorderWidthTop(1f);
        col2Cell.setBorderWidthBottom(1f);

        mainTable.addCell(col2Cell);

        // =========================
        // COLUNA 3 (FORMA PGTO)
        // =========================
        PdfPTable paymentTable = new PdfPTable(1);
        paymentTable.setWidthPercentage(100);

        paymentTable.addCell(horizontalCell("FORMA PGTO", headerFont));

        paymentTable.addCell(horizontalCell("CIELO PARCELADO", valueFont));
        paymentTable.addCell(horizontalCell("CIELO PARCELADO", valueFont));
        paymentTable.addCell(horizontalCell("CIELO PARCELADO", valueFont));
        paymentTable.addCell(horizontalCell("CIELO PARCELADO", valueFont));
        paymentTable.addCell(horizontalCell("CIELO PARCELADO", valueFont));

        PdfPCell col3Cell = new PdfPCell(paymentTable);
        col3Cell.setBorderWidthLeft(0f);
        col3Cell.setBorderWidthRight(1f);
        col3Cell.setBorderWidthTop(1f);
        col3Cell.setBorderWidthBottom(1f);

        mainTable.addCell(col3Cell);

        document.add(mainTable);
    }

    private PdfPTable createInstallmentColumn(Font headerFont, Font valueFont, String[][] data)
            throws DocumentException {

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 2, 3, 2 });

        table.addCell(horizontalCell("PARC.", headerFont));
        table.addCell(horizontalCell("VENC.", headerFont));
        table.addCell(horizontalCell("VALOR", headerFont));

        for (String[] row : data) {
            table.addCell(horizontalCell(row[0], valueFont));
            table.addCell(horizontalCell(row[1], valueFont));
            table.addCell(horizontalCell(row[2], valueFont));
        }

        return table;
    }

    private PdfPCell horizontalCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setBorderWidthLeft(0f);
        cell.setBorderWidthRight(0f);
        cell.setBorderWidthTop(0.8f);
        cell.setBorderWidthBottom(0.8f);

        return cell;
    }

    private void addSectionDivider(Document document) throws DocumentException {

        DottedLineSeparator dottedLine = new DottedLineSeparator();
        dottedLine.setGap(3f); // espaço entre os pontos
        dottedLine.setLineWidth(1f);
        dottedLine.setPercentage(100);

        document.add(Chunk.NEWLINE);
        document.add(new Chunk(dottedLine));
        document.add(Chunk.NEWLINE);
    }

    private void addProductsDescriptionTable(Document document, ServiceOrderPdfDTO serviceOrderDTO) throws Exception {
        Font smallFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
        Font smallBoldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
        PdfPTable itemsTable = new PdfPTable(3);
        itemsTable.setWidthPercentage(100);
        itemsTable.setSpacingBefore(1f);
        itemsTable.setSpacingAfter(8f);
        itemsTable.setWidths(new float[] { 2, 4, 3 });

        itemsTable.addCell(noBorderCell("ID", smallBoldFont));
        itemsTable.addCell(noBorderCell("PRODUTO", smallBoldFont));
        itemsTable.addCell(noBorderCell("VALOR", smallBoldFont));

        PdfPCell lineCell;

        for (int i = 0; i < 3; i++) {
            lineCell = new PdfPCell(new Phrase(""));
            lineCell.setBorder(PdfPCell.BOTTOM);
            lineCell.setBorderWidthBottom(1.5f);
            lineCell.setBorderColorBottom(java.awt.Color.BLACK);
            lineCell.setColspan(1);
            itemsTable.addCell(lineCell);
        }

        for (SaleItemPdfDTO item : serviceOrderDTO.saleItems()) {

            itemsTable.addCell(noBorderCell(item.productId().toString(), smallFont));
            itemsTable.addCell(noBorderCell(item.productName(), smallFont));
            itemsTable.addCell(noBorderCell(String.format("R$ %.2f", item.price()), smallFont));

        }

        itemsTable.addCell(noBorderCell(""));
        itemsTable.addCell(noBorderCell(""));
        itemsTable.addCell(noBorderCell(String.format("R$ %.2f", serviceOrderDTO.totalAmount()), smallBoldFont));

        document.add(itemsTable);
    }

    private void addProductsGridTable(Document document, ServiceOrderPdfDTO serviceOrderDTO) throws Exception {

        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(5f);
        table.setSpacingAfter(8f);

        table.setWidths(new float[] { 2, 4, 5, 2 });

        // ===== HEADER =====
        table.addCell(createGridCell("CÓDIGO", boldFont));
        table.addCell(createGridCell("PRODUTO", boldFont));
        table.addCell(createGridCell("DESCRIÇÃO", boldFont));
        table.addCell(createGridCell("QTD", boldFont));

        // ===== DADOS =====
        for (SaleItemPdfDTO item : serviceOrderDTO.saleItems()) {

            table.addCell(createGridCell(item.productId().toString(), normalFont));
            table.addCell(createGridCell(item.productName(), normalFont));
            table.addCell(createGridCell("descricao", normalFont));
            table.addCell(createGridCell(String.valueOf("9"), normalFont));
        }

        // ===== LINHA OBS =====
        PdfPCell obsCell = new PdfPCell(new Phrase("OBS:", boldFont));
        obsCell.setColspan(4);
        obsCell.setBorderWidth(1f);
        obsCell.setPadding(4f);
        obsCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(obsCell);

        document.add(table);
    }

    private PdfPCell createGridCell(String text, Font font) {

        PdfPCell cell = new PdfPCell(new Phrase(text != null ? text : "", font));
        cell.setBorderWidth(1f);
        cell.setPadding(4f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        return cell;
    }

    private PdfPCell headerCellPrescription(String text) {
        Font font = new Font(Font.HELVETICA, 7, Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private PdfPCell valueCellPrescription(String text) {
        Font font = new Font(Font.HELVETICA, 7);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private PdfPCell noBorderCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell noBorderCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    // private ServiceOrderPdfDTO buildDto(Sale sale) {

    //     List<SaleItemPdfDTO> items = sale.getSaleItems()
    //             .stream()
    //             .map(item -> new SaleItemPdfDTO(
    //                     item.getPrice(),
    //                     item.getProduct().getId(),
    //                     item.getProduct().getName()))
    //             .toList();

    //     return new ServiceOrderPdfDTO(
    //             sale.getId(),
    //             sale.getClient().getId(),
    //             sale.getClient().getName(),
    //             sale.getIssueDate(),
    //             sale.getSaleStatus(),
    //             sale.getEstimatedDeliveryDate(),
    //             sale.getTotalAmount(),
    //             items);
    // }
}
