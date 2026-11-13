import java.util.*;
import java.util.concurrent.*;
class StationTask implements Runnable {
    final int id; StationTask(int id){this.id=id;}
    public void run(){try{Thread.sleep(10L*id);}catch(InterruptedException e){Thread.currentThread().interrupt();}
        System.out.println("Runnable station "+id+" scanned 5 scripts");}
}
class ExtendedStation extends Thread {
    final int id; ExtendedStation(int id){this.id=id;}
    public void run(){try{Thread.sleep(10L*id);}catch(InterruptedException e){Thread.currentThread().interrupt();}
        System.out.println("Thread station "+id+" scanned 5 scripts");}
}
public class ThreadStationsDemo {
    public static void main(String[] args)throws Exception{
        Thread[] a=new Thread[4]; for(int i=0;i<4;i++){a[i]=new ExtendedStation(i+1);a[i].start();}
        for(Thread t:a)t.join(); System.out.println("All Thread stations joined");
        Thread[] b=new Thread[4];for(int i=0;i<4;i++){b[i]=new Thread(new StationTask(i+1));b[i].start();}
        for(Thread t:b)t.join();System.out.println("All Runnable stations joined");
        Thread probe=new Thread(()->System.out.println("Probe on "+Thread.currentThread().getName()));
        System.out.println("Before start isAlive="+probe.isAlive());probe.start();probe.join();
        System.out.println("After join isAlive="+probe.isAlive());
        Thread direct=new Thread(()->System.out.println("Direct run on "+Thread.currentThread().getName()));direct.run();
        System.out.println("Finish-order trials (scheduler-dependent):");
        for(int r=1;r<=10;r++){List<Integer> order=Collections.synchronizedList(new ArrayList<>());Thread[] ts=new Thread[4];
            for(int i=0;i<4;i++){final int id=i+1;ts[i]=new Thread(()->{try{Thread.sleep((long)(Math.random()*20));}catch(Exception e){}order.add(id);});ts[i].start();}
            for(Thread t:ts)t.join();System.out.println("Run "+r+": "+order);}
        ExecutorService pool=Executors.newFixedThreadPool(2);List<Future<Integer>> fs=new ArrayList<>();
        for(int i=1;i<=4;i++){final int id=i;fs.add(pool.submit(()->id*5));}
        int total=0;for(Future<Integer> f:fs)total+=f.get();pool.shutdown();
        System.out.println("Executor alternative total="+total);
    }
}
