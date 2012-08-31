package views

import scalaz._
import Scalaz._

/**
 * Custom tags implementations
 */
object Tags {

  def linkStyleSheets(
    library: String,
    version: Option[String] = None,
    suffix: Option[String] = None,
    extension: String = "css",
    files: Seq[String] = null
  ) = {
    val paths = buildAssetPathsAccordingToNomenclature("/assets/css/", library, version, suffix, Option(files),extension)
    paths.map(p => <link rel="stylesheet" type="text/css" href={p} />)
  }

  def linkJavascripts(
    library: String,
    version: Option[String] = None,
    suffix: Option[String] = None,
    extension: String = "js",
    files: Seq[String] = null
  ) = {
    val paths = buildAssetPathsAccordingToNomenclature("/assets/js/", library, version, suffix, Option(files),extension)
    paths.map(p => <script src={p} />)
  }

  protected[views] def buildAssetPathsAccordingToNomenclature(
    in: String,
    assetName: String,
    version: Option[String],
    variantSuffix: Option[String],
    assetComponents: Option[Seq[String]],
    filesExtension: String
    ): Seq[String] = {

    import models.StringUtils._

    val pathPattern = ":root:library/:library:version:component:variantSuffix:filesExtension";
    val commonReplacements = Seq(
      (":root", in),
      (":library", assetName),
      (":version", version.map("-" + _).getOrElse("")),
      (":variantSuffix", variantSuffix.map("." + _).getOrElse("")),
      (":filesExtension", "." + filesExtension)
    )
    def pathWithFileSuffix(suffix: Option[String]) = pathPattern |> composeReplacementsSequence(commonReplacements :+ (":component", suffix.map("-" + _).getOrElse("")))

    assetComponents match {
      case Some(components) =>
        components.map(c => pathWithFileSuffix(Option(c)))
      case None =>
        Seq(pathWithFileSuffix(None))
    }
  }
}
