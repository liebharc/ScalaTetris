package scalatetris.environment
import junit.framework.TestCase
import junit.framework.Assert.assertTrue
import junit.framework.Assert.assertFalse

class StoneTest extends TestCase {
  
  def testDoesCollide {
    val s1 = Stone(Point(3, 5))
    val s2 = Stone(Point(3, 5))
    val result = s1.doesCollide(s2)
    assertTrue(result)
  }
  
  def testDoesNotCollide {
    val s1 = Stone(Point(3, 5))
    val s2 = Stone(Point(3, 4))
    val result = s1.doesCollide(s2)
    assertFalse(result)
  }
  
  def testIsInFrame {
    val stone = Stone(Point(3, 5))
    val frame = Size(5, 6)
    val result = stone.isInFrame(frame)
    assertTrue(result)
  }
  
  def testIsBelowFrame {
    val stone = Stone(Point(3, 5))
    val frame = Size(5, 5)
    val result = stone.isInFrame(frame)
    assertFalse(result)
  }
  
  def testIsLeftOfFrame {
    val stone = Stone(Point(-1, 3))
    val frame = Size(5, 5)
    val result = stone.isInFrame(frame)
    assertFalse(result)
  }
}
