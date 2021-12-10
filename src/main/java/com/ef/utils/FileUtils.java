package com.ef.utils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ErFeng_V
 * @ProjectName: parse_file
 * @Date: 2021/12/10/10:52 上午
 * @Description:
 */
public class FileUtils {

    /**
     * 编码方式
     */
    private static final String ENCODING = "UTF-8";

    /**
     * 获取文件的行
     *
     * @param fileName
     *            文件名称
     * @return List<String>
     */
    public static String getContentByLine(String fileName) {
        StringBuffer lines = new StringBuffer();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            File file=new File(fileName);
            //System.out.println( file.getAbsolutePath());

            // 判断文件是否存在
            if (file.isFile() && file.exists()) {
                read = new InputStreamReader(new FileInputStream(file), ENCODING);
                bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (lineTxt == null || lineTxt.length() == 0) {
                        continue;
                    }
                    lines.append(lineTxt+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (read != null) {
                    read.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return lines.toString();
    }
}