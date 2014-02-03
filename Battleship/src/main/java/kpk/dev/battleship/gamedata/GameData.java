package kpk.dev.battleship.gamedata;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import kpk.dev.battleship.grid.GridData;
import kpk.dev.battleship.players.PlayerBase;
import kpk.dev.battleship.ui.views.pieces.builders.ShipBuilder;

/**
 * Created by krasimir.karamazov on 1/22/14.
 */
public class GameData {
    private List<PlayerBase> mPlayers;
    private static GameData sInstance;
    private static Lock lock = new ReentrantLock();
    private List<GridData> mGrids;
    private GameData() {
        mPlayers = new LinkedList<PlayerBase>();
        mGrids = new LinkedList<GridData>();
    }

    public synchronized static GameData getInstance(){
        if(sInstance == null) {
            sInstance = new GameData();
        }

        return sInstance;
    }

    public void addPlayer(PlayerBase player) {
        GridData data = GridData.generate(ShipBuilder.buildFleet());
        player.setGridData(data);
        mPlayers.add(player);
        mGrids.add(data);
    }

    public void performMove(int column ,int row, GridData gridData) {

    }

    public List<PlayerBase> getPlayers(){
        return mPlayers;
    }

    public int getPlayersNum() {
        return mPlayers.size();
    }

    public void startGame() {
        for(PlayerBase player : mPlayers) {
            player.startGame();
        }
    }
}
