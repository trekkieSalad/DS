public class Gunfight {
    public void duel(Gunslinger g1,Gunslinger g2){
        boolean ganador = false;
        int ronda = 1;
        while (!ganador && ronda < 51){
            GunslingerAction pistolero1, pistolero2;
            pistolero1 = g1.action();
            pistolero2 = g2.action();
            g1.rivalAction(pistolero2);
            g2.rivalAction(pistolero1);
            System.out.println("Round "+ronda+"--------------------");
            System.out.println("Gunslinger 1: "+pistolero1);
            System.out.println("Gunslinger 2: "+pistolero2);

            //caso ganador el primer pistolero
            if ((pistolero1 == GunslingerAction.SHOOT && pistolero2 == GunslingerAction.RELOAD)
                    || (pistolero1 == GunslingerAction.MACHINE_GUN && pistolero2 != GunslingerAction.MACHINE_GUN)){
                ganador = true;

                System.out.println("\nThe duel has ended");
                System.out.println("\nWinner: GUNSLINGER 1");
            }
            //caso ganador segundo pistolero
           else if ((pistolero2 == GunslingerAction.SHOOT && pistolero1 == GunslingerAction.RELOAD)
                    || (pistolero2 == GunslingerAction.MACHINE_GUN && pistolero1 != GunslingerAction.MACHINE_GUN)){
                ganador = true;

                System.out.println("\nThe duel has ended");
                System.out.println("\nWinner: GUNSLINGER 2");
            }
           //caso de empate
           else if (pistolero1 == GunslingerAction.SHOOT && pistolero2 == GunslingerAction.SHOOT
                    || pistolero1 == GunslingerAction.MACHINE_GUN&&pistolero2==GunslingerAction.MACHINE_GUN || ronda == 50){
                ganador = true;

                System.out.println("\nThe duel has ended");
                System.out.println("\nTABLAS");
            }
           //caso en el que se continua la batalla
           else {
                System.out.println("The duel continues ...\n");
            }
            ronda++;
        }
    }
}
