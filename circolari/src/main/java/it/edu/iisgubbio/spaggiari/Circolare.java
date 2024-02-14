package it.edu.iisgubbio.spaggiari;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****************************************************************************
 * Rappresenta il documento di una circolare sul file system
 ***************************************************************************/
public class Circolare {
	private String nomeCompleto;
	private String nome;
	private int numero;
	
	public Circolare(String nomeFile){
		nomeCompleto=nomeFile;
		//Integrazione nuovo metodo divisione stringa
		//Ho cambaito il programma dato che le circolari non sono divise da un -
		Pattern r = Pattern.compile("CIRC([0-9]+) *(.*)\\.pdf$");
		
		Matcher m = r.matcher(nomeCompleto);
		
	    if (m.find( )) {
	    	numero=Integer.parseInt(m.group(1));
	    	nome=m.group(2);
	    	System.out.println("Found value: " + m.group(1) );
	        System.out.println("Found value: " + m.group(2) );
	    } else {
	        System.out.println("NO MATCH");
		}
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getNome() {
		return nome;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return "nome: " + nomeCompleto;
	}
	
}
