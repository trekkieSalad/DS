import e2.Project;
import e2.ProjectItem;
import e2.Team;
import e2.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    @Test
    void createWorkers(){
        var worker1 = new Worker("paco", 10);
        var worker2 = new Worker("daniel", 11);
        var worker3 = new Worker("andres", 9);

        var team1 = new Team("team1");
        var team2 = new Team("team2");
        var superteam = new Team("dreamteam");

        assertEquals("paco",worker1.getName());
        assertEquals("daniel",worker2.getName());
        assertEquals("andres",worker3.getName());
        assertEquals(10,worker1.getEurosHour());
        assertEquals(11,worker2.getEurosHour());
        assertEquals(9,worker3.getEurosHour());

        assertEquals(0,worker1.getHours());
        worker1.addHours(5);
        assertEquals(5, worker1.getHours());

        assertEquals(0,worker3.getHours());
        worker3.addHours(6);
        assertEquals(6, worker3.getHours());

        assertThrows(IllegalArgumentException.class, () -> worker1.addHours(-5));
        assertThrows(IllegalArgumentException.class, () -> worker1.addHours(-3));

        assertEquals(50, worker1.cost());
        assertEquals(0,worker2.cost());
        assertEquals(54,worker3.cost());

        List<ProjectItem> workerlist = new ArrayList<>();
        workerlist.add(worker3);
        assertEquals(workerlist, worker3.workers());
        assertNotEquals(workerlist,worker2.workers());

        assertEquals(worker1.printMembers(), "\tWorker paco: 5.0 hours, 50.0 €");
        assertEquals(worker2.printMembers(), "\tWorker daniel: 0.0 hours, 0.0 €");
        assertEquals(worker3.printMembers(), "\tWorker andres: 6.0 hours, 54.0 €");



        team1.insertMember(worker3);
        team2.insertMember(worker1);
        team2.insertMember(worker2);
        superteam.insertMember(team1);
        superteam.insertMember(team2);

        List<ProjectItem> testListMembers= new ArrayList<>();
        testListMembers.add(worker1);
        testListMembers.add(worker2);

        assertThrows(IllegalArgumentException.class, () -> team1.insertMember(worker3));
        assertEquals(testListMembers, team2.getMembers());

        assertEquals(6,team1.getHours());
        assertEquals(11,superteam.getHours());

        assertEquals(54,team1.cost());
        assertEquals(50,team2.cost());
        assertEquals(104,superteam.cost());

        List<ProjectItem> testListWorkers= new ArrayList<>();
        testListWorkers.add(worker3);
        testListWorkers.add(worker1);
        testListWorkers.add(worker2);

        assertEquals(testListWorkers, superteam.workers());
        assertEquals(workerlist, team1.workers());

        assertEquals(team1.printMembers(), "Team team1: 6.0 hours, 54.0 €\n\t" +
                                                "\tWorker andres: 6.0 hours, 54.0 €");
        assertEquals(superteam.printMembers(), "Team dreamteam: 11.0 hours, 104.0 €\n\t" +
                                                "Team team1: 6.0 hours, 54.0 €\n\t"+
                                                "\tWorker andres: 6.0 hours, 54.0 €\n\t"+
                                                "Team team2: 5.0 hours, 50.0 €\n\t"+
                                                "\tWorker paco: 5.0 hours, 50.0 €\n\t" +
                                                "\tWorker daniel: 0.0 hours, 0.0 €");





        var project1 = new Project("proyecto1",team1);
        var superproject = new Project("superproyecto",superteam);

        assertEquals("proyecto1",project1.getName());
        assertEquals("superproyecto",superproject.getName());

        assertEquals(superteam,superproject.getMembers());
        assertEquals(team1, project1.getMembers());

        assertEquals(superproject.members(), "Team dreamteam: 11.0 hours, 104.0 €\n\t" +
                                                    "Team team1: 6.0 hours, 54.0 €\n\t"+
                                                    "\tWorker andres: 6.0 hours, 54.0 €\n\t"+
                                                    "Team team2: 5.0 hours, 50.0 €\n\t"+
                                                    "\tWorker paco: 5.0 hours, 50.0 €\n\t" +
                                                    "\tWorker daniel: 0.0 hours, 0.0 €");

        assertEquals(testListWorkers, worker2.coWorkers(superproject));
        testListWorkers.remove(worker1);
        testListWorkers.remove(worker2);
        assertEquals(testListWorkers, worker3.coWorkers(project1));
        assertThrows(IllegalArgumentException.class, () -> worker1.coWorkers(project1));

        project1.addHours(worker3,3);
        assertEquals(9,worker3.getHours());
        assertThrows(IllegalArgumentException.class, () -> project1.addHours(worker3,-3));
        assertThrows(IllegalArgumentException.class, () -> superproject.addHours(worker3,-3));

        assertThrows(IllegalArgumentException.class, () -> worker3.addProject(project1));

        assertNotEquals(worker1.getProjects(),worker3.getProjects());
        team1.insertMember(worker1);

    }

}