package scalatetris.environment

case class Point(val x: Int, val y: Int) {
  
  def moveDown() = Point(x, y+1)
  
  def moveLeft() = Point(x-1, y)
  
  def moveRight() = Point(x+1, y)
  
  def isInFrame(frame: Size) = (0 until frame.width).contains(x) &&
                                (0 until frame.height).contains(y)
                                
  def isOnTop(): Boolean = y == 0
}
