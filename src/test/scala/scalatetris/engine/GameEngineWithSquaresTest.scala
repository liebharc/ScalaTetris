package scalatetris.engine

import scalatetris.environment._
import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class GameEngineWithSquaresTest {
  var engine: GameEngine = _
  
  @Before def setUp() = {
    engine = new GameEngine(Size(5, 5), OnlySquaresStoneFactory)
  }
  
  @Test def testStoneAtBottomDown {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    assertEquals(Stone(List(Point(2, 4), Point(3, 4), Point(2, 3), Point(3, 3))), 
                 engine.stones.last)
  }
  
  @Test def testStoneMoveLeft {
    engine.moveLeft()
    assertEquals(List(Point(1, 0), Point(2, 0), Point(1,1), Point(2, 1)), engine.points)
  }
}
