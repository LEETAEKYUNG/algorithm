package excelTest.sxssf;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRowImpl;

public class AligningCells {

    public static void main(String[] args) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet = wb.createSheet();
        XSSFRow row = sheet.createRow((short) 2);
        row.setHeightInPoints(30);
        for (int i = 0; i < 8; i++) {
            //column width is set in units of 1/256th of a character width
            sheet.setColumnWidth(i, 256 * 15);
        }

        createCell(wb, row, (short) 0, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_BOTTOM);
        createCell(wb, row, (short) 1, XSSFCellStyle.ALIGN_CENTER_SELECTION, XSSFCellStyle.VERTICAL_BOTTOM);
        createCell(wb, row, (short) 2, XSSFCellStyle.ALIGN_FILL, XSSFCellStyle.VERTICAL_CENTER);
        createCell(wb, row, (short) 3, XSSFCellStyle.ALIGN_GENERAL, XSSFCellStyle.VERTICAL_CENTER);
        createCell(wb, row, (short) 4, XSSFCellStyle.ALIGN_JUSTIFY, XSSFCellStyle.VERTICAL_JUSTIFY);
        createCell(wb, row, (short) 5, XSSFCellStyle.ALIGN_LEFT, XSSFCellStyle.VERTICAL_TOP);
        createCell(wb, row, (short) 6, XSSFCellStyle.ALIGN_RIGHT, XSSFCellStyle.VERTICAL_TOP);

        //center text over B4, C4, D4
        row = sheet.createRow((short) 3);
        centerAcrossSelection(wb, row, (short) 1, (short) 3, XSSFCellStyle.VERTICAL_CENTER);
        
        //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol) 
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 3));
        sheet.autoSizeColumn(1, true);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("D:\\excelAlignTest.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     */
    private static void createCell(XSSFWorkbook wb, XSSFRow row, short column,
            short halign, short valign) {
        XSSFCell cell = row.createCell(column);
        cell.setCellValue(new XSSFRichTextString("Align It"));
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(cellStyle);
        
    }

    /**
     * Center a text over multiple columns using ALIGN_CENTER_SELECTION
     *
     * @param wb the workbook
     * @param row the row to create the cell in
     * @param start_column  the column number to create the cell in and where the selection starts
     * @param end_column    the column number where the selection ends
     * @param valign the horizontal alignment for the cell.
     *
     * @author Cristian Petrula, Romania
     */
    private static void centerAcrossSelection(XSSFWorkbook wb, XSSFRow row,
            short start_column, short end_column, short valign) {

        // Create cell style with ALIGN_CENTER_SELECTION
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        cellStyle.setVerticalAlignment(valign);

        // Create cells over the selected area
        for (int i = start_column; i <= end_column; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
        }

        // Set value to the first cell
        XSSFCell cell = row.getCell(start_column);
        cell.setCellValue(new XSSFRichTextString("Align It 11111111111111111122222222222222222222222333333333333333"));

        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(cellStyle);
        
        // Make the selection
        CTRowImpl ctRow = (CTRowImpl) row.getCTRow();
        List spanList = new ArrayList();

        // Add object with format start_coll:end_coll. For example 1:3 will span from
        // cell 1 to cell 3, where the column index starts with 0
        //
        // You can add multiple spans for one row
        Object span = start_column + ":" + end_column;
        spanList.add(span);

        //add spns to the row
        ctRow.setSpans(spanList);
    }
} 