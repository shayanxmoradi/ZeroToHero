package org.example.zerotohero.oop.kapselung;
// Kapselung

/**
 * Kapselung bedeutet, die Daten (Attribute) eines Objekts vor direktem Zugriff von außen zu schützen und
 * den Zugriff nur über definierte Schnittstellen (Methoden) zu erlauben. Dies erhöht die Sicherheit und
 * Kontrolle über die Daten. Man spricht hier auch oft von "Data Hiding".
 */
public class BankKonto {

        private double kontostand; // Gekapseltes Attribut

        /**
         * Konstruktor für ein Bankkonto.
         *
         * @param initialerKontostand Der anfängliche Kontostand.
         */
        public BankKonto(double initialerKontostand) {
            if (initialerKontostand >= 0) {
                this.kontostand = initialerKontostand;
            } else {
                this.kontostand = 0;
                System.out.println("Der initiale Kontostand darf nicht negativ sein. Kontostand wurde auf 0 gesetzt.");
            }
        }

        /**
         * Gibt den aktuellen Kontostand zurück.
         *
         * @return Der aktuelle Kontostand.
         */
        public double getKontostand() {
            return kontostand;
        }

        /**
         * Zahlt einen Betrag auf das Konto ein.
         *
         * @param betrag Der einzuzahlende Betrag. Muss positiv sein.
         */
        public void einzahlen(double betrag) {
            if (betrag > 0) {
                this.kontostand += betrag;
                System.out.println(betrag + " EUR eingezahlt. Neuer Kontostand: " + this.kontostand + " EUR");
            } else {
                System.out.println("Der Einzahlungsbetrag muss positiv sein.");
            }
        }

        /**
         * Hebt einen Betrag vom Konto ab, falls ausreichend Deckung vorhanden ist.
         *
         * @param betrag Der abzuhebende Betrag. Muss positiv sein.
         */
        public void abheben(double betrag) {
            if (betrag <= 0) {
                System.out.println("Der Abhebungsbetrag muss positiv sein.");
            } else if (this.kontostand >= betrag) {
                this.kontostand -= betrag;
                System.out.println(betrag + " EUR abgehoben. Neuer Kontostand: " + this.kontostand + " EUR");
            } else {
                System.out.println("Nicht genügend Guthaben vorhanden. Abhebung von " + betrag + " EUR nicht möglich.");
            }
        }


    public static void main(String[] args) {
        BankKonto meinKonto = new BankKonto(100.0);
        System.out.println("Initialer Kontostand: " + meinKonto.getKontostand() + " EUR");

        meinKonto.einzahlen(50.0);
        meinKonto.abheben(30.0);
        meinKonto.abheben(150.0); // Versuch, mehr abzuheben als vorhanden
        // meinKonto.kontostand = -1000; // Direkter Zugriff nicht möglich und würde die Logik umgehen
    }
}