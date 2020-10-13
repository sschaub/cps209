import model.World;

public class TestUtils {
    public static World setupWorld() {
        World.reset();
        return World.instance();
    }
}
