import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class predefinedExceptions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            int[] arr = {1,2,3,4,5};
            int i,b;

            i = sc.nextInt();
            b = sc.nextInt();
            System.out.println(arr[i]);
            System.out.println(arr[i]/b);
        }

        catch(ArithmeticException e){
            System.out.println("Exception caught is" + e.getMessage());
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception caught is" + e.getMessage());
        }
        finally{
            sc.close();
            System.out.println("x: finally executed");
        }
    }
}

