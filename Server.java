package com.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {
// variable
    private static DatagramSocket socket;
    private static DatagramPacket packet;
    private static boolean running;
    private static ArrayList<ClientInfo> clients= new ArrayList<>();
    private static int clientId;
    private static String message;



    /*** this method will start the server, create the needed resources and initializing them  */
    public static void start(int port)
    {
        try
        {       /** creating our socket  */
            socket= new DatagramSocket(port);
            running =true;
            listen();


            System.out.println("system started on port:"+ port);
        }
        catch ( Exception e )
        {
            /***  It is a method of Java's throwable class which prints the throwable along
             * with other details like the line number and class name where the exception occurred.
             */
            e.printStackTrace();
        }
    } //end of start method......

    /*** this method will send the message to every connected clients of our server  */
    private static void broadCast(String message)
    {
       for (ClientInfo info : clients)
       {
        send (message,info.getAddress, info.getPort());
       }

    }
    //end of broadcast method...

    /*** this method will send the message to individual client  */
    private static void send(String message, InetAddress address,int port)
    {
       try
       {
           /**  converting string message to byte []  that has a size of our message not 1024 bytes.
            *  While sending data we needs 4 parameter and for receiving only need 2.
            * we are putting the data to a datagramPacket and telling it where to send  by giving the parameter address and port.
            * then telling our socket to send the info. packet to the given address and port . */
           message += "\\e" ;
          byte[] data = message.getBytes();
           packet =new DatagramPacket(data, data.length, address,port);
           socket.send(packet);
           System.out.println("Send message to, " + address.getHostAddress() +":"+ port);

       }

        catch( Exception e)
         {
            e.printStackTrace();
         }
    } // end of send method......



    /*** this method will have thread in it and will run all time as the server runs. Keeps the program alive */
    private static void listen()
    {
        /** Creating new thread with String object */
      Thread t= new Thread (" chat program")// listener/receiving the messages.
      {
         public void run()
         {
           try {
               while (running) {
                   /**  while server is running creating byte [] to receive packet/data and saving it in data [] as data. */
                   byte[] data = new byte[1024]; // to get our data

                   /**  DatagramPacket will write thw data in to the data [] */
                   packet = new DatagramPacket(data, data.length);
                   socket.receive(packet);

                   /**  after saving data in to byte [] in order to get the info. out of the byte creating a
                    *   string variable called message   */
                   message = new String(data);
                   message = message.substring(0, message.indexOf("\\e"));
                    //   "\\e" end identifier at the end of each message to find the end of each message .

                   if (! isCommend(message, packet))
                   {
                       /** after receiving the message broadcasting the message to each client */
                       broadCast(message);
               }
            }

            } catch (Exception e)
              {
                  e.printStackTrace();
              }
            }
       };
        /** method to start the thread */
        t.start();
    } //end of listen method...

/** "\con:(name)" = connection which connect client to the server with name parameter
  *  "\discon(clientId):" = disconnect client from the server with clientId parameter
 */
    public static boolean isCommend(String message, DatagramPacket packet)
    {
      if (message.startsWith("\\con:"))
       {
         //run connection code
           /** when the connection run we take the name from the message after ":"and add the name/client to the client [].
            * after creating the new client we pass the value name, clientId ,packet and port .
            * clientId ++ will give unique id to each active client on our server */
         String name= message.substring(message.indexOf(":")+1);
         clients.add(new ClientInfo(name, clientId ++, packet.getAddress(),packet.getPort()));

        // run broadcast method
        broadCast("User"+ name + ", connected");
          return true;
       }

         return false;
  }

    /*** this method will stop the server without closing the program  */
    public static void stop()
    {
      running =false;
    } //end of stop method....

}// end of the class....


// ******************Not finished yet ***********