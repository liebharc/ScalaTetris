package scalatetris.environment

class Board (val size: Size) {
  var stones = List[Stone]()
  
  def points = stones.map(_.points).flatten
  
  def points_=(p: List[Point]) = stones = List(Stone(p))
  
  def draw() = {
    var drawing = ""
    for (arg <- (0 until size.height)) {
      drawing += "|"
      val pointsInLine = points.filter(p => p.y == arg)
      for (x <- (0 until size.width)) {
        if (pointsInLine.exists(p => p.x == x)) 
          drawing += "x"
        else
          drawing += " "
      }  
      drawing += "|\n"
    }
    drawing += "-" *(size.width + 2) + "\n" 
    drawing
  }
  
  var isGameRunning = true
}


