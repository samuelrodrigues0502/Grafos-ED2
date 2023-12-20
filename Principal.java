package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);

		List<Aeroporto> listaAero = new ArrayList<>();
		List<Rotas> listaRotas = new ArrayList<>();
		List<Voos> listaVoos = new ArrayList<>();

		String caminho = ".\\src\\Voos.txt";

		lerArquivo(caminho, listaAero, listaRotas, listaVoos);
		
		//Percorre a lista de voos e chama uma função que determina
		//a duração do voo com base em seu fuso horário e hora de chegada
		//e partida de cada voo em específico.
		for (int i = 0; i < listaVoos.size(); i++) {
			Aeroporto aero1 = new Aeroporto();
			for (int j = 0; j < listaAero.size(); j++) {
				if(listaVoos.get(i).origem.equals(listaAero.get(j).nome)) {
					aero1 = listaAero.get(j);
				}
			}
			Aeroporto aero2 = new Aeroporto();
			for (int j = 0; j < listaAero.size(); j++) {
				if(listaVoos.get(i).destino.equals(listaAero.get(j).nome)) {
					aero2 = listaAero.get(j);
				}
			}
			listaVoos.get(i).gerarDuracaoVoo(aero1, aero2);
		}
		
		/*System.out.printf("\nAeroportos:\n");
		imprimeLista(listaAero);
		System.out.printf("\nRotas:\n");
		imprimeLista(listaRotas);
		/*System.out.printf("\nVoos:\n");
		imprimeLista(listaVoos);*/
		
		GrafoRotas grafRotas = new GrafoRotas();
		grafRotas.criaGrafo(listaRotas);
		grafRotas.imprimeGrafo();
		
		GrafoVoos grafVoos = new GrafoVoos();
		grafVoos.criaGrafo(listaVoos);
		grafVoos.imprimeGrafo();
		
		Relatorios relat = new Relatorios();
		
		int op = 0;
		
		//RELATÓRIOS//
		while(op != 6) {
			
			System.out.println("\n ====================================== RELATÓRIOS ======================================\n");

			System.out.println(" 1 - Relatório que mostra um caminho com base nos aeroportos inseridos(origem, destino).");
			System.out.println(" 2 - Relatório que mostra os voos que não possuem escala de um aeroporto especifico.");
			System.out.println(" 3 - Relatório que mostra a viagem com menor custo com base na distância e duração entre dois aeroportos."); 
			System.out.println(" 4 - Relatório que mostra a partir do grafo de rotas os aeroportos que são considerados pontes.");
			System.out.println(" 5 - Relatório que mostra uma rota que conecta todos os aeroportos, a partir de um aeroporto.");
			System.out.println(" 6 - Encerrar o programa.\n");

			System.out.print(" Escolha o relatório desejado: ");
			op = entrada.nextInt();

			if(op == 1) {
				
				System.out.print("\n Defina o aeroporto origem: ");
				String aeroOrigem = entrada.next();
				System.out.print(" Defina o aeroporto destino: ");
				String aeroDestino = entrada.next();
				aeroOrigem = aeroOrigem.toUpperCase();
				aeroDestino = aeroDestino.toUpperCase();
				
				if((grafRotas.grafo.containsKey(aeroOrigem)) && (grafRotas.grafo.containsKey(aeroDestino)))
					relat.relatorio1(grafRotas.grafo, aeroOrigem, aeroDestino);
				else
					System.out.println("\n Algum aeroporto inserido não existe em nosso sistema!!!\n");
			}
			if(op == 2) {
				System.out.print("\n Defina o aeroporto: ");
				String aero = entrada.next();
				aero = aero.toUpperCase();
				
				if((grafVoos.grafo.containsKey(aero)))
					relat.relatorio2(grafVoos.grafo, aero);
				else
					System.out.println("\n O aeroporto inserido não existe em nosso sistema!!!\n");
			}
			if(op == 3) {
				
				System.out.print("\n Defina o aeroporto origem: ");
				String aeroOrigem = entrada.next();
				System.out.print(" Defina o aeroporto destino: ");
				String aeroDestino = entrada.next();
				aeroOrigem = aeroOrigem.toUpperCase();
				aeroDestino = aeroDestino.toUpperCase();
				
				if((grafVoos.grafo.containsKey(aeroOrigem)) && (grafVoos.grafo.containsKey(aeroDestino))) {
					//imprime a menor rota com base na distância entre os aeroportos.
					relat.relatorio3_1(grafVoos.grafo, aeroOrigem, aeroDestino);
					//imprime a menor rota com base na duração entre os aeroportos.
					relat.relatorio3_2(grafVoos.grafo, aeroOrigem, aeroDestino);
				}else
					System.out.println("\n Algum aeroporto inserido não existe em nosso sistema!!!\n");
			}
			if(op == 4) {
				
				relat.relatorio4(grafRotas.grafo);
				
			}
			if(op == 5) {
				System.out.print("\n Defina o aeroporto: ");
				String aero = entrada.next();
				aero = aero.toUpperCase();
				
				GrafoRotas grafAGM = new GrafoRotas();
				grafAGM.criaGrafo(listaRotas);//Duplicando o garfo de rotas para a criação de uma AGM.
				
				if((grafAGM.grafo.containsKey(aero)))
					relat.relatorio5(grafAGM.grafo, aero);
				else
					System.out.println("\n O aeroporto inserido não existe em nosso sistema!!!\n");
			}
			if(op <= 0 && op > 6) {
				System.out.println("Opção indisponível, digite novamente;");
			}	
		}
	}

	public static void imprimeLista(List lista) {
		for (Object object : lista) {
			System.out.println(object);
		}
	}
	
	public static void lerArquivo(String caminho, List<Aeroporto> listaAero, 
								  List<Rotas> listaRotas,	List<Voos> listaVoos) {
		try {
			FileReader arq = new FileReader(".\\src\\Voos.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";

			while (!(linha = lerArq.readLine()).equals("!")) {
				String[] split = linha.split(",");

				Aeroporto aero = new Aeroporto();
				
				aero.nome = split[0].toString();
				aero.fuso = split[1].toString();
				aero.cordX = Integer.parseInt(split[2].toString());
				aero.cordY = Integer.parseInt(split[3].toString());
				listaAero.add(aero);
				
			}

			while (!(linha = lerArq.readLine()).equals("!")) {
				String[] split = linha.split(",");

				Rotas rota = new Rotas();
				rota.origem = split[0].toString();
				rota.destino = split[1].toString();

				int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
				for (int j = 0; j < listaAero.size(); j++) {//GERANDO DISTÂNCIA COM BASE NOS Xs E Ys
					if (rota.origem.equals(listaAero.get(j).nome)) {
						x1 = listaAero.get(j).cordX;
						y1 = listaAero.get(j).cordY;
						break;
					}
				}
				for (int j = 0; j < listaAero.size(); j++) {
					if (rota.destino.equals(listaAero.get(j).nome)) {
						x2 = listaAero.get(j).cordX;
						y2 = listaAero.get(j).cordY;
						break;
					}
				}
				//Usando pitágoras para gerar a distância entre os aeroportos.
				double aux = Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2);
				double distancia = Math.sqrt(aux);
				rota.distancia = (int) distancia * 10;

				listaRotas.add(rota);
			}

			while (!(linha = lerArq.readLine()).equals("!")) {
				String[] split = linha.split(",");

				Voos voo = new Voos();
				voo.numeroVoo = split[0].toString();
				voo.origem = split[1].toString();
				voo.horaPartida = split[2].toString() + "M"; 
				voo.destino = split[3].toString();
				voo.horaChegada = split[4].toString() + "M";
				voo.qtdParadas = split[5].toString();

				int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
				for (int j = 0; j < listaAero.size(); j++) {//GERANDO DISTÂNCIA COM BASE NOS Xs E Ys
					if (voo.origem.equals(listaAero.get(j).nome)) {
						x1 = listaAero.get(j).cordX;
						y1 = listaAero.get(j).cordY;
						break;
					}
				}

				for (int j = 0; j < listaAero.size(); j++) {
					if (voo.destino.equals(listaAero.get(j).nome)) {
						x2 = listaAero.get(j).cordX;
						y2 = listaAero.get(j).cordY;
						break;
					}
				}
				//Usando pitágoras para gerar a distância entre os aeroportos.
				double aux = Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2);
				double distancia = Math.sqrt(aux);
				voo.distancia = (int) distancia * 10;

				listaVoos.add(voo);
			}

			arq.close();
			lerArq.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}