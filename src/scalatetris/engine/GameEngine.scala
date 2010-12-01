package scalatetris.engine

import scalatetris.environment.Board
import scalatetris.environment.Point
import scalatetris.environment.Stone

class GameEngine (val board: Board) {
  
  createNewStone()
  
  def createNewStone() {
    val newStone = new Stone(new Point((board.size.width / 2), 0))
    if (board.stones.exists(s => s.doesCollide(newStone)) ||
        (!board.stones.isEmpty && board.stones.head.isOnTop))
      board.isGameRunning = false
    else
      board.stones ::= newStone
  }
  
  def moveDown() {
     if (!move(s => s.moveDown())) {
      removeFullRows()
      createNewStone()
     }
  }
  
  private def move(action: (Stone) => Stone) = {
    val oldStone = board.stones.head
    val newStone = action(oldStone)
    if (newStone.isInFrame(board.size) && !board.stones.exists(s => s.doesCollide(newStone))) {
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
  
  private def removeFullRows() {
    var remaining = board.stones
    board.stones = Nil
    var rowNo = board.size.height
    while (rowNo >= 0) {
      val (stonesInRow, nextRemaining) = remaining.partition(s => s.start.y == rowNo)
      remaining = nextRemaining
      if (stonesInRow.size != board.size.width) {
        board.stones :::= stonesInRow
        rowNo -= 1
      } else {
        remaining = remaining.map{s => s.moveDown}.toList
      }
    }
  }
}
  

