package com.server;

import java.net.InetAddress;


// storing clients info.
public class ClientInfo
{
    public InetAddress getAddress;
    private String name;
    private int id;
    private InetAddress address ; /** represents an Internet Protocol (IP) address. */
    private int port;


    public ClientInfo(String name, int id, InetAddress address, int port)
    {
        this.name = name;
        this.id = id;
        this.address = address;
        this.port = port;
    }



    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public InetAddress getAddress()
    {
        return address;
    }

    public int getPort()
    {
        return port;
    }


    } // end of the class


