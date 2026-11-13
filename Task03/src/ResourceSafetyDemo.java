import java.io.*;
class TrackedResource implements AutoCloseable {
    static int open=0; String name;
    TrackedResource(String n){name=n;open++;}
    void read(boolean fail)throws IOException{if(fail)throw new IOException("Corrupt input: "+name);System.out.println("Read "+name);}
    public void close(){open--;System.out.println("Closed "+name);}
}
public class ResourceSafetyDemo {
    static void manual() {
        System.out.println("MANUAL CLOSE");
        TrackedResource a=null,b=null;
        try { a=new TrackedResource("store-A"); a.read(false); b=new TrackedResource("store-B"); b.read(true); }
        catch(IOException e){System.out.println("Failure: "+e.getMessage());}
        finally { if(b!=null)b.close(); } // intentionally flawed: a is leaked
        System.out.println("Open after manual close: "+TrackedResource.open);
        TrackedResource.open=0; // reset controlled demonstration counter
    }
    static void automatic() {
        System.out.println("TRY-WITH-RESOURCES");
        try(TrackedResource a=new TrackedResource("store-A");TrackedResource b=new TrackedResource("store-B")){
            a.read(false);b.read(true);
        }catch(IOException e){System.out.println("Failure: "+e.getMessage());}
        System.out.println("Open after try-with-resources: "+TrackedResource.open);
    }
    public static void main(String[] args){manual();automatic();}
}
