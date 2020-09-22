package xiaochen.entity;

public class Person {
    private String name;
    private int age;

    void sayHi(String tips) {
        System.out.println("------->" + tips);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
