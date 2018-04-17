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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import tictactoejava.controller.jamesWebService;

/**
 *
 * @author Ashish
 */
public class gamePlayScreen {
    private errorPopUp errorUI = new errorPopUp();
    private int gid;
    private int pid;
    private int alreadyShowingWait = 0;
    private int alreadyShowingGameEnd = 0;
    private int hasTheSecondPlayerJoinedYet = 0;
    private int isGameInProgress  = 0;
    private int isTheTextBeingDisaplyed = 0;
    private jamesWebService james = new jamesWebService();
    private JFrame waiting = new JFrame("Waiting");
    private JFrame gamePlayFrame = new JFrame("Your score list");
    private JFrame gameEnd = new JFrame("The Game has ended");
    private JFrame waitingForSecondPlayerToJoin = new JFrame("Waiting for second Player");
    private JButton oneone = new JButton();
    private JButton onetwo = new JButton();
    private JButton onethree = new JButton();
    private JButton twoone = new JButton();
    private JButton twotwo = new JButton();
    private JButton twothree = new JButton();
    private JButton threeone = new JButton();
    private JButton threetwo = new JButton();
    private JButton threethree = new JButton();
        
       public void drawUI(int gID,int pID){
         gid = gID;
         pid = pID;
         System.out.println("Game ID of the selected game is"+gID);
        gamePlayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gamePlayFrame.setBounds(50, 100, 400, 400);
        gamePlayFrame.setLayout(new GridLayout(3,3));
       
        JButton clickMe = new JButton("Click me");
        gamePlayFrame.add(oneone);
        gamePlayFrame.add(onetwo);
        gamePlayFrame.add(onethree);
        gamePlayFrame.add(twoone);
        gamePlayFrame.add(twotwo);
        gamePlayFrame.add(twothree);
        gamePlayFrame.add(threeone);
        gamePlayFrame.add(threetwo);
        gamePlayFrame.add(threethree);
        
        oneone.setBackground(Color.CYAN);
        onetwo.setBackground(Color.CYAN);
        onethree.setBackground(Color.CYAN);
        twoone.setBackground(Color.CYAN);
        twotwo.setBackground(Color.CYAN);
        twothree.setBackground(Color.CYAN);
        threeone.setBackground(Color.CYAN);
        threetwo.setBackground(Color.CYAN);
        threethree.setBackground(Color.CYAN);
        
        //Code for waiting frame
        waiting.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         waiting.setBounds(50, 100, 400, 200);
         waiting.setLayout(new GridLayout(1,1));
         JLabel errorMessage = new JLabel("Waiting for the other player to take a move");
         waiting.add(errorMessage);
        checkTurn();
        
        //Code for game ending.
        gameEnd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         gameEnd.setBounds(50, 100, 400, 200);
         gameEnd.setLayout(new GridLayout(1,1));
         
        waitingForSecondPlayerFrameManager();
        checkTurn();
        //timeFunction();
        //Action Listener for yourscore button
        oneone.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("11 clicked");
                 int result = takeASquare(0,0);
                 System.out.println("Square taken"+result);
                 timeFunction();
                        } 
        } );
        
        onetwo.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("12 clicked");
                 int result = takeASquare(0,1);
                 System.out.println("Square taken"+result);
                timeFunction();
                        } 
        } );
       onethree.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("13 clicked");
                 int result = takeASquare(0,2);
                 System.out.println("Square taken"+result);
                 timeFunction();
                        } 
        } );
       twoone.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("21 clicked");
                 int result = takeASquare(1,0);
                 System.out.println("Square taken"+result);
                  timeFunction();
                        } 
        } );
       twotwo.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("22 clicked");
                 int result = takeASquare(1,1);
                 System.out.println("Square taken"+result);
                  timeFunction();
                        } 
        } );
       twothree.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("23 clicked");
                 int result = takeASquare(1,2);
                 System.out.println("Square taken"+result);
                 timeFunction();
                        } 
        } );
       threeone.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("31 clicked");
                 int result = takeASquare(2,0);
                 System.out.println("Square taken"+result);
                  timeFunction();
                        } 
        } );
       threetwo.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("32 clicked");
                 int result = takeASquare(2,1);
                 System.out.println("Square taken"+result);
                 timeFunction();
                        } 
        } );
       threethree.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 System.out.println("33 clicked");
                 int result = takeASquare(2,2);
                 System.out.println("Square taken"+result);
                 timeFunction();
                        } 
        } );
       
       clickMe.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                 randomFunction();
                        } 
        } );
        
        gamePlayFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent evt) {
                actionOnClose();
                }
             });
        
        gamePlayFrame.show(true);
    }
       
    public int takeASquare(int x,int y){
        int returnValue = 0;
        String result = james.takeSquare(x, y, gid, pid);
         if(result.equals("ERROR") | result.equals("ERROR-TAKEN")| result.equals("ERROR-DB")){
                               errorUI.showErrorPopUp(result);
                            } else if(Integer.parseInt(result)==0){ 
                             errorUI.showErrorPopUp("Returned 0");
                            } else{
                                returnValue = 1;
                            }
        return returnValue;
    }
    
    public int checkWin(int gid){
        int returnValue = 0;
       
        String result = james.checkWin(gid);
         if(result.equals("ERROR-RETRIEVE") | result.equals("ERROR-NOGAME")| result.equals("ERROR-DB")){
                               //do nothing
                            } else {
                             returnValue = Integer.parseInt(result);
                             
                            }

        return returnValue;
    }
    
    public void timeFunction(){
     Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

        @Override
        public void run() {
            int result = checkWin(gid);
            if(alreadyShowingGameEnd == 0){
            switch (result) {
                                   
                                    case 0:
                                        //Do nothing and continue the game
                                        break;
                                    case 1:
                                        james.setGameState(gid, 1);
                                        gameEndFrameManager("Player 1 has won the game");
                                        alreadyShowingGameEnd =1;
                                        t.cancel();
                                        break;
                                    case 2:
                                        james.setGameState(gid, 2);
                                        gameEndFrameManager("Player 2 has won the game");
                                        alreadyShowingGameEnd =1;
                                        t.cancel();
                                        break;
                                    case 3:
                                        james.setGameState(gid, 3);
                                        gameEndFrameManager("Damn It's a draw");
                                        alreadyShowingGameEnd =1;
                                        t.cancel();
                                        break;
                                    default:
                                        gameEndFrameManager("Something weird just happened");
                                        alreadyShowingGameEnd =1;
                                        t.cancel();
                                        break;
                            }
             }else{
                //do nothing
            }
        }

    }, 0, 1000);
    }
    
    public void checkTurn(){
     Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

        @Override
        public void run() {
        randomFunction();
        hasSecondPlayerJoined();
        }

    }, 0, 1000);
    }
    
    public void randomFunction(){
       
        String result = james.getBoard(gid);
        if(result.equals("ERROR-NOMOVES") | result.equals("ERROR-DB")){
                               //do nothing
                            } else {
                                if(result.length() > 0) {
                                        String[] rows = result.split("\n");
                                        int num_moves = rows.length;
                                        int[][] table = new int[3][3];
                                        for(int a=0;a<3;a++) {
                                            for(int b=0;b<3;b++) {
                                                table[a][b] = 0;
                                            }
                                        }

                                        String latestTransaction = rows[num_moves-1];
                                        String[] cells = latestTransaction.split(",");
                                        int pids = Integer.parseInt(cells[0]);
                                        int xy =  Integer.parseInt(cells[1]+cells[2]);
                                       
                                        if(pid == pids){
                                            if(alreadyShowingWait == 1){
                                                //do nothing
                                                
                                            }else{
                                               alreadyShowingWait = 1;
                                                changeTextOfButton(xy,1);
                                                waiting.show(true);
                                              
                                            }
                                            
                                        }else{
                                           changeTextOfButton(xy,2);
                                            waiting.show(false);
                                            alreadyShowingWait = 0;
                                        }
            
        }
                             
                            }
    
    }
    
 
   public void gameEndFrameManager(String text){
        if(alreadyShowingGameEnd == 0){
         alreadyShowingGameEnd = 1; 
         JLabel endMessage = new JLabel(text);
         gameEnd.add(endMessage);
         gameEnd.show(true);
         gamePlayFrame.show(false);
        } 
        
   } 
   
   public void changeTextOfButton(int buttonNo,int playerType){
        
       switch (buttonNo) {
            
                                    case 00:
                                        //Do nothing and continue the game
                                       if(playerType == 1){
                                           oneone.setText("X");
                                       }else{
                                            oneone.setText("0");
                                       }
                                        break;
                                    case 01:
                                       if(playerType == 1){
                                           onetwo.setText("X");
                                       }else{
                                            onetwo.setText("0");
                                       }
                                        break;
                                    case 02:
                                        if(playerType == 1){
                                              onethree.setText("X");
                                          }else{
                                               onethree.setText("0");
                                          }
                                        break;
                                     case 10:
                                        if(playerType == 1){
                                           twoone.setText("X");
                                       }else{
                                            twoone.setText("0");
                                       }
                                        break;
                                    case 11:
                                       if(playerType == 1){
                                           twotwo.setText("X");
                                       }else{
                                            twotwo.setText("0");
                                       }
                                        break;
                                    case 12:
                                        if(playerType == 1){
                                              twothree.setText("X");
                                          }else{
                                               twothree.setText("0");
                                          }
                                           break;
                                    
                                    case 20:
                                        if(playerType == 1){
                                           threeone.setText("X");
                                       }else{
                                            threeone.setText("0");
                                       }
                                        break;
                                    case 21:
                                       if(playerType == 1){
                                           threetwo.setText("X");
                                       }else{
                                            threetwo.setText("0");
                                       }
                                        break;
                                    case 22:
                                        if(playerType == 1){
                                              threethree.setText("X");
                                          }else{
                                               threethree.setText("0");
                                          }
                                        break;
                                        
                                    default:
                                    System.out.println("Something weird happened");
                                        break;
                            }
   }
   
   public void hasSecondPlayerJoined(){
      
      
      if(isGameInProgress == 0){
          String result = james.getGameState(gid);
           if(hasTheSecondPlayerJoinedYet == 0){
           
           if(result.equals("ERROR-NOGAME") | result.equals("ERROR-DB")){
                               //do nothing
                            } else if(Integer.parseInt(result) == -1){
                             waitingForSecondPlayerToJoin.show(true);
                             gamePlayFrame.show(false);
                              hasTheSecondPlayerJoinedYet = 1;
           }
          
       } else if(Integer.parseInt(result) == 0){
           waitingForSecondPlayerToJoin.show(false);
           gamePlayFrame.show(true);
           isGameInProgress = 1;
           
       }
      }else{
          //do Nothing
      }
      
     
      
                                        
   }
     
   public void waitingForSecondPlayerFrameManager(){
         waitingForSecondPlayerToJoin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         waitingForSecondPlayerToJoin.setBounds(50, 100, 400, 200);
         waitingForSecondPlayerToJoin.setLayout(new GridLayout(1,1));
         waitingForSecondPlayerToJoin.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent evt) {
                actionOnClose();
                }
             });
         JLabel errorMessage = new JLabel("Waiting for the second player to join");
         waitingForSecondPlayerToJoin.add(errorMessage);
      }
   
   public void actionOnClose(){
       james.deleteGame(gid, pid);
 
   }
   

}
