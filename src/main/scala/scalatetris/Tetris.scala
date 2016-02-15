package scalatetris

import akka.actor._
import scalatetris.engine.GameEngine

class Tetris(val engine: GameEngine) extends Actor {
  def act() {
    while (true) {
      engine.moveDown()
      Thread.sleep(1000)
      Runtime.getRuntime().exec("clear")
      val drawing = engine.board.draw()
      System.out.print(drawing)
    }
  }
}
