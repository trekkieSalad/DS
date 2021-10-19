public abstract class Personaje    {
    private final String nombre;
    private int puntosdevida;
    private  int resistencia;

    public Personaje(String nombre,int puntosdevida,int resistencia) {
        this.nombre = nombre;
        this.puntosdevida=puntosdevida;
        this.resistencia=resistencia;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPuntosdevida() {
        return puntosdevida;
    }
    public int getResistencia() {
        return resistencia;
    }
    public void setPuntosdevida(int puntosdevida){
        this.puntosdevida=puntosdevida;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void puntosDeVidaRestantes(int ataque) {
        int vida;
        vida=this.getResistencia()-ataque;
        if(vida<=0){
            //se pone un se suma porque si se llega a este caso vida es negativa
            this.setPuntosdevida(this.getPuntosdevida()+vida);
        }
    }
    public  void ataque(int ataque,Personaje personaje){
        personaje.puntosDeVidaRestantes(ataque);
    }


}
