package it.edu.iisgubbio.servizitotem;

import java.io.IOException;
import java.util.ArrayList;

import it.edu.iisgubbio.servizitotem.orario.Attivita;
import it.edu.iisgubbio.servizitotem.orario.Input;

public class Archivio {
    public static ArrayList<Attivita> attivita;
    static{
        try {
            attivita = Input.leggiOrarioCurricolari();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
