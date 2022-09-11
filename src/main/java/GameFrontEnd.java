package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameFrontEnd {
    JFrame frame;
    JTextArea text;

    JLabel scoreBoard;
    JButton button1;

    JButton button2;

    JButton button3;

    JButton button4;

    public void initialView() {
        Font font = new Font("Serif", Font.PLAIN, 28);
        frame = new JFrame("Quiz Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);


        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        text = new JTextArea("Pick A category");
        text.setFont(font);
        text.setEditable(false);
        text.setLineWrap(true);
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 40;
        pane.add(text, c);

        ImageIcon geoIcon = new ImageIcon("Icons/Globe.jpeg");
        button1 = new JButton("Geography", geoIcon); // https://opentdb.com/api.php?amount=10&category=22&type=multiple
        button1.setForeground(Color.RED);
        button1.setFont(font);
        button1.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button1, c);

        ImageIcon compIcon = new ImageIcon("Icons/new_computer.png");
        button2 = new JButton("Computers", compIcon); // https://opentdb.com/api.php?amount=10&category=18&type=multiple
        button2.setForeground(Color.RED);
        button2.setFont(font);
        button2.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(button2, c);

        ImageIcon gameIcon = new ImageIcon("Icons/videoGames.jpeg");
        button3 = new JButton("Video Games", gameIcon); //https://opentdb.com/api.php?amount=10&category=15&type=multiple
        button3.setFont(font);
        button3.setForeground(Color.RED);
        button3.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(button3, c);

        ImageIcon sportIcon = new ImageIcon("Icons/SportsBalls.jpeg");
        button4 = new JButton("Sports", sportIcon); //https://opentdb.com/api.php?amount=10&category=21&type=multiple
        button4.setFont(font);
        button4.setForeground(Color.RED);
        button4.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(button4, c);


        frame.add(pane);
        frame.setVisible(true);
    }

    public void questionView(int points) {
        Font font = new Font("Serif", Font.PLAIN, 28);
        Font ansfont = new Font("Serif", Font.PLAIN, 20);
        frame = new JFrame("Quiz Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);

        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        scoreBoard = new JLabel("Score " + points + "/10");
        scoreBoard.setHorizontalTextPosition(SwingConstants.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(scoreBoard, c);

        text = new JTextArea();
        text.setSize(450, 300);
        text.setFont(font);
        text.setEditable(false);
        text.setLineWrap(true);
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 20;
        pane.add(text, c);

        button1 = new JButton();
        button1.setFont(ansfont);
        button1.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(button1, c);

        button2 = new JButton();
        button2.setFont(ansfont);
        button2.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(button2, c);

        button3 = new JButton();
        button3.setFont(ansfont);
        button3.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(button3, c);

        button4 = new JButton();
        button4.setFont(ansfont);
        button4.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        pane.add(button4, c);

        frame.add(pane);
        frame.setVisible(true);
    }

    public void changeQuestion(String question, int points, ArrayList<String> answers) {
        scoreBoard.setText("Score " + points + "/10");
        text.setText(question);
        button1.setText(answers.get(0));
        button2.setText(answers.get(1));
        button3.setText(answers.get(2));
        button4.setText(answers.get(3));
    }

    public void flash(Boolean correct) {
        if (correct) {
            Color green = new Color(0, 128, 0);
            text.setForeground(green);
        } else {
            Color red = new Color(255, 87, 51);
            text.setForeground(red);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void endPage(int points) {
        frame = new JFrame("Results:");
        Font font = new Font("Serif", Font.PLAIN, 28);
        text = new JTextArea("You got " + points + "/10 correct.");
        text.setFont(font);
        text.setEditable(false);
        text.setLineWrap(true);
        frame.add(text);
        frame.setVisible(true);
    }
    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }
}
