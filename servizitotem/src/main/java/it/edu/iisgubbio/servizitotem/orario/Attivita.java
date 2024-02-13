package it.edu.iisgubbio.servizitotem.orario;

/****************************************************************************
 * Parresenta una singola attività, è un oggetto immutabile
 ***************************************************************************/
public record Attivita(String docente, String classe, String stanza, int giorno, int ora) implements Comparable<Attivita> {

    private static final String[] ng = {"lun","mar","mer","gio","ven"};
    @Override
    public int compareTo(Attivita o) {
        int delta = giorno - o.giorno;
        if(delta==0) {
            delta = ora - o.ora;
        }
        return delta;
    }

    @Override
    public String toString() {
        return "["+ng[giorno]+" "+ora+" "+stanza+"] "+docente+" "+classe;
    }
}
