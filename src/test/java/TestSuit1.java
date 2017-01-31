import org.junit.*;

/**
 * Created by devyash on 1/30/17.
 */
public class TestSuit1 {

    @BeforeClass
    public static void beforefunction(){
        System.out.println("This is Before Class TestSuit");
    }

    @Before
    public void before1(){
        System.out.println("This is Before1 TestSuit");
    }

    @Test
    public void test1(){
        System.out.println("This is Test1 TestSuit");
    }

    @Test
    public void test2(){
        System.out.println("This is Test2 TestSuit");
    }


    @After
    public void after1(){
        System.out.println("This is after1 TestSuit");
    }

    @AfterClass
    public static void afterfuntion(){
        System.out.println("This is a after class function TestSuit");
    }

}
