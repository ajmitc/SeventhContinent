package sevcon.game.tile;

import java.awt.Color;

import java.util.List;
import java.util.ArrayList;

import sevcon.game.action.PlayerAction;

public class CurseTile extends Tile
{
    private String _description;
    //private List<PlayerAction>         _availableActions;

    public CurseTile( int uniqueNum, int num, Color color, String title, String flavorText, String desc )
    {
        super( uniqueNum, num, color, title, flavorText, TileType.CURSE );

        _description = desc;
        //_availableActions      = new ArrayList<>();
    }

    public String getDescription(){ return _description; }
    //public List<PlayerAction> getAvailableActions(){ return _availableActions; }
}
