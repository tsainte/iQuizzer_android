package br.com.mobwiz.iquizzer.model.game;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import br.com.mobwiz.iquizzer.model.dao.JogoDAO;
import br.com.mobwiz.iquizzer.model.dao.PerguntaDAO;
import br.com.mobwiz.iquizzer.model.entities.Jogo;
import br.com.mobwiz.iquizzer.model.entities.Pergunta;
import br.com.mobwiz.iquizzer.model.entities.Quiz;
import br.com.mobwiz.iquizzer.model.entities.Resposta;
import br.com.mobwiz.iquizzer.model.entities.Resultado;
import br.com.mobwiz.iquizzer.util.Functions;

public class GameEngine implements Serializable {
	int currentRound;

	int maxRounds;
	int maxTimePerRound;
	Score score;

	Quiz quiz;
	ArrayList<Pergunta> perguntas;
	
	Context context;
	//ArrayList<Resultado_Pergunta> resultado_perguntas;
	PerguntaDAO perguntaDAO;
	JogoDAO jogoDAO;
	private Jogo jogo;
	public GameEngine(Quiz quiz, Context context) {
		this.quiz = quiz;
		this.context = context;
		
	}
	void initParameters(){
	    currentRound = 0;
	    maxRounds = 5;
	    maxTimePerRound = 10; //nao usado ainda
	    score = new Score();
	}
	
	public void start(){
	    //jogoDAO = [[JogoDAO alloc] init];
	    //jogo = [jogoDAO createJogo];
		initParameters();
	    perguntaDAO = new PerguntaDAO(context);
	    
	    perguntas = perguntaDAO.getRandomPerguntas(quiz,maxRounds);
	    
	    jogo = new Jogo();
	    jogoDAO = new JogoDAO(context);
	 //   resultado_perguntas = [[NSMutableArray alloc] init];
	    
	}
	public Pergunta popPergunta(){
		Pergunta pergunta;
		try{
			pergunta = perguntas.get(0);
			perguntas.remove(0);
		} catch (Exception e){
			pergunta = null;
		}
		return pergunta;
	}
	
	public void pushResultado(Resposta resposta){
		Resultado rp = new Resultado(resposta);
		score.incrementByAwnser(resposta);
		jogo.addResultado(rp);
		
	}
	/*
	-(void)pushResultado_pergunta:(Pergunta*)pergunta resultado:(BOOL)resultado{
	    Resultado_Pergunta* rp = [jogoDAO createResultado_Pergunta];
	    rp.pergunta = pergunta;
	    rp.acertou = [NSNumber numberWithBool:resultado];
	    
	    [jogo addResultado_perguntaObject:rp];
	}*/
	
	public void saveResultsOLD(){
	    //TODO refatorar para utilizar UTC
	//    jogo.dia = [Functions currentDate];
	//    jogo.hora = [Functions currentTime];
	    
	//    jogo.pontos = [NSNumber numberWithInt:0]; //TODO criar objeto de pontuacao
		
	//    NSLog(@"game of jsons: %@", [[NSString alloc] initWithData:[jogoDAO createBody:jogo] encoding:NSUTF8StringEncoding]);
	    /*
	    NSError* error;
	    if (![jogoDAO.managedContext save:&error]){
	        NSLog(@"%@", [error description]);
	    } else { //se tudo ok, envia pra nuvem
	        NSLog(@"envia pra nuvem...");
	    }*/
	}
	public void saveResults(){
		jogo.setDia(Functions.currentDate());
		jogo.setHora(Functions.currentTime());
		
		jogo.setPontos(score.getValue());
		jogoDAO.saveOnCloud(jogo);
	}
	public void close() {
		perguntaDAO.close();
		
	}
	public Jogo getJogo() {
		return jogo;
	}

}
