import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        choisirJeu();
    }

    // Methode demandant a l'utilisateur de choisir un jeu a jouer
    public static void choisirJeu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Choisissez un jeu (1 : morpion, 2 : puissance 4) : ");
        int jeu = input.nextInt();
        if (jeu == 1) {
            // Créer un objet de la classe Morpion
            Morpion morpion = new Morpion(3, 3, 3);
            // Faire jouer au morpion
            morpion.jouer();
        } else {
            // Créer un objet de la classe Power4
            Power4 power4 = new Power4(6, 7, 4);
            // Faire jouer au puissance 4
            power4.jouer();
        }
    }

}
