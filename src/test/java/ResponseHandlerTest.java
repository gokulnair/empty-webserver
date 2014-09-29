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
  public void ItShouldGetAResponse() throws Exception
  {
    ResponseHandler response = processRequestHandler("GET / HTTP/1.1");
    assertEquals("HTTP/1.1 200 OK", response.getResponse("GET", "/", ""));
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
    ResponseHandler response = new ResponseHandler();
    assertEquals("HTTP/1.1 404 Not Found", response.getResponse("GET", "/foobar", ""));
  }

  @Test
  public void ItShouldReturnA200OK() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    assertEquals("HTTP/1.1 200 OK", response.getResponse("GET", "/", ""));
  }

  @Test
  public void ItShouldBeAbleToHandlePostMethod() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    assertEquals("HTTP/1.1 200 OK", response.getResponse("POST", "/form", "foo=bar"));
  }

  @Test
  public void ItShouldBeAbleToHandlePutMethod() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    assertEquals("HTTP/1.1 200 OK", response.getResponse("POST", "/form", "foo=bar"));
  }

  @Test
  public void ItShouldReturn302ForRedirects() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    assertEquals("HTTP/1.1 302 Found\n" +
      "Location: http://localhost:5000", response.getResponse("GET", "/redirect", ""));
  }

  @Test
  public void ItShouldReturnALocationHeaderForRedirects() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("GET", "/redirect", "");
    String header = response.getHeaders("Location");

    assertEquals("http://localhost:5000", header);
  }

  @Test
  public void ItShouldSetHeadersWIthAGivenKeyValue() throws Exception
  {
    ResponseHandler response = processRequestHandler("GET /redirect HTTP/1.1");
    response.setHeader("Location", "localhost:5000");
    assertEquals("localhost:5000", response.getHeaders("Location"));
  }



}
