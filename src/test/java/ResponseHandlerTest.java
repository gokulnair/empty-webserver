import com.fire.RequestHandler;
import com.fire.ResponseHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan.alviar on 9/25/14.
 */
public class ResponseHandlerTest {

  @Test
  public void ItShouldReadARequest() throws Exception
  {
    RequestHandler request = new RequestHandler("GET / HTTP/1.1");
    ResponseHandler response = new ResponseHandler(request);
    assertEquals(request, response.getRequest());
  }

}