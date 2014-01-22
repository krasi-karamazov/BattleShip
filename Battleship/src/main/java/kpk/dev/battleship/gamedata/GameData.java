package kpk.dev.battleship.gamedata;

import java.util.ArrayList;
import java.util.List;

import kpk.dev.battleship.players.PlayerBase;

/**
 * Created by krasimir.karamazov on 1/22/14.
 */
public class GameData {
    private List<PlayerBase> mPlayers;
    private static GameData sInstance;
    private static Object lockObject = new Object();
    private GameData() {
        mPlayers = new ArrayList<PlayerBase>();
    }

    public static GameData getInstance(){
        synchronized (lockObject){
            if(sInstance == null) {
                sInstance = new GameData();
            }
        }

        return sInstance;
    }

    public void addPlayer(PlayerBase player) {
        mPlayers.add(player);
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
