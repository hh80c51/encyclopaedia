package interview;

import java.util.Scanner;

public class ExceptionTest {
	public static void main(String[] args) {
//		System.out.println(ret());
		while(true) {
			Scanner input = new Scanner(System.in);
			int n = input.nextInt();
			int x = 100;	//名单人数
			int y = 10;		//每桌人数
			int m = 0;
			m = n%10==0?n/10:n/10+1;
			System.out.println("第"+n+"个人坐在"+m+"桌");
		}
		
	}
	
	private static Integer ret() {
		int ret = 0;
		try{
		throw new Exception();
		}
		catch(Exception e){
		ret = 1;
		return ret;
		}
		finally{
		ret = 2;
		}
	}
}
