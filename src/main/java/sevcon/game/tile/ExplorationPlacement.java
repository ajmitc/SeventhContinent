package sevcon.game.tile;

import sevcon.game.util.Direction;

public class ExplorationPlacement
{
    private int _tileNumber;
    private Direction _direction;
    private int _area;

    public ExplorationPlacement( int tileNum, Direction dir, int area )
    {
        _tileNumber = tileNum;
        _direction = dir;
        _area = area;
    }

    public int getTileNumber(){ return _tileNumber; }
    public Direction getDirection(){ return _direction; }
    public int getArea(){ return _area; }
}

