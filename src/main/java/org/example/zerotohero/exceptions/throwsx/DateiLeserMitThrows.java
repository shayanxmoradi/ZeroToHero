package org.example.zerotohero.exceptions.throwsx;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * fur checked Exception
 * Diese Klasse demonstriert die Verwendung des 'throws'-Schlüsselworts,
 * um die Verantwortung für die Behandlung einer checked Exception
 * an die aufrufende Methode weiterzugeben.
 */
class DateiLeserMitThrows {

    /**
     * Liest das erste Zeichen aus einer Datei.
     * Diese Methode deklariert, dass sie eine FileNotFoundException oder IOException auslösen kann.
     * @param dateiname Der Name der zu lesenden Datei.
     * @return Das erste Zeichen der Datei als int.
     * @throws FileNotFoundException wenn die Datei nicht existiert.
     * @throws IOException wenn ein anderer E/A-Fehler auftritt.
     */
    public int leseErstesZeichen(String dateiname) throws FileNotFoundException, IOException {
        FileReader reader = null;
        try {
            File datei = new File(dateiname);
            reader = new FileReader(datei);
            return reader.read(); // Liest ein einzelnes Zeichen
        } finally {
            // Sicherstellen, dass der Reader geschlossen wird, auch wenn eine Exception auftritt
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("FileReader für '" + dateiname + "' geschlossen.");
                } catch (IOException e) {
                    // Diese Exception könnte man hier loggen oder ignorieren,
                    // da die ursprüngliche Exception (falls vorhanden) wichtiger ist.
                    System.err.println("Fehler beim Schließen des FileReaders: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        DateiLeserMitThrows leser = new DateiLeserMitThrows();
        String existierendeDatei = "test.txt"; // Erstelle eine leere Datei "test.txt" im Projektverzeichnis für einen erfolgreichen Test
        String nichtExistierendeDatei = "nichtda.txt";

        // Vorbereitung: Erstelle eine Dummy-Datei für den Test
        try {
            new File(existierendeDatei).createNewFile();
        } catch (IOException e) {
            System.err.println("Konnte Testdatei nicht erstellen: " + e.getMessage());
        }


        System.out.println("--- Fall 1: Datei existiert ---");
        try {
            int zeichen = leser.leseErstesZeichen(existierendeDatei);
            if (zeichen != -1) {
                System.out.println("Erstes Zeichen: " + (char) zeichen);
            } else {
                System.out.println("Datei ist leer.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fehler beim Lesen (FileNotFound): " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen (IO): " + e.getMessage());
        }

        System.out.println("\n--- Fall 2: Datei existiert nicht ---");
        try {
            leser.leseErstesZeichen(nichtExistierendeDatei);
        } catch (FileNotFoundException e) {
            System.err.println("Fehler beim Lesen (FileNotFound): " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen (IO): " + e.getMessage());
        } finally {
            // Aufräumen: Lösche die Dummy-Datei
            new File(existierendeDatei).delete();
        }
    }
}