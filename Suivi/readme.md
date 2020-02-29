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
    - [] à faire
- tables de décision des tests unitaires
    - [] à faire

---
