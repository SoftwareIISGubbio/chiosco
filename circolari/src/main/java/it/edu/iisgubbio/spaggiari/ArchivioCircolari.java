package it.edu.iisgubbio.spaggiari;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArchivioCircolari {
	static File folder =new File(System.getProperty("user.home")+"/archivio/");
	static List<Circolare> listaCircolari = new ArrayList<>();

//	public static void aggiornaLista() {
//		File[] listOfFiles = folder.listFiles();
//		for (File file : listOfFiles) {
//			if (file.isFile()) {
//				listaCircolari.add(new Circolare(file.getName()));
//			}
//		}
//	}
	public ArchivioCircolari() {
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				listaCircolari.add(new Circolare(file.getName()));
			}
		}
	}
	
	public static void aggiungiCircolare(String nome) {
		listaCircolari.add(new Circolare(nome));
	}
	
	public static boolean esiste(int numeroDaCercare) {
//		aggiornaLista();
		for (Circolare circ : listaCircolari) {
			if(circ.getNumero()==numeroDaCercare) {
				return true;
			}
		}
		return false;
	}
}
