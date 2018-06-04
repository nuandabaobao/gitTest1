package com.tbjj.portal.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChengZhenxing on 2017/3/20.
 */
public class FileUtil {
    public static final String lineSeparator = "\r\n";

    public static List<List<String>> readFileByLine(InputStream inputStream, String regEx) throws IOException {
        List<List<String>> fileStrList = new ArrayList<>();
        if (null != inputStream) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String temp = null;
                temp = reader.readLine();
                while (temp != null) {
                    String[] lineStr = temp.split(StringEscapeUtils.unescapeJava(regEx), -1);
                    List<String> lineList = Arrays.asList(lineStr);
                    fileStrList.add(lineList);
                    temp = reader.readLine();
                }
            }
        }
        return fileStrList;
    }

    public static File generateFile(String path, List<List<String>> content, String regEx) throws IOException {
        UUID uuid = UUID.randomUUID();
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(path + File.separator + uuid.toString() + ".txt");
        if (file.createNewFile()) {
            writeFileContent(file, content, StringEscapeUtils.unescapeJava(regEx));
            return file;
        } else {
            throw new IOException("创建文件失败");
        }
    }

    private static void writeFileContent(File file, List<List<String>> content, String regEx) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file); PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
            String fileContent = getContentString(content, StringEscapeUtils.unescapeJava(regEx));
            printWriter.write(fileContent.toCharArray());
            printWriter.flush();
        }
    }

    public static String getContentString(List<List<String>> content, String regEx) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < content.size(); i++) {
            for (int j = 0; j < content.get(i).size(); j++) {
                builder.append(content.get(i).get(j));
                if (j < content.get(i).size() - 1) {
                    builder.append(StringEscapeUtils.unescapeJava(regEx));
                }
            }
            builder.append(lineSeparator);
        }
        return builder.toString();
    }

//    public static void main(String[] args) {
//        File f = new File("E:" + File.separator + "test.txt");
//        try (InputStream inputStream = new FileInputStream(f) {
//        }) {
//            String regEx = "\t";
//            List<List<String>> result = readFileByLine(inputStream, regEx);
//
//            generateFile("e:\\import-order", result, regEx);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
