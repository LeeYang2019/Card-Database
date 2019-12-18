package edu.yang.service;

import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    UploadFileReader newTest;

    @BeforeEach
    void setUp() {
        newTest = new UploadFileReader();
    }

    @Test
    void readExcelFileSuccess() {
        String excelFilePath = "temp/Cards.xlsx";
        List<YugiohCard> list = newTest.readExcelFile(excelFilePath, null);
        assertEquals(4, list.size());
    }

}