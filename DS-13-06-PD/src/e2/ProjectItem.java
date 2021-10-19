package e2;

import java.util.*;

public abstract class ProjectItem {

    private String name;
    private List<Project> projects = new ArrayList<>();


    public ProjectItem(String name){
        this.name = name;
    }

    //devuelve el nombre de un trabajador o equipo
    public String getName() {
        return name;
    }

    //devuelve los proyectos en los que participa un trabajador o equipo
    public List<Project> getProjects() {
        return projects;
    }

    //añade un proyecto a un trabajador o equipo
    public void addProject(Project project){
        if (projects.contains(project))
            throw new IllegalArgumentException(this.name + " ya está participando en el proyecto " + project.getName());
        projects.add(project);
    }

    //devuelve la lista de cotrabajadores
    public List<ProjectItem> coWorkers(Project project){
        if (!projects.contains(project))
            throw new IllegalArgumentException("el tabajador o equipo no participa en este proyecto");
        return project.getMembers().workers();
    }

    public abstract List<ProjectItem> workers();
    public abstract float getHours();
    public abstract float cost();
    public abstract String printMembers();

}
