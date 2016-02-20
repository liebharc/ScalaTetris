package scalatetris.environment

import java.util.Calendar

class Board (val size: Size) {
  var stones = List[Stone]()
  
  private var _isGameRunning = true
  
  def isGameRunning = _isGameRunning
    
  def points = stones.map(_.points).flatten
  
  def points_=(p: List[Point]) = stones = List(Stone(p))
  
  var statistics = Statistics(Calendar.getInstance().getTime(), 0)
   
  def draw() = 
  if (isGameRunning) {
    drawBoardOnly + "\n" +
    statistics.draw() 
  } else 
  {
    drawBoardOnly + 
    "GAME OVER\n" +
    statistics.draw()
  }
  
  def drawBoardOnly() = {
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
  
  def restart() {
    _isGameRunning = true
    statistics = Statistics(Calendar.getInstance().getTime(), 0)
    stones = List[Stone]()
  }
  
  def endGame() = _isGameRunning = false
}


