package com.qb.wxbase.exception;

/**
 * 空参数异常
 * @author Administrator
 */
public class NullParamException extends Exception{
	public NullParamException() {
		super("空参数异常");
	}

	public NullParamException(String s) {
		super(s);
	}
}
