package br.com.mobwiz.iquizzer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import br.com.mobwiz.iquizzer.model.entities.Jogo;
import br.com.mobwiz.iquizzer.model.entities.Resultado_Pergunta;

public class GameOver extends Activity {
	Jogo jogo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        
        Intent intent = getIntent();
        jogo = (Jogo) intent.getSerializableExtra("jogo");
        initParameters();
        
    }
	private void initParameters() {
		int acertos = countAcertos();
		TextView txtacertos = (TextView ) findViewById(R.id.lblAcertos);
		
		txtacertos.setText("Acertos: " + acertos);
		
		TextView txterros = (TextView) findViewById(R.id.lblErros);
		txterros.setText("Erros: " + (5 - acertos));
		
	}
	private int countAcertos() {
		ArrayList<Resultado_Pergunta> rps = jogo.getResultado_perguntas();
		int acertos = 0;
		for (Resultado_Pergunta rp : rps){
			if (rp.isCorreta()){
				acertos++;
			}
		}
		return acertos;
	}
	public void menu(View v){
 		Intent i = new Intent(getApplicationContext(), GameMenu.class);
 		//precisa matar as activites abertas!!
 		startActivity(i);
	}
}
