package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Jogo implements Serializable{

	String dia;
	String hora;
	int pontos;
	int id;
	ArrayList<Resultado_Pergunta> resultado_perguntas;
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Resultado_Pergunta> getResultado_perguntas() {
		return resultado_perguntas;
	}
	public void setResultado_perguntsa(
			ArrayList<Resultado_Pergunta> resultado_perguntas) {
		this.resultado_perguntas = resultado_perguntas;
	}
	public void addResultado_Pergunta(Resultado_Pergunta rp) {
		if (resultado_perguntas == null){
			resultado_perguntas = new ArrayList <Resultado_Pergunta>();
		}
		resultado_perguntas.add(rp);
		
	}

}
