package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.junit.Test;

public class FunctionTest {

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

