package e4;

public enum Rutas {
    NORTE("NORTH"),
    SUR("SOUTH"),
    ESTE("EAST"),
    OESTE("WEST");
    private final String abreviatura;

    /**
     *
     * @param abreviatura The string with the name of the Cardinal Point
     */
    Rutas(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * Returns the name of the cardinal point
     * @return A string with the name of the cardinal point
     */
    public String getAbreviatura() {
        return abreviatura;
    }
}
