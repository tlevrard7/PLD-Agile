# Glossaire

- **Plan de la ville** : Ensemble de noeuds et de tronçons.
- **Noeud = Point** : Point sur une carte. Un noeud est caractérisé par un id, une latitude et une longitude. La latitude et la longitude sont en degrés décimaux.  
- **Segment de route = Tronçon** : Ligne reliant 2 noeuds. La ligne est caractérisée par un idNoeudOrigine, idNoeudDestination, une longueur en mètres et un nom de rue.
- **Intersection** : Noeud/Point qui est le lieu de recontre de 2 segments de route. 
- **Entrepôt** : Point de départ de chaque livreur au début d'une tournée. Ce point est caractérisé par un idNoeud définit dans le plan de la ville.
- **Enlèvement = Pickup**
- **Livraison = Delivery** 
- **Point d'enlèvement** : Point sur un plan où on récupère un colis. Ce point est caractérisé par une adresse d'enlèvement qui est un idNoeud définit dans le plan de la ville.
- **Durée d'enlèvement** : Durée pour récupérer un colis une fois que le livreur est arrivé au point d'enlèvement.
- **Point de livraison** : Point sur un plan où on livre un colis. Ce point est caractérisé par une adresse de livraison qui est un idNoeud définit dans le plan de la ville.
- **Durée de livraison** : Durée pour livrer un colis une fois que le livreur est arrivé au point de livraison.
- **Demande de Pickup&Delivery / Pickup&Delivery** : Combinaison d'un point d'enlèvement, d'un point de livraison, d'une durée d'enlèvement et d'une durée de livraison.
- **Livreur** : Personne qui peut effectuer des Pickup&Delivery
- **Programme de Pickup&Delivery** : Ensemble de Pickup&Delivery encore non assignés à un livreur
- **Tournée** : Séquence de points définissant un ordre de passage à des points d'enlèvement ou de livraison. Une tournée débute et finit à l'entrepôt et est associée à un livreur.
- **Carte** : Représentation graphique d'un plan de ville pouvant également contenir des points d'enlèvement et de livraison, des tournées ...
- **Utilisateur** : Utilisateur de l'application. Celui-ci est un gestionnaire de livreurs, c'est à dire qu'il va assigner un livreur à chacune des livraisons pour ensuite calculer les tournées de ces livreurs.
- **Client** : Personné liée à l'origine d'une demande de Pickup&Delivery

