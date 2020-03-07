package model;

import java.util.ArrayList;
import java.util.List;

public class World {

    private List<Critter> critters = new ArrayList<Critter>(); // list of critters in this world

    // dimensions of world
    private int width;
    private int height;

    /**
     * Creates a subclass of Critter and adds it to the list of critters
      * @param type - the type of critter to create
     * @return the created critter
     */
    public Critter create(CritterType type) {
        Critter critter = new Critter(type);
        critters.add(critter);
        return critter;
    }

    /**
     * Finds a Critter in the list of critters
     * @param id - id of critter to find
     * @return Critter with specified id, or null if no such critter is in the list
     */
    public Critter find(int id) {
        for (Critter critter : critters) {
            if (critter.getId() == id) {
                return critter;
            }
        }
        return null;
    }

    /**
     * Removes the critter with the specified id from the list of critters
     * @param id - id of critter to destroy
     * @return the destroyed Critter, or null if no critter had the specified id
     */
    public Critter remove(int id) {
        for (Critter critter : critters) {
            if (critter.getId() == id) {
                critters.remove(critter);
                return critter;
            }
        }
        return null;
    }

    // setters and getters

    public List<Critter> getCritters() {
        return critters;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    // Singleton implementation

    // prevent direct instantiation outside this class
    private World() {
        this.width = 100;
        this.height = 100;
    }

    private static World instance = new World();

    public static World instance() {
        return instance;
    }

    public static void reset() {
        instance = new World();
    }


}
