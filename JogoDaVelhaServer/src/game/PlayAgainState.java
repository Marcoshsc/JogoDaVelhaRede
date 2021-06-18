package game;

public class PlayAgainState {

    private boolean p1Wants = false;
    private boolean p2Wants = false;

    public synchronized void setP1Wants() {
        p1Wants = true;
        if(p2Wants) {
            System.out.println("Create new game.");
        }
    }

    public synchronized void setP2Wants() {
        p2Wants = true;
        if(p1Wants) {
            System.out.println("Create a new game.");
        }
    }
}
