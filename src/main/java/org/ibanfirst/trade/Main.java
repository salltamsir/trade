package org.ibanfirst.trade;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ibanfirst.trade.mapper.CustomObjectMapper;
import org.ibanfirst.trade.model.Trade;
import org.ibanfirst.trade.parser.TradeParser;
import org.ibanfirst.trade.response.ErrorResponse;
import org.ibanfirst.trade.response.TradeResponse;
import org.ibanfirst.trade.validator.TradeValidatorImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
           System.err.println("Invalid number of argument");
           System.exit(1);
        }
        String path = args[0];
        String fileContent = readFileAsString(path);
        if (fileContent == null || fileContent.isEmpty()) {
            System.err.println("File is empty");
            System.exit(1);
        }
        parseString(fileContent);
    }

    public static String readFileAsString(String filePath) {
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            System.err.printf("File \"%s\" is not found \n", e.getMessage());
            System.exit(1);
        }
        return new String(fileBytes);
    }
    private static void parseString(String fileContent) throws JsonProcessingException {
        TradeParser tradeParser = new TradeParser(new TradeValidatorImpl());
        try {
            Trade trade = tradeParser.tradeFromString(fileContent);
            System.out.println(CustomObjectMapper.getInstance().writeValueAsString(TradeResponse.fromTrade(trade)));
        } catch (IllegalArgumentException illegalArgumentException){
            System.err.println(CustomObjectMapper.getInstance().writeValueAsString(new ErrorResponse(illegalArgumentException.getMessage())));
            System.exit(1);
        }
    }
}
