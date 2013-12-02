package controllers

import play.api._
import play.api.mvc._

object Admin extends Controller {

	def home = Action {
	  Ok("Admin")
	}
}