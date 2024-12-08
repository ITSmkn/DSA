package postfixprefix;

public class MainClass {

    public static void main(String[] args) {
        PostfixPrefix exp = new PostfixPrefix();

        System.out.println("Postfix :" + exp.convertToPostfix());
        System.out.println("Prefix : " + exp.convertToPrefix());

        System.out.println(exp.compute(10.0));

        exp.plotGraph();
        
 

    }

}
