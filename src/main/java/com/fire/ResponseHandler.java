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
    String method = request.getMethod();
    String result = "HTTP/1.1 404 Not Found";

    if (method.equals("GET")) {
      if(!request.getPath().equals("/foobar"))
        result = "HTTP/1.1 200 OK";
      if(request.getPath().equals("/redirect"))
        result = "HTTP/1.1 302 Found\nLocation: http://locahost:5000";
    } else if (method.equals("POST")) {
      result = "HTTP/1.1 200 OK";
    } else if(method.equals("PUT")){
      result = "HTTP/1.1 200 OK";
    }

    return result;
  }

  public String getHeaders(String headerName) {
    String result = "";
    if(headerName.equals("Location"))
      result = "localhost:5000";
    return result;
  }
}
