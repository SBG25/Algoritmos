package bab_mochila;

import java.util.Comparator;

public class Objeto {
	private int valor;
	private int peso;
	private double ratio;
	private int identificador;
	
	public Objeto(int id, int v, int p) {
		identificador = id;
		valor = v;
		peso = p;
		ratio = (double)v/p;
	}

	public int getValor() {
		return valor;
	}
	
	public int getIdentificador() {
		return identificador;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	public static class ComparadorObjeto implements Comparator<Objeto>{

		@Override
		public int compare(Objeto o1, Objeto o2) {
			if(o1.getRatio() > o2.getRatio()) return -1;
			if(o1.getRatio() < o2.getRatio()) return 1;
			else return 0;
		}
	}
}
