package scalatetris.environment
import org.junit.Test
import org.junit.Assert._
import scalatetris.engine._

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
    val engine = new GameEngine(Size(2, 3), OnlyPointsStoneFactory)
    engine.moveDown()
    engine.moveDown()
    engine.moveRight()
    val drawing = engine.drawBoardOnly()
    val expected = 
      "|  |\n" +
      "|  |\n" +
      "| x|\n" +
      "----\n"
    assertEquals(expected, drawing)
  }
  
  @Test def testDrawBoardWithSeveralStones() {
    val engine = new GameEngine(Size(2, 3), OnlyPointsStoneFactory)
    engine.moveDown()
    engine.moveDown()
    engine.moveRight()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    engine.moveRight()
    engine.moveLeft()
    val drawing = engine.drawBoardOnly()
    val expected = 
      "|x |\n" +
      "| x|\n" +
      "| x|\n" +
      "----\n"
    assertEquals(expected, drawing)
  }
}
