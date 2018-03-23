package test.kev.participle;

import kev.participle.TextRead;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TextRead Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/21/2018</pre>
 */
public class TextReadTest {

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
        TextRead.textRead("E:\\a.txt");

    }


} 
