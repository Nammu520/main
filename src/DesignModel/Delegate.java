package DesignModel;

public class Delegate {
    public static void main(String[] args) {

    }

}

interface Person {
    void said();
}

class Boss implements Person {
    @Override
    public void said() {
        System.out.println("I am a Boss");
    }
}

class Normal implements Person {
    @Override
    public void said() {
        System.out.println("I can help Boss to do something");
    }
}

class Delegater {
    Person p = new Normal();

    public void said() {
        p.said();
    }
}