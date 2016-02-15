package scalatetris.engine

import junit.framework.TestCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import junit.framework.Assert.assertFalse
import scalatetris.environment._

class GameEngineWithSquaresTest extends TestCase {

  var board: Board = _
  var engine: GameEngine = _
  
  override def setUp() = {
    board = new Board(Size(5, 5))
    engine = new GameEngine(board) {
      override protected def createRandomStone(start: Point) = Square(start)
    }
  }
  
  def testStoneAtBottomDown {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    assertEquals(Stone(List(Point(2, 4), Point(3, 4), Point(2, 3), Point(3, 3))), 
                 board.stones.last)
  }
  
  def testStoneMoveLeft {
    engine.moveLeft()
    assertEquals(List(Point(1, 0), Point(2, 0), Point(1,1), Point(2, 1)), board.points)
  }
}
