package in.curioustools.at1.test;

import org.junit.Test;

import in.curioustools.at1.test.Person.CatBuilder;

public class Tester {

    @Test
    public void test(){
        Tester.main(null);
    }

    public static void main2(String[] args) {

        Person p = new Person("Ansh",22,'M');

        String details  = p.getClassDetails();
        System.out.println(details);

        StringBuilder b = new StringBuilder();
        char end = '\t';
        String details2 = p.getClassDetails(b,end);
        System.out.println(details2);

        CatBuilder c = new CatBuilder();
        char end2 = ',';
        String details3 = p.getClassDetails(c,end2);
        System.out.println(details3);

    }

    public  interface GenderEnforcement {
        public boolean getGender();
    }

    public static class  Ravi implements GenderEnforcement {
        @Override public boolean getGender() { return false; }
    }

    public static class Ram implements GenderEnforcement {
        @Override public boolean getGender() { return false; }
    }

    public static void checkGender(GenderEnforcement g){
        System.out.println(g.getGender());
    }

    public static void main(String[] args) {
        checkGender(new Ram());
        checkGender(new Ravi());
    }


}
