package cn.com.lezz.test;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ByteTest {

    @Test
    public void test(){
        byte[] bytes = new byte[100];
        bytes[0]  = 0;
        bytes[1]  = 0;
        bytes[2]  = 0;
        bytes[3]  = 0;
        bytes[4]  = 1;

        System.out.println(new String(bytes, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8)[4]);
        System.out.println(bytes[0]);
    }

    /**
     * 获取图片 base64
     */
    @Test
    public void test1(){
        List<String> list = new ArrayList<>(3);
        list.add("");
        list.add("");
        list.add("");
        System.out.println(list.size());
        list.set(0,"aaa");
        list.set(1,"aaa1");
        list.set(2,"aaa2");
        System.out.println(list.toString());





    }

    @Test
    public void test2(){
        File file = new File("D:/cache/test/新建文件夹");
        deleteFile(file);


    }
    private void deleteFile(File file){
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files){
                deleteFile(f);
            }
        }
        file.delete();
    }

    @Test
    public void test3(){
        String s = "RlBNKzkFFhKYoEPZJEq44EEdJ6G4wEYFFdXYyF45Fi2YsF1hJpl4sGyZFTx4y2y1FTRIynXlJTOokGbpJE9YmHS5FM+4oIfVJMOomJ3VJL1YsGBFJ61I2JlBJ64Y2FHdJFdYwKCVFUBYuLPJFLpYsLRJFbpIsLY1FUU4uLxNJj5YuNQhJz4YwOsBFzg4mNTpFMFIsMTFJUc4qNQpFU4ooNJBFcAomNgdFkRIkPzRFcF4qezpJMWIuP1VFcYIwQ0WJeh4wP7tFcPYqREaJmwIoRwaFtK4mC5mFtQYwSPmFex4sCiWFdqYyCsmFeWowDTuFW+YuDsKJutYuEMiFm9YwEsaFuhIuAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABMAJhYhwdg9FxFU0o82EkFGDC0wY1MLIhBhqrAlERCdySYg0ZkBRwEQITgeAYG8m3JAVEXQNldgVQBFADETk1QAAaLBXhARBB0fNFGllgwAsdm9Z0RGnLwOEBGkt0kAoOoAAAAAAAAAAAAAAAAAAAA=\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
        System.out.println(s);
        System.out.println(s.trim());

    }

    @Test
    public void test4(){
        BigDecimal b = new BigDecimal("0.01");
        b = b.movePointRight(2);
        long a = b.longValue();
        System.out.println(a);



    }

}
