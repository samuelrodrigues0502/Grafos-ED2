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

public class GrafoVoos {

	Map<String, List<Voos>> grafo;
	List<Voos> listaVoos;
	
	public GrafoVoos() {
		grafo = new HashMap<String, List<Voos>>();
		listaVoos = new ArrayList<>();
	}
	
	public void criaGrafo(List<Voos> lista) {//função que cria o grafo de voos a partir da lista de voos.
		
		this.listaVoos = lista;
		
		
		//laço que percorre a lista de voos
		//e preenche a estrutura grafo
		for (int i = 0; i < listaVoos.size(); i++) {
			
			//Verificação da existência do aeroporto nos indices do Map.
			if (grafo.containsKey(listaVoos.get(i).origem)) {
				grafo.get(listaVoos.get(i).origem).add(listaVoos.get(i));
			//Caso não exista, o mesmo é adicionado com sua respectiva rota.
			} else {
				List<Voos> voos = new ArrayList<Voos>();
				grafo.put(listaVoos.get(i).origem, voos);
				grafo.get(listaVoos.get(i).origem).add(listaVoos.get(i));
			}

		}
		
	}
	
	public void imprimeGrafo() {
		System.out.println("\n Grafo Voos (Aero Origem -> (Número do voo, Aero Origem"
				+ ", Hora Partida, Aero Destino, Hora Chegada, Quantidade de Paradas, Distância, Duração),...\n");
		for (String key : grafo.keySet()) {
			System.out.println(" " + key + " -> " + grafo.get(key));
		}
	}
	
}

