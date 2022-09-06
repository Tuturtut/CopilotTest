public class Sudoku {
    // Attributs
    private int[][] grille;
    private int nbLignes;
    private int nbColonnes;
    private int nbCases;
    private int nbCasesVides;
    private int nbCasesRemplies;
    private int nbCasesRempliesInit;
    private int nbCasesVidesInit;
    private int nbCasesRempliesMax;
    private int nbCasesRempliesMin;
    private int nbCasesVidesMax;
    private int nbCasesVidesMin;

    // Constructeur
    public Sudoku(int nbLignes, int nbColonnes, int nbCases) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbCases = nbCases;
        this.grille = new int[nbLignes][nbColonnes];
        this.nbCasesVides = nbLignes * nbColonnes;
        this.nbCasesRemplies = 0;
        this.nbCasesRempliesInit = 0;
        this.nbCasesVidesInit = nbLignes * nbColonnes;
        this.nbCasesRempliesMax = 0;
        this.nbCasesRempliesMin = nbLignes * nbColonnes;
        this.nbCasesVidesMax = 0;
        this.nbCasesVidesMin = nbLignes * nbColonnes;
    }

    // Modifier une case
    public void modifierCase(int ligne, int colonne, int valeur) {
        grille[ligne][colonne] = valeur;
    }

    // Afficher la grille
    public void afficherGrille() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Tester si la grille est remplie
    public boolean grilleRemplie() {
        return nbCasesRemplies == nbCases;
    }

    // Tester si la grille est vide
    public boolean grilleVide() {
        return nbCasesVides == nbCases;
    }

    // Tester si la grille est valide
    public boolean grilleValide() {
        // Tester les lignes
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes - nbCases + 1; j++) {
                if (grille[i][j] != 0) {
                    boolean valide = true;
                    for (int k = 1; k < nbCases; k++) {
                        if (grille[i][j] != grille[i][j + k]) {
                            valide = false;
                        }
                    }
                    if (valide) {
                        return true;
                    }
                }
            }
        }

        // Tester les colonnes
        for (int i = 0; i < nbColonnes; i++) {
            for (int j = 0; j < nbLignes - nbCases + 1; j++) {
                if (grille[j][i] != 0) {
                    boolean valide = true;
                    for (int k = 1; k < nbCases; k++) {
                        if (grille[j][i] != grille[j + k][i]) {
                            valide = false;
                        }
                    }
                    if (valide) {
                        return true;
                    }
                }
            }
        }

        // Tester les carrés
        for (int i = 0; i < nbLignes - nbCases + 1; i++) {
            for (int j = 0; j < nbColonnes - nbCases + 1; j++) {
                if (grille[i][j] != 0) {
                    boolean valide = true;
                    for (int k = 1; k < nbCases; k++) {
                        if (grille[i][j] != grille[i + k][j + k]) {
                            valide = false;
                        }
                    }
                    if (valide) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Poser une case
    public void poserCase(int ligne, int colonne, int valeur) {
        if (grille[ligne][colonne] == 0) {
            grille[ligne][colonne] = valeur;
            nbCasesVides--;
            nbCasesRemplies++;
        }
    }
    // Retirer une case
    public void retirerCase(int ligne, int colonne) {
        if (grille[ligne][colonne] != 0) {
            grille[ligne][colonne] = 0;
            nbCasesVides++;
            nbCasesRemplies--;
        }
    }
    // Tester si la case est vide
    public boolean caseVide(int ligne, int colonne) {
        return grille[ligne][colonne] == 0;
    }
    // Tester si la case est remplie
    public boolean caseRemplie(int ligne, int colonne) {
        return grille[ligne][colonne] != 0;
    }
    // Jouer
    public void jouer() {
        // Initialiser la grille
        initialiserGrille();

        // Afficher la grille
        afficherGrille();

        // Jouer
        while (!grilleRemplie() && !grilleValide()) {
            // Choisir une case
            int ligne = (int) (Math.random() * nbLignes);
            int colonne = (int) (Math.random() * nbColonnes);

            // Choisir une valeur
            int valeur = (int) (Math.random() * nbCases) + 1;

            // Poser la case
            poserCase(ligne, colonne, valeur);

            // Afficher la grille
            afficherGrille();
        }
    }

    // Initialiser la grille
    public void initialiserGrille() {
        // Initialiser les cases
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                grille[i][j] = 0;
            }
        }

        // Initialiser les compteurs
        nbCasesVides = nbLignes * nbColonnes;
        nbCasesRemplies = 0;
    }
}
