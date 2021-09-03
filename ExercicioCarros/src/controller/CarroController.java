package controller;
import java.util.concurrent.Semaphore;

public class CarroController extends Thread {
	
	private Semaphore semaforo;
	private double tempoFinal, tempoInicial, tempoTotal;
	private String rota;
	private int idVeiculo;
	
	//Construtor
	public CarroController(Semaphore semaforo, String rota) {
		this.semaforo = semaforo;
		this.rota = rota;
		this.idVeiculo = (int)this.getId();
	}
	
	public void run () { //Roda o método
		deslocaCarro();
		try { //Tentativa e erro
			veiculoAguardo();
			semaforo.acquire();
		} catch (Exception e) { //Tratamento de exceção
			e.printStackTrace(); 
		} finally {
			semaforo.release(); //Libera o semaforo
		}
		cruzaCarro();
	}
//Problema dividido em 2 partes essenciais: Ordenar os carros (Definir a posição e mover um de cada vez) e iniciar o deslocamento
	private void deslocaCarro () {
		int deslocamento = 0; int tempo = 500;
		while (deslocamento < 100) { //Valor aleatorio para o deslocamento
			deslocamento += (int)(Math.random()*5 + 6); // deslocamento = deslocamento + Math.random()*5 + 6. Serve para atribuir um valor aleatório até 100 na variável deslocamento
			try {
				Thread.sleep(tempo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("O carro #" + idVeiculo + " já percorreu " +deslocamento + " metros");
			}
	}
	
	private void veiculoAguardo () {
		System.out.println("O veículo #" + idVeiculo + " está parado no cruzamento");
		tempoInicial = System.nanoTime();		
	}
	
	private void cruzaCarro() {
		tempoFinal = System.nanoTime();
		tempoTotal = (tempoFinal - tempoInicial)/Math.pow(10, 9);
		System.out.println("O automóvel # " + idVeiculo + " aguardou " + tempoTotal + " " +  "segundos no cruzamento e cruzou no sentido" + " " +rota);
	}
		
}
	

