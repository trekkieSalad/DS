import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuegoTest {

    @Test
    void ataques() {
        Juego errores=new Juego(false);
        //comprobacion de teses que se producen errores cuando los datos no son validos
        assertThrows(IllegalArgumentException.class, () -> errores.anadirPersonaje("pdro", "orcor2", 9, 10));
        assertThrows(IllegalArgumentException.class, () -> errores.anadirPersonaje("orco", "orcor2", -9, 10));
        assertThrows(IllegalArgumentException.class, () -> errores.anadirPersonaje("orco", "orcor2", 9, -5));

        Juego juego = new Juego(true);
        Juego juego1 = new Juego(true);
        Juego juego2 = new Juego(true);
        Juego juego3 = new Juego(false);


        //comprobacion de de un empate entre dos criaturas
        juego.anadirPersonaje("elfo", "elfo1", 20, 5);//el ataque es de 10 con una semilla alectoria de 20
        //por tanto en cada turno se le resta la vida de cada personaje 10 ataque-5 resitencia=5 puntod de vida
        juego.anadirPersonaje("trasgos", "trasgo", 20, 5);
        assertEquals("TURN1\nFight between elfo1 ( Energy =20) and trasgo ( Energy =20)\n"+"TURN2\n"+
                "Fight between elfo1 ( Energy =15) and trasgo ( Energy =15)\n" + "TURN3\n" +
                "Fight between elfo1 ( Energy =10) and trasgo ( Energy =10)\n" + "TURN4\n" +
                "Fight between elfo1 ( Energy =5) and trasgo ( Energy =5)\n" +"Elfos elfo1 dies!!\n" +"Trasgos trasgo dies!!\n"+
                "EMPATE\n", juego.batalla( 20));

        //comprobar que el ataque de los elfos aumenta en 10 unidades cuando su adversario es un orco tambien comprobar que la
        //armadura del arbersario el orco disminuye un 10% cuando ataque el orco

        //el ataque del elfo sería de 20 unidades y su defensa sería de 9 al disminuir en un 10%
        juego.anadirPersonaje("elfo", "elfo1", 20, 10);
        juego.anadirPersonaje("orco", "orco1", 20, 10);
        assertEquals("TURN1\nFight between elfo1 ( Energy =20) and orco1 ( Energy =20)\n"+"TURN2\n"+
                "Fight between elfo1 ( Energy =19) and orco1 ( Energy =10)\n"+"Orcos orco1 dies!!\n"+"WIN Heroes\n",
                juego.batalla(20));

        //comprobar que el ataque de hobbit disminuye en 5 unidades cuando el adversario es un trasgo

        //el traso no pierde vida por que ataque del hobbit despues de disminuir su ataque es de 5 igual a su armadura
        juego1.anadirPersonaje("trasgos", "trasgo", 20, 5);
        juego1.anadirPersonaje("hobbit", "hibbit", 20, 5);
        assertEquals("TURN1\nFight between hibbit ( Energy =20) and trasgo ( Energy =20)\n"+"TURN2\n"+
                "Fight between hibbit ( Energy =15) and trasgo ( Energy =20)\n" + "TURN3\n" +
                "Fight between hibbit ( Energy =10) and trasgo ( Energy =20)\n" + "TURN4\n" +
                "Fight between hibbit ( Energy =5) and trasgo ( Energy =20)\n" +"Hobbits hibbit dies!!\n" +
                "WIN Bestias\n", juego1.batalla(20));



        //comprobar el funcionamiento  que las tropas tenga mas de un personaje y el mismo numero de personajes
        //que se descarta un soldado cunado no hay soldados suficiente
        juego2.anadirPersonaje("trasgos", "trasgo", 10, 5);
        juego2.anadirPersonaje("humano", "humano1", 10, 5);
        juego2.anadirPersonaje("trasgos", "trasgo1", 10, 5);
        juego2.anadirPersonaje("hobbit", "bbit1", 10, 5);
        juego2.anadirPersonaje("orco", "orcor2", 10, 5);
        juego2.anadirPersonaje("elfo", "elfo1", 10, 5);
        assertEquals("TURN1\n"+"Fight between humano1 ( Energy =10) and trasgo ( Energy =10)\n"+
                        "Fight between bbit1 ( Energy =10) and trasgo1 ( Energy =10)\n"+
                        "Fight between elfo1 ( Energy =10) and orcor2 ( Energy =10)\n"+ "Orcos orcor2 dies!!\n"+
                            "TURN2\n"+
                        "Fight between humano1 ( Energy =5) and trasgo ( Energy =5)\n"
                        +"Humanos humano1 dies!!\n"+"Trasgos trasgo dies!!\n"+
                        "TURN3\n"+
                "Fight between bbit1 ( Energy =5) and trasgo1 ( Energy =10)\n"+"Hobbits bbit1 dies!!\n"+
                "TURN4\n"+
                "Fight between elfo1 ( Energy =5) and trasgo1 ( Energy =10)\n"+"Elfos elfo1 dies!!\n"
                +"WIN Bestias\n",juego2.batalla(20));

        //comprobacion lucha alectoria tienen que dar resultados difrentes
        juego3.anadirPersonaje("trasgos", "trasgo", 10, 5);
        juego3.anadirPersonaje("humano", "humano1", 10, 5);
        juego3.anadirPersonaje("orco", "orco", 10, 5);
        juego3.anadirPersonaje("hobbit", "bbit1", 10, 92);
        juego3.anadirPersonaje("orco", "orcor2", 10, 5);
        juego3.anadirPersonaje("elfo", "elfo1", 10, 5);

        assertNotEquals(juego3.batalla(0),juego3.batalla(0));




    }
}