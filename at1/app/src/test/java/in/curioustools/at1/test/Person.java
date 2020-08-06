package in.curioustools.at1.test;

public class Person {
    private String name;
    private int age;
    private char gender;

    public Person(String name, Integer age, Character gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getClassDetails() {
        StringBuilder builder = new StringBuilder();
        char endChar = '\n';
        builder.append(name).append(endChar);
        builder.append(age).append(endChar);
        builder.append(gender).append(endChar);
        return builder.toString();
    }

    public String getClassDetails(StringBuilder builder, char endChar) {
        builder.append(name);
        builder.append(endChar);
        builder.append(age);
        builder.append(endChar);
        builder.append(gender);
        builder.append(endChar);
        return builder.toString();
    }

    public String getClassDetails(X builder, char endChar) {
        builder.append(name);
        builder.append(endChar);
        builder.append(age);
        builder.append(endChar);
        builder.append(gender);
        builder.append(endChar);
        return builder.toString();
    }


    interface X {
        public void append(int o);

        public void append(String o);

        public void append(char o);

        public String toString();
    }


    public static class CatBuilder implements X{
        String cat = "";
        public void append(int o) {
            cat+=o;
        }

        public void append(String o) {
            cat+=o;
        }

        public void append(char o) {
            cat+=o;
        }

        public String toString() {
            return "CatBuilder{"+cat+'}';
        }
    }

}
