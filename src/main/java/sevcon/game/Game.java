package sevcon.game;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import sevcon.game.tile.*;
import sevcon.game.character.*;
import sevcon.game.util.*;

public class Game
{
    private boolean _gameover;

    private GameBoard _gameboard;

    private List<Tile> _allTiles;
    private List<GameCharacter> _allCharacters;
    private List<ClueTile> _allClues;

    private List<Player> _players;
    private int _currentPlayer;
    private int _diceCount;
    private List<ClueTile> _clues;
    private List<Tile> _satchel;

    private Deck<Tile> _actionDeck;

    private Map<Integer, Deck<ExplorationTile>> _explorationTiles;

    private List<TemporaryEventTile> _temporaryEvents;

    public Game()
    {
        _gameover = false;
        _gameboard = new GameBoard();
        _allTiles = new ArrayList<>();
        _allCharacters = new ArrayList<>();
        _allClues = new ArrayList<>();
        _players = new ArrayList<>();
        _currentPlayer = 0;
        _diceCount = 0;
        _clues = new ArrayList<>();
        _actionDeck = new Deck<Tile>();
        _explorationTiles = new HashMap<Integer, Deck<ExplorationTile>>();
        _satchel = new ArrayList<>();
        _temporaryEvents = new ArrayList<>();

        loadTiles();
        loadCharacters();
        loadClues();

        // Load Exploration Tiles
        for( int i = 0; i < 4; ++i )
        {
            _explorationTiles.put( new Integer( i + 1 ), new Deck<ExplorationTile>() );
            // TODO Create Tiles
        }
    }

    private void loadTiles()
    {

    }

    private void loadCharacters()
    {
        GameCharacter c = new GameCharacter( "Ferdinand Lachapelliere" );
        _allCharacters.add( c );
    }

    private void loadClues()
    {
        ClueTile c = new ClueTile( 0, 1, null, "The Voracious Goddess", "", "", 10 );
        _allClues.add( c );
    }

    public Tile getTile( int tileNumber )
    {
        List<Tile> matches = new ArrayList<>();
        List<Tile> goldMatches = new ArrayList<>();
        for( Tile tile: _allTiles )
        {
            if( tile.getNumber() == tileNumber )
            {
                if( tile.getColor() != Tile.GOLD )
                    matches.add( tile );
                else
                    goldMatches.add( tile );
            }
        }
        if( matches.size() == 0 )
        {
            // No Green tiles left, draw a Gold tile, if possible
            if( goldMatches.size() == 0 )
                return null;
            Collections.shuffle( goldMatches );
            Tile tile = goldMatches.get( 0 );
            _allTiles.remove( tile );
            return tile;
        }
        Collections.shuffle( matches );
        Tile tile = matches.get( 0 );
        _allTiles.remove( tile );
        return tile;
    }

    public boolean placeTileOnBoard( Tile tile, int x, int y )
    {
        _gameboard.placeTile( tile, x, y );
        if( tile instanceof TerrainTile )
        {
            TerrainTile tt = (TerrainTile) tile;
            // Add Exploration Tiles
            for( ExplorationPlacement ep: tt.getExplorationPlacements() )
            {
                Deck<ExplorationTile> expDeck = getExplorationDeck( ep.getArea() );
                expDeck.shuffle();
                ExplorationTile et = expDeck.draw();
                et.setReplacementTileNumber( ep.getTileNumber() );
                int tx = Direction.getX( x, ep.getDirection() );
                int ty = Direction.getY( y, ep.getDirection() );
                _gameboard.placeTile( et, tx, ty );
            }
        }
        return true;
    }

    public List<SkillTile> getSkillsForCharacter( GameCharacter c )
    {
        List<SkillTile> skills = new ArrayList<>();

        return skills;
    }

    public List<CurseTile> getCursesForClue( ClueTile clue )
    {
        List<CurseTile> curses = new ArrayList<>();

        return curses;
    }

    /**
     * Create the 35 tiles used in every game
     */
    public List<Tile> getDefaultActionDeckTiles()
    {
        List<Tile> tiles = new ArrayList<>();

        return tiles;
    }

    public GameBoard getGameBoard(){ return _gameboard; }
    public List<Tile> getAllTiles(){ return _allTiles; }
    public List<GameCharacter> getAllCharacters(){ return _allCharacters; }
    public List<ClueTile> getAllClues(){ return _allClues; }
    public List<Player> getPlayers(){ return _players; }

    public Player getActivePlayer(){ return _players.get( _currentPlayer ); }
    public void setActivePlayer( int i )
    {
        if( i >= 0 && i < _players.size() )
            _currentPlayer = i;
    }

    public Tile getActivePlayerTile()
    {
        Player p = getActivePlayer();
        return _gameboard.getTileAt( p.getX(), p.getY() );
    }

    public int getDiceCount(){ return _diceCount; }
    public void setDiceCount( int dc ){ _diceCount = dc; }

    public List<ClueTile> getClues(){ return _clues; }

    public Deck<Tile> getActionDeck(){ return _actionDeck; }

    public Map<Integer, Deck<ExplorationTile>> getExplorationTiles(){ return _explorationTiles; }
    public Deck<ExplorationTile> getExplorationDeck( int i ){ return _explorationTiles.get( new Integer( i ) ); }
    public void shuffleExplorationTiles()
    { 
        for( Integer key: _explorationTiles.keySet() )
            _explorationTiles.get( key ).shuffle();
    }

    public List<Tile> getSatchel(){ return _satchel; }

    public List<TemporaryEventTile> getTemporaryEvents(){ return _temporaryEvents; }

    public boolean isGameOver(){ return _gameover; }
    public void setGameOver( boolean v ){ _gameover = v; }
}

