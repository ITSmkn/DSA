package bignumber;

 
public class BigNumberMain {

   
    public static void main(String[] args) {
        BigNumber bn1 = new BigNumber(44);
        BigNumber bn2 = new BigNumber("123456");
        byte b[] = {1,2,3,4,5,6};
        BigNumber bn3 = new BigNumber(b);
        
        BigNumber bn4 = bn1.Plus(bn2);
        bn4.print();
        
 
    }
    
}
