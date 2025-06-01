package org.example.zerotohero.oop.vererbung;

/**
 * ererbung ermöglicht es, eine neue Klasse (Subklasse oder abgeleitete Klasse) auf Basis
 * einer bestehenden Klasse (Superklasse oder Basisklasse) zu erstellen. Die Subklasse erbt
 * Attribute und Methoden der Superklasse und kann eigene hinzufügen oder geerbte Methoden überschreiben.
 * Dies fördert die Wiederverwendbarkeit von Code und den Aufbau von Hierarchien.
 */
public class Fahrzeug {
    protected String marke; // protected: Zugriff für Subklassen und im selben Paket
    private int baujahr;    // private: Nur innerhalb dieser Klasse zugreifbar

    /**
     * Konstruktor für ein Fahrzeug.
     * @param marke Die Marke des Fahrzeugs.
     * @param baujahr Das Baujahr des Fahrzeugs.
     */
    public Fahrzeug(String marke, int baujahr) {
        this.marke = marke;
        this.baujahr = baujahr;
    }

    /**
     * Gibt die Marke des Fahrzeugs zurück.
     * @return Die Marke.
     */
    public String getMarke() {
        return marke;
    }

    /**
     * Gibt das Baujahr des Fahrzeugs zurück.
     * @return Das Baujahr.
     */
    public int getBaujahr() {
        return baujahr;
    }

    /**
     * Startet das Fahrzeug.
     * Diese Methode kann von Subklassen überschrieben werden.
     */
    public void starten() {
        System.out.println("Das Fahrzeug startet.");
    }

    /**
     * Stoppt das Fahrzeug.
     */
    public void stoppen() {
        System.out.println("Das Fahrzeug stoppt.");
    }
}

/**
 * Subklasse Auto, die von Fahrzeug erbt.
 * Fügt spezifische Eigenschaften und Methoden für Autos hinzu.
 */
class Auto extends Fahrzeug {
    private int anzahlTueren;

    /**
     * Konstruktor für ein Auto.
     * @param marke Die Marke des Autos.
     * @param baujahr Das Baujahr des Autos.
     * @param anzahlTueren Die Anzahl der Türen des Autos.
     */
    public Auto(String marke, int baujahr, int anzahlTueren) {
        super(marke, baujahr); // Aufruf des Konstruktors der Superklasse
        this.anzahlTueren = anzahlTueren;
    }

    /**
     * Gibt die Anzahl der Türen zurück.
     * @return Die Anzahl der Türen.
     */
    public int getAnzahlTueren() {
        return anzahlTueren;
    }

    /**
     * Überschreibt die starten() Methode der Superklasse Fahrzeug.
     * Gibt eine spezifische Startmeldung für ein Auto aus.
     */
    @Override // Annotation zeigt an, dass diese Methode eine Superklassen-Methode überschreibt
    public void starten() {
        System.out.println("Das Auto der Marke " + super.getMarke() + " startet den Motor.");
    }

    /**
     * Spezifische Methode für die Klasse Auto.
     */
    public void kofferraumOeffnen() {
        System.out.println("Der Kofferraum des Autos wird geöffnet.");
    }

    public static void main(String[] args) {
        Fahrzeug meinFahrzeug = new Fahrzeug("Unbekannt", 2020);
        meinFahrzeug.starten();
        System.out.println("Marke: " + meinFahrzeug.getMarke() + ", Baujahr: " + meinFahrzeug.getBaujahr());

        System.out.println("\n--- Auto ---");
        Auto meinAuto = new Auto("BMW", 2023, 4);
        meinAuto.starten(); // Ruft die überschriebene Methode auf
        meinAuto.stoppen();
        meinAuto.kofferraumOeffnen();
        System.out.println("Marke: " + meinAuto.getMarke() + ", Baujahr: " + meinAuto.getBaujahr() + ", Türen: " + meinAuto.getAnzahlTueren());
    }
}
