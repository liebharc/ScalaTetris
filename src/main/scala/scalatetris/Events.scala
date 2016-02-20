package scalatetris

object UserInteraction extends Enumeration {
  type UserInteraction = Value
  val Left, Right, Down, RotateLeft, RotateRight, Restart = Value
}

object EngineEvent extends Enumeration {
  type EngineEvent = Value
  val Tick = Value
}