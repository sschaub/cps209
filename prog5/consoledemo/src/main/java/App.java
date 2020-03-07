/*
 * This class contains the main method of the application
 */


import model.Critter;
import model.CritterType;
import model.World;

import java.util.Scanner;

public class App {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to World Builder!");

        boolean done = false;
        WorldCommand lastCommand = null;
        while (!done) {
            String[] cmd = getCommand();
            try {
                switch (cmd[0]) {
                    case "create":
                        CritterType type = (cmd[1].equals("tracker")) ? CritterType.TRACKER : CritterType.WANDERER;
                        var createCmd = new CreateCommand(type);
                        createCmd.execute();
                        System.out.println("Created critter: " + createCmd.getCritter());
                        lastCommand = createCmd;
                        break;
                    case "move":
                        // move id toX toY
                        var moveCmd = new MoveCommand(Integer.parseInt(cmd[1]),
                                Integer.parseInt(cmd[2]),
                                Integer.parseInt(cmd[3]));
                        moveCmd.execute();
                        System.out.println("Moved critter.");
                        lastCommand = moveCmd;
                        break;
                    case "animate":
                        System.out.println("\nMoving all critters a bit...");
                        for (var critter : World.instance().getCritters()) {
                            critter.updatePosition();
                        }
                    case "print":
                        System.out.println("\nCritters in the world:");
                        System.out.println("----------------------");
                        for (var critter : World.instance().getCritters()) {
                            System.out.println(critter);
                        }
                        break;
                    case "quit":
                        done = true;
                        break;
                    case "undo":
                        if (lastCommand != null) {
                            lastCommand.undo();
                            System.out.println("Undid last command.");
                            lastCommand = null;
                        } else {
                            System.out.println("No command to undo");
                        }
                        break;
                    default:
                        System.out.println("Unknown command " + cmd[0]);
                        break;
                }

            } catch (Exception e) {
                System.out.println("Problem executing command: " + e);

            }
        }
    }

    static String[] getCommand() {
        System.out.print("\nCommand: ");
        String cmdLine = scanner.nextLine();
        return cmdLine.split(" ");
    }
}
