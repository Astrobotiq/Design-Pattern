import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

abstract class Task{
    public abstract void Execute(CPU cpu);
}

class makeRed extends Task{
    @Override
    public void Execute(CPU cpu) {
        cpu.makeRed();
    }
}

class makeBlue extends Task{
    @Override
    public void Execute(CPU cpu) {
        cpu.makeBlue();
    }
}

class makeGreen extends Task{
    @Override
    public void Execute(CPU cpu) {
        cpu.makeGreen();
    }
}

class makeYellow extends Task{
    @Override
    public void Execute(CPU cpu) {
        cpu.makeYellow();
    }
}

class PC{

}

class CPU extends PC{
    String screenColor;

    void makeRed(){
        screenColor = "Red";
        Print();
    }

    void makeBlue(){
        screenColor = "Blue";
        Print();
    }

    void makeGreen(){
        screenColor = "Green";
        Print();
    }

    void makeYellow(){
        screenColor = "Yellow";
        Print();
    }

    public void Print(){
        System.out.println("The screen now shows "+ screenColor);
    }
}

class TaskScheduler{
    ArrayList<Task> taskQueue;
    int current;
    int lastExecuted;
    CPU cpu;
    public TaskScheduler(CPU cpu){
        taskQueue = new ArrayList<>();
        current = 0;
        lastExecuted = 0;
        this.cpu =cpu;
    }

    public void addTask(Task task){
        taskQueue.add(current,task);
        current++;
    }

    void run(){
        if (current==0 )
            return;

        do{
            taskQueue.get(lastExecuted++).Execute(cpu);
        }
        while (lastExecuted<current);

    }

    void doNext(){
        if (current==0 || lastExecuted == current-1)
            return;
        taskQueue.get(lastExecuted++).Execute(cpu);
    }

    public void undo(){
        if (lastExecuted==0)
            return;
        taskQueue.get(--lastExecuted).Execute(cpu);
    }

    public void redo(){
        if (lastExecuted == current || current == 0)
            return;
        taskQueue.get(++lastExecuted).Execute(cpu);
    }
}

class OS {
    TaskScheduler taskScheduler;
    CPU cpu = new CPU();

    public OS(){
        taskScheduler = new TaskScheduler(cpu);
    }


    void add(ArrayList<Task> tasks){
        for (Task task: tasks) {
            add(task);
        }
    }

    void add(Task task){
        taskScheduler.addTask(task);
    }

    void undo(){
        taskScheduler.undo();
    }

    void redo(){
        taskScheduler.redo();
    }

    void run(){
        taskScheduler.run();
    }

}

public class CommandPatterns{
    public static void main(String[] args) {
        OS os = new OS();


        os.add(new makeGreen());
        os.add(new makeGreen());
        os.add(new makeGreen());
        os.add(new makeGreen());
        os.add(new makeRed());
        os.add(new makeRed());
        os.add(new makeRed());

        os.run();

        os.undo();
        os.undo();
        os.undo();
        os.undo();
        os.undo();
        os.undo();
        os.undo();

        os.redo();
        os.redo();
        os.redo();
        os.redo();
        os.redo();
    }
}