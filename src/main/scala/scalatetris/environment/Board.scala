package scalatetris.environment

import java.util.Calendar

class Board private (
    val size: Size, 
    val stones: List[Stone],
    val preview: Stone,
    val statistics: Statistics,
    val isGameRunning: Boolean) {
  def this(size: Size, firstStone: Stone, firstPreview: Stone) {
    this(
        size,
        List[Stone](firstStone.toTopCenter(Point(size.width / 2, 0))),
        firstPreview,
        Statistics(Calendar.getInstance().getTime(), 0),
        true)
  }
  
  private def topCenter = Point(size.width / 2, 0)
    
  def points = stones.map(_.points).flatten
  
  def update(stones: List[Stone]) = new Board(size, stones, preview, statistics, isGameRunning)
  
  def update(stones: List[Stone], numberOfRowsRemoved: Int, preview: Stone) = {
    if (stones.exists(s => s.doesCollide(this.preview)) ||
        (!stones.isEmpty && stones.head.isOnTop))
    {
      new Board(size, stones, preview, statistics, false)
    }
    else
    {
      new Board(
          size, 
          this.preview.toTopCenter(topCenter) :: stones, 
          preview, 
          statistics.anotherRowHasBeenCompleted(numberOfRowsRemoved), 
          isGameRunning)
    }
  }
  
  def forceNewStone(preview: Stone) =
    new Board(
        size, 
        this.preview.toTopCenter(topCenter) :: stones,
        preview, 
        statistics, 
        isGameRunning)
     
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
}


