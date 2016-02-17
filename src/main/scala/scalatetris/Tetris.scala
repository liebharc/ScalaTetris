package scalatetris

import akka.actor._
import UserInteraction._
import EngineEvent._
import environment.Board
import scalatetris.engine.GameEngine

class Tetris(val engine: GameEngine, val board: Board, val display: Display) extends Actor { 
  def receive = {
    case _ if !board.isGameRunning => ()
    case Left => 
      engine.moveLeft()
      display.render(renderAll())
    case Right =>
      engine.moveRight()
      display.render(renderAll())
    case Down =>
      engine.moveDown()
      display.render(renderAll())
    case Tick => {
      engine.moveDown()
      display.render(renderAll())
    }
  }
  
  def renderAll() = {
    board.draw() + "\n\n" +
    engine.statistics().draw()
  }
}