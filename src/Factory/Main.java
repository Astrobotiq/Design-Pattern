package Factory;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BundleBuilder builder = new BundleBuilder();
        AbstractFactory factory = new ModernFactory();

        builder.createBundle(factory,"Modern Africa",100, "Cloud Marble" , 100 );
        builder.DisplayBundle();

        factory = new AntiqueFactory();
        builder.createBundle(factory,"Antique-1",150,"Valedictorian Factory.Table",150);
        builder.DisplayBundle();

        factory = new ModernFactory();
        builder.createBundleAll(factory,"Modern Africa",100, "Cloud Marble" , 100 );
        builder.DisplayBundle();

        factory = new AntiqueFactory();
        builder.createBundleAll(factory,"Antique-1",150,"Valedictorian Factory.Table",150);
        builder.DisplayBundle();

        factory = new FutureNowFactory();
        builder.createBundle(factory,"Wavelet Factory.Chair",200,"Corian Factory.Table",200);
        builder.DisplayBundle();
        builder.createBundleAll(factory,"Wavelet Factory.Chair",250,"Corian Factory.Table",250);
        builder.DisplayBundle();


    }
}

class BundleBuilder{
    ArrayList<Furniture> furnitures;

    public void createBundle(AbstractFactory factory,String nameOfChair, int priceOfChair, String nameOfTable, int priceOfTable){
        furnitures = new ArrayList<>();
        furnitures.add(factory.createTable(nameOfTable,priceOfTable));
        furnitures.add(factory.createChair(nameOfChair,priceOfChair));
    }

    public void createBundleAll(AbstractFactory factory,String nameOfChair, int priceOfChair, String nameOfTable, int priceOfTable){
        furnitures = factory.createFurniture(nameOfChair,priceOfChair,nameOfTable,priceOfTable);
    }

    void DisplayBundle(){
        System.out.println("\tListing Parts\n\t-------------");
        furnitures.forEach(p  -> System.out.println("\t"+ p.displayName() +
                " " + p.getPrice()));
    }
}

abstract class AbstractFactory{
    public abstract Chair createChair(String name, int price);
    public abstract Table createTable(String name, int price);
    public abstract ArrayList<Furniture> createFurniture(String nameOfChair, int priceOfChair, String nameOfTable, int priceOfTable);

}

class AntiqueFactory extends AbstractFactory{

    @Override
    public Chair createChair(String name, int price) {
        return new AntiqueChair(name, price);
    }

    @Override
    public Table createTable(String name, int price) {
        return new AntiqueTable(name, price);
    }

    @Override
    public ArrayList<Furniture> createFurniture(String nameOfChair, int priceOfChair, String nameOfTable, int priceOfTable) {
        ArrayList<Furniture> list = new ArrayList();
        list.add(new AntiqueTable(nameOfTable,priceOfTable));
        list.add(new AntiqueChair(nameOfChair,priceOfChair));
        return list;
    }
}

class ModernFactory extends AbstractFactory{

    @Override
    public Chair createChair(String name, int price) {
        return new ModernChair(name, price);
    }

    @Override
    public Table createTable(String name, int price) {
        return new ModernTable(name, price);
    }

    @Override
    public ArrayList<Furniture> createFurniture(String nameOfChair, int priceOfChair, String nameOfTable, int priceOfTable) {
        ArrayList<Furniture> list = new ArrayList();
        list.add(new ModernTable(nameOfTable,priceOfTable));
        list.add(new ModernChair(nameOfChair,priceOfChair));
        return list;
    }
}

class FutureNowFactory extends AbstractFactory{

    @Override
    public Chair createChair(String name, int price) {
        return new FutureChair(name, price);
    }

    @Override
    public Table createTable(String name, int price) {
        return new FutureTable(name, price);
    }

    @Override
    public ArrayList<Furniture> createFurniture(String nameOfChair, int priceOfChair, String nameOfTable, int priceOfTable) {
        ArrayList<Furniture> list = new ArrayList();
        list.add(new FutureTable(nameOfTable,priceOfTable));
        list.add(new FutureChair(nameOfChair,priceOfChair));
        return list;
    }
}

abstract class Furniture{
    public Furniture(String name, int price){
        this.name = name;
        this.price = price;
    }
    String name;
    int price;


    public String displayName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}

class Chair extends Furniture{
    public Chair(String name, int price){
        super(name,price);
    }
}

class AntiqueChair extends Chair{
    public AntiqueChair(String name, int price){
        super(name,price);
    }
}

class ModernChair extends Chair{
    public ModernChair(String name, int price){
        super(name,price);
    }
}

class FutureChair extends Chair{
    public FutureChair(String name, int price){
        super(name,price);
    }
}

class Table extends Furniture{
    public Table(String name, int price){
        super(name,price);
    }
}

class AntiqueTable extends Table{
    public AntiqueTable(String name, int price){
        super(name,price);
    }
}

class ModernTable extends Table{
    public ModernTable(String name, int price){
        super(name,price);
    }
}

class FutureTable extends Table{
    public FutureTable(String name, int price){
        super(name,price);
    }
}


