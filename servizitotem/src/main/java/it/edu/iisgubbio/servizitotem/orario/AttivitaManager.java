package it.edu.iisgubbio.servizitotem.orario;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttivitaManager {
    @GetMapping("orario/attivita")
    public ArrayList<Attivita> cerca(
            @RequestParam(required = false) String docente,
            @RequestParam(required = false) String classe,
            @RequestParam(required = false) String stanza
    ) {
        ArrayList<Attivita> r = new ArrayList<>();
        boolean prendi;
        for(Attivita a: Archivio.attivita) {
            prendi = true;
            if(docente!=null && !docente.equals(a.docente())) {
                prendi=false;
            }
            if(classe!=null && !classe.equals(a.classe())) {
                prendi=false;
            }
            if(stanza!=null && !stanza.equals(a.stanza())) {
                prendi=false;
            }
            if(prendi) {
                r.add(a);
            }
        }
        return r;
    }

    @GetMapping("orario/info/docenti")
    public String[] elencaDocenti() {
        HashSet<String> doc = new HashSet<String>();
        for(Attivita a: Archivio.attivita) {
            doc.add( a.docente() );
        }
        return doc.toArray( new String[0] );
    }

    @GetMapping("orario/info/classi")
    public String[] elencaClassi() {
        HashSet<String> cla = new HashSet<String>();
        for(Attivita a: Archivio.attivita) {
            cla.add( a.classe() );
        }
        return cla.toArray( new String[0] );
    }

    @GetMapping("orario/info/stanze")
    public String[] elencastanze() {
        HashSet<String> sta = new HashSet<String>();
        for(Attivita a: Archivio.attivita) {
            sta.add( a.stanza() );
        }
        return sta.toArray( new String[0] );
    }
}