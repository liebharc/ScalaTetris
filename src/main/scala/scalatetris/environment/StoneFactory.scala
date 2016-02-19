package scalatetris.environment

import java.util.Random

trait StoneFactory {
   def createRandomStone(start: Point): Stone
}

object RandomStoneFactory extends StoneFactory {
  private val random = new Random()
  
  def createRandomStone(start: Point) = 
    {
      val rand = random.nextInt(2)
      rand match {
        case 0 => Square(start)
        case 1 => Line(start)
      }
    }
}

object OnlyPointsStoneFactory extends StoneFactory {
   def createRandomStone(start: Point) = Stone(start)
}

object OnlySquaresStoneFactory extends StoneFactory {
   def createRandomStone(start: Point) = Square(start)
}