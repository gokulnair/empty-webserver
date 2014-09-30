import org.junit.Test;

import com.fire.FileHandler;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by jonathan.alviar on 9/30/14.
 */
public class FileHandlerTest {

  @Test
  public void ItShouldReadAllTheDataInAFile() throws Exception {
    FileHandler file = new FileHandler();
    String currentDir = System.getProperty("user.dir");
    String content = file.read(currentDir+ "/public/file1");
    assertEquals("file1 contents\n" +
                 "More Lines For testing\n" +
                 "Hello World\n" +
                 "test line\n", content);

  }

  @Test
  public void ItShowFolderStrcuture() throws Exception {
    FileHandler fileHandler = new FileHandler();
    String currentDir = System.getProperty("user.dir");
    String folderStruct = fileHandler.getFolderStructure(currentDir + "/public");
    assertEquals("file1\n" +
                 "file2\n" +
                 "image.gif\n" +
                 "image.jpeg\n" +
                 "image.png\n" +
                 "partial_content.txt\n" +
                 "patch-content.txt\n" +
                 "text-file.txt\n", folderStruct);
  }
}
