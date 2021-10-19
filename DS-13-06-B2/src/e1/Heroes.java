public abstract class Heroes extends Personaje {

    public Heroes(String nombre, int puntosdevida, int resistencia) {
        super(nombre, puntosdevida, resistencia);
    }
    @Override
    public abstract String toString() ;
}
