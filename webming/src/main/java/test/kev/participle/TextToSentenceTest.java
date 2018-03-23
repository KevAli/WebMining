package test.kev.participle;

import kev.participle.TextReadAndWrite;
import kev.participle.TextToSentence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TextToSentence Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/21/2018</pre>
 */
public class TextToSentenceTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: textToSentence(String text)
     */
    @Test
    public void testTextToSentence() throws Exception {
        String text = TextReadAndWrite.textRead("E:\\a.txt");
        TextToSentence.textToSentence(text);
    }


} 
