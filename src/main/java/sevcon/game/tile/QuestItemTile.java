package sevcon.game.tile;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import sevcon.game.action.PlayerAction;

public class QuestItemTile extends Tile
{
    private String _description;
    private List<PlayerAction> _availableActions;
    private List<String>       _keywords;

    public QuestItemTile( int uniqueNum, int num, Color color, String title, String flavorText, String desc )
    {
        super( uniqueNum, num, color, title, flavorText, TileType.QUEST_ITEM );

        _description = desc;
        _availableActions = new ArrayList<>();
        _keywords = new ArrayList<>();
    }

    public String getDescription(){ return _description; }
    public List<PlayerAction> getAvailableActions(){ return _availableActions; }
    public List<String > getKeywords(){ return _keywords; }
}
