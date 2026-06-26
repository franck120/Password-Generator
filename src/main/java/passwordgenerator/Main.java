package passwordgenerator;
import java.nio.charset.StandardCharsets; //Import nécessaire pour SecureRandom qui est une version de random plus adapté à la génération de mot de passe
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SecureRandom randomSec = new SecureRandom();
        System.out.println("BIENVENUE SUR PASSWORD GENERATOR VOTRE GÉNÉRATEUR DE MOT DE PASSE");
        Scanner scanner = new Scanner(System.in);
        int passwordCount = getNumberUserInput(scanner, "Combien de mots de passe veux-tu générer : ");
        int passwordLength = getNumberUserInput(scanner,"Veuillez entrez la longueur du mot de passe : ");

        boolean upperCaseCharater = getBooleanUserInput(scanner,"Inclure les majuscules ?");
        boolean lowerCaseCharacter = getBooleanUserInput(scanner,"Inclure les minuscules ?");
        boolean integerCharacter = getBooleanUserInput(scanner,"Inclure les chiffres ?");
        boolean symbolCharater = getBooleanUserInput(scanner, "Inclure les symboles ?");
        //Tant qu'au moins une valeur n'est pas choisit continuer de demander
        while(!CheckCategory(upperCaseCharater,lowerCaseCharacter,integerCharacter,symbolCharater)){
            System.out.println("Au moins une catégorie doit être choisi");
            upperCaseCharater = getBooleanUserInput(scanner,"Inclure les majuscules ?");
            lowerCaseCharacter = getBooleanUserInput(scanner,"Inclure les minuscules ?");
            integerCharacter = getBooleanUserInput(scanner,"Inclure les chiffres ?");
            symbolCharater = getBooleanUserInput(scanner, "Inclure les symboles ?");
        }
        //Tant que la longueur du mot de passe sera inférieur au nombre d'options sélectionnés.Demander une nouvelle longueur
        int selectedOptions = computeSelectedOption(upperCaseCharater,lowerCaseCharacter,integerCharacter,symbolCharater);
        while (passwordLength < selectedOptions) { 
            System.out.println("La longueur du mot de passe doit être au moins égale au nombre d'option sélectionnées.");
            passwordLength = getNumberUserInput(scanner,"Veuillez entrez la longueur du mot de passe : ");
        }
        System.out.println("Longueur choisie : " + passwordLength);
        System.out.println("Nombre demandé : " + passwordCount);
        System.out.println("Majuscule: " + upperCaseCharater);
        System.out.println("Minuscule: " + lowerCaseCharacter);
        System.out.println("Nombre : " + integerCharacter);
        System.out.println("Symbol : " + symbolCharater);
        for(int i = 1; i <= passwordCount; i++){
            String password = generatePasswod(passwordLength,upperCaseCharater,lowerCaseCharacter,integerCharacter,symbolCharater,randomSec);
            System.out.println( i + "---" + password);
        }
        scanner.close();
    }

    // Elle évite de répéter plusieurs fois le même code de saisie.
    private static int getNumberUserInput(Scanner scanner, String message){
        while(true) { //Permet de vérifier que l'utilisateur saisisse des valeurs qui ferait planter l'application
            System.out.print(message);
            String valeur = scanner.nextLine();
            try {
                int nombre = Integer.parseInt(valeur);
                if(nombre > 0) {
                    return nombre;
                }
                System.out.println( "Le nombre doit être supérieur à 0.");
            } catch(NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }
    // Cette méthode transforme la réponse utilisateur en une valeur true ou false utilisable dans le programme.
    private static boolean getBooleanUserInput(Scanner scanner,String message){
        while(true){
            System.out.print(message + " (O/N) : ");
            String reponse = scanner.nextLine().trim().toLowerCase();
            if(reponse.equals("O")){
                return true;
            }
            if(reponse.equals("N")){
                return false;
            }
            System.out.println("Votre réponse est invalide");
        }
    }
    //Permet de vérifier que l'utiliser à choisit au moins une des quatres options proposés
    private static boolean CheckCategory(boolean upper,boolean lower,boolean digits,boolean symbols) {
        return upper || lower || digits || symbols;
    }
    //Permet de s'assurer qu'avec les options qui vont être ajoutés sur le mot de passe n’excède pas la longueur donné par l'utilisateur 
    private static int computeSelectedOption(boolean upper,boolean lower,boolean digits,boolean symbols) {
        int minimum = 0;
        if(upper){
            minimum++;
        }
        if(lower){
            minimum++;
        }
        if(digits){
            minimum++;
        }
        if(symbols){
            minimum++;
        }
        return minimum;

    }
    //Permet de génère un mot de passe selon les choix de l'utilisateur.
    private static String generatePasswod(int length,boolean upper,boolean lower,boolean digits, boolean symbols,SecureRandom randomSec) {
        //Eléments qui seront mélangés en fonction des options afin de générer le mot de passe le plus aléatoire possible
        String majuscules = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minuscules = "abcdefghijklmnopqrstuvwxyz";
        String chiffres = "0123456789";
        String symboles = "!@#$%^&*()-_=+[]{};:,.?";
        StringBuilder pool = new StringBuilder(); //Classe Java Permettant de construire une chaîne de caractère
        List<Character> caracteres = new ArrayList<>();
       
       //Récupére de façon aléatoire un élément de majuscules en fonction de son index dans la chaîne
        if(upper){
            caracteres.add(majuscules.charAt(randomSec.nextInt(majuscules.length()))); 
            pool.append(majuscules);
        }
         
        //Récupére de façon aléatoire un élément de miniscules en fonction de son index dans la chaîne
        if(lower){
            caracteres.add(
                minuscules.charAt(randomSec.nextInt(minuscules.length()))
            );
            pool.append(minuscules);
        }
        
        //Récupére de façon aléatoire un élément de chiffres en fonction de son index dans la chaîne
        if(digits){
            caracteres.add(chiffres.charAt(randomSec.nextInt(chiffres.length())) );
            pool.append(chiffres);
        }
        
        //Récupére de façon aléatoire un élément de symbol en fonction de son index dans la chaîne
        if(symbols){
            caracteres.add(symboles.charAt(randomSec.nextInt(symboles.length())));
            pool.append(symboles);
        }

        //Si la taille des caractères mélangés est inférieur à la longueur demandé ajouter d'autre
        while(caracteres.size() < length){
            caracteres.add(pool.charAt(randomSec.nextInt(pool.length())));
        }
        // Mélange les caractères pour éviter un ordre prévisible.
        Collections.shuffle(caracteres, randomSec);

        StringBuilder resultat = new StringBuilder();
        for(char c : caracteres){
            resultat.append(c);
        }
        return resultat.toString();
    }
    //Permet de tester le mot de passe et de retourner son score
    private static int ckeckPasswordWithZwcnbn(String password) {
        try {
            ProcessBuilder builder = new ProcessBuilder("docker","run","--rm", "password-generator", password); //Permet de lancer la conteneur et de tester le mot de passe
            builder.redirectErrorStream(true);
            Process process = builder.start();
            String result = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim(); // Récupère la valeur retourner par le conteneur
            process.waitFor();
            return Integer.parseInt(result);
        } catch(Exception e){
            throw new RuntimeException( "Erreur lors de la communication avec  Docker", e);
        }
    }
}