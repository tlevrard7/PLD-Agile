# Questions à poser à la PO

## Nouvelles questions 

### Les adresses dans le XML sont sous la forme : "26464256", ça représente quoi  des id noeuds ??

OUI c'est ça et donc il faudra, lors de la création d'une nouvelle livraison, ne pouvoir sélectionner que un point défini dans le plan

### Par quel moyen voulez vous qu'on modifie l'ordre de passage au point ? On clique sur un point et après ?

On peut définir un ordre entre les différents points, c'est à dire qu'on peut cliquer sur un point et dire qu'il doit être ffait avant un autre point

## Questions répondues

### Faut-il faire "Avoir plusieurs livreurs et donc assigner un livreur à une livraison ?", "Sauvegarde de tournées", "Charger des tournées à partir d'un fichier"

Oui il faut tout faire 

### Une demande de Pickup&Delivery est décrite par un point d'enlèvement et un point de livraison, ce sont des 'Intersection' du même genre que pour le plan ?


### Dans le fichier il y a également dureeEnlevement et dureeLivraison, on ignore ? On prend 5 minutes comme il y a écrit dans le sujet pour le durée d'une livraison ?

On prend ce qu'il y a dans les XML

### Faut-il mettre la même couleur pour un un couple (pointEnelevement, pointLivraison) ?

Oui

### Modification interactive du programme : "Changer l’ordre de passage aux points" ça correspond à quoi sachant que c'est notre programme qui le détermine?

Une fois qu'une tournée est calculée, le gestionnaire peut modifier l'ordre calculé en déplaçant un point pour par exemple répondre à d'autres contraintes client.

Par exemple un client a appelé et il veut être livré avant 10h donc on déplace l'ordre et ça recalcule. 

### C'est quoi un Persona exactement ?

Description de l'utilisateur : qui il est est ? Ses compétences en Info ? Les IHM peuvent dépendre du Persona

### Vous avez besoin quoi comme design pattern?

A voir quand on fait le class diagram