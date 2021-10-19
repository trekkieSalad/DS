package e2;

import java.util.*;

public class Team extends ProjectItem{

    private List<ProjectItem> members = new ArrayList<>();

    public Team(String name){
        super(name);
    }

    public List<ProjectItem> getMembers() {
        return members;
    }

    //inserta un trabajador o equipo en un equipo
    public void insertMember(ProjectItem member){
        if (members.contains(member))
            throw new IllegalArgumentException("el trabajador ya forma parte del equipo");
        members.add(member);
        for (Project project : super.getProjects()){
            if(!member.getProjects().contains(project))
                member.addProject(project);
        }
    }

    //devuelve las horas trabajadas por un equipo
    public float getHours(){
        float hours = 0;
        for (ProjectItem member : members){
            hours += member.getHours();
        }
        return hours;
    }

    //devuelve el coste de un equipo
    public float cost(){
        float money = 0;
        for (ProjectItem member : members){
            money += member.cost();
        }
        return money;
    }

    //devuelve una lista con los trabajadores de un equipo
    public List<ProjectItem> workers(){
        List<ProjectItem> workers= new ArrayList<>();
        for (ProjectItem member : members){
            workers.addAll(member.workers());
        }
        return workers;
    }

    //añade un proyecto a un equipo y a sus integrantes
    public void addProject(Project project){
        super.addProject(project);
        for (ProjectItem member : members){
            member.addProject(project);
        }
    }

    //devuelve el equipo y sus integrantes como un string
    @Override
    public String printMembers() {
        String team = "Team " + super.getName() + ": " + getHours() +
                        " hours, " + cost() + " €";
        for (ProjectItem member : members) {
            team = team.concat("\n\t" + member.printMembers());
        }
        return team;
    }
}
