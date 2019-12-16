package edu.yang.service;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.*;

/**
 * reads files passed in from FielUpload jsp and yugiohCard cardSets information
 * @author nyang
 */
public class UploadFileReader implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * reads upload file from FileUpload jsp and creates YugiohCard objects
     * @param fileName name of upload file
     * @param user remoteUser
     * @return list of yugiohCards
     */
    public List<YugiohCard> readExcelFile(String fileName, Object user) {


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

                }
                int id = newYugiohCardDao.insert(newYugiohCard);

            }
        } catch (Exception e) {
            logger.error(e);
        }
        return cardList;
    }

    /**
     * reads file of yugioh cardSets information
     * @return map of key value pairs
     */
    public Map<String, String> readFile() {

        String fileName = "docs/cardSets.txt";

        Map<String, String> cardSetsMap = new HashMap<>();

        try {
            File file = new File(fileName);
            BufferedReader inputReader =
            new BufferedReader(new FileReader(file));

            // while there is a line, store as string and parse
            while (inputReader.ready()) {
                String line = inputReader.readLine();
                line = line.replace("\n", ",");
                String[] tokens = line.split(",");

                cardSetsMap = createMap(tokens);
                return cardSetsMap;
            }
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        } catch (IOException inputOutputException) {
            inputOutputException.printStackTrace();
        }
        return cardSetsMap;
    }

    /**
     * parse string tokens
     * @param tokens tokens of card sets information
     * @return a map of key value pairs
     */
    private Map<String,String> createMap(String[] tokens) {

        Map<String, String> cardSetsMap = new HashMap<>();

        for (int i = 0; i < tokens.length; i += 2) {
            if (i != tokens.length - 1) {
                cardSetsMap.put(tokens[i], tokens[i + 1]);
            }
        }
        return cardSetsMap;
    }
}
