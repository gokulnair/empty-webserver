package com.fire;

public interface Socket {
  void start();
  String readSocketData();
  void writeSocketData(String input);
  void writeSocketData(byte[] input);
}
