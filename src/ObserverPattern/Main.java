package ObserverPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Principal principal = new Principal();
        SchoolBell bell = new SchoolBell(principal);
        Observer class1 = new Classroom(bell,20);
        Observer class2 = new Classroom(bell,20);
        Observer class3 = new Classroom(bell,20);
        Observer class4 = new Classroom(bell,50);
        Observer class5 = new Classroom(bell,50);
        Observer class6 = new Classroom(bell,50);
        bell.setInternalClock(20);
        System.out.println("Count: "+Counter.getCounter());
        bell.setInternalClock(50);
        System.out.println("Count: "+Counter.getCounter());
    }



}

abstract class Subject{
    //protected ArrayList<Classroom> list;
    public Principal principal;

    public Subject(Principal prin){
        //list = new ArrayList<>();
        principal = prin;
    }
    public void attach(Classroom observer){
        principal.register(this,observer);
    }

    public void deattach(Classroom observer){
        //list.remove(observer);
        principal.unregister(this,observer);
    }

    public abstract void Notify();
}

class SchoolBell extends Subject{
    private int internalClock;

    public SchoolBell(Principal prin) {
        super(prin);
    }

    public void setInternalClock(int time){
        internalClock = time;
        if (time==20||time==50){
            Notify();
        }
    }

    @Override
    public void Notify() {
        if (internalClock == 20){
            principal.notifyTwenty(this);
        } else if (internalClock == 50) {
            principal.notifyFifty(this);
        }
    }
}

abstract class Observer{
    protected SchoolBell subject;

    public Observer(SchoolBell sub){
        subject = sub;
    }

    public abstract void update();
}

class Classroom extends Observer{
    int invokeTime;

    public Classroom(SchoolBell sub, int time) {
        super(sub);
        subject.attach(this);
        invokeTime = time;
    }

    public int getTime(){
        return invokeTime;
    }


    @Override
    public void update() {
        System.out.println("Tenefüs başladı. Saat: " + invokeTime);
        Counter.addToCounter();
    }
}

class Counter{
    private static int updateCounter;

    public static void addToCounter(){
        updateCounter +=1;
    }

    public static int getCounter(){
        return updateCounter;
    }

}

class Principal{
    HashMap<Subject,Classroom> hashMap;

    public Principal(){
        hashMap = new HashMap<>();
    }
    public void register(Subject bell, Classroom classroom){
        hashMap.put(bell,classroom);
    }
    public void unregister(Subject bell, Classroom classroom){
        hashMap.remove(bell,classroom);
    }

    public void notifyTwenty(Subject subject){
        System.out.println(hashMap.size());
        for (Map.Entry<Subject, Classroom> entry : hashMap.entrySet()){
            if (subject == entry.getKey()) {
                if (entry.getValue().getTime() == 20){
                    entry.getValue().update();
                }
            }
        }
    }

    public void notifyFifty(Subject subject){
        for (Map.Entry<Subject, Classroom> entry : hashMap.entrySet()){
            if (subject == entry.getKey()) {
                if (entry.getValue().getTime() == 50){
                    entry.getValue().update();
                }
            }
        }
    }

}
