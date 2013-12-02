package models

case class UserGroup (id: Option[Int], ownerId: Int, name: String, users: String, groups: String)

trait UserGroupComponent {
  this: Profile with UserComponent => 
    
  import profile.simple._
  
  object UserGroups extends Table[UserGroup]("user_groups") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def ownerId = column[Int]("owner_id")
    def name = column[String]("name")
    def users = column[String]("users")
    def groups = column[String]("groups")
    
    def owner = foreignKey("owner_fk", ownerId, Users)(_.id)
    
    def * = id.? ~ ownerId ~ name ~ users ~ groups <> (UserGroup, UserGroup.unapply _)
  }
}