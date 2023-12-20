/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Samuel
 */
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Relatorios {
	
	public void relatorio1(Map<String, List<Rotas>> grafRotas, String origem, String destino) {
		
		//algoritmoDijkstra
		
		//Lista que guarda o menor caminho.
		List<String> caminhoPercorrido = new ArrayList<>();
		//Lista que controla por quais vértices ainda deve ocorrer as verificações de menor rota.
		List<String> nosAtuais = new ArrayList<>();
		nosAtuais.add(origem);
		
		Map<String, String> nosAnteriores = new HashMap<String, String>();//Map que guarda os nós anteriores para formar o menor caminho.
		Map<String, Boolean> nosVisitados = new HashMap<String, Boolean>();//Map que mostra os aeroportos visitados.
		Map<String, Integer> distancias = new HashMap<String, Integer>();//Map que guarda as estimativas de distancias entre o aeroporto origem e os demais.
		
		for(String key : grafRotas.keySet()) {
			if(key.equals(origem)) {
				nosVisitados.put(key, true);//setando nó inicial como 1, pq ele já foi visitado.
				distancias.put(key, 0);//setando distancia do nó inicial pra ele mesmo como 0;
			}else {
				nosVisitados.put(key, false);//setando os nós como não visitados = 0;
				distancias.put(key, 9999999);//setando as distãncias iniciais como "infinito" para servirem como estimativa.
			}
			nosAnteriores.put(key, "");//Setando os aeroportos no hash map como sem destino ainda.
		}
		
		while(nosAtuais.size() != 0) {//enquanto não passar por todos aeroportos o while continua.
			String key = nosAtuais.get(0);//pega o aeroporto que está na primeira posição dos nós atuais.

			for(int i = 0; i < grafRotas.get(key).size(); i++) {//percorre por todas as rotas do aeroporto em questão

				//guardamos o vizinho e sua distância
				String vizinho = grafRotas.get(key).get(i).destino;
				int distancia = grafRotas.get(key).get(i).distancia;
				
				//o vizinho é adicionado nos visitados,
				//caso o mesmo ainda não tenha sido visitado.
				if(!(nosVisitados.get(vizinho))) {
					nosVisitados.put(vizinho, true);
					nosAtuais.add(vizinho);
				}

				//essa verificação ocorre para compararmos se a distância atual
				//entre o aeroporto em questão e seu vizinho é menor do que a
				//que já está setada no map.
				if(distancia + distancias.get(key) < distancias.get(vizinho)) {
					distancias.put(vizinho, distancia + distancias.get(key));
					nosAnteriores.put(vizinho, key);
					//então a distância é atualizada e o nó anterior
					//daquele vizinho é setado como o aeroporto atual.
				}
			}
			//por fim ocorre a remoção do aeroporto verificado,
			//essa lista funciona como uma fila.
			nosAtuais.remove(0);
		}
		
		//Com base nos nós anteriores é possível se formar o caminho inverso
		//do destino até a origem, para portanto formamos a rota entre os aeroportos.
		String aux = destino;
		caminhoPercorrido.add(destino);
		
		while(!(origem.equals(aux))){
			
			aux = nosAnteriores.get(aux);
			caminhoPercorrido.add(aux);
		}
		
		System.out.print("\n Menor rota para os aeroportos inseridos:\n [ ");
		for (int i = caminhoPercorrido.size()-1; i >= 0; i--) {
			System.out.print(caminhoPercorrido.get(i) + ", ");
		}
		System.out.println("]\n Distância total da rota: " + distancias.get(destino) + " KM");
		
	}
	
	public void relatorio2(Map<String, List<Voos>> grafVoos, String aero) {
		
		System.out.println(" Lista de Voos que partem de " + aero + " e não possuem escala:");
		
		//Laço que percorre o grafo de voos na posição do aeroporto escolhido
		//e retorna somente os voos que não possuem escala.
		for (int i = 0; i < grafVoos.get(aero).size(); i++) {
			if(grafVoos.get(aero).get(i).qtdParadas.equals("0")) {
				System.out.println(grafVoos.get(aero).get(i));
			}
		}
	}
	
	public void relatorio3_1(Map<String, List<Voos>> grafVoos, String origem, String destino) {

		//algoritmoDijkstraPelaDistancia
		
		//Lista que guarda o menor caminho.
		List<String> caminhoPercorrido = new ArrayList<>();
		//Lista que controla por quais vértices ainda deve ocorrer as verificações de menor rota.
		List<String> nosAtuais = new ArrayList<>();
		nosAtuais.add(origem);

		Map<String, String> nosAnteriores = new HashMap<String, String>();//Map que guarda os nós anteriores para formar o menor caminho.
		Map<String, Boolean> nosVisitados = new HashMap<String, Boolean>();//Map que mostra os aeroportos visitados.
		Map<String, Integer> distancias = new HashMap<String, Integer>();//Map que guarda as estimativas de distancias entre o aeroporto origem e os demais.


		for(String key : grafVoos.keySet()) {
			if(key.equals(origem)) {
				nosVisitados.put(key, true);//setando nó inicial como 1, pq ele já foi visitado.
				distancias.put(key, 0);//setando distancia do nó inicial pra ele mesmo como 0;
			}else {
				nosVisitados.put(key, false);//setando os nós como não visitados = 0;
				distancias.put(key, 9999999);//setando as distãncias iniciais como "infinito" para servirem como estimativa.
			}
			nosAnteriores.put(key, "");//Setando os aeroportos no hash map como sem destino ainda.
		}

		while(nosAtuais.size() != 0) {//enquanto não passar por todos aeroportos o while continua.
			String key = nosAtuais.get(0);//pega o aeroporto que está na primeira posição dos nós atuais.

			for(int i = 0; i < grafVoos.get(key).size(); i++) {//percorre por todas os voos do aeroporto em questão
				
				//guardamos o vizinho e sua distância
				String vizinho = grafVoos.get(key).get(i).destino;
				int distancia = grafVoos.get(key).get(i).distancia;
				
				//o vizinho é adicionado nos visitados,
				//caso o mesmo ainda não tenha sido visitado.
				if(!(nosVisitados.get(vizinho))) {
					nosVisitados.put(vizinho, true);
					nosAtuais.add(vizinho);
				}
				//essa verificação ocorre para compararmos se a distância atual
				//entre o aeroporto em questão e seu vizinho é menor do que a
				//que já está setada no map.
				if(distancia + distancias.get(key) < distancias.get(vizinho)) {
					distancias.put(vizinho, distancia + distancias.get(key));
					nosAnteriores.put(vizinho, key);
				}
			}
			//por fim ocorre a remoção do aeroporto verificado,
			//essa lista funciona como uma fila.
			nosAtuais.remove(0);
		}
		//Com base nos nós anteriores é possível se formar o caminho inverso
		//do destino até a origem, para portanto formamos a menor rota entre os aeroportos.
		String aux = destino;
		caminhoPercorrido.add(destino);

		while(!(origem.equals(aux))){

			aux = nosAnteriores.get(aux);
			caminhoPercorrido.add(aux);
		}

		System.out.print("\n Menor rota, com base na distancia, para os aeroportos inseridos:\n [ ");
		for (int i = caminhoPercorrido.size()-1; i >= 0; i--) {
			System.out.print(caminhoPercorrido.get(i) + ", ");
		}
		System.out.println("]\n Distância total da rota: " + distancias.get(destino) + " KM");

	}

	public void relatorio3_2(Map<String, List<Voos>> grafVoos, String origem, String destino) {

		//algoritmoDijkstraPelaDuração
		
		//Lista que guarda o menor caminho.
		List<String> caminhoPercorrido = new ArrayList<>();
		//Lista que controla por quais vértices ainda deve ocorrer as verificações de menor caminho.
		List<String> nosAtuais = new ArrayList<>();
		nosAtuais.add(origem);

		Map<String, String> nosAnteriores = new HashMap<String, String>();//Map que guarda os nós anteriores para formar o menor caminho.
		Map<String, Boolean> nosVisitados = new HashMap<String, Boolean>();//Map que mostra os aeroportos visitados.
		Map<String, Long> tempos = new HashMap<String, Long>();//Map que guarda as estimativas de tempo entre aeroportos.


		for(String key : grafVoos.keySet()) {
			if(key.equals(origem)) {
				nosVisitados.put(key, true);//setando nó inicial como 1, pq ele já foi visitado.
				tempos.put(key, (long)0);//setando distancia do nó inicial pra ele mesmo como 0;
			}else {
				nosVisitados.put(key, false);//setando os nós como não visitados = 0;
				tempos.put(key, (long)999999999);//setando os tempos iniciais como "infinito" para servirem como estimativa.
			}
			nosAnteriores.put(key, "");//Setando os aeroportos no hash map como sem destino ainda.
		}

		while(nosAtuais.size() != 0) {//enquanto não passar por todos aeroportos o while continua.
			String key = nosAtuais.get(0);

			for(int i = 0; i < grafVoos.get(key).size(); i++) {
				
				//guardamos o vizinho e seu tempo de voo.
				String vizinho = grafVoos.get(key).get(i).destino;
				//Tranformando a duração em long para fazer a comparação de menor duração.
				long tempo = grafVoos.get(key).get(i).duracao.getSeconds();

				//o vizinho é adicionado nos visitados,
				//caso o mesmo ainda não tenha sido visitado.
				if(!(nosVisitados.get(vizinho))) {
					nosVisitados.put(vizinho, true);
					nosAtuais.add(vizinho);
				}
				
				//essa verificação ocorre para compararmos se o tempo atual
				//entre o aeroporto em questão e seu vizinho é menor do que o
				//que já está setado no map.
				if(tempo + tempos.get(key) < tempos.get(vizinho)) {
					tempos.put(vizinho, tempo + tempos.get(key));
					nosAnteriores.put(vizinho, key);
				}
			}
			//por fim ocorre a remoção do aeroporto verificado,
			//essa lista funciona como uma fila.
			nosAtuais.remove(0);
		}
		
		//Com base nos nós anteriores é possível se formar o caminho inverso
		//do destino até a origem, para portanto formamos a menor rota entre os aeroportos.
		String aux = destino;
		caminhoPercorrido.add(destino);

		while(!(origem.equals(aux))){

			aux = nosAnteriores.get(aux);
			caminhoPercorrido.add(aux);
		}

		System.out.print("\n Menor rota, com base na duração, para os aeroportos inseridos:\n [ ");
		for (int i = caminhoPercorrido.size()-1; i >= 0; i--) {
			System.out.print(caminhoPercorrido.get(i) + ", ");
		}

		Duration duracaoRota = Duration.ofSeconds(tempos.get(destino));
		System.out.println("]\n Duração total da rota: " + duracaoRota);
	}
	
	public void relatorio4(Map<String, List<Rotas>> grafRotas) {
		
		//busca em profundidade
		
		System.out.print(" Aeroportos considerados ponte: ");
		
		//Lista que indica quais aeroportos são atingidos a partir de um específico.
		List<String> aeroAtingidos = new ArrayList<>();
		
		//Laço que percorre todos os aeroportos e define o índice como origem
		//da função do relatorio4_1 que verifica se o aeroporto origem é uma ponte.
		
		for (String key : grafRotas.keySet()) {
			
			aeroAtingidos.add(key);//Adiciona o origem nos aeroportos atingidos.
			
			relatorio4_1(grafRotas, key, aeroAtingidos);
			
			//Se a lista de percorridos for menor que a quantidade de aeroportos
			//indica que o mesmo é importante para atingir os demais.
			if(aeroAtingidos.size() < grafRotas.size()) {
				System.out.print(key + " ");
			}
			
			//limpando a lista de aeroportos atingidos
			//após a verificação do aeroporto em questão.
			aeroAtingidos.clear();
		}
		
		System.out.println();
		
	}
	
	public void relatorio4_1(Map<String, List<Rotas>> grafRotas, String origem, List<String> aeroAtingidos) {
		
		if(aeroAtingidos.size() == 1) {//Verifica se há somente um aeroporto nos atingidos
			aeroAtingidos.add(grafRotas.get(origem).get(0).destino);//Realizo a primeira inserção e chamo a recursão.
			relatorio4_1(grafRotas, grafRotas.get(origem).get(0).destino, aeroAtingidos);
		}else {
			for (int i = 0; i < grafRotas.get(origem).size(); i++) {//Percorre as rotas do aeroporto origem.
				if(!(aeroAtingidos.contains(grafRotas.get(origem).get(i).destino))){
					//Se o destino daquela rota ainda não tenha sido atingido, a mesma é adicionada na lista.
					aeroAtingidos.add(grafRotas.get(origem).get(i).destino);
					relatorio4_1(grafRotas, grafRotas.get(origem).get(i).destino, aeroAtingidos);
				}
			}
		}
		
	}
	
	public void relatorio5(Map<String, List<Rotas>> listAdjacencia, String origem) {
		
		//algoritmoPrim - Gera um grafo mínimo que interliga todos os aeroportos.

		List<String> listT = new ArrayList<>();//Lista que auxilia na descisão do caminho da AGM.
		//Contabiliza os vértices já passados.
		List<Rotas> listFringe = new ArrayList<>();//Lista que pega e guarda as posíveis rotas a 
		//partir do vértice localizado.
		List<Rotas> listAGM = new ArrayList<>();//Lista que define as ligações da AGM.

		
		listT.add(origem);
		removeDaListaAdj(listAdjacencia, origem);
		

		while (listT.size() < listAdjacencia.size()) {//Fica em loop enquanto todos os aeroportos não forem adicionados na listT.

			String ultimaPos = listT.get(listT.size()-1);//pega o último aeroporto da lista para definir o menor caminho
			//a partir dele.
			int menorPeso = 99999999;
			String verticeDestino = "";

			//laço que contabiliza as possíveis rotas a partir de um aeroporto..
			for (int i = 0; i < listAdjacencia.get(ultimaPos).size(); i++) {
				listFringe.add(listAdjacencia.get(ultimaPos).get(i));
				
				//Usado para controle.
				//System.out.println("Franja:");
				//System.out.println(listFringe);
			}
			
			int posMenor = 0;
			//laço que determina o menor caminho comparando os aeroportos contabilizados
			//na franja e seus respectivos pesos.
			for (int i = 0; i < listFringe.size(); i++) {
				if (listFringe.get(i).distancia < menorPeso) {
					menorPeso = listFringe.get(i).distancia;
					verticeDestino = listFringe.get(i).destino;
					posMenor = i;
				}
			}

			//adiciona o aeroporto determinado como melhor destino na listT
			//caso o mesmo já não esteja lá.
			if (!(listT.contains(verticeDestino))) {
				listT.add(verticeDestino);
			}
			//adiciono no grafo agm a rota com menor peso.
			if(!(listAGM.contains(listFringe.get(posMenor))))
				listAGM.add(listFringe.get(posMenor));
			
			//laço que remove da franja o vértice escolhido como destino e seu respectivo peso.
			for (int i = listFringe.size()-1; i >= 0; i--) {
				if(listFringe.get(i).destino.equals(verticeDestino)) {
					listFringe.remove(i);
				}
			}			
			removeDaListaAdj(listAdjacencia, verticeDestino);

		}

		System.out.println("\n Ligações grafo AGM\n");
		for (int i = 0; i < listAGM.size(); i++) {
			System.out.println(" "+listAGM.get(i));
		}
		
		//crioum grafo de rotas com base na lista de rotas do grafo AGM.
		System.out.println("\n Grafo de rotas com base na AGM");
		GrafoRotas grafRotasAGM = new GrafoRotas();
		grafRotasAGM.criaGrafo(listAGM);
		grafRotasAGM.imprimeGrafo();
		
		System.out.println("\n Caminho gerado a partir da busca em profundidade no grafo de rotas:\n");
		criaRota(grafRotasAGM, origem, null);
		
		System.out.println("\n Essa 'rota' não é considerada um circuito Hamiltoniano, pois\n "
				+ "não é possível passar por todos aeroportos sem ocorrer NENHUMA repetição. \n");

	}
	
	public static void removeDaListaAdj(Map<String, List<Rotas>> grafRotas, String vertice) {
        //Após a adição de um vértice na listaT ocorre a
        //remoção do vértice dos destinos dos vértices da lista de adjacencia.
        //para que o mesmo não seja mais incluido na possível menor rota.
        
        for (String key : grafRotas.keySet()) {
			for (int i = 0; i < grafRotas.get(key).size(); i++) {
				if(grafRotas.get(key).get(i).destino.equals(vertice)) {
					grafRotas.get(key).remove(i);
				}
			}
        }
	}
	
	public static void criaRota(GrafoRotas grafRotasAGM, String aeroOrigem, String aeroAnterior) {
		
		//Função que realiza a busca em profundidade por todo o grafo
		//de rotas da AGM.
		//Essa busca percorre todos os aeroportos adjacentes ao origem e adiciona a rota utilizada
		//ao caminho que está sendo gerado.
		//Tudo isso através de recursão, onde a cada iteração o aeroporto origem e o anterior muda.
		
		for (int i = 0; i < grafRotasAGM.grafo.get(aeroOrigem).size(); i++) {
			if(!(grafRotasAGM.grafo.get(aeroOrigem).get(i).destino.equals(aeroAnterior))) {
				System.out.println(" " + aeroOrigem + " <-> " + grafRotasAGM.grafo.get(aeroOrigem).get(i).destino);
				criaRota(grafRotasAGM, grafRotasAGM.grafo.get(aeroOrigem).get(i).destino, aeroOrigem);
			}
		}
		
		if(aeroAnterior != null) {
			System.out.println(" " + aeroOrigem + " <-> " + aeroAnterior);
		}
		
	}

}