package com.athongkun.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	Timer timer;  
    public TimerTest(int time){  
        timer = new Timer();  
        timer.schedule(new TimerTaskTest01(), time * 1000);  
    }  
      
    public static void main(String[] args) {  
        System.out.println("timer begin....");  
        new TimerTest(3);  
    }  
    
    public class TimerTaskTest01 extends TimerTask{  
    	
    	public void run() {  
    		System.out.println("Time's up!!!!");  
    	}  
    }  
}
