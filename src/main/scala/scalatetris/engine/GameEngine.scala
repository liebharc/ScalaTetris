package scalatetris.engine

import scalatetris.environment._
import java.util.Calendar 

sealed class GameEngine (val board: Board, val stoneFactory: StoneFactory) {
  
  createNewStone()
  
  def createNewStone() {
    val newStone = stoneFactory.createRandomStone(Point((board.size.width / 2), 0))
    if (board.stones.exists(s => s.doesCollide(newStone)) ||
        (!board.stones.isEmpty && board.stones.head.isOnTop))
      board.endGame()
    else
      board.stones ::= newStone
  }
  
  def moveDown() {
     if (!move(s => s.moveDown())) {
      val (points, numberOfRemovedRows) = removeFullRows(board.points)
      board.points = points
      board.statistics = board.statistics.anotherRowHasBeenCompleted(numberOfRemovedRows)
      createNewStone()
     }
  }
  
  private def move(action: (Stone) => Stone) = {
    if (board.stones.isEmpty)
      createNewStone()
    
    val oldStone = board.stones.head
    val newStone = action(oldStone)
    if (newStone.isInFrame(board.size) && !board.stones.tail.exists(s => s.doesCollide(newStone))) {
      board.stones = newStone :: board.stones.tail
      true
    }
    else false
  }
  
  def moveLeft() {
    move(s => s.moveLeft())
  }
  
  def moveRight() {
    move(s => s.moveRight)
  }
  
  def rotateLeft() {
    move(s => s.rotateLeft)
  }
  
  def rotateRight() {
    move(s => s.rotateRight)
  }
  
  def restart() = board.restart()
  
  def draw() = board.draw()
  
  def isGameRunning = board.isGameRunning
  
  private def removeFullRows(points: List[Point], 
                            height: Int = board.size.height): (List[Point], Int) = points match {
   case Nil => (Nil, 0)
   case _ => val (pointsInRow, pointsNotInRow) = points.partition(_.y == height)
     val (rows, numberOfRemovedRows) = removeFullRows(pointsNotInRow, height - 1)
     if (pointsInRow.length == board.size.width) {
       (rows.map(_.moveDown()), numberOfRemovedRows + 1)
     }
     else { 
       (pointsInRow ::: rows, numberOfRemovedRows)
     }
 }
}
  

