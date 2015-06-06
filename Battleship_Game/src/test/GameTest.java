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
        game.setPlayer2Name("John");
        assertEquals("John", game.getPlayer2().getName());
        game.setPlayer2Name("Player 2");
        assertEquals("Player 2", game.getPlayer2().getName());
        game.setPlayer1File("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board1.txt");
        game.setPlayer2File("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board2.txt");
        assertEquals("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board1.txt", game.getPlayer1File());
        assertEquals("/Users/Angie/Documents/MIEIC/2A2S/LPOO/praticas/Battleship/txt/board2.txt", game.getPlayer2File());
        assertEquals(1, game.getPlayer1().getNumber());
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
        game.changeShipPosition(2, Position.Instance(3, 0), Position.Instance(0, 0), true);
        assertEquals("   a b c d e f g h i j \n" +
                     " A A A A A A . . . . . \n" +
                     " B . . . . S . . . . . \n" +
                     " C . . B . . . . . . . \n" +
                     " D . . B . . . . . . . \n" +
                     " E . . B . . . . . . . \n" +
                     " F . . B D D C . . . . \n" +
                     " G . . . . . C . . . . \n" +
                     " H . . D . . C S . . . \n" +
                     " I . . D . . . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(2));
        game.changeShipPosition(2, Position.Instance(0, 0), Position.Instance(2, 2), true);
        assertEquals("   a b c d e f g h i j \n" +
                     " A A A A A A . . . . . \n" +
                     " B . . . . S . . . . . \n" +
                     " C . . B . . . . . . . \n" +
                     " D . . B . . . . . . . \n" +
                     " E . . B . . . . . . . \n" +
                     " F . . B D D C . . . . \n" +
                     " G . . . . . C . . . . \n" +
                     " H . . D . . C S . . . \n" +
                     " I . . D . . . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(2));
        game.removeShipBoard(1, 1);
        assertEquals("   a b c d e f g h i j \n" +
                     " A A A A A A . . . . . \n" +
                     " B S . . . . . . . . . \n" +
                     " C . . . . . . . . . . \n" +
                     " D . . D D . . . . . . \n" +
                     " E . . . . D . . C . . \n" +
                     " F . . . . D . . C . . \n" +
                     " G . . . . . . . C . . \n" +
                     " H . . . . . . . . . . \n" +
                     " I . . . . S . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(1));
        game.manualPlaceShip(1, 1, Position.Instance(0, 9), false);
        assertEquals("   a b c d e f g h i j \n" +
                     " A A A A A A . . . . . \n" +
                     " B S . . . . . . . . . \n" +
                     " C . . . . . . . . . . \n" +
                     " D . . D D . . . . . . \n" +
                     " E . . . . D . . C . . \n" +
                     " F . . . . D . . C . . \n" +
                     " G . . . . . . . C . . \n" +
                     " H . . . . . . . . . . \n" +
                     " I . . . . S . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(1));
        game.placeShipBoard(1, 1);
        assertEquals("   a b c d e f g h i j \n" +
                     " A A A A A A . . . . B \n" +
                     " B S . . . . . . . . B \n" +
                     " C . . . . . . . . . B \n" +
                     " D . . D D . . . . . B \n" +
                     " E . . . . D . . C . . \n" +
                     " F . . . . D . . C . . \n" +
                     " G . . . . . . . C . . \n" +
                     " H . . . . . . . . . . \n" +
                     " I . . . . S . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(1));
        game.changeShipPosition(1, Position.Instance(0, 9), Position.Instance(1, 1), true);
        game.removeShipBoard(2, 0);
        game.manualPlaceShip(2, 0, Position.Instance(0, 9), false);
        game.placeShipBoard(2, 0);
        assertEquals("   a b c d e f g h i j \n" +
                     " A . . . . . . . . . A \n" +
                     " B . . . . S . . . . A \n" +
                     " C . . B . . . . . . A \n" +
                     " D . . B . . . . . . A \n" +
                     " E . . B . . . . . . A \n" +
                     " F . . B D D C . . . . \n" +
                     " G . . . . . C . . . . \n" +
                     " H . . D . . C S . . . \n" +
                     " I . . D . . . . . . . \n" +
                     " J . . . . . . . . . . \n", game.printPlayer(2));
        game.changeShipPosition(2, Position.Instance(0, 9), Position.Instance(0, 0), true);
        game.startGame();
        assertEquals("Tony:                        Player 2:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A A A A A A . . . . .        A . . . . . . . . . . \n" +
                     " B S B B B B . . . . .        B . . . . . . . . . . \n" +
                     " C . . . . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . D D . . . . . .        D . . . . . . . . . . \n" +
                     " E . . . . D . . C . .        E . . . . . . . . . . \n" +
                     " F . . . . D . . C . .        F . . . . . . . . . . \n" +
                     " G . . . . . . . C . .        G . . . . . . . . . . \n" +
                     " H . . . . . . . . . .        H . . . . . . . . . . \n" +
                     " I . . . . S . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(1));
        assertEquals("Player 2:                    Tony:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A A A A A A . . . . .        A . . . . . . . . . . \n" +
                     " B . . . . S . . . . .        B . . . . . . . . . . \n" +
                     " C . . B . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . B . . . . . . .        D . . . . . . . . . . \n" +
                     " E . . B . . . . . . .        E . . . . . . . . . . \n" +
                     " F . . B D D C . . . .        F . . . . . . . . . . \n" +
                     " G . . . . . C . . . .        G . . . . . . . . . . \n" +
                     " H . . D . . C S . . .        H . . . . . . . . . . \n" +
                     " I . . D . . . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(2));
        game.computeState();
        assertEquals(0, game.getState());
        game.setStartingPlayer(1);
        assertEquals(1, game.getStartingPlayer());
    }

    @Test
    public void test5SaveLoad() throws Exception {
        Game game = Game.Instance();
        game.saveGame(1);
        game.resetConfig();
        assertEquals(0, game.getNumberShips());
        game.loadGame();
        game.startGame();
        assertEquals("Tony:                        Player 2:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A A A A A A . . . . .        A . . . . . . . . . . \n" +
                     " B S B B B B . . . . .        B . . . . . . . . . . \n" +
                     " C . . . . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . D D . . . . . .        D . . . . . . . . . . \n" +
                     " E . . . . D . . C . .        E . . . . . . . . . . \n" +
                     " F . . . . D . . C . .        F . . . . . . . . . . \n" +
                     " G . . . . . . . C . .        G . . . . . . . . . . \n" +
                     " H . . . . . . . . . .        H . . . . . . . . . . \n" +
                     " I . . . . S . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(1));
        assertEquals("Player 2:                    Tony:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A A A A A A . . . . .        A . . . . . . . . . . \n" +
                     " B . . . . S . . . . .        B . . . . . . . . . . \n" +
                     " C . . B . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . B . . . . . . .        D . . . . . . . . . . \n" +
                     " E . . B . . . . . . .        E . . . . . . . . . . \n" +
                     " F . . B D D C . . . .        F . . . . . . . . . . \n" +
                     " G . . . . . C . . . .        G . . . . . . . . . . \n" +
                     " H . . D . . C S . . .        H . . . . . . . . . . \n" +
                     " I . . D . . . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(2));
        assertEquals(1, game.getStartingPlayer());
        game.saveGame(2);
        game.resetConfig();
        assertEquals(0, game.getNumberShips());
        game.loadGame();
        assertEquals("Tony:                        Player 2:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A A A A A A . . . . .        A . . . . . . . . . . \n" +
                     " B S B B B B . . . . .        B . . . . . . . . . . \n" +
                     " C . . . . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . D D . . . . . .        D . . . . . . . . . . \n" +
                     " E . . . . D . . C . .        E . . . . . . . . . . \n" +
                     " F . . . . D . . C . .        F . . . . . . . . . . \n" +
                     " G . . . . . . . C . .        G . . . . . . . . . . \n" +
                     " H . . . . . . . . . .        H . . . . . . . . . . \n" +
                     " I . . . . S . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(1));
        assertEquals("Player 2:                    Tony:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A A A A A A . . . . .        A . . . . . . . . . . \n" +
                     " B . . . . S . . . . .        B . . . . . . . . . . \n" +
                     " C . . B . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . B . . . . . . .        D . . . . . . . . . . \n" +
                     " E . . B . . . . . . .        E . . . . . . . . . . \n" +
                     " F . . B D D C . . . .        F . . . . . . . . . . \n" +
                     " G . . . . . C . . . .        G . . . . . . . . . . \n" +
                     " H . . D . . C S . . .        H . . . . . . . . . . \n" +
                     " I . . D . . . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(2));
        assertEquals(2, game.getStartingPlayer());
    }

    @Test
    public void test6Play() throws Exception {
        Game game = Game.Instance();
        assertFalse(game.attackPosition(game.getPlayer2(), game.getPlayer1(), Position.Instance(0, 9)));
        assertTrue(game.attackPosition(game.getPlayer1(), game.getPlayer2(), Position.Instance(0, 0)));
        assertFalse(game.attackPosition(game.getPlayer1(), game.getPlayer2(), Position.Instance(1, 0)));
        assertTrue(game.attackPosition(game.getPlayer2(), game.getPlayer1(), Position.Instance(0, 0)));
        assertTrue(game.attackPosition(game.getPlayer2(), game.getPlayer1(), Position.Instance(1, 0)));
        assertFalse(game.attackPosition(game.getPlayer2(), game.getPlayer1(), Position.Instance(1, 0)));
        assertEquals("Tony:                        Player 2:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A * A A A A . . . . -        A * . . . . . . . . . \n" +
                     " B * B B B B . . . . .        B - . . . . . . . . . \n" +
                     " C . . . . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . D D . . . . . .        D . . . . . . . . . . \n" +
                     " E . . . . D . . C . .        E . . . . . . . . . . \n" +
                     " F . . . . D . . C . .        F . . . . . . . . . . \n" +
                     " G . . . . . . . C . .        G . . . . . . . . . . \n" +
                     " H . . . . . . . . . .        H . . . . . . . . . . \n" +
                     " I . . . . S . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(1));
        assertEquals("Player 2:                    Tony:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A * A A A A . . . . .        A * . . . . . . . . - \n" +
                     " B - . . . S . . . . .        B * . . . . . . . . . \n" +
                     " C . . B . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . B . . . . . . .        D . . . . . . . . . . \n" +
                     " E . . B . . . . . . .        E . . . . . . . . . . \n" +
                     " F . . B D D C . . . .        F . . . . . . . . . . \n" +
                     " G . . . . . C . . . .        G . . . . . . . . . . \n" +
                     " H . . D . . C S . . .        H . . . . . . . . . . \n" +
                     " I . . D . . . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(2));
        game.saveGame(1);
        game.resetConfig();
        game.loadGame();
        assertEquals("Tony:                        Player 2:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A * A A A A . . . . -        A * . . . . . . . . . \n" +
                     " B * B B B B . . . . .        B - . . . . . . . . . \n" +
                     " C . . . . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . D D . . . . . .        D . . . . . . . . . . \n" +
                     " E . . . . D . . C . .        E . . . . . . . . . . \n" +
                     " F . . . . D . . C . .        F . . . . . . . . . . \n" +
                     " G . . . . . . . C . .        G . . . . . . . . . . \n" +
                     " H . . . . . . . . . .        H . . . . . . . . . . \n" +
                     " I . . . . S . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(1));
        assertEquals("Player 2:                    Tony:\n" +
                     "   a b c d e f g h i j          a b c d e f g h i j \n" +
                     " A * A A A A . . . . .        A * . . . . . . . . - \n" +
                     " B - . . . S . . . . .        B * . . . . . . . . . \n" +
                     " C . . B . . . . . . .        C . . . . . . . . . . \n" +
                     " D . . B . . . . . . .        D . . . . . . . . . . \n" +
                     " E . . B . . . . . . .        E . . . . . . . . . . \n" +
                     " F . . B D D C . . . .        F . . . . . . . . . . \n" +
                     " G . . . . . C . . . .        G . . . . . . . . . . \n" +
                     " H . . D . . C S . . .        H . . . . . . . . . . \n" +
                     " I . . D . . . . . . .        I . . . . . . . . . . \n" +
                     " J . . . . . . . . . .        J . . . . . . . . . . \n", game.printPlayer(2));
        assertEquals(1, game.getStartingPlayer());
    }

}