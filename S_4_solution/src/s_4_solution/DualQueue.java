package s_4_solution;

// <<<<<<<<<<<<<<<<<|soal<3>|>>>>>>>>>>>>>>>>>>

public class DualQueue {
private int[] queueData;  
    private int queue1Size, totalCapacity, queue1Front, queue2Front, queue1Rear, queue2Rear;  

    public DualQueue() {  
        totalCapacity = 100;  
        queueData = new int[totalCapacity];  
        queue1Size = 50;  
        queue1Front = 0;  
        queue2Front = queue1Size;  
        queue1Rear = -1;  
        queue2Rear = queue1Size - 1;  
    }  

    public DualQueue(int queue1Size, int queue2Size) {  
        totalCapacity = queue1Size + queue2Size;  
        queueData = new int[totalCapacity];  
        this.queue1Size = queue1Size;  
        queue1Front = 0;  
        queue2Front = queue1Size;  
        queue1Rear = -1;  
        queue2Rear = queue1Size - 1;  
    }  

    public void addElement(int value, int queueNumber) {  
        if (queueNumber == 1) {  
            if ((queue1Rear + 1) >= queue1Size) {  
                System.out.println("Queue 1 is full.");  
                return;  
            }  
            queue1Rear++;  
            queueData[queue1Rear] = value;  
        } else if (queueNumber == 2) {  
            if ((queue2Rear + 1) >= totalCapacity) {  
                System.out.println("Queue 2 is full.");  
                return;  
            }  
            queue2Rear++;  
            queueData[queue2Rear] = value;  
        }  
    }  

    public int removeElement(int queueNumber) {  
        if (queueNumber == 1) {  
            if (queue1Rear < queue1Front) {  
                System.out.println("Queue 1 is empty.");  
                return -1;  
            }  
            return queueData[queue1Front++];  
        } else if (queueNumber == 2) {  
            if (queue2Rear < queue2Front) {  
                System.out.println("Queue 2 is empty.");  
                return -1;  
            }  
            return queueData[queue2Front++];  
        }  
        return -1;  
    }  

    public void displayQueues() {  
        System.out.print("Queue 1: ");  
        for (int i = queue1Front; i <= queue1Rear; i++) {  
            System.out.print(queueData[i] + " ");  
        }  
        System.out.println();  

        System.out.print("Queue 2: ");  
        for (int i = queue2Front; i <= queue2Rear; i++) {  
            System.out.print(queueData[i] + " ");  
        }  
        System.out.println();  
    }   
}
