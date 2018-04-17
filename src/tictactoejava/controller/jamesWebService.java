/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoejava.controller;
import james.TTTWebService;
import james.TTTWebService_Service;
/**
 *
 * @author Ashish
 */
public class jamesWebService {
    private TTTWebService proxy;
    private TTTWebService_Service hm;
    
    
    public jamesWebService(){
        hm = new TTTWebService_Service();
        proxy = hm.getTTTWebServicePort();
    }
    
    public TTTWebService getProxy(){
        return proxy;
    }
    
    public TTTWebService callMe() {
       jamesWebService ws = new jamesWebService();
        TTTWebService myLink = ws.getProxy();
       return myLink;
    }
    
    public int signin(String username, String password){
        TTTWebService myLink = callMe();
        int result = myLink.login(username, password);
        return result;
    }
    
    public String register(String username, String password, String name,String surname){
        TTTWebService myLink = callMe();
       String result = myLink.register(username, password, name, surname);
        return result;
    }
    
    public String newGame(int uid){
        TTTWebService myLink = callMe();
        String result = myLink.newGame(uid);
        return result;
    }
    
    public String joinGame(int uid, int gid){
        TTTWebService myLink = callMe();
        String result = myLink.joinGame(uid, gid);
        return result;
    }
    
    public String getBoard(int gid){
        TTTWebService myLink = callMe();
        String result = myLink.getBoard(gid);
        return result;
    }
    
    public String getGameState(int gid){
        TTTWebService myLink = callMe();
        String result = myLink.getGameState(gid);
        return result;
    }
    
    public String setGameState(int gid, int gstate){
        TTTWebService myLink = callMe();
        String result = myLink.setGameState(gid, gstate);
        return result;
    }
    
    public String checkSquare(int x, int y, int gid){
        TTTWebService myLink = callMe();
        String result = myLink.checkSquare(x, y, gid);
        return result;
    }
    
    public String takeSquare(int x, int y,int gid,int pid){
        TTTWebService myLink = callMe();
        String result = myLink.takeSquare(x, y, gid, pid);
        return result;
    }
    
    public String checkWin(int gid){
        TTTWebService myLink = callMe();
        String result = myLink.checkWin(gid);
        return result;
    }
    
    public String deleteGame(int gid, int  uid){
        TTTWebService myLink = callMe();
        String result = myLink.deleteGame(gid, uid);
        return result;
    }
    
    public String showMyOpenGames(int uid){
        TTTWebService myLink = callMe();
        String result = myLink.showMyOpenGames(uid);
        return result;
    }
    
    public String showAllMyGames(int uid){
        TTTWebService myLink = callMe();
        String result = myLink.showAllMyGames(uid);
        return result;
    }
    
    public String leagueTable(){
        TTTWebService myLink = callMe();
        String result = myLink.leagueTable();
        return result;
    }
    
    public String showOpenGames(){
        TTTWebService myLink = callMe();
        String result = myLink.showOpenGames();
        return result;
    }
    
    public String[][] getOpenGameList(){
         TTTWebService myLink = callMe();
         String result = myLink.showOpenGames();
         
             if(result.equals("ERROR-NOGAMES") | result.equals("ERROR-DB")){
                               //do nothing
                               String[][] nogames = null;
                               nogames = new String[3][3];
                               nogames[0][0] = "No games found";
                               nogames[0][1] = "Start A new Game";
                               nogames[0][2] = " ";
                               return nogames;
                            } else {
                            
                            
          String[][] table = null;
        
          if(result.length() > 0) {    
         String[] rows = result.split("\n");
         int num_moves = rows.length;
        
            table = new String[num_moves][3];
            
            for(int a=0;a<num_moves;a++) {
                for(int b=0;b<3;b++) {
                    table[a][b] = "0";
                }
            }
            for(int i=0;i<num_moves;i++) {
                String[] cells = rows[i].split(",");
                String gid = cells[0];
                String playerName = cells[1];
                String date = cells[2];
                table[i][0] = gid;
                table[i][1] = playerName;
                table[i][2] = date;
            }
            
    }
    
    return table;
    }   
    }
}


