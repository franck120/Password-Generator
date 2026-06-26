package passwordgenerator;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
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
}