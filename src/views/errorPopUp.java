/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ashish
 */
public class errorPopUp {
    public void showErrorPopUp(String error){
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setBounds(50, 100, 400, 200);
        errorFrame.setLayout(new GridLayout(1,1));
        JLabel errorMessage = new JLabel("An Error has occured, Please retry, Error details = "+error);
        errorFrame.add(errorMessage);
        errorFrame.show(true);
    }
    
}
