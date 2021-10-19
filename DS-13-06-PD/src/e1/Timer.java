package e1;

public class Timer implements EstadoTermostato,Notificar{
    private static  Timer instancia ;

    private  int tiempo;
    public static Timer getEstado(int tiempo) {
        return instancia = new Timer(tiempo); }

    private Timer(int tiempo) {
        this.tiempo=tiempo;
    }



    //funcion que cambia del modo Timer a otro modo de funcionamiento
    @Override
    public String cambiarEstado(Termostato termostato,EstadoTermostato estadoTermostato) {
        //condicion que impide hacer el cambio de timer a  program
        if(estadoTermostato instanceof Program)
            throw new IllegalArgumentException("no se puede cambiar de Timer a Program");
        //termostato.detach();
        return estadoTermostato.cambiarFuncionamientoTermostato(termostato);
    }
    //funcion que inicializa el modo Manual en el termostato
    @Override
    public String cambiarFuncionamientoTermostato(Termostato termostato) {
        termostato.setEncedido(Encedido.ON);
        termostato.setEstado(this);
        termostato.attach(this);
        termostato.inicializarTiempo();
        return "Se activa el modo Timer "+tiempo+" minutos .\n";

    }

    //funcion que imprime la informacion del termostato en modo program
    @Override
    public String print(Termostato termostato) {

        if (termostato.getEncedido().equals(Encedido.ON))
            return (termostato.getTemperatura()+" Modo Timer ( faltan "
                    +(this.tiempo - termostato.getTiempo())+" minutos ) - Calefaccion encendida .\n");
        else
            return (termostato.getTemperatura()+" Modo Program ( faltan " +(this.tiempo - termostato.getTiempo())+" minutos ) "+
                    "- Calefaccion apagada .\n");
    }

    //funcion que recibira informacion del termostato cuando cambia algun valor
    @Override
    public void notificar(Subject subject) {
        Termostato termostato=(Termostato)subject;
        if(this.tiempo<=termostato.getTiempo()) {
            termostato.notificarEvento("Se desactiva el modo Timer .\n");
            termostato.inicializarTiempo();
            termostato.setEstado(Off.getEstado());
            termostato.detach();
        }

    }
}
