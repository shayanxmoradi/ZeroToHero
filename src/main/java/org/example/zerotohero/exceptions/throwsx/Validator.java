package org.example.zerotohero.exceptions.throwsx;

/**
 * Diese Klasse demonstriert, wie man mit 'throw' explizit Exceptions auslöst,
 * oft basierend auf Validierungsprüfungen.
 */
class Validator {

    /**
     * Validiert das Alter einer Person.
     * @param alter Das zu validierende Alter.
     * @throws IllegalArgumentException wenn das Alter negativ ist.
     */
    public void validiereAlter(int alter) {
        if (alter < 0) {
            // Explizites Auslösen einer unchecked Exception
            throw new IllegalArgumentException("Alter darf nicht negativ sein. Angegeben: " + alter);
        }
        if (alter > 120) {
            // Beispiel für das Auslösen einer anderen Art von Exception oder einer benutzerdefinierten.
            // Hier bleiben wir bei IllegalArgumentException zur Vereinfachung.
            System.out.println("Warnung: Das Alter ist sehr hoch: " + alter);
        }
        System.out.println("Alter " + alter + " ist gültig.");
    }

    public static void main(String[] args) {
        Validator validator = new Validator();

        try {
            validator.validiereAlter(25);
            validator.validiereAlter(-5); // Dies wird eine Exception auslösen
            validator.validiereAlter(30); // Diese Zeile wird nicht erreicht
        } catch (IllegalArgumentException e) {
            System.err.println("Validierungsfehler: " + e.getMessage());
        }

        System.out.println("\nVersuch mit hohem Alter:");
        try {
            validator.validiereAlter(130);
        } catch (IllegalArgumentException e) {
            System.err.println("Validierungsfehler: " + e.getMessage());
        }
    }
}