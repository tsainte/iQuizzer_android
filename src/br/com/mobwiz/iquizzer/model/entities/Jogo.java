package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Jogo implements Serializable{

	String dia;
	String hora;
	int pontos;
	int id;
	ArrayList<Resultado> resultados;
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
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}
	public void setResultados(ArrayList<Resultado> resultado) {
		this.resultados = resultado;
	}
	public void addResultado(Resultado rp) {
		if (resultados == null){
			resultados = new ArrayList <Resultado>();
		}
		resultados.add(rp);
		
	}

}
