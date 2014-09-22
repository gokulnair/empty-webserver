
import com.fire.Server;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by charles.zhang on 9/22/14.
 */
public class ServerTest {

  @Test
  public void ItStartsASocket() throws Exception
  {
    MockSeverSocket mockSocket = new MockSeverSocket();
    Server server = new Server(mockSocket);

    server.run();

    assertTrue(mockSocket.Started);
  }




}
