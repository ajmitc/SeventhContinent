package sevcon;

import sevcon.view.*;
import sevcon.game.*;
import sevcon.game.character.*;
import sevcon.game.tile.*;

public class CommandLineController implements GameMenuItemCallback
{
    private Model _model;
    private View _view;

    private GameMenu _mainmenu;

    private boolean _exit;

    public CommandLineController( Model model, View view )
    {
        _model = model;
        _view = view;
        _exit = false;

        _mainmenu = new GameMenu( "Seventh Continent" );
        _mainmenu.getItems().add( new GameMenuItem( "N", "New Game", null, this ) );
        _mainmenu.getItems().add( new GameMenuItem( "C", "Continue Game", null, this ) );
        _mainmenu.getItems().add( new GameMenuItem( "E", "Exit", null, this ) );

        while( !_exit )
        {
            GameMenuItem selected = _view.displayMenu( _mainmenu );
            selected.getCallback().performAction( selected, _model, _view );
        }
    }

    public void performAction( GameMenuItem selected, Model model, View view )
    {
        if( selected.getOptionDisplay().equalsIgnoreCase( "n" ) )
        {
            newGame();
        }
        else if( selected.getOptionDisplay().equalsIgnoreCase( "c" ) )
        {
            continueGame();
        }
        else if( selected.getOptionDisplay().equalsIgnoreCase( "e" ) )
        {
            _exit = true;
        }
    }

    public void newGame()
    {
        Game game = new Game();
        _model.setGame( game );

        // Get number of players
        int numPlayers = 0;
        while( true )
        {
            String sNumPlayers = ((CommandLineView) _view).getInput().getInput( "How many players (1)? " );
            if( sNumPlayers.equals( "" ) )
                sNumPlayers = "1";
            try
            {
                numPlayers = Integer.decode( sNumPlayers );
            }
            catch( Exception e )
            {
                e.printStackTrace();
                continue;
            }
            break;
        }

        for( int i = 0; i < numPlayers; ++i )
        {
            // Choose Character
            GameCharacter c = chooseCharacter();
            game.getPlayers().add( new Player( c.getName(), c ) );
        }

        game.setDiceCount( game.getPlayers().size() == 1? 4: (game.getPlayers().size() == 2? 3: 2) );

        // Get Clue
        ClueTile clue = chooseClue();
        game.getClues().add( clue );

        // Build Action Deck
        buildDefaultActionDeck();

        // If easy mode, add Tile 777 to satchel
        Tile tile777 = _model.getGame().getTile( 777 );
        if( tile777 != null )
            _model.getGame().getSatchel().add( tile777 );

        // TODO If hardcore mode, add Tile 650 to satchel

        // Shuffle Exploration Tiles by Area
        _model.getGame().shuffleExplorationTiles();

        // Get lowest-numbered TerrainTile indicated on Clue Cards
        int lowest = Integer.MAX_VALUE;
        for( ClueTile c: _model.getGame().getClues() )
        {
            if( c.getNumber() < lowest )
                lowest = c.getNumber();
        }

        Tile startingTile = _model.getGame().getTile( lowest );
        _model.getGame().placeTileOnBoard( startingTile, 0, 0 );

        for( Player player: _model.getGame().getPlayers() )
        {
            player.setLocation( 0, 0 );
        }

        // Ready!
        playGame();
    }

    public void continueGame()
    {
        System.err.println( "Not yet implemented" );
    }


    private GameCharacter chooseCharacter()
    {
        GameMenu menu = new GameMenu( "Choose Character" );
        for( GameCharacter c: _model.getGame().getAllCharacters() )
        {
            menu.getItems().add( new GameMenuItem( c.getName(), c, null ) );
        }
        GameMenuItem selected = _view.displayMenu( menu );
        GameCharacter c = (GameCharacter) selected.getObject();
        return c;
    }

    private ClueTile chooseClue()
    {
        GameMenu menu = new GameMenu( "Choose Clue" );
        for( ClueTile c: _model.getGame().getAllClues() )
        {
            menu.getItems().add( new GameMenuItem( c.getTitle(), c, null ) );
        }
        GameMenuItem selected = _view.displayMenu( menu );
        ClueTile c = (ClueTile) selected.getObject();
        return c;
    }

    private void buildDefaultActionDeck()
    {
        // 35 Skill tiles common to all characters
        for( Tile tile: _model.getGame().getDefaultActionDeckTiles() )
        {
            _model.getGame().getActionDeck().add( tile );
        }

        // 5 Skill tiles specific to each character in play
        for( Player player: _model.getGame().getPlayers() )
        {
            for( SkillTile skill: _model.getGame().getSkillsForCharacter( player.getCharacter() ) )
            {
                _model.getGame().getActionDeck().add( skill );
            }
        }

        // Curse cards associated with Clue(s)
        for( ClueTile clue: _model.getGame().getClues() )
        {
            for( CurseTile curse: _model.getGame().getCursesForClue( clue ) )
            {
                _model.getGame().getActionDeck().add( curse );
            }
        }

        // 4 "Death is lurking" Curse tiles
        for( int i = 0; i < 4; ++i )
        {
            _model.getGame().getActionDeck().add( new CurseTile( i, 0, null, "Death is Lurking", "Death is Lurking", "Death is Lurking" ) );
        }
    }


    private void playGame()
    {
        Game game = _model.getGame();
        while( !game.isGameOver() )
        {
            // TODO Choose Active Player

            // TODO Check for other involved players

            Player currentPlayer = game.getActivePlayer();

            // Take an action
            // Action may be:
            // - on current Tile
            // - on adjacent Exploration Tile
            // - on adjacent/attached Permanent Event Tile
            // - on attached Temporary Event Tile
            // - a Skill/Bonus/State card in hand
            // - an Item card in inventory
            // - a Quest item card in satchel
            List<AvailableAction> availableActions = new ArrayList<>();
            TerrainTile currentTile = (TerrainTile) game.getActivePlayerTile();

            // Current Tile actions
            currentTile.getAvailableActions().stream().forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );

            // Adjacent Tile actions
            game.getGameBoard().getNeighbors( currentPlayer.getX(), currentPlayer.getY() ).stream().forEach( tile -> {
                if( tile instanceof ExplorationTile )
                {
                    ((ExplorationTile) tile).getAvailableActions().stream().forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );
                }
                else if( tile instanceof PermanentEventTile )
                {
                    ((PermanentEventTile) tile).getAvailableActions().stream().forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );
                }
            });

            // Temporary Events in effect
            game.getTemporaryEvents().stream().flatMap( te -> te.getAvailableActions().stream() ).forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );

            // Player State actions
            currentPlayer.getStates().stream().flatMap( st -> st.getAvailableActions().stream() ).forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );

            // Player Inventory actions
            currentPlayer.getInventory().stream().forEach( tile -> {
                if( tile instanceof SkillTile )
                {
                    ((SkillTile tile).getAvailableActions().stream().forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );
                }
                else if( tile instanceof BonusTile )
                {
                    ((BonusTile tile).getAvailableActions().stream().forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );
                }
                else if( tile instanceof ItemTile )
                {
                    ((ItemTile tile).getAvailableActions().stream().forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );
                }
            });

            // Quest Items in satchel
            game.getSatchel().stream().forEach( tile -> {
                if( tile instanceof QuestItemTile )
                {
                    ((QuestItemTile) tile).getAvailableActions().stream().forEach( aa -> availableActions.add( new AvailableAction( aa, currentTile ) ) );
                }
            });

            // TODO Player(s) may also move any number of already-explored tiles

            // TODO OK, let user choose action
        }
    }


    private static class AvailableAction
    {
        public PlayerAction action;
        public Tile sourceTile;

        public AvailableAction( PlayerAction pa, Tile t )
        {
            action = pa;
            sourceTile = t;
        }
    }
}

