package interview;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelArrays {
	public static void main(String[] args) {
		long[] arrayOfLong = new long[20000];
		
		//parallelSetAll()方法来对一个有20000个元素的数组进行随机赋值
		Arrays.parallelSetAll(arrayOfLong,
				index -> ThreadLocalRandom.current().nextInt( 100000 ));
		
		//打印前十个元素的值
		Arrays.stream(arrayOfLong).limit(10).forEach(
				i -> System.out.print( i + " "));
		System.out.println();
		
		Arrays.parallelSort( arrayOfLong );
		Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
				i -> System.out.print( i + " "));
		System.out.println();
	}
}
