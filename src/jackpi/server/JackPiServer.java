package jackpi.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Messing Levin <meslem12@htl-kaindorf.ac.at>
 */
public class JackPiServer
{

  /*
  * --Protokoll--
  * Connection opened
  * Client schickt: "R1-R2" (100-100)
  * Server antwortet "Ergebnis" (50)
  * Connection closed
  *
   */
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
          InputStream is = socket.getInputStream();

          byte[] tmp = new byte[50];
          is.read(tmp);

          String[] array = new String(tmp).split("-");

          double r1 = Double.parseDouble(array[0].replace(',','.'));
          double r2 = Double.parseDouble(array[1].replace(',','.'));
          
          String res = String.format("%.2f", (1/(1/r1 + 1/r2)));
          
          os.write(res.getBytes());
          os.flush();
          System.out.println("Data sent: " + res);
          socket.close();
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
