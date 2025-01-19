import java.lang.module.ModuleDescriptor;

/*
class User: id (auto generate), uuid(auto generate), name, email, isDeleted
Create a User management console system:
1. Create user (when create new user send notification to telegram)
2. Search user by uuid
3. Update user by uuid (update: name, email, isDeleted)
4. Delete user by uuid
5. Display all users (display as table and can paginate - one page has 5 users and display only users that have isDeleted=false)
6. Exit
 */
class User{
    private int id;
    private int uuid;
    private String name;
    private String email;
    boolean isDeleted;
    public User(Builder builder){
        id = builder.id;
        uuid = builder.uuid;
        name = builder.name;
        email = builder.email;
        isDeleted = builder.isDeleted;;
    }
    static class Builder{
        private int id;
        private int uuid;
        private String name;
        private String email;
        private boolean isDeleted;
        Builder builder(){
            return  this;
        }
        Builder setId(int id){
            this.id = id;
            return this;
        }
        Builder setUUid(int uuid){
            this.uuid = uuid;
            return this;
        }

    }
}
public class Main {
    public static void main(String[] args) {

    }
}