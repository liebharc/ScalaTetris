package scalatetris

import scalatetris.environment.Board
import scalatetris.engine.GameEngine
import scalatetris.environment.Size
import akka.actor.ActorSystem
import akka.actor.Props

object Main extends App {
    System.out.print();
    val board = new Board(new Size(6, 8))
    val engine = new GameEngine(board)
    val system = ActorSystem()
    val tetris = system.actorOf(Props(new Tetris(engine)), name = "tetris")	
    val drawing = board.draw()
    Display.render(drawing)
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
      val drawing = board.draw()
      Display.render(drawing)
    }
    System.exit(0)
}
