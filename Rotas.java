/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

public class Rotas implements Comparable<Rotas>{
	
	public String origem;
	public String destino;
	public int distancia;
	
	
	public Rotas() {
		// TODO Auto-generated constructor stub
	}
	
	public Rotas(String origem, String destino, int distancia) {
		
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
		
	}

	
	@Override
	public int compareTo(Rotas o) {
		
		if(o.origem.equals(origem) && o.destino.equals(destino) && o.distancia == distancia) {
			return 0; 
		}
		
		
		return -1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( " +this.origem + ", " + this.destino + ", " + this.distancia + " KM )";
	}
}