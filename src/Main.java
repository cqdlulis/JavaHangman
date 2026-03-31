import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public void main(String[] args) {
        int chance = 0;
        int check = 0;
        int win = 0;
        Leitor leitor = new Leitor();
        String teste = leitor.jogo();
        Welcome welcome = new Welcome();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String> listacheca = new ArrayList<>();
        String[] aux = teste.split(" ");
        welcome.start();
        System.out.println();
        System.out.print("Word: ");
        for (String auxi : aux) {
            for (int i = 0; i < auxi.length(); i++) {
                System.out.print("_");
                lista.add("_");
            }
            System.out.print(" ");
            lista.add(" ");
        }
        lista.remove(lista.size()- 1);
        String[] auxa = teste.split("");
        String escolha;
        String teste2 = teste.replace(" ","");
        String[] auxb = teste2.split("");

        do {

            do{
                System.out.println();
                System.out.print("Guess a letter: ");
                escolha = scanner.nextLine();
            }while(listacheca.contains(escolha));
            for (int i = 0; i < lista.toArray().length; i++) {
                if (auxa[i].equals(escolha)) {
                    listacheca.add(escolha);
                    lista.set(i, auxa[i]);
                    win++;
                } else {
                    check++;
                }}
            if(check == lista.toArray().length){
                System.out.println("Wrong Guess!");
                chance++;
                welcome.erro(chance);
            }
            check = 0;
            System.out.print("Word: ");
            for(String testeaa : lista){
                System.out.print(testeaa);
            }
            if(win == auxb.length){
                System.out.println();
                System.out.println("YOU WON!");
                break;
            }
        }while (chance < 6);
        if(chance == 6){
            System.out.println();
            System.out.println("YOU LOST!");
        }
        System.out.println("The word was: " + teste);

    }
}
