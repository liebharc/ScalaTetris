package scalatetris

import scalatetris.environment.Board
import scalatetris.engine.GameEngine
import scalatetris.environment.Size

object Main {
  def main(args: Array[String]): Unit = {
    val board = new Board(new Size(6, 8))
    val engine = new GameEngine(board)
    Runtime.getRuntime().exec("clear")
    val drawing = board.draw()
    System.out.print(drawing)
    new Tetris(engine).start
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
}
