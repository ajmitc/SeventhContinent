package sevcon.game.tile;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import sevcon.game.Resource;
import sevcon.game.action.PlayerAction;

public class TerrainTile extends Tile
{
    private List<PlayerAction>         _availableActions;
    private List<Resource>             _availableResources;
    private List<ExplorationPlacement> _explorationPlacements;

    public TerrainTile( int uniqueNum, int num, Color color, String flavorText )
    {
        super( uniqueNum, num, color, "", flavorText, TileType.TERRAIN );

        _availableActions      = new ArrayList<>();
        _availableResources    = new ArrayList<>();
        _explorationPlacements = new ArrayList<>();
    }

    public List<PlayerAction> getAvailableActions(){ return _availableActions; }
    public List<Resource> getAvailableResources(){ return _availableResources; }
    public List<ExplorationPlacement> getExplorationPlacements(){ return _explorationPlacements; }
}
