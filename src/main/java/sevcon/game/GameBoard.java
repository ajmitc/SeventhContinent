package sevcon.game;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

import sevcon.game.tile.Tile;

/**
 * Holds the Terrain/Event/Exploration/etc tiles 
 */
public class GameBoard
{
    private List<PlacedTile> _tiles;

    public GameBoard()
    {
        _tiles = new ArrayList<>();
    }

    public Tile getTileAt( int x, int y )
    {
        for( PlacedTile pt: _tiles )
        {
            if( pt.x == x && pt.y == y )
                return pt.tile;
        }
        return null;
    }

    public List<Tile> getNeighbors( int x, int y )
    {
        return _tiles.stream()
            .filter( pt -> Math.abs( pt.x - x ) <= 1 && Math.abs( pt.y - y ) <= 1 ) // Only include tiles that are at most 1 tile away
            .filter( pt -> pt.x != x && pt.y != y ) // Do not include the center tile
            .collect( Collectors.toList() );
    }

    public void placeTile( Tile tile, int x, int y )
    {
        _tiles.add( new PlacedTile( tile, x, y ) );
    }

    private static class PlacedTile
    {
        public Tile tile;
        public int x;
        public int y;

        public PlacedTile( Tile tile, int x, int y )
        {
            this.tile = tile;
            this.x = x;
            this.y = y;
        }
    }
}

