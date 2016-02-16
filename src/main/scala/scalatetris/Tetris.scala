package scalatetris

import akka.actor._
import UserInteraction._
import EngineEvent._
import environment.Board
import scalatetris.engine.GameEngine

class Tetris(val engine: GameEngine, val board: Board, val display: Display) extends Actor { 
  def receive = {
    case Left => 
      if (board.isGameRunning) {
        engine.moveLeft()
        display.render(board.draw())
      }
    case Right =>
      if (board.isGameRunning) {
        engine.moveRight()
        display.render(board.draw())
      }
    case Down =>
      if (board.isGameRunning) {
        engine.moveDown()
        display.render(board.draw())
      }
    case Tick => {
      if (board.isGameRunning) {
        engine.moveDown()
        display.render(board.draw())
      }
    }
  }
}