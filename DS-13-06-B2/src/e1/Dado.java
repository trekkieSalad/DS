import java.util.Random;

public class Dado  {
    //funcion que calcula el dado que tiene que pasar de si es truncado si esto ocrre se le pasa la semilla al rando en
//caso contrario se hace le alectorio para 100 numeros si es heroe en caso contrario para 90 numeros
    Truncado truncado;
    public Dado() {
    }
    //se lanzan dos veces el dado y se cogue el mayor de dos
        public int pasarDadoHeroe() {
            Random rando = new Random();
            return Math.max(rando.nextInt(101),rando.nextInt(101));
        }
        //dado para bestias
        public int pasarDadoBestias(){
            Random random = new Random();
            return random.nextInt(91);
        }
}

