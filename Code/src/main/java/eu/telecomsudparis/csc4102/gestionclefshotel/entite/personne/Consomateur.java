package eu.telecomsudparis.csc4102.gestionclefshotel.entite.personne;


import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Cette classe définit le Consomateur. Les notifications sont typées.
 *
 * @author Denis Conan
 */
public class Consomateur implements Subscriber<String> {
    /**
     * identifiant du Consomateur : pour les messages a la console qui servent a
     * suivre l'execution.
     */
    private String id;
    private String nom;
    private String prenom;

    /**
     * la souscription. Cet objet sert a controler le flux.
     */
    private Subscription souscription;
    // on pourrait ajouter une collection pour garder les notifications recues

    /**
     * constructeur.
     *
     * @param id identifiant pour les affichages.
     */
    public Consomateur(final String id,String nom,String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public void onSubscribe(final Subscription souscription) {
        this.souscription = souscription;
        this.souscription.request(1);
        System.out.println("Consomateur " + id + nom +prenom+ " pret a recevoir la premiere notification");
    }

    @Override
    public void onNext(final String notification) {
        // reception d'une notification : ici, simple affichage a la console
        System.out.println("Consomateur " + id + nom +prenom + " a recu une nouvelle notification : " + notification);
        // on declare qu'on est pret a recevoir un nouveau message
        souscription.request(1);
    }

    @Override
    public void onError(final Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Consomateur "+ id + nom +prenom+ " est desabonne suite a la fermeture du flux");
    }

}
