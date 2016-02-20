package scalatetris.environment
import org.junit.Test
import org.junit.Assert._

class PointTest {
  
  @Test def add {
    val p1 = Point(3, 5)
    val p2 = Point(5, 2)
    val result = p1 + p2
    assertEquals(Point(8, 7), result)
  }
  
  @Test def sub {
    val p1 = Point(3, 5)
    val p2 = Point(5, 2)
    val result = p1 - p2
    assertEquals(Point(-2, 3), result)
  }
  
  @Test def mul {
    val p = Point(3, 5)
    val result = p * 3
    assertEquals(Point(9, 15), result)
  }
  
  @Test def div {
    val p = Point(6, 5)
    val result = p / 3
    assertEquals(Point(2, 1), result)
  }
  
  @Test def rotateLeftAndIsCenter {
    val p = Point(6, 5)
    val c = Point(6, 5)
    val result = p.rotateAroundCenterLeft(c)
    assertEquals(Point(6, 5), result)
  }
  
  @Test def rotateLeftAroundCenter {
    val p = Point(1, 1)
    val c = Point(1, 2)
    val result = p.rotateAroundCenterLeft(c)
    assertEquals(Point(2, 2), result)
  }
  
  @Test def rotateRightAroundCenter {
    val p = Point(1, 1)
    val c = Point(1, 2)
    val result = p.rotateAroundCenterRight(c)
    assertEquals(Point(0, 2), result)
  }
  
  @Test def rotateRightAroundCenterFromHorizontalBeginning {
    val p = Point(1, 3)
    val c = Point(1, 2)
    val result = p.rotateAroundCenterRight(c)
    assertEquals(Point(2, 2), result)
  }
  
  @Test def rotateRightAroundCenterFromDiagonalBeginning {
    val p = Point(1, 1)
    val c = Point(2, 2)
    val result = p.rotateAroundCenterRight(c)
    assertEquals(Point(1, 3), result)
  }
}
