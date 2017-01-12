package jackpi.server;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Messing Levin <meslem12@htl-kaindorf.ac.at>
 */
public class JackPiServer
{

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    try
    {
      System.out.println("Starting Server");
      ServerSocket server = new ServerSocket(9999);
      while(true)
      {
        try (Socket socket = server.accept())
        {
          System.out.println("Connected");
          OutputStream os = socket.getOutputStream();
          os.write("I am here".getBytes());
          os.flush();
        }
        System.out.println("Disconnected");
      }
    }
    catch (Exception ex)
    {
      System.out.println(ex);
    }
  }

}
