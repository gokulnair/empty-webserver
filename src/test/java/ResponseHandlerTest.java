import com.fire.ResponseHandler;
import org.junit.Test;

import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan.alviar on 9/25/14.
 */
public class ResponseHandlerTest {

  @Test
  public void ItShouldGetAResponse() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("GET", "/a", "");
    assertEquals("HTTP/1.1 200 OK", response.getOperation());
  }

  @Test
  public void ItShouldReturnAResponse() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("GET", "/foobar", "");
    assertEquals("HTTP/1.1 404 Not Found", response.getOperation());
  }

  @Test
  public void ItShouldReturnA200OK() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("GET", "/a", "");
    assertEquals("HTTP/1.1 200 OK", response.getOperation());
  }

  @Test
  public void ItShouldBeAbleToHandlePostMethod() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("POST", "/form", "foo=bar");
    assertEquals("HTTP/1.1 200 OK", response.getOperation());
  }

  @Test
  public void ItShouldBeAbleToHandlePutMethod() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("POST", "/form", "foo=bar");
    assertEquals(200, response.getStatusCode());
  }

  @Test
  public void ItShouldReturn302ForRedirects() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("GET", "/redirect", "");
    assertEquals(302, response.getStatusCode());
    assertEquals("Location: http://localhost:5000/", response.getHeaders());
  }

  @Test
  public void ItShouldReturnALocationHeaderForRedirects() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("GET", "/redirect", "");
    String header = response.getHeader("Location");

    assertEquals("http://localhost:5000/", header);
  }

  @Test
  public void ItShouldHandle405CodesForAGivenMethod() throws Exception
  {
    ResponseHandler response = new ResponseHandler();

    response.getResponse("PUT", "/file1", "");
    assertEquals("HTTP/1.1 405 Method Not Allowed",
      response.getOperation());

    response.getResponse("POST", "/text-file.txt", "");
    assertEquals("HTTP/1.1 405 Method Not Allowed",
      response.getOperation());
  }

  @Test
  public void ItShouldAddAllowHeaderToResponse() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.getResponse("OPTIONS", "/method_options", "");
    String header = response.getHeader("Allow");
    assertEquals("GET,HEAD,POST,OPTIONS,PUT", header);
  }

  @Test
  public void ItShouldSetHeadersWIthAGivenKeyValue() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.setHeader("Location", "localhost:5000");
    assertEquals("localhost:5000", response.getHeader("Location"));
  }

  @Test
  public void ItShouldReturnAStringOfHeaders() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.setHeader("Location", "localhost:5000");
    response.setHeader("Content-Type", "application/JSON");
    assertEquals("Location: localhost:5000\nContent-Type: application/JSON",
      response.getHeaders());
  }

  @Test
  public void ItShouldGetBodyResponse() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    response.setBodyResponse("test");
    assertEquals("test", new String(response.getBodyResponse()));
  }

  @Test
  public void ItShouldBuildTheResponseString() throws Exception
  {
    ResponseHandler response = new ResponseHandler();

  }

  @Test
  public void ItShouldDecodeParameters() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    assertEquals("abc", response.decodeParameters(URLEncoder.encode("abc", "UTF-8")));
  }

  @Test
  public void ItShouldGetQuery() throws Exception
  {
    ResponseHandler response = new ResponseHandler();
    assertEquals("name=networking", response.getQuery("/index.html?name=networking#DOWNLOADING"));
  }

}
