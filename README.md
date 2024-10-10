# Rick And Morty API

Ce projet consiste à communiquer avec une API pour obtenir des personnages de la série Rick et Morty.
Cette liste permet de visualiser les différentes informations des personnages de lé série, là où elles vivent.

## IMPORTANT !

Le projet n'est malheureusement pas terminé et la connexion à l'API ne fonctionne pas correctement, c'est pourquoi tout les personnages, pour des raisons de test, proviennent de la dimension C-137
et de la planet Terre.

## Table des matières

- [Structure](#structure)

## Structure

L'application est structuré en MVI, de la manière suivante :

- Couche Data
    Cette couche de l'application permet d'instancier les données depuis l'API
    Elle se distingue en plusieurs parties :
      - di : Dans ce dossier ce trouve le DataModule qui permet d'instancier nos services à l'API. C'est notamment grace à l'injection de dépendnaces qu'on réalise cela
      - local : Pour la base de données local, on utilise Realm
        - objects :  Il s'agit d'objets Realms pour pouvoir créer des objets de base de donnée locale
      - remote : Il s'agit des fichiers qui font appel à l'API afin d'obtenir les données
        - responses : Symbolise les réponses de l'API
      - repositories : Il s'agit de la couche qui permet de faire le lien entre le domain et les data

- Couche Domian
    Cette couche permet de créer des objets plus faciles à manipuler que les données de l'API
      - di : Vide
      - models : Fichiers regroupant les models par type de donnée
        - character : model character
        - location : model location
      - repositories : Permet de faire les différentes requêtes GET ou POST que la couche data utilisera

- Couche features/characters
    Cette couche permet de créer les interfaces graphiques pour l'utilisateur
      - details : Écran et vue-modèle lié aux détails d'information
      - list : Représente la vue et la vue-modèle de la liste de tous les personnages
