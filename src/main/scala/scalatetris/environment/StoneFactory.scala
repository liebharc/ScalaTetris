package scalatetris.environment

import java.util.Random

trait StoneFactory {
   def createRandomStone(start: Point): Stone
}

object RandomStoneFactory extends StoneFactory {
  private val random = new Random()
  
  def createRandomStone(start: Point) = 
    {
      val rand = random.nextInt(7)
      rand match {
        case 0 => Square(start)
        case 1 => Line(start)
        case 2 => WinnerPodium(start)
        case 3 => LetterLLeft(start)
        case 4 => LetterLRight(start)
        case 5 => StepLeft(start)
        case 6 => StepRight(start) 
      }
    }
}

object OnlyPointsStoneFactory extends StoneFactory {
   def createRandomStone(start: Point) = Stone(start)
}

object OnlySquaresStoneFactory extends StoneFactory {
   def createRandomStone(start: Point) = Square(start)
}

object OnlyLinesStoneFactory extends StoneFactory {
   def createRandomStone(start: Point) = Line(start)
}

object NoStonesFactory extends StoneFactory {
   def createRandomStone(start: Point) = Stone(Nil)
}