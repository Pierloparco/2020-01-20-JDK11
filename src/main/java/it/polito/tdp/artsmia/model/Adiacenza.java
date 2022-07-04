package it.polito.tdp.artsmia.model;

public class Adiacenza implements Comparable<Adiacenza> {
	
	private int art1;
	private int art2;
	private Integer peso;
	
	
	public Adiacenza(int art1, int art2, int peso) {
		super();
		this.art1 = art1;
		this.art2 = art2;
		this.peso = peso;
	}
	
	public int getArt1() {
		return art1;
	}
	public void setArt1(int art1) {
		this.art1 = art1;
	}
	public int getArt2() {
		return art2;
	}
	public void setArt2(int art2) {
		this.art2 = art2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	@Override
	public int compareTo(Adiacenza o) {
		return -this.peso.compareTo(o.getPeso());
	}

}
