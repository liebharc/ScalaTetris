package scalatetris.engine

import scalatetris.environment.Board
import scalatetris.environment.Point
import scalatetris.environment.Stone

class GameEngine (val board: Board) {
  def createNewStone() {
    val newStone = new Stone(new Point((board.size.width / 2), 0))
    board.stones ::= newStone
  }
  
  def moveDown() {
    move(s => s.moveDown())
  }
  
  private def move(action: (Stone) => Stone) {
    val oldStone = board.stones.head
    val newStone = action(oldStone)
    if (newStone.isInFrame(board.size) && !board.stones.exists(s => s.doesCollide(newStone))) {
      board.stones = newStone :: board.stones.tail
      removeFullRows()
    }    
  }
  
  def moveLeft() {
    move(s => s.moveLeft())
  }
  
  def moveRight() {
    move(s => s.moveRight)
  }
  
  private def removeFullRows() {
    var remaining = board.stones
    for (rowNo <- (0 until board.size.height)) {
      val (stonesInRow, nextRemaining) = remaining.partition(s => s.start.y == rowNo)
      remaining = nextRemaining
      if (stonesInRow.size == board.size.width) {
        board.stones = board.stones.filter(s => !stonesInRow.contains(s))
      }
    }
  }
}
  

