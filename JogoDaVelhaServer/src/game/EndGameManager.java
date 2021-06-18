package game;

import communication.CommunicationAnswer;
import communication.CommunicationHandler;
import communication.CommunicationTypes;
import domain.Player;

import java.io.IOException;
import java.util.Arrays;

public class EndGameManager implements Runnable {

    private final Player p1;
    private final Player p2;
    private boolean p1Wants;
    private boolean p2Wants;

    public EndGameManager(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p1Wants = p1.isComputer();
        this.p2Wants = p2.isComputer();
    }

    public boolean isP1Wants() {
        return p1Wants;
    }

    public boolean isP2Wants() {
        return p2Wants;
    }

    @Override
    public void run() {
       runAction();
    }

    private void runAction() {
        handleForPlayer(p1, p2);
        handleForPlayer(p2, p1);
    }

    private void handleForPlayer(Player player, Player opponent) {
        try {
            CommunicationAnswer answer = CommunicationHandler.of(player.getConnectionHandler()).getMessage(
                    Arrays.asList(CommunicationTypes.LEAVE, CommunicationTypes.PLAY_AGAIN),
                    Arrays.asList(null, null)
            );
            if(answer.getType() == CommunicationTypes.LEAVE) {
                if(player.getConnectionHandler() != null) {
                    CommunicationHandler.of(player.getConnectionHandler()).sendMessage(CommunicationTypes.LEAVE);
                }
                if(opponent.getConnectionHandler() != null) {
                    CommunicationHandler.of(opponent.getConnectionHandler()).sendMessage(CommunicationTypes.LEAVE);
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
