package scalatetris

import scalatetris.environment.Board
import scalatetris.engine.GameEngine
import scalatetris.environment.Size
import akka.actor.ActorSystem
import akka.actor.Props

object Main extends App {

    val board = new Board(new Size(6, 8))
    val engine = new GameEngine(board)
val system = ActorSystem("TetrisSystem")
val tetris = system.actorOf(Props(new Tetris(engine)), name = "tetris")	
    Runtime.getRuntime().exec("clear")
    val drawing = board.draw()
    System.out.print(drawing)
    while(board.isGameRunning) {
      val input = System.in.read
      input match {
        case 'a' => engine.moveLeft()
                   engine.moveDown()
        case 'd' => engine.moveRight()
                    engine.moveDown()
        case 's' => engine.moveDown()
                    engine.moveDown()
        case '\n' => ()
        case _ => engine.moveDown()
      }
      Runtime.getRuntime().exec("clear")
      val drawing = board.draw()
      System.out.print(drawing)
    }
    System.exit(0)
}
