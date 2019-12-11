package me.jy.lang;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
@Slf4j
public class CharacterUnicode {

    public static void printUnicode(String character) {

        List<String> unicodeHexString = new ArrayList<>(character.length());
        List<String> unicodeBinaryString = new ArrayList<>(character.length());
        for (char c : character.toCharArray()) {
            unicodeHexString.add(Integer.toHexString(c));
            String binaryString = leftPad(Integer.toBinaryString(c & 0xFFFF), 16);
            Splitter.fixedLength(8).split(binaryString).forEach(unicodeBinaryString::add);
        }
        log.debug("[Unicode] {}: {}", character, leftPad(unicodeHexString, 8));
        log.debug("[Unicode] {}: {}", character, unicodeBinaryString);
    }

    public static void printCharBytes(String character, Charset charset) {
        byte[] bytes = character.getBytes(charset);
        List<String> binaryStrings = new ArrayList<>();
        for (byte b : bytes) {
            binaryStrings.add(leftPad(Integer.toBinaryString(b & 0xFF), 8));
        }
        log.debug("[{}] {}: {}", charset, character, byteToHex(bytes));
        log.debug("[{}] {}: {}", charset, character, binaryStrings);
    }

    private static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(leftPad(Integer.toHexString(b & 0xFF), 2));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "\uD840\uDC32";
        printUnicode(s);
        printCharBytes(s, StandardCharsets.UTF_8);
        printCharBytes(s, StandardCharsets.UTF_16BE);
        printCharBytes(s, Charset.forName("UTF-32"));
    }

    private static String leftPad(List<String> list, int length) {
        return leftPad(String.join("", list), length);
    }

    private static String leftPad(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        return "0".repeat(length - str.length()) + str;
    }

}
