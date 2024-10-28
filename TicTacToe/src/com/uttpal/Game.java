package com.uttpal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Game implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean UttpalTurn;

    Game() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("FirstProject");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        UttpalTurn();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText().equals("")) {
                buttons[i].setText(UttpalTurn ? "O" : "X");
                buttons[i].setEnabled(false);
                buttons[i].setForeground(UttpalTurn ? Color.RED : Color.BLUE);

                UttpalTurn = !UttpalTurn;
                textfield.setText(UttpalTurn ? "Uttpal's turn" : "Vikas' turn");

                check();
                break;
            }
        }
    }

    public void UttpalTurn() {
        UttpalTurn = random.nextInt(2) == 0;
        textfield.setText(UttpalTurn ? "Uttpal's turn" : "Vikas' turn");
    }

    public void check() {
        String[][] winPatterns = {
                {"0", "1", "2"}, {"3", "4", "5"}, {"6", "7", "8"},
                {"0", "3", "6"}, {"1", "4", "7"}, {"2", "5", "8"},
                {"0", "4", "8"}, {"2", "4", "6"}
        };

        for (String[] pattern : winPatterns) {
            if (buttons[Integer.parseInt(pattern[0])].getText().equals("X") &&
                    buttons[Integer.parseInt(pattern[1])].getText().equals("X") &&
                    buttons[Integer.parseInt(pattern[2])].getText().equals("X")) {
                UttpalWins(Integer.parseInt(pattern[0]), Integer.parseInt(pattern[1]), Integer.parseInt(pattern[2]));
                return;
            }
            if (buttons[Integer.parseInt(pattern[0])].getText().equals("O") &&
                    buttons[Integer.parseInt(pattern[1])].getText().equals("O") &&
                    buttons[Integer.parseInt(pattern[2])].getText().equals("O")) {
                VikasWins(Integer.parseInt(pattern[0]), Integer.parseInt(pattern[1]), Integer.parseInt(pattern[2]));
                return;
            }
        }

        if (reStart()) {
            textfield.setText("Game over!");
        }
    }

    public void UttpalWins(int a, int b, int c) {
        highlightWinningButtons(a, b, c);
        textfield.setText("Hurry!😅🎇! Uttpal Wins");
    }

    public void VikasWins(int a, int b, int c) {
        highlightWinningButtons(a, b, c);
        textfield.setText("OH✌️✌️ Vikas Won");
    }

    public void highlightWinningButtons(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    boolean reStart() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

}
