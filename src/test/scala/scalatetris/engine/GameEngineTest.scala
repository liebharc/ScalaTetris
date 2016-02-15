package scalatetris.engine

import scalatetris.environment._
import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class GameEngineTest {
  
  var board: Board = _
  var engine: GameEngine = _
  
  @Before def setUp() = {
    board = new Board(Size(3, 3))
    engine = new GameEngine(board) {
      override protected def createRandomStone(start: Point) = Stone(start)
    }
  }
  
  @Test def testStoneCreation {
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(1, 0)), board.stones.head)
  }
  
  @Test def testStoneMoveDown {
    engine.moveDown()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(1, 1)), board.stones.head)
    
    engine.moveDown()
    assertEquals(Stone(Point(1, 2)), board.stones.head)
  }
  
  @Test def testStoneAtBottomDown {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    assertEquals(Stone(Point(1, 2)), board.stones.last)
  }
  
  @Test def testStoneMoveLeft {
    engine.moveLeft()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(0, 0)), board.stones.head)
  }
  
  @Test def testStoneAtLeftEndMoveLeft {
    engine.moveLeft()
    engine.moveLeft()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(0, 0)), board.stones.head)
  }
  
  @Test def testStoneMoveRight {
    engine.moveRight()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(2, 0)), board.stones.head)
  }
  
  @Test def testStoneAtRightEndMoveRight {
    engine.moveRight()
    engine.moveRight()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(2, 0)), board.stones.head)
  }
  
  @Test def testCollidingStonesDown {
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()

    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    
    assertEquals(List(Point(0,2), Point(0,1)), board.points.tail)
  }
  
  @Test def testCollidingStonesLeft {
    engine.moveDown()
    engine.moveLeft()    
    
    engine.createNewStone()
    engine.moveDown()
    engine.moveLeft()    
    
    assertEquals(List(Stone(Point(1,1)), Stone(Point(0,1))), board.stones)
  }
  
  @Test def testCollidingStonesRight {
    engine.moveDown()
    engine.moveRight()    
    
    engine.createNewStone()
    engine.moveDown()
    engine.moveRight()    
    
    assertEquals(List(Stone(Point(1,1)), Stone(Point(2,1))), board.stones)
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
    
    assertEquals(3, board.points.size)    
    engine.moveDown()
    assertEquals(1, board.points.size)
  }
  
  @Test def testStonesInARowButNotAtBottom {
    engine.moveRight()
    engine.moveDown()
    engine.createNewStone()
    
    engine.moveDown()
    engine.createNewStone()
    
    engine.moveLeft()
    engine.moveDown()
    assertEquals(3, board.stones.size)
  }
  
  @Test def testGameEndsIfNewStoneCollides {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveDown()
    engine.moveDown()
    
    assertTrue(board.isGameRunning)
    engine.moveDown()
    assertFalse(board.isGameRunning)
  }
  
  @Test def testGameEndsIfThereIsARowToHeaven {
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    
    assertTrue(board.isGameRunning)
    engine.moveLeft()
    engine.moveDown()
    assertFalse(board.isGameRunning)
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
    
    assertEquals(List(Stone(List(Point(1,2), Point(2,2)))), board.stones.tail)
  }  
}
