/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Samuel
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoRotas {
	
	Map<String, List<Rotas>> grafo;
	List<Rotas> listaRotas;
	
	public GrafoRotas() {
		grafo = new HashMap<String, List<Rotas>>();
		listaRotas = new ArrayList<>();
	}
	
	public void criaGrafo(List<Rotas> lista) {//função que cria o grafo de rotas a partir da lista de rotas.
		
		this.listaRotas = lista;
		
		for (int i = 0; i < listaRotas.size(); i++) {//laço que percorre a lista de rotas
													 //e preenche a estrutura grafo de forma com que
													 //o mesmo seja bidirecional.
			
			//Verificação da existência do aeroporto nos indices do Map.
			if (grafo.containsKey(listaRotas.get(i).origem)) {
				if (!(grafo.get(listaRotas.get(i).origem).contains(listaRotas.get(i)))) {
																								
					grafo.get(listaRotas.get(i).origem).add(listaRotas.get(i));
				}
			//Caso não exista, o mesmo é adicionado com sua respectiva rota.
			} else {
				List<Rotas> rotas = new ArrayList<Rotas>();
				grafo.put(listaRotas.get(i).origem, rotas);
				grafo.get(listaRotas.get(i).origem).add(listaRotas.get(i));
			}
			//Verificação da existência do aeroporto nos indices do Map.
			if (grafo.containsKey(listaRotas.get(i).destino)) {
				if (!(grafo.get(listaRotas.get(i).destino).contains(listaRotas.get(i)))) {
					grafo.get(listaRotas.get(i).destino).add(new Rotas(listaRotas.get(i).destino,
							listaRotas.get(i).origem, listaRotas.get(i).distancia));
				}
				//Caso não exista, o mesmo é adicionado com sua respectiva rota.
			} else {
				List<Rotas> rotas = new ArrayList<Rotas>();
				grafo.put(listaRotas.get(i).destino, rotas);
				grafo.get(listaRotas.get(i).destino).add(
						new Rotas(listaRotas.get(i).destino, listaRotas.get(i).origem, listaRotas.get(i).distancia));
			}
		}
	}
	
	public void imprimeGrafo() {
		System.out.println("\n Grafo Rotas (Aero Origem -> (Aero Origem, Aero Detino, Distância),...)\n");
		for (String key : grafo.keySet()) {
			System.out.println(" " + key + " -> " + grafo.get(key));
		}
	}
	
	
	
}
