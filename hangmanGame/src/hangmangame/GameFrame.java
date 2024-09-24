/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import hangman.Hangman;
import hangman.Hangman_Service;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author James
 */
public class GameFrame extends JFrame implements ActionListener {
    private Hangman_Service hm;
    private Hangman proxy;
    private JFrame parent;
    private int pid, gid, counter, wordSize, correct;
    String player, myWord;
    String[] theLetters;
    private JLabel pic;
    private JLabel[] letters;
    private JButton[] buttons;
    private JTextField entry;
    private JButton guess;
    private BufferedImage current;
    JPanel letterGrid, entrySection;
    
    public GameFrame(int p, JFrame pops) {
        hm = new Hangman_Service();
        proxy = hm.getHangmanPort();
        
        parent = pops;
        
        pid = p;
        gid = proxy.newGame(pid);
        myWord = proxy.getWord(gid);
        theLetters = myWord.split("");
        wordSize = theLetters.length;
        player = proxy.getUsername(pid);
        counter = 8;
        
        setTitle("Hangman - Welcome " + player);
        getContentPane().setBackground(Color.WHITE);
        setBounds(20,20,800,800);
        setLayout(new GridLayout(3,1));
        pic = new JLabel();
        pic.setPreferredSize(new Dimension(400,400));
        pic.setHorizontalAlignment(JLabel.CENTER);
        Image dimg = null;
        try {
            String path = counter + ".png";
            ClassLoader classLoader = getClass().getClassLoader();
            File picFile = new File(classLoader.getResource(path).getFile());
            current = ImageIO.read(picFile);
            dimg = current.getScaledInstance(362, 268, Image.SCALE_SMOOTH);
        } catch(IOException io) {
            System.out.println(io.getMessage());
        }
        pic.setIcon(new ImageIcon(dimg));
        add(pic);
        
        letters = new JLabel[theLetters.length];
        letterGrid = new JPanel();
        letterGrid.setBackground(Color.WHITE);
        letterGrid.setLayout(new GridLayout(1,letters.length));
        for(int i=0;i<letters.length;i++) {
            letters[i] = new JLabel("_", JLabel.CENTER);
            letters[i].setPreferredSize(new Dimension(50,50));
            letters[i].setFont(new Font("Serif", Font.BOLD, 32));
            letterGrid.add(letters[i]);
        }
        add(letterGrid);
        
        entrySection = new JPanel();
        entrySection.setLayout(new GridLayout(2,13));
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String[] letterBits = alphabet.split("");
        buttons = new JButton[26];
        for(int i=0;i<2;i++) {
            for(int j=0;j<13;j++) {
                int pos = (i*13) + j;
                buttons[pos] = new JButton(letterBits[pos]);
                buttons[pos].setBackground(Color.WHITE);
                buttons[pos].addActionListener(this);
                entrySection.add(buttons[pos]);
            }
        }
        add(entrySection);
        
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

    public void restart() {
        gid = proxy.newGame(pid);
        myWord = proxy.getWord(gid);
        theLetters = myWord.split("");
        wordSize = theLetters.length;
        player = proxy.getUsername(pid);
        counter = 8;
        redraw(counter);
        
        letters = null;
        letters = new JLabel[theLetters.length];
        letterGrid.removeAll();
        letterGrid.setLayout(new GridLayout(1,letters.length));
        for(int i=0;i<letters.length;i++) {
            letters[i] = new JLabel("_", JLabel.CENTER);
            letters[i].setPreferredSize(new Dimension(50,50));
            letters[i].setFont(new Font("Serif", Font.BOLD, 32));
            letterGrid.add(letters[i]);
        }
        letterGrid.revalidate();
        
        for(int i=0;i<26;i++) {
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setEnabled(true);
        }
        entrySection.revalidate();        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        for(int i=0;i<26;i++) {
            if(source == buttons[i]){
                if(playGuess(buttons[i].getText())) {
                    buttons[i].setEnabled(false);
                    buttons[i].setBackground(Color.GREEN);
                
                    if(correct == wordSize) {
                        if(JOptionPane.showConfirmDialog(null, "Congratulations! You win!!\nPlay Again(Y/N)") == 0) {
                            restart();
                        } else {
                            parent.setVisible(true);
                            dispose();
                        }
                    }   
                } else {
                    buttons[i].setEnabled(false);
                    buttons[i].setBackground(Color.GREEN);                
                }
                if(counter <= 0) {
                    if(JOptionPane.showConfirmDialog(null, "Game over - you lose\n The word was " + myWord + "\nPlay Again(Y/N)") == 0) {
                        restart();
                    } else {
                        dispose();
                    }
                }
            }
        }
    }
    
    public boolean playGuess(String choice) {
        boolean correctPick = false;
        if(counter > 0) {
            String letter = choice;
            String pos = proxy.addGuess(letter, pid, gid);
            if(pos.length() > 0) {
                String[] poses = pos.split(",");
                correct += poses.length;
                int[] indices = new int[poses.length];
                for(int i=0;i<poses.length;i++) {
                    indices[i] = Integer.parseInt(poses[i]);
                }
                for(int j=0;j<indices.length;j++) {
                    letters[indices[j]].setText(letter);
                }
                
                correctPick = true;
            } else {
                counter--;
                redraw(counter);
            }
        } 
        return correctPick;
    }
    
    public void redraw(int p) {
        Image dimg = null;
        try {
            String path = p + ".png";
            ClassLoader classLoader = getClass().getClassLoader();
            File picFile = new File(classLoader.getResource(path).getFile());
            current = ImageIO.read(picFile);
            dimg = current.getScaledInstance(362, 268, Image.SCALE_SMOOTH);
        } catch(IOException io) {
            System.out.println(io.getMessage());
        }
        pic.setIcon(new ImageIcon(dimg));
        pic.repaint();
    }
}
