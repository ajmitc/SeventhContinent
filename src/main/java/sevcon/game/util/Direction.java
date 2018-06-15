package sevcon.game.util;

public enum Direction
{
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static int getX( int fromX, Direction d )
    {
        if( d == EAST )
            return fromX + 1;
        if( d == WEST )
            return fromX - 1;
        return fromX;
    }

    public static int getY( int fromY, Direction d )
    {
        if( d == NORTH )
            return fromY + 1;
        if( d == SOUTH )
            return fromY - 1;
        return fromY;
    }
}

