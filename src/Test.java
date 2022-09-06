public class Test {

    // Objet de la classe Morpion
    public static void main(String[] args) {
        // Créer un objet de la classe Morpion
        Morpion morpion = new Morpion(3, 3, 3);

        Power4 power4 = new Power4(6, 7, 4);

        Sudoku sudoku = new Sudoku(9, 9, 3);
        sudoku.jouer();
        }

        // Fonction qui fais la somme de deux nombres
        public static int somme(int a, int b) {
            return a + b;
        }

}
