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
  public void ItShouldGetRequestMethod() throws Exception
  {
    RequestHandler requestHandler = new RequestHandler("GET / HTTP/1.1");
    assertEquals("GET", requestHandler.getMethod());
  }

  @Test
  public void ItShouldSetRequestMethod() throws Exception
  {
    RequestHandler requestHandler = new RequestHandler("GET / HTTP/1.1");
    assertEquals("GET", requestHandler.getMethod() );
  }

  @Test
  public void ItShouldParseRequestCorrectly() throws Exception
  {
    RequestHandler requestHandler = new RequestHandler("GET / HTTP/1.1");
    requestHandler.parse("POST /form HTTP/1.1 ");
    assertEquals("POST", requestHandler.getMethod());
    assertEquals("/form", requestHandler.getPath());

    requestHandler.parse("");
    assertEquals("POST", requestHandler.getMethod());
    assertEquals("/form", requestHandler.getPath());
  }


}
