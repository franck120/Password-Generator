Doc - Password Generator
1. Analyse Fonctionnelle
Password Generator est un outil en ligne de commande (CLI) permettant de générer des mots de passe et de tester leurs robustes, selon les choix de l'utilisateur.

L'utilisateur interagit directement avec le terminal. Il peut définir les caractéristiques des mots de passe qu'il souhaite obtenir, puis l'application génère les mots de passe demandés et vérifie leur niveau de sécurité grâce à un outil externe exécuté dans un conteneur Docker.

Au lancement du programme, l'utilisateur doit renseigner plusieurs informations :

La longueur souhaitée du mot de passe.
Le nombre de mots de passe à générer.
Les types de caractères à utiliser.

Les options disponibles sont :
Lettres majuscules : A-Z
Lettres minuscules : a-z
Chiffres :0-9
Symboles :!@#$%^&*
L'application vérifie ensuite que les paramètres fournis sont corrects.
Par exemple : La longueur doit être supérieure à zéro.
Au moins une options de caractères doit être sélectionnée.

L'application possède un mode permettant de générer plusieurs mots de passe en une seule exécution.
Après chaque génération, le mot de passe est envoyé vers un outil de vérification exécuté dans un conteneur Docker.
Le conteneur retourne un score compris entre 0 et 4.
La correspondance utilisée est :
Score	Niveau
0	Très faible
1	Faible
2	Moyen
3	Fort
4	Très fort
L'application affiche ensuite le niveau correspondant à l'utilisateur.

2. Analyse Technique

Structure du programme

Le projet est développé en Java 21 avec Maven.

Classe PasswordGenerator : Cette classe contient la logique principale de l'application.
Elle permet :
d'afficher les messages dans le terminal
de récupérer les choix de l'utilisateur
de générer les mots de passe
d'appeler le conteneur Docker 
d'afficher le résultat.
Génération du mot de passe

La génération utilise la classe Java : SecureRandom
Qui est une alternative à Random plus adapté pour la génération de mot de passe.

Le programme :

Ajoute au moins un caractère de chaque catégorie sélectionnée.
Complète le mot de passe jusqu'à la longueur demandée.
Mélange les caractères obtenus.
Communication entre Java et Docker

La communication entre l'application Java et Docker est réalisée avec : ProcessBuilder

Java exécute une commande Docker : docker run --rm password-generator motdepasse

Le mot de passe généré est envoyé comme paramètre au conteneur.

Le conteneur effectue ensuite l'analyse de sécurité et retourne uniquement le score.

L'image du conteneur est construite sur un projet node se trouvant dans zxcvbn_docker avec le dockerfile car zxcvbn utiliser pour le teste du mot de passe est un bibliothèque node

3. Guide d'installation
Prérequis
Java 21;
Maven;
Docker Desktop.

Vérification :
java -version
mvn -version
docker --version
Asurer vous que docker desktop est lancé

Installation du projet
git clone URL_DU_DEPOT
Puis:
cd PasswordGenerator
cd zxcvbn_docker

Construire l'image :
docker build -t password-generator .

Vérifier que l'image existe :
docker images

Résultat attendu :
password-generator

Compilation de l'application Java
Revenir à la racine du projet :
cd ..
Compiler avec Maven :
mvn clean package

Cette commande génère un fichier .jar dans :

target/
Exécution de l'application
Lancer la commande :
java -jar target/PasswordGenerator-1.0.jar
Le terminal affiche :

BIENVENUE SUR PASSWORD GENERATOR VOTRE GÉNÉRATEUR DE MOT DE PASSE
Combien de mots de passe veux-tu générer

L'utilisateur peut ensuite configurer la génération.
