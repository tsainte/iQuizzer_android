package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;

public class Resposta implements Serializable{

	String conteudo;
	boolean correta; //verificar se o JSON consegue trazer bool
	int id;
	Pergunta pergunta;
	
	public Resposta(int id, String conteudo, boolean correta) {
		this.id = id;
		this.conteudo = conteudo;
		this.correta = correta;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
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
	public String toString(){
		return conteudo;
	}
}
