# Description des use cases

## Charger un plan de ville à partir d'un fichier XML

Après avoir lancé l'application, l'utilisateur décide de charger un fichier XML qui représente le plan d'une ville <br/>

Ce fichier contient : 
- Les intersections (latitude;longitude)
- Les segments de route (originIntersection;destinationIntersection;length(meters);name) 
- L'adresse de l'entrepôt qui est le point de départ de chaque tournée 

## Consulter le plan de la ville sur la carte

Après avoir chargé le fichier XML décrivant le plan de la ville, l'utilisateur décide de consulter ce plan sur une carte.

## Modifier le nombre de livreur 

L'utiliseur décide de modifier le nombre de livreurs. Il indique donc un nombre désignant le nombre de livreurs souhaité.
Par défaut, le nombre de livreur est défini à 1.

## Charger un programme de Pickup&Delivery à partir d’un fichier XML

L'utilisateur décide de charger un fichier XML qui décrit les différentes demandes de Pickup&Delivery.
Une demande de Pickup&Delivery est décrite par un point d'enlèvement et un point de livraison.

## Consulter un programme de Pickup&Delivery

Après avoir chargé le fichier XML contenant les demandes de Pickup&Delivery, l'utilisateur décide de les consulter. <br/>
- D'une part, il peut consulter l'ensemble des demandes sur une même carte (où est déjà affiché le plan de la ville) sachant qu'un carré représente un point d'enlèvement et un rond représente un point de livraison. Un rond et un carré associés à un même Pickup&Delivery partagent la même couleur, et chaque paire (rond/carré) a une couleur distincte des autres.
- D'autre part, il dispose d'une liste déroulante contenant chacune des demandes. Il peut ainsi en sélectionner une en particulier afin d'afficher (uniquement) sur la carte les points d'enlèvement et de livraison relatifs à cette demande.

## Assigner un livreur à une livraison

L'utilisateur clique sur une ligne de la liste déroulante contenant les demandes de Pickup&Delivery pour sélectionner une demande en particulier. Il peut ensuite assigner un livreur à cette demande. 

## Calculer puis consulter la tournée d'un livreur

L'utilisateur sélectionne un livreur en particulier puis décide de calculer la tournée de celui-ci. La tournée calculée est alors affichée sur la carte du plan de la ville. 
La position de l'entrepôt, les différentes routes à emprunter ainsi que les différents points d'enlèvement et de livraison sont clairement indiqués. 
De plus, l'utilisateur peut consulter l'heure de début (8h = départ de l'entrepôt) et l'heure de fin de la tournée (retour à l'entrepôt). 
L'utilisateur peut également cliquer sur un point d'enlèvement ou de livraison en particulier afin de consulter l'heure d'arrivée ou de départ du livreur à/de celui-ci. 
Cela permet notamment de prévenir le client.

Pour chaque Pickup&Delivery, le point Pickup doit être visité avant le point Delivery. 
Enfin, la tournée calculée doit être la meilleure possible, c'est à dire celle de durée minimale sachant que la vitesse du livreur est constante et égale à 15 km/h.








