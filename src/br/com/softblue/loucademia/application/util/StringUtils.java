package br.com.softblue.loucademia.application.util;

import java.time.LocalDate;

public class StringUtils {

	public static boolean isEmpty(String s) {
		
		if(s == null) {
			return true;
		}
		
		return s.trim().length() == 0;
	}
	
	public static String leftZeros(int value, int finalize) {
		
		return String.format("%0"+finalize+"d",value);
		
		
	}
	
	public static void main(String[] args) {
		
//		String str = null;
//		
//		//boolean b = isEmpty(str);
//	
//		int  v = 100;
//		
//		System.out.println(leftZeros(v, 6));
		
	}
}
