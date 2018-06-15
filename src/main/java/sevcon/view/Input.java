package sevcon.view;

import java.util.Scanner;

public class Input
{	
	private Scanner _scanner;
	
	public Input()
	{
		_scanner = new Scanner( System.in );
	}
	
	public String getInput( String prompt )
	{
		System.out.print( prompt );
		String inp = _scanner.nextLine().trim();
		return inp;
	}
	
	public String getInput()
	{
		return getInput( "> " );
	}
	
	public void waitForEnter()
	{
		System.out.print( "(Press Enter to Continue)" );
		_scanner.nextLine();
	}
}
