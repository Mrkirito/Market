package marketUtil;

import java.util.Random;

public class StringRandom {
	//生成随机数字和字母,  
    public String getStringRandom(int length) {   
        String val = "";  
        Random random = new Random();  
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
        	//random.nextInt(4)返回一个0,1,2,3之间的一个随机数
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
            	//int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
            	int temp=65;
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }
        return val.substring(0, 4)+'-'+val.substring(4, 8)+'-'+val.substring(8, 12);
    }	
}
