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
}