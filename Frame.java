package com.client;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Frame {
    private Client client;

    public Frame() {
       Client client=new Client("localhost", 1813 );
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Chat Programme");

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JScrollPane JScrollpane = new JScrollPane();
        JTextField textField = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JMenuBar mb = new JMenuBar();
        frame.add(new JScrollPane(), BorderLayout.CENTER);
        panel.add(label); // Components Added using Flow Layout
        panel.add(textField);
        panel.add(send);
        frame.setLocationRelativeTo(null);

        // Text Area at the Center
        JTextArea textArea = new JTextArea();

        //Adding Components to the frame.

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, textArea);
        frame.setVisible(true);
    }
   public void printToConsole()
   {
     // textArea.setText(JTextArea.getText()+ message +"\n");
}

}
