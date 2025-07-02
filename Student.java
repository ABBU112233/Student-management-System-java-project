public class Student extends Person {
    private int id;
    // private String name;
    // private  int age;

    public int getId(){
        return id;
    }

    public void setId(int newId){
        this.id=newId;
    }
    public void displayInfo(){
        System.out.println(id);
        System.out.println(getName());
        System.out.println(getAge());
    }
}
