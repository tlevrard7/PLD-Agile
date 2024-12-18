# Documentation des Tests Postman pour la gestion des données (Data)

Vous pouvez rejoindre l'environnement de test sur Postman via le lien suivant : 

https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-f36d8570-5f67-4758-b55d-48cf8489d03f?action=share&creator=40455199&ctx=documentation

## **Cas 1 : Réinitialisation des données de livraison**

### Description  
Tester le cas où un chargement d'un fichier de livraison occure alors qu'un fichier de livraison à déjà été chargé.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/data/reset`  

### Réponse attendue :

```
Données réinitialisées avec succès.
```

---