package sevcon.game.action;

import java.util.List;
import java.util.ArrayList;

/**
 * This class describes the requirements and outcomes of an available action
 */
public class PlayerAction
{
    private boolean _mandatory;
    private GameAction _action; // ie. Dig
    private int _minCardsToDraw;
    private int _minSuccesses;
    private boolean _costLocked;
    private List<PlayerActionOutcome> _successOutcomes;
    private List<PlayerActionOutcome> _failureOutcomes;

    public PlayerAction( GameAction action, boolean mandatory, int minDraw, int minSuccesses, boolean costLocked )
    {
        _action = action;
        _mandatory = mandatory;
        _minCardsToDraw = minDraw;
        _minSuccesses = minSuccesses;
        _costLocked = costLocked;
        _successOutcomes = new ArrayList<>();
        _failureOutcomes = new ArrayList<>();
    }

    public GameAction getGameAction(){ return _action; }
    public boolean isMandatory(){ return _mandatory; }
    public int getMinCardsToDraw(){ return _minCardsToDraw; }
    public int getMinSuccesses(){ return _minSuccesses; }
    public boolean isCostLocked(){ return _costLocked; }
    public List<PlayerActionOutcome> getSuccessOutcomes(){ return _successOutcomes; }
    public List<PlayerActionOutcome> getFailureOutcomes(){ return _failureOutcomes; }
}

