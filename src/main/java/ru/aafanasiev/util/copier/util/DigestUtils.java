/**
 *
 */
package ru.aafanasiev.util.copier.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Electronic signature utility
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 * @version 1.0 2017-03-23
 */
public class DigestUtils {
    /** Symbol for byte half value */
    private static final char[] SYMBOLS = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * Private constructor for utility class
     */
    private DigestUtils() {
    }

    /**
     * Create digest as string for source string
     *
     * @param source Source string
     * @return String sign (digest) as docded string
     * @throws NoSuchAlgorithmException Exception
     */
    public static String stringDigestSHA256(String source) throws NoSuchAlgorithmException {
        if(null == source) {
            return null;
        }

        byte[] hash = digestSHA256(source);

        StringBuilder pwd = new StringBuilder();
        for(byte b : hash) {
            int val = (b) & 0xff;
            int low = val & 0x0f;
            int high = val >> 4;
            pwd.append(SYMBOLS[high]).append(SYMBOLS[low]);
        }

        return pwd.toString();
    }

    /**
     * Create digest as byte array for source string
     *
     * @param source Source string
     * @return String sign (digest) as byte array
     * @throws NoSuchAlgorithmException Exception
     */
    public static byte[] digestSHA256(String source) throws NoSuchAlgorithmException {
        if(null == source) {
            return new byte[] {};
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        return digest.digest(source.getBytes(StandardCharsets.UTF_8));
    }

}
