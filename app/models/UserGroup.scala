package models

case class UserGroup (id: Option[Int], ownerId: Int, name: String, users: String, groups: String)

trait UserGroupComponent {
  this: Profile with UserComponent => 
    
  import profile.simple._
  
  object UserGroups extends Table[UserGroup]("user_groups") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def ownerId = column[Int]("OWNER_ID")
    def name = column[String]("NAME")
    def users = column[String]("USERS")
    def groups = column[String]("GROUPS")
    
    def owner = foreignKey("OWNER_FK", ownerId, Users)(_.id)
    
    def * = id.? ~ ownerId ~ name ~ users ~ groups <> (UserGroup, UserGroup.unapply _)
  }
}