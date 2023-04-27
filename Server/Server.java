package Server;
import Client.CommandCaller;
import Server.commands.Command;

import Server.commands.SaveCommand;
import Server.commands.SortCommand;
import Server.core.SpaceMarine;
import Server.core.SpaceMarines;
import Server.core.SpaceMarinesComparator;
import Server.parser.Reader;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Server{
    public final static int SERVER_PORT = 8001;

    public static void main(String[] args) throws InterruptedException {


        try{

            ServerSocket server = new ServerSocket(SERVER_PORT);
            Socket client = server.accept();
            System.out.println("Connection with " + client.getInetAddress() + " is accepted");

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            ObjectInputStream objIn = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());

            //ServerSocketChannel ssc = ServerSocketChannel.open();
            //ssc.socket().bind (new InetSocketAddress (SERVER_PORT));
            //ssc.configureBlocking(false);

            Reader rd = new Reader(new File("C:\\Users\\real_\\IdeaProjects\\SimpleServer\\src\\main\\java\\Server\\parser\\backup2.xml"));
            SpaceMarines sps = rd.getPersons();
            Stream<SpaceMarine> helpStream = sps.getSpaceMarine().stream();
            sps = new SpaceMarines(helpStream.sorted(new SpaceMarinesComparator()).collect(Collectors.toCollection(LinkedList::new)));
            CollectionManager cm = new CollectionManager(sps);
            ServerInvoker si = new ServerInvoker(cm);
            while (!client.isClosed()){
                System.out.println("Server is reading...\n");

                CommandCaller commandCaller = (CommandCaller) objIn.readObject();
                String clientCommandName = commandCaller.getCommandName();
                String clientCommandParam = commandCaller.getCommandParam();
                System.out.println(clientCommandName);
                Command clientCommand = si.formCommand(clientCommandName, clientCommandParam);
                System.out.println("Got command from Client: " + clientCommandName);
                //clientCommand.execute();
                if (clientCommandName.equals("exit")){
                    clientCommand.execute();
                    System.out.println("Пока");
                    break;
                }else{
                    objOut.writeObject(clientCommand);
                    objOut.flush();
                }
            }

            System.out.println("Disconnection...");
            SaveCommand saveCommand = new SaveCommand(cm);
            saveCommand.execute();
            in.close();
            out.close();
            client.close();

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
