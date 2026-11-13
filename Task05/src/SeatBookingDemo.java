import java.util.concurrent.*;
class SeatBooking {
    final boolean[] booked; int bookingsMade=0;
    SeatBooking(int n){booked=new boolean[n];}
    boolean unsafe(int seat,CountDownLatch checked,CountDownLatch go){
        if(booked[seat])return false;checked.countDown();
        try{go.await();}catch(InterruptedException e){Thread.currentThread().interrupt();}
        booked[seat]=true;bookingsMade++;return true;
    }
    synchronized boolean synchronizedMethod(int seat){
        if(booked[seat])return false;booked[seat]=true;bookingsMade++;return true;
    }
    boolean synchronizedBlock(int seat){
        synchronized(this){if(booked[seat])return false;booked[seat]=true;bookingsMade++;return true;}
    }
}
public class SeatBookingDemo {
    static int unsafeTest()throws Exception{
        SeatBooking s=new SeatBooking(1);int n=20;CountDownLatch checked=new CountDownLatch(n),go=new CountDownLatch(1);Thread[] ts=new Thread[n];
        for(int i=0;i<n;i++)ts[i]=new Thread(()->s.unsafe(0,checked,go));
        for(Thread t:ts)t.start();checked.await();go.countDown();for(Thread t:ts)t.join();return s.bookingsMade;
    }
    static int safeTest(boolean block)throws Exception{
        SeatBooking s=new SeatBooking(10);Thread[] ts=new Thread[100];
        for(int i=0;i<100;i++){final int seat=i%10;ts[i]=new Thread(()->{if(block)s.synchronizedBlock(seat);else s.synchronizedMethod(seat);});ts[i].start();}
        for(Thread t:ts)t.join();return s.bookingsMade;
    }
    public static void main(String[] args)throws Exception{
        System.out.println("Unsafe controlled race bookings on one seat="+unsafeTest());
        System.out.println("Synchronized method seats sold="+safeTest(false));
        System.out.println("Synchronized block seats sold="+safeTest(true));
    }
}
