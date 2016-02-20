package scalatetris.environment

import java.util.Calendar

class Board (val size: Size, firstStone: Stone, firstPreview: Stone) {
  private def topCenter = Point(size.width / 2, 0)
  
  private var _stones = List[Stone](firstStone.toTopCenter(topCenter))
  
  private var _isGameRunning = true
  
  def isGameRunning = _isGameRunning
    
  def points = _stones.map(_.points).flatten
  
  private var _statistics = Statistics(Calendar.getInstance().getTime(), 0)
  
  private var _preview = firstPreview
  
  def preview = _preview
  
  def stones = _stones
  
  def statistics = _statistics
  
  def update(stones: List[Stone]) {
    _stones = stones
  }
  
  def update(stones: List[Stone], numberOfRowsRemoved: Int, preview: Stone) {
    if (stones.exists(s => s.doesCollide(_preview)) ||
        (!stones.isEmpty && stones.head.isOnTop))
    {
      _isGameRunning = false    
    }
    else
    {
      _stones = _preview.toTopCenter(topCenter) :: stones
      _statistics = _statistics.anotherRowHasBeenCompleted(numberOfRowsRemoved)
      _preview = preview
    }
  }
  
  def forceNewStone(preview: Stone) {
    _stones = _preview.toTopCenter(topCenter) :: _stones
    _preview = preview
  }
   
  def draw() = 
  if (isGameRunning) {
    drawBoardOnly + "\n" +
    _statistics.draw() 
  } else 
  {
    drawBoardOnly + 
    "GAME OVER\n" +
    _statistics.draw()
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
    _statistics = Statistics(Calendar.getInstance().getTime(), 0)
    _stones = List[Stone]()
  }
  
  def endGame() = _isGameRunning = false
}


