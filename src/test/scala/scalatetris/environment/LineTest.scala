package scalatetris.environment
import scalatetris.engine._
import org.junit._
import org.junit.Assert._

/**
 * Rotation definitions are taken from http://tetris.wikia.com/wiki/DTET_Rotation_System
 */
class LineTest {
  
  var board: Board = _
  var engine: GameEngine = _
  
  @Before def setUp() = {
    board = new Board(Size(5, 5))
    engine = new GameEngine(board, OnlyLinesStoneFactory)
  }
  
  @Test def drawLineTest {
    val drawing = board.drawBoardOnly()
    val expected = 
      "|  x  |\n" +
      "|  x  |\n" +
      "|  x  |\n" +
      "|  x  |\n" +
      "|     |\n" +
      "-------\n"
    assertEquals(expected, drawing)
  }
  
  @Test def rotateLeftTest {
    engine.rotateLeft()
    val drawing = board.drawBoardOnly()
    val expected = 
      "|     |\n" +
      "|     |\n" +
      "| xxxx|\n" +
      "|     |\n" +
      "|     |\n" +
      "-------\n"
    assertEquals(expected, drawing)
  }
  
  @Test def rotateRightTest {
    engine.rotateRight()
    val drawing = board.drawBoardOnly()
    val expected = 
      "|     |\n" +
      "|     |\n" +
      "|xxxx |\n" +
      "|     |\n" +
      "|     |\n" +
      "-------\n"
    assertEquals(expected, drawing)
  }
  
  @Test def rotateRightTwiceTest {
    engine.rotateRight()
    engine.rotateRight()
    val drawing = board.drawBoardOnly()
    val expected = 
      "| x   |\n" +
      "| x   |\n" +
      "| x   |\n" +
      "| x   |\n" +
      "|     |\n" +
      "-------\n"
    assertEquals(expected, drawing)
  }
  
  @Test def rotationNotPossibleTest {
    engine.rotateRight()
    val drawing = board.drawBoardOnly()
    val expected = 
      "|     |\n" +
      "|     |\n" +
      "|xxxx |\n" +
      "|     |\n" +
      "|     |\n" +
      "-------\n"
    assertEquals(expected, drawing)
  }
}
