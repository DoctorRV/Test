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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tictactoejava.controller.jamesWebService;

/**
 *
 * @author Ashish
 */
public class openGameList {
    private errorPopUp errorUI = new errorPopUp();
    private jamesWebService james = new jamesWebService();
    private JFrame openGameFrame = new JFrame("Open Game List");
    private JTable gamesList;
    private int playerID;
    String username;
    public void drawUI(){
        gamePlayScreen gameplay = new gamePlayScreen();
        System.out.println("Player id is "+playerID);
        String table[][] = james.getOpenGameList();
        openGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        openGameFrame.setBounds(50, 100, 500, 700);
        openGameFrame.setLayout(new GridLayout(4,1));
        JButton yourScore = new JButton("Your Scores");
        yourScore.setBackground(Color.CYAN);
        JButton leaderBoard = new JButton("Leader Board");
        leaderBoard.setBackground(Color.CYAN);
        JButton newGame = new JButton("New Game");
        newGame.setBackground(Color.CYAN);
        //TESTING
       /*
        DefaultListModel<String> l1 = new DefaultListModel<>();  
         l1 = getTheList();
         JList<String> list = new JList<>(l1);    
         */
        //JTable openGames = new JTable();
        openGameFrame.add(yourScore);
        openGameFrame.add(leaderBoard);
        openGameFrame.add(newGame);
        //openGameFrame.add(openGames);
        
        String gamelist = getOpenGameList();
        checkTurn();
        String[] columns = {"GameID", "PlayerID", "Timestamp"};
        
        if(table != null) {
               gamesList = new JTable(table, columns);
        } else {
                //String[][] blank = {{}};
                gamesList = new JTable();
        }
        
        gamesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {                                    
                JTable source = (JTable)evt.getSource();
                int row = source.rowAtPoint(evt.getPoint());
                int column = 0;
                int gID = Integer.parseInt(source.getModel().getValueAt(row, column).toString());
                System.out.println(gID);  
                int gameID = gID;
                    System.out.println("List Selected"+gameID);
                    String result = james.joinGame(playerID, gameID);
                    if(result == "ERROR-DB"){
                        errorUI.showErrorPopUp(result);
                    }else{
                    gameplay.drawUI(gameID,playerID);
                    openGameFrame.show(false);
                    }
            } 
        });
	
        /*
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    int gameID = getIDFromString(list.getSelectedValue());
                    System.out.println("List Selected"+gameID);
                    String result = james.joinGame(playerID, gameID);
                    if(result == "ERROR-DB"){
                        errorUI.showErrorPopUp(result);
                    }else{
                    gameplay.drawUI(gameID,playerID);
                    openGameFrame.show(false);
                    }
                    
                }
            }
        });
        */
        //Action Listener for yourscore button
        yourScore.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         yourScore yourscore = new yourScore();
                         yourscore.drawUI(playerID,username);
                         openGameFrame.show(false);
                        } 
        } );
        
          //Action Listener for clear button
        leaderBoard.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                         leaderBoard leaderboard = new leaderBoard();
                         leaderboard.drawUI();
                         leaderboard.setPlayerID(playerID, username);
                         openGameFrame.show(false);
                        } 
        } );
        
         newGame.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                        
                         String result = james.newGame(playerID);
                         if(result.equals("ERROR-NOTFOUND") | result.equals("ERROR-INSERT") | result.equals("ERROR-RETRIEVE") | result.equals("ERROR-DB")){
                               errorUI.showErrorPopUp(result);
                            } else {
                              gameplay.drawUI(Integer.parseInt(result),playerID);
                               openGameFrame.show(false);
                            }
                        ;
                        } 
        } );
        openGameFrame.add(gamesList);
        openGameFrame.add(new JScrollPane(gamesList));
        openGameFrame.show(true);
    }
    
    public void setPlayerId(int pid, String userName){
        playerID = pid;
        username = userName;
    }
    
    public String getOpenGameList(){
        String result = james.showOpenGames();
        System.out.println(result);
        return result;
    }
    
    public DefaultListModel<String> getTheList(){
        DefaultListModel<String> l1 = new DefaultListModel<>(); 
        String openGameList = james.showOpenGames();
        String[] split = openGameList.split("\n");
        System.out.println(openGameList+"Check this out = "+split.length);
        for(int i = 0 ; i <split.length;i++){
            l1.addElement(split[i]);
        }
        return l1;
    }

    public int getIDFromString(String idString){
        String idstring = idString.length() < 2 ? idString : idString.substring(0, 2);
        int gID = Integer.parseInt(idstring);
        return gID;
    }
    
       public void refresh() {
        String[][] list = james.getOpenGameList();
        String[] columns = {"GameID", "PlayerID", "Timestamp"};
        DefaultTableModel dataSet = new DefaultTableModel(list, columns);
        gamesList.setModel(dataSet);
        gamesList.repaint();
    }
     
        public void checkTurn(){
     Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

        @Override
        public void run() {
            refresh();
        }

    }, 0, 1000);
    }
    }
    

