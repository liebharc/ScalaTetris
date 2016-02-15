package scalatetris.environment
import org.junit.Test
import org.junit.Assert._

class StoneTest {
  
  @Test def testDoesCollide {
    val s1 = Stone(Point(3, 5))
    val s2 = Stone(Point(3, 5))
    val result = s1.doesCollide(s2)
    assertTrue(result)
  }
  
  @Test def testDoesNotCollide {
    val s1 = Stone(Point(3, 5))
    val s2 = Stone(Point(3, 4))
    val result = s1.doesCollide(s2)
    assertFalse(result)
  }
  
  @Test def testIsInFrame {
    val stone = Stone(Point(3, 5))
    val frame = Size(5, 6)
    val result = stone.isInFrame(frame)
    assertTrue(result)
  }
  
  @Test def testIsBelowFrame {
    val stone = Stone(Point(3, 5))
    val frame = Size(5, 5)
    val result = stone.isInFrame(frame)
    assertFalse(result)
  }
  
  @Test def testIsLeftOfFrame {
    val stone = Stone(Point(-1, 3))
    val frame = Size(5, 5)
    val result = stone.isInFrame(frame)
    assertFalse(result)
  }
}
