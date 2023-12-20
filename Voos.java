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
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Voos {
	
	public String numeroVoo;
	public String origem;
	public String horaPartida;
	public String destino;
	public String horaChegada;
	public String qtdParadas;
	public int distancia;
	public Duration duracao;
	
	public Voos() {
		// TODO Auto-generated constructor stub
	}
	
	public Voos(String numeroVoo, String origem, String horaPartida, String destino, String horaChegada,
			String qtdParadas, int distancia, Duration duracao) {
		
		this.numeroVoo = numeroVoo;
		this.origem = origem;
		this.horaPartida = horaPartida;
		this.destino = destino;
		this.horaChegada = horaChegada;
		this.qtdParadas = qtdParadas;
		this.distancia = distancia;
		this.duracao = duracao;
	}

	//função que seta a duração de cada voo específico.
	public void gerarDuracaoVoo(Aeroporto aero1, Aeroporto aero2) {
		
		DateTimeFormatter formatacaoHora = DateTimeFormatter.ofPattern("hmma");
		ZoneOffset fuso1 = ZoneOffset.of(aero1.fuso);
		ZoneOffset fuso2 = ZoneOffset.of(aero2.fuso);
		
		OffsetTime hora1 = OffsetTime.of(LocalTime.parse(this.horaPartida, formatacaoHora), fuso1);
		OffsetTime hora2 = OffsetTime.of(LocalTime.parse(this.horaChegada, formatacaoHora), fuso2);
		
		OffsetTime hora1Trans = hora1.withOffsetSameInstant(ZoneOffset.UTC);
		OffsetTime hora2Trans = hora2.withOffsetSameInstant(ZoneOffset.UTC);
		
		Duration duracao = Duration.between(hora1Trans, hora2Trans);
		
		if(duracao.isNegative()) {
			duracao = duracao.plusDays(1);
		}
		
		this.duracao = duracao;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "( " + this.numeroVoo + ", " + this.origem + ", HP:" +
				this.horaPartida + ", " + this.destino + ", HC:" + this.horaChegada +
				", " + this.qtdParadas + ", D:" + this.distancia + " KM" + ", Duração: " + this.duracao + " )";
	}
	
}