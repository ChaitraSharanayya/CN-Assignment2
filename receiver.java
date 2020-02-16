/*Receiver side*/
 
package network3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class receiver {

   
    public static void main(String[] args) throws SocketException, IOException {
        
         DatagramSocket receiverSocket = new DatagramSocket(9876);
         boolean bye=false;
      
      while(true) 
        {
          byte[] receivebuffer = new byte[1024];
          byte[] sendbuffer  = new byte[1024];
          DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
          receiverSocket.receive(recvdpkt);
          InetAddress IP = recvdpkt.getAddress();
          int portno = recvdpkt.getPort();
          String senderdata = new String(recvdpkt.getData());
          System.out.println("\nsender : "+ senderdata);
          System.out.print("\nreceiver : ");
          BufferedReader receiverRead = new BufferedReader(new InputStreamReader (System.in) );
          String receiverdata = receiverRead.readLine();
          
          sendbuffer = receiverdata.getBytes();
          DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP,portno);
          receiverSocket.send(sendPacket); 
          //here the check condition for receiverdata which must be bye
          if(receiverdata.equalsIgnoreCase("bye"))
          {
              System.out.println("connection ended by receiver");
              break;
          }
          
        }
      receiverSocket.close();
  }
          
  }
