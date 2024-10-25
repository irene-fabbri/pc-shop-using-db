package com.example.demo;

public class PcSelezionato {
	String nome;
	String marca;
	String descrizione;
	double prezzo;
	String url;
	int qnt;
	
	public PcSelezionato() {
	}
	
	public PcSelezionato(Pc pc, int qnt) {
		this.nome = pc.nome;
		this.marca = pc.marca;
		this.descrizione = pc.descrizione;
		this.prezzo = pc.prezzo;
		this.url = pc.url;
		this.qnt = qnt;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

}
