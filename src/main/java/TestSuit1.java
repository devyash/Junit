import org.junit.*;

/**
 * Created by devyash on 1/30/17.
 */
public class TestSuit1 {
    @BeforeClass
    public static void beforefunction(){
        System.out.println("This is Before Class");
    }

    @Before
    public void before1(){
        System.out.println("This is Before1");
    }

    @Test
    public void test1(){
        System.out.println("This is Test1");
    }

    @Test
    public void test2(){
        System.out.println("This is Test2");
    }


    @After
    public void after1(){
        System.out.println("This is after1");
    }

    @AfterClass
    public static void afterfuntion(){
        System.out.println("This is a after class function");
    }

}
