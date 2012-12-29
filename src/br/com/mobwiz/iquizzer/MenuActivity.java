package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;

@SuppressLint("NewApi")
public class MenuActivity extends Activity implements DialogInterface.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    public void openGameMenu(View v){
 		Intent i = new Intent(getApplicationContext(), GameMenu.class);
 		startActivity(i);
    }
    public void openPopupMenu(View v){
    	ArrayList<String> options = new ArrayList<String>();
    	options.add("criar");
    	options.add("meus quizzes");
    	options.add("baixar");
    	
    	final CharSequence[] items = {"Criar Quiz", "Meus Quizzes", "Baixar Quiz"};
    	
    	//criar listview, dialog.builder.....
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(items, this);
		AlertDialog alert = builder.create();
		alert.show();
    }



	@Override

	public void onClick(DialogInterface dialog, int item) {
		Intent i; 
		switch(item){
	         	case 0:
	         		i = new Intent(getApplicationContext(), CriarQuiz.class);
	         		startActivity(i);
	         		break;
	         	case 1: 
	         		i = new Intent(getApplicationContext(), MeusQuizzes.class);
	         		startActivity(i);
	         		break;
	         	case 2:
	         		i = new Intent(getApplicationContext(), BaixarQuizzes.class);
	         		startActivity(i);
	         		break;
	         } 
		
	    }
}
