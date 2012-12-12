package br.com.mobwiz.iquizzer.util;

import android.content.Context;
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
}
