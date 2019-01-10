package com.qb.wxbase.exception;

/**
 * 数组下标溢出异常
 * 
 * @author Administrator
 */
public class IndexOverFlowException extends Exception {
	public IndexOverFlowException() {
		super("数组下标溢出");
	}

	public IndexOverFlowException(int index, int size) {
		super("数组有效长度为:%s,期望下标为:%i"
				.replace("%s", String.valueOf(size))
				.replace("%i", String.valueOf(index)));
	}
}
