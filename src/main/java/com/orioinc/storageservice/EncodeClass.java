package com.orioinc.storageservice;

import java.util.Random;
import java.util.zip.CRC32;

public class EncodeClass {

    public String encodeKey(String inputText) {
        CRC32 crc32 = new CRC32();
        Random random = new Random();

        int randomDigit = random.nextInt();
        String text = inputText + randomDigit;
        crc32.update(text.getBytes());
        long hash = crc32.getValue();
        String hex = Long.toHexString(hash);
        String shortHash = hex.substring(hex.length() - 6);
        return shortHash;
    }

}
