package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPlayerButton implements ActionListener{
    
	Menu menu = null;
	
	public EndPlayerButton(Menu menu)
	{
		super();
		this.menu = menu;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (menu.getActivePlayer().isTurnOver())
		{
			menu.getActivePlayer().endTurn();
			menu.text.setText("Turn ended");
			menu.setActivePlayer(null);
		}
    }
}
