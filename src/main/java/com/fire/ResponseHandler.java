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

  public String getContent() {
    String input = request.getRequest();

    if(input.equals("GET / HTTP/1.1")) {
      return new String("HTTP/1.1 200 OK");
    } else {
      return new String("HTTP/1.1 404 Not Found");
    }
  }

}
