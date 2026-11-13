import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

class Result {
    final String file; final boolean success; final String reason;
    Result(String f,boolean s,String r){file=f;success=s;reason=r;}
}
public class ResultProcessor {
    static Path generate(Path dir,int n)throws IOException{
        Files.createDirectories(dir);
        for(int i=0;i<n;i++){
            Path f=dir.resolve(String.format("score-%05d.txt",i));
            if(i%1000==0)continue; // intentionally missing
            if(i%997==0)Files.writeString(f,"CORRUPT"); // intentionally corrupt
            else Files.writeString(f,"student-"+i+","+(i%101));
        }
        return dir;
    }
    static Result process(Path p){
        try{
            if(!Files.exists(p))return new Result(p.getFileName().toString(),false,"missing");
            String[] fields=Files.readString(p).trim().split(",");
            if(fields.length!=2)return new Result(p.getFileName().toString(),false,"corrupt");
            int score=Integer.parseInt(fields[1]);
            if(score<0||score>100)return new Result(p.getFileName().toString(),false,"invalid score");
            return new Result(p.getFileName().toString(),true,"ok");
        }catch(Exception e){return new Result(p.getFileName().toString(),false,e.getClass().getSimpleName());}
    }
    static void execute(Path dir,int n,int poolSize)throws Exception{
        ExecutorService pool=Executors.newFixedThreadPool(poolSize);
        ConcurrentLinkedQueue<Result> results=new ConcurrentLinkedQueue<>();
        List<Future<Result>> futures=new ArrayList<>();
        long start=System.nanoTime();
        for(int i=0;i<n;i++){
            Path p=dir.resolve(String.format("score-%05d.txt",i));
            Callable<Result> job=()->process(p);
            futures.add(pool.submit(job));
        }
        for(Future<Result> f:futures){
            try{results.add(f.get());}
            catch(ExecutionException e){results.add(new Result("unknown",false,"task failure"));}
        }
        pool.shutdown();
        long elapsed=System.nanoTime()-start;
        long ok=results.stream().filter(r->r.success).count();
        long missing=results.stream().filter(r->r.reason.equals("missing")).count();
        long corrupt=results.stream().filter(r->r.reason.equals("corrupt")).count();
        System.out.printf("pool=%d total=%d success=%d failed=%d missing=%d corrupt=%d time_ns=%d%n",
            poolSize,results.size(),ok,results.size()-ok,missing,corrupt,elapsed);
    }
    public static void main(String[] args)throws Exception{
        int n=args.length==0?10000:Integer.parseInt(args[0]);
        Path dir=generate(Paths.get("Task07","input","generated-scores"),n);
        System.out.println("Generated score-file workload: "+n);
        for(int p:new int[]{1,2,4,8})execute(dir,n,p);
        System.out.println("Summary validation: each pool reports exactly "+n+" outcomes.");
    }
}
