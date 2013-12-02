package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.db.slick._
import models.Image
import play.api.Play.current
import scala.slick.lifted.Query

object Application extends Controller {

  def index = DBAction { implicit request =>
    	val images = Query(Images.list)
      Ok(views.html.index("Welcome!", Query(Images.list)))
  }
  
  def upload = Action {
    Ok(views.html.upload("Upload your stuff"))
  }
  
  def uploadImage = DBAction(parse.multipartFormData) { implicit request =>
    request.body.file("image").map { picture =>
			import java.io.File
			val filename = picture.filename
			val contentType = picture.contentType
			picture.ref.moveTo(new File(s"/tmp/picture/$filename"))
			Ok("File uploaded")
    }.getOrElse {
    	Redirect(routes.Application.index).flashing("error" -> "Missing file")
    }
  }
  
  def addComment = Action { implicit request =>
    Ok(views.html.index("Add Comment"))
  }
  
  def viewImage(id: Int) = Action {
    Ok(views.html.index("View Image"))
  }
  
  def viewGroup(id: Int) = Action {
    Ok(views.html.index("View Group"))
  }

  def createGroup = Action {
    Ok(views.html.index("Create Group"))
  }
}