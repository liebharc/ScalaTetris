package scalatetris

import scalatetris.environment.Board
import scalatetris.engine.GameEngine
import scalatetris.environment.Size
import akka.actor.ActorSystem
import akka.actor.Props
import swing._
import java.awt.Font
import scala.swing.event.Key
import scala.swing.event.KeyPressed
import scala.swing.event.KeyReleased
import scala.swing.event.KeyPressed
import scala.swing.event.KeyReleased

object Main extends SimpleSwingApplication {
    def top = {
      val area = new TextArea {
        font = new Font(Font.MONOSPACED, Font.PLAIN, 16)
        preferredSize = new Dimension(640, 480)
        editable = false
      }
      val display = new SwingDisplay(area)
      val frame = new MainFrame {
        title = "Scala Tetris"
        contents = area
        preferredSize = new Dimension(640, 480)
      }
      
      val board = new Board(new Size(6, 8))
      val engine = new GameEngine(board)
      val system = ActorSystem()
      val tetris = system.actorOf(Props(new Tetris(engine)), name = "tetris")	
      val drawing = board.draw()
      display.render(drawing)
      
      listenTo(area.keys)
      reactions += {
        case key: KeyPressed => {
          // TODO consider board.isGameRunning
          key.key match {
            case Key.A => 
              /* TODO inform Tetris actor and remove this code */
              engine.moveLeft()
              display.render(board.draw()) 
            case Key.S => 
              /* TODO inform Tetris actor and remove this code */
              engine.moveDown()
              display.render(board.draw())
            case Key.D => 
              /* TODO inform Tetris actor and remove this code */
              engine.moveRight()
              display.render(board.draw())
            case _ => ()
          }
          
          key.consume()
        }
        case key: KeyReleased => key.consume()
      }
      
      frame
    }
}
