package kpk.dev.battleship.players;

import java.util.Random;
import kpk.dev.battleship.grid.GridData;

/**
 * Created by krasimir.karamazov on 1/22/14.
 */
public class AndroidPlayer extends PlayerBase {
    private Thread mAndroidPlayerThread;
    public AndroidPlayer() {
        super();
        mAndroidPlayerThread = new Thread(new AndroidPlayerMover());
    }

    @Override
    public void performMove() {
        Random rand = new Random(System.currentTimeMillis() + (long)Math.random());
        int column = rand.nextInt((GridData.NUM_COLUMNS - 1) + 1) + 1;
        int row = rand.nextInt((GridData.NUM_COLUMNS - 1) + 1) + 1;
    }

    @Override
    public void startGame() {
        mAndroidPlayerThread.start();
    }

    private class AndroidPlayerMover implements Runnable{
        @Override
        public void run() {
            while(true) {
                try{
                    Thread.currentThread().sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
