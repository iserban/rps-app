package com.iserban.app.controller;

import com.iserban.app.model.players.Player;

/**
 * Interface for game controller.
 */
public interface Controller {

    public String[] getGameModes();

    public String[] getGameMoves();

    public String[] getPlayerNames();

    public String[] getPlayerTypes();

    public void selectGameMode(int index);

    public void playNextRound();

    public void selectMove(int index);

    public void startNewGame();

    public void promptForMove(Player player);
}
