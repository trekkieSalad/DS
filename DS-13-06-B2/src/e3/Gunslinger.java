import java.util.ArrayList;
import java.util.List;

public class Gunslinger extends GunslingerBehavior {
    private int loads;
    private Behavior comportamiento;
    int comportamiento_predefinido;
    private final List<GunslingerAction> rival;
    //constructor de un gunliger sin comportamiento predefinido
    public Gunslinger(){
        this.comportamiento=null;
        rival = new ArrayList<>();
        loads = 0;
        this.comportamiento_predefinido=3;
    }
    //constructor de un gunlinger con comportamiento predefinido
    public Gunslinger(int  comportamiento_predefinido){
       this.comportamiento_predefinido=comportamiento_predefinido;
        rival = new ArrayList<>();
        loads = 0;
    }

    public GunslingerAction action(){
        this.setBehavior(this);
        GunslingerAction action = comportamiento.action(this);
        if (action == GunslingerAction.RELOAD) loads++;
        else if(action==GunslingerAction.SHOOT&&loads<1)
            throw new IllegalArgumentException("no es posible accer el disparo");
        else if(action==GunslingerAction.SHOOT) loads--;
        else if(action==GunslingerAction.MACHINE_GUN&&loads<5)
            throw new IllegalArgumentException("no es posible accer el MachineGun");
        else if(action==GunslingerAction.MACHINE_GUN) loads-=5;
        return action;
    }

    public int getLoads(){
        return loads;
    }

    public void rivalAction(GunslingerAction action){
        rival.add(action);
    }

    public List<GunslingerAction> getRivalActions(){
        return rival;
    }

    public int getRivalLoads(){
        int loads = 0, shoots = 0;
        for (GunslingerAction x : rival){
            if (x == GunslingerAction.RELOAD)
                loads++;
            if (x == GunslingerAction.SHOOT)
                shoots++;
        }
        return loads - shoots;
    }

    public void setBehavior(Behavior behavior){
        comportamiento = behavior;
    }
}
