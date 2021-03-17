package cn.com.lezz.test;

import cn.com.lezz.test.util.ZipUtil;
import cn.com.lezz.test.util.ZipUtilTest;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

public class ZipTest {


    @Test
    public void test1() throws ZipException {

        List<File> files = new ArrayList<>(2);
        files.add(new File("D:/其他/desktopBackground/1.jpg"));
        files.add(new File("D:/其他/desktopBackground/2.png"));
        ZipUtil.compressFromFile("D:/其他/desktopBackground/pic.zip", files);
    }


    @Test
    public void test2() throws Exception {

        OutputStream outputStream = new FileOutputStream(new File("D:/其他/xx.zip"));
        ZipOutputStream out = new ZipOutputStream(outputStream);
        ZipUtilTest.compress(new File("D:/其他/desktopBackground"), out, "test/aa/");
        out.close();
    }

    @Test
    public void test3() throws Exception {
        ZipUtilTest.unZip(new File("D:/其他/xx.zip"), "D:/其他/新建文件夹/");
    }


}
