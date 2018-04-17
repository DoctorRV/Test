/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Ashish
 */
public class loadingScreen {
        public void drawUI(){
        JFrame loadingScreenFrame = new JFrame("Welcome");
        loadingScreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadingScreenFrame.setBounds(100, 100, 600, 200);
        //loadingScreenFrame.setBackground(Color.LIGHT_GRAY);
        //loadingScreenFrame.setForeground(Color.yellow);
        //loadingScreenFrame.setLayout(new GridLayout(4,2));
        loadingScreenFrame.setLayout(new BoxLayout(loadingScreenFrame.getContentPane(), BoxLayout.LINE_AXIS));
        JLabel welcomeMessage = new JLabel("Tic Tac Toe - Kahin Bhi Ho \t", SwingConstants.HORIZONTAL);
        //welcomeMessage.setSize(0, 0);
        //JLabel WelcomeImage = new JLabel ("");
        //WelcomeImage.setIcon(new ImageIcon(getClass().getResource("Tic_Tac_Toe.png")));
        //ImageIcon icon = new ImageIcon("Tic_Tac_Toe.png");
        //ImageIcon.
        //welcomeMessage.setIcon(Icon"src\\views\\Tic_Tac_Toe.png");
        //welcomeMessage.setAlignmentX(200);
        //welcomeMessage.setAlignmentY(100);
        //welcomeMessage.setBounds(100, 50, 100, 50);
        welcomeMessage.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.CYAN);
        
        signUpButton.setBounds(50, 80, 4, 2);
        JButton signInButton = new JButton("Sign In");
        signInButton.setBackground(Color.CYAN);
        loadingScreenFrame.add(welcomeMessage);
        loadingScreenFrame.add(signInButton);
        loadingScreenFrame.add(signUpButton);
        
        
        //Action Listener for clear button
        signInButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         signIn signin = new signIn();
                         signin.drawUI();
                         loadingScreenFrame.show(false);
                        } 
        } );
        
          //Action Listener for clear button
        signUpButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         signUp signup = new signUp();
                         signup.drawUI();
                         loadingScreenFrame.show(false);
                        } 
        } );
        
        loadingScreenFrame.show(true);
        }
     
}
