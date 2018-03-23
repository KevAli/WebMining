package test.kev.demo;

import kev.demo.Demo02;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Demo02 Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/20/2018</pre>
 */
public class Demo02Test {

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
        Demo02 demo02 = new Demo02();
        demo02.testAnanlyzer();

    }


} 
