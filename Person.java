//Super class
public abstract class Person {
    private String name;
    private int age;
    //grtter for name
    public String getName(){
        return name;
    }
    //setter for name
    public void setName(String newName){
        this.name=newName;
    }
    public int getAge(){
        return age;
    }
    //setter for name
    public void setAge(int newAge){
        this.age=newAge;
    }
    public abstract void displayInfo();
}
