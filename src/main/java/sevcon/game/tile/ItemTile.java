package sevcon.game.tile;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import sevcon.game.action.PlayerAction;

public class ItemTile extends Tile
{
    private int _initialDurability;
    private String _description;
    private List<PlayerAction> _availableActions;
    private List<String>       _keywords;

    public ItemTile( int uniqueNum, int num, Color color, String title, String flavorText, int durability, String desc )
    {
        super( uniqueNum, num, color, title, flavorText, TileType.ITEM );

        _initialDurability = durability;
        _description = desc;
        _availableActions = new ArrayList<>();
        _keywords = new ArrayList<>();
    }

    public int getInitialDurability(){ return _initialDurability; }
    public String getDescription(){ return _description; }
    public List<PlayerAction> getAvailableActions(){ return _availableActions; }
    public List<String > getKeywords(){ return _keywords; }
}
