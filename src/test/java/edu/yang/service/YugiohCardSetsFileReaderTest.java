package edu.yang.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class YugiohCardSetsFileReaderTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    YugiohCardSetsFileReader newTest;

    @BeforeEach
    void setUp() {
        newTest = new YugiohCardSetsFileReader();
    }

    @Test
    void readCardSetFileSuccess() {
        String fileName = "cardSets.txt";
        Map<String, String> cardSetsMap = newTest.readFile(fileName);
        assertEquals(425, cardSetsMap.size());
    }

}