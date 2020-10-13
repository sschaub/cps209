import model.Critter;
import model.CritterType;
import model.World;

public class CreateCommand extends WorldCommand {

    private CritterType critterType;

    private Critter critter; // the critter created by this command

    public CreateCommand(CritterType critterType) {
        this.critterType = critterType;
    }

    @Override
    public void execute() {
        this.critter = World.instance().create(critterType);
    }

    @Override
    public void undo() {
        World.instance().remove(critter.getId());
    }

    public Critter getCritter() {
        return critter;
    }

}
