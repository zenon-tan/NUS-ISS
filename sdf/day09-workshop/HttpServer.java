import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpServer {

    public static final String ERROR405 = "HTTP/1.1 405 Method Not Allowed\r\n\r\n<method name> not supported\r\n";
    public static final String FILENOTEXIST = "HTTP/1.1 404 Not Found\r\n\r\n<resource name> not found\r\n";
    public static final String FILENOTDIR = "File exists but its not a directory";
    public static final String FILEISOK = "HTTP/1.1 200 OK\r\n";
    public static final String FILEPNG = "HTTP/1.1 200 OK\r\nContent-Type: image/png\r\n\r\n";

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

            return fileLoc;
        
    }
    
}
