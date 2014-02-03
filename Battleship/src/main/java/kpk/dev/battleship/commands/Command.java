package kpk.dev.battleship.commands;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public abstract class Command {

   public abstract void execute(CommandExecutionListener listener);
}
