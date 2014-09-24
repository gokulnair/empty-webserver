package com.fire;

/**
 * Created by charles.zhang on 9/24/14.
 */
public class Request {
  private String method;
  private String url;

  public Request(String method, String url) {
    this.method = method;
    this.url =url;
  }

  public String method() {
    return method;
  }

  public String url() {
    return url;
  }
}
