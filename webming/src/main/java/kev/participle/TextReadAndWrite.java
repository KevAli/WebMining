package kev.participle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/21
 * \* Time: 13:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TextReadAndWrite {
    public static String textRead(String textPath) {
        File textFile = new File(textPath);
        BufferedReader reader = null;
        StringBuffer text = new StringBuffer();
        String tempString = null;
        try {
            reader = new BufferedReader(new FileReader(textFile));
            while ((tempString = reader.readLine()) != null) {
                text.append(tempString);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        System.out.println(text);

        return text.toString();
    }

    public static void textWrite(String textPath, String text) {
        try {
            FileWriter fw = new FileWriter(textPath);
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
