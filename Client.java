/*
    RAYA - ПРОГРАММИРОВАНИЕ И IT 
*/ 

package Sockets;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        try{
        clientSocket = new Socket("localhost",4040);       // Подключение к серверу на localhost:4040
        reader = new BufferedReader(new InputStreamReader(System.in));
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        String word = reader.readLine();

        out.write(word + "\n");
        out.flush();

        String serverWord = in.readLine();
            System.out.println(serverWord);


    }catch(Exception e){

        }finally {
            System.out.println("Client has exit");
            clientSocket.close();
            in.close();
            out.close();
        }
    }

}
