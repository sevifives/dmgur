package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def upload = Action {
    Ok(views.html.index("Upload your stuff"))
  }
  
  def uploadImage = Action {
    Ok(views.html.index("Your images is uploaded"))
  }
  
  def addComment = Action {
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