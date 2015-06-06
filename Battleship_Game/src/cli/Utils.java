package cli;

import java.io.IOException;

public class Utils {

    private static boolean ide;

    private static boolean nix;

    Utils(boolean newIDE, boolean newNIX) {
        ide = newIDE;
        nix = newNIX;
    }

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
