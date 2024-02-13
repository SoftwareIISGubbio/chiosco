package it.edu.iisgubbio.servizitotem.orario;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Input {

    private static class SpanManager {
        private boolean spazi[][] = new boolean[20][20]; // FIXME numeri arbitrari
        public void occupa(int r, int c) {
            spazi[r][c]=true;
        }
        public int primaLibera(int r) {
            for(int i=0; i<20; i++) {
                if(spazi[r][i]==false) {
                    return i;
                }
            }
            return -1; // FIXME: tanto non succede!
        }
    }

    public static ArrayList<Attivita> leggiOrarioCurricolari() throws IOException{
        ArrayList<Attivita> risposta = new ArrayList<>();
        Document doc = Jsoup.connect("https://lnx.iisgubbio.edu.it/Orario/orario_docenti_nuovo.html").get();
        doc.select("table").forEach((table)->{
            Element head = table.select("thead th").first();
            if(head != null) {
                String nome = head.text();
                SpanManager sm = new SpanManager();
                Elements righe = table.select("tbody tr");
                for(int iTR=0; iTR<righe.size(); iTR++) {
                    Elements colonne = righe.get(iTR).select("td");
                    for(int iTD=0; iTD<colonne.size(); iTD++) {
                        String rspan = colonne.get(iTD).attr("rowspan");
                        int numeroOre = rspan.equals("") ? 1 : Integer.parseInt(rspan);
                        Element studenti = colonne.get(iTD).select(".studentsset").first();
                        Element stanze = colonne.get(iTD).select(".room").first();
                        int colonnaVera = sm.primaLibera(iTR);
                        if(studenti !=null ) {
                            String stanza = stanze!=null ? stanze.text() : "?stanza?";
                            for(int consecutive=0; consecutive<numeroOre; consecutive++) {
                                risposta.add( new Attivita(nome,studenti.text(),stanza,
                                        colonnaVera,iTR+consecutive) );
                                sm.occupa(iTR+consecutive, colonnaVera);
                            }
                        }  else {
                            // in ogni caso occupo gli spazi sulla matrice
                            for(int consecutive=0; consecutive<numeroOre; consecutive++) {
                                sm.occupa(iTR+consecutive, colonnaVera);
                            }
                        }
                    }
                }
            }
        });
        return risposta;
    }

}
