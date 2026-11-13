class ExamException extends Exception {
    ExamException(String m) { super(m); }
    ExamException(String m, Throwable cause) { super(m,cause); }
}
class SubmissionStorageException extends ExamException {
    SubmissionStorageException(String m, Throwable c) { super(m,c); }
}
class ExamWindowClosedException extends ExamException { ExamWindowClosedException(String m){super(m);} }
class StudentNotRegisteredException extends ExamException { StudentNotRegisteredException(String m){super(m);} }
class FileSizeLimitException extends ExamException { FileSizeLimitException(String m){super(m);} }
class Storage {
    void save(String name,long kb) throws SubmissionStorageException {
        try {
            if(kb>500) throw new java.io.IOException("Disk quota exceeded");
            System.out.println("Saved "+name);
        } catch(java.io.IOException e) {
            throw new SubmissionStorageException("Could not store "+name,e);
        }
    }
}
class ExamService {
    Storage storage=new Storage();
    void submit(String file,long kb,boolean registered,boolean open) throws ExamException {
        if(!open) throw new ExamWindowClosedException("Exam window closed");
        if(!registered) throw new StudentNotRegisteredException("Student not registered");
        if(kb>1000) throw new FileSizeLimitException("Answer file exceeds 1000 KB");
        storage.save(file,kb);
    }
}
public class StorageDemo {
    public static void main(String[] args) {
        ExamService s=new ExamService();
        Object[][] cases={{"answer01.pdf",120L,true,true},{"answer02.pdf",800L,true,true},
            {"answer03.pdf",100L,true,false},{"answer04.pdf",100L,false,true},{"answer05.pdf",1500L,true,true}};
        for(Object[] c:cases) try {
            s.submit((String)c[0],(Long)c[1],(Boolean)c[2],(Boolean)c[3]);
        } catch(ExamException e) {
            System.out.println("Error: "+e.getMessage());
            System.out.println("Cause: "+(e.getCause()==null?"none":e.getCause().getMessage()));
            if(e.getCause()!=null) e.printStackTrace(System.out);
        }
    }
}
