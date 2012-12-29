package br.com.mobwiz.iquizzer.model.game;
import br.com.mobwiz.iquizzer.model.entities.Resposta;

class Score {
	private int time;
	private int value = 0;
	
	private int corretaValue = 10;
	public void incrementByAwnser(Resposta r){
		if (r.isCorreta()){
			value = value + corretaValue;
		}
	}
	public int getValue() {
		return value;
	}
}
