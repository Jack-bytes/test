package cn.com.lezz.test;

import org.junit.jupiter.api.Test;

import java.io.*;

public class FileMergeTest {

    @Test
    public void test() throws IOException {
        File dir = new File("D:\\cache\\sql22\\uns");
        File[] files = dir.listFiles();


        FileWriter fileWriter = new FileWriter("D:\\cache\\sql22\\uns\\unsMerge.sql");
        BufferedWriter writer = new BufferedWriter(fileWriter);

        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            writer.write("-- 分隔线------------"+ file.getName() +"-------------------------------------------------");
            writer.write(13);
            writer.write(10);

            writer.write(13);
            writer.write(10);

            String line;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.write(13);
                writer.write(10);
            }
            writer.write(13);
            writer.write(10);

            writer.write(13);
            writer.write(10);
            writer.write(13);
            writer.write(10);
            writer.flush();


        }
    }

}
