import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Leitor {
    public String jogo(){

        ArrayList<String> lista = new ArrayList<>();
        Random random = new Random();
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader("palavras.txt"));){
            while((line = reader.readLine()) != null){
                lista.add(line);
            }
            return(lista.get(random.nextInt(lista.size())));
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            return("erro");
        }
        catch(IOException e){
            System.out.println("Something went wrong.");
            return("erro");
        }
    }

}
