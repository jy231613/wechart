package com.qb.wxbase.create.singlist;

import com.qb.wxbase.exception.IndexOverFlowException;
import com.qb.wxbase.exception.NullParamException;

import java.util.Arrays;


/**
 * Sing 链表集合框架
 * @author Administrator
 * 特性:
 * 	允许数据重复
 * 	不允许空数据
 * 	有序集合
 * @param <E>
 */
public class SingArrayList<E> implements SingList<E> {
	
	/**
	 * 默认集合长度
	 */
	private final int DEFAULT_SIZE = 10;
	
	/**
	 * 默认空数组
	 */
	private final Object[] DEFAULT_NULL = {};
	
	/**
	 * 数据存储空间(存储数组)
	 */
	private Object[] objs;
	
	/**
	 * 当前有效的集合长度
	 */
	private int size = 0;
	
	/**
	 * 先给几个私有的集合方法,比如
	 * 	查看下标是否越界,
	 * 	集合长度自动扩容等
	 * 	添加一个子集和父集合的begin-over
	 */

	/**
	 * 查看下标是否越界
	 * @param index 下标
	 * @return true:越界
	 */
	private boolean indexIsOverflow(int index) {
		return index>=this.size;
	}
	
	/**
	 * 集合自动扩容
	 * @param size 新增加的数据长度
	 */
	private void arrayCapacityUp(int size) {
		if (this.objs.length-this.size<size) {
			Object[] oldObjs = this.objs;
			int addSize = ((this.size+size)/10+(DEFAULT_SIZE/10))*10;
			this.objs = new Object[addSize];
			System.arraycopy(oldObjs, 0, this.objs, 0, oldObjs.length);
		}
	}
	
	/**
	 * 构造方法:
	 * 	1.空构造
	 * 	2.自定义长度集合
	 * 	3.数组数据构造
	 */
	
	
	/**
	 * 空构造初始化一个默认长度的
	 */
	public SingArrayList() {
		this.objs = new Object[DEFAULT_SIZE];
	}
	
	/**
	 * 自定义长度集合
	 * 	=0:返回空集合
	 * 	<0:返回默认长度集合
	 * 	>0:自定义长度集合
	 * @param size 集合长度
	 */
	public SingArrayList(int size) {
		if (size==0) {
			this.objs = DEFAULT_NULL;
		}else if (size<0) {
			this.objs = new Object[DEFAULT_SIZE];
		}else {
			this.objs = new Object[size];
		}
	}
	
	/**
	 * 传递带默认集合数组的集合链表
	 * 	=null:返回默认长度集合
	 * 	=0:返回空集合
	 * 	else:赋值给当前数组
	 * @param es 默认的数组
	 */
	public SingArrayList(E[] es) {
		if (es==null) {
			this.objs = new Object[DEFAULT_SIZE];
		}else if (es.length==0) {
			this.objs = DEFAULT_NULL;
		}else {
			this.objs = Arrays.copyOf(es, es.length);
			this.size = this.objs.length;
		}
	}
	
	
	
	/**
	 * 做增删改查操作的时候
	 * 	1.注意数组的维护
	 * 	2.数组实际长度的维护(size)
	 */
	
	@Override
	public void add(E e) throws NullParamException {
		if (e==null) {
			throw new NullParamException();
		}else {
			arrayCapacityUp(1);
			this.objs[this.size] = e;
			this.size++;
		}
	}

	@Override
	public void remove(int index) throws IndexOverFlowException {
		if (indexIsOverflow(index)) {
			throw new IndexOverFlowException(index,this.size);
		}else {
			this.objs[index] = null;
			this.remove(null);
		}
	}

	@Override
	public void remove(E e) {
		Object[] newObjs = new Object[size];
        int index = 0;
        int nullSize = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.objs[i]!=null&&!this.objs[i].equals(e)){
                newObjs[index] = this.objs[i];
                index++;
            }else {
            	nullSize++;
            }
        }
        this.objs = Arrays.copyOf(newObjs,newObjs.length);
        this.size = this.objs.length-nullSize;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E e) throws IndexOverFlowException, NullParamException {
		if (indexIsOverflow(index)) {
			throw new IndexOverFlowException(index,this.size);
		}else {
			if (e==null) {
				throw new NullParamException();
			}else {
				E obj = (E) this.objs[index];
				this.objs[index] = e;
				return obj;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOverFlowException {
		if (indexIsOverflow(index)) {
			throw new IndexOverFlowException(index,this.size);
		}else {
			return (E) this.objs[index];
		}
	}
	
	@Override
	public String toString() {
		String strw = "[";
		for (int i = 0; i < this.size; i++) {
			strw+=this.objs[i].toString()+",";
		}
		return strw.substring(0,strw.length()-1)+"]";
	}
	
	/**
	 * 获取当前集合有效长度
	 * @return 有效长度
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * 获取当前空间长度
	 * @return 空间长度
	 */
	public int spaceSize() {
		return this.objs.length;
	}
	
	/**
	 * 获取到当前的object[]
	 * @return this.objs
	 */
	protected Object[] objArray() {
		return this.objs;
	}
}
