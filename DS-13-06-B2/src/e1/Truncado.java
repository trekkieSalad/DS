import java.util.Random;

public class Truncado extends Dado {

    //dado truncado
    public int truncado(int semillas){
        Random semilla = new Random(semillas);
        return  semilla.nextInt(91);
    }
}
