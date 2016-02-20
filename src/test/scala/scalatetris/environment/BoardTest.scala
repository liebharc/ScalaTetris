package scalatetris.environment
import org.junit.Test
import org.junit.Assert._

class BoardTest {
  
  @Test def testDrawEmptyBoard() {
    val board = new Board(Size(2, 3), Stone(Nil), Stone(Nil))
    val drawing = board.drawBoardOnly()
    val expected = 
      "|  |\n" +
      "|  |\n" +
      "|  |\n" +
      "----\n"
    assertEquals(expected, drawing)
  }
  
  @Test def testDrawBoardWithOneStone() {
    val board = new Board(Size(2, 3), Stone(Nil), Stone(Nil))
    board.stones ::= Stone(Point(1, 2))
    val drawing = board.drawBoardOnly()
    val expected = 
      "|  |\n" +
      "|  |\n" +
      "| x|\n" +
      "----\n"
    assertEquals(expected, drawing)
  }
  
  @Test def testDrawBoardWithSeveralStones() {
    val board = new Board(Size(2, 3), Stone(Nil), Stone(Nil))
    board.stones ::= Stone(Point(1, 2))
    board.stones ::= Stone(Point(1, 1))
    board.stones ::= Stone(Point(0, 0))
    val drawing = board.drawBoardOnly()
    val expected = 
      "|x |\n" +
      "| x|\n" +
      "| x|\n" +
      "----\n"
    assertEquals(expected, drawing)
  }
  
  @Test def testDrawBoardWithTwoStonesOnOneSpot() {
    val board = new Board(Size(2, 3), Stone(Nil), Stone(Nil))
    board.stones ::= Stone(Point(1, 2))
    board.stones ::= Stone(Point(1, 2))
    val drawing = board.drawBoardOnly()
    val expected = 
      "|  |\n" +
      "|  |\n" +
      "| x|\n" +
      "----\n"
    assertEquals(expected, drawing)
  }
}
