package e2;

import java.util.*;

public class Worker extends ProjectItem implements Employee{

    private final int eurosHour;
    private float hours=0;

    public Worker(String name, int eurosHour){
        super(name);
        this.eurosHour=eurosHour;
    }

    //devuelve las horas trabajadas por un trabajaador
    public float getHours() {
        return hours;
    }

    public int getEurosHour() {
        return eurosHour;
    }

    //establece las horas trabajadas por un trabajador
    public void addHours(int hours) {
        if (hours < 0)
            throw new IllegalArgumentException("El numero de horas no puede ser negativo");
        this.hours += hours;
    }

    //devuelve el coste de un trabajador
    public float cost(){
        return hours * eurosHour;
    }

    //devuelve el trabajador en formato de lista
    public List<ProjectItem> workers(){
        List<ProjectItem> worker = new ArrayList<>();
        worker.add(this);
        return worker;
    }

    //devuelve un string con la informacion de un trabajador
    @Override
    public String printMembers() {
        return "\tWorker " + super.getName() + ": " + hours + " hours, "
                + cost() + " €";
    }

}
