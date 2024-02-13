package it.edu.iisgubbio.lettoreCircolari;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static List<Circolari> listaCircolari = new ArrayList<>();
	
	public static void crea(String nomeCartella) {
		File folder = new File("/Users/classe4I/archivio");
        File[] listOfFiles = folder.listFiles();
        
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    listaCircolari.add(new Circolari(file.getName()));
                    System.out.println(file);
                }
            }
        } else {
            System.out.println("La cartella non contiene file.");
        }
	}
	public static boolean esiste(int numeroDaCercare) {
		for (Circolari circ : listaCircolari) {
			if(circ.getNumero()==numeroDaCercare);
		}
		return false;
	}
	public static void main(String[] args) {
		
		crea("/Users/classe4I/archivio");
		boolean k = esiste(317);	
		System.out.println(k);
        System.out.println("Nomi dei file: " + listaCircolari);
		
	}
}
