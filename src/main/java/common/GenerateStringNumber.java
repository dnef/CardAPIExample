package common;

import java.util.Random;
import java.util.stream.Collectors;

public class GenerateStringNumber {
    public static String generate(int length){
        String symbols = "1234567890";
        String random = new Random().ints(length, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        return symbols;
    }
}
