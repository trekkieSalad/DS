import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GunfightTest {
    @Test
    void pistolero() {
        Gunslinger gunslinger1 = new Gunslinger(0);
        Gunslinger gunslinger2 = new Gunslinger(2);
        Gunslinger error1 = new Gunslinger(1);


        //comprobacio que se produce un error al intertar disparar sin cargas
        assertThrows(IllegalArgumentException.class, error1::action);
        //comprobacion que se recarga despuesde hacer un loads
        assertEquals(gunslinger1.getLoads(), 0);
        gunslinger1.action();
        assertEquals(gunslinger1.getLoads(), 1);

        //comprobar que despues de un disparo se disminuye las cargas
        gunslinger1.action();
        assertEquals(gunslinger1.getLoads(), 0);

        //comprobacion del funcionamiento de MachineGun
        assertEquals(gunslinger2.getLoads(), 0);
        gunslinger2.action();
        assertEquals(gunslinger2.getLoads(), 1);
        gunslinger2.action();
        assertEquals(gunslinger2.getLoads(), 2);
        gunslinger2.action();
        assertEquals(gunslinger2.getLoads(), 3);
        gunslinger2.action();
        assertEquals(gunslinger2.getLoads(), 4);
        gunslinger2.action();
        assertEquals(gunslinger2.getLoads(), 5);
        gunslinger2.action();
        assertEquals(gunslinger2.getLoads(), 0);

        //comprobacion de que las acciones del rival se guardan en una lista
        Gunslinger acciones1 = new Gunslinger();
        List<GunslingerAction> listaprueba = new ArrayList<>();
        GunslingerAction aux2;
        for (int i = 0; i < 7; i++) {
            aux2 = acciones1.action();
            listaprueba.add(aux2);
            gunslinger1.rivalAction(aux2);
            assertEquals(gunslinger1.getRivalActions(), listaprueba);
        }
    }
        @Test
        void duel() {

            Gunfight gunfight = new Gunfight();

//salida de pantalla de duel repetido 5 veces

            for (int i = 0; i < 5; i++) {
                Gunslinger gunslinger3 = new Gunslinger();
                Gunslinger gunslinger4 = new Gunslinger();
                gunfight.duel(gunslinger3, gunslinger4);
            }
        }


    }
