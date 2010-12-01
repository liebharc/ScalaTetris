package scalatetris.engine

import junit.framework.TestCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import junit.framework.Assert.assertFalse
import scalatetris.environment._

class GameEngineTest extends TestCase {
  
  var board = new Board(Size(3, 3))
  var engine = new GameEngine(board)
  
  override def setUp() = {
    board = new Board(Size(3, 3))
    engine = new GameEngine(board)
  }
  
  def testStoneCreation {
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(1, 0)), board.stones.head)
  }
  
  def testStoneMoveDown {
    engine.moveDown()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(1, 1)), board.stones.head)
    
    engine.moveDown()
    assertEquals(Stone(Point(1, 2)), board.stones.head)
  }
  
  def testStoneAtBottomDown {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    assertEquals(Stone(Point(1, 2)), board.stones.last)
  }
  
  def testStoneMoveLeft {
    engine.moveLeft()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(0, 0)), board.stones.head)
  }
  
  def testStoneAtLeftEndMoveLeft {
    engine.moveLeft()
    engine.moveLeft()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(0, 0)), board.stones.head)
  }
  
  def testStoneMoveRight {
    engine.moveRight()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(2, 0)), board.stones.head)
  }
  
  def testStoneAtRightEndMoveRight {
    engine.moveRight()
    engine.moveRight()
    assertEquals(1, board.stones.length)
    assertEquals(Stone(Point(2, 0)), board.stones.head)
  }
  
  def testCollidingStonesDown {
    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()

    engine.moveLeft()
    engine.moveDown()
    engine.moveDown()
    
    assertEquals(List(Stone(Point(0,1)), Stone(Point(0,2))), board.stones.tail)
  }
  
  def testCollidingStonesLeft {
    engine.moveDown()
    engine.moveLeft()    
    
    engine.createNewStone()
    engine.moveDown()
    engine.moveLeft()    
    
    assertEquals(List(Stone(Point(1,1)), Stone(Point(0,1))), board.stones)
  }
  
  def testCollidingStonesRight {
    engine.moveDown()
    engine.moveRight()    
    
    engine.createNewStone()
    engine.moveDown()
    engine.moveRight()    
    
    assertEquals(List(Stone(Point(1,1)), Stone(Point(2,1))), board.stones)
  }
  
  def testStonesInARow {
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
    
    assertEquals(3, board.stones.size)    
    engine.moveDown()
    assertEquals(1, board.stones.size)
  }
  
  def testStonesInARowButNotAtBottom {
    engine.moveRight()
    engine.moveDown()
    engine.createNewStone()
    
    engine.moveDown()
    engine.createNewStone()
    
    engine.moveLeft()
    engine.moveDown()
    assertEquals(3, board.stones.size)
  }
  
  def testGameEndsIfNewStoneCollides {
    engine.moveDown()
    engine.moveDown()
    engine.moveDown()
    
    engine.moveDown()
    engine.moveDown()
    
    assertTrue(board.isGameRunning)
    engine.moveDown()
    assertFalse(board.isGameRunning)
  }
  
  def testGameEndsIfThereIsARowToHeaven {
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
  
  def testStonesFall {
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
    
    assertEquals(List(Stone(Point(1,2)), Stone(Point(2,2))), board.stones.tail)
  }  
}
