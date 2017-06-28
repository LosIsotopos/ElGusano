package paqueteGusano;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		Gusano gus = new Gusano("gusano1.in");
		ArrayList<Integer> resultado;
		//		gus.mostrarIn();
		gus.resolverFloyd();
//		gus.mostrarMatResultado();
		resultado = gus.comprobar();
		for (Integer integer : resultado) {
			System.out.println(integer);
		}
	}

}
