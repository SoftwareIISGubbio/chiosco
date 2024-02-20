package it.edu.iisgubbio.spaggiari;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * La classe gestisce l'archivio delle circolari
 *
 * @author Filippo Nardoni
 ***************************************************************************/
public class ArchivioCircolari {
    private File folder;  // FIXME : pessima soluzione
    private List<Circolare> listaCircolari = new ArrayList<>();

    /************************************************************************
     * Imposta la cartella all'interno della quale lavorare
     *
     * @param indirizzoCartella cartella in cui vengono memorizzati i file
     ***********************************************************************/
    public ArchivioCircolari(String indirizzoCartella) {
        folder= new File(indirizzoCartella);
    }
    /************************************************************************
     * Aggirona la lista per controllare eventuali file aggiunti
     ***********************************************************************/
    public void aggiornaLista() {
        System.out.println(folder);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                listaCircolari.add(new Circolare(file.getName()));
            }
        }
    }

    /************************************************************************
     * Permette di ottenere tutte gli oggetti Circolari presenti nel archivio
     *
     * @return lista vettore che contiene tutte le circolari presenti nella
     * cartella folder
     ***********************************************************************/
    public List<Circolare> getLista() {
        File[] listOfFiles = folder.listFiles();
        List<Circolare> lista = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                lista.add(new Circolare(file.getName()));
            }
        }
        return lista;
    }
    /************************************************************************
     * Controlla l'esistenza di una circolare nell'archivio tramite il suo
     * numero
     *
     * @param numeroDaCercare indica il numero della circolare che dovrà
     * essere cercato
     ***********************************************************************/
    public boolean esiste(int numeroDaCercare) {
        aggiornaLista();
        for (Circolare circ : listaCircolari) {
            if(circ.getNumero()==numeroDaCercare) {
                return true;
            }
        }
        return false;
    }
}
