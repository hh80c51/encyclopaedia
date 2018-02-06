package com.athongkun.utils;
import java.util.UUID;  

public class CreateUUid {
	  
    public static void main(String[] args) {  
        String  uuid36 = UUID.randomUUID().toString();  
        String  uuid32 = uuid36.replaceAll("-", "");  
        System.out.println("【uuid36】" + uuid36 + "\n【uuid32】" + uuid32 );  
    }  
}
