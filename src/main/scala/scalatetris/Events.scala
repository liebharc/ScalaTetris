package scalatetris

object UserInteraction extends Enumeration {
  type UserInteraction = Value
  val Left, Right, Down, RotateLeft, RotateRight = Value
}

object EngineEvent extends Enumeration {
  type EngineEvent = Value
  val Tick = Value
}