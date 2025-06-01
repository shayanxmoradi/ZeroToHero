package org.example.zerotohero.exceptions.try_with_resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Diese Klasse demonstriert die Verwendung von try-with-resources
 * für das automatische Schließen von Ressourcen wie FileReader und BufferedReader.
 */
class TryWithResourcesDemo {

    /**
     * Verwendet try-with-resources, um FileReader und BufferedReader automatisch zu schließen.
     * @param dateipfad Der Pfad zur Datei.
     */
    public void leseDatei(String dateipfad) {
        // FileReader und BufferedReader implementieren AutoCloseable
        try (FileReader fr = new FileReader(dateipfad);
             BufferedReader br = new BufferedReader(fr)) {

            String zeile;
            System.out.println("Inhalt der Datei '" + dateipfad + "':");
            while ((zeile = br.readLine()) != null) {
                System.out.println(zeile);
            }
        } catch (IOException e) { // FileNotFoundException ist eine Unterklasse von IOException
            System.err.println("Fehler beim Lesen der Datei '" + dateipfad + "': " + e.getMessage());
            // FileNotFoundException und andere IOExceptions reagieren oder loggen.
        }
        // fr und br sind hier automatisch geschlossen, auch wenn eine Exception aufgetreten ist.
    }

    public static void main(String[] args) {
        TryWithResourcesDemo demo = new TryWithResourcesDemo();
        String datei = "beispiel.txt";

        // Erstelle eine Beispieldatei für den Test
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(datei), "Hallo Welt!\nZweite Zeile.".getBytes());
        } catch (IOException e) {
            System.err.println("Konnte Beispieldatei nicht erstellen: " + e.getMessage());
            return;
        }

        demo.leseDatei(datei);
        System.out.println("\nVersuch, eine nicht existierende Datei zu lesen:");
        demo.leseDatei("gibtsnicht.txt");

        // Lösche die Beispieldatei
        try {
            java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(datei));
        } catch (IOException e) {
            System.err.println("Konnte Beispieldatei nicht löschen: " + e.getMessage());
        }
    }
}