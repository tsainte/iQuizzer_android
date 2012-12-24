package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;

public class Resultado implements Serializable{

	private Resposta resposta;

	public Resultado(Resposta resposta) {
		this.resposta = resposta;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	
}
