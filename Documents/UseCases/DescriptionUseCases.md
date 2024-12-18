# Description des use cases

## Charger un plan de ville à partir d'un fichier XML

Après avoir lancé l'application, l'utilisateur décide de charger un fichier XML qui représente le plan d'une ville <br/>

Ce fichier contient : 
- Les intersections (latitude;longitude)
- Les segments de route (originIntersection;destinationIntersection;length(meters);name) 
- L'adresse de l'entrepôt qui est le point de départ de chaque tournée 

## Consulter le plan de la ville sur la carte

Après avoir chargé le fichier XML décrivant le plan de la ville, l'utilisateur décide de consulter ce plan sur une carte.

## Charger un programme de Pickup&Delivery à partir d’un fichier XML

L'utilisateur décide de charger un fichier XML qui décrit les différentes demandes de Pickup&Delivery.
Une demande de Pickup&Delivery est décrite par un point d'enlèvement et un point de livraison.

## Consulter un programme de Pickup&Delivery

Après avoir chargé le fichier XML contenant les demandes de Pickup&Delivery, l'utilisateur décide de les consulter. <br/>
- D'une part, il peut consulter l'ensemble des demandes sur une même carte (où est déjà affiché le plan de la ville) sachant qu'un carré représente un point d'enlèvement et un rond représente un point de livraison. Un rond et un carré associés à un même Pickup&Delivery partagent la même couleur, et chaque paire (rond/carré) a une couleur distincte des autres.
- D'autre part, il dispose d'une liste déroulante contenant chacune des demandes. Il peut ainsi en sélectionner une en particulier afin d'afficher (uniquement) sur la carte les points d'enlèvement et de livraison relatifs à cette demande.

## Calculer puis consulter la tournée d'un livreur

L'utilisateur sélectionne un livreur en particulier puis décide de calculer la tournée de celui-ci. La tournée calculée est alors affichée sur la carte du plan de la ville. 
La position de l'entrepôt, les différentes routes à emprunter ainsi que les différents points d'enlèvement et de livraison sont clairement indiqués. 
De plus, l'utilisateur peut consulter l'heure de début (8h = départ de l'entrepôt) et l'heure de fin de la tournée (retour à l'entrepôt). 
L'utilisateur peut également cliquer sur un point d'enlèvement ou de livraison en particulier afin de consulter l'heure d'arrivée ou de départ du livreur à/de celui-ci. 
Cela permet notamment de prévenir le client.

Pour chaque Pickup&Delivery, le point Pickup doit être visité avant le point Delivery. 
Enfin, la tournée calculée doit être la meilleure possible, c'est à dire celle de durée minimale sachant que la vitesse du livreur est constante et égale à 15 km/h.

## Assigner un livreur à une livraison

L'utilisateur clique sur une ligne de la liste déroulante contenant les demandes de Pickup&Delivery pour sélectionner une demande en particulier. Il peut ensuite assigner un livreur à cette demande. 

## Modifier le nombre de livreurs

L'utiliseur décide de modifier le nombre de livreurs. Il indique donc un nombre désignant le nombre de livreurs souhaité.
Par défaut, le nombre de livreur est défini à 1.

## Ajouter une livraison à un programme de Pickup&Delivery

L'utilisateur vient de recevoir un appel d'un client qui souhaite être livré demain. Il décide alors de rajouter la livraison au programme de Pickup&Delivery.
Il choisit d'abord le point d'enlèvement en cliquant sur un point de la carte de la ville. Puis, de la même manière, il choisit le point de livraison.
Enfin, il doit saisir une durée d'enlèvement et une durée de livraison.

## Supprimer une livraison d'un programme de Pickup&Delivery ou d'une tournée

L'utilisateur vient de recevoir un appel d'un client qui lui indique qu'il ne sera pas présent demain pour réceptionner son colis.
L'utilisateur décide alors de supprimer la livraison. Pour cela, il commence par rechercher la livraison sur la carte (à partir du point de livraison que lui a indiqué le client) puis il clique sur le point de livraison correspondant à la livraison et il indique qu'il veut supprimer la livraison. 
Cela supprime cette livraison de la carte et de la liste des livraisons.
De plus, si cette livraison était déjà assignée à un livreur, sa tournée est recalculée.

## Déplacer un Pickup ou un Delivery d'un programme de Pickup&Delivery ou d'une tournée

L'utilisateur, après avoir reçu un appel concernant une livraison, décide de déplacer un point pickup (ou delivery). 
Pour cela, il sélectionne le point pickup (ou delivery) sur la carte et le déplace afin d'indiquer son nouvel emplacement. 

Si cette livraison était déjà assignée à un livreur, sa tournée est recalculée.

## Changer l'ordre de passage aux points

L'utilisateur vient de recevoir un appel d'un client qui lui indique qu'il doit impérativement être livré avant 10h. L'utilisateur clique alors sur la livraison correspondante et indique qu'elle doit être effectuée avant 10h . cela recalcule donc la tournée concernée (s'il y en a une).

## Sauvegarder des tournées dans un fichier

L'utilisateur décide de sauvegarder dans un fichier les tournées qu'il vient de calculer. Pour cela, il clique simplement sur un bouton "sauvegarder les tournées"
qui va lancer le téléchargement d'un fichier xml contenant les différentes tournées calculées.

## Charger des tournées à partir d'un fichier

L'utilisateur décide de charger des tournées préalablement calculées à partir d'un fichier xml. Pour cela, il clique simplement sur un bouton "charger des tournées", l'application lui demande alors de sélectionner un fichier xml contenant ces tournées. Une fois le fichier chargée, les tournées peuvent être consultées sur la carte.








