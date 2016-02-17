package scalatetris.engine

import scalatetris.environment.Board
import scalatetris.environment.Point
import scalatetris.environment.Stone
import scalatetris.environment.StoneFactory

sealed class GameEngine (val board: Board, val stoneFactory: StoneFactory) {
  
  createNewStone()
  
  def createNewStone() {
    val newStone = stoneFactory.createRandomStone(Point((board.size.width / 2), 0))
    if (board.stones.exists(s => s.doesCollide(newStone)) ||
        (!board.stones.isEmpty && board.stones.head.isOnTop))
      board.isGameRunning = false
    else
      board.stones ::= newStone
  }
  
  def moveDown() {
     if (!move(s => s.moveDown())) {
      board.points = removeFullRows(board.points)
      createNewStone()
     }
  }
  
  private def move(action: (Stone) => Stone) = {
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
  
 private def removeFullRows(points: List[Point], 
                            height: Int = board.size.height): List[Point] = points match {
   case Nil => Nil
   case _ => val (pointsInRow, pointsNotInRow) = points.partition(_.y == height)
     if (pointsInRow.length == board.size.width) 
       removeFullRows(pointsNotInRow, height - 1).map(_.moveDown())
     else 
       pointsInRow ::: removeFullRows(pointsNotInRow, height - 1)
 }
}
  

