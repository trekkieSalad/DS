package e4;


public class TrafficJunction {
    //se crea un array con el semaforo que contradaran los cuatro tipos de semaforos norte sur este y oeste con
    // sus caracteristicas
    private final Semaforo[] semaforos={ null, null,null,null};
    //se crea un array con el enun rutas para uilizar posteriormente el values de rutas
    private Rutas[] ruta;
    /**
     * Creates a trafic junction with four traffic lights named north , south ,
     * east and west . The north traffic light has just started its green cycle .
     */

    public TrafficJunction() {
        this.ruta = Rutas.values();
        //se va creando los  semaforos norte sur este oeste con sus cualidades inicializada
        for (int i = 0; i < ruta.length; i++) {
            this.semaforos[i]=new Semaforo(ruta[i]);
        }
    }
    /**
     * Indicates that a second of time has passed , so the traffic light with
     * the green or amber light should add 1 to its counter . If the counter
     * passes its maximum value the color of the traffic light must change .
     * If it changes to red then the following traffic light changes to green .
     * The order is: north , south , east , west and then again north .
     */
    public void timesGoesBy () {

        boolean se_producio_cambio=false;
        for (int i = 0; i <semaforos.length ; i++) {
            if(!se_producio_cambio) {
                if (this.semaforos[i].cambio()) {
                    if (i == 3)
                        this.semaforos[0].setColores(Colores.Verde);
                    this.semaforos[i + 1].setColores(Colores.Verde);
                    se_producio_cambio = true;
                }
            }
        }
    }

    /**
     * If active is true all the traffic lights of the junction must change to
     * blinking amber ( meaning a non - controlled junction ).
     * If active is false it resets the traffic lights cycle and started again
     * with north at green and the rest at red.
     * @param active true or false
     */
    public void amberJunction ( boolean active ) {
        if (active) {
            for (Semaforo semaforo : semaforos) {
                semaforo.setColores(Colores.Ambar);
                semaforo.setValido(true);
            }
        }
        else{
            this.ruta = Rutas.values();
            for (int i = 0; i < ruta.length; i++) {
                this.semaforos[i]=new Semaforo(ruta[i]);
            }
        }
    }
    /**
     * Returns a String with the state of the traffic lights .
     * The format for each traffic light is the following :
     * - For red colors : "[ name : RED ]"
     * - For green colors : "[ name : GREEN counter ]"
     * - For yellow colors with blink at OFF : "[ name : YELLOW OFF counter ]
     * - For yellow colors with blink at ON: "[ name : YELLOW ON]
     * Examples :
     * [ NORTH : GREEN 2][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER OFF 5][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER ON ][ SOUTH : AMBER ON ][ EAST : AMBER ON ][ WEST : AMBER ON]
     * @return String that represents the state of the traffic lights
     */

    @Override
    public String toString () {
        String string="" ;
        for (Semaforo semaforo : semaforos) {
            string = string.concat(semaforo.toString());
        }
        return string;
    }

}

