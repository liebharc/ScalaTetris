package scalatetris.engine

import junit.framework.TestCase
import junit.framework.Assert.assertEquals
import scalatetris.environment._

class GameEngineTest extends TestCase {
  val board = new Board(Size(3, 3))
  val engine = new GameEngine(board)
  
  def testStoneCreation {
    engine.createNewStone()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(1, 0)), board.stones.head)
  }
  
  def testStoneMoveDown {
    engine.createNewStone()
    engine.moveStone("down")
    assertEquals(Stone(Point(1, 1)), board.stones.head)
  }
}
