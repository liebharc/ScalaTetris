package scalatetris

import akka.actor._
import scalatetris.engine.GameEngine

class Tetris(val engine: GameEngine) extends Actor {
  def act {
    /*loop {
      reactWithin(1000) {
        case TIMEOUT => 
		engine.moveDown()
      Runtime.getRuntime().exec("clear")
      val drawing = engine.board.draw()
      System.out.print(drawing)
      }
    }*/
  }
  
  def receive = {
  case _ => println("tetris")
  }
}