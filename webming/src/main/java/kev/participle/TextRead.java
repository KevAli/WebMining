package kev.participle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/21
 * \* Time: 13:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TextRead {
    public static String textRead(String TextPath) {
        File textFile = new File(TextPath);
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
        System.out.println(text);

        return text.toString();
    }
}
