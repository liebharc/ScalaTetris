package scalatetris.environment

case class Stone(val start: Point) {
  
  def moveDown() = Stone(start.moveDown())
  
  def moveLeft() = Stone(start.moveLeft())
  
  def moveRight() = Stone(start.moveRight())
  
  def doesCollide(other: Stone) = this.start == other.start
  
  def isInFrame(frame: Size) = (0 until frame.width).contains(start.x) &&
                                (0 until frame.height).contains(start.y)
                                
  def isOnTop(): Boolean = start.y == 0
}
