package e1;



//clase abstracta que implemta el interzar que avisara a las clases interesadas de los cambios producidos en el termostato
public abstract class Subject {
    private Notificar notificars;

    //añadir el estado del termosto que quiere recibir informacion del termostato
    public void attach(Notificar notificar){
        notificars=notificar;
    }

    //eleminar una clase que quiere recibir informacion del termostato
    public void  detach(){
        notificars=null; }

    //funcion que decide que clases tienen que recibir la informacion del termostato
    public void setNotificars(){
        if (notificars!=null)
        notificars.notificar(this);
    }

}
