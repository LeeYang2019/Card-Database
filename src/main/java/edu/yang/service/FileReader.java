package edu.yang.service;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public List<YugiohCard> excelRead(String fileName, Object user) {

        logger.info("name of file coming in: " + fileName);
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);

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

                    newYugiohCard.setUser((User)user);
                    logger.info(newYugiohCard.toString());
                }
                int id = newYugiohCardDao.insert(newYugiohCard);
                logger.info("id : " +  id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardList;
    }
}
