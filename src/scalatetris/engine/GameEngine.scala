package scalatetris.engine

import scalatetris.environment.Board
import scalatetris.environment.Point
import scalatetris.environment.Stone

class GameEngine (val board: Board) {
  def createNewStone() {
    val newStone = new Stone(new Point((board.size.width / 2), 0))
    board.stones ::= newStone
  }
}
  

