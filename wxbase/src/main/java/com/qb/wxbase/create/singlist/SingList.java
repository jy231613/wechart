package com.qb.wxbase.create.singlist;

import com.qb.wxbase.exception.IndexOverFlowException;
import com.qb.wxbase.exception.NullParamException;

/**
 * 集合接口
 * @param <E> e
 */
public interface SingList<E> {
	/**
	 * 添加一个E对象
	 * @param e
	 */
	void add(E e)throws NullParamException;

	/**
	 * 移除一个对象
	 * @param index 下标
	 */
	void remove(int index)throws IndexOverFlowException;
	
	/**
	 * 移除一个对象
	 * @param e 对象
	 */
	void remove(E e);

	/**
	 * 设置一个对象
	 * @param index 下标
	 * @param e 设置的对象
	 * @return 返回旧对象
	 */
	E set(int index, E e)throws IndexOverFlowException,NullParamException;

	/**
	 * 获取下标对象
	 * @param index 下标
	 * @return E
	 * @throws IndexOverFlowException
	 */
	E get(int index)throws IndexOverFlowException;
}
