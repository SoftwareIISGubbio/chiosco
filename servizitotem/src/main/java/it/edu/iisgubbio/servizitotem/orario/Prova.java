package it.edu.iisgubbio.servizitotem.orario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

// https://www.baeldung.com/java-with-jsoup
public class Prova {

    public static void main(String[] args) throws IOException {
        var k = Input.leggiOrarioCurricolari();
        System.out.println(k.size());
        long start, stop;

        // ricerca lineare su stream
        start = System.currentTimeMillis();
        ArrayList<Attivita> streamTrovate = (ArrayList<Attivita>) k.stream().filter( e -> e.classe().equals("4 I") ).sorted().collect(Collectors.toList());
        stop = System.currentTimeMillis();
        System.out.println(streamTrovate.getClass());
        System.out.println("%d stream %d ".formatted(streamTrovate.size(), stop-start));

        // ArrayList
        start = System.currentTimeMillis();
        ArrayList<Attivita> arrayListTrovate = new ArrayList<>();
        for(Attivita a: k) {
            if(a.classe().equals("4 I")) {
                arrayListTrovate.add(a);
            }
        }
        Collections.sort(arrayListTrovate);
        stop = System.currentTimeMillis();
        System.out.println("%d ArrayList %d ".formatted(arrayListTrovate.size(), stop-start));
    }

}
