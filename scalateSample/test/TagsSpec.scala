package views

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class TagsSpec extends Specification {

  import views.Tags._

  "Asset paths according to the assets nomenclature of the project " should {

    " be '/assets/foos/foo/foo.bar' for '/assets/foos/' location, 'foo' library and 'bar' extension " in {
      val result = buildAssetPathsAccordingToNomenclature("/assets/foos/","foo",None,None,None,"bar")
      result must equalTo(Seq("/assets/foos/foo/foo.bar"))
    }

    " be '/assets/foos/foo/foo-1.2.3.bar' for '/assets/foos/' location, 'foo' library, 'bar' extension and version '1.2.3'" in {
      val result = buildAssetPathsAccordingToNomenclature("/assets/foos/","foo",Option("1.2.3"),None,None,"bar")
      result must equalTo(Seq("/assets/foos/foo/foo-1.2.3.bar"))
    }

    " be '/assets/foos/foo/foo-1.2.3.min.bar' for '/assets/foos/' location, 'foo' library, 'bar' extension,version '1.2.3' and variant 'min'" in {
      val result = buildAssetPathsAccordingToNomenclature("/assets/foos/","foo",Option("1.2.3"),Option("min"),None,"bar")
      result must equalTo(Seq("/assets/foos/foo/foo-1.2.3.min.bar"))
    }

    " be a sequence of '/assets/foos/foo/foo-1.2.3.core.min.bar','/assets/foos/foo/foo-1.2.3.ext.min.bar' for '/assets/foos/' location, 'foo' library, 'bar' extension,version '1.2.3', variant 'min' and 'core','ext' asset components" in {
      val result = buildAssetPathsAccordingToNomenclature("/assets/foos/","foo",Option("1.2.3"),Option("min"),Option(Seq("core","ext")),"bar")
      result must equalTo(Seq("/assets/foos/foo/foo-1.2.3-core.min.bar","/assets/foos/foo/foo-1.2.3-ext.min.bar"))
    }

  }

}