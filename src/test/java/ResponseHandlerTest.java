import com.fire.RequestHandler;
import com.fire.ResponseHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan.alviar on 9/25/14.
 */
public class ResponseHandlerTest {

  public ResponseHandler processRequestHandler(String path) {
    RequestHandler request = new RequestHandler(path);
    return new ResponseHandler(request);
  }

  @Test
  public void ItShouldReadARequest() throws Exception
  {
    RequestHandler request = new RequestHandler("GET / HTTP/1.1");
    ResponseHandler response = new ResponseHandler(request);
    assertEquals(request, response.getRequest());
  }

  @Test
  public void ItShouldReturnAResponse() throws Exception
  {
    ResponseHandler response = processRequestHandler("GET /foobar HTTP/1.1");
    assertEquals("HTTP/1.1 404 Not Found", response.getData());
  }

  @Test
  public void ItShouldReturnA200OK() throws Exception
  {
    ResponseHandler response = processRequestHandler("GET / HTTP/1.1");
    assertEquals("HTTP/1.1 200 OK", response.getData());
  }

  @Test
  public void ItShouldBeAbleToHandlePostMethod() throws Exception
  {
    String postPath = "POST /form HTTP/1.1 \n" + "foo=bar";
    ResponseHandler response = processRequestHandler(postPath);

    assertEquals("HTTP/1.1 200 OK", response.getData());
  }

  @Test
  public void ItShouldBeAbleToHandlePutMethod() throws Exception
  {
    String putPath = "PUT /form HTTP/1.1 \n" + "foo=bar";
    ResponseHandler response = processRequestHandler(putPath);

    assertEquals("HTTP/1.1 200 OK", response.getData());
  }

}
