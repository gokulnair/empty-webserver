import org.junit.Test;

import com.fire.FileHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jonathan.alviar on 9/30/14.
 */
public class FileHandlerTest {

  @Test
  public void ItShouldReadAllTheDataInAFile() throws Exception
  {
    FileHandler file = new FileHandler();
    String currentDir = System.getProperty("user.dir");
    String content = file.read(currentDir+ "/public/file1");
    assertEquals("file1 contents\n" +
                 "More Lines For testing\n" +
                 "Hello World\n" +
                 "test line\n", content);

  }


}
