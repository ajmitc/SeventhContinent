package sevcon.game.tile;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import sevcon.game.action.PlayerAction;

public class ExplorationTile extends Tile
{
    private int _area;
    private List<PlayerAction>         _availableActions;
    //private List<ExplorationPlacement> _explorationPlacements;
    private int _replacementTileNumber; // Replace this Exploration Tile with the Tile with this number

    public ExplorationTile( int uniqueNum, int num, Color color, String flavorText, int area )
    {
        super( uniqueNum, num, color, "", flavorText, TileType.EXPLORATION );

        _area = area;
        _availableActions      = new ArrayList<>();
        //_explorationPlacements = new ArrayList<>();
        _replacementTileNumber = -1;
    }

    public int getArea(){ return _area; }
    public List<PlayerAction> getAvailableActions(){ return _availableActions; }
    //public List<ExplorationPlacement> getExplorationPlacements(){ return _explorationPlacements; }

    public int getReplacementTileNumber(){ return _replacementTileNumber; }
    public void setReplacementTileNumber( int n ){ _replacementTileNumber = n; }
}
