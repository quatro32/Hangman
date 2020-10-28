/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;

import ActionListeners.TimerListener;
import ActionListeners.UserNotifier;
import Classes.Game;
import Enums.Enums.GameState;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Interfaces.Timeable;

/**
 *
 * @author shnva
 */
public class GameUI extends javax.swing.JFrame {

    private final Classes.Game game;
    private Timer timer;
    private Component currentComponent;
    private final UserNotifier userNotifier;

    public GameUI(Game game) {
        initComponents();
        this.game = game;
        this.currentComponent = this;
        //initializing a custom event listener, so the logic layer can invoke these ui-related methods
        this.userNotifier = new UserNotifier() {
            @Override
            public String getUserInput(String message) {
                return JOptionPane.showInputDialog(message);
            }
            
            @Override
            public void notifyUser(String message) {
                JOptionPane.showMessageDialog(currentComponent, message);
            }

            @Override
            public void showHighscores(List<String> highscores) {
                dispose();
                new Highscores(highscores).setVisible(true);
            }

            @Override
            public void closeGame() {
                currentComponent.setVisible(false);
            }
            
            @Override
            public void drawTimer(String text) {
                lblTimer.setText(text);
            }
        };

        //if difficulty is easy, show hint button
        if (!game.getCurrentDifficulty().hasHintAvailable()) {
            btnGetHint.setVisible(false);
        }
        
        if(game.getCurrentDifficulty() instanceof Timeable)
        {
            Timeable timeableDifficulty = (Timeable)this.game.getCurrentDifficulty();
            lblTimer.setVisible(true);
            timeableDifficulty.setTimer(game, userNotifier);
        }


        //BufferedImage image = ImageIO.read(new File("C:\\Users\\shnva\\Documents\\Hangman\\maxresdefault.jpg"));
        //draw all dashes for the current word
        drawUI(false);
    }
    
    private void drawUI(boolean redraw) {
        //Draw all (un)guessed characters according to already guessed characters
        panelGuessedLetters.removeAll();
        for (Character c : this.game.getPlayingWord().getWordText().toCharArray()) {
            JLabel l = new JLabel(this.game.getGuessedCharacters().contains(c) ? c.toString() : "_");
            l.setFont(new java.awt.Font("Arial Black", 1, 20));
            panelGuessedLetters.add(l);
            panelGuessedLetters.validate();
            panelGuessedLetters.repaint();
        }

        if (this.game.getAmountOfTries() < 9) {
            jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\shnva\\Documents\\Hangman\\hangman_" + this.game.getAmountOfTries() + ".png"));
        }

        if (redraw) {
            //Empty and redraw wrongly guessed characters
            panelWrongLetters.removeAll();
            for (Character c : this.game.getWrongCharacters()) {
                JLabel l = new JLabel(c.toString().toUpperCase());
                l.setFont(new java.awt.Font("Arial Black", 1, 36));
                panelWrongLetters.add(l);
                panelWrongLetters.validate();
                panelWrongLetters.repaint();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        txtGuessField = new javax.swing.JTextField();
        btnGuessLetter = new javax.swing.JButton();
        btnGuessWord = new javax.swing.JButton();
        panelWrongLetters = new javax.swing.JPanel();
        panelGuessedLetters = new javax.swing.JPanel();
        btnGetHint = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        btnQuitGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(214, 217, 224));

        jLabel1.setText("Vul de letters of het woord in dat je wilt raden:");

        txtGuessField.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        btnGuessLetter.setText("Raad letter");
        btnGuessLetter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuessLetterActionPerformed(evt);
            }
        });

        btnGuessWord.setText("Raad woord");
        btnGuessWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuessWordActionPerformed(evt);
            }
        });

        panelWrongLetters.setBackground(new java.awt.Color(204, 204, 204));
        panelWrongLetters.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelWrongLetters.setToolTipText("");
        panelWrongLetters.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelWrongLetters.setLayout(new java.awt.GridLayout(10, 4));

        panelGuessedLetters.setLayout(new java.awt.GridLayout(1, 30));

        btnGetHint.setText("Hint");
        btnGetHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetHintActionPerformed(evt);
            }
        });

        lblTimer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTimer.setText("00:00:00");

        btnQuitGame.setText("Stop spel");
        btnQuitGame.setToolTipText("");
        btnQuitGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGuessField, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuessLetter)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuessWord)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuitGame)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGetHint, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTimer)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(panelGuessedLetters, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelWrongLetters, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelWrongLetters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelGuessedLetters, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblTimer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btnGetHint, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtGuessField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuessWord)
                    .addComponent(btnGuessLetter)
                    .addComponent(btnQuitGame))
                .addContainerGap())
        );

        lblTimer.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuessLetterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuessLetterActionPerformed
        String input = txtGuessField.getText();
        if (input.length() == 1) {
            this.game.guessLetter(input.charAt(0), userNotifier);
        } else {
            JOptionPane.showMessageDialog(this, "Er is meer dan één letter ingevoerd!");
        }
        
        txtGuessField.setText("");
        drawUI(true);

        this.game.askQuizQuestion(userNotifier);
        
        drawUI(true);
    }//GEN-LAST:event_btnGuessLetterActionPerformed

    private void btnGuessWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuessWordActionPerformed
        // TODO add your handling code here:
        String input = txtGuessField.getText();
        if(!input.equals("")) {
            this.game.guessWord(txtGuessField.getText(), userNotifier);
        } else {
             JOptionPane.showMessageDialog(this, "Er is niets ingevoerd!");
             txtGuessField.setText("");
        }
    }//GEN-LAST:event_btnGuessWordActionPerformed

    private void btnGetHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetHintActionPerformed
        this.game.getHint(userNotifier);
        btnGetHint.setVisible(false);
        drawUI(true);
    }//GEN-LAST:event_btnGetHintActionPerformed

    private void btnQuitGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitGameActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(this, "Dit spel stoppen?");
        if(dialogResult == JOptionPane.YES_OPTION) {
            //close the game
            currentComponent.setVisible(false);
            this.game.quitGame();
        }
    }//GEN-LAST:event_btnQuitGameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetHint;
    private javax.swing.JButton btnGuessLetter;
    private javax.swing.JButton btnGuessWord;
    private javax.swing.JButton btnQuitGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JPanel panelGuessedLetters;
    private javax.swing.JPanel panelWrongLetters;
    private javax.swing.JTextField txtGuessField;
    // End of variables declaration//GEN-END:variables
}
