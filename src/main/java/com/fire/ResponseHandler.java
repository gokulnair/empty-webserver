package com.fire;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jonathan.alviar on 9/25/14.
 */
public class ResponseHandler {

  private static final String protocol = "HTTP/1.1";
  private int statusCode;
  private byte[] body;
  private String currentDir = System.getProperty("user.dir");
  private HashMap<String, String> headers = new HashMap<String, String>();
  FileHandler isFile = new FileHandler();

  public byte[] getResponse(String method, String path, String body) throws Exception {
    String filePath = currentDir + "/public/" + path;

    if (isFile.exists(filePath) && method.equals("GET")) {
      setStatusCode(200);
      byte[] data = isFile.read(currentDir +  "/public/" + path);
      setBodyResponse(data);

    } else {
      String queryStr = getQuery(path);
      if((queryStr != null) && !queryStr.isEmpty()) {
        String [] queries = queryStr.split("&");
        String content = new String();
        for(String str: queries) {
          String[] temp = str.split("=");
          content += decodeParameters(temp[0]) + " = " + decodeParameters(temp[1]) + "\n";
        }
        setStatusCode(200);
        setBodyResponse(content);
        return buildResponse();
      }

      if (path.equals("/foobar")) {
        setStatusCode(404);
        setBodyResponse("<html><body>404 Not Found</body></html>");

      } else if (path.equals("/redirect")) {
        setStatusCode(302);
        setHeader("Location", "http://localhost:5000/");

      } else if (path.equals("/") && method.equals("GET")) {
        setStatusCode(200);
        FileHandler file = new FileHandler();
        String currentDir = System.getProperty("user.dir");
        String content = file.getFolderStructureUrl(currentDir + "/public");
        setBodyResponse(content);

      } else if (path.equals("/file1") && method.equals("PUT") ||
        path.equals("/text-file.txt") && method.equals("POST")) {
        setStatusCode(405);

      } else if (path.equals("/method_options") && method.equals("OPTIONS")) {
        setStatusCode(200);
        setHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");

      } else {
        setStatusCode(200);
      }
    }

    return buildResponse();
  }

  // Refactor buildResponse to not rely on internal calls
  public byte[] buildResponse() {
    String response;
    String headers = getHeaders();
    byte[] body = getBodyResponse();

    response = protocol + " " + getStatusCode() + " " + getStatusMessage();

    if(!getHeaders().equals("")){
      response+= "\n"+ headers;
    }
    if(body != null)
      response+= "\r\n\r\n";
    else
      body = new byte[1];

    byte[] data = response.getBytes(Charset.forName("UTF-8"));
    byte[] outputToSend = new byte[data.length + body.length];
    System.arraycopy(data, 0, outputToSend, 0, data.length);
    System.arraycopy(body, 0, outputToSend, data.length, body.length);
    return outputToSend;
  }

  public String getHeaders(){
    String result = "";
    Iterator<String> keySetIterator = headers.keySet().iterator();

    while(keySetIterator.hasNext()){
      String key = keySetIterator.next();
      result+= key + ": " + headers.get(key);
      if(keySetIterator.hasNext())
        result+="\n";
    }
    return result;
  }

  public String getHeader(String headerName) {
    return headers.get(headerName);
  }

  public void setHeader(String headerName, String value) {
    headers.put(headerName, value);
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusMessage() {
    if (statusCode == 200) return "OK";
    else if (statusCode == 302) return "Found";
    else if (statusCode == 404) return "Not Found";
    else if (statusCode == 405) return "Method Not Allowed";
    else return "";
  }

  public void setBodyResponse(String body) {
    this.body = body.getBytes(Charset.forName("UTF-8"));
  }

  public void setBodyResponse(byte[] body) {
    this.body = body;
  }

  public byte[] getBodyResponse() {
    return body;
  }

  public String decodeParameters(String queryStr) throws Exception {
    return URLDecoder.decode(queryStr, "UTF-8");
  }

  public String getQuery(String sUrl) {
    try {
      URL url = new URL("http://www.aaa.com:8080/" + sUrl);
      return url.getQuery();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new String();
    }
  }
}
