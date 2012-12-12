package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;
import java.util.ArrayList;



public class Quiz implements Serializable{
	private String titulo;
	private int id;
	private ArrayList<Pergunta> perguntas;
	public Quiz(){
		
	}
	public Quiz(int id, String titulo){
		this.id = id;
		this.titulo = titulo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Pergunta> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(ArrayList<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	public String toString(){
		return titulo;
	}

}
