

import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;
import java.util.List;                  


public class Player implements Comparator<Player>{
     private String name = null;
     private Integer score = 0;
     List<Player> players = new ArrayList<Player>();

     public Player(String name, Integer score) {
         this.name = name;
         this.score = score;
     }
     
     //getters 
     public String getName() {
         return this.name;
     }

     public Integer getScore() {
         return this.score;
     }
     
     //setters
     public void setName(String name) {
         this.name = name;
     }

     public void setScore(int score) {
         this.score = score;
     }
     
     Comparator<Player> compareByScore = new Comparator<Player>() {
    	    @Override
    	    public int compare(Player p1, Player p2) {
    	        return p1.getScore().compareTo(p2.getScore());
    	    }
    	};
    	
    	
    		
            @Override 
            public int compare(Player p1, Player p2) {
                return p1.score - p2.score;
            }



	}