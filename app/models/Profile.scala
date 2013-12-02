// https://github.com/slick/slick-examples/blob/master/src/main/scala/com/typesafe/slick/examples/lifted/MultiDBCakeExample.scala

package models
import play.api.Play.current
import slick.driver.ExtendedProfile

trait Profile {
  val profile: ExtendedProfile
}