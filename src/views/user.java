/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author Ashish
 */
public class user {
    private String username;
    private int wins = 0;
    private int lost = 0;
    private int draws = 0;
    private int totalGames = 0;
    
    public int getWins(){
        return wins;
    }
    
    public int getLost(){
        return lost;
    }
    
    public int getDraws(){
        return draws;
    }
    
    public int getTotalGames(){
        return totalGames;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void incrementWins(){
        wins = wins+1;
    }
    
    public void incrementLost(){
        lost = lost+1;
    }
    
    public void incrementDraws(){
        draws = draws+1;
    }
    
    public void incrementTotalGames(){
        totalGames = totalGames +1;
    }
    
    public void setUsername(String userName){
        username = userName;
    }
}
