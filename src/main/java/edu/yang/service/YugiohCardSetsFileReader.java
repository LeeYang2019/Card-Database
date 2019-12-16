package edu.yang.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * reads cardSets.txt file
 * parses tokens and creates hashMap with key-value pairs
 * @author nyang
 */
public class YugiohCardSetsFileReader {

    private final Logger logger = LogManager.getLogger(this.getClass());

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

            while (inputReader.ready()) {
                String line = inputReader.readLine();
                line = line.replace("\n", ",");
                String[] tokens = line.split(",");

                cardSetsMap = createMap(tokens);
                return cardSetsMap;
            }
        } catch (FileNotFoundException fileNotFound) {
            logger.info("fileName: " + fileName + " could not be found.");
        } catch (IOException inputOutputException) {
            logger.info("IOException");
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
