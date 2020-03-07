import model.CritterType;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CommandTests {
    @Test
    public void testCreate() {
        var world = TestUtils.setupWorld();
        var createCmd = new CreateCommand(CritterType.TRACKER);
        createCmd.execute();

        assertEquals(1, world.getCritters().size());
        assertTrue(createCmd.getCritter() != null);
        assertTrue(createCmd.getCritter().getId() != 0);

        var createCmd2 = new CreateCommand(CritterType.TRACKER);
        createCmd2.execute();
        assertEquals(2, world.getCritters().size());
        assertTrue(createCmd.getCritter().getId() != createCmd2.getCritter().getId());

        createCmd2.undo();
        assertEquals(1, world.getCritters().size());
        assertEquals(createCmd.getCritter(), world.find(createCmd.getCritter().getId()));

    }

}