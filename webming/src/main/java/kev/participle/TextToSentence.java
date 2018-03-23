package kev.participle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Kevin
 * \* Date: 2018/3/21
 * \* Time: 14:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TextToSentence {
    public static List<Sentence> textToSentence(String text) {
        /*正则表达式：句子结束符*/
        String regEx = "。|：|！|？|[.]|:|;";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        /*按照句子结束符分割句子*/
        String[] sentences = p.split(text);
        /*将句子结束符连接到相应的句子后*/
        //String[] sentences = text.split("。|：|！|？");
        List<Sentence> sentencesList = new ArrayList<Sentence>();
        /*将句子结束符连接到相应的句子后*/
        if(sentences.length > 0)
        {
            int count = 0;
            while(count < sentences.length)
            {
                if(m.find())
                {
                    sentences[count] += m.group();
                }
                count++;
            }
        }
        for (int i = 0; i < sentences.length; i++) {
            sentencesList.add(new Sentence("sentence" + Integer.toString(i), sentences[i]));
            System.out.println(i + "-----" + sentences[i]);
        }
        return sentencesList;
    }
}
