package data;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Menu extends Frame implements ActionListener, WindowListener {

    TextField text = new TextField(20);
    Button activateButton;
    Button endCharacterButton;
    Player activePlayer = null;
    public Player player1;
    public Player player2;
    public Player nextPlayer;
    //private int numClicks = 0;

    public Menu(String title, Player player1, Player player2) {

            super(title);
            setLayout(new FlowLayout());
            addWindowListener(this);
            //text box
            add(text);
            
            //start player button
            Button startPlayerButton = new Button("Start Player's turn");
            add(startPlayerButton);
            startPlayerButton.addActionListener(new StartPlayerButton(this));
            
            //activate character button
            activateButton = new Button("Activate a character");
            add(activateButton);
            activateButton.addActionListener(this);
            this.player1 = player1;
            this.player2 = player2;
            nextPlayer = player1;
            
            //end character button
            endCharacterButton = new Button("End character's turn");
            add(endCharacterButton);
            endCharacterButton.addActionListener(new EndCharacterButton(this));
            
            Button endPlayerButton = new Button("End Player's turn");
            add(endPlayerButton);
            endPlayerButton.addActionListener(new EndPlayerButton(this));
            
            

    }

    public void setActivePlayer(Player player)
    {
    	activePlayer = player;
    }
    
    public Player getActivePlayer()
    {
    	return activePlayer;
    }
    
    public void actionPerformed(ActionEvent e) {
            //numClicks++;
    		//getActivePlayer().takeTurn(); //will probably be replaced
    	if (activePlayer != null)
    	{
    		if (activePlayer.numActivations > 0)
    		{
    			getActivePlayer().setReadyToSelect(true);
    			text.setText("Select a character");
    		}
    		else
    		{
    			text.setText("No activations left");
    		}
    	}
    	else
    	{
    		text.setText("You must start your turn first");
    	}
    }

    public void windowClosing(WindowEvent e) {
            dispose();
            System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
	
}
