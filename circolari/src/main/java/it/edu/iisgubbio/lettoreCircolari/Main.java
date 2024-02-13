package it.edu.iisgubbio.lettoreCircolari;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static List<Circolari> listaCircolari = new ArrayList<>();
	
	public static void crea(String nomeCartella) {
		//modificato folder così che utilizzi la cartella dove vengono scaricati i file
		File folder = new File(nomeCartella);
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
		
		crea(System.getProperty("user.home")+"/archivio/");
        System.out.println("Nomi dei file: " + listaCircolari);
		
	}
}
