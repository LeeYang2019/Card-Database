package edu.yang.service;

import edu.yang.entity.YugiohCard;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUpload {
/*
    public List<Book> readBooksFromExcelFile(String excelFilePath) throws IOException {
        List<Book> listBooks = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Book aBook = new Book();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 1:
                        aBook.setTitle((String) getCellValue(nextCell));
                        break;
                    case 2:
                        aBook.setAuthor((String) getCellValue(nextCell));
                        break;
                    case 3:
                        aBook.setPrice((double) getCellValue(nextCell));
                        break;
                }


            }
            listBooks.add(aBook);
        }

        workbook.close();
        inputStream.close();

        return listBooks;
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }
*/
    public List<YugiohCard> excelRead(String fileName) {


        List<YugiohCard> cardList = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(new File(fileName))) {

            Sheet sheet = wb.getSheetAt(0);

            int rowStart = sheet.getFirstRowNum();
            int rowEnd = sheet.getLastRowNum();

            //row 0 has headings; begin at row 1; increment rowStart and end + 1
            for (int i = rowStart + 1; i < rowEnd + 1; i++) {


                Row row = sheet.getRow(i);
                YugiohCard newYugiohCard = new YugiohCard();

                //for every column in the row
                for (int col = row.getFirstCellNum(); col < row.getLastCellNum(); col++) {
                    Cell cell = row.getCell(col);

                    switch (col) {
                        case 0:
                            newYugiohCard.setCardName(cell.getStringCellValue());
                            break;
                        case 1:
                            newYugiohCard.setCardType(cell.getStringCellValue());
                            break;
                        case 2:
                            newYugiohCard.setCardRarity(cell.getStringCellValue());
                            break;
                        case 3:
                            newYugiohCard.setCardSet(cell.getStringCellValue());
                            break;
                        case 4:
                            newYugiohCard.setIndex(cell.getStringCellValue());
                            break;
                        case 5:
                            newYugiohCard.setPrice(cell.getNumericCellValue());
                            break;
                        case 6:
                            newYugiohCard.setQuantity((int)cell.getNumericCellValue());
                            break;
                        case 7:
                            newYugiohCard.setStatus(cell.getStringCellValue());
                            break;
                    }

                    System.out.println("col : " + col);
                }
                cardList.add(newYugiohCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardList;
    }
}
