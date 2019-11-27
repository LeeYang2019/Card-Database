package edu.yang.service;

import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileUploadTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    FileReader newTest;

    @BeforeEach
    void setUp() {
        newTest = new FileReader();
    }

    @Test
    void excelRead() {
        String excelFilePath = "temp/Cards.xlsx";
        List<YugiohCard> list = newTest.excelRead(excelFilePath, null);

        logger.info("Cards from " + excelFilePath);
        for (YugiohCard card: list) {
            logger.info(card.toString());
        }
    }
}