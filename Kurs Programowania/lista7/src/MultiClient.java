import java.net.*;
import java.io.*;
 

public class MultiClient {
 
    public static void main(String[] args) {

    try  {
 
        Socket socket = new Socket("localhost", 4444); 
            // Wysylanie do serwera
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Odbieranie z serwera
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Console console = System.console();
        String text, serverOutput;;

        text = console.readLine("Podaj typ zmiennych wprowadzanych do drzewa: ");
        text.toLowerCase();

        out.println(text);

        serverOutput = in.readLine();
        System.out.println(serverOutput);

        if(serverOutput.equals("-> Wrong name of the type")){
            socket.close();
            System.exit(0);
        }

        do {
            text = console.readLine("Enter commend: ");
            text.toLowerCase();


            // Wysylanie do serwera
            out.println(text);

            // Odbieranie z serwer
            serverOutput = in.readLine();
            System.out.println(serverOutput);

        } while (!text.equals("exit"));
        socket.close();
 
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}