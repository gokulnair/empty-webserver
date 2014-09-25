import com.fire.RequestHandler;
import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

/**
 * Created by charles.zhang on 9/24/14.
 */
public class RequestHandlerTest {

  @Test
  public void ItShouldGetRequestData() throws Exception
  {
    RequestHandler requestHandler = new RequestHandler("GET / HTTP/1.1");
    assertEquals("GET", requestHandler.getMethod());
    assertEquals("/", requestHandler.getUrl());
  }


}
