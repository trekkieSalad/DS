package e4;

public enum Colores {
    Rojo("RED"),
    Verde("GREEN"),
    Ambar("AMBER");

    private final String abreviatura;

    Colores(String abreviatura) {
        this.abreviatura = abreviatura;

    }
    public String getAbreviatura() {
        return abreviatura;
    }
}