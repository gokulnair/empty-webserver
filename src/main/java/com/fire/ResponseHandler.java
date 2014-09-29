package com.fire;

import java.util.HashMap;

/**
 * Created by jonathan.alviar on 9/25/14.
 */
public class ResponseHandler {
  
  private HashMap<String, String> headers = new HashMap<String, String>();

  public String getResponse(String method, String path, String body) {
    String result = "HTTP/1.1 404 Not Found";

    if (method.equals("GET")) {
      if(!path.equals("/foobar"))
        result = "HTTP/1.1 200 OK";
      if(path.equals("/redirect")) {
        setHeader("Location", "http://localhost:5000/");
        result = buildResponse();
      }
    } else if (method.equals("POST")) {
      result = "HTTP/1.1 200 OK";
    } else if(method.equals("PUT")){
      result = "HTTP/1.1 200 OK";
    }
    return result;
  }

  public String buildResponse(){
    String response = "HTTP/1.1 302 Found\nLocation: " + getHeaders("Location");
    return response;
  }

  public String getHeaders(String headerName) {
    return headers.get(headerName);
  }

  public void setHeader(String headerName, String value) {
    headers.put(headerName, value);
  }
}
