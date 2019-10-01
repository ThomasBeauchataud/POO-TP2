package thread;

import entity.Pigeon;
import entity.Position;

public class MovePigeon implements Runnable {

    private Pigeon pigeon;
    private Position positionToReach;

    public MovePigeon(Pigeon pigeon, Position positionToReach) {
        this.pigeon = pigeon;
        this.positionToReach = positionToReach;
    }

    @Override
    public void run() {
        while(true) {
            this.pigeon.moveTo(this.positionToReach);
            try {
                Thread.sleep(this.pigeon.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
