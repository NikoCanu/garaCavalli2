import java.util.Random;

public class Cavallo implements Runnable {
    private String nome;
    private int metriPercorsi = 0;
    private int metriTotali;
    private int velocita;
    private boolean infortunato = false;
    private Random rand;
    private long tempoIniziale;
    private long tempoTotale;


    public Cavallo(String nome, int metriTotali, int velocita) {
        this.nome = nome;
        this.metriTotali = metriTotali;
        this.velocita = velocita;
        this.rand = new Random();
    }

    @Override
    public void run() {
        tempoIniziale = System.currentTimeMillis();

        while (metriPercorsi < metriTotali) {


            if (rand.nextInt(100) < 5) {
                infortunato = true;
                break;
            }


            metriPercorsi += velocita;

            if (metriPercorsi > metriTotali) {
                metriPercorsi = metriTotali;
            }


            System.out.println(nome + " sta al metro " + metriPercorsi + " su " + metriTotali);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tempoTotale = System.currentTimeMillis() - tempoIniziale;

        if (infortunato) {
            System.out.println(nome + " si è infortunato e ha abbandonato la gara!");
        } else {
            System.out.println(nome + " ha completato la gara!");
        }
    }

    public boolean isInfortunato() {
        return infortunato;
    }

    public long getTempoTotale() {
        return tempoTotale;
    }

    public String getNome() {
        return nome;
    }

    public boolean haFinito() {
        return metriPercorsi >= metriTotali && !infortunato;
    }
}
