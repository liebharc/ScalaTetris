package scalatetris.engine

import junit.framework.TestCase
import junit.framework.Assert.assertEquals
import scalatetris.environment._

class GameEngineTest extends TestCase {
  
  def testStoneCreation {
    val board = new Board(Size(3, 3))
    val engine = new GameEngine(board)
    engine.createNewStone()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(1, 0)), board.stones.head)
  }
}
