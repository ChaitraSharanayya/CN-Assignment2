/*Sender side*/
package network3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class sender {

    
    public static void main(String[] args) throws SocketException, IOException {
        
         BufferedReader senderRead =new BufferedReader(new InputStreamReader(System.in));
      
      InetAddress IP = InetAddress.getByName("127.0.0.1");
      DatagramSocket senderSocket = new DatagramSocket();
      while(true)    //true
      {
    	  byte[] sendbuffer = new byte[1024];
          byte[] receivebuffer = new byte[1024];
          
          System.out.print("\nsender: ");
          String senderData =senderRead.readLine();
          sendbuffer = senderData.getBytes();        
          DatagramPacket sendPacket =
          new DatagramPacket(sendbuffer, sendbuffer.length, IP, 9876);
         senderSocket.send(sendPacket);
          if(senderData.equalsIgnoreCase("bye"))
          {
              System.out.println("Connection ended by sender");
              break;
          }
          DatagramPacket receivePacket =
          new DatagramPacket(receivebuffer, receivebuffer.length);
          senderSocket.receive(receivePacket);
          String serverData = new String(receivePacket.getData());
          System.out.print("\nServer: " + serverData);
          
          
          }
          senderSocket.close();
        }
        
    }
