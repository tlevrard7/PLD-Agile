# UseCases non implémentés :

## Consulter les moments importants d'une tournée

L'utilisateur peut consulter l'heure de début (8h = départ de l'entrepôt) et l'heure de fin de la tournée (retour à l'entrepôt). 
L'utilisateur peut également cliquer sur un point d'enlèvement ou de livraison en particulier afin de consulter l'heure d'arrivée ou de départ du livreur à/de celui-ci. Cela permet notamment de prévenir le client.

##  Modifier le nombre de livreurs : rajouter/supprimer un livreur

L'utilisateur peut modifier le nombre de livreurs en ajoutant ou en supprimant un livreur déjà existant. Pour créer un nouveau livreur, il suffit de saisir un nom et un prénom de livreur puis de valider

## Ajouter une livraison à un programme de Pickup&Delivery

L'utilisateur vient de recevoir un appel d'un client qui souhaite être livré demain. Il décide alors de rajouter la livraison au programme de Pickup&Delivery.
Il choisit d'abord le point d'enlèvement en cliquant sur un point de la carte de la ville. Puis, de la même manière, il choisit le point de livraison.
Enfin, il doit saisir une durée d'enlèvement et une durée de livraison.

## Supprimer une livraison d'un programme de Pickup&Delivery ou d'une tournée

L'utilisateur vient de recevoir un appel d'un client qui lui indique qu'il ne sera pas présent demain pour réceptionner son colis, le client lui indique alors le point de livraison correspondant.
L'utilisateur décide alors de supprimer la livraison. Pour cela, il commence par rechercher la livraison sur la carte ou sur la liste déroulante des livraisons puis il clique sur le point de livraison (ou la livraison) correspondant à la livraison et il indique qu'il veut supprimer la livraison. 
Cela supprime cette livraison de la carte et de la liste des livraisons.
De plus, si cette livraison était déjà assignée à un livreur, sa tournée est recalculée automatiquement.

## Charger des tournées à partir d'un fichier

L'utilisateur décide de charger des tournées (préalablement calculées) à partir d'un fichier xml. Pour cela, il clique simplement sur un bouton "charger des tournées", l'application lui demande alors de sélectionner un fichier xml contenant ces tournées. Une fois le fichier chargé, il est possible de consulter ces tournées.

## Changer l'ordre de passage aux points

L'utilisateur vient de recevoir un appel d'un client qui lui indique qu'il doit impérativement être livré avant 10h. L'utilisateur clique alors sur la livraison correspondante et indique qu'elle doit être effectuée avant 10h . cela recalcule donc la tournée concernée (s'il y en a une).
