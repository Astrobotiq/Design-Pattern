import java.util.Scanner;

class Interval {
    int min;
    int max;

    public Interval(int min, int max){
        this.min = min;
        this.max = max;
    }

    public boolean compare(int c){
        return (c>min && c<=max)? true : false;
    }
}

abstract class Snacks{

    protected Interval interval;

    abstract boolean moneyCheck(int p);

    abstract boolean evaluate(int c);

    abstract void push();

    abstract void drop();
}

class PotatoChips extends Snacks{
    public PotatoChips(){
        interval = new Interval(0,20);
    }
    @Override
    boolean moneyCheck(int p) {
        return (p>=30)?true:false;
    }

    public boolean evaluate(int c){
        if(interval.compare(c)){
            System.out.println("Found Chips...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Potato Chips...");}
    public void drop(){System.out.println("You can now take your Chips...");}
}

class Soda extends Snacks{
    public Soda(){
        interval = new Interval(20,40);
    }
    @Override
    boolean moneyCheck(int p) {
        return (p>=15)?true:false;
    }

    public boolean evaluate(int c){
        if(c>20 && c<=40){
            System.out.println("Found Soda...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Soda...");}
    public void drop(){System.out.println("You can now take your Soda...");}
}

class Gummy extends Snacks{
    public Gummy(){
        interval = new Interval(40,60);
    }
    @Override
    boolean moneyCheck(int p) {
        return (p>=25)?true:false;
    }

    public boolean evaluate(int c){
        if(interval.compare(c)){
            System.out.println("Found Gummy...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Gummy...");}
    public void drop(){System.out.println("You can now take your Gummy...");}
}

class VendingMachine{
    Scanner scan = new Scanner(System.in);
    Snacks[] snacks;
    public VendingMachine(){
        snacks = new Snacks[]{new PotatoChips(),new Gummy(),new Soda()};
    }
    public void custChoice(int ch){
        for (Snacks s: snacks) {
            if(s.evaluate(ch)){
                System.out.println("Last Step: Please put the money for the product you choose.\nChips: 30\nSoda: 15\nGummy: 25");
                int money = scan.nextInt();
                if(s.moneyCheck(money)) {
                    s.push();
                    s.drop();
                }
                else{System.out.println("You did not entered enough money... Cannot give product... Here, take your money back.");}
            }
        }

    }


}

public class Test{
    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        VendingMachine V1 = new VendingMachine();
        System.out.println("Please enter the slot of the product you want...");
        V1.custChoice(scan.nextInt());
    }
}



