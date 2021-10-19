public class Elfos  extends Heroes {

    public Elfos(String nombre, int puntosdevida, int resistencia) {
        super(nombre, puntosdevida, resistencia);
    }
    @Override
    public void ataque(int ataque1, Personaje bestias){
        if(bestias.toString().equals("Orcos")){//caso que el oponete sea un orco se le suma 10 al ataque
            bestias.puntosDeVidaRestantes(ataque1+10);
        }
        else
            bestias.puntosDeVidaRestantes(ataque1);

    }
    @Override
    public String toString() {
        return "Elfos";
    }

}
