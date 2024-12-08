package bignumber;

 
public class BigNumberMain {

   
    public static void main(String[] args) {
        BigNumber b1 = new BigNumber("123456789123456" );
        BigNumber b2 = new BigNumber("12345");
    
        b1.divide(b2).print();
 
    }
    
}
