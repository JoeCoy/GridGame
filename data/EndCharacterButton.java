package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndCharacterButton implements ActionListener{
    
	Menu menu = null;
	
	public EndCharacterButton(Menu menu)
	{
		super();
		this.menu = menu;
	}
	
	public void actionPerformed(ActionEvent e) {
        //numClicks++;
		if (menu.getActivePlayer() != null && menu.getActivePlayer().getCurrentSelection() != null)
		{
			menu.getActivePlayer().getCurrentSelection().deactivate();
			menu.text.setText("Number of Activations: " + menu.getActivePlayer().numActivations);
		}
    }
}
