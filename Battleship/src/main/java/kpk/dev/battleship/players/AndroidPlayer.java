package kpk.dev.battleship.players;


import java.util.Random;
import kpk.dev.battleship.gamedata.GameData;
import kpk.dev.battleship.grid.GridData;

/**
 * Created by krasimir.karamazov on 1/22/14.
 */
public class AndroidPlayer extends PlayerBase {
    private Thread mAndroidPlayerThread;
    private Random mRand;
    public AndroidPlayer(String name) {
        super();
        mRand = new Random(System.currentTimeMillis() + (long)Math.random());
        mAndroidPlayerThread = new Thread(new AndroidPlayerMover(), name);
    }

    @Override
    public void performMove(int column ,int row) {
        GameData.getInstance().performMove(column, row, getGridData());
    }

    @Override
    public void startGame() {
        mAndroidPlayerThread.start();
    }

    private class AndroidPlayerMover implements Runnable{
        @Override
        public void run() {
            try{
                while(true) {
                    int col = mRand.nextInt(11);
                    int row = mRand.nextInt(11);
                    while(!canMove(col, row)){

                        col = mRand.nextInt(11);
                        row = mRand.nextInt(11);
                    }
                    performMove(col, row);
                    Thread.sleep(1000);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
