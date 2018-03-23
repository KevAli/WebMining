package test.kev.demo;

import kev.demo.Demo03;
import kev.participle.TextReadAndWrite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Demo03 Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/20/2018</pre>
 */
public class Demo03Test {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: testAnanlyzer()
     */
    @Test
    public void testTestAnanlyzer() throws Exception {
        Demo03 demo03 = new Demo03();
        String text = TextReadAndWrite.textRead("E:\\a.txt");
        demo03.testAnanlyzer(text);
    }


} 
