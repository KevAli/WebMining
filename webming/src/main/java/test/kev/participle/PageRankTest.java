package test.kev.participle;

import kev.participle.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * PageRank Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/23/2018</pre>
 */
public class PageRankTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: pageRankOnce(List<Word> wordGraph)
     */
    @Test
    public void testPageRankOnce() throws Exception {
        String text = TextRead.textRead("E:\\a.txt");
        List<Word> wordList = TextTowordAnsj.textToWord(text);
        List<Word> wordGraphList = Tools.buildGraphList(wordList, 3);
        System.out.println(wordList.size() + "---" + wordGraphList.size());
        wordGraphList = PageRank.pageRankOnce(wordGraphList);
        for (Word word : wordGraphList) {
            System.out.println(word.getPrValue());
        }
    }

    /**
     * Method: pageRank(List<Word> wordGraph, double threshold)
     */
    @Test
    public void testPageRank() throws Exception {
        String text = TextRead.textRead("E:\\a.txt");
        List<Word> wordList = TextTowordAnsj.textToWord(text);
        List<Word> wordGraphList = Tools.buildGraphList(wordList, 30);
        System.out.println(wordList.size() + "---" + wordGraphList.size());
        wordGraphList = PageRank.pageRank(wordGraphList, 0.01);
        for (Word word : wordGraphList) {
            System.out.println(word.getValue()+"----"+word.getPrValue());
        }

    }


    /**
     * Method: culThreshold(Map<Word, Double> prMap, List<Word> wordGraph)
     */
    @Test
    public void testCulThreshold() throws Exception {

/* 
try { 
   Method method = PageRank.getClass().getMethod("culThreshold", Map<Word,.class, List<Word>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
