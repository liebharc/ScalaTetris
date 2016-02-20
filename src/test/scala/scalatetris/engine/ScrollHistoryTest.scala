package scalatetris.engine

import scalatetris.environment._
import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class ScrollHistoryTest {
  var engine: GameEngine = _
  
  @Before def setUp() = {
    engine = new GameEngine(Size(3, 3), OnlyPointsStoneFactory)
    engine.moveDown()
  }
  
  @Test def goBackwardTest {
    val stones = engine.stones
    engine.moveDown
    engine.backwardInTime()
    engine.backwardInTime()
    assertEquals(stones, engine.stones)
    assertEquals(false, engine.isGameRunning)
  }
  
  @Test def goForwardTest {
    engine.moveDown
    var future = engine.stones
    engine.backwardInTime()
    engine.backwardInTime()
    engine.backIntoTheFuture()
    engine.backIntoTheFuture()
    assertEquals(future, engine.stones)
    assertEquals(false, engine.isGameRunning)
  }
}
