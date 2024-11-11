import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Maneggio {
    private List<Cavallo> cavalli;
    private int percorsoTotale;
    private List<Cavallo> cavalliFinisher;

    public Maneggio(int percorsoTotale) {
        this.percorsoTotale = percorsoTotale;
        this.cavalli = new ArrayList<>();
        this.cavalliFinisher = new ArrayList<>();
    }


    public void aggiungi(String nome, int velocita) {
        Cavallo cavallo = new Cavallo(nome, percorsoTotale, velocita);
        Thread th = new Thread(cavallo);
        cavalli.add(cavallo);
    }


    public void go() {

        for (Cavallo cavallo : cavalli) {
            new Thread(cavallo).start();
        }


        for (Cavallo cavallo : cavalli) {
            try {
                new Thread(cavallo).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (Cavallo cavallo : cavalli) {
            if (cavallo.haFinito()) {
                cavalliFinisher.add(cavallo);
            }
        }

        // Ordina i cavalli
        Collections.sort(cavalliFinisher, (c1, c2) -> Long.compare(c1.getTempoTotale(), c2.getTempoTotale()));


        System.out.println("Classifica finale:");
        for (int i = 0; i < cavalliFinisher.size(); i++) {
            Cavallo cavallo = cavalliFinisher.get(i);
            System.out.println((i + 1) + ". " + cavallo.getNome() + " in " + cavallo.getTempoTotale() / 1000.0 + " secondi");
        }
    }


    public void salvaRisultati(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)))) {

            writer.write("\nClassifica:\n");


            for (int i = 0; i < cavalliFinisher.size(); i++) {
                Cavallo cavallo = cavalliFinisher.get(i);
                String risultato = (i + 1) + ". " + cavallo.getNome() + " in " + cavallo.getTempoTotale() / 1000.0 + " secondi\n";
                writer.write(risultato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
