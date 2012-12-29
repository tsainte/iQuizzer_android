package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import br.com.mobwiz.iquizzer.model.dao.JogoDAO;
import br.com.mobwiz.iquizzer.model.entities.Jogo;
import br.com.mobwiz.iquizzer.model.entities.Resultado;
import br.com.mobwiz.iquizzer.util.Functions;

public class GameOver extends Activity {
	Jogo jogo;
	JogoDAO jogoDAO;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        
        Intent intent = getIntent();
        jogo = (Jogo) intent.getSerializableExtra("jogo");
        jogoDAO = new JogoDAO(this);
        initParameters();
        
    }
	private void initParameters() {
		int acertos = countAcertos();
		TextView txtacertos = (TextView ) findViewById(R.id.lblAcertos);
		
		txtacertos.setText("Acertos: " + acertos);
		
		TextView txterros = (TextView) findViewById(R.id.lblErros);
		txterros.setText("Erros: " + (5 - acertos));
		//saveResults();
		
	}
	private int countAcertos() {
		ArrayList<Resultado> rps = jogo.getResultados();
		int acertos = 0;
		for (Resultado rp : rps){
			if (rp.getResposta().isCorreta()){
				acertos++;
			}
		}
		return acertos;
	}
	public void menu(View v){
 		Intent i = new Intent(getApplicationContext(), MenuActivity.class);
 		//precisa matar as activites abertas!!
 		startActivity(i);
	}/* moved to gameengine
	public void saveResults(){
		jogo.setDia(Functions.currentDate());
		jogo.setHora(Functions.currentTime());
		
		jogo.setPontos(0);
		jogoDAO.saveOnCloud(jogo);
	}*/
}
