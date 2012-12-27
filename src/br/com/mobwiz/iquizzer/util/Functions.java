package br.com.mobwiz.iquizzer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.Toast;

public class Functions {
	public static void toast(Context ctx, String msg){
		Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
		toast.show();
	}
	public static boolean intToBool(int intValue) {
		if (intValue != 0){
			return true;
		}
		return false;
	}
	public static int boolToInt(boolean booleanValue){
		if (booleanValue){
			return 1;
		}
		return 0;
	}
	public static String currentDate() {
		Calendar c = Calendar.getInstance(); 
		String date = ""+c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);

		return date;
	}
	public static String currentTime() {
		Calendar c = Calendar.getInstance(); 
		String time = ""+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);

		return time;
	}
}
