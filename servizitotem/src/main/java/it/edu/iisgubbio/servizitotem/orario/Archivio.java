package it.edu.iisgubbio.servizitotem.orario;

import java.io.IOException;
import java.util.ArrayList;

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
