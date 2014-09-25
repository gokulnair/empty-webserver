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
}
