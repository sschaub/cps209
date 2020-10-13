import model.Critter;
import model.CritterType;
import org.junit.Test;
import static org.junit.Assert.*;

public class WorldTest {
    @Test
    public void testCreate() {
        var world = TestUtils.setupWorld();

        Critter tracker = world.create(CritterType.TRACKER);
        assertTrue(tracker.getId() != 0);
        assertTrue(tracker.getX() < world.getWidth());
        assertEquals(1, world.getCritters().size());

        Critter wanderer = world.create(CritterType.WANDERER);
        assertTrue(tracker.getId() != 0);
        assertTrue(tracker.getX() < world.getWidth());
        assertEquals(2, world.getCritters().size());
    }

    @Test
    public void testGetById() {
        var world = TestUtils.setupWorld();
        Critter tracker = world.create(CritterType.TRACKER);
        Critter wanderer = world.create(CritterType.WANDERER);

        assertEquals(tracker, world.find(tracker.getId()));
        assertEquals(wanderer, world.find(wanderer.getId()));
        assertEquals(null, world.find(0));
    }
}
