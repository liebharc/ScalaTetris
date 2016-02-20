package scalatetris.engine

import scalatetris.environment._
import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class GameEngineTest {
  var engine: GameEngine = _
  
  @Before def setUp() = {
    engine = new GameEngine(Size(3, 3), OnlyPointsStoneFactory)
  }
  
  @Test def testStoneCreation {
    assertEquals(1, engine.stones.length)
    assertEquals(Stone(Point(1, 0)), engine.stones.head)
  }
  
  @Test def testStoneMoveDown {
    engine.moveDown()
    assertEquals(1, engine.stones.length)
    assertEquals(Stone(Point(1, 1)), engine.stones.head)
    
    engine.moveDown()
    assertEquals(Stone(Point(1, 2)), engine.stones.head)
  }
  
  @Test def testStoneAtBottomDown {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    assertEquals(Stone(Point(1, 2)), engine.stones.last)
  }
  
  @Test def testStoneMoveLeft {
    engine.moveLeft()
    assertEquals(1, engine.stones.length)
    assertEquals(Stone(Point(0, 0)), engine.stones.head)
  }
  
  @Test def testStoneAtLeftEndMoveLeft {
    engine.moveLeft()
    engine.moveLeft()
    assertEquals(1, engine.stones.length)
    assertEquals(Stone(Point(0, 0)), engine.stones.head)
  }
  
  @Test def testStoneMoveRight {
    engine.moveRight()
    assertEquals(1, engine.stones.length)
    assertEquals(Stone(Point(2, 0)), engine.stones.head)
  }
  
  @Test def testStoneAtRightEndMoveRight {
    engine.moveRight()
    engine.moveRight()
    assertEquals(1, engine.stones.length)
    assertEquals(Stone(Point(2, 0)), engine.stones.head)
  }
  
  @Test def testCollidingStonesDown {
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()

    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    
    assertEquals(List(Point(0,2), Point(0,1)), engine.points.tail)
  }
  
  @Test def testCollidingStonesLeft {
    engine.moveDown()
    engine.moveLeft()    
    
    engine.forceNewStone()
    engine.moveDown()
    engine.moveLeft()    
    
    assertEquals(List(Stone(Point(1,1)), Stone(Point(0,1))), engine.stones)
  }
  
  @Test def testCollidingStonesRight {
    engine.moveDown()
    engine.moveRight()    
    
    engine.forceNewStone()
    engine.moveDown()
    engine.moveRight()    
    
    assertEquals(List(Stone(Point(1,1)), Stone(Point(2,1))), engine.stones)
  }
  
  @Test def testStonesInARow {
    engine.moveRight()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    
    assertEquals(3, engine.points.size)    
    engine.moveDown()
    assertEquals(1, engine.points.size)
  }
  
  @Test def testStonesInARowButNotAtBottom {
    engine.moveRight()
    engine.moveDown()
    engine.forceNewStone()
    
    engine.moveDown()
    engine.forceNewStone()
    
    engine.moveLeft()
    engine.moveDown()
    assertEquals(3, engine.stones.size)
  }
  
  @Test def testGameEndsIfNewStoneCollides {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveDown()
    engine.moveDown()
    
    assertTrue(engine.isGameRunning)
    engine.moveDown()
    assertFalse(engine.isGameRunning)
  }
  
  @Test def testGameEndsIfThereIsARowToHeaven {
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    
    assertTrue(engine.isGameRunning)
    engine.moveLeft()
    engine.moveDown()
    assertFalse(engine.isGameRunning)
  }
  
  @Test def testStonesFall {
    engine.moveRight()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveRight()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveDown()
    engine.moveDown()
    
    /*
     * |   |
     * |xx |
     * |xxx|
     * -----
     * 
     * should become
     * |   |
     * |   |
     * |xx |
     * ----- 
     */
    
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    assertEquals(List(Stone(List(Point(1,2), Point(2,2)))), engine.stones.tail)
    assertEquals(engine.statistics.rowsCompleted, 1)
  }  
}
