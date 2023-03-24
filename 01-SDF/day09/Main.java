import java.io.Console;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception {

        int port = 3000;
        String docRoot = "./target";

        if(args.length == 2) {

            if(args[0].equals("--port")) {

            port = Integer.parseInt(args[1]);

            } else if(args[0].equals("--docroot")) {

                docRoot = args[1];

            }


        } else if (args.length == 4) {

            port = Integer.parseInt(args[1]);
            docRoot = args[3];

        }

        System.out.printf("Port: %d Doc Root: %s\n", port, docRoot);

        System.out.println("Accepting connections...");

        ServerSocket server = new ServerSocket(port);

        while(true) {

            HttpServer server = new HttpServer();
            server.checkPath(docRoot);

        }

        String output = server.startServer(port);
        System.out.println("Output: " + output);
        String msg = server.checkResource(docRoot, output);
        System.out.println("Msg: " + msg);
        server.writeFile(server.sc, msg);



        // Parse input into HttpClientConnection
        // HttpClientConnection will check the input and if correct, pass the info
        // To the server and get the item

        // Read input from console

    
        
    }
    
}
