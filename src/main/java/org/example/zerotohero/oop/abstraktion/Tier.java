package org.example.zerotohero.oop.abstraktion;

/**
 * Abstraktion bedeutet, komplexe Realität zu vereinfachen, indem man sich auf die wesentlichen Aspekte konzentriert und irrelevante Details ausblendet.
 * In Java wird dies oft durch abstrakte Klassen und Interfaces erreicht.
 *
 * Abstrakte Klassen: Können nicht instanziiert werden und können abstrakte Methoden (ohne Implementierung) sowie konkrete Methoden enthalten.
 * Sie dienen als Basis für Subklassen, die die abstrakten Methoden implementieren müssen.
 * Interfaces: Definieren einen Vertrag von Methoden, die eine Klasse implementieren muss.
 * Alle Methoden in einem Interface (vor Java 8) waren implizit public abstract. Seit Java 8 können Interfaces auch default und static Methoden mit
 * Implementierung enthalten.
 */
public abstract class Tier {
    private String name;

    /**
     * Konstruktor für ein Tier.
     * @param name Der Name des Tieres.
     */
    public Tier(String name) {
        this.name = name;
    }

    /**
     * Abstrakte Methode zur Lautäußerung.
     * Jede Subklasse muss diese Methode implementieren.
     */
    public abstract void lautGeben(); // Keine Implementierung hier

    /**
     * Konkrete Methode, die von allen Subklassen geerbt wird.
     */
    public void schlafen() {
        System.out.println(name + " schläft.");
    }

    /**
     * Getter für den Namen.
     * @return Der Name des Tieres.
     */
    public String getName() {
        return name;
    }
}

/**
 * Konkrete Klasse Hund, die von Tier erbt.
 */
class Hund extends Tier {
    /**
     * Konstruktor für einen Hund.
     * @param name Der Name des Hundes.
     */
    public Hund(String name) {
        super(name);
    }

    /**
     * Implementiert die abstrakte Methode lautGeben für einen Hund.
     */
    @Override
    public void lautGeben() {
        System.out.println(getName() + " bellt: Wuff!");
    }
}

/**
 * Konkrete Klasse Katze, die von Tier erbt.
 */
class Katze extends Tier {
    /**
     * Konstruktor für eine Katze.
     * @param name Der Name der Katze.
     */
    public Katze(String name) {
        super(name);
    }

    /**
     * Implementiert die abstrakte Methode lautGeben für eine Katze.
     */
    @Override
    public void lautGeben() {
        System.out.println(getName() + " miaut: Miau!");
    }
}

/**
 * Interface, das das Verhalten definiert, fliegen zu können.
 * Kann von verschiedenen Klassen implementiert werden, auch wenn sie nicht in derselben Vererbungshierarchie stehen.
 */
interface Fliegfaehig {
    // Seit Java 8 sind default Methoden mit Implementierung in Interfaces möglich
    default void landeVorgangStarten() {
        System.out.println("Landeanflug wird eingeleitet...");
    }

    /**
     * Abstrakte Methode zum Fliegen.
     */
    void fliegen();

    /**
     * Statische Methode im Interface (seit Java 8).
     * @return Die maximale Flughöhe in Metern.
     */
    static int getMaxFlughoehe() {
        return 10000; // Beispielwert
    }
}

/**
 * Klasse Vogel, die Tier erweitert und Fliegfaehig implementiert.
 */
class Vogel extends Tier implements Fliegfaehig {

    /**
     * Konstruktor für einen Vogel.
     * @param name Der Name des Vogels.
     */
    public Vogel(String name) {
        super(name);
    }

    @Override
    public void lautGeben() {
        System.out.println(getName() + " zwitschert: Piep piep!");
    }

    @Override
    public void fliegen() {
        System.out.println(getName() + " fliegt durch die Lüfte.");
    }

    // Optional: Überschreiben der default Methode
    @Override
    public void landeVorgangStarten() {
        System.out.println(getName() + " beginnt mit dem eleganten Landeanflug.");
    }
}


 class AbstraktionDemo {
    public static void main(String[] args) {
        // Tier tier = new Tier("Generisches Tier"); // Fehler! Kann nicht instanziiert werden.

        Tier bello = new Hund("Bello");
        Tier minzi = new Katze("Minzi");
        Vogel tweety = new Vogel("Tweety");

        bello.lautGeben();
        bello.schlafen();

        minzi.lautGeben();
        minzi.schlafen();

        tweety.lautGeben();
        tweety.schlafen();
        tweety.landeVorgangStarten(); // Ruft überschriebene default Methode auf
        tweety.fliegen();

        System.out.println("Maximale Flughöhe laut Interface: " + Fliegfaehig.getMaxFlughoehe() + "m");

        // Polymorphie mit abstrakter Klasse
        Tier[] tiereImZoo = {bello, minzi, tweety};
        for (Tier tier : tiereImZoo) {
            System.out.print(tier.getName() + " macht: ");
            tier.lautGeben(); // Polymorpher Aufruf der spezifischen Implementierung
        }

        // Polymorphie mit Interface
        Fliegfaehig[] flugobjekte = {tweety}; // Hier könnten auch Flugzeuge etc. sein
        for (Fliegfaehig obj : flugobjekte) {
            obj.fliegen();
        }
    }
}
