import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HttpServer {

    public static final String ERROR405 = "HTTP/1.1 405 Method Not Allowed\r\n\r\n<method name> not supported\r\n";
    public static final String FILENOTEXIST = "HTTP/1.1 404 Not Found\r\n\r\n<resource name> not found\r\n";
    public static final String FILENOTDIR = "File exists but its not a directory";
    public static final String FILEISOK = "HTTP/1.1 200 OK\\r\n";

    public Socket sc;
    ServerSocket server;



    public void checkPath(String docRoot) {

        Path pth = Paths.get(docRoot);
        File dir = pth.toFile();

        if(!dir.exists()) {

            System.out.println("Path does not exist");
            System.exit(1);

        } else if(!dir.isDirectory()) {

            System.out.println("File exists but its not a directory");
            System.exit(1);
        } else {

            // System.out.println("Yay!");

        }


    }

    public String startServer(int port) throws Exception {

        // If path is valid, start a server and accept connections

        System.out.println("Accepting connections...");

       server = new ServerSocket(port);

        while(true) {

            sc = server.accept();

            System.out.printf("New connection from %d\n",server.getLocalPort());
            
            InputStream is = sc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String resource = br.readLine();  

            //br.close();

            return resource;

        }


    }

    // public String getResource() {

    // }

    public String checkResource(String docRoot, String resource) throws IOException {

        String fileLoc = "";

        System.out.println(resource);
            String[] splitString = resource.split(" ");

            if(!splitString[0].equals("GET")) {

                return ERROR405;

            }
            System.out.println(splitString[1]);
            fileLoc = docRoot + splitString[1];

            Path pth = Paths.get(fileLoc);
            File dir = pth.toFile();
    
            if(!dir.exists()) {
    
                return FILENOTEXIST;
                
    
            } 

            System.out.println(fileLoc);

            return fileLoc;
        
    }


    public void writeFile(Socket socket, String msg) throws Exception {

        String prefix = FILEISOK;
        String outPutMsg = "";

        if(msg.equals(ERROR405)) {

            outPutMsg = ERROR405;

        } else if(msg.equals(FILENOTEXIST)) {

            outPutMsg = FILENOTEXIST;
        } else {

            if(msg.contains("png")) {

                outPutMsg = msg;
            }

            outPutMsg = msg;
        }

        System.out.println(outPutMsg);


        FileReader fr = new FileReader(outPutMsg);
        BufferedReader br = new BufferedReader(fr);

        String line;
        byte[] lineBytes;
        OutputStream os = socket.getOutputStream();

        HttpWriter writer = new HttpWriter(os);

        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        if(outPutMsg.contains("png")) {

            prefix = "HTTP/1.1 200 OK\r\nContent-Type: image/png\r\n\r\n";

        }

        System.out.println(prefix);

        // writer.writeString(prefix);

        while(null != (line = br.readLine())) {

            if(!line.trim().isEmpty()) {

                // lineBytes = line.getBytes();

                writer.writeString(prefix + line);
                
            }

        }

        writer.flush();

        br.close();
        fr.close();
        os.close();
        osw.close();
        bw.close();
        server.close();

    }

    public String addPrefix(String line) {

        String prefix = "";

        if(line.contains("png")) {

            prefix = "HTTP/1.1 200 OK\r\nContent-Type: image/png\r\n\r\n";
            return prefix;

        }

        return "";

        


    }
    
}
