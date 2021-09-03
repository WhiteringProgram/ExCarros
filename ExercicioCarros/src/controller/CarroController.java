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
	
	public void run () { //Roda o m�todo
		deslocaCarro();
		try { //Tentativa e erro
			veiculoAguardo();
			semaforo.acquire();
		} catch (Exception e) { //Tratamento de exce��o
			e.printStackTrace(); 
		} finally {
			semaforo.release(); //Libera o semaforo
		}
		cruzaCarro();
	}
//Problema dividido em 2 partes essenciais: Ordenar os carros (Definir a posi��o e mover um de cada vez) e iniciar o deslocamento
	private void deslocaCarro () {
		int deslocamento = 0; int tempo = 500;
		while (deslocamento < 100) { //Valor aleatorio para o deslocamento
			deslocamento += (int)(Math.random()*5 + 6); // deslocamento = deslocamento + Math.random()*5 + 6. Serve para atribuir um valor aleat�rio at� 100 na vari�vel deslocamento
			try {
				Thread.sleep(tempo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("O carro #" + idVeiculo + " j� percorreu " +deslocamento + " metros");
			}
	}
	
	private void veiculoAguardo () {
		System.out.println("O ve�culo #" + idVeiculo + " est� parado no cruzamento");
		tempoInicial = System.nanoTime();		
	}
	
	private void cruzaCarro() {
		tempoFinal = System.nanoTime();
		tempoTotal = (tempoFinal - tempoInicial)/Math.pow(10, 9);
		System.out.println("O autom�vel # " + idVeiculo + " aguardou " + tempoTotal + " " +  "segundos no cruzamento e cruzou no sentido" + " " +rota);
	}
		
}
	

