/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Falling Apples");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);


        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);
      
        JOptionPane.showMessageDialog(frame, "Welcome to Falling Apples!" + "\n" + "\n" +
    			"How to Play:" + "\n" + 
    			"Try and catch as many apples as possible! " + "\n" +
    			"Use the right and left arrow keys to move the basket back and forth." + "\n" +
    			"Careful though, if you catch a worm the game is over!" + 
    			"\n" + "Click OK to begin! Good Luck!" );
        
        
        
        
        	



        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);
        
        
        //  directions button 
        frame.add(control_panel, BorderLayout.NORTH);
        final JButton directions = new JButton("Directions");
        directions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	court.playing = false;
            	JOptionPane.showMessageDialog(frame, "Welcome to Falling Apples!" + "\n" + "\n" +
            			"How to Play:" + "\n" + 
            			"Try and catch as many apples as possible! " + "\n" +
            			"Use the right and left arrow keys to move the basket back and forth." + "\n" +
            			"Careful though, if you catch a worm the game is over!" + "\n" + "Good Luck!");
            	court.reset();
            }
            
        });
        control_panel.add(directions);
        
        //high score button 
        frame.add(control_panel, BorderLayout.NORTH);
        final JButton scores = new JButton("High Scores");
        scores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(frame, "High Scores:" + "\n" + "Name: " + court.players.get(0).getName() + " Score: " +
            			court.players.get(0).getScore() + "\n" + "Name: " + court.players.get(1).getName() + " Score: " +
                    			court.players.get(1).getScore() +"\n" + "Name: " + court.players.get(2).getName() + " Score: " +
                            			court.players.get(2).getScore());
            	court.reset();
            }
            
        });
        control_panel.add(scores);
        
       /*
       //pause and start button
        frame.add(control_panel, BorderLayout.NORTH);
        final JButton pause = new JButton("Start/Pause");
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.playing = !court.playing;
            }
        });
       
       
        control_panel.add(pause);
        */
        
       

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}