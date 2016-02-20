package scalatetris

import akka.actor._
import UserInteraction._
import EngineEvent._
import environment.Board
import scalatetris.engine.GameEngine

class Tetris(val engine: GameEngine, val display: Display) extends Actor {
  private var tickCounts = 0 
  
  def receive = {
    case Restart if !engine.isGameRunning => 
      engine.restart()
    case _ if !engine.isGameRunning => ()
    case Left => 
      engine.moveLeft()
      display.render(renderAll())
    case Right =>
      engine.moveRight()
      display.render(renderAll())
    case Down =>
      engine.moveDown()
      display.render(renderAll())
    case RotateLeft =>
      engine.rotateLeft()
      display.render(renderAll())
    case RotateRight =>
      engine.rotateRight()
      display.render(renderAll())
    case Tick =>
      tickCounts += 1
      if (tickCounts % 5 == 0) {
        engine.moveDown()
      }
      
      display.render(renderAll())
    case _ => ()
  }
  
  def renderAll() = {
    engine.draw()
  }
}