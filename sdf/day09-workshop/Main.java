import java.io.Console;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class Main {


    public static void main(String[] args) throws Exception {

        ServerSocket server;
        Socket sc;


        // Task 3: get port and docRoot from command line
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

        // Task 4: Check if Path is valid
        Path pth = Paths.get(docRoot);
        File dir = pth.toFile();

        if(!dir.exists()) {

            System.out.println("Path does not exist");
            System.exit(1);

        } else if(!dir.isDirectory()) {

            System.out.println("File exists but its not a directory");
            System.exit(1);
        } else {

            System.out.println("Yay!");

        }

        // Task 5: Create Threadpool

        // Task 6: Read incoming request
        server = new ServerSocket(port);
        HttpServer httpserver = new HttpServer();

        while(true) {

            sc = server.accept();

            System.out.printf("New connection from %d\n",server.getLocalPort());
            
            InputStream is = sc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String getLine = br.readLine(); 
            System.out.println(getLine);
            String fileLoc = httpserver.checkResource(docRoot, getLine);
            System.out.println("fileLoc" + fileLoc);

            String outPutMsg = "";
            if(fileLoc.contains("png")) {


                outPutMsg = HttpServer.FILEPNG;


            } else {

                outPutMsg = HttpServer.FILEISOK;

            }

            System.out.println(outPutMsg);

            FileReader fr = new FileReader(fileLoc);
            BufferedReader brr = new BufferedReader(fr);

            OutputStream os = sc.getOutputStream();
            HttpWriter writer = new HttpWriter(os);


            String line;

            while(null != (line = brr.readLine())) {

                if(!line.trim().isEmpty()) {
    
                    // lineBytes = line.getBytes();

                    System.out.println(line);
    
                    writer.writeString(line);
                    writer.flush();
                    
                }
    
            }

            writer.flush();

            br.close();
            fr.close();
            os.close();

            server.close();


            


        }

        

        




        
    }

    

    
}
