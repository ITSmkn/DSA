package s_4_solution;
 
// <<<<<<<<<<<<<<<<<|soal<2>|>>>>>>>>>>>>>>>>>>

// every member of this Queue is more than 0 (0<x) 
public class Queue {
    
    int elements [];
    
    private int front;
    private int rear;
    
    private int number; // the number of elements .
    
    private final int length = 10; // length can be changed (it's 10 by default) ...
    
    public Queue(){
        front = 0;
        rear = -1;        
        number = 0;
        elements = new int[length];
    }
    
    private boolean isFull(){
        if(rear == length-1 && front == 0){
            return true;
        }
        return false;
    }
    
    private boolean isEmpty(){
        if(number == 0){
            return true;
        }
        return false;
    }
    
    public void enQueue(int input){ // input > 0;
        if(isFull()) throw new FullQueueException("Queue is Full!");
 
        rear += 1;
        number += 1;
        elements[rear] = input;
 
    }
    
    public int deQueue(){
        if(isEmpty()) throw new EmptyQueueException("Queue is Empty!");
        int min = elements[0];
        for(int i = 0 ; i <= rear ; i++){
            if(elements[i] < min){
                min = elements[i];
            }
        }
        
        for(int i = 0 ; i <= rear ; i++){
            if(min == elements[i]){
                elements[i] = 0 ;
                number-=1;
                for(int j = i ; j < rear ; j++){
                    elements[j] = elements[j+1];
                }
                elements[rear] = 0;
                rear-=1;
                break;
            }

        }
        return min;
        
    }
    
}

 