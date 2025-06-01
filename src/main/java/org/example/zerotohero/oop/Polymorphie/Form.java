package org.example.zerotohero.oop.Polymorphie;


    /**
     * Polymorphie ("Vielgestaltigkeit") bedeutet, dass Objekte unterschiedlicher Klassen auf dieselbe Nachricht (Methodenaufruf)
     * unterschiedlich reagieren können. Es gibt zwei Hauptarten:
     *
     * Compile-Zeit-Polymorphie (Method Overloading): Mehrere Methoden mit demselben Namen, aber unterschiedlichen Parametern in derselben Klasse.
     * Der Compiler entscheidet zur Compile-Zeit, welche Methode aufgerufen wird.
     * Laufzeit-Polymorphie (Method Overriding): Eine Subklasse stellt eine spezifische Implementierung einer Methode bereit,
     * die bereits in ihrer Superklasse definiert ist. Die Entscheidung, welche Methode ausgeführt wird, fällt zur Laufzeit,
     * basierend auf dem tatsächlichen Objekttyp. Dies hängt eng mit der Vererbung zusammen.
     */
    interface Form {
        /**
         * Zeichnet die Form.
         * Die konkrete Implementierung hängt von der jeweiligen Form ab.
         */
        void zeichnen(); // Abstrakte Methode (implizit public abstract)
    }

    /**
     * Klasse Kreis, die das Interface Form implementiert.
     */
    class Kreis implements Form {
        private String farbe;

        /**
         * Konstruktor für einen Kreis.
         * @param farbe Die Farbe des Kreises.
         */
        public Kreis(String farbe) {
            this.farbe = farbe;
        }

        /**
         * Implementiert die zeichnen Methode für einen Kreis.
         */
        @Override
        public void zeichnen() {
            System.out.println("Zeichne einen " + farbe + "en Kreis.");
        }
    }

    /**
     * Klasse Rechteck, die das Interface Form implementiert.
     */
    class Rechteck implements Form {
        private int breite;
        private int hoehe;

        /**
         * Konstruktor für ein Rechteck.
         * @param breite Die Breite des Rechtecks.
         * @param hoehe Die Höhe des Rechtecks.
         */
        public Rechteck(int breite, int hoehe) {
            this.breite = breite;
            this.hoehe = hoehe;
        }

        /**
         * Implementiert die zeichnen Methode für ein Rechteck.
         */
        @Override
        public void zeichnen() {
            System.out.println("Zeichne ein Rechteck mit Breite " + breite + " und Höhe " + hoehe + ".");
        }
    }

    /**
     * Klasse, die Methodenüberladung (Compile-Zeit-Polymorphie) demonstriert.
     */
    class Rechner {

        /**
         * Addiert zwei Ganzzahlen.
         * @param a Die erste Ganzzahl.
         * @param b Die zweite Ganzzahl.
         * @return Die Summe der beiden Zahlen.
         */
        public int addiere(int a, int b) {
            System.out.println("Addiere (int, int) aufgerufen");
            return a + b;
        }

        /**
         * Addiert drei Ganzzahlen. (Überladung)
         * @param a Die erste Ganzzahl.
         * @param b Die zweite Ganzzahl.
         * @param c Die dritte Ganzzahl.
         * @return Die Summe der drei Zahlen.
         */
        public int addiere(int a, int b, int c) {
            System.out.println("Addiere (int, int, int) aufgerufen");
            return a + b + c;
        }

        /**
         * Addiert zwei Gleitkommazahlen. (Überladung)
         * @param a Die erste Gleitkommazahl.
         * @param b Die zweite Gleitkommazahl.
         * @return Die Summe der beiden Zahlen.
         */
        public double addiere(double a, double b) {
            System.out.println("Addiere (double, double) aufgerufen");
            return a + b;
        }
    }


     class PolymorphieDemo {
        public static void main(String[] args) {
            System.out.println("--- Laufzeit-Polymorphie (Method Overriding) ---");
            Form meineForm1 = new Kreis("Rot");   // Superklassen-Referenz zeigt auf Subklassen-Objekt
            Form meineForm2 = new Rechteck(5, 10); // Superklassen-Referenz zeigt auf Subklassen-Objekt

            meineForm1.zeichnen(); // Ruft zeichnen() von Kreis auf
            meineForm2.zeichnen(); // Ruft zeichnen() von Rechteck auf

            // Array von Formen
            Form[] formen = {new Kreis("Blau"), new Rechteck(3, 7), new Kreis("Grün")};
            for (Form f : formen) {
                f.zeichnen(); // Polymorpher Aufruf
            }

            System.out.println("\n--- Compile-Zeit-Polymorphie (Method Overloading) ---");
            Rechner rechner = new Rechner();
            System.out.println("Summe 1: " + rechner.addiere(5, 10));
            System.out.println("Summe 2: " + rechner.addiere(5, 10, 15));
            System.out.println("Summe 3: " + rechner.addiere(2.5, 3.7));
        }
    }
