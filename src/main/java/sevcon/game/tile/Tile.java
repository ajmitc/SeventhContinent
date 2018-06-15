package sevcon.game.tile;

import java.awt.Color;

public class Tile
{
    public static final Color GREEN = Color.GREEN;
    public static final Color GOLD  = Color.YELLOW;

    protected String _title;
    protected int _uniqueNumber;
    protected int _number;
    protected String _flavorText;
    protected Color _color; // Green or Gold
    protected TileType _type;

    public Tile( int uniqueNum, int num, Color color, String title, String flavorText, TileType type )
    {
        _uniqueNumber = uniqueNum;
        _number = num;
        _color = color;
        _title = title;
        _flavorText = flavorText;
        _type = type;
    }

    public int getUniqueNumber(){ return _uniqueNumber; }
    public int getNumber(){ return _number; }
    public Color getColor(){ return _color; }
    public String getTitle(){ return _title; }
    public String getFlavorText(){ return _flavorText; }

    public TileType getType(){ return _type; }
}

