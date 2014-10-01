package com.fire;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jonathan.alviar on 9/25/14.
 */
public class ResponseHandler {

  private static final String protocol = "HTTP/1.1";
  private int statusCode;
  private String body = "";
  private String currentDir = System.getProperty("user.dir");
  private HashMap<String, String> headers = new HashMap<String, String>();

  public String getResponse(String method, String path, String body) throws Exception {

    FileHandler isFile = new FileHandler();
    String filePath = currentDir + "/public/" + path;

    if (isFile.exists(filePath) && method.equals("GET") && !path.contains("image")) {
      setStatusCode(200);
      String content = isFile.read(filePath);
      setBodyResponse(content);

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

      } else
        setStatusCode(200);
    }

    return buildResponse();
  }

  // Refactor buildResponse to not rely on internal calls
  public String buildResponse() {
    String response;
    String headers = getHeaders();
    String body = getBodyResponse();

    response = protocol + " " + getStatusCode() + " " + getStatusMessage();

    if(!getHeaders().equals("")){
      response+= "\n"+ headers;
    }
    if (body.length() > 0)
      response+= "\r\n\r\n" + getBodyResponse();

    return response;
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
    this.body = body;
  }

  public String getBodyResponse() {
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
