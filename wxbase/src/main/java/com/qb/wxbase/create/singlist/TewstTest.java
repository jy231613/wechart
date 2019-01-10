package com.qb.wxbase.create.singlist;


import com.qb.wxbase.exception.IndexOverFlowException;
import com.qb.wxbase.exception.NullParamException;

/**
 * 测试类
 */
public class TewstTest {
	public static void main(String[] args) {
		try {
			SingArrayList<String> sings = new SingArrayList<>();
			sings.add("123");
			sings.add("456");
			sings.add("333");
			sings.add("333");
//			String s = "";
//			for (int i = 0; i < sings.size(); i++) {
//				s+=sings.get(i)+",";
//			}
//			System.out.println("["+s+"]");
			sings.set(1,"000");
//			sings.remove("333");
//			sings.remove(3);
			System.out.println(sings.toString());
//			sings.remove(10);
//			System.out.println(sings.get(0));
		}catch (IndexOverFlowException e) {
			e.printStackTrace();
		}catch (NullParamException e) {
			e.printStackTrace();
		}
	}
}
