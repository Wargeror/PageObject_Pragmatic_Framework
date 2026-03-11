package utils;

import java.security.SecureRandom;

public class CustomRandomStringGenerator {

    public static String randomAlphaNumeric(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String randomAlphabetic(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String nameGenerator(int length){
        String firstchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String chars = "abcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        sb.append(firstchar.charAt(random.nextInt(firstchar.length())));
        for (int i = 1; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String passwordGenerator(int length){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&?";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        int totalDesiredLength = length * 3;

        for (int i = 0; i < totalDesiredLength; i++) {
            int sequenceStep = i % 3;

            if (sequenceStep == 0) {
                sb.append(letters.charAt(random.nextInt(letters.length())));
            } else if (sequenceStep == 1) {
                sb.append(numbers.charAt(random.nextInt(numbers.length())));
            } else {
                sb.append(symbols.charAt(random.nextInt(symbols.length())));
            }
        }
        String letterPool = sb.toString();
        for (int i = 0; i < length; i++) {
            sb.append(letterPool.charAt(random.nextInt(letterPool.length())));
        }
        return sb.toString();
    }

    public static String randomNumeric(int length) {
        String chars = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String generateEmail(){
        String prefix = CustomRandomStringGenerator.randomAlphaNumeric(9);
        String domain = CustomRandomStringGenerator.randomAlphabetic(6);
        String randomEmailAddress = prefix + "@" + domain + ".com";
        return randomEmailAddress;
    }

}

//apache.commons-lang3
//        String prefix = RandomStringUtils.randomAlphanumeric(9);
//        String domain = RandomStringUtils.randomAlphabetic(6);