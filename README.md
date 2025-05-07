Scala Project GS
Description

Ce projet implémente une simulation de courses automobiles en Scala. Il inclut la gestion des circuits, des voitures, des pilotes et des courses. Il permet de simuler des podiums aléatoires et calculés en fonction de divers critères, tels que l'expérience du pilote et les caractéristiques de la voiture. Le projet utilise des concepts de programmation fonctionnelle et orientée objet.
Fonctionnalités

    Gestion des circuits : Chaque circuit a un nom, un lieu, une longueur et un niveau de difficulté.

    Gestion des voitures : Différentes catégories de voitures (Classic, Sport, SuperSport) avec des caractéristiques spécifiques telles que la vitesse, l'accélération, le moteur, etc.

    Gestion des pilotes : Les pilotes ont un nom, un prénom, un âge, une voiture, de l'expérience et un palmarès avec un classement par course.

    Simulations de course : Simulation de podiums aléatoires et calculés avec des résultats basés sur les performances des pilotes et des voitures.

Prérequis

    Scala 2.13.x

    sbt (Scala Build Tool)

Installation

    Clonez le repository :

git clone https://github.com/DLuffy07/scalaProjectGS.git

Accédez au dossier du projet :

cd scalaProjectGS

Compilez le projet avec sbt :

    sbt compile

Exécution

Pour lancer l'application, utilisez la commande suivante avec sbt :

sbt run

Cela exécutera la classe principale Main, qui simule les courses et affiche les résultats des podiums ainsi que les informations sur les pilotes.
Tests

Les tests unitaires sont écrits avec ScalaTest. Pour exécuter les tests, utilisez la commande suivante :

sbt test

Cela exécutera les tests définis dans le projet, y compris les tests des classes MyList, Pilote, Course, et d'autres composants du programme.
Structure du projet

Le projet est organisé comme suit :

src
 ├── main
 │    ├── scala
 │    │    ├── cars
 │    │    ├── competition
 │    │    ├── exceptions
 │    │    ├── mylist
 │    │    └── pilote


Auteurs

    Charles-Edouard Tagba

    Gael AKOUSSAH

    Moez ZARROUK
    

Licence

Ce projet est sous licence MIT.
