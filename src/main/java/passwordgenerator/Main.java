package passwordgenerator;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        System.out.println("BIENVENUE SUR PASSWORD GENERATOR VOTRE GÉNÉRATEUR DE MOT DE PASSE");
        Scanner scanner = new Scanner(System.in);
        int passwordCount = getUserInput(scanner, "Combien de mots de passe veux-tu générer : ");
        int passwordLength = getUserInput(scanner,"Veuillez entrez la longueur du mot de passe : ");
        System.out.println("Longueur choisie : " + passwordLength);
        System.out.println("Nombre demandé : " + passwordCount);
        scanner.close();
    }


    private static int getUserInput(Scanner scanner, String message){
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }
}