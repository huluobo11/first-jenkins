package org.util.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OrderUtil {
	
	/**
	* @Description: 字典序排序
	* @param list
	* @return
	 */
	public static List distionary(List list){
		try {
			Collections.sort(list, new SpellComparator());
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));  
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	/**
	* @Description: 字典序排序
	* @param list
	 */
	public static String distionaryString(List list){
		StringBuffer buffer=new StringBuffer();
		try {
			Collections.sort(list, new SpellComparator());
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		List list=new ArrayList();
		list.add("1393517055");
		list.add("wolf55");
		list.add("1392716103");
		list.add("Green");
		OrderUtil.distionary(list);
	}
}
/** 
 * 汉字拼音排序比较器 
 */  
class SpellComparator implements Comparator {  
 public int compare(Object o1, Object o2) {  
  try {  
   // 取得比较对象的汉字编码，并将其转换成字符串  
   String s1 = new String(o1.toString().getBytes());  
   String s2 = new String(o2.toString().getBytes());  
   // 运用String类的 compareTo（）方法对两对象进行比较  
   return s1.compareTo(s2);  
  } catch (Exception e) {  
   e.printStackTrace();  
  }  
  return 0;  
 } 
}

