package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.com.mobwiz.iquizzer.model.dao.QuizDAO;
import br.com.mobwiz.iquizzer.model.entities.Quiz;
import br.com.mobwiz.iquizzer.util.Functions;

public class BaixarQuizzes extends ListActivity{
	QuizDAO quizDAO;
	ArrayList<Quiz> quizzes;
	
	public void init(){
		try{
			quizDAO = new QuizDAO(this);
			quizzes = quizDAO.findAllFromServer();

		} catch (Exception e){
			e.printStackTrace();
		}
	}
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        carregarLista();
        //setContentView(R.layout.baixar_quizzes);
        

    }
    private void carregarLista() {

    
  		//ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quizzesTitle);
  		BaixarQuizAdapter arrayAdapter = new BaixarQuizAdapter(this, android.R.layout.simple_list_item_1, quizzes);
    	super.setListAdapter(arrayAdapter);

		
	}
	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		
		super.onListItemClick(listView, view, position, id);

		//Recebe objeto clicado pelo usuario
		String item = (String)super.getListAdapter().getItem(position);
		
	}
	public void comprar(View v){

		try {
			quizDAO.downloadQuiz((Integer)v.getTag());
			Functions.toast(this, "Instalado com sucesso!");
			
			Button button = (Button)v;
			button.setText("Instalado");
			button.setEnabled(false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
