//si el se construye el gunlinger con comportamiento_predefinido a 1 siempre dispara si se pone a 0 recarga dispar
//si es 2 solo recarga asta llegar a mAachine_gun
public class GunslingerBehavior implements Behavior{
    public GunslingerAction action(Gunslinger g){
        int rando;
            //siempre dispare
            if(g.comportamiento_predefinido==1)
                return GunslingerAction.SHOOT;
            if(g.getLoads()==0&&g.getRivalLoads()==0)
                return GunslingerAction.RELOAD;
            if (g.getLoads() == 5)
                return GunslingerAction.MACHINE_GUN;
            //recarga dispara
            if(g.comportamiento_predefinido==0)
            {
                return GunslingerAction.SHOOT;
            }
            //recarga continuamente asta llegar al machineGUn donde se vacia a cero
            if(g.comportamiento_predefinido==2){
                return GunslingerAction.RELOAD;
            }
            //posibles opciones que se pueden hacer si ya se recargo una vez en modo alectorio
            if(g.getLoads()>0) {
                rando = (int) (Math.random() * 3);
                if (rando == 1)
                    return GunslingerAction.RELOAD;
                else if (rando == 2)
                    return GunslingerAction.SHOOT;

                return GunslingerAction.PROTECT;
            }

            //posbles opciones que se pueden hacer si no tienes cargas en modo alectorio
            rando=(int) (Math.random() * 2);
            if(rando==0)
                return GunslingerAction.RELOAD;
            else
                return GunslingerAction.PROTECT;
        }
    }


