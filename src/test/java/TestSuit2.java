import org.junit.*;

/**
 * Created by devyash on 1/30/17.
 */
public class TestSuit2 {

    @BeforeClass
    public static void beforefunction(){
        System.out.println("This is Before Class TestSuit2");
    }

    @Before
    public void before1(){
        System.out.println("This is Before1 TestSuit2");
    }

    @Test
    public void test1(){
        System.out.println("This is Test1 TestSuit2");
    }

    @Test
    public void test2(){
        System.out.println("This is Test2 TestSuit2");
    }


    @After
    public void after1(){
        System.out.println("This is after1 TestSuit2");
    }

    @AfterClass
    public static void afterfuntion(){
        System.out.println("This is a after class function TestSuit2");
    }
}
