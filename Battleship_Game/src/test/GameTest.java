package test;

import logic.Game;
import logic.Position;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {

    @Test
    public void test1Setup() throws Exception {
        Game game = Game.Instance();
        game.setNumberPlayers(2);
        assertEquals(2, game.getNumberPlayers());
        game.setPlayer1Name("Tony");
        assertEquals("Tony", game.getPlayer1().getName());
        game.setPlayer2Name("Player 2");
        assertEquals("Player 2", game.getPlayer2().getName());
        game.setPlayer1File("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board1.txt");
        game.setPlayer2File("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board2.txt");
        assertEquals("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board1.txt", game.getPlayer1File());
        assertEquals("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board2.txt", game.getPlayer2File());
    }

    @Test
    public void test2Config() throws Exception {
        Game game = Game.Instance();
        game.setConfigFile("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/config.txt");
        game.readConfig();
        assertEquals(0, game.validateConfig());
        assertEquals(10, game.getDimV());
        assertEquals(10, game.getDimH());
        assertEquals(7, game.getNumberShips());
        assertEquals("A - Aircraft carrier. Size = 5.", game.printShip(1, 0));
        assertEquals("B - Battleship. Size = 4.", game.printShip(1, 1));
        assertEquals("C - Cruiser. Size = 3.", game.printShip(1, 2));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(1, 3));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(1, 4));
        assertEquals("S - Submarine. Size = 1.", game.printShip(1, 5));
        assertEquals("S - Submarine. Size = 1.", game.printShip(1, 6));
        assertEquals("A - Aircraft carrier. Size = 5.", game.printShip(2, 0));
        assertEquals("B - Battleship. Size = 4.", game.printShip(2, 1));
        assertEquals("C - Cruiser. Size = 3.", game.printShip(2, 2));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(2, 3));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(2, 4));
        assertEquals("S - Submarine. Size = 1.", game.printShip(2, 5));
        assertEquals("S - Submarine. Size = 1.", game.printShip(2, 6));
    }

    @Test
    public void test3Config() throws Exception {
        Game game = Game.Instance();
        game.resetConfig();
        assertEquals(0, game.getNumberShips());
        game.loadConfig();
        assertEquals(0, game.validateConfig());
        assertEquals(10, game.getDimV());
        assertEquals(10, game.getDimH());
        assertEquals(7, game.getNumberShips());
        assertEquals("A - Aircraft carrier. Size = 5.", game.printShip(1, 0));
        assertEquals("B - Battleship. Size = 4.", game.printShip(1, 1));
        assertEquals("C - Cruiser. Size = 3.", game.printShip(1, 2));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(1, 3));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(1, 4));
        assertEquals("S - Submarine. Size = 1.", game.printShip(1, 5));
        assertEquals("S - Submarine. Size = 1.", game.printShip(1, 6));
        assertEquals("A - Aircraft carrier. Size = 5.", game.printShip(2, 0));
        assertEquals("B - Battleship. Size = 4.", game.printShip(2, 1));
        assertEquals("C - Cruiser. Size = 3.", game.printShip(2, 2));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(2, 3));
        assertEquals("D - Destroyer. Size = 2.", game.printShip(2, 4));
        assertEquals("S - Submarine. Size = 1.", game.printShip(2, 5));
        assertEquals("S - Submarine. Size = 1.", game.printShip(2, 6));
    }

    @Test
    public void test4Place() throws Exception {
        Game game = Game.Instance();
        Random random = new Random(0);
        game.autoPlaceShips(random, 1);
        assertEquals("   a b c d e f g h i j \n" +
                     " A . . . . . . . . A . \n" +
                     " B S B B B B . . . A . \n" +
                     " C . . . . . . . . A . \n" +
                     " D . . D D . . . . A . \n" +
                     " E . . . . D . . C A . \n" +
                     " F . . . . D . . C . . \n" +
                     " G . . . . . . . C . . \n" +
                     " H . . . . . . . . . . \n" +
                     " I . . . . S . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(1));
        game.autoPlaceShips(random, 2);
        assertEquals("   a b c d e f g h i j \n" +
                     " A . . . . . . . . . . \n" +
                     " B . . . . S . . . . . \n" +
                     " C A . B . . . . . . . \n" +
                     " D A . B . . . . . . . \n" +
                     " E A . B . . . . . . . \n" +
                     " F A . B D D C . . . . \n" +
                     " G A . . . . C . . . . \n" +
                     " H . . D . . C S . . . \n" +
                     " I . . D . . . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(2));
        game.changeShipPosition(1, Position.Instance(0, 8), Position.Instance(0, 0), true);
        assertEquals("   a b c d e f g h i j \n" +
                     " A A A A A A . . . . . \n" +
                     " B S B B B B . . . . . \n" +
                     " C . . . . . . . . . . \n" +
                     " D . . D D . . . . . . \n" +
                     " E . . . . D . . C . . \n" +
                     " F . . . . D . . C . . \n" +
                     " G . . . . . . . C . . \n" +
                     " H . . . . . . . . . . \n" +
                     " I . . . . S . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(1));

    }
}