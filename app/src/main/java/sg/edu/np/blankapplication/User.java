package sg.edu.np.blankapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;
    static ArrayList<User> userList = new ArrayList<User>();
    public User(String n, String d, int i, boolean f){
        name=n;
        description=d;
        id=i;
        followed=f;
    }
    public User(String n, String d, boolean f){
        name=n;
        description=d;
        followed=f;
    }
}
