import model.Critter;
import model.World;

public class MoveCommand extends WorldCommand {

    private int critterId;
    private int toX, toY;

    public MoveCommand(int critterId, int toX, int toY) {
        this.critterId = critterId;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public void execute() {
        Critter critter = World.instance().find(critterId);
        if (critter != null) {
            critter.setX(toX);
            critter.setY(toY);
        }
    }

    @Override
    public void undo() {

    }
}
