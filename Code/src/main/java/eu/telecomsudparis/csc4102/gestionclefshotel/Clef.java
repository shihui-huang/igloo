package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

import java.util.Arrays;

public class Clef {
    /**
     * la clef.
     */
    private byte[] value;


    /**
     * Instantiates a new Clef.
     *
     * @param value the value
     * @throws ProblemeDansGenerationClef the probleme dans generation clef
     */
    public Clef(final byte[] value) {
        this.value = value.clone();
    }

    /**
     * Instantiates a new Clef.
     */
    public Clef() {
        this.value = new byte[Util.TAILLE_CLEF];
    }

    public Clef(final Clef clef) {
        this.value = clef.value.clone();
    }
    /**
     * Get clef byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getValue() {
        return value.clone();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clef clef = (Clef) o;
        return Arrays.equals(value, clef.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override
    public String toString() {
        return Util.clefToString(value);
    }
}