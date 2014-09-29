package com.fire;

/**
 * Created by charles.zhang on 9/24/14.
 */
public class RequestHandler {
  private String request;
  private String path;
  private String method;

  public RequestHandler(String request) {
    this.request = request;
    if(request.contains(" ") && request.length() > 0) {
      String[] requestArray = request.split(" ");
      this.method = requestArray[0];
      this.path = requestArray[1];
    } else {
      this.method = new String();
      this.path = new String();
    }
  }

  public String getMethod() {
    return method;
  }

  public String getPath() {
    return path;
  }

  public String getRequest() {
    return request;
  }
}
