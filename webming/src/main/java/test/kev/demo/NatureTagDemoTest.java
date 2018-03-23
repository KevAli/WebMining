package test.kev.demo;

import kev.demo.NatureTagDemo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * NatureTagDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/20/2018</pre>
 */
public class NatureTagDemoTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: NatureTagDemo()
     */
    @Test
    public void testNatureTagDemo() throws Exception {
        NatureTagDemo.NatureTagDemo();
    }

    /**
     * Method: KeyWordCompuerDemo()
     */
    @Test
    public void testKeyWordCompuerDemo() throws Exception {
        NatureTagDemo.KeyWordCompuerDemo();
    }


} 
