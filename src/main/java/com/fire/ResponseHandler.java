package com.fire;

import java.io.IOException;
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
  private HashMap<String, String> headers = new HashMap<String, String>();

  public String getResponse(String method, String path, String body) throws IOException {

    if (path.equals("/foobar"))
      setStatusCode(404);

    else if (path.equals("/redirect")) {
      setStatusCode(302);
      setHeader("Location", "http://localhost:5000/");
    }

    else if (path.equals("/file1") && method.equals("GET")) {
      setStatusCode(200);
      FileHandler file = new FileHandler();
      List<String> content = file.read("/Users/jonathan.alviar/tmol/8thLightTrain/cob_spec/public/file1");
      setBodyResponse(content.get(0));
    }

    else if (path.equals("/file1") && method.equals("PUT") ||
      path.equals("/text-file.txt") && method.equals("POST")) {
      setStatusCode(405);
    }

    else if (path.equals("/method_options") && method.equals("OPTIONS")) {
      setStatusCode(200);
      setHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
    }

    else
      setStatusCode(200);

    return buildResponse();
  }

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
}
