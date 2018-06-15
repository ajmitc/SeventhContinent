package sevcon.game.tile;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import sevcon.game.Resource;
import sevcon.game.action.PlayerAction;

public class TemporaryEventTile extends Tile
{
    private String _description;
    private List<PlayerAction> _availableActions;

    public TemporaryEventTile( int uniqueNum, int num, Color color, String title, String flavorText, String desc )
    {
        super( uniqueNum, num, color, title, flavorText, TileType.TEMPORARY_EVENT );

        _description = desc;
        _availableActions = new ArrayList<>();
    }

    public String getDescription(){ return _description; }
    public List<PlayerAction> getAvailableActions(){ return _availableActions; }
}
