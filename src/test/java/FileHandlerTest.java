import org.junit.Test;

import com.fire.FileHandler;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

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

  @Test
  public void ItShowFolderStrcutureUrl() throws Exception {
    FileHandler fileHandler = new FileHandler();
    String currentDir = System.getProperty("user.dir");
    String folderStruct = fileHandler.getFolderStructureUrl(currentDir + "/public");

    assertEquals("<a href=\"file1\">file1</a>\n" +
                 "<a href=\"file2\">file2</a>\n" +
                 "<a href=\"image.gif\">image.gif</a>\n" +
                 "<a href=\"image.jpeg\">image.jpeg</a>\n" +
                 "<a href=\"image.png\">image.png</a>\n" +
                 "<a href=\"partial_content.txt\">partial_content.txt</a>\n" +
                 "<a href=\"patch-content.txt\">patch-content.txt</a>\n" +
                 "<a href=\"text-file.txt\">text-file.txt</a>\n", folderStruct);
  }

  @Test
  public void ItShouldCheckIfAFileExist() throws Exception
  {
    FileHandler fileHandler = new FileHandler();
    String currentDir = System.getProperty("user.dir");
    String file = currentDir + "/public/file1";
    assertTrue(fileHandler.exists(file));
  }
}
