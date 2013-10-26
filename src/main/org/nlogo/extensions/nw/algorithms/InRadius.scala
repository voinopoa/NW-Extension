// (C) Uri Wilensky. https://github.com/NetLogo/NW-Extension

package org.nlogo.extensions.nw.algorithms

import org.nlogo.agent.Turtle
import org.nlogo.api
import org.nlogo.extensions.nw.GraphContext
import org.nlogo.extensions.nw.util.TurtleSetsConverters.toTurtleSet

object InRadius {
  def inRadius(
    graphContext: GraphContext,
    start: Turtle,
    radius: Int,
    directed: Boolean = false,
    reverse: Boolean = false): api.AgentSet = {
    val result: Stream[Turtle] =
      new BreadthFirstSearch(graphContext)
        .from(start, !directed, directed && reverse, directed && !reverse)
        .takeWhile(_.tail.size <= radius)
        .map(_.head)
    toTurtleSet(result, graphContext.world)
  }
}
