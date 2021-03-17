package cn.com.lezz.test;

import cn.com.lezz.test.util.DesUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

public class GeneratePassword {

    @Test
    public void test(){
        String encodeStr = DesUtils.encode("ynej@nra6666", "NRA_RMS4");
        String password = new String(Base64.encodeBase64(encodeStr.getBytes()));
        System.out.println(password);
    }
}
