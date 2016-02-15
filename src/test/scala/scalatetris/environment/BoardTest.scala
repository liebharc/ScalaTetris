package scalatetris.environment
import org.junit.Test
import org.junit.Assert._

class BoardTest {
  
  @Test def testDrawEmptyBoard() {
    val board = new Board(Size(2, 3))
    val drawing = board.draw()
    assertEquals("|  |\n|  |\n|  |\n----\n", drawing)
  }
  
  @Test def testDrawBoardWithOneStone() {
    val board = new Board(Size(2, 3))
    board.stones ::= Stone(Point(1, 2))
    val drawing = board.draw()
    assertEquals("|  |\n|  |\n| x|\n----\n", drawing)
  }
  
  @Test def testDrawBoardWithSeveralStones() {
    val board = new Board(Size(2, 3))
    board.stones ::= Stone(Point(1, 2))
    board.stones ::= Stone(Point(1, 1))
    board.stones ::= Stone(Point(0, 0))
    val drawing = board.draw()
    assertEquals("|x |\n| x|\n| x|\n----\n", drawing)
  }
  
  @Test def testDrawBoardWithTwoStonesOnOneSpot() {
    val board = new Board(Size(2, 3))
    board.stones ::= Stone(Point(1, 2))
    board.stones ::= Stone(Point(1, 2))
    val drawing = board.draw()
    assertEquals("|  |\n|  |\n| x|\n----\n", drawing)
  }
}
