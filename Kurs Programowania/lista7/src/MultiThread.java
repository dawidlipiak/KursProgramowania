import java.io.*;
import java.net.*;
 

public class MultiThread extends Thread {
    private Socket socket;
 
    public MultiThread(Socket socket) {
        this.socket = socket;
    }
 
    public void run() {

        try {
             //Odbieranie od socketa
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
    
            //Wysylanie do socketa
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);
    
            String type,action;

            Tree tree = null;

            type = in.readLine();

            String elementStr;
            int elementInt;
            double elementDouble;

            switch (type) {
                case "string" ->{
                    tree = new Tree<String>();
                }
                case "integer", "int" -> {
                    tree = new Tree<Integer>();
                }
                case "double" -> {
                    tree = new Tree<Double>();
                }
                default -> {
                    out.println("-> Wrong name of the type");
                    socket.close();
                }
            }

            out.println("-> " + type + " tree created");

            String [] actionParts;

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
                                break;
                           }
                            else if(type.equals("double")) {
                                elementDouble = Double.parseDouble(actionParts[1]);
                                tree.insert(elementDouble);
                                out.println("-> " + elementDouble + " added");
                                break;
                            }
                            else {
                                elementInt= Integer.parseInt(actionParts[1]);
                                tree.insert(elementInt);
                                out.println("-> " + elementInt+ " added");
                                break;
                            }

                        case "search":
                            if(type.equals("string")) {
                                elementStr = actionParts[1];
                                out.println("-> " + tree.search(elementStr));
                                break;
                            }
                            else if(type.equals("double")) {
                                elementDouble = Double.parseDouble(actionParts[1]);
                                out.println("-> " + tree.search(elementDouble));
                                break;
                            }
                            else {
                                elementInt= Integer.parseInt(actionParts[1]);
                                out.println("-> " + tree.search(elementInt));
                                break;
                            }


                        case "delete":
                            if(type.equals("string")) {
                                elementStr = actionParts[1];
                                tree.delete(elementStr);
                                out.println("-> " + elementStr + " deleted");
                                break;
                            }
                            else if(type.equals("double")) {
                                elementDouble = Double.parseDouble(actionParts[1]);
                                tree.delete(elementDouble);
                                out.println("-> " + elementDouble + " deleted");
                                break;
                            }
                            else {
                                elementInt= Integer.parseInt(actionParts[1]);
                                tree.delete(elementInt);
                                out.println("-> " + elementInt + " deleted");
                                break;
                            }
                        default:
                            out.println("-> Wrong action name");

                    }
                } catch (NumberFormatException e){
                    out.println("-> Wrong type of element ");
                }
    
            } while (!actionParts[0].equals("exit"));
    
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}