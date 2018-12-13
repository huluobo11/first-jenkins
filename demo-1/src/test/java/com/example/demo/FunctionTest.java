package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.junit.Test;

public class FunctionTest {
/*	
 --------------------java8常用函数式接口规律发现-----------------------------------------------	
前缀
	int /double  参数类型或返回值 类型为int /double，如果表示参数类型，则只有一个参数。
	Bi  有两个输入参数
	Binary 有两个相同类型的输入参数
	ToDouble / ToLong  返回 double/long 类型
	
 后缀
	 Consumer (消费者) 有参且 无返回值
	 Function (功能) 有参且有返回值
	 Operator (运算) 输入参数和返回值类型相同
	 Predicate （断定）有参数，且返回值为boolean类型
	 Supplier  （供应商）无参且有返回值
	 
例子 
	BiConsumer 有两个输入参数且无返回值的操作
	BinaryOperator 两个相同类型参数且和 返回值 类型 和参数类型相同的 操作
	DoubleToLongFunction 接受一个double类型输入，返回一个long类型结果
	
	
*/
	@Test
	public void testFunction() throws Exception {
		int x =1, y =3;
		Integer result = operate((a, b)-> a + b, x, y);
		System.out.println(result);
	}

	public Integer operate(BinaryOperator<Integer> binaryOperator, Integer x, Integer y) {
		return binaryOperator.apply(x, y);
	}

	@Test
	public void tt() {
		int i =1;
		i=++i+i++;
		System.out.println(i);
		List<Object> arrayList = new ArrayList<>();
		List<Object> collect = arrayList.stream().limit(2).collect(Collectors.toList());
		/*System.out.println(0xaa | 0x55);
		System.out.println(15 & 240);
		System.out.println(10 ^ 12);
		System.out.println(-2 >> 1);
		System.out.println(-2 >>> 1);*/
	}
	
}

