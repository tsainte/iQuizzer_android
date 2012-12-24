package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import br.com.mobwiz.iquizzer.model.entities.Pergunta;
import br.com.mobwiz.iquizzer.model.entities.Quiz;
import br.com.mobwiz.iquizzer.model.entities.Resposta;
import br.com.mobwiz.iquizzer.util.Functions;

public class GameActivity extends Activity implements OnItemClickListener {
	Quiz quiz;
	Pergunta currentPergunta;
	ArrayList<Resposta> respostas;
	GameEngine engine;
	
	int theRound = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        Intent intent = getIntent();
        quiz = (Quiz) intent.getSerializableExtra("quiz");
        
        engine = new GameEngine(quiz, this);
        engine.start();
        roundUp();
    }

     void roundUp(){
        currentPergunta = engine.popPergunta();
        if (currentPergunta == null){
            gameover();
            return;
        }
        
        TextView round = (TextView)findViewById(R.id.lblRound);
        round.setText("Pergunta "+ ++theRound + "/" + 5); //5 is hardcoded!
        
        EditText txtPergunta = (EditText) findViewById(R.id.txtPergunta);
        txtPergunta.setText(currentPergunta.getConteudo());
        
        respostas = currentPergunta.getRespostas();

        //TODO atualizar listview
        //[tvRespostas reloadData];
        carregarLista();
        
    }
    void roundDown(Resposta r){

        engine.pushResultado(r);
        //incrementa pontuacao
        //chama roundUp
        roundUp();
    }
    void gameover(){
        theRound = 0;
        engine.saveResults();
        engine.close(); //should be inside saveresults?
 		Intent i = new Intent(getApplicationContext(), GameOver.class);
 		i.putExtra("jogo",engine.jogo);
 		startActivity(i);
        //engine = null;
        
    }
    private void carregarLista() { 
  		
    	//ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quizzesTitle);
    	ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, respostas);
  		ListView listView = (ListView)findViewById(R.id.tvRespostas);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

		Resposta resposta = respostas.get(position);
		Functions.toast(this, "correta: "+resposta.isCorreta());
		roundDown(resposta);
	}

}
