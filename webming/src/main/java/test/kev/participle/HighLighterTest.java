package test.kev.participle;

import kev.participle.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * HighLighter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/23/2018</pre>
 */
public class HighLighterTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: highLight(List<Word> keyWordList, String textArticle)
     */
    @Test
    public void testHighLight() throws Exception {
//        String text = TextReadAndWrite.textRead("E:\\a.txt");
//        List<Word> wordList = TextTowordAnsj.textToWord(text);
//        List<Word> wordGraphList = Tools.buildGraphList(wordList, 10);
//        System.out.println(wordList.size() + "---" + wordGraphList.size());
//        wordGraphList = PageRank.pageRank(wordGraphList, 0.001);
//        List<Word> topT = Tools.getTopT(wordGraphList, 10);
//        String highLighttext = HighLighter.highLight(topT, text);
//        TextReadAndWrite.textWrite("E:\\result.html", highLighttext);
    }


} 
