/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Samuel
 */
public class Aeroporto implements Comparable<Aeroporto>{
	
	public String nome;
	public String fuso;
	public int cordX;
	public int cordY;
	
	public Aeroporto() {
		// TODO Auto-generated constructor stub
		
		
	}
	
		
	public Aeroporto(String nome, String fuso, int cordX, int cordY) {
		
		this.nome = nome;
		this.fuso = fuso;
		this.cordX = cordX;
		this.cordY = cordY;
	}



	@Override
	public int compareTo(Aeroporto o) {
		
		if(nome.equals(o.nome) && fuso.equals(o.fuso) && cordX == o.cordX && cordY == o.cordY)
			return 0;
		
		return -1;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nome: " + this.nome + " Fuso: " + this.fuso + " Cord X: " + this.cordX + " Cord Y: "+ this.cordY;
	}
	
}