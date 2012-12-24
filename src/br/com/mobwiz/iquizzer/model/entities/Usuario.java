package br.com.mobwiz.iquizzer.model.entities;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String apelido;
	private int id;
	private String nome;
	private int pontos_criador;
	private int pontos_jogador;
	private String sobrenome;
	private String senha;
	
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontos_criador() {
		return pontos_criador;
	}
	public void setPontos_criador(int pontos_criador) {
		this.pontos_criador = pontos_criador;
	}
	public int getPontos_jogador() {
		return pontos_jogador;
	}
	public void setPontos_jogador(int pontos_jogador) {
		this.pontos_jogador = pontos_jogador;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
