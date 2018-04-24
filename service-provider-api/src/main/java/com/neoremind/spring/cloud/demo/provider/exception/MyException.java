package com.neoremind.spring.cloud.demo.provider.exception;

public class MyException extends RuntimeException {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 5196421433506179782L;

  /**
   * Creates a new instance of MyException.
   */
  public MyException() {
    super();
  }

  /**
   * Creates a new instance of MyException.
   */
  public MyException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  /**
   * Creates a new instance of MyException.
   */
  public MyException(String arg0) {
    super(arg0);
  }

  /**
   * Creates a new instance of MyException.
   */
  public MyException(Throwable arg0) {
    super(arg0);
  }

}
