package cn.com.lezz.test.util;

import java.util.Arrays;

public class SM4 {

    private static final String ENCRYPT_KEY="YNEJKJ_P@ssW0rd#";
    /**
     * 加密
     * @param plain
     * @param key
     * @return
     */
    public static byte[] encodeSM4(byte[] plain, byte[] key) {
        if (plain == null || plain.length == 0) {
            return null;
        }
        byte[] encodeValue = formatPlain(plain);
        byte[] cipher = new byte[encodeValue.length];

        SM4Base sm4 = new SM4Base();
        if (sm4.sm4(encodeValue, key, cipher, SM4Base.ENCRYPT) > 0) {
            return cipher;
        } else {
            return null;
        }
    }

    /**
     * 解密
     * @param cipher
     * @param key
     * @return
     */
    public static byte[] decodeSM4(byte[] cipher, byte[] key) {
        int cipherLen = cipher.length;
        if (cipher == null || cipherLen == 0) {
            return null;
        }
        byte[] plain = new byte[cipherLen];

        SM4Base sm4 = new SM4Base();

        if (sm4.sm4(cipher, key, plain, SM4Base.DECRYPT) > 0) {
            return plain;
        } else {
            return null;
        }
    }

    /**
     * 获取key的byte数组
     * @param key
     * @return
     */
    public static byte[] getKeyBytes(String key) {
        if (key== null || key.isEmpty()) {
            key = ENCRYPT_KEY;
        }
        byte[] keyBytes = key.getBytes();
        if (keyBytes.length != 16) {
            return Arrays.copyOf(keyBytes, 16);
        }
        return keyBytes;
    }

    /**
     * 格式化加密的byte数组长度为16的整数倍
     * @param plain
     * @return
     */
    private static byte[] formatPlain(byte[] plain) {
        int plainLen = plain.length;
        int modValue = plainLen % SM4Base.BLOCK;
        if (modValue == 0) {
            return plain;
        }

//		byte[] newPlain = new byte[plainLen + SM4Base.BLOCK - modValue];
        return Arrays.copyOf(plain, plainLen + SM4Base.BLOCK - modValue);
    }
}
