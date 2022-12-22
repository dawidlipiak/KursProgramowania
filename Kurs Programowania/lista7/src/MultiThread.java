import java.io.*;
import java.net.*;

/**
 * Class that runs a client actions
 */
public class MultiThread extends Thread {
    private Socket socket;

    /**
     * Constructor of a single thread
     * @param socket connected client socket
     */
    public MultiThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Function that creates a binary tree for a client
     */
    public void run() {

        try {
            //Input from socket
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
    
            //Output for socket
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);

            Tree tree = null;

            String type;
            type = in.readLine();

            //Creating a specific type tree
            switch (type) {
                case "string" ->{ tree = new Tree<String>(); }
                case "integer", "int" -> { tree = new Tree<Integer>(); }
                case "double" -> { tree = new Tree<Double>(); }
                default -> {
                    out.println("-> Wrong name of the type");
                    socket.close();
                }
            }
            out.println("-> " + type + " tree created");


            String [] actionParts;
            String elementStr, action;
            int elementInt;
            double elementDouble;

            //handling client's commends
            do {
                action = in.readLine();
                actionParts = action.split(" ");

                try {
                    switch (actionParts[0]) {
                        case "draw":
                            out.println("-> " + tree.draw());
                            break;

                        case "insert":
                            if(type.equals("string")) {
                                elementStr = actionParts[1];
                                tree.insert(elementStr);
                                out.println("-> " + elementStr + " added");
                            }
                            else if(type.equals("double")) {
                                elementDouble = Double.parseDouble(actionParts[1]);
                                tree.insert(elementDouble);
                                out.println("-> " + elementDouble + " added");
                            }
                            else {
                                elementInt= Integer.parseInt(actionParts[1]);
                                tree.insert(elementInt);
                                out.println("-> " + elementInt+ " added");
                            }
                            break;

                        case "search":
                            if(type.equals("string")) {
                                elementStr = actionParts[1];
                                out.println("-> " + tree.search(elementStr));
                            }
                            else if(type.equals("double")) {
                                elementDouble = Double.parseDouble(actionParts[1]);
                                out.println("-> " + tree.search(elementDouble));
                            }
                            else {
                                elementInt= Integer.parseInt(actionParts[1]);
                                out.println("-> " + tree.search(elementInt));
                            }
                            break;

                        case "delete":
                            if(type.equals("string")) {
                                elementStr = actionParts[1];
                                tree.delete(elementStr);
                                out.println("-> " + elementStr + " deleted");
                            }
                            else if(type.equals("double")) {
                                elementDouble = Double.parseDouble(actionParts[1]);
                                tree.delete(elementDouble);
                                out.println("-> " + elementDouble + " deleted");
                            }
                            else {
                                elementInt= Integer.parseInt(actionParts[1]);
                                tree.delete(elementInt);
                                out.println("-> " + elementInt + " deleted");
                            }
                            break;

                        case "exit": break;

                        default:
                            out.println("-> Wrong action name");
                    }
                } catch (NumberFormatException e) { out.println("-> Wrong type of element "); }
    
            } while (!actionParts[0].equals("exit"));
    
            socket.close();

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}