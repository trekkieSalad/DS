import java.util.*;


public class Juego {

    private final List<Heroes> tropas1;//tropa de heroe
    private final List<Bestias> tropas2;//tropa de bestias
    private final Truncado dado=new Truncado();
    private final boolean truncado;//variable que nos indica si  se quiere usar el dado truncado
    public Juego(boolean truncado) {
        tropas1 = new ArrayList<>();
        tropas2 = new ArrayList<>();
        this.truncado=truncado;
    }
    //funcion que añade un personaje a la lista correspondiente
    public void anadirPersonaje(String personaje, String nombre, int vida, int resistencia) {
        //impridir que se añadan personajes con atributos erroneos
        if (vida <= 0 || resistencia < 0)
            throw new IllegalArgumentException("resistencia o datos no validos");

        if(personaje.equals("elfo"))
            tropas1.add(new Elfos(nombre, vida, resistencia));
        else if(personaje.equals("hobbit"))
        tropas1.add(new Hobbits(nombre, vida, resistencia));
        else if (personaje.equals("humano"))
            tropas1.add(new Humanos(nombre, vida, resistencia));
        else if(personaje.equals("orco"))
            tropas2.add(new Orcos(nombre, vida, resistencia));
        else if(personaje.equals("trasgos"))
            tropas2.add(new Trasgos(nombre, vida, resistencia));
        else
           throw new IllegalArgumentException("tipo no existente");

    }

    //funcion que elemina un personaje de la lista seleccionada
    private String eleminarPersonaje(List<? extends Personaje> lista, Personaje personaje) {
        //string que dice que personaje fue eleminado
        String persona = personaje.toString() + " " + personaje.getNombre() + " dies!!\n";
        //despaza todos los compañeros que estaban en posiciones posteriores una posicion para ocupar su lugar
        //la funcion remove de list ya cubre el despalzacimiento
        lista.remove(personaje);
        return persona;
    }
    //funcion que muestra el comportamiento de la batalla
    public String batalla(int semilla) {
        StringBuilder stringbuilder = new StringBuilder();
        int ronda = 0;
        //se sale del bucle cuando uno de los dos ejercitos no tiene mas soldados mostrando quien es el ganador
        while (!tropas2.isEmpty() && !tropas1.isEmpty()) {
            stringbuilder.append("TURN").append(++ronda).append("\n");
            //se recorre la lista de cada tropa y haciendo que luchen entre si los dolsdados que estean en la misma
            //posicion ademas coguiendo el minimo de cada tropa para impidir que las batallas sean injustas
            for (int i = 0; i < Math.min(tropas2.size(), tropas1.size()); i++) {
                //se devuel quien combate en cada ronda y si fue eleminado algún personaje en esta ronda
                stringbuilder.append(ataque(tropas1.get(i), tropas2.get(i), semilla));
            }
        }
        //caso de empate
        if (tropas1.isEmpty() && tropas2.isEmpty())
            stringbuilder.append("EMPATE\n");
        else if (tropas1.isEmpty()) {
            stringbuilder.append("WIN Bestias\n");
        } else {
            stringbuilder.append("WIN Heroes\n");
        }
        return stringbuilder.toString();
    }


    //funcion que calcula el daño de dos tropas y si se tienen que eleminar de la lista
    private String ataque(Personaje heroe, Personaje bestia, int semilla) {
        //situacion en la que ataca heroe
        StringBuilder stringBuilder = new StringBuilder();
        int dado1, dado2;

        String formato = String.format("Fight between %s ( Energy =%d) and %s ( Energy =%d)\n", heroe.getNombre(),
                heroe.getPuntosdevida(), bestia.getNombre(), bestia.getPuntosdevida());
        stringBuilder.append(formato);
        //calcula el valor de los dados
        if(truncado){
            dado1= dado.truncado(semilla);
            dado2= dado.truncado(semilla);
        }
        else {
            dado1 = dado.pasarDadoHeroe();
            dado2 = dado.pasarDadoBestias();
        }
        heroe.ataque(dado1,bestia);
        bestia.ataque(dado2, heroe);
        //eleminacion de un heroe si su vida es menor que cero ó igual a cero
        if (heroe.getPuntosdevida() <= 0)
            stringBuilder.append(eleminarPersonaje(tropas1, heroe));
        //eleminacion de una bestia  si su vida es menor que cero ó igual a cero
        if (bestia.getPuntosdevida() <= 0)
            stringBuilder.append(eleminarPersonaje(tropas2, bestia));

        return stringBuilder.toString();
    }
}


