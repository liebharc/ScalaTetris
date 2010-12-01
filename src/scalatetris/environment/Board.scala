package scalatetris.environment

class Board (val size: Size) {
  var stones = List[Stone]()
  
  def draw() = {
    var drawing = ""
    for (arg <- (0 until size.height)) {
      drawing += "|"
      val stonesInLine = stones.filter(s => s.start.y == arg)
      for (x <- (0 until size.width)) {
        if (stonesInLine.exists(s => s.start.x == x)) 
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


