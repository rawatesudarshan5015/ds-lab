import java.util.*;

class Machine{

    private String name;
    private int clockTime;

    public Machine(String name,int clockTime){
        this.name=name;
        this.clockTime=clockTime;
    }

    public String getName(){
        return name;
    }
    public int getClockTime(){
        return clockTime;
    }

    public void adjustclock(int offset){
        clockTime += offset;
    }

    public int calculateoffset(int masterTime){
        return clockTime-masterTime;
    }
}


public class berkeley{
    public static void main(String[] args){
        int masterTime=10000;

        List<Machine>machine=new ArrayList<>();

        machine.add(new Machine("Machine A",9990));
        machine.add(new Machine("Machine b",10010));
        machine.add(new Machine("Machine c",10030));
        machine.add(new Machine("Machine d",9980));


        List<Integer>offsets=new ArrayList<>();
        System.out.println("Initial clock time");
        for(Machine m:machine){
            System.out.println(m.getName()+": "+m.getClockTime());
            int offset = m.calculateoffset(masterTime);
            offsets.add(offset);
        }


        offsets.add(0);

        int totaloffset =0;
        for(int offset:offsets){
            totaloffset +=offset;
        }
        int averageoffset = totaloffset/offsets.size();

        System.out.println("Adjusted clocks....");

        masterTime += averageoffset;
        System.out.println("master clock Adjusted to: "+masterTime);

        for(Machine m:machine){
            int offset =m.calculateoffset(masterTime);
            m.adjustclock(-offset);
        }

        System.out.println("Synchronized clock time: ");
        System.out.println("master clock time: "+masterTime);

        for(Machine m:machine){
            System.out.println(m.getName()+": "+m.getClockTime());

        }


    }
}