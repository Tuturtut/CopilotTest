import java.util.Random;
import java.util.Scanner;

public class Power4 {
    // Attributs uniquement
    private int nbLignes;
    private int nbColonnes;
    private int nbAlignes;
    private int[][] grille;
    private int joueur;
    private int nbCoups;
    private int nbCoupsMax;
    private int gagnant;
    private boolean fini;
    // Constructeur
    public Power4(int nbLignes, int nbColonnes, int nbAlignes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbAlignes = nbAlignes;
        this.nbCoups = 0;
        this.nbCoupsMax = nbLignes * nbColonnes;
        this.grille = new int[nbLignes][nbColonnes];
        this.joueur = 1;
        this.gagnant = 0;
        this.fini = false;
    }

    // Modifier une case
    public void modifierCase(int ligne, int colonne, int joueur) {
        grille[ligne][colonne] = joueur;
    }

    // Tester si quelqu'un a gagné
    public boolean gagnant() {
        // Tester les lignes
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes - nbAlignes + 1; j++) {
                if (grille[i][j] != 0) {
                    boolean gagne = true;
                    for (int k = 1; k < nbAlignes; k++) {
                        if (grille[i][j] != grille[i][j + k]) {
                            gagne = false;
                        }
                    }
                    if (gagne) {
                        return true;
                    }
                }
            }
        }

        // Tester les colonnes
        for (int i = 0; i < nbColonnes; i++) {
            for (int j = 0; j < nbLignes - nbAlignes + 1; j++) {
                if (grille[j][i] != 0) {
                    boolean gagne = true;
                    for (int k = 1; k < nbAlignes; k++) {
                        if (grille[j][i] != grille[j + k][i]) {
                            gagne = false;
                        }
                    }
                    if (gagne) {
                        return true;
                    }
                }
            }
        }
        // Tester les diagonales
        for (int i = 0; i < nbLignes - nbAlignes + 1; i++) {
            for (int j = 0; j < nbColonnes - nbAlignes + 1; j++) {
                if (grille[i][j] != 0) {
                    boolean gagne = true;
                    for (int k = 1; k < nbAlignes; k++) {
                        if (grille[i][j] != grille[i + k][j + k]) {
                            gagne = false;
                        }
                    }
                    if (gagne) {
                        return true;
                    }
                }
            }
        }
        // Tester les diagonales inversées
        for (int i = 0; i < nbLignes - nbAlignes + 1; i++) {
            for (int j = nbColonnes - 1; j >= nbAlignes - 1; j--) {
                if (grille[i][j] != 0) {
                    boolean gagne = true;
                    for (int k = 1; k < nbAlignes; k++) {
                        if (grille[i][j] != grille[i + k][j - k]) {
                            gagne = false;
                        }
                    }
                    if (gagne) {
                        return true;
                    }
                }
            }
        }
        // Si on arrive ici, personne n'a gagné
        return false;
    }
    // Afficher la grille de jeu dans la console en couleur comme ca
    //   0 1 2 3 4 5 6
    // 0 . . . . . . .
    // 1 . . . . . . .
    // 2 . . . . . . .
    // 3 . . . . . . .
    // 4 . . . . . . .
    // 5 . . . . . . .
    public void afficherGrille() {
        // Afficher les numéros de colonnes
        System.out.print("  ");
        for (int i = 0; i < nbColonnes; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Afficher la grille
        for (int i = 0; i < nbLignes; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < nbColonnes; j++) {
                if (grille[i][j] == 0) {
                    System.out.print(". ");
                } else if (grille[i][j] == 1) {
                    System.out.print("\u001B[31mX\u001B[0m ");
                } else if (grille[i][j] == 2) {
                    System.out.print("\u001B[34mO\u001B[0m ");
                }
            }
            System.out.println();
        }
    }

    // Jouer un coup
    public void jouerCoup(int colonne) {
        // Trouver la ligne où jouer
        int ligne = nbLignes - 1;
        while (ligne >= 0 && grille[ligne][colonne] != 0) {
            ligne--;
        }
        // Si la colonne est pleine, on ne fait rien
        if (ligne < 0) {
            return;
        }
        // Jouer le coup
        grille[ligne][colonne] = joueur;
        // Afficher la grille
        afficherGrille();
        // Tester si le joueur a gagné
        if (gagnant()) {
            gagnant = joueur;
            fini = true;
        }
        // Passer au joueur suivant
        joueur = joueur % 2 + 1;
        // Incrémenter le nombre de coups joués
        nbCoups++;
        // Tester si la partie est finie
        if (nbCoups == nbCoupsMax) {
            fini = true;
        }
    }

    // Tester si la partie est finie
    public boolean fini() {
        return fini;
    }

    // Afficher le gagnant
    public void afficherGagnant() {
        if (gagnant == 0) {
            System.out.println("Match nul");
        } else {
            System.out.println("Le joueur " + gagnant + " a gagné");
        }
    }
    // Faire jouer un joueur humain
    public void jouerHumain() {
        Scanner input = new Scanner(System.in);
        // Tant que la partie n'est pas finie
        while (!fini()) {
            // Demander au joueur de jouer
            System.out.print("Joueur " + joueur + ", entrez une colonne : ");
            int colonne = input.nextInt();
            // Jouer le coup
            jouerCoup(colonne);
        }
        // Afficher le gagnant
        afficherGagnant();
    }

    // Faire jouer un ordinateur contre un humain
    public void jouerOrdi() {
        // Tant que la partie n'est pas finie
        while (!fini()) {
            // Si c'est au tour de l'ordinateur
            if (joueur == 2) {
                // Jouer un coup aléatoire
                Random random = new Random();
                int colonne = random.nextInt(nbColonnes);
                jouerCoup(colonne);
            } else {
                // Demander au joueur de jouer
                Scanner input = new Scanner(System.in);
                System.out.print("Joueur " + joueur + ", entrez une colonne : ");
                int colonne = input.nextInt();
                // Jouer le coup
                jouerCoup(colonne);
            }
        }
        // Afficher le gagnant
        afficherGagnant();
    }
    // Choisir le mode de jeu
    public void choisirMode() {
        Scanner input = new Scanner(System.in);
        System.out.print("Choisissez le mode de jeu (1 : humain, 2 : ordinateur) : ");
        int mode = input.nextInt();
        if (mode == 1) {
            afficherGrille();
            jouerHumain();
        } else {
            afficherGrille();
            jouerOrdi();
        }
    }

    // Methode jouer
    public void jouer() {
        choisirMode();
    }

}
