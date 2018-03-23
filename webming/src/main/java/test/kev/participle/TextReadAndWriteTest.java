package test.kev.participle;

import kev.participle.TextReadAndWrite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TextReadAndWrite Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/21/2018</pre>
 */
public class TextReadAndWriteTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: textRead(String TextPath)
     */
    @Test
    public void testTextRead() throws Exception {
        TextReadAndWrite.textRead("E:\\a.txt");

    }


} 
