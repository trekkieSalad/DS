package e4;

public class Semaforo {

    private Colores colores;
    private int tiempo;
    private final Rutas ruta;
    private  boolean valido;

    /**
     * Create a traffic light instance parsing a Route Enum
     * @param rutas The Enum representing the cardinal point of the route
     */
    public Semaforo(Rutas rutas) {
        this.ruta = rutas;
        this.tiempo = 0;
        if (this.ruta == Rutas.NORTE)
            this.colores = Colores.Verde;
        else
            this.colores = Colores.Rojo;
    }

    /**
     * Return the actual color of the traffic light
     * @return A string with the color's name of the traffic light
     */
    public String getColor() {
        return this.colores.getAbreviatura();
    }

    /**
     * Returns the time taken by a traffic light in a color
     * @return A int with the time
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * Returns the route of the traffic light
     * @return The enum of the semaphore route
     */
    public Rutas getRuta() {
        return ruta;
    }

    /**
     * Returns a string with state of the traffic light
     * @return A string with the route, the color and the time of a traffic light in this color
     */
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

    /**
     * Set the state of the signal of the traffic light
     * @param valido A boolean that's set the signal of the traffic light as valid (TRUE) or invalid (FALSE)
     */
    public void setValido(boolean valido) {
        this.valido = valido;
    }

    /**
     * Check if the signal is activated or deactivated
     * @return True if the signal is activated and False if the signal are deactivated
     */
    public boolean isValido(){
        return this.valido;
    }

    /**
     * Set the color of the traffic light
     * @param color An Enum of the actual color of the traffic light
     */
    public void setColores(Colores color) {
        this.colores = color;
        this.tiempo = 0;
    }

    /**
     * This method increases the time the traffic light has been in its current state
     * and checks if it needs to change the color
     * @return True if change from amber to red and False if change from green to amber
     */
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