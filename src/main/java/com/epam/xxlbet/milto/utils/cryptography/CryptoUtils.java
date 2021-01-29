package com.epam.xxlbet.milto.utils.cryptography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * CryptoUtils.
 *
 * @author Aliaksei Milto
 */
public class CryptoUtils {
    private static final Logger LOG = LoggerFactory.getLogger(CryptoUtils.class);

    private static SecretKeySpec secretKey;
    private static byte[] key;

    /**
     * Sets secretKey for encryption and decryption.
     */
    private static void setKey(String myKey) {
        MessageDigest sha;
        try {
            key = myKey.getBytes(UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Something went wrong while executing CryptoUtils.setKey()", e);
        }
    }

    /**
     * Encrypt given string using secret key.
     * Secret key for decryption and encryption should be the same.
     * If any exception occurred, return null as the result.
     *
     * @param strToEncrypt string to encrypt
     * @param secret secret key
     * @return Encrypted string
     */
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(UTF_8)));
        } catch (Exception e) {
            LOG.error("Error while encrypting at CryptoUtils.encrypt: ", e);
        }
        return null;
    }

    /**
     * Decrypt given string using secret key.
     * Secret key for decryption and encryption should be the same.
     * If any exception occurred, return null as the result.
     *
     * @param strToDecrypt string to encrypt
     * @param secret secret key
     * @return Decrypted string
     */
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            LOG.error("Error while decrypting CryptoUtils.decrypt:", e);
        }
        return null;
    }
}
