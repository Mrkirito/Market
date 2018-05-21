package marketUtil;


import java.security.MessageDigest;



import sun.misc.BASE64Encoder;


public class Md5Encoder {

	public String EncoderByMd5(String str){
	    //确定计算方法
		String newstr="";
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			//加密后的字符串
			newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
			return newstr;
		} catch (Exception e) {			
			e.printStackTrace();
			return newstr;
		}
					
	}
	
	/*public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }*/
}
