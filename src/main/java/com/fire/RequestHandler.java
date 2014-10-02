package com.fire;

/**
 * Created by charles.zhang on 9/24/14.
 */
public class RequestHandler {
  private String path = "";
  private String method = "";

  public RequestHandler(String request) {
    parse(request);
  }

  public void parse(String request) {
    if(request != null && request.length() > 0) {
      String[] requestArray = request.split(" ");
      setMethod(requestArray[0]);
      setPath(requestArray[1]);
    }
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) { this.path = path; }

}
