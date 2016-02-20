package scalatetris

import scalatetris.engine.GameEngine
import scalatetris.environment._
import UserInteraction._
import EngineEvent._
import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
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
        preferredSize = new Dimension(640, 768)
        editable = false
      }
      
      val frame = new MainFrame {
        title = "Scala Tetris"
        contents = area
        preferredSize = new Dimension(640, 768)
      }
      
      val board = new Board(new Size(10, 20))
      val engine = new GameEngine(board, RandomStoneFactory)
      val display = new SwingDisplay(area)
      val drawing = board.draw()
      display.render(drawing)
      
      val system = ActorSystem()
      val tetris = system.actorOf(Props(new Tetris(engine, display)), name = "tetris")
      import system.dispatcher
      system.scheduler.schedule(
          Duration(500, "ms"),
          Duration(100, "ms"),
          tetris, 
          Tick)
      
      listenTo(area.keys)
      reactions += {
        case key: KeyPressed => {
          key.key match {
            case Key.A => 
              tetris ! Left
            case Key.S => 
              tetris ! Down
            case Key.D => 
              tetris ! Right
            case Key.Q => 
              tetris ! RotateLeft
            case Key.E => 
              tetris ! RotateRight
            case Key.R => 
              tetris ! Restart
            case _ => ()
          }
        }
      }
      
      frame
    }
}
