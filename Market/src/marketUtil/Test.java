package marketUtil;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		StringRandom test = new StringRandom();  
		String str=test.getStringRandom(12);
		System.out.println(str);		
		Random random = new Random();
		int d=random.nextInt(2);
		System.out.println(d);
		str="zhouhaihua";
		Md5Encoder m=new Md5Encoder();
		str=m.EncoderByMd5(str);
		System.out.println("md5:"+str);
	}
}
