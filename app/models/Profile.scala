package models
import play.api.Play.current
import slick.driver.ExtendedProfile

trait Profile {
  val profile: ExtendedProfile
}