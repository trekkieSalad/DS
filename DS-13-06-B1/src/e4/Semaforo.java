package e4;

public class Semaforo {
    private Colores colores;
    private int tiempo;
    private final Rutas ruta;
    private  boolean valido;
    //construcion de cada semaforo
    public Semaforo(Rutas rutas) {
        this.ruta = rutas;
        this.tiempo = 0;
        if (this.ruta == Rutas.NORTE)
            this.colores = Colores.Verde;
        else
            this.colores = Colores.Rojo;
    }
    //funcion que devuelve el color de cada semaforo
    public String getColor() {
        return this.colores.getAbreviatura();
    }
    //funcion que devuel el tiempo transcurrido en el color actual
    public int getTiempo() {
        return tiempo;
    }
    //funcion que devuel en el semaforo que estamos utilizando
    public Rutas getRuta() {
        return ruta;
    }

    @Override
    public String toString() {
        //los formatos correspondiente con el tipo dependiendo del color que tenga el semaforo
        String patron1 = "[%s: %s %d]";
        String patron2 = "[%s: %s ON]";
        String patron3 = "[%s: %s OFF %d]";
        String patron4 = "[%s: %s]";
        //caso que el semaforo tiene el color verde
        if (this.getColor().equals("GREEN"))
            return String.format(patron1, this.ruta.getAbreviatura(), this.getColor(), this.getTiempo());
            //caso que el semaforo tiene el color ambar
        else if (this.getColor().equals("AMBER")) {
            //comprueba si se activo la señal de ambar
            if (this.isValido())
                return String.format(patron2, this.ruta.getAbreviatura(), this.getColor());
                //caso que el semaforo tiene el color rojo
            else
                return String.format(patron3, this.ruta.getAbreviatura(), this.getColor(), this.getTiempo());
        } else
            return String.format(patron4, this.ruta.getAbreviatura(), this.getColor());
    }
    //asinacion de control del color ambar
    public void setValido(boolean valido) {
        this.valido = valido;
    }
    //devuelve si la señal de control esta activa
    public boolean isValido(){
        return this.valido;
    }
    //set que hace el cambio de color del semaforo inicializando el color
    public void setColores(Colores colores) {
        this.colores = colores;
        this.tiempo = 0;
    }

    public boolean cambio() {
        //se incrementa el tiempo del color que esta actualmente el semaforo y comprueba si tiene que hacer el cambio de
        //color
        this.tiempo++;
        if (this.tiempo > 15 && this.colores.getAbreviatura().equals("GREEN")) {
            setColores(Colores.Ambar);
            return false;
        }
        if (this.tiempo > 5 && this.colores.getAbreviatura().equals("AMBER") && !this.isValido()) {
            setColores(Colores.Rojo);
            return true;
        }
        return false;
    }




}