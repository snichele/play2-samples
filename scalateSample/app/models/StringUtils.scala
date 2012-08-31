package models

import scalaz._
import Scalaz._

/**
 * A String manipulation utility object.
 */
object StringUtils {
  val replace = (from: String, to: String) => (_:String).replaceAll(from, to)
  val replaceNothing = replace("","")
  def composeReplacementsSequence(rules: Seq[Tuple2[String,String]]) = {
    rules.foldLeft(replaceNothing)((composedReplace,nextRule) => composedReplace ∘ replace(nextRule._1, nextRule._2) )
  }
}
