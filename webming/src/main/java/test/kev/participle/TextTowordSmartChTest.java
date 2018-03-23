package test.kev.participle;

import kev.participle.TextReadAndWrite;
import kev.participle.TextTowordSmartCh;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TextTowordSmartCh Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/22/2018</pre>
 */
public class TextTowordSmartChTest {

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

    }

    /**
     * Method: textToWord(List<Sentence> sentenceList)
     */
    @Test
    public void testTextToWordSentenceList() throws Exception {
        String text = TextReadAndWrite.textRead("E:\\a.txt");
        TextTowordSmartCh.textToWord(text);
    }


} 
