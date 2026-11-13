import java.util.concurrent.*;
import java.util.concurrent.locks.*;
class TokenCounter {
    private final int[] buffer=new int[5];private int count=0,in=0,out=0;
    public synchronized void put(int token)throws InterruptedException{
        while(count==buffer.length)wait();
        buffer[in]=token;in=(in+1)%buffer.length;count++;notifyAll();
    }
    public synchronized int take()throws InterruptedException{
        while(count==0)wait();
        int token=buffer[out];out=(out+1)%buffer.length;count--;notifyAll();return token;
    }
}
class Wallet {final int id;int balance;final ReentrantLock lock=new ReentrantLock();Wallet(int i,int b){id=i;balance=b;}}
public class ConcurrencyProblemsDemo {
    static void transfer(Wallet from,Wallet to,int amount){
        Wallet first=from.id<to.id?from:to,second=from.id<to.id?to:from;
        first.lock.lock();try{second.lock.lock();try{from.balance-=amount;to.balance+=amount;}finally{second.lock.unlock();}}finally{first.lock.unlock();}
    }
    static void await(CountDownLatch l){try{l.await();}catch(InterruptedException e){Thread.currentThread().interrupt();}}
    static void deadlockCheck()throws Exception{
        Object a=new Object(),b=new Object();CountDownLatch both=new CountDownLatch(2);
        Thread x=new Thread(()->{synchronized(a){both.countDown();await(both);synchronized(b){}}});
        Thread y=new Thread(()->{synchronized(b){both.countDown();await(both);synchronized(a){}}});
        x.setDaemon(true);y.setDaemon(true);x.start();y.start();x.join(150);y.join(150);
        System.out.println("Opposite lock-order deadlock detected="+(x.isAlive()&&y.isAlive()));
    }
    public static void main(String[] args)throws Exception{
        deadlockCheck();Wallet a=new Wallet(1,1000),b=new Wallet(2,1000);transfer(a,b,100);transfer(b,a,50);
        System.out.println("Ordered-lock balances="+a.balance+","+b.balance);
        TokenCounter c=new TokenCounter();int[] sums=new int[2];
        Thread producer=new Thread(()->{try{for(int i=1;i<=20;i++)c.put(i);}catch(InterruptedException e){Thread.currentThread().interrupt();}});
        Thread[] consumers=new Thread[2];for(int i=0;i<2;i++){final int id=i;consumers[i]=new Thread(()->{try{for(int k=0;k<10;k++)sums[id]+=c.take();}catch(InterruptedException e){Thread.currentThread().interrupt();}});}
        producer.start();for(Thread t:consumers)t.start();producer.join();for(Thread t:consumers)t.join();
        System.out.println("Sum of tokens collected: "+(sums[0]+sums[1])+" (expected 210)");
        ArrayBlockingQueue<Integer> q=new ArrayBlockingQueue<>(5);q.put(7);System.out.println("ArrayBlockingQueue take="+q.take());
    }
}
