package cn.com.lezz.test.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.util.Random;

public class DesUtils {
    public static final String ALGORITHM_DES = "DES/ECB/PKCS5PADDING";

    public static final String CHIPHER_ALGORITHM = "AES/ECB/NoPadding";

    public static final String RANDOM_KEY="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%^&*";
    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws InvalidAlgorithmParameterException
     * @throws Exception
     */
    public static String encode(String key,String data) {
        if(data == null)
            return null;
        try{
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(data.getBytes());
            return byte2hex(bytes);
        }catch(Exception e){
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static String decode(String key,String data) {
        if(data == null)
            return null;
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(hex2byte(data.getBytes())));
        } catch (Exception e){
            e.printStackTrace();
            return data;
        }
    }

    /**
     * 二行制转字符串
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if((b.length%2)!=0)
            throw new IllegalArgumentException();
        byte[] b2 = new byte[b.length/2];
        for (int n = 0; n < b.length; n+=2) {
            String item = new String(b,n,2);
            b2[n/2] = (byte)Integer.parseInt(item,16);
        }
        return b2;
    }


    /**
     * AES 加密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] encrypt4AES(String content, String password) {
        try {
            byte[] enCodeFormat = password.getBytes();
            if(enCodeFormat.length == 16 && content != null) {
                int additional = 16 - content.getBytes().length % 16;

                StringBuffer buffer = new StringBuffer();
                buffer.append(content);
                for(int i = 0; i < additional; i++) {
                    buffer.append(" ");
                }

                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                Cipher cipher = Cipher.getInstance(CHIPHER_ALGORITHM);

//				byte[] byteContent = content.getBytes();
                byte[] byteContent = buffer.toString().getBytes();
                cipher.init(Cipher.ENCRYPT_MODE, key);

                byte[] result = cipher.doFinal(byteContent);
                return result;

            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * AES 解密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] decrypt4AES(byte[] content, String password) {
        try {
            byte[] enCodeFormat = password.getBytes();
            if(enCodeFormat.length == 16) {
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                Cipher cipher = Cipher.getInstance(CHIPHER_ALGORITHM);

                cipher.init(Cipher.DECRYPT_MODE, key);

                byte[] result = cipher.doFinal(content);

                return result;
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static byte[] decrypt4AES(String content, String password) {
        try {
            byte[] enCodeFormat = password.getBytes();
            if(enCodeFormat.length == 16) {
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                Cipher cipher = Cipher.getInstance(CHIPHER_ALGORITHM);

                cipher.init(Cipher.DECRYPT_MODE, key);

                byte[] result = cipher.doFinal(content.getBytes());

                return result;
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * des加密生成随机八位key
     */
    public static String getRandomKey(){

        Random random = new Random();
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(RANDOM_KEY.length());
            sBuffer.append(RANDOM_KEY.charAt(number));
        }
        return sBuffer.toString();
    }

}
