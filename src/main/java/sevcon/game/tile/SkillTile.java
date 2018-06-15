package sevcon.game.tile;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import sevcon.game.action.PlayerAction;

public class SkillTile extends Tile
{
    private int _cost;
    private float _successes;

    private List<PlayerAction>         _availableActions;
    //private List<SkillPlacement> _explorationPlacements;

    public SkillTile( int uniqueNum, int num, Color color, String title, String flavorText, int cost, float successes )
    {
        super( uniqueNum, num, color, title, flavorText, TileType.EXPLORATION );

        _cost = cost;
        _successes = successes;

        _availableActions      = new ArrayList<>();
        //_explorationPlacements = new ArrayList<>();
    }

    public int getCost(){ return _cost; }
    public List<PlayerAction> getAvailableActions(){ return _availableActions; }
    //public List<SkillPlacement> getSkillPlacements(){ return _explorationPlacements; }
}
