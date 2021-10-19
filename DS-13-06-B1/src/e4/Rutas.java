package e4;

public enum Rutas {
    NORTE("NORTH"),
    SUR("SOUTH"),
    ESTE("EAST"),
    OESTE("WEST");
    private final String abreviatura;

    Rutas(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    public String getAbreviatura() {
        return abreviatura;
    }
}
