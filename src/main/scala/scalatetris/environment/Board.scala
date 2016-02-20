package scalatetris.environment

import java.util.Calendar

class Board (val size: Size, firstStone: Stone, firstPreview: Stone) {
  private def topCenter = Point(size.width / 2, 0)
  
  var stones = List[Stone](firstStone.toTopCenter(topCenter))
  
  private var _isGameRunning = true
  
  def isGameRunning = _isGameRunning
    
  def points = stones.map(_.points).flatten
  
  var statistics = Statistics(Calendar.getInstance().getTime(), 0)
  
  private var _preview = firstPreview
  
  def preview = _preview
  
  def update(stones: List[Stone]) {
    this.stones = stones
  }
  
  def update(stones: List[Stone], numberOfRowsRemoved: Int, preview: Stone) {
    if (stones.exists(s => s.doesCollide(_preview)) ||
        (!stones.isEmpty && stones.head.isOnTop))
    {
      _isGameRunning = false    
    }
    else
    {
      this.stones = _preview.toTopCenter(topCenter) :: stones
      statistics = statistics.anotherRowHasBeenCompleted(numberOfRowsRemoved)
      _preview = preview
    }
  }
  
  def forceNewStone(preview: Stone) {
    this.stones = _preview.toTopCenter(topCenter) :: stones
    _preview = preview
  }
   
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


