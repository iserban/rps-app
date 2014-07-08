package com.iserban.app.controller;

import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.type.Game;
import com.iserban.app.model.players.Player;
import com.iserban.app.model.players.RPSHumanPlayer;
import com.iserban.app.model.players.RPSRandomComputerPlayer;
import com.iserban.app.view.GameView;

import java.util.List;

/**
 * Controller for Rock, Paper, Scissors game.
 */
public class RPSController implements Controller {
    private Game game; // model
    private GameView gameView; // view
    private final String[] gameModes = new String[] {"Human vs. Computer", "Computer vs. Computer"};
    private final String[] gameMoves;
    private Player currentPlayer;

    public RPSController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;

        // add controller as listener for view
        gameView.setController(this);
        // start view
        gameView.startView(game.getName());

        // set possible game moves
        List<Move> possibleMoves = game.getPossibleMoves();
        gameMoves = new String[possibleMoves.size()];
        for (int i=0 ; i<possibleMoves.size(); i++) {
            gameMoves[i] = possibleMoves.get(i).getName();
        }
    }

    public Game getGame() {
        return game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method called by view to get names of all available game modes.
     * @return String array of all game modes
     */
    @Override
    public String[] getGameModes() {
        return gameModes;
    }

    /**
     * Method called by view to get names of all available game moves.
     * @return String array of all game moves
     */
    @Override
    public String[] getGameMoves() {
        return gameMoves;
    }

    @Override
    public String[] getPlayerNames() {
        List<Player> playerList = game.getPlayerList();
        String[] playerNames = new String[playerList.size()];
        for (int i=0; i<playerList.size(); i++) {
            playerNames[i] = playerList.get(i).getName();
        }
        return playerNames;
    }

    @Override
    public String[] getPlayerTypes() {
        List<Player> playerList = game.getPlayerList();
        String[] playerTypes = new String[playerList.size()];
        for (int i=0; i<playerList.size(); i++) {
            playerTypes[i] = playerList.get(i).getPlayerType();
        }
        return playerTypes;
    }

    /**
     * Method called by view to select the game mode. Will instantiate players according to selected mode.
     * @param index selects game mode (0 - Human vs. Computer, 1 - Computer vs. Computer)
     */
    @Override
    public void selectGameMode(int index) {
        // add players to game
        switch (index) {
            case 0:
                game.addPlayer(new RPSHumanPlayer(game, "Player 1"));
                game.addPlayer(new RPSRandomComputerPlayer(game, "Player 2"));
                break;
            case 1:
                game.addPlayer(new RPSRandomComputerPlayer(game, "Player 1"));
                game.addPlayer(new RPSRandomComputerPlayer(game, "Player 2"));
            break;
            default:
                throw new IllegalArgumentException("Unsupported game mode index: " + index);
        }

        // tell view to display game panel
        gameView.displayGamePanel();
    }

    /**
     * Method called by view to trigger the next game round.
     */
    @Override
    public void playNextRound() {
        game.playRound();
    }

    /**
     * Method called by view to return the user's next move. Human players only.
     */
    @Override
    public void selectMove(int index) {
        Move move = game.getPossibleMoves().get(index);
        currentPlayer.setCurrentMove(move);
    }

    /**
     * Method called by model to solicit the user to select the next move. Human players only.
     * @param player
     */
    @Override
    public void promptForMove(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player can not be null.");
        }
        currentPlayer = player;
        gameView.displaySelectMoveDialog();
    }

    /**
     * Method called by view to start a new game.
     */
    @Override
    public void startNewGame() {
        game.resetGame();
        gameView.startView(game.getName());
    }
}
