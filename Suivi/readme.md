
Attention !
Deux fois j'ai fait un suivi et deux fois votre fusion à supprimer le suivi !
C'est très gênant car j'ai besoin de relire les remarques que je vous ai
déjà faites !
Encore une fois, faites attention à ce que vous faites !

# Suivi du lun. 16 mars 2020 14:31:19 CET
Denis Conan
- cohérence entre le modèle et le code
    - [] qu'est-ce que associerBadge(final Badge badge, boolean bidirectionnel)
         dans Client ? l'association du diagramme de classes est
         bidirectionnelle, donc aussi dans le code
    - à quelques détails près comme les arguments, c'est cohérent
- cohérence entre la préparation des tests et la programmation des tests
    - []
- [] un invariant s'exprime avec les attributs de la classe (uniquement) :
     par exemple, l'invariant de Badge ne doit pas aller dans Chambre ou
     dans Client
     + écire this.client != null dans l'invariant de Badge signifie qu'un
       badge est toujours en possession d'un client : c'est faux
     + idem avec d'autres termes de cet invariant
- [] vous avez trop de getters et de setters ; certains ne sont pas utiles ;
     vous cassez la propriété d'encapsulation
- [] les méthodes de la façade ne doivent pas utiliser des objets
     (internes) au système ; sinon, cela signifie que les acteurs doivent
     connaître le COMMENT en plus du QUOI
     - les méthodes chercherXxxx doivent rester privées
     - les méthodes créerXxxx ne doivent pas retourner d'objets (internes)
       du système
- programmation des cas d'utilisation
    - « créer chambre » : ok
    - « check-in » : ok
    - « check-out » : ok
- programmation des tests de validation des cas d'utilisation
    - « créer chambre » : ok
    - « check-in » : ok
    - « check-out » : ok
- programmation des tests unitaires
    - constructeur ok
    - [] l'autre, c'est pas clair entre table de décision et code (détail)
- equals, hashCode et toString
    - [] revoyez le cours sur equals et plus particulièrement la propriété
         de consistance (vous mettez trop d'attributs, par exemple dans
         Chambre::equals)

---
