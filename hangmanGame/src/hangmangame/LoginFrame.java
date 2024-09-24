/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import hangman.Hangman;
import hangman.Hangman_Service;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel usernameLbl, passwordLbl;
    private JTextField username, password;
    private JButton clear, submit;
    private GameFrame next;
    private Hangman proxy;
    private Hangman_Service hm;
    private HangmanGame parent;
    
    public LoginFrame(HangmanGame pops) {
        parent = pops;
        setTitle("Hangman Login");
        setBounds(20,20,300,200);
        setLayout(new GridLayout(3,2));
        
        usernameLbl = new JLabel("Username :");
        passwordLbl = new JLabel("Password: ");
        username = new JTextField();
        password = new JTextField();
        clear = new JButton("Clear");
        clear.addActionListener(this);
        submit = new JButton("Submit");
        submit.addActionListener(this);
        
        add(usernameLbl);
        add(username);
        add(passwordLbl);
        add(password);
        add(clear);
        add(submit);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
                dispose();
            }
        };
        addWindowListener(exitListener);
        
        pops.setVisible(false);
        setVisible(true);
        hm = new Hangman_Service();
        proxy = hm.getHangmanPort();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == clear) {
            username.setText("");
            password.setText("");
        }
        
        if(source == submit) {
            String u = username.getText();
            String p = password.getText();
            
            int value = proxy.checkLogin(u, p);
            switch(value) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Invalid credentials.\nTry again.");
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
                    username.setText("");
                    password.setText("");
                    username.requestFocusInWindow();
                    next = new GameFrame(value, parent);
                    setVisible(false);
            }
        }
    }
}
