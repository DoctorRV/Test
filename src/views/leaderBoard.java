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
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import tictactoejava.controller.jamesWebService;

/**
 *
 * @author Ashish
 */
public class leaderBoard {
    private errorPopUp errorUI = new errorPopUp();
    private jamesWebService james = new jamesWebService();
    private int playerID ;
    private String username;
    ArrayList<user> users = new ArrayList<>();
    private JTable leaderBoard;
    String table[][] = null;
    public void drawUI(){
        
        JFrame leaderBoardFrame = new JFrame("Leader Board");
        leaderBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaderBoardFrame.setBounds(50, 100, 500, 700);
        leaderBoardFrame.setLayout(new GridLayout(4,1));
        JButton gobackButton = new JButton("< Go back");
        gobackButton.setBackground(Color.CYAN);
        DefaultListModel<String> l1 = new DefaultListModel<>();  
         l1 = getTheList();
         JList<String> list = new JList<>(l1); 
         calculateLeaderBoard();
         testing();
        String[] columns = {"Username", "Total Games", "Wins","Lost","Draws"};
        
        if(table != null) {
               leaderBoard = new JTable(table, columns);
        } else {
                //String[][] blank = {{}};
                leaderBoard = new JTable();
        }
        leaderBoardFrame.add(gobackButton);
        leaderBoardFrame.add(leaderBoard);
         leaderBoardFrame.add(new JScrollPane(leaderBoard));
        leaderBoardFrame.add(list);
    
        //Action Listener for yourscore button
        gobackButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         openGameList openGame = new openGameList();
                         leaderBoardFrame.show(false);
                         openGame.setPlayerId(playerID, username);
                         openGame.drawUI();
                        } 
        } );
        
        
        leaderBoardFrame.show(true);
    }
    public DefaultListModel<String> getTheList(){
        DefaultListModel<String> l1 = new DefaultListModel<>(); 
        String openGameList = james.leagueTable();
        String[] split = openGameList.split("\n");
        System.out.println(openGameList+"Check this out = "+split.length);
        for(int i = 0 ; i <split.length;i++){
            l1.addElement(split[i]);
        }
        return l1;
    }
    public void setPlayerID(int playerId , String Username){
        playerID = playerId;
        username = Username; 
    }
    
    public void calculateLeaderBoard(){
        
        String result = james.leagueTable();
        String[][] table = null;
        if(result.equals("ERROR-NOGAMES")| result.equals("ERROR-DB")){
                               //do nothing 
                            } else {
                              
                            
         
        if(result.length() > 0) {    
        String[] rows = result.split("\n");
         int num_moves = rows.length;
            table = new String[num_moves][5];
            
            for(int a=0;a<num_moves;a++) {
                for(int b=0;b<5;b++) {
                    table[a][b] = "0";
                }
            }
            for(int i=0;i<num_moves;i++) {
                String[] cells = rows[i].split(",");
                String gid = cells[0];
                String hUsername = cells[1];
                String oUsernmae = cells[2];
                String gameState = cells[3];
                String date = cells[4];
                table[i][0] = gid;
                table[i][1] = hUsername;
                table[i][2] = oUsernmae;
                table[i][3] = gameState;
                table[i][4] = date;
            }
            
             for(int a=0;a<num_moves;a++) {
                String hUsername = table[a][1];
                String oUsername = table[a][2];
                int gameState = Integer.parseInt(table[a][3]);
                int doesFirstUserExistInUsersArray = checkIfUserExists(hUsername);
                int doesSecondUserExistInUsersArray = checkIfUserExists(oUsername);
                
                if(doesFirstUserExistInUsersArray == -1){
                       doesFirstUserExistInUsersArray = createANewObject(hUsername);
                    }
                if(doesSecondUserExistInUsersArray == -1){
                       doesSecondUserExistInUsersArray = createANewObject(oUsername);
                    }
                
            switch (gameState) {
                case 1:
                    if(doesFirstUserExistInUsersArray >-1){
                        incrementWins(doesFirstUserExistInUsersArray,doesSecondUserExistInUsersArray);
                    }
                    break;
                case 2:
                    if(doesSecondUserExistInUsersArray >-1){
                        incrementWins(doesSecondUserExistInUsersArray,doesFirstUserExistInUsersArray);
                    }
                    break;
                case 3:
                    if(doesSecondUserExistInUsersArray >-1 & doesFirstUserExistInUsersArray >-1){
                        aDraw(doesSecondUserExistInUsersArray,doesFirstUserExistInUsersArray);
                    }
                    break;
                case 0:
                    gameInPlay(doesSecondUserExistInUsersArray,doesFirstUserExistInUsersArray);
                    break;
                default:
                    break;
            }
                
            }
        }
        }
    }
    
    public int checkIfUserExists(String userNames){
        System.out.println("I AM IN THIS READ ME PLEASE");
          int doesTheUserExistInUsersArray = -1;
          if(users.isEmpty()){
              System.out.println("In is empty"+"the size of whatever is"+users.size());
              
          }else{
              System.out.println("I AM IN THIS READ ME PLEASE222222222222");
                for(int i = 0; i<users.size();i++){
                    System.out.println("i am in checkifUserExit in the stupid loop this is itrration no"+i+"the size of whatever is"+users.size()+"The username is"+userNames+users.get(i).getUsername());
                   
                        if(userNames.equals(users.get(i).getUsername())){
                             System.out.println("I am in found it lol"+userNames+users.get(i).getUsername());
                            doesTheUserExistInUsersArray = i;
                           
                        }else{
                         //Do nothing   
                        }
                    }
          }
                  
        return doesTheUserExistInUsersArray;
    }
    
    public int incrementWins(int locationInArrayWinner, int locationInArrayLoser){
        users.get(locationInArrayWinner).incrementWins();
        users.get(locationInArrayWinner).incrementTotalGames();
        users.get(locationInArrayLoser).incrementLost();
        users.get(locationInArrayLoser).incrementTotalGames();
        return 0;
    }
    
      public int aDraw(int locationInArrayWinner, int locationInArrayLoser){
        users.get(locationInArrayWinner).incrementDraws();
        users.get(locationInArrayWinner).incrementTotalGames();
        users.get(locationInArrayLoser).incrementDraws();
         users.get(locationInArrayLoser).incrementTotalGames();
        return 0;
    }
      
    public int gameInPlay(int locationInArrayWinner, int locationInArrayLoser){
        users.get(locationInArrayWinner).incrementTotalGames();
         users.get(locationInArrayLoser).incrementTotalGames();
        return 0;
    }
    
    public int createANewObject(String userNames){
         users.add(new user());
         System.out.println("Creating a new object"+users.size());
         users.get(users.size()-1).setUsername(userNames);
         System.out.println("Setting the username"+users.get(users.size()-1).getUsername());
         return users.size()-1;
    }
    
    public void testing(){
         table = new String[users.size()][5];
             for(int a=0;a<users.size();a++) {
                for(int b=0;b<5;b++) {
                    table[a][b] = "0";
                }
            }
        for(int i = 0; i< users.size();i++){
            System.out.println(users.get(i).getUsername());
            System.out.println("Total Games played"+users.get(i).getTotalGames()+"Wins : "+users.get(i).getWins()+" Lost: "+users.get(i).getLost()+"Draws: "+users.get(i).getDraws());
            table[i][0] = users.get(i).getUsername();
            table[i][1] = String.valueOf(users.get(i).getTotalGames());
            table[i][2] = String.valueOf(users.get(i).getWins());
            table[i][3] = String.valueOf(users.get(i).getLost());
            table[i][4] = String.valueOf(users.get(i).getDraws());
            
        }
    }
}
