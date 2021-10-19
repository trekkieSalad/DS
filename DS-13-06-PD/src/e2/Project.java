package e2;

import java.util.List;

public class Project {

    private final String name;
    private final ProjectItem members;

    public Project(String name, ProjectItem members){
        this.name=name;
        this.members=members;
        members.addProject(this);
    }

    public String getName() { return name; }

    public ProjectItem getMembers() { return members; }

    public void addHours(Employee employee, int hours){ employee.addHours(hours); }

    public String members(){ return members.printMembers(); }



}
