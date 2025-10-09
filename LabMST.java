import java.util.*;
public class LabMST{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        String s=sc.nextLine();
        
        int vw=0;
        int cn=0;
        int dig=0;
        int spChar=0;

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch>='a' && ch<='z' || ch>='A' && ch<='Z'){
                if(ch=='a' || ch=='e' || ch=='i' || ch=='o' ||ch=='u' || ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U'){
                    vw++;
                }
                else{
                    if(cn<3){
                        cn++;
                    }
                }
            }else if(ch>='0' && ch<='9'){
                dig++;
            }else{
                spChar++;
            }
        }
        System.out.println("Vowels: " + vw);
        System.out.println("Consonants: " + cn);
        System.out.println("Digits: " + dig);
        System.out.println("Special Characters: " + spChar);
        sc.close();
    }
}