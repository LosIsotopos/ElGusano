package paqueteGusano;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gusano {
	private final int CANTMAXNODOS = 15;
	private int[][] mat = new int[CANTMAXNODOS][CANTMAXNODOS];
	private ArrayList<Nodo> infectadas = new ArrayList<Nodo>();
	private int[][] resultado;
	public Gusano(String path) throws IOException {
		int origen;
		int costo;
		int destino;
		int cantAristas;
		Scanner sc = new Scanner(new File(path));
		cantAristas = sc.nextInt();
		while (cantAristas != 0) {
			origen = sc.nextInt();
			costo = sc.nextInt();
			destino = sc.nextInt();
			mat[origen-1][destino-1] = costo;
			mat[destino-1][origen-1] = costo;
			cantAristas--;
		}
		cantAristas = sc.nextInt();
		while (cantAristas != 0) {
			infectadas.add(new Nodo(sc.nextInt(),sc.nextInt()));
			cantAristas--;
		}
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if(i != j && mat[i][j] == 0) {
					mat[i][j] = 999;
				}
			}
		}
	}
	
	public void mostrarIn() {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		
		for (Nodo nodo : infectadas) {
			System.out.println(nodo.getComputadora() + " " + nodo.getHora());
		}
	}
	
	
	public void resolverFloyd() {
		int[][] fCero = mat.clone();
		for (int i = 0; i < fCero.length; i++) {
			fCero[i][i] = 0;
		}
		resultado = fCero.clone();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (i != j ) {
					for (int k = 0; k < mat.length; k++) {
						if (i != k || j != k) {
							if(fCero[i][j] > (fCero[i][k] + fCero[k][j])) {
								resultado[i][j] = (fCero[i][k] + fCero[k][j]);
							}
						}
					}	
				}
			}
			fCero = resultado.clone();
		}
	}
	
	public void mostrarMatResultado() {
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado.length; j++) {
				System.out.print(resultado[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public ArrayList<Integer> comprobar() {
		ArrayList<ArrayList<Integer>> lista = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> listRes = new ArrayList<Integer>();
		for (int i = 0; i < infectadas.size(); i++) {
			lista.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < infectadas.size(); i++) {
			for (int j = 0; j < mat.length; j++) {
				if (resultado[infectadas.get(i).getComputadora()-1][j] == infectadas.get(i).getHora()) {
					lista.get(i).add(j+1);
				}
			}
		}
		int z = 0;
		int contador = 1;
		while ( z < lista.get(0).size()) {
			for (int i = 1; i < lista.size(); i++) {
				if(lista.get(i).contains(lista.get(0).get(z))) {
					contador++;
				} 
			}
			if(contador == infectadas.size()) {
				listRes.add(lista.get(0).get(z));
			}
			contador = 1;
			z++;
		}
		return listRes;
	}
	
	
}
