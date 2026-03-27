package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String randomAlphaNumeric(int length) {
        @SuppressWarnings("SpellCheckingInspection") String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String randomAlphabetic(int length) {
        @SuppressWarnings("SpellCheckingInspection") String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String nameGenerator(int length){
        @SuppressWarnings("SpellCheckingInspection") String firstchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        @SuppressWarnings("SpellCheckingInspection") String chars = "abcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        sb.append(firstchar.charAt(random.nextInt(firstchar.length())));
        for (int i = 1; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String passwordGenerator(int length){
        @SuppressWarnings("SpellCheckingInspection") String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&?";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        StringBuilder pool = new StringBuilder(length);
        int totalDesiredLength = length * 3;

        for (int i = 0; i < totalDesiredLength; i++) {
            int sequenceStep = i % 3;

            if (sequenceStep == 0) {
                pool.append(letters.charAt(random.nextInt(letters.length())));
            } else if (sequenceStep == 1) {
                pool.append(numbers.charAt(random.nextInt(numbers.length())));
            } else {
                pool.append(symbols.charAt(random.nextInt(symbols.length())));
            }
        }
        String letterPool = pool.toString();
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
        String prefix = Utils.randomAlphaNumeric(9);
        String domain = Utils.randomAlphabetic(6);
        return prefix + "@" + domain + ".com";
    }

    /**
     * Captures a screenshot of the current browser window and saves it to a file.
     * The filename includes a timestamp to ensure uniqueness.
     *
     * @param driver   The WebDriver instance.
     * @param testName The name of the test or a descriptive string for the screenshot.
     * @return The absolute path of the saved screenshot, or null if it failed.
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String directory = "target/screenshots/";
        
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File(directory + fileName);

        try {
            FileHandler.createDir(new File(directory));
            FileHandler.copy(screenshotFile, targetFile);
            System.out.println("Screenshot saved to: " + targetFile.getAbsolutePath());
            return targetFile.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
