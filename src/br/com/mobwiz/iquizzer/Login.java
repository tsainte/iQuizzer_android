package br.com.mobwiz.iquizzer;

import br.com.mobwiz.iquizzer.util.Authenticator;
import br.com.mobwiz.iquizzer.util.Functions;
import br.com.mobwiz.iquizzer.util.WebService;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Login extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
        
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean isAuth = preferences.getBoolean("isAuth", false);
		if (isAuth){
			Intent it = new Intent(this,MenuActivity.class);
			
			startActivity(it);
			finish();
		}
	}
	public void logar (View v){
		EditText username = (EditText)findViewById(R.id.username);
		EditText password = (EditText)findViewById(R.id.password);
		
		Authenticator auth = new Authenticator();
		auth.context = this;
		String status = auth.login(username.getText().toString(), password.getText().toString());
		if (status.equalsIgnoreCase("ok")){
			Intent it = new Intent(this,MenuActivity.class);
			
			startActivity(it);
			finish();
		} else{
			Functions.toast(this, status);
		}
	}
}
