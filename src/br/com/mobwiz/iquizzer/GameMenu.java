package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.mobwiz.iquizzer.model.dao.QuizDAO;
import br.com.mobwiz.iquizzer.model.entities.Quiz;
import br.com.mobwiz.iquizzer.util.Functions;



public class GameMenu extends Activity implements OnItemClickListener {
	ArrayList<Quiz> quizzes;
	QuizDAO quizDAO;
	
	public void init(){
		try{
			quizDAO = new QuizDAO(this);
			quizzes = quizDAO.findAll();
			int size = 0;
			if (quizzes != null) size = quizzes.size();

		} catch (Exception e){
			e.printStackTrace();
		}
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        init();
        carregarLista();

    }
    private void carregarLista() { 
  		
    	//ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quizzesTitle);
    	ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, quizzes);
  		ListView listView = (ListView)findViewById(R.id.lista_quizzes);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Quiz quiz = quizzes.get(position);
 		Intent i = new Intent(getApplicationContext(), GameActivity.class);
 		i.putExtra("quiz",quiz);
 		startActivity(i);
	}
}
