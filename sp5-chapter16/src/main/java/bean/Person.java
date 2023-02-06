package bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    @Value("${person.name}") // configuration 에서 bean 을 설정한 이후에 @Value 작업을 하는 듯 하다.
    private String name;
    @Value("${person.age}")
    private int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
