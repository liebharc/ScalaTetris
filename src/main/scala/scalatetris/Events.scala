package scalatetris

object UserInteraction extends Enumeration {
  type UserInteraction = Value
  val Left, Right, Down = Value
}

object EngineEvent extends Enumeration {
  type EngineEvent = Value
  val Tick = Value
}