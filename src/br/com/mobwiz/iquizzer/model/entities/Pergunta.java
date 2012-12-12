package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Pergunta implements Serializable{
	String conteudo;
	int id;
	Quiz quiz;
	ArrayList<Resposta> respostas;
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public ArrayList<Resposta> getRespostas() {
		return respostas;
	}
	public void setRespostas(ArrayList<Resposta> respostas) {
		this.respostas = respostas;
	}

	public Pergunta(int id, String conteudo){
		this.id = id;
		this.conteudo = conteudo;
	}
}
