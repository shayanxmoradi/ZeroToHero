package org.example.zerotohero.exceptions.custom_exceptions;

/**
 * Eine benutzerdefinierte checked Exception, die ausgelöst wird,
 * wenn ein Bankkonto nicht genügend Guthaben für eine Transaktion hat.
 */
class UngenuegendeDeckungException extends Exception { // Erbt von Exception -> checked
    private double fehlenderBetrag;

    public UngenuegendeDeckungException(String message, double fehlenderBetrag) {
        super(message); // Ruft den Konstruktor der Superklasse (Exception) auf
        this.fehlenderBetrag = fehlenderBetrag;
    }

    public double getFehlenderBetrag() {
        return fehlenderBetrag;
    }
}

/**
 * Eine benutzerdefinierte unchecked Exception für ungültige Transaktionsbeträge.
 */
class UngueltigerBetragRuntimeException extends RuntimeException { // Erbt von RuntimeException -> unchecked
    public UngueltigerBetragRuntimeException(String message) {
        super(message);
    }
}

/**
 * Ein einfaches Bankkonto, das benutzerdefinierte Exceptions verwenden kann.
 */
class BankkontoMitCustomExceptions {
    private double kontostand;
    private String kontoinhaber;

    public BankkontoMitCustomExceptions(String kontoinhaber, double initialerKontostand) {
        this.kontoinhaber = kontoinhaber;
        if (initialerKontostand < 0) {
            throw new UngueltigerBetragRuntimeException("Initialer Kontostand darf nicht negativ sein.");
        }
        this.kontostand = initialerKontostand;
    }

    public void einzahlen(double betrag) {
        if (betrag <= 0) {
            throw new UngueltigerBetragRuntimeException("Einzahlungsbetrag muss positiv sein: " + betrag);
        }
        this.kontostand += betrag;
        System.out.println(betrag + " EUR eingezahlt. Neuer Kontostand: " + this.kontostand + " EUR");
    }

    /**
     * Hebt einen Betrag vom Konto ab.
     * @param betrag Der abzuhebende Betrag.
     * @throws UngenuegendeDeckungException wenn der Kontostand nicht ausreicht.
     */
    public void abheben(double betrag) throws UngenuegendeDeckungException {
        if (betrag <= 0) {
            throw new UngueltigerBetragRuntimeException("Abhebungsbetrag muss positiv sein: " + betrag);
        }
        if (this.kontostand < betrag) {
            double fehlt = betrag - this.kontostand;
            throw new UngenuegendeDeckungException(
                    "Nicht genügend Guthaben für Abhebung von " + betrag + " EUR. Es fehlen " + fehlt + " EUR.",
                    fehlt
            );
        }
        this.kontostand -= betrag;
        System.out.println(betrag + " EUR abgehoben. Neuer Kontostand: " + this.kontostand + " EUR");
    }

    public double getKontostand() {
        return kontostand;
    }

    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public static void main(String[] args) {
        BankkontoMitCustomExceptions konto = null;
        try {
            konto = new BankkontoMitCustomExceptions("Max Mustermann", 100.0);
            konto.einzahlen(50.0);
            // konto.einzahlen(-10); // Würde UngueltigerBetragRuntimeException auslösen

            konto.abheben(30.0);
            konto.abheben(150.0); // Sollte UngenuegendeDeckungException auslösen

        } catch (UngenuegendeDeckungException e) {
            System.err.println("Fehler bei der Transaktion: " + e.getMessage());
            System.err.println("Fehlender Betrag: " + e.getFehlenderBetrag() + " EUR");
        } catch (UngueltigerBetragRuntimeException e) {
            System.err.println("Systemfehler: " + e.getMessage());
        } catch (Exception e) { // Allgemeiner Fallback
            System.err.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
        }

        if (konto != null) {
            System.out.println("Aktueller Kontostand von " + konto.getKontoinhaber() + ": " + konto.getKontostand() + " EUR");
        }

        System.out.println("\nTest mit negativem Initialbetrag:");
        try {
            new BankkontoMitCustomExceptions("Test User", -50);
        } catch (UngueltigerBetragRuntimeException e) {
            System.err.println("Fehler beim Erstellen des Kontos: " + e.getMessage());
        }
    }
}