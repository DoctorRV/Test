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
public class signIn {
    public void drawUI(){
        jamesWebService james = new jamesWebService();
        errorPopUp errorpage = new errorPopUp();
        JFrame signInFrame = new JFrame("Sign In");
        signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signInFrame.setBounds(50, 100, 400, 200);
        signInFrame.setLayout(new GridLayout(3,1));
        JButton signInButton = new JButton ("Sign In");
        signInButton.setBackground(Color.CYAN);
        JButton clear = new JButton("Clear");
        clear.setBackground(Color.CYAN);
        JLabel needANewAccount = new JLabel("Need a new account?");
        needANewAccount.setBackground(Color.CYAN);
        JButton signUpButton = new JButton("Sign Up ");
        signUpButton.setBackground(Color.CYAN);
        JTextField username = new JTextField("Username");
        username.setBackground(Color.LIGHT_GRAY);
        JTextField password = new JTextField("Password");
        password.setBackground(Color.LIGHT_GRAY);
        signInFrame.add(username);
        signInFrame.add(password);
        signInFrame.add(signInButton);
        signInFrame.add(clear);
        signInFrame.add(needANewAccount);
        signInFrame.add(signUpButton);
        
        //Action Listener for clear button
        clear.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         username.setText(" ");
                         password.setText(" ");
                        } 
        } );
        
        //Action Listener for signIn button
        signInButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                           System.out.println("Sign in button pressed");
                           int result = james.signin(username.getText(), password.getText());
                           if(result <= 0){
                               errorpage.showErrorPopUp("Error");
                               System.out.println(result);
                           } else{
                               System.out.println(result);
                               openGameList opengame = new openGameList();
                               opengame.setPlayerId(result,username.getText());
                               opengame.drawUI();
                               signInFrame.show(false);
                           }
                         
                        } 
        } );
        
        //Action Listener for signUp button
        signUpButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                           System.out.println("Sign up button pressed");
                            signUp signup = new signUp();
                           signInFrame.show(false);
                           signup.drawUI();
                        } 
        } );
        
        
        signInFrame.show(true);
    }
}
