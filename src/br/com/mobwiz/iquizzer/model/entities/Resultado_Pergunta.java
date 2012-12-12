package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;

public class Resultado_Pergunta implements Serializable{

	boolean correta; 
	int id;
	Pergunta pergunta;
	public Resultado_Pergunta(Pergunta pergunta, boolean resultado) {
		this.pergunta = pergunta;
		correta = resultado;
	}
	public boolean isCorreta() {
		return correta;
	}
	public void setCorreta(boolean correta) {
		this.correta = correta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	
}
