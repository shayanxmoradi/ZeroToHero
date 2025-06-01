package org.example.zerotohero.exceptions;

/**
 * Diese Klasse demonstriert die grundlegende Verwendung von try-catch-finally Blöcken
 * zur Ausnahmebehandlung bei einer einfachen Division.
 */
class GrundlegendeExceptionBehandlung {

    public void fuehreDivisionAus(int zeahler, int nenner) {
        try {
            System.out.println("Versuche Division: " + zeahler + " / " + nenner);
            int ergebnis = zeahler / nenner; // Kann ArithmeticException auslösen
            System.out.println("Ergebnis: " + ergebnis);
        } catch (ArithmeticException e) {
            System.err.println("Fehler: Division durch Null ist nicht erlaubt!");
            // e.printStackTrace(); // Gibt den Stack Trace aus, nützlich für Debugging
        } catch (Exception e) { // Ein allgemeinerer Catch-Block
            System.err.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
        }


        finally {
            System.out.println("Der finally-Block wird immer ausgeführt.");
            // Hier könnten Ressourcen bereinigt werden, z.B. Schließen einer Datei.
        }
        System.out.println("Nach dem try-catch-finally Block.");
    }

    public static void main(String[] args) {
        GrundlegendeExceptionBehandlung handler = new GrundlegendeExceptionBehandlung();

        System.out.println("--- Fall 1: Erfolgreiche Division ---");
        handler.fuehreDivisionAus(10, 2);

        System.out.println("\n--- Fall 2: Division durch Null ---");
        handler.fuehreDivisionAus(5, 0);

        System.out.println("\n--- Fall 3: Anderer Fehler (hier nicht simuliert, aber der Catch-Block ist da) ---");
        // Um den allgemeinen Exception-Block zu testen, müsste man eine andere Exception provozieren.
    }
}