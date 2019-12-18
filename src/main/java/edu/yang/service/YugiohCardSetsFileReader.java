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
    private final String fileName = "cardSets.txt";

    /**
     *
     * @param fileName
     * @return
     */
    private String[] readFile(String fileName) {

        try {

            ClassLoader classLoader = YugiohCardSetsFileReader.class.getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            BufferedReader inputReader =
            new BufferedReader(new FileReader(file));

            while (inputReader.ready()) {
                String line = inputReader.readLine();
                line = line.replace("\n", ",");
                String[] tokens = line.split(",");
                return tokens;
            }
        } catch (FileNotFoundException fileNotFound) {
            logger.info("fileName: " + fileName + " could not be found.");
        } catch (IOException inputOutputException) {
            logger.info("IOException");
        }
        return null;
    }

    /**
     *
     * @param prefix
     * @return
     */
    public String getProductName(String prefix) {

        String tokens[] = readFile(fileName);

        Map<String, String> cardSetsMap = new HashMap<>();

        for (int i = 0; i < tokens.length; i += 2) {
            if (i != tokens.length - 1) {
                cardSetsMap.put(tokens[i], tokens[i + 1]);
            }
        }
        String productName = cardSetsMap.get(prefix);
        logger.info(productName);
        return productName;
    }
}
