package e1;

public class Termostato extends Subject {

    //private EstadoTermostato estado ;
    private EstadoTermostato estado;
    private float temperatura;
    private int tiempo;
    private Encedido encedido;
    private final StringBuilder eventos=new StringBuilder();

    //creacion de un termostato inicializado en la clase termostato
    public Termostato() {
        this.estado=Off.getEstado();
    }

    //funcion que devuelve el tiempo transcurrido en un estado concreto
    public int getTiempo() {
        return tiempo;
    }

    //funcion que devuelve si el termostato esta encencendido ON  o apagado OFF
    public Encedido getEncedido() {
        return encedido;
    }
    //fucion que inicializa a cero el tiempo que lleva en un estado el termostato para empezar a contar el tiempo
    //que lleva en el siguiente estado
    public void inicializarTiempo() {
        this.tiempo = 0;
    }

    //funcion que devuelve el la temperatura que reguistra actualmente el termostato
    public float getTemperatura() {
        return temperatura;
    }

    //funcion que cambia a encendido a apagado po viceversa el termostato
    public void setEncedido(Encedido encedido) {
        this.encedido = encedido;
    }
    //funcion que establece el nuevo estado del termostato
    public void setEstado(EstadoTermostato estadoTermostato){this.estado=estadoTermostato;}

    //funcion que recibe los cambios de temperatura y tiempo que informara a las clases Timer y Program si
    //las condiciones de tiempo ó de temperatura se cumplen para cambiar el modo del termostato
    public void newTemperature(float currentTemperature) {
        this.temperatura = currentTemperature;
        this.tiempo+=5;
       setNotificars();
    }

    //funcion que cambia a Off el termostato
    public void cambiaOff(){
     notificarEvento(estado.cambiarEstado(this,Off.getEstado()));
      //eventos.append("Se activa el modo Off .\n");

    }
    //funcion que cambia a Manual el termostato
    public void cambiarManual(){
       notificarEvento(estado.cambiarEstado(this,Manual.getEstado()));
       // eventos.append("Se activa el modo Manual .\n");
    }
    //funcion que cambia a Program el termostato
    public void cambiarProgram(float temperatura){
        //Program.getEstado(5).setTemperatura(6);
        notificarEvento(estado.cambiarEstado(this,Program.getEstado(temperatura)));
        //eventos.append("Se activa el modo Program a ").append(temperatura).append(" grados .\n");
    }
    //funcion que cambia a Timer el termostato
    public void cambiarTimer(int tiempo){
        notificarEvento(estado.cambiarEstado(this,Timer.getEstado(tiempo)));
        //eventos.append("Se activa el modo Timer ").append(tiempo).append(" minutos .\n");
    }

    //funcion que devuelve los eventos transcurridos
    public String eventos(){
        return eventos.toString();
    }

    //funcion que guarda el evento que se ha producido
    public void notificarEvento(String evento){
        eventos.append(evento);
    }

    //funcion que muestra informacion  del termostato en el momento que es llamado
    public String screenInfo(){
        return estado.print(this);
    }



}