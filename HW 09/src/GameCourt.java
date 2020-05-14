/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.lang.Math; 
import javax.swing.ImageIcon;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
	


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	

    // the state of the game logic
    
    private Basket basket; 
    
    private YellowApple apple2;
    private GreenApple apple3; 
    private OrangeApple apple4; 
    private Worm worm; 
    private Worm worm2; 
    private Apple apple; 
    
    
    
   
    
    private int count = 0;
    private int missed = 0;
    private int highest = 0;
    
    int a1Caught = 0;
    int a1 = 0; 
    int a2Caught = 0;
    int a2 = 0; 
    int a3Caught = 0;
    int a3 = 0; 
    int a4Caught = 0;
    int a4 = 0; 
    
    public List<Player> players = new ArrayList<Player>();
    
    
    /*
     * this array will hold the information on the apples the rows are the apples and the first column 
    will hold the the number of times that one was caught and the second will hold the number of times 
    it was missed, this will be used to print the apple that the user missed the most frequently 
    */
    int[][] apples = new int[4][2]; 
    
   


    public static boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 700;
    public static final int COURT_HEIGHT = 520;
    public static final int BASKET_VELOCITY = 20;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

 
    
    public GameCourt(JLabel status) {
  
    	   
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(new Color(135, 206, 235));
        

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    basket.setVx(-BASKET_VELOCITY);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    basket.setVx(BASKET_VELOCITY);
                }
            }

            public void keyReleased(KeyEvent e) {
                basket.setVx(0);
               
            }
        });

        this.status = status;
    }
    
    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        basket = new Basket(COURT_WIDTH, COURT_HEIGHT);
        
        worm = new Worm(COURT_WIDTH, COURT_HEIGHT);
        worm.setPx((int) (Math.random()* COURT_WIDTH));
        worm.setVy((int) (Math.random()* 25 + 5));
        
        apple = new Apple(COURT_WIDTH, COURT_HEIGHT);
        apple.setPx((int) (Math.random()* COURT_WIDTH));
        apple.setVy((int) (Math.random()* 25 + 5));
        
        worm2 = new Worm(COURT_WIDTH, COURT_HEIGHT);
        worm2.setPx((int) (Math.random()* COURT_WIDTH));
        worm2.setVy((int) (Math.random()* 25 + 5));
        
        apple2 = new YellowApple(COURT_WIDTH, COURT_HEIGHT);
        apple2.setPx((int) (Math.random()* COURT_WIDTH));
        apple2.setVy((int) (Math.random()* 25 + 5));
        
        apple3 = new GreenApple(COURT_WIDTH, COURT_HEIGHT);
        apple3.setPx((int) (Math.random()* COURT_WIDTH));
        apple3.setVy((int) (Math.random()* 25 + 5));
        
        apple4 = new OrangeApple(COURT_WIDTH, COURT_HEIGHT);
        apple4.setPx((int) (Math.random()* COURT_WIDTH));
        apple4.setVy((int) (Math.random()* 25 + 5));

        playing = true;
       
        count = 0;
        missed = 0; 
        highest = 0; 
        a1Caught = 0;
        a1 = 0; 
       a2Caught = 0;
        a2 = 0; 
       a3Caught = 0;
        a3 = 0; 
        a4Caught = 0;
        a4 = 0; 
        apples[0][1] = a1; 
        apples[1][1] = a2; 
        apples[2][1] = a3; 
        apples[3][1] = a4; 
        apples[0][0] = a1Caught;
        apples[1][0] = a2Caught;
        apples[2][0] = a3Caught;
        apples[3][0] = a4Caught;
        
        status.setText("Apples Caught: 0"  + "\n" + " Apple Missed: 0" );
        
        

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	
           basket.move();
           worm.move();
           apple.move();
           worm2.move();
           apple2.move();
           apple3.move();
           apple4.move();

           if (worm.getPy() == 500) {
        	   worm.setPx((int) (Math.random()* COURT_WIDTH));
        	   worm.setPy(0);
        	   worm.setVy((int) (Math.random()* 25 + 5));

           }
           if (apple.getPy() == 480) {
        	   apple.setPx((int) (Math.random()* COURT_WIDTH));
        	   apple.setPy(0);
        	   apple.setVy((int) (Math.random()* 25 + 5));
        	   missed ++;
        	   a1++;
			   apples[0][1] = a1; 
			   status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
			 
           }

           if (worm2.getPy() == 500) {
        	   worm2.setPx((int) (Math.random()* COURT_WIDTH));
        	   worm2.setPy(0);
        	   worm2.setVy((int) (Math.random()* 25 + 5));

           }
           if (apple2.getPy() == 480) {
        	   apple2.setPx((int) (Math.random()* COURT_WIDTH));
        	   apple2.setPy(0);
        	   apple2.setVy((int) (Math.random()* 25 + 5));
        	   missed ++;
        	   int a2 = 0;
        	   a2++;
			   apples[1][1] = a2; 
			   status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
           }
           if (apple3.getPy() == 480) {
        	   apple3.setPy(0);
        	   apple3.setPx((int) (Math.random()* COURT_WIDTH));
        	   apple3.setVy((int) (Math.random()* 25 + 5));
        	   missed ++;
        	   a3++;
			   apples[2][1] = a3; 
			   status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
           }
           if (apple4.getPy() == 480) {
        	   apple4.setPy(0);
        	   apple4.setPx((int) (Math.random()* COURT_WIDTH));
        	   apple4.setVy((int) (Math.random()* 25 + 5));
        	   missed ++;
        	   a4++;
			   apples[3][1] = a4; 
			   status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
           }
        
        
            // check for the game end conditions
         
            if (apple.touching(basket)) {
            	count ++;
            	a1Caught++; 
            	apples[0][0] = a1Caught;
            	status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
            	apple.setPy(0);
          	   apple.setPx((int) (Math.random()* COURT_WIDTH));
          	 apple.setVy((int) (Math.random()* 25 + 5));
            	
           }
            if (apple2.touching(basket)) {
            	count ++;
            	a2Caught++; 
            	apples[1][0] = a2Caught;
            	status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
            	 apple2.setPy(0);
          	   apple2.setPx((int) (Math.random()* COURT_WIDTH));
          	 apple2.setVy((int) (Math.random()* 25 + 5));
            	
           }
            if (apple3.touching(basket)) {
            	count ++;
            	a3Caught++; 
            	apples[2][0] = a3Caught;
            	status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
            	 apple3.setPy(0);
          	   apple3.setPx((int) (Math.random()* COURT_WIDTH));
          	 apple3.setVy((int) (Math.random()* 25 + 5));
            	
           }
            if (apple4.touching(basket)) {
            	count ++;
            	a4Caught++; 
            	apples[3][0] = a4Caught;
            	status.setText("Apples Caught: " + count + " Apples Missed: " + missed);
            	 apple4.setPy(0);
          	   apple4.setPx((int) (Math.random()* COURT_WIDTH));
          	 apple4.setVy((int) (Math.random()* 25 + 5));
            	
           }
            JFrame frame = new JFrame("InputDialog Example #1");
            players.add(new Player("", (Integer)0));
            players.add(new Player("", (Integer)0));
            players.add(new Player("", (Integer)0));
            
            if (worm.touching(basket)) {
            	highest = apples[0][1];
                for(int i = 1; i < 4; i++) {
                
                		if (apples[i][1] > apples[i-1][1]) {
                			highest = apples[i][1];
                		}
                	}
   
                status.setText("GAME OVER! Apples Caught: " + count + " Apples Missed: " + missed + 
                		" You even missed one apple type " + highest + " times...");
                				               			
                playing = false;
                String name = JOptionPane.showInputDialog(frame, "Sorry, you lost! \n Your score was: " + count
                		+ "\n Enter your name below:");
                players.add(new Player(name, (Integer)count));
                Collections.sort(players, new MyComparator());
            }
            

            
            if (worm2.touching(basket)) {
            	 highest = apples[0][1];
                 for(int i = 1; i < 4; i++) {
                 
                 		if (apples[i][1] > apples[i-1][1]) {
                 			highest = apples[i][1];
                 		}
                 	}
            	   
                status.setText("GAME OVER! Apples Caught: " + count + " Apples Missed: " + missed + 
                		" You even missed one apple type " + highest + " times...");
                playing = false;
                String name = JOptionPane.showInputDialog(frame, "Sorry, you lost! \n Your score was: " + count  
                		+ "\n Enter your name below:");
                players.add(new Player(name, (Integer)count));
                
            }
          
            //sorting the array from high score to low 
            Collections.sort(players, new MyComparator());

            // update the display
         
        }
            repaint();
            
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        apple.draw(g);
        worm.draw(g);
        apple2.draw(g);
        worm2.draw(g);
        apple3.draw(g);
        apple4.draw(g);
        basket.draw(g);
        
 
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}