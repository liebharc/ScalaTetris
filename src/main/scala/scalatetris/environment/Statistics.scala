package scalatetris.environment
import java.util._
import java.text.SimpleDateFormat

case class Statistics(val startTime: Date, val rowsCompleted: Long) {
  def anotherRowHasBeenCompleted(numberOfRows: Int) = Statistics(startTime, rowsCompleted + numberOfRows)
  
  def draw() = {
    val now = Calendar.getInstance().getTime()
    val duration = now.getTime() - startTime.getTime()
    val format = new SimpleDateFormat("mm:ss");
    "Rows completed: " + rowsCompleted + "\n" +
    "Time spent    : " + format.format(duration)
  }
}