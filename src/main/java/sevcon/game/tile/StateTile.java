package sevcon.game.tile;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import sevcon.game.action.PlayerAction;

public class StateTile extends Tile
{
    private String _description;
    private List<PlayerAction> _availableActions;

    public StateTile( int uniqueNum, int num, Color color, String title, String flavorText, String desc )
    {
        super( uniqueNum, num, color, title, flavorText, TileType.STATE );

        _description = desc;
        _availableActions = new ArrayList<>();
    }

    public String getDescription(){ return _description; }
    public List<PlayerAction> getAvailableActions(){ return _availableActions; }
}
