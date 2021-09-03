package view;

import java.util.concurrent.Semaphore;
import controller.CarroController;

public class Principal {
	public static void main (String [] args) {
		Semaphore semaforo = new Semaphore(1);
		String [] sentido = {"Norte-Sul", "Sul-Norte","Leste-Oeste", "Oeste-Leste"};
		for (int i = 0; i < 4 ; i++) {
			CarroController veiculo = new CarroController(semaforo, sentido [i]);
			veiculo.start();
		}
	}
}

