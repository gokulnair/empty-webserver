package com.fire;

/**
 * Created by jonathan.alviar on 9/25/14.
 */
public class ResponseHandler {
  
  private RequestHandler request;
  
  public ResponseHandler(RequestHandler request) {
    this.request = request; 
  }

  public RequestHandler getRequest() {
    return request;
  }

  public String getData() {
    String input = request.getRequest();
    String method = request.getMethod();
    String result = "HTTP/1.1 404 Not Found";

    if (method.equals("GET")) {
      if(!request.getPath().equals("/badurl"))
        result = "HTTP/1.1 200 OK";
    } else if (method.equals("POST")) {
      result = "HTTP/1.1 200 OK";
    }

    return result;
  }

}
