package bignumber;

 
public class BigNumberMain {

   
    public static void main(String[] args) {
        BigNumber b1 = new BigNumber(500);
        BigNumber b2 = new BigNumber("-20000000");
        
        b1.print();
        b2.print();
        b1.Plus(b2).print();
        
        // ...
                
 
    }
    
}
