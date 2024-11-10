package s_4_solution;

public class S_4_solution_Main {

    public static void main(String[] args){
        
    // ======================== Queue ===============================
        Queue q = new Queue();
        for(int i = 0 ; i < 10 ; i++){
            System.out.print(q.elements[i] + " ");
        }
        
        System.out.print( "\n");
        q.enQueue(5); 
        for(int i = 0 ; i < 10 ; i++){
            System.out.print(q.elements[i] + " ");
        }
        
        System.out.print( "\n");
        q.enQueue(6);
        for(int i = 0 ; i < 10 ; i++){
            System.out.print(q.elements[i] + " ");
        }
        
        System.out.print( "\n");
        q.deQueue();
        for(int i = 0 ; i < 10 ; i++){
            System.out.print(q.elements[i] + " ");
        }
             
        System.out.print( "\n");
        q.enQueue(1);
        for(int i = 0 ; i < 10 ; i++){
            System.out.print(q.elements[i] + " ");
        }
        
        System.out.print( "\n");
        q.enQueue(5);
        q.enQueue(4);
        q.enQueue(3);
        for(int i = 0 ; i < 10 ; i++){
            System.out.print(q.elements[i] + " ");
        }
        
        System.out.print( "\n");
        q.deQueue();
        for(int i = 0 ; i < 10 ; i++){
            System.out.print(q.elements[i] + " ");
        }
    
    // ======================= DualQueue =======================
    
        DualQueue Q = new DualQueue(50, 50);  

        Q.addElement(5, 1);  
        Q.addElement(6, 1);  
        Q.addElement(7, 1);  
        // ==============================
        Q.addElement(20, 2);  
        Q.addElement(30, 2);  
        Q.addElement(40, 2);  

        Q.displayQueues();
        System.out.print(Q.removeElement(1) + "\n");  
        System.out.print(Q.removeElement(2) + "\n");  
        Q.displayQueues();  
 
    }
    
}
