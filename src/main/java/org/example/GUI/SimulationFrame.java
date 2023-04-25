package org.example.GUI;

import org.example.BusinessLogic.SimulationManager;
import org.example.Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame {
    private JButton startButton;
    private JButton submitButton;
    private JButton rezultatButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JPanel Auchan;
    private JTextField textField4;
    private JTextArea textArea1;

    public SimulationFrame(SimulationManager m)
    {
        this.startButton.setFont(new Font("Arial", Font.PLAIN, 35));
        JFrame frame = new JFrame("Auchan");
        frame.setContentPane(Auchan);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(750, 550);
        Controller c = new Controller();
        startButton.addActionListener(c.ButtonStart(m));







    }

    public JButton getRezultatButton() {
        return rezultatButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JPanel getBila() {
        return Auchan;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

}
