import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Lunghezza percorso (in metri): ");
        int lunghezza = scanner.nextInt();
        scanner.nextLine();
        Maneggio maneggio = new Maneggio(lunghezza);

        System.out.print("Numero dei cavalli: ");
        int nCavalli = scanner.nextInt();
        scanner.nextLine();


        for (int i = 0; i < nCavalli; i++) {
            System.out.print("Nome cavallo no. " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            System.out.print("Velocità (metri al secondo) di " + nome + ": ");
            int velocita = scanner.nextInt();
            scanner.nextLine();
            maneggio.aggiungi(nome, velocita);
            System.out.println("");
        }


        maneggio.go();

        String fileName = "risultati.txt";
        maneggio.salvaRisultati(fileName);

        scanner.close();
    }
}
