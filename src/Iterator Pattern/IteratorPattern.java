package week3_iterator;
import jdk.jfr.Frequency;

import java.util.ArrayList;
//
//Iterator pattern:
//
//Provide a way to access the elements of an aggregate object
//sequentially without exposing its underlying representation.
//
//The classes and/or objects participating in this pattern are:

//1. Iterator  (AbstractIterator)
//		defines an interface for accessing and traversing elements.
//2. ConcreteIterator  (Iterator)
//		implements the Iterator interface.
//		keeps track of the current position in the traversal of the aggregate.
//3. Aggregate  (AbstractCollection)
//		defines an interface for creating an Iterator object
//4. ConcreteAggregate  (Collection)
//		implements the Iterator creation interface to return an instance of the proper ConcreteIterator
//

public class IteratorPattern {
    static void printAggregate(AbstractIterator i) {
        System.out.println("Iterating over collection:");
        for(i.First();  !i.IsDone(); i.Next()) {
            System.out.println(i.CurrentItem().getName());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        // Create Aggregate.
        AbstractAggregate aggregate = new Collection();
        aggregate.add(new Channel("Das Erste", "Germany", 10));
        aggregate.add(new Channel("CTV-1", "China", 657));
        aggregate.add(new Channel("NOW", "Türkiye", 555));
        aggregate.add(new Channel("Show Tv", "Türkiye", 0));
        aggregate.add(new Channel("TVNZ-1", "New Zealand", 999));
        aggregate.add(new Channel("CNC World", "China", 789));
        aggregate.add(new Channel("TRT-1", "Türkiye", 676));
        aggregate.add(new Channel("ZDF", "Germany", 155));
        aggregate.add(new Channel("Mehwar TV", "Egypt", 56));

        // Create Iterator
        AbstractIterator iterator = aggregate.CreateIterator(true,0,0);
        AbstractIterator iterator2 = aggregate.CreateIterator(false,100,600);

        // Traverse the Aggregate.
        printAggregate(iterator);

        printAggregate(iterator2);
    }
}

//
//Our concrete collection consists of Items.
//

class Channel {
    public Channel(String name, String countryOrigins, int frequency) {
        _name = name;
        _frequency = frequency;
        _countryOrigins = countryOrigins;
    };
    public String getName() { return _name;};
    public int get_frequency() {return _frequency;}
    public String get_countryOrigins() {return _countryOrigins;}
    private String _name;
    private int _frequency;
    private String _countryOrigins;

    @Override
    public String toString() {
        return _name + '\t'+_frequency + "\t" + _countryOrigins ;
    }
}

//
//This is the abstract "Iterator".
//		AbstractIterator
//

interface  AbstractIterator {
    void First();
    void Next();
    Boolean IsDone () ;
    Channel CurrentItem() ;
}

//
//This is the "concrete" Iterator for collection.
//		CollectionIterator
//

class TurkeyIterator implements AbstractIterator {
    public void First() {
        for (; _current<_collection.getCount();_current++){
            if (_collection.get(_current).get_countryOrigins().equals("Türkiye")){
                return;
            }
        }
    }
    public void Next()  {
        for (; ++_current<_collection.getCount();){
            if (_collection.get(_current).get_countryOrigins().equals("Türkiye")){
                return;
            }
        }
    }
    public Channel CurrentItem() { return (IsDone()?null:_collection.get(_current)); }
    public Boolean IsDone() {	return _current >= _collection.getCount(); }
    public TurkeyIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }
    private Collection _collection;
    private int _current;
};

class FrequencyIterator implements AbstractIterator {
    public void First() {
        for (; _current<_collection.getCount();_current++){
            if (_min<_collection.get(_current).get_frequency() && _max>_collection.get(_current).get_frequency()){
                return;
            }
        }
    }
    public void Next()  {
        for (; ++_current<_collection.getCount();){
            if (_min<_collection.get(_current).get_frequency() && _max>_collection.get(_current).get_frequency()){
                return;
            }
        }
    }
    public Channel CurrentItem() { return (IsDone()?null:_collection.get(_current)); }
    public Boolean IsDone() {	return _current >= _collection.getCount(); }
    public FrequencyIterator(Collection collection,int min, int max) {
        _collection = collection;
        _current = 0;
        _min = min;
        _max = max;
    }
    private Collection _collection;
    private int _current;
    private int _min;
    private int _max;
};


//
//This is the abstract "Aggregate".
//			AbstractAggregate
//

interface AbstractAggregate {
    public AbstractIterator CreateIterator(boolean iteratorType , int min, int max);
    public void add(Channel it); 		// Not needed for iteration.
    public int getCount (); // Needed for iteration.
    public Channel get(int idx); // Needed for iteration.
};

//
//This is the concrete Aggregate.
//			Collection
//

class Collection implements AbstractAggregate {
    private	 ArrayList<Channel> _items = new ArrayList<Channel>();
    public	AbstractIterator CreateIterator(boolean iteratorType , int min, int max) {
        if (iteratorType){
            return new TurkeyIterator(this);
        }
        else {
            return new FrequencyIterator(this,min,max);
        }
    }
    public int getCount () {return _items.size(); }
    public void add(Channel item) {_items.add(item);};
    public Channel get(int index) { return _items.get(index);};
};

