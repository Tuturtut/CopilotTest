// Classe morpion
// Auteur: M. Chabot
// Date: 2015-09-15
// Version: 1.0
// Description: Classe principale du jeu du morpion

import java.util.Random;
import java.util.Scanner;

// Declaration de la classe
public class Morpion {
    // Declaration des variables
    private int nbLignes;
    private int nbColonnes;
    private int nbAlignes;
    private int nbCoups;
    private int nbCoupsMax;
    private int[][] grille;
    private int joueur;
    private int gagnant;
    private boolean fini;

    // Constructeur
    public Morpion(int nbLignes, int nbColonnes, int nbAlignes) {
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

    // tester si quelqu'un a gagné
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
        return false;
    }
    // Faire l'action d'un joueur
    public void jouer(int ligne, int colonne) {
        if (grille[ligne][colonne] == 0) {
            modifierCase(ligne, colonne, joueur);
            nbCoups++;
            if (gagnant()) {
                gagnant = joueur;
                fini = true;
            } else if (nbCoups == nbCoupsMax) {
                fini = true;
            } else {
                joueur = joueur == 1 ? 2 : 1;
            }
        }
    }
    // Faire la partie
    public void jouer() {
        choixMode();
    }
    // faire jouer l'ordinateur
    public void jouerOrdi() {
        Scanner input = new Scanner(System.in);
        while (!fini) {
            afficherGrille();
            if (joueur == 1) {
                System.out.println("Joueur " + joueur + ", à vous de jouer!");
                System.out.print("Ligne: ");
                int ligne = input.nextInt();
                System.out.print("Colonne: ");
                int colonne = input.nextInt();
                jouer(ligne - 1, colonne - 1);
            } else {
                System.out.println("Joueur " + joueur + ", à vous de jouer!");
                Random rand = new Random();
                int ligne = rand.nextInt(nbLignes);
                int colonne = rand.nextInt(nbColonnes);
                while (grille[ligne][colonne] != 0) {
                    ligne = rand.nextInt(nbLignes);
                    colonne = rand.nextInt(nbColonnes);
                }
                System.out.println("Ligne: " + (ligne + 1));
                System.out.println("Colonne: " + (colonne + 1));
                jouer(ligne, colonne);
            }
        }
        afficherGrille();
        if (gagnant == 0) {
            System.out.println("Match nul!");
        } else {
            System.out.println("Le joueur " + gagnant + " a gagné!");
        }
    }
    // Faire le choix du mode de jeu
    public void choixMode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez le mode de jeu:");
        System.out.println("1. Joueur contre joueur");
        System.out.println("2. Joueur contre ordinateur");
        int choix = input.nextInt();
        if (choix == 1) {
            jouer();
        } else {
            jouerOrdi();
        }
    }
    // Afficher la grille avec symbole et couleur qui ressemble a ca
    //       |   |
    //    -----------
    //       |   |
    //    -----------
    //       |   |
    public void afficherGrille() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (grille[i][j] == 0) {
                    System.out.print("   ");
                } else if (grille[i][j] == 1) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" O ");
                }
                if (j < nbColonnes - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < nbLignes - 1) {
                for (int j = 0; j < nbColonnes; j++) {
                    System.out.print("---");
                    if (j < nbColonnes - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }

}