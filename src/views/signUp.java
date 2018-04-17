/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import tictactoejava.controller.jamesWebService;

/**
 *
 * @author Ashish
 */
public class signUp {
        public void drawUI(){
        jamesWebService james = new jamesWebService();
        errorPopUp errorUI = new errorPopUp();
        JFrame signInFrame = new JFrame("Sign In");
        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signInFrame.setBounds(50, 100, 800, 200);
        signInFrame.setLayout(new GridLayout(2,1));
        JButton signInButton = new JButton ("Sign In");
        signInButton.setBackground(Color.CYAN);
        JButton clear = new JButton("Clear");
        clear.setBackground(Color.CYAN);
        JLabel needANewAccount = new JLabel("Already have an account?");
        JButton signUpButton = new JButton("Sign Up ");
        signUpButton.setBackground(Color.CYAN);
        JTextField username = new JTextField("Username");
        username.setBackground(Color.LIGHT_GRAY);
        JTextField password = new JTextField("Password");
        password.setBackground(Color.LIGHT_GRAY);
        JTextField firstname = new JTextField("First Name");
        firstname.setBackground(Color.LIGHT_GRAY);
        JTextField surname = new JTextField("surname");
        surname.setBackground(Color.LIGHT_GRAY);
        signInFrame.add(firstname);
        signInFrame.add(surname);
        signInFrame.add(username);
        signInFrame.add(password);
        signInFrame.add(signUpButton);
        signInFrame.add(clear);
        signInFrame.add(needANewAccount);
        signInFrame.add(signInButton);

        //Action Listener for clear button
        clear.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         username.setText(" ");
                         password.setText(" ");
                         firstname.setText(" ");
                         surname.setText(" ");
                        } 
        } );
        
        //Action Listener for signIn button
        signInButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                           System.out.println("Sign in button pressed");
                           signIn signin = new signIn();
                           signInFrame.show(false);
                           signin.drawUI();
                        } 
        } );
        
        //Action Listener for signUp button
        signUpButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                           System.out.println("Sign up button pressed");
                            String result = james.register(username.getText(), password.getText(), firstname.getText(), surname.getText());
                            if(result.equals("ERROR-REPEAT") | result.equals("ERROR-INSERT") | result.equals("ERROR-RETRIEVE") | result.equals("ERROR-DB")){
                               errorUI.showErrorPopUp(result);
                            } else {
                               openGameList opengame = new openGameList();
                               opengame.setPlayerId(Integer.parseInt(result),username.getText());
                               opengame.drawUI();
                               signInFrame.show(false);
                            }
                        } 
        } );
        
        
        signInFrame.show(true);
    }
}
