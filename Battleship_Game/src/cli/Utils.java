package cli;

import java.io.IOException;

/**
 * Class Utils contains the method clearScreen for the command line interface
 */
public class Utils {

    /** boolean that indicates if the application is run inside de IDE */
    private static boolean ide;

    /** boolean that indicates if the application is run in a *nix type machine */
    private static boolean nix;

    /**
     * Instantiates a new Utils
     *
     * @param newIDE intended value for ide
     * @param newNIX intended value for nix
     */
    Utils(boolean newIDE, boolean newNIX) {
        ide = newIDE;
        nix = newNIX;
    }

    /**
     * clears the console screen
     */
    public static void clearScreen() {
        if (ide) {
            for (int i = 0; i < 20; i++) System.out.println();
        }
        else if (nix) {
            System.out.print("\033c");
        }
        else {
            try {
                Runtime.getRuntime().exec("cls");
            } catch (IOException except) {
                for (int i = 0; i < 20; i++) System.out.println();
            }
        }
    }

}
