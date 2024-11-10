package s_4_solution;
 
public class EmptyQueueException extends RuntimeException{
    
    public EmptyQueueException(){
        super();
    }
    
    public EmptyQueueException(String str){
        super(str);
    }
    
}
