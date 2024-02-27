package it.edu.iisgubbio.spaggiari;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/****************************************************************************
 * Rappresenta il documento di una circolare sul file system
 *
 * @author Filippo Nardoni
 ***************************************************************************/
public class Circolare {
    private String nomeCompleto;
    private String nome;
    private int numero;

    /************************************************************************
     * Imposta il nomeCompleto del file da cui poi prendere il numero e il
     * nome
     *
     * @param nomeFile il nome completo del file
     ***********************************************************************/
    public Circolare(String nomeFile){
        nomeCompleto=nomeFile;

        Matcher m = patMat(nomeCompleto);

        if (m.find( )) {
            numero=Integer.parseInt(m.group(1));
            nome=m.group(2);
            System.out.println("numero circolare: " + m.group(1) );
            System.out.println("nome circolare: " + m.group(2) );
        } else {
            System.out.println("NO MATCH");
        }
    }

    /************************************************************************
     * Metodo di utilità per costruire un Matcher
     *
     * @param nomeCompleto il nome completo del file
     ***********************************************************************/
    private static Matcher patMat(String nomeCompleto) {
        Pattern r = Pattern.compile("^CIRC([0-9]+) *(.*)$");

        return r.matcher(nomeCompleto);
    }

    /************************************************************************
     * Ricava il numero della circolare utilizzando il nome completo del file
     *
     * @param nomeCompleto il nome completo del file
     ***********************************************************************/
    public static int getNumeroDaNome(String nomeCompleto) {
        Matcher m = patMat(nomeCompleto);

        if (m.find( )) {
            return Integer.parseInt(m.group(1));
        } else {
            System.out.println("NO MATCH : getNumero");
        }
        return 0;
    }

    /************************************************************************
     * Verifica se il file rispetta i requisiti per essere un oggetto
     * Circolare partendo dal nome completo
     *
     * @param nomeCompleto il nome completo del file
     ***********************************************************************/
    public static boolean isCircolare(String nomeCompleto) {
        Matcher m = patMat(nomeCompleto);

        if (m.find( )) {
            return true;
        }
        return false;
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
