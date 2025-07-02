import java.util.Map;
import java.util.HashMap;
public abstract class UserOperations<T extends Person> {
    protected Map<Integer, T> userMap = new HashMap<>();

    // Abstract: each user type (Student, Teacher) defines its own way to add
    public abstract void addUser();

    // Common delete logic
    public void deleteUserById(int id) {
        if (userMap.containsKey(id)) {
            userMap.remove(id);
            System.out.println("User with ID " + id + "is  deleted.");
        } else {
            System.out.println("User not found.");
        }
    }

    // Common search logic
    public void searchUserById(int id) {
        T user = userMap.get(id);
        if (user != null) {
            user.displayInfo(); // Call abstract display method from Person
        } else {
            System.out.println("User not found.");
        }
    }
}