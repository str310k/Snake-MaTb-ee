package org.example;

import javax.swing.*;



public class MainWindow extends JFrame {

    public MainWindow() {

        setTitle("змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(565, 540);
        setLocation(500, 250);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow MW = new MainWindow();
    }
}