import com.fire.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by charles.zhang on 9/24/14.
 */
public class RequestTest {

  @Test
  public void ItGetHttpMethod() throws Exception {
    Request request = new Request("GET", "url");
    assertEquals("GET", request.method());
    assertEquals("url", request.url());
  }
}
