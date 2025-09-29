import java.util.*;
public class LabMST{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        String s=sc.nextLine();
        
        int vowels=0;
        int consonants=0;
        int digits=0;
        int specialChar=0;

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch>='a' && ch<='z' || ch>='A' && ch<='Z'){
                if(ch=='a' || ch=='e' || ch=='i' || ch=='o' ||ch=='u' || ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U'){
                    vowels++;
                }
                else{
                    consonants++;
                }
            }else if(ch>='0' && ch<='9'){
                digits++;
            }else{
                specialChar++;
            }
        }
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Special Characters: " + specialChar);
    }
}