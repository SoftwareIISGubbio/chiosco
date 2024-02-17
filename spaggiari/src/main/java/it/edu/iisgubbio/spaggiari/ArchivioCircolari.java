package it.edu.iisgubbio.spaggiari;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArchivioCircolari {
	public static File folder;  // FIXME : pessima soluzione
	private static List<Circolare> listaCircolari = new ArrayList<>();

	public static void aggiornaLista() {
	    System.out.println(folder);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				listaCircolari.add(new Circolare(file.getName()));
			}
		}
	}

	public static boolean esiste(int numeroDaCercare) {
		aggiornaLista();
		for (Circolare circ : listaCircolari) {
			if(circ.getNumero()==numeroDaCercare) {
				return true;
			}
		}
		return false;
	}
}
