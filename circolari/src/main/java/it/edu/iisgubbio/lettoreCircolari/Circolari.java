package it.edu.iisgubbio.lettoreCircolari;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****************************************************************************
 * Rappresenta il documento di una circolare sul file system
 ***************************************************************************/
public class Circolari {
	private String nomeCompleto;
	private String nome;
	private int numero;
	
	public Circolari(String nomeFile){
		nomeCompleto=nomeFile;
		//Integrazione nuovo metodo divisione stringa
		Pattern r = Pattern.compile("([0-9]+) *- *(.*)\\.pdf$");
		
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
