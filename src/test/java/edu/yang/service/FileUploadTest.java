package edu.yang.service;

import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    FileUpload newTest;

    @BeforeEach
    void setUp() {
        newTest = new FileUpload();
    }

    @Test
    void excelRead() {
        String excelFilePath = "temp/Cards.xlsx";
        List<YugiohCard> list = newTest.excelRead(excelFilePath);

        logger.info("Cards from " + excelFilePath);
        for (YugiohCard card: list) {
            logger.info(card.toString());
        }
    }
}