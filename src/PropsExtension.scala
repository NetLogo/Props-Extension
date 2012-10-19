package org.nlogo.extensions.props

import org.nlogo.api.{ Argument, Context, DefaultClassManager, DefaultCommand, DefaultReporter, PrimitiveManager, Syntax }, Syntax._

class PropsExtension extends DefaultClassManager {
  def load(primitiveManager: PrimitiveManager) {
    primitiveManager.addPrimitive("get", Get)
    primitiveManager.addPrimitive("set", Set)
  }
}

object Get extends DefaultReporter {
  override def getAgentClassString = "O---"
  override def getSyntax = reporterSyntax(Array(StringType), StringType | NumberType)
  override def report(args: Array[Argument], context: Context) : AnyRef = {
    Option(System.getProperty(args(0).getString)) getOrElse Double.box(0)
  }
}

object Set extends DefaultCommand {
  override def getAgentClassString = "O---"
  override def getSyntax = commandSyntax(Array(StringType, StringType))
  override def perform(args: Array[Argument], context: Context) {
    System.setProperty(args(0).getString, args(1).getString)
  }
}
