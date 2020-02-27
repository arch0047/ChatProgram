package com.client;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    private DatagramSocket socket;
    DatagramPacket packet;
    private InetAddress address;
    private int port;     /*** represents an Internet Protocol (IP) address.*/

    public Client( String address, int port) {
        try {

            this.address = InetAddress.getByName(address);
            this.port=port;

            socket = new DatagramSocket();

        } catch (Exception e) {
            e.printStackTrace();
        }

        send("\\con: Archana");
    }


    public void send(String message) {
        try{
        message += "\\e";
        byte[] data= message.getBytes();
        packet= new DatagramPacket(data,data.length,address,port);
        socket.send(packet);
        System.out.print("Sent message to," + address.getHostAddress()+":"+ port);
        }
catch(Exception e)
     {
        e.printStackTrace();
     }
}
}

// ***********Not finished yet *********