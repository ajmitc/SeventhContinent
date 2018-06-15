package sevcon.game.tile;

import java.awt.Color;

public class ClueTile extends Tile
{
    private String _description;
    private int _startingTile;

    public ClueTile( int uniqueNum, int num, Color color, String title, String flavorText, String desc, int startingTile )
    {
        super( uniqueNum, num, color, title, flavorText, TileType.CLUE );

        _description = desc;
        _startingTile = startingTile;
    }

    public String getDescription(){ return _description; }
    public int getStartingTile(){ return _startingTile; }
}
