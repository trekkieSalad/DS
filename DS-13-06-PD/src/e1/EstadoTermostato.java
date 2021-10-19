package e1;

//interzaz que representa las operaciones que pueden hacer los estados de los termostato
public interface EstadoTermostato {

    public String cambiarEstado(Termostato termostato,EstadoTermostato estadoTermostato);
    public  String cambiarFuncionamientoTermostato(Termostato termostato);
    public String print(Termostato termostato);




}
