public class Welcome {
    void start(){
        System.out.println("************************");
        System.out.println("Welcome to Java Hangman!");
        System.out.println("************************");
    }
    void erro(int chance){
        switch (chance){
            case 0 -> System.out.println("""
                    """);
            case 1 -> System.out.println("""
                       o
                       |
                    
                      """);
            case 2 -> System.out.println("""
                       o
                      /|
                    
                      """);
            case 3 -> System.out.println("""
                       o
                      /|\\
                    
                      """);
            case 4 -> System.out.println("""
                       o
                      /|\\
                       |
                      """);
            case 5 -> System.out.println("""
                       o
                      /|\\
                      /|
                      """);
            case 6 -> System.out.println("""
                       o
                      /|\\
                      /|\\
                      """);
        }
    }

}
