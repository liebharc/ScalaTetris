package scalatetris.environment
import scalatetris.engine._
import org.junit._
import org.junit.Assert._

/**
 * Rotation definitions are taken from http://tetris.wikia.com/wiki/DTET_Rotation_System
 */
class LineTest {
  
  var engine: GameEngine = _
  
  @Before def setUp() = {
    engine = new GameEngine(Size(5, 5), OnlyLinesStoneFactory)
  }
  
  @Test def drawLineTest {
    val drawing = engine.drawBoardOnly()
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
    val drawing = engine.drawBoardOnly()
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
    val drawing = engine.drawBoardOnly()
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
    val drawing = engine.drawBoardOnly()
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
    val drawing = engine.drawBoardOnly()
    val expected = 
      "|     |\n" +
      "|     |\n" +
      "|xxxx |\n" +
      "|     |\n" +
      "|     |\n" +
      "-------\n"
    assertEquals(expected, drawing)
  }
  
  @Test def toTopCenerTest {
    val stone = engine.stones.head
    val moved = 
      stone
      .moveDown()
      .moveDown()
      .moveLeft()
    val upAgain = moved.toTopCenter(Point(2, 0))
    assertEquals(stone, upAgain)
  }
}
