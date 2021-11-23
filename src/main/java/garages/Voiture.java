package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        Stationnement s = new Stationnement(this, g);

        if (!(myStationnements.size() == 0) && myStationnements.get(myStationnements.size() - 1).estEnCours()) {
            throw new IllegalArgumentException("La voiture est déjà dans un garage");
        }

        myStationnements.add(s);
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        if (!myStationnements.get(myStationnements.size() - 1).estEnCours()) {
            throw new IllegalArgumentException("La voiture n'est pas dans un garage");
        }
        myStationnements.get(myStationnements.size() - 1).terminer();
    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        Set<Garage> myGarages = new HashSet<>();
        for (int i = 0; i < myStationnements.size(); i++) {
            myGarages.add(myStationnements.get(i).getGarage());
        }
        System.out.println(myGarages);
        return myGarages;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        if (myStationnements.size() == 0) {
            return false;
        } else {
            return (myStationnements.get(myStationnements.size() - 1).estEnCours());
        }
    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    public void imprimeStationnements(PrintStream out) {
        for (Garage g : this.garagesVisites()) {
            out.println(g.toString() + ":");
            for (Stationnement s : this.myStationnements) {
                if (s.getGarage() == g) {
                    out.println(s.toString());
                }
            }
        }

    }

}
