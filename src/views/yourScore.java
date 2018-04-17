/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import tictactoejava.controller.jamesWebService;

/**
 *
 * @author Ashish
 */
public class yourScore {
    private errorPopUp errorUI = new errorPopUp();
    private jamesWebService james = new jamesWebService();
    private int playerid;
    private int gamesWon = 0;
    private int gamesLost = 0;
    private int gamesDraw = 0;
    private int numberOfGamesPlayed = 0;
    private int gamesStillInProgress = 0;
    private String username;
    private JLabel stats = new JLabel();
    private JLabel data = new JLabel();
    public void drawUI(int playerID,String userName){
        playerid = playerID;
        username = userName;
        JFrame yourScoreFrame = new JFrame("Your score list");
        yourScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        yourScoreFrame.setBounds(50, 100, 500, 700);
        yourScoreFrame.setLayout(new GridLayout(4,1));
        JButton gobackButton = new JButton("< Go back");
        JTable yourScoreList = new JTable();
        
        DefaultListModel<String> l1 = new DefaultListModel<>();  
         l1 = getTheList();
         JList<String> list = new JList<>(l1);
        yourScoreFrame.add(gobackButton);;
       
        calculateStats();
        stats.setText("The Stats of all the games you have played");
        data.setText("Games Played: "+numberOfGamesPlayed+" Won: "+gamesWon+" Lost: "+gamesLost+" Draws: "+gamesDraw);
        yourScoreFrame.add(stats);
        yourScoreFrame.add(data);
         yourScoreFrame.add(list);
        //Action Listener for yourscore button
        gobackButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         openGameList openGame = new openGameList();
                         yourScoreFrame.show(false);
                         openGame.setPlayerId(playerid, username);
                         openGame.drawUI();
                        } 
        } );
        
        System.out.println("STATS coming your way Number of total games = "+numberOfGamesPlayed+" Number of games that are yet to end = "+gamesStillInProgress+" Wins = "+gamesWon+" Lost games = "+gamesLost+" Draws = "+gamesDraw);
        
        
        yourScoreFrame.show(true);
    }
    
    public DefaultListModel<String> getTheList(){
        DefaultListModel<String> l1 = new DefaultListModel<>();
        System.out.println("I am inside get the list");
        String openGameList = james.showAllMyGames(playerid);
        String[] split = openGameList.split("\n");
        System.out.println(openGameList);
        for(int i = 0 ; i <split.length;i++){
            l1.addElement(split[i]);
        }
        return l1;
    }
    
    public void calculateStats(){
        String result = james.showAllMyGames(playerid);
        String[][] table = null;
         if(result.equals("ERROR-NOGAMES")| result.equals("ERROR-DB")){
                               //do nothing 
                            } else {
                              
                            
         
        if(result.length() > 0) {    
        String[] rows = result.split("\n");
         int num_moves = rows.length;
         numberOfGamesPlayed = num_moves;
            table = new String[num_moves][4];
            
            for(int a=0;a<num_moves;a++) {
                for(int b=0;b<3;b++) {
                    table[a][b] = "0";
                }
            }
            for(int i=0;i<num_moves;i++) {
                String[] cells = rows[i].split(",");
                String gid = cells[0];
                String hUsername = cells[1];
                String oUsernmae = cells[2];
                String date = cells[3];
                table[i][0] = gid;
                table[i][1] = hUsername;
                table[i][2] = oUsernmae;
                table[i][3] = date;
            }
            
              for(int a=0;a<num_moves;a++) {
                int gameID = Integer.parseInt(table[a][0]);
                String hUsername = table[a][1];
                String oUsername = table[a][2];
                int isUserHorO = 0;
               
                if(hUsername.equals(username)){
                    isUserHorO = 1;
                }else if(oUsername.equals(username)){
                    isUserHorO = 2;
                }
                
                //Now we calll checkWin for each game in our list
                int gameResultInInt = 0;
                String gameResult = james.checkWin(gameID);
                if(gameResult.equals("ERROR-RETRIEVE") | gameResult.equals("ERROR-NOGAME")| gameResult.equals("ERROR-DB")){
                               //do nothing
                            } else {
                             gameResultInInt = Integer.parseInt(gameResult);
                            }
                
            switch (gameResultInInt) {
           
                case 0:
                     //Game has not ended so do not do anything yet
                    gamesStillInProgress = gamesStillInProgress+1;
                    break;
                case 1:
                    if(isUserHorO == 1){
                        gamesWon = gamesWon+1;
                    }else{
                        gamesLost = gamesLost+1;
                    }
                    break;
                case 2:
                    if(isUserHorO == 2){
                        gamesWon = gamesWon +1;
                    }else{
                        gamesLost = gamesLost +1;
                    }
                    break;
                case 3:
                    gamesDraw = gamesDraw +1;
                    break;
                default:
                    break;
            }
                  for(int b=0;b<3;b++) {
                    System.out.println(table[a][b]);
                }
            }
            
    }
        
         
    }
}
}
