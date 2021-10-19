package e1;

public class Off implements EstadoTermostato {

    private  static Off instancia=null ;
    //funcion que devuelve la instancia del estado Off
    public static Off getEstado() {
        if(instancia==null) {
            instancia = new Off();
        }
        return instancia; }


    //funcion que cambia el OFF del termostato a otro estado
    @Override
    public String cambiarEstado(Termostato termostato,EstadoTermostato estadoTermostato) {
        termostato.inicializarTiempo();
        return estadoTermostato.cambiarFuncionamientoTermostato(termostato);
    }
    //funcion que inicializa el modo Off en el termostato
    @Override
    public String cambiarFuncionamientoTermostato(Termostato termostato) {
        termostato.setEncedido(Encedido.OFF);
        termostato.setEstado(this);
        return "Se activa el modo Off .\n";
    }


    //funcion que imprime la informacion del termostato en modo OFF
    @Override
    public String print(Termostato termostato) {
        return termostato.getTemperatura()+" Modo Off - Calefaccion apagada .\n";
    }


}
