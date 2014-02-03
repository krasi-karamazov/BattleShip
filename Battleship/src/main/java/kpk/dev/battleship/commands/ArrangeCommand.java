package kpk.dev.battleship.commands;

import java.util.Map;

import kpk.dev.battleship.grid.GridData;
import kpk.dev.battleship.players.PlayerBase;
import kpk.dev.battleship.ui.views.pieces.ShipData;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class ArrangeCommand extends Command {
    private PlayerBase mPlayer;
    private Map<String, ShipData> mFleet;
    private GridData mGridData;
    public ArrangeCommand(PlayerBase player, Map<String, ShipData> fleet, GridData gridData) {
        mPlayer = player;
        mFleet = fleet;
        mGridData = gridData;
    }

    @Override
    public void execute(CommandExecutionListener listener) {

        if(listener != null) {
            listener.commandCompleted();
        }
    }
}
