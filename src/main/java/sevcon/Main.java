package sevcon;

import sevcon.view.View;
import sevcon.view.CommandLineView;

public class Main
{
    public static void main( String ... args )
    {
        Model model = new Model();
        View view = new CommandLineView( model );
        new CommandLineController( model, view );
    }
}
