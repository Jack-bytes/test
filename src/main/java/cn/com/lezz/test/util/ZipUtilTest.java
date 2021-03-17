package cn.com.lezz.test.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.*;

public class ZipUtilTest {

    private final String encryptKey = "";

    private final String encryptSwitch = "";


    public File compress(String srcDir, String targetDir, String fileName) throws Exception {
        File srcFile = new File(srcDir);
        if (!srcFile.exists()) {
            return null;
        }

        FileOutputStream os = null;
        CheckedOutputStream cos = null;
        ZipOutputStream zipOut = null;
        File destFile = null;
        try {
            File targetDirectory = new File(targetDir);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }

            destFile = new File(targetDir + fileName);

            os = new FileOutputStream(destFile);
            cos = new CheckedOutputStream(os, new CRC32());

            zipOut = new ZipOutputStream(cos);

            File[] files = srcFile.listFiles();
            for (File file : files) {
                compress(file, zipOut, "");
            }
        } finally {
            if (zipOut != null) {
                zipOut.close();
            }
            if (cos != null) {
                cos.close();
            }
            if (os != null) {
                os.close();
            }
        }
        return destFile;
    }


    public static void compress(File file, ZipOutputStream zipOut, String baseDir) throws Exception {
        if (file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    public static void compressDirectory(File file, ZipOutputStream zipOut, String baseDir) throws Exception {
        File[] files = file.listFiles();
        for (int i = 0, len = files.length; i < len; i++) {
            compress(files[i], zipOut, file.getName() + File.separator);
        }
    }

    public static void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws Exception {
        if (file == null || !file.exists()) {
            return;
        }

        BufferedInputStream bis = null;
        try {
            byte[] b = new byte[1024 * 8];
            bis = new BufferedInputStream(new FileInputStream(file));

            ZipEntry zip = new ZipEntry(baseDir + file.getName());

            zipOut.putNextEntry(zip);

            int count = 0;
            while ((count = bis.read(b)) != -1) {
                zipOut.write(b, 0, count);
            }
        } finally {
            if (bis != null) {
                bis.close();
            }
        }

    }

//-----------------------------------------------------------

    public static void compressFile1(File file, ZipOutputStream zipOut, String baseDir) {
        if (file == null || !file.exists()) {
            return;
        }
        BufferedInputStream bis = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            byte[] b = new byte[fis.available()];
            bis.read(b);

            ZipEntry zip = new ZipEntry(baseDir + file.getName());
            zipOut.putNextEntry(zip);
            zipOut.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (zipOut != null) {
                try {
                    zipOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//---------------------------------------------

    public static String unZip(File file, String unZipDirectory) throws Exception {
        File tempDirectoryFile = new File(unZipDirectory);
        if (!tempDirectoryFile.exists()) {
            tempDirectoryFile.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file);
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file), Charset.forName("GBK"));
        ZipEntry zipEntry = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            String fileName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                File directory = new File(unZipDirectory + fileName);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                continue;
            }
            File orderFile = new File(unZipDirectory + fileName);
            if (!orderFile.exists()) {
                File parent = orderFile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                orderFile.createNewFile();
            }
            OutputStream os = new FileOutputStream(orderFile);
            InputStream is = zipFile.getInputStream(zipEntry);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            os.close();
            is.close();
        }
        zipInputStream.close();
        zipFile.close();
        return unZipDirectory;
    }


    @SuppressWarnings("all")
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        }
        file.delete();
    }




    /**
     * 根据指定的被加密的 file 得到解密之后的 file
     *
     * @param file
     * @return
     */
    public File decryptFile(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        if ("0".equals(encryptSwitch)) {
            return file;
        }
        String fileName;
        try {
            fileName = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            fileName = file.getAbsolutePath();
        }
        String tempFileName = fileName + "temp";
        File tempEncryptFile = new File(tempFileName);
        // 源加密文件重命名
        file.renameTo(tempEncryptFile);

        File decryptFile = new File(fileName);
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(tempEncryptFile));
            bout = new BufferedOutputStream(new FileOutputStream(decryptFile));
            byte[] buffer = new byte[1024];
            byte[] decryptByte = null;
            byte[] decryptKey = SM4.getKeyBytes(encryptKey);
            while (bin.read(buffer) > 0) {
                decryptByte = SM4.decodeSM4(buffer, decryptKey);
                bout.write(decryptByte);
            }
            if (decryptFile.exists()) {
                return decryptFile;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 删除临时文件
            try {
                this.deleteFile(tempEncryptFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据指定的未加密的 file 得到加密之后的 file
     *
     * @param file
     * @return
     */
    public File encryptFile(File file, String fileName) {
        if (file == null || !file.exists()) {
            return null;
        }
        if ("0".equals(encryptSwitch)) {
            return file;
        }
        File encryptFile = new File(fileName);
        File encryptDir = encryptFile.getParentFile();
        if (encryptDir != null && encryptDir.isDirectory() && !encryptDir.exists()) {
            encryptDir.mkdirs();
        }
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(file));
            bout = new BufferedOutputStream(new FileOutputStream(encryptFile));
            byte[] buffer = new byte[1024];
            byte[] encryptByte = null;
            byte[] key = SM4.getKeyBytes(encryptKey);
            while (bin.read(buffer) > 0) {
                encryptByte = SM4.encodeSM4(buffer, key);
                bout.write(encryptByte);
            }
            return encryptFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 删除加密源文件
            try {
                this.deleteFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
