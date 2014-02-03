package kpk.dev.battleship.commands;

import kpk.dev.battleship.grid.GridData;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class MoveCommand extends Command {

    private GridData mData;
    private int mColumn;
    private int mRow;
    public MoveCommand(GridData data, int column, int row) {
        mData = data;
        mColumn = column;
        mRow = row;
    }

    @Override
    public void execute(CommandExecutionListener listener) {
        mData.selectCell(mColumn, mRow);
        if(listener != null) {
            listener.commandCompleted();
        }
    }
}
