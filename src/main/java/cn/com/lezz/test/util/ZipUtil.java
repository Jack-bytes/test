package cn.com.lezz.test.util;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.*;
import java.util.List;

public class ZipUtil {

    public void test() throws IOException {
        new ZipFile("").getInputStream(new FileHeader());
    }

    //??????? 可否不生成File, 直接就要一个byte, 处理byte就行,在实际应用中如果要在磁盘生成文件,一是影响性能,二是生成很多垃圾文件



    /**
     * 在指定路径与文件名下生成压缩文件;
     *
     * @param fileName 生成的zip文件的路径及文件名全称;
     * @param files files
     * @throws ZipException 抛异常原因之一是程序繁忙, 这种情况需要处理;
     */
    public static void compressFromFile(String fileName, List<File> files) throws ZipException {
        new ZipFile(fileName).addFiles(files);
    }

    //流添加到Zip中
    //new ZipFile("filename.zip").addStream(inputStream, new ZipParameters());

    /**
     *
     * @param fileName
     * @param inputStream
     * @throws ZipException
     */
    public static void compressFromInputStream(String fileName, InputStream inputStream) throws ZipException {
        new ZipFile(fileName).addStream(inputStream, new ZipParameters());
    }






    //加密AES
    //    ZipParameters zipParameters = new ZipParameters();
    //zipParameters.setEncryptFiles(true);
    //zipParameters.setEncryptionMethod(EncryptionMethod.AES);
    //// Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
    //zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
    //
    //    List<File> filesToAdd = Arrays.asList(
    //            new File("somefile"),
    //            new File("someotherfile")
    //    );
    //
    //    ZipFile zipFile = new ZipFile("filename.zip", "password".toCharArray());
    //zipFile.addFiles(filesToAdd, zipParameters);

//    zipParameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);   这个就是标准的加密,也就是AES256,和上面一样的;






























    public void zipOutputStreamExample(File outputZipFile, List<File> filesToAdd, char[] password,
                                       CompressionMethod compressionMethod, boolean encrypt,
                                       EncryptionMethod encryptionMethod, AesKeyStrength aesKeyStrength)
            throws IOException {

        ZipParameters zipParameters = buildZipParameters(compressionMethod, encrypt, encryptionMethod, aesKeyStrength);
        byte[] buff = new byte[4096];
        int readLen;

        try(ZipOutputStream zos = initializeZipOutputStream(outputZipFile, encrypt, password)) {
            for (File fileToAdd : filesToAdd) {

                // Entry size has to be set if you want to add entries of STORE compression method (no compression)
                // This is not required for deflate compression
                if (zipParameters.getCompressionMethod() == CompressionMethod.STORE) {
                    zipParameters.setEntrySize(fileToAdd.length());
                }

                zipParameters.setFileNameInZip(fileToAdd.getName());
                zos.putNextEntry(zipParameters);

                try(InputStream inputStream = new FileInputStream(fileToAdd)) {
                    while ((readLen = inputStream.read(buff)) != -1) {
                        zos.write(buff, 0, readLen);
                    }
                }
                zos.closeEntry();
            }
        }
    }

    private ZipOutputStream initializeZipOutputStream(File outputZipFile, boolean encrypt, char[] password)
            throws IOException {

        FileOutputStream fos = new FileOutputStream(outputZipFile);

        if (encrypt) {
            return new ZipOutputStream(fos, password);
        }

        return new ZipOutputStream(fos);
    }

    private ZipParameters buildZipParameters(CompressionMethod compressionMethod, boolean encrypt,
                                             EncryptionMethod encryptionMethod, AesKeyStrength aesKeyStrength) {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(compressionMethod);
        zipParameters.setEncryptionMethod(encryptionMethod);
        zipParameters.setAesKeyStrength(aesKeyStrength);
        zipParameters.setEncryptFiles(encrypt);
        return zipParameters;
    }




    public void extractWithZipInputStream(File zipFile, char[] password) throws IOException {
        LocalFileHeader localFileHeader;
        int readLen;
        byte[] readBuffer = new byte[4096];

        InputStream inputStream = new FileInputStream(zipFile);
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream, password)) {
            while ((localFileHeader = zipInputStream.getNextEntry()) != null) {
                File extractedFile = new File(localFileHeader.getFileName());
                try (OutputStream outputStream = new FileOutputStream(extractedFile)) {
                    while ((readLen = zipInputStream.read(readBuffer)) != -1) {
                        outputStream.write(readBuffer, 0, readLen);
                    }
                }
            }
        }
    }





}
