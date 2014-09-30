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
  public void ItShouldReadAFile() throws Exception
  {
    FileHandler file = new FileHandler();
    List<String> content = file.read("/Users/jonathan.alviar/tmol/8thLightTrain/cob_spec/public/file1");
    if (content.size() > 0) {
      assertEquals("file1 contents", content.get(0));
    } else {
      assertTrue(false);
    }
  }



}
