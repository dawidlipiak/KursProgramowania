import java.net.*;
import java.io.*;

/**
 * Creating a client and its activities handling
 */
public class MultiClient {

    /**
     * main function of a client
     * @param args
     */
    public static void main(String[] args) {

    try  {
        //Creating a socket for a client
        Socket socket = new Socket("localhost", 4444);

        // Input for the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Output from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Creating a console for client's actions
        Console console = System.console();

        String text, serverOutput;;

        text = console.readLine("Podaj typ zmiennych wprowadzanych do drzewa: ");
        text.toLowerCase();
        out.println(text);

        // Returned information about creating a tree or sending error
        serverOutput = in.readLine();
        System.out.println(serverOutput);

        if(serverOutput.equals("-> Wrong name of the type")){
            socket.close();
            System.exit(0);
        }

        // Console handling
        do {
            text = console.readLine("Enter commend: ");
            text.toLowerCase();

            // Commend sent to the server
            out.println(text);

            // Returned action made on the server
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