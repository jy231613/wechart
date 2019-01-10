package com.qb.wxbase.create.singlist;

import com.qb.wxbase.exception.IndexOverFlowException;
import com.qb.wxbase.exception.NullParamException;

import java.util.ArrayList;

/**
 * SingList包装类
 * @author 贾恒飞
 * @param <E> 泛型
 */
public class SingUltimately<E> extends SingArrayList<E> {
	
	@SuppressWarnings("unchecked")
	public ArrayList<E> toArray(){
		ArrayList<E> arrays = new ArrayList<>(size());
		for (int i = 0; i < size(); i++) {
			arrays.add((E) objArray()[i]);
		}
		return arrays;
	}

	@Override
	public void add(E e) {
		try {
			super.add(e);
		} catch (NullParamException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void remove(int index) {
		try {
			super.remove(index);
		} catch (IndexOverFlowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public E set(int index, E e) {
		try {
			return super.set(index, e);
		} catch (IndexOverFlowException e1) {
			e1.printStackTrace();
		} catch (NullParamException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public E get(int index) {
		try {
			return super.get(index);
		} catch (IndexOverFlowException e) {
			e.printStackTrace();
		}
		return null;
	}
}
