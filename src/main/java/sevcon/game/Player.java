package sevcon.game;

import java.util.List;
import java.util.ArrayList;

import sevcon.game.character.GameCharacter;
import sevcon.game.tile.Tile;

public class Player
{
    private String _name;
    private GameCharacter _character;

    private List<Tile> _inventory;

    private List<StateTile> _states;

    // Player location, must match a Tile in GameBoard
    private int _x, _y;

    public Player( String name, GameCharacter character )
    {
        _name = name;
        _character = character;
        _inventory = new ArrayList<>();
        _states = new ArrayList<>();
        _x = 0;
        _y = 0;
    }

    public String getName(){ return _name; }
    public void setName( String n ){ _name = n; }

    public GameCharacter getCharacter(){ return _character; }
    public void setCharacter( GameCharacter c ){ _character = c; }

    public List<Tile> getInventory(){ return _inventory; }
    public List<StateTile> getStates(){ return _states; }

    public int getX(){ return _x; }
    public int getY(){ return _y; }
    public void setLocation( int x, int y ){ _x = x; _y = y; }
}

