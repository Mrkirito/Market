package marketUtil;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public class RandomCode {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		String year = isLowTen(c.get(Calendar.YEAR));
		String month = isLowTen(c.get(Calendar.MONTH)+1);
		String day = isLowTen(c.get(Calendar.DAY_OF_MONTH));
		
		String date = year + month + day ;
		String str ="";
		for(int i = 0; i < 4; i++){
			str+=randomChar();
		}
		System.out.println(str + "-" + date);
	}
	public static String isLowTen(int time){
		String date;
		if(time<10){
			date = "0" + time;
		}else{
			date = time + "";
		}
		return date;
	}
	
	public static char randomChar(){
		Random r = new Random();
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
}
