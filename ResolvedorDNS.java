import java.net.*;
import java.io.*;

import static java.lang.Thread.sleep;

public class ResolvedorDNS{

	public static String ip;

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(4999);
		Socket cliente = ss.accept();
		System.out.println("Cliente conectado.");
		while(true) {
			ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
			String url = (String) entrada.readObject();
			conectaServidorLocal(url);
		}

	}

	private static void conectaServidorLocal(String url) throws Exception{
		//O Server Local é apenas para armazenar em cache os enderecos ip acessados
		ServerLocal serverLocal = new ServerLocal("192.168.0.1", url);

		ip = serverLocal.getIp();

		if(ip==null){
			//sleep(2000);
			System.out.println("Nao encontrado no servidor local.");
			conectaServidorRaiz(url);
		}else{
			System.out.println("Encontrado no servidor local.");
			System.out.println(url+"="+ip+"\n-------------------------------------");
		}
	}

	private static void conectaServidorRaiz(String url) throws Exception {
		NameServerRaiz nameServerRaiz = new NameServerRaiz("192.168.0.2", url);
		sleep(1000);
		try{
			ip = nameServerRaiz.getEnderecoTLD();
		}catch(Exception e){

		}
		//sleep(2000);
		if(ip!=null)conectaServerTLD(url);
		else System.out.println("Requisição DNS não completada\n-------------------------------------");
	}

	private static void conectaServerTLD(String url) throws Exception {
		if(ip.equals("192.155.155.0")) {
			ServerCom serverCom = new ServerCom(ip, url);
			ip = serverCom.getEnderecoNameServerDomain();
		}else if(ip.equals("192.155.155.1")) {
			ServerOrg serverOrg = new ServerOrg(ip, url);
			ip = serverOrg.getEnderecoNameServerDomain();
		}else if(ip.equals("192.155.155.4")) {
			ServerBr serverBr = new ServerBr(ip, url);
			ip = serverBr.getEnderecoNameServerDomain();
		}else if(ip.equals("192.155.155.3")) {
			ServerEdu serverEdu = new ServerEdu(ip, url);
			ip = serverEdu.getEnderecoNameServerDomain();
		}

		System.out.println("Endereco do Autoritativo: "+ip);

		if(ip!=null)conectaServerAutoritativo(url);
		else System.out.println("Requisição DNS não completada\n-------------------------------------");
	}

	private static void conectaServerAutoritativo(String url) throws IOException {
		if(ip.equals("186.155.155.0")){ //caso seja o endereço do autoritário google para google.com
			AutoritarioGoogle autoritarioGoogle = new AutoritarioGoogle(url, "186.155.155.0");
			ip=autoritarioGoogle.getIp();
		}else if(ip.equals("187.155.155.0")){ //caso seja o endereço do autoritário americanas para americanas.com.br
			AutoritarioAmericanas autoritarioAmericanas = new AutoritarioAmericanas(url, "187.155.155.0");
			ip=autoritarioAmericanas.getIp();
		}else if(ip.equals("185.155.155.0")){ //caso seja o endereço do autoritário ufpa para ufpa.edu
			AutoritarioUfpa autoritarioUfpa = new AutoritarioUfpa(url, "185.155.155.0");
			ip=autoritarioUfpa.getIp();
		}else if(ip.equals("183.155.155.1")){ //caso seja o endereço do autoritário panama para panama.org
			AutoritarioPanama autoritarioPanama = new AutoritarioPanama(url, "183.155.155.1");
			ip=autoritarioPanama.getIp();
		}

		if(ip!=null){
			System.out.println(url+"="+ip+"\n-------------------------------------");

			try(FileWriter fw = new FileWriter("cacheLocal.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
			{
				out.print(","+url+"="+ip);
			} catch (IOException e) {
				// exceção
			}
		}
		else System.out.println("Não é possível acessar esse site");
	}
}