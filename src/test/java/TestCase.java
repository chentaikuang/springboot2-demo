import org.junit.Before;
import org.junit.Test;

public class TestCase {

    @Before
    public void init() throws Exception {
        System.out.println("init");
    }
    @Test
    public void testCreate() throws Exception {
        System.out.println("创建----");
    }
}