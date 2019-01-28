package com.example.demo;

public class ExceptionTest {
		public static void main(String[] args) {
			/*boolean flag = checkScore(-10);
			System.out.println(flag);*/
			
			
			
			try {
				checkScore(110);
			} catch (Exception e) {
				//System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			
			//checkScore(110);
		}
		
	 
		public static void checkScore(int score) throws Exception {
			if(score < 0 || score > 100) {
			 
				throw new MyException("考试成绩不符合要求");
			} 
			
			System.out.println("考试成绩符合要求");
		}
		
		
	}
