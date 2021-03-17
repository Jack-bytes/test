package cn.com.lezz.test;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cn.com.lezz.test.entity.Criterion;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test11 {

    @Test
    public void test() {
        String[] s = {"a", "b", "c", "d", "e", "f", "a"};
        Set<String> set = new HashSet<>(Arrays.asList(s));
        System.out.println(set);
    }

    @Test
    public void test1() {
        File directory = new File("D:/其他/desktopBackground");
        File[] subFile = directory.listFiles();
        for (File file : subFile) {

            System.out.println(file.getName());
        }
    }

    @Test
    public void test2() {
        String s = new SimpleDateFormat("yyyy-MM").format(new Date());
        System.out.println(s);

    }

    @Test
    public void test3() throws ParseException {
        String token = JWT.create()
                .withClaim("loginName", "admin")
                .withClaim("id", "4028938170af22c90170af23262a0041")
                .withExpiresAt(new SimpleDateFormat("yyyyMMdd").parse("20201201"))
                .sign(Algorithm.HMAC256("123456"));
        System.out.println(token);
    }

    @Test
    public void test4() {

        String array = "[\"10\",\"2\",\"3\"]";
        //String[] s = JSON.parseObject(array, String[].class);
        Set<String> set = JSON.parseObject(array, Set.class);
        System.out.println(set);

        for (Object o : set) {
            System.out.println(o.getClass().getSimpleName());
        }
    }

    @Test
    public void test5() {

        String s = "{  \"actPrice\": 0,  \"categoryFlat\": 0,  \"categoryPeak\": 0,  \"categoryValley\": 0,  \"cityId\": 530100,  \"criterionDate\": \"2020-10-31T16:00:00.000Z\",  \"criterionType\": \"大工业\",  \"govFund\": 0,  \"groupId\": [\"4098cd8f9bce11ea88fbfa163e640425\", \"649854a565575fa63db92a496d07bd57\"],  \"id\": \"\",  \"lineLoss\": 0,  \"monthlyPrice\": 0,  \"remark\": \"\",  \"section\": \"0-1千伏\",  \"state\": 0,  \"tadPrice\": 0}";

        Criterion c = JSON.parseObject(s, Criterion.class);
        System.out.println(c.getGroupId());
    }

    @Test
    public void test6(){


//        System.out.println(System.getenv());
//        String a = new String("1");
//
//        System.out.println("1" == a);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>(list);
        cowList.removeIf(item -> item.equals("2"));
        list = cowList;
        System.out.println(list);
        System.out.println(cowList);



    }

}
