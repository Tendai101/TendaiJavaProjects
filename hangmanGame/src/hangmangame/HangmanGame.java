package hangmangame;

import hangman.Hangman;
import hangman.Hangman_Service;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HangmanGame extends JFrame implements ActionListener {

    private Hangman proxy;
    private Hangman_Service hm;
    private JButton login;
    private JButton register;
    
    public HangmanGame() {
        hm = new Hangman_Service();
        proxy = hm.getHangmanPort();
        
        setTitle("My New Hangman Game");
        setBounds(20,20,320,240);
        setLayout(new GridLayout(2,1));
        JPanel banner = new JPanel();
        banner.setLayout(new GridLayout(2,1));
        JLabel myLabel = new JLabel("Welcome to Hangman");
        JLabel instructions = new JLabel("Select one of the options below to proceed.");
        banner.add(myLabel);
        banner.add(instructions);
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(1,2));
        login = new JButton("Login");
        login.addActionListener(this);
        
        register = new JButton("Register");
        register.addActionListener(this);
        
        menu.add(login);
        menu.add(register);
        
        add(banner);
        add(menu);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public Hangman getProxy() {
        return proxy;
    }
    
    public static void main(String[] args) {
        HangmanGame myGame = new HangmanGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object source = e.getSource();
            if(source == login) {
                //JOptionPane.showMessageDialog(null, "Login");
                LoginFrame myLogin = new LoginFrame(this);
            }
            if(source == register) {
                RegisterFrame myRegister = new RegisterFrame(this);
            }
        } catch(Exception ee) {
            System.err.println(ee.getMessage());
        }
    }
    
}
