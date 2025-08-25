import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private int numberToGuess; // Nombre à deviner
    private int attemptsLeft = 5; // Nombre d'essais restants
    private JTextField inputField; // Champ de texte
    private JButton guessButton; // Bouton pour valider
    private JLabel messageLabel; // Zone d'affichage du texte

    public GamePanel() {
        setLayout(new BorderLayout());
        /*
                ▶️ Layout manager
                Définit un BorderLayout → un gestionnaire d’emplacement en 5 zones : NORTH, SOUTH, EAST, WEST, CENTER.
                Cela organise visuellement les composants dans le panneau.
        */

        numberToGuess = (int)(Math.random() * 100 + 1);
        /*
                Math.random() ∈ [0, 1[
                Multiplié par 100 → [0, 100[
                +1 → [1, 100] (entier entre 1 et 100 inclus)
                Cast explicite (int) pour obtenir un entier
         */

        messageLabel = new JLabel("Devine un nombre entre 1 et 100 (5 essais)", SwingConstants.CENTER);
        /*
                JLabel : zone de texte affichée à l’écran
                SwingConstants.CENTER : centre le texte horizontalement
         */

        // Initialisation 'Champ de texte'
        inputField = new JTextField();

        // Initialisation  'Bouton'
        guessButton = new JButton("Deviner");

        guessButton.addActionListener(new GuessHandler());
        /*
                ▶️ Ajout d’un écouteur d’événement
                Associe un ActionListener au bouton
                Lors du clic, GuessHandler.actionPerformed sera appelé
         */

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(guessButton, BorderLayout.EAST);
            /*
                    ▶️ Sous-panneau pour regrouper le champ + le bouton
                    inputPanel est une zone contenant :
                    inputField au centre
                    guessButton à droite (East)
             */

        add(messageLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        /*
                ▶️ Ajout des composants au panneau principal
                messageLabel en haut
                inputPanel (champ + bouton) au centre
         */
    }

    private class GuessHandler implements ActionListener { // Classe interne privé | GuessHandler est un écouteur d’événements
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(inputField.getText());
                attemptsLeft--;

                if (guess == numberToGuess) {
                    messageLabel.setText("Bravo ! Tu as trouvé !");
                    guessButton.setEnabled(false);
                } else if (attemptsLeft == 0) {
                    messageLabel.setText("Perdu ! Le nombre était : " + numberToGuess);
                    guessButton.setEnabled(false);
                } else if (guess < numberToGuess) {
                    messageLabel.setText("Trop petit ! Essais restants : " + attemptsLeft);
                } else {
                    messageLabel.setText("Trop grand ! Essais restants : " + attemptsLeft);
                }

                inputField.setText("");
            } catch (NumberFormatException ex) {
                messageLabel.setText("Entre un nombre valide !");
            }
        }
    }
}