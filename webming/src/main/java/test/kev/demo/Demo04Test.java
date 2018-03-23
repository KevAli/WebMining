package test.kev.demo;

import kev.demo.Demo04;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Demo04 Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/20/2018</pre>
 */
public class Demo04Test {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: test()
     */
    @Test
    public void testTest() throws Exception {
        Demo04.test();
    }


} 
