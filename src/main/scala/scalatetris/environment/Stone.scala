package scalatetris.environment

object Stone {
  def apply(start: Point): Stone = Stone(List(start))
}

object Square {
  def apply(start: Point): Stone =
    Stone(List(start,          start.moveRight,
               start.moveDown, start.moveDown.moveRight))
}

case class Stone(val points: List[Point]) {
  
  def moveDown() = Stone(points.map{_.moveDown()})
  
  def moveLeft() = Stone(points.map{_.moveLeft()})
  
  def moveRight() = Stone(points.map{_.moveRight()})
  
  def doesCollide(other: Stone) = this.points.exists{a => 
    other.points.exists(a == _)
  }
  
  def isInFrame(frame: Size) = points.forall(_.isInFrame(frame))
                                
  def isOnTop(): Boolean = points.exists(_.isOnTop)
  
}