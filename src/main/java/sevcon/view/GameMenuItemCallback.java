package sevcon.view;

import sevcon.Model;

public interface GameMenuItemCallback
{
	public void performAction( GameMenuItem item, Model model, View view );
}
