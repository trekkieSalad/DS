public class Hobbits extends Heroes{
    public Hobbits(String nombre, int puntosdevida, int resistencia) {
        super(nombre, puntosdevida, resistencia);
    }

    public void ataque(int ataque, Personaje bestias) {
        if(bestias.toString().equals("Trasgos")){//caso que el oponete sea un trasgo se le resta 5 al ataque
        bestias.puntosDeVidaRestantes(ataque-5);
        }
        else
            bestias.puntosDeVidaRestantes(ataque);
    }


    @Override
    public String toString() {
        return "Hobbits";
    }
}
