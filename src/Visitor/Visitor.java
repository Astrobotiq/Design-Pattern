package Visitor;
import java.util.ArrayList;
interface Element {
    public void Accept(Visitor visitor);
}
interface Visitor {
    public void Visit(KingTutankhamun element);
    public void Visit(BodyWorlds element);
    public void Visit(Titanic element);

    public void Visit(Viking element);
}

abstract class Exhibition implements Element {
    private String name;
    private int noOfVisitors;

    private boolean continueApprove;
    // Constructor
    public Exhibition(String name) {
        this.name = name;
    }

    public String getName() {return name;};
    public void setname(String value) {name = value;};
    public int getNoOfVisitors() {return noOfVisitors;};
    public void setNoOfVisitors(int num) {noOfVisitors = num;};
    public boolean getContinueApprove() {return continueApprove;};
    public void setContinueApprove(boolean willContinue) {continueApprove = willContinue;};
}

class BodyWorlds extends Exhibition {
    public BodyWorlds(String name) {
        super (name);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class KingTutankhamun extends Exhibition {
    public KingTutankhamun(String name) {
        super (name);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}

}

class Titanic extends Exhibition {
    public Titanic(String name) {
        super (name);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class Viking extends Exhibition {
    public Viking(String name) {
        super (name);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}
}


class Exhibitions {
    public void Add(Exhibition employee){ employees.add(employee);};
    public void Remove(Exhibition employee) {
        for (int i = 0; i< employees.size(); i++) {
            if (employees.get(i).getName() == employee.getName()) {
                employees.remove(i);
                return;
            }
        }
    }
    public void Accept(Visitor visitor) {
        // elements accept the visitor
        for (Exhibition e: employees) {
            e.Accept(visitor);		}
    }
    private ArrayList<Exhibition> employees = new ArrayList<Exhibition>();
};


class ApproveVisitor implements  Visitor {
    public void Visit(KingTutankhamun element){
        element.setContinueApprove(element.getNoOfVisitors()>100);
        System.out.println(element.getName() + "'s new approve status: " +element.getContinueApprove());
    }
    public void Visit(BodyWorlds element){
        element.setContinueApprove(element.getNoOfVisitors()>75);
        System.out.println(element.getName() + "'s new approve status: " +element.getContinueApprove());
    }
    public void Visit(Titanic element){
        element.setContinueApprove(element.getNoOfVisitors()>250);
        System.out.println(element.getName() + "'s new approve status: " +element.getContinueApprove());
    }

    @Override
    public void Visit(Viking element) {
        element.setContinueApprove(element.getNoOfVisitors()>200);
        System.out.println(element.getName() + "'s new approve status: " +element.getContinueApprove());
    }
}

class CountVisitor implements Visitor {
    public void Visit(KingTutankhamun element){
        element.setNoOfVisitors(1+ (int)(Math.random()*((150-1)+1)));
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }
    public void Visit(BodyWorlds element){
        element.setNoOfVisitors(1+ (int)(Math.random()*((100-1)+1)));
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }
    public void Visit(Titanic element){
        element.setNoOfVisitors(1+ (int)(Math.random()*((350-1)+1)));
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }

    @Override
    public void Visit(Viking element) {
        element.setNoOfVisitors(1+ (int)(Math.random()*((275-1)+1)));
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }
}

class SecondChanceVisitor implements Visitor {
    public void Visit(KingTutankhamun element){
        element.setNoOfVisitors(element.getNoOfVisitors()>200? element.getNoOfVisitors(): element.getNoOfVisitors()+25);
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }
    public void Visit(BodyWorlds element){
        element.setNoOfVisitors(element.getNoOfVisitors()>75? element.getNoOfVisitors(): element.getNoOfVisitors()+10);
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }
    public void Visit(Titanic element){
        element.setNoOfVisitors(element.getNoOfVisitors()>200? element.getNoOfVisitors(): element.getNoOfVisitors()+50);
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }

    @Override
    public void Visit(Viking element) {
        element.setNoOfVisitors(element.getNoOfVisitors()>200? element.getNoOfVisitors(): element.getNoOfVisitors()+30);
        System.out.print(element.getName() + "'s nunber of visitors:");
        System.out.println(element.getNoOfVisitors());
    }
}

class VisitorPattern {
    public static void main(String[] args) {

        Exhibitions exhibitions = new Exhibitions();
        KingTutankhamun king = new KingTutankhamun("King");
        BodyWorlds body = new BodyWorlds("Body");
        Titanic titanic = new Titanic("Ship");
        Viking viking = new Viking("Viking");
        exhibitions.Add(king);
        exhibitions.Add(body);
        exhibitions.Add(titanic);
        exhibitions.Add(viking);

        Visitor countVis = new CountVisitor();
        Visitor approveVis = new ApproveVisitor();
        Visitor secondChance = new SecondChanceVisitor();


        exhibitions.Accept(countVis);
        exhibitions.Accept(secondChance);
        exhibitions.Accept(approveVis);



    }
}