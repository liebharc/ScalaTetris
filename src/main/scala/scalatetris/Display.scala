package scalatetris

object Display {
  private def isWindows() = {
    System.getProperty("os.name").contains("Windows")
  }
  
  def render(value: String) {
    System.out.println(value)
  }
}