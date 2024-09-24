/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import hangman.Hangman;
import hangman.Hangman_Service;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author James
 */
public class RegisterFrame extends JFrame implements ActionListener {

    private HangmanGame parent;
    private Hangman proxy;
    private Hangman_Service hm;
    private JTextField name,surname,username,password,email;
    private JLabel nameLbl, surnameLbl, usernameLbl, passwordLbl, emailLbl;
    private JButton submit, clear;
    private GameFrame game;
    
    public RegisterFrame(HangmanGame pops) {
        parent = pops;
        hm = new Hangman_Service();
        proxy = hm.getHangmanPort();
        setTitle("HagmanGame Registation");
        setBounds(20,20,300,450);
        setLayout(new GridLayout(6,2));
        
        nameLbl = new JLabel("Name:");
        name = new JTextField();
        add(nameLbl);
        add(name);
        
        surnameLbl = new JLabel("Surname:");
        surname = new JTextField();
        add(surnameLbl);
        add(surname);
        
        usernameLbl = new JLabel("Username:");
        username = new JTextField();
        add(usernameLbl);
        add(username);
        
        passwordLbl = new JLabel("Password:");
        password = new JTextField();
        add(passwordLbl);
        add(password);
        
        emailLbl = new JLabel("Email:");
        email = new JTextField();
        add(emailLbl);
        add(email);
        
        clear = new JButton("Clear");
        clear.addActionListener(this);
        add(clear);
        
        submit = new JButton("Submit");
        submit.addActionListener(this);
        add(submit);
        
        //pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
                dispose();
            }
        };
        addWindowListener(exitListener);
        
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == clear) {
            username.setText("");
            password.setText("");
        }
        
        if(source == submit) {
            String n = name.getText();
            String s = surname.getText();
            String u = username.getText();
            String p = password.getText();
            String eml = email.getText();
            
            int value = proxy.registerUser(n, s, u, p, eml);
            switch(value) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Unable to add credentials.\nTry again.");
                    username.setText("");
                    password.setText("");
                    username.requestFocusInWindow();
                break;
                
                case -1:
                    JOptionPane.showMessageDialog(null, "Problem with database.\nTry again later");
                    username.setText("");
                    password.setText("");
                    username.requestFocusInWindow();
                break;
                
                default:
                    name.setText("");
                    surname.setText("");
                    email.setText("");
                    username.setText("");
                    password.setText("");
                    username.requestFocusInWindow();
                    game = new GameFrame(value, parent);
                    setVisible(false);
            }
        }
    }
}
