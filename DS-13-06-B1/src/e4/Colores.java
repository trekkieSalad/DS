package e4;

public enum Colores {
    Rojo("RED"),
    Verde("GREEN"),
    Ambar("AMBER");

    private final String abreviatura;

    /**
     *
     * @param abreviatura The string with the name of the color
     */
    Colores(String abreviatura) {
        this.abreviatura = abreviatura;

    }

    /**
     * Returns the name of the color that's the enum represent
     * @return A String with the color's name
     */
    public String getAbreviatura() {
        return abreviatura;
    }
}