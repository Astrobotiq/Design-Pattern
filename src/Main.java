import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Product oled = new Product(200,"OLED TV" , "");
        Product oqled = new Product(200,"QLED TV" , "");
        Product ram = new Product(200,"RAM" , "");
        Product ssd = new Product(200,"SSD" , "");
        Product suit = new Product(200,"Suit" , "");
        Product shirt = new Product(200,"Shirt" , "");
        Product skirt = new Product(200,"Skirt" , "");
        Product faceCream = new Product(200,"Face Cream" , "");
        Product sunProtector = new Product(200,"Sun Protector" , "");
        Product tent = new Product(200,"Tent","");
        Product shampoo = new Product(200,"Shampoo","");
        Product parfum = new Product(200,"Parfum","");

        Category tv = new Category("TV");
        Category pc = new Category("PC");
        Category men = new Category("Men");
        Category women = new Category("Woman");
        Category skinCare = new Category("Skin Care");
        Category electronics = new Category("Electronics");
        Category fashion = new Category("Fashion");
        Category outdoor = new Category("Outdoor");
        Category cosmetics = new Category("Cosmetics");

        tv.add(oled);
        tv.add(oqled);
        pc.add(ram);
        pc.add(ssd);
        men.add(suit);
        women.add(shirt);
        women.add(skirt);
        skinCare.add(faceCream);
        skinCare.add(sunProtector);
        electronics.add(tv);
        electronics.add(pc);
        fashion.add(men);
        fashion.add(women);
        outdoor.add(tent);
        cosmetics.add(skinCare);
        cosmetics.add(shampoo);
        cosmetics.add(parfum);

        Category category = new Category("Category");
        category.add(electronics);
        category.add(fashion);
        category.add(outdoor);
        category.add(cosmetics);

        //category.display(1); //It works
        //System.out.println(category.totalPrice("Electronics")); // It works
        //category.remove(oled); //It works
        if (category.find("Suit")){
            System.out.println("We have found suit");
        }
        if (!category.find("ToothBruch")){
            System.out.println("Go look another place");
        }






    }
}
abstract class HepsiTrendy11{
    abstract void display(int indent);
    abstract String getName();
    abstract void remove(HepsiTrendy11 trendy);
    abstract int getPrice();
    abstract boolean find(String name);
    abstract int totalPrice(String name);
}
class Product extends HepsiTrendy11{
    String name;
    String description;
    int price;
    public Product(int price, String name, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public void display(int indent){
        for(int i = 1;i <= indent;i++) 	System.out.print("-");
        System.out.println(" "  + name);
    }

    public void remove(HepsiTrendy11 trendy){}

    public String getName(){
        return name;
    }

    public boolean find(String name){
        return this.name == name;
    }

    public int getPrice(){
        return price;
    }

    public int totalPrice(String name){
        return 0;
    }
}

class Category extends HepsiTrendy11{
    String name;
    ArrayList<HepsiTrendy11> container;
    public Category(String name){
        container = new ArrayList<>();
        this.name = name;
    }

    public void add(HepsiTrendy11 trendy){
        container.add(trendy);
    }
    public void remove(HepsiTrendy11 trendy){
        for (int i = 0; i < container.size(); i++){

            if (container.get(i) == trendy){
                container.remove(i);
            }

            container.get(i).remove(trendy);

        }
    }
    public void display(int indent){
        for(int i = 1;i <= indent;i++) System.out.print("-");
        System.out.println( "+ " + getName());

        // Display each child element on this node
        for (int i= 0; i< container.size(); i++) {
            container.get(i).display(indent+2);
        }
    }
    public String getName(){
        return name;
    }

    public boolean find(String name){
        for (HepsiTrendy11 trendy11:container) {
            if (trendy11.find(name)){
                return true;
            }
        }
        return false;
    }

    public int getPrice(){
        int total = 0;
        for (HepsiTrendy11 trendy:container) {
            total+= trendy.getPrice();
        }
        return total;
    }

    public int totalPrice(String name){
        int total = 0;
        for (HepsiTrendy11 trendy:container) {
            if (trendy.getName().equals(name)){
                total+=trendy.getPrice();
            }
        }
        return total;
    }
}
