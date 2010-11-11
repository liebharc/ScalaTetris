package scalatetris

import scalatetris.environment.Board
import scalatetris.environment.Size

object Main {
  def main(args: Array[String]): Unit = {
    val board = new Board(new Size(6, 8))
    Runtime.getRuntime().exec("clear")
    val drawing = board.draw()
    System.out.print(drawing)
  }
}
