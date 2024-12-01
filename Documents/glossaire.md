# Glossaire

- **Plan** : Ensemble de noeuds et de tronçons.
- **Carte** : Représentation graphique d'un plan de ville pouvant également contenir des points d'enlèvement et de livraison, des tournées, etc. Elle peut être répresentée par un Plan.
- **Noeud = Point** : Point sur une carte. Un noeud est caractérisé par un id, une latitude et une longitude. La latitude et la longitude sont en degrés décimaux.  
- **Segment de route = Tronçon** : Ligne reliant 2 noeuds. La ligne est caractérisée par un idNoeudOrigine, idNoeudDestination, une longueur en mètres et un nom de rue.
- **Intersection** : Noeud/Point qui est le lieu de recontre d'un segment de route. 
- **Entrepôt** : Point de départ de chaque livreur au début d'une tournée. Ce point est caractérisé par un idNoeud définit dans le plan de la ville.
- **Point d'enlèvement = Pickup** : Point sur un plan où on récupère un colis. Ce point est caractérisé par une adresse d'enlèvement qui est un idNoeud définit dans le plan de la ville.
- **Point de livraison = Destination** : Point sur un plan où on livre un colis. Ce point est caractérisé par une adresse de livraison qui est un idNoeud définit dans le plan de la ville.  
- **TypePoint** : Indicateur de la fonction d'un point: soit il s'agit d'un point de livraison(destination), d'enlèvement(pickup), de l'entrepôt ou d'une simple intersection.
- **Durée d'enlèvement** : Durée pour récupérer un colis une fois que le livreur est arrivé au point d'enlèvement.
- **Durée de livraison** : Durée pour livrer un colis une fois que le livreur est arrivé au point de livraison.
- **Demande de Pickup&Delivery = Livraison** : Combinaison d'un point d'enlèvement, d'un point de livraison, d'une durée d'enlèvement et d'une durée de livraison.
- **Livreur** : Personne qui peut effectuer des livraisons.
- **Programme de Pickup&Delivery** : Ensemble de livraisons encore non assignés à un livreur
- **Tournée** : Programme de livraisons assignées à un livreur. Elle comporte le circuit optimal à prendre par ce livreur et les livraisons reálisées.
- **Circuit** : Séquence de segemnts parcourus pour réaliser une tournée. Un circuit débute et se finit à l'entrepôt en passant par tous les points Pickup et Destination assignés.
- **Utilisateur** : Utilisateur de l'application. Celui-ci est un gestionnaire de livreurs, c'est à dire qu'il va assigner un livreur à chacune des livraisons pour ensuite calculer les tournées de ces livreurs.
- **Client** : Personné à l'origine d'une demande de Livraison.

