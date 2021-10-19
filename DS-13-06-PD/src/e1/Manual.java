package e1;

public class Manual implements EstadoTermostato{
    private static  Manual instancia=null ;

    private Manual() {
    }

    //funcion que devuelve la instancia del estado manual
    public static Manual getEstado() {
        if(instancia==null) {
            instancia = new Manual();
        }
        return instancia; }


    //funcion que cambia del modo manual a otro estado
    @Override
    public String cambiarEstado(Termostato termostato,EstadoTermostato estadoTermostato){
        termostato.inicializarTiempo();
        return estadoTermostato.cambiarFuncionamientoTermostato(termostato);
    }

    //funcion que inicializa el modo Manual en el termostato
    @Override
    public String cambiarFuncionamientoTermostato(Termostato termostato) {
        termostato.setEncedido(Encedido.ON);
        termostato.setEstado(this);
        return "Se activa el modo Manual .\n";
    }

    //funcion que imprime la informacion del termostato en modo Manual
    @Override
    public String print(Termostato termostato) {
        return termostato.getTemperatura()+" Modo Manual - Calefaccion encendida .\n";
    }

}
