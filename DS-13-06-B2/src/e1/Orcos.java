public class Orcos extends Bestias{

    public Orcos(String nombre, int puntosdevida, int resistencia) {
        super(nombre, puntosdevida, resistencia);
    }

    @Override
    public  void ataque(int ataque,Personaje heroes){
        int actual_armadura= (int) (heroes.getResistencia()*0.1);
        //se le resta un 10% el nivel de armadura al contricante y al terminar de atacar le restaura el nivel que
        // tenía al princio
        heroes.setResistencia((heroes.getResistencia()-actual_armadura));
        heroes.puntosDeVidaRestantes(ataque);
        heroes.setResistencia(heroes.getResistencia()+actual_armadura);
    }

    @Override
    public String toString() {
        return "Orcos";
    }
}
