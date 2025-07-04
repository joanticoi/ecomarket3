package com.example.ecomarket.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class BlowfishUtil {

    private static final String ALGORITHM = "Blowfish";
    private static final String MODE = "Blowfish/CBC/PKCS5Padding";
    private static final int BLOCK_SIZE = 8;

    public static String encrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(MODE);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

        byte[] iv = new byte[BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());

        byte[] encryptedWithIV = new byte[BLOCK_SIZE + encrypted.length];
        System.arraycopy(iv, 0, encryptedWithIV, 0, BLOCK_SIZE);
        System.arraycopy(encrypted, 0, encryptedWithIV, BLOCK_SIZE, encrypted.length);

        return Base64.getEncoder().encodeToString(encryptedWithIV);
    }

    public static String decrypt(String encryptedData, String key) throws Exception {
        byte[] encryptedWithIV = Base64.getDecoder().decode(encryptedData);

        byte[] iv = new byte[BLOCK_SIZE];
        byte[] encrypted = new byte[encryptedWithIV.length - BLOCK_SIZE];
        System.arraycopy(encryptedWithIV, 0, iv, 0, BLOCK_SIZE);
        System.arraycopy(encryptedWithIV, BLOCK_SIZE, encrypted, 0, encrypted.length);

        Cipher cipher = Cipher.getInstance(MODE);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted);
    }
}
