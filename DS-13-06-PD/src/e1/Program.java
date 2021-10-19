package e1;

public class Program implements EstadoTermostato,Notificar {

    private static Program instancia=null ;
    private  float temperatura;


    public static Program getEstado(float temperatura) {
        return  instancia = new Program(temperatura);
    }

    private Program(float temperatura) {
        this.temperatura=temperatura;
    }

    //funcion que cambia de Program a otro estado
    @Override
    public String cambiarEstado(Termostato termostato,EstadoTermostato estadoTermostato) {
        //condicion que impide hacer el cambio de program timer
        if(estadoTermostato instanceof Timer)
            throw new IllegalArgumentException("no se puede cambiar de Program a Timer");
        //termostato.detach();
        termostato.inicializarTiempo();
       return estadoTermostato.cambiarFuncionamientoTermostato(termostato);
    }

    //funcion que inicializa el modo Manual en el termostato
    @Override
    public String cambiarFuncionamientoTermostato(Termostato termostato) {
        termostato.setEstado(this);
        if(temperatura>termostato.getTemperatura())
            termostato.setEncedido(Encedido.ON);
        else
            termostato.setEncedido(Encedido.OFF);
        termostato.attach(this);
        return "Se activa el modo Program a "+temperatura  +" grados .\n" ;
    }

    //funcion que imprime la informacion del termostato en modo program
    @Override
    public String print(Termostato termostato) {
        if (termostato.getEncedido().equals(Encedido.ON))
            return termostato.getTemperatura()+" Modo Program (a " +this.temperatura+" grados ) - Calefaccion encendida .\n";
        else
            return termostato.getTemperatura()+" Modo Program (a " +this.temperatura+ " grados ) - Calefaccion apagada .\n";

    }


    //funcion que recibira informacion del termostato cuando cambia algun valor
    @Override
    public void notificar(Subject subject) {
        Termostato termostato=(Termostato)subject;
        if(temperatura>termostato.getTemperatura())
            termostato.setEncedido(Encedido.ON);
        else
            termostato.setEncedido(Encedido.OFF);
    }
}
