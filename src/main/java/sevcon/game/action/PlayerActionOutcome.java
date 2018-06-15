package sevcon.game.action;

import java.util.List;
import java.util.ArrayList;

/**
 * This class describes the requirements and outcomes of an available action
 */
public class PlayerActionOutcome
{
    private Integer _takeTileNumber;  // Player takes tile with this number

    public PlayerActionOutcome( Integer takeTileNumber )
    {
        _takeTileNumber = takeTileNumber;
    }

    public Integer getTakeTileNumber(){ return _takeTileNumber; }
}

