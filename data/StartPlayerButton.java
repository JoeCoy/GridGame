package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPlayerButton implements ActionListener{
    
	Menu menu = null;
	
	public StartPlayerButton(Menu menu)
	{
		super();
		this.menu = menu;
	}
	
	public void actionPerformed(ActionEvent e) {
        //numClicks++;
		if (menu.getActivePlayer() == null)
		{
			menu.activePlayer = menu.nextPlayer;
			
			if (menu.nextPlayer == menu.player1)
			{
				menu.nextPlayer = menu.player2;
			}
			else
			{
				menu.nextPlayer = menu.player1;
			}
			
			menu.getActivePlayer().takeTurn();
			menu.text.setText("Number of Activations: " + menu.getActivePlayer().numActivations);
		}
    }
}
