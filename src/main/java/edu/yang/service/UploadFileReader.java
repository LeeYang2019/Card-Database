package edu.yang.service;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class UploadFileReader implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties;

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

    public Map<String, String> readFile() {

        String propertiesFile = "/indieproject.properties";
        properties = loadProperties(propertiesFile);

        String fileName = "docs/cardSets.txt";//properties.getProperty("yugioh.cardSet");

        System.out.println("reading from fileName : " + propertiesFile);
        System.out.println("reading from fileName : " + fileName);

        Map<String, String> cardSetsMap = new HashMap<>();

        try {
            File file = new File(fileName);
            BufferedReader inputReader =
            new BufferedReader(new FileReader(file));

            // while there is a line, store as string and parse
            while (inputReader.ready()) {
                String line = inputReader.readLine();

                System.out.println(line);

                String[] tokens = line.split("\n");

                for (int i = 0; i < tokens.length; i ++) {
                    System.out.println(i + " : " + tokens[i]);
                }


                cardSetsMap = createMap(tokens);
            }
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        } catch (IOException inputOutputException) {
            inputOutputException.printStackTrace();
        }
        return cardSetsMap;
    }

    public Map<String,String> createMap(String[] tokens) {

        Map<String, String> cardSetsMap = new HashMap<>();

        for (int i = 0; i < tokens.length; i++) {
            if (i / 2 == 1) {
                System.out.println("token: " + tokens[i]);
                cardSetsMap.put(tokens[i],tokens[i + 1]);
            }
        }
        return cardSetsMap;
    }
}
