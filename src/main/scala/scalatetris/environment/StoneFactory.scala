package scalatetris.environment

trait StoneFactory {
   def createRandomStone(start: Point): Stone
}

object OnlyPointsStoneFactory extends StoneFactory {
   def createRandomStone(start: Point) = Stone(start)
}

object OnlySquaresStoneFactory extends StoneFactory {
   def createRandomStone(start: Point) = Square(start)
}