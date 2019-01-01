import io.goeasy.GoEasy;
import org.junit.Test;

public class TestGoEasy {
    @Test
    public void testGoEasy() {
        //发送消息
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-d8c061cbb5c84f6999a30a96a07b44b2");
        goEasy.publish("testhjl", "Hello, GoEasy!");
    }
}
