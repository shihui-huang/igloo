Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser)

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - RÉPONSE et éventuelles remarques de votre part,


# Suivi du lun. 24 févr. 2020 16:27:10 CET
Denis Conan
- diagramme de cas d'utilisation
    - [x] qu'est-ce que « (ré)initialiser un badge » ?
    - [x] qu'est-ce que « effacer un badge » ?
- préconditions et postconditions
    - [x] « créer un client » : votre formulation de la précondition implique
         que vous identifiez de manière unique un client avec son nom + son
         prénom ; autrement dit, vous n'avez pas d'identifiant : est-ce bien
         cela ? c'est possible
    - [x] « enregistrer... » : il manque les conditions sur le badge d'accès
         + par construction « dernière paire de clefs... » est vrai, non ?
           (sinon, il y a des cas d'utilisation avec des postconditions
            incomplètes ou fausses, non ?)
    - [x] « libérer... » : n'y a-t-il pas trop de contraintes ? (par construction
         certaines sont vraies, non ?)
- tables de décision des tests de validation
    - [x] à adapter selon les remarques précédentes
- diagramme de classes
    - [x] une chambre est toujours associée à un badge ?
    - [x] une chambre peut être associée à plusieurs badges ?
    - [x] redondance de chemins entre Chambre et Badge (association directe et
         passage par PaireClefs) : c'est une faiblesse
    - [x] redondance de chemins entre Chambre et Client
    - [x] non-respect des propriétés de la composition en mettant deux
         compositions sur PaireClefs : le cycle de vie de PaireClefs ne peut
         pas être lié à la fois à Chambre et à Badge, autrement dit, un
         composant n'est que dans un composé
- diagrammes de séquence
    - [x] « créer une chambre » : retirez la création de la serrure de ce
         diagramme de séquence car cela concerne l'autre système
    - [] « enregistrer... » : on ne met pas d'affectation mais des messages
         + la formulation « clefs.clef1 » pose problème : l'attribut clef1 est
           public (au lieu d'être privé) et il doit y avoir un message
           explicite sur un participant de type PaireClefs
         + les associations sont bidirectionnelles dans le diagramme de classes
           et il manque donc des associations d'objets dans le diagramme de
           séquence
    - [] « libérer... » : idem sur le traitement des associations
- diagramme de machine à états
    - [x] à faire
- fiches des classes
    - [x] à faire
- invariant
    - [x] à faire
- tables de décision des tests unitaires
    - [] à faire

---

# Suivi du lun. 02 mars 2020 14:40:39 CET
Denis Conan
- merci pour l'indication des commentaires pris en compte ; je regarde les
  nouveaux éléments uniquement
- à la lecture du journal du dépôt, je comprends que vous vous êtes concentrés
  sur l'amélioration de la modélisation
- préconditions et postconditions
    - [x] « enregistrer... » : on ne donne une dernière paire de clefs en
         entrée de ce cas d'utilisation ; en revanche, on donne l'identifiant
         d'un badge et on vérifie qu'il est à la réception
- diagramme de classes
    - [x] ne mettez pas les opérations dans le diagramme de classes ; ce n'est
         pas nécessaire et pas encore pertinent (la liste précise avec les
         prototypes sera donné dans la fiche de la classe ; et c'est amplement
         suffisant)
    - [x] pb de multiplicité et de sémantique sur les compositions vers
         PaireClefs : un objet composant n'est que dans un composé
         => vous ne pouvez pas mettre une composition, mais vous pouvez mettre
            une agrégation si vous le souhaitez
         + la multiplicité « 1 » de PaireClefs vers Chambre ou vers Badge
           posera un problème dans le sprint 2 ; mettez « 0..1 »
- diagrammes de séquence
    - [x] « créer une chambre » : on ne crée pas la serrure dans ce diagramme :
         c'est un autre système possiblement utilisé par un autre acteur, etc.
         + écrire clefs.clef1 suppose que l'attribut clef1 est public (ce que
           nous ne voulons pas)
         + comme vous avez le concept PaireClefs, vous devez créer
           un nouvel objet PaireClefs
    - [x] « enregistrer... » : vous avez décider de dessiner le diagramme sans
         donner auparavant la séquence (comme suggéré / demandé dans l'énoncé
         du TP) ; et, vous avez oublié une partie de la précondition : par
         exemple la chambre doit être disponible
         + on ne met pas d'affectation à la place d'un message
         + idem écrire clefs.clef1
         + comme vous avez le concept PaireClefs, vous devez préférer créer
           un nouvel objet PaireClefs au lieu de modifier le contenu de
           l'existant (ce qui est dangereux lorsque la paire de clefs reste
           associée au badge)
         + au lieu des clefs en argument de inscrireClefs, vous devez mettre
           un objet de type PaireClefs
         + l'association entre Badge et Client est bidirectionnelle ; vous
           devez donc aussi associer le client
    - [x] « libérer... » : les associations autour de Chambre, Badge et Client
         sont bidirectionnelles ; donc, il manque des messages pour retirer
         toutes les associations

- tous les éléments de la modélisation ne sont pas dans modelisation.pdf

- [x] pour que le script de la séance passe, vous devez commencer la programmation

- [x] je vous encourage à débuter la programmation en hors présentiel avant la
  prochaine séance ce mercredi

---

# Suivi du lun. 16 mars 2020 14:31:19 CET
Denis Conan
- cohérence entre le modèle et le code
    - [x] qu'est-ce que associerBadge(final Badge badge, boolean bidirectionnel)
         dans Client ? l'association du diagramme de classes est
         bidirectionnelle, donc aussi dans le code
    - à quelques détails près comme les arguments, c'est cohérent
- cohérence entre la préparation des tests et la programmation des tests
    - [?]

(**On écrit le test selon le préparation,est-ce que vous pouvez expliquer plus présisément,svp?)


- [x] un invariant s'exprime avec les attributs de la classe (uniquement) :
     par exemple, l'invariant de Badge ne doit pas aller dans Chambre ou
     dans Client
     + écire this.client != null dans l'invariant de Badge signifie qu'un
       badge est toujours en possession d'un client : c'est faux
     + idem avec d'autres termes de cet invariant
- [?] vous avez trop de getters et de setters ; certains ne sont pas utiles ;
     vous cassez la propriété d'encapsulation

(**Est-ce que vous pouvez expliquer plus présisément,svp?)

- [?] les méthodes de la façade ne doivent pas utiliser des objets
     (internes) au système ; sinon, cela signifie que les acteurs doivent
     connaître le COMMENT en plus du QUOI
     - [?]les méthodes chercherXxxx doivent rester privée:s
     - [x]les méthodes créerXxxx ne doivent pas retourner d'objets (internes)
       du système

(**Si on met les méthodes chercherXxxx à privée, comment peut-on tester le dernier
    test dans TestEnregistereOccupationChambre?)

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
    - [?] l'autre, c'est pas clair entre table de décision et code (détail)
- equals, hashCode et toString
    - [x] revoyez le cours sur equals et plus particulièrement la propriété
         de consistance (vous mettez trop d'attributs, par exemple dans
         Chambre::equals)

---

# Suivi du mer. 01 avril 2020 08:03:14 CEST
Denis Conan
- vous avez demandé le suivi beaucoup trop tard ; donc, uniquement partiel
- (**On écrit le test selon le préparation,est-ce que vous pouvez expliquer
     plus présisément,svp?)
    - nommage correct ; donc, soit correction par vos soins, soit mauvaise
      appréciation de ma part lors du suivi
- (**Si on met les méthodes chercherXxxx à privée, comment peut-on tester le
     dernier test dans TestEnregistereOccupationChambre?)
    - cf. mon message dans le forum vendredi 27 mars
- getters/setters (**Est-ce que vous pouvez expliquer plus présisément,svp?)
    - pb de qualité
- [x] utilisation des Streams : dans chercherChambre,
     chambre.getId() == id est une très grosse erreur
- utilisation de la classe Optional : ok
- [x] ajout des classes Clefs et PaireDeClefs : pourquoi ClefVide::getValue
     retourne null ?
     + dans le diagramme de classes, ce ne peut pas être une composition car
       il y a des agrégations
- [x] intégration du patron de conception Singleton : à faire
- [x] intégration du patron de conception Publier-Souscrire : les temporisations
     de l'exemple Thread.sleep() n'est pas à mettre dans votre logiciel
     + qu'est-ce que getPublisher dans la façade ? il n'y a pas de cas
       d'utilisation dans le diagramme de cas d'utilisation
     + mauvais nommage de la classe de consommation car c'est le nom d'un
       acteur ; ambigu par exemple avec Client
- cas d'utilisation du Sprint 2
     - [x] « perte sans remplacement » : précondition incomplète car le
          cahier des charges qui distinguent explicitement deux situations
          selon que le badge perdu était à la réception ou avec un client
          => adapter la suite
          + mauvaise formulation logique de la postcondition
          + diagramme de séquence avec modélisation de la suppression du badge
            mal placée
     - [x] « perte avec remplacement » : à faire


---
