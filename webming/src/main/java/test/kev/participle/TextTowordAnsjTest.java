package test.kev.participle;

import kev.participle.TextReadAndWrite;
import kev.participle.TextTowordAnsj;
import kev.participle.Tools;
import kev.participle.Word;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * TextTowordAnsj Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/21/2018</pre>
 */
public class TextTowordAnsjTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: textToWord(String text)
     */
    @Test
    public void testTextToWordText() throws Exception {
        String text = TextReadAndWrite.textRead("E:\\a.txt");
        List<Word> wordList = TextTowordAnsj.textToWord(text);
//        for (Word word : wordList) {
//            System.out.println(word);
//        }
        List<Word> wordGraphList = Tools.buildGraphList(wordList, 3);
        System.out.println(wordList.size() + "---" + wordGraphList.size());

//        for (Word word : wordGraphList) {
//            System.out.println(word.getNeighbors());
//        }

//        wordList = Tools.getTopT(wordList, 10);
//        for (Word word : wordList) {
//            System.out.println(word.getPrValue());
//        }
//        System.out.println("-----------------------------------------");
////        Map<Word, Set<Word>> wordMap2 = PageRank.PageRank(wordMap);
//        wordList = Tools.getTopT(wordList, 10);
//        for (Word word : wordList) {
//            System.out.println(word.getPrValue());
//        }

        //TextToSentence.textToSentence(text);

    }

    /**
     * Method: textToWord(List<Sentence> sentenceList)
     */
    @Test
    public void testTextToWordSentenceList() throws Exception {

    }


} 
