package sevcon.view;

import java.util.stream.*;

import sevcon.Model;
import sevcon.game.*;

public class CommandLineView extends View
{
	private Input _input = new Input();
	
	public CommandLineView( Model model )
	{
		super( model );
	}
	
	public GameMenuItem displayMenu( GameMenu menu )
	{
		if( menu.getTitle() != null && !menu.getTitle().equals( "" ) )
			System.out.println( menu.getTitle() );
		for( int i = 0; i < menu.getItems().size(); ++i )
		{
			GameMenuItem item = menu.getItems().get( i );
            if( item.getOptionDisplay() != null && !item.getOptionDisplay().equals( "" ) )
                System.out.print( item.getOptionDisplay() + ") " );
            else
                System.out.print( (i + 1) + ") " );
			System.out.println( item.getDisplay() );
		}
		boolean done = false;
		while( !done )
		{
			String inp = _input.getInput( menu.getPrompt() );

			GameMenuItem selectedItem = null;
            for( GameMenuItem item: menu.getItems() )
            {
                if( item.getOptionDisplay() != null && item.getOptionDisplay().equalsIgnoreCase( inp ) )
                {
                    selectedItem = item;
                    break;
                }
            }

            if( selectedItem == null )
            {
                int selection = -1;
                try
                {
                    selection = Integer.decode( inp );
                }
                catch( Exception e )
                {
                    System.err.println( "Invalid selection" );
                    continue;
                }
                selection -= 1;
                if( selection < 0 || selection >= menu.getItems().size() )
                {
                    System.err.println( "Invalid selection" );
                    continue;
                }
                selectedItem = menu.getItems().get( selection );
            }

			//item.getCallback().performAction( item, _model, this );
			return selectedItem;
		}
		return null;
	}
	
	public void refresh()
	{

	}
	
	public Input getInput(){ return _input; }
}
