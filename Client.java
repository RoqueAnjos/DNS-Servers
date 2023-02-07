import java.net.*;
import java.io.*;
import java.net.InetAddress;
import java.util.*;

public class Client{

	public static void main(String[] args) throws IOException{ 
		Scanner in = new Scanner(System.in);
		try{
			Socket socket = new Socket("localhost", 4999);

			while(true){
			System.out.println("Digite um url: ");
			String url = in.nextLine();

			ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
	        saida.flush();
	        saida.writeObject(url);
	        //saida.close();
	        //socket.close();
			}
		}
		catch (UnknownHostException e){
			System.out.println("Deu falha");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}