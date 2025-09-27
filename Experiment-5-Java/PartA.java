import java.util.Scanner;
import java.util.ArrayList;
public class PartA{
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> list =new ArrayList<>();

        for(int i=0;i<3;i++){
            System.out.println("Enter value of index:"+i);
            int num=sc.nextInt(); //int
            list.add(num); //int =>integer autoboxing [10,30,4]
        }
        int sum=0;
        for(Integer ele:list){
            sum+=ele; //unboxing object(Integer) =>list
        }
        System.out.println("Sum:"+sum);
    }
}