import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.net.InetAddress;

public class ServerLocal extends Servidor{

	private String ipUrl;
	private String endereco;

	public ServerLocal(String endereco, String url) throws IOException {
        super(endereco, "cacheLocal");
		this.endereco = endereco;

        try{
        	String linha = leituraArquivo(url);
        	procuraIp(linha, url);
        }catch(Exception e){}
    }

    public void procuraIp(String linha, String url) throws Exception{
    	String[] enderecos = linha.split(",");

    	for (int i=0; i< enderecos.length; i++){

			String[] ipSite = enderecos[i].split("=");

			ipSite = formataString(ipSite);

    		if(ipSite[0].equals(url)){
    			//System.out.println(url + "=" + ipSite[1]);
    			ipUrl = ipSite[1];
    			break;
    		}	
    	}
    }

    public String getIp(){
    	return ipUrl;
    }

    public static String[] formataString(String[] ipSite){
        if(ipSite[0].contains("{")){
            ipSite[0] = ipSite[0].substring(1, ipSite[0].length());
        }

        if(ipSite[1].contains("}")){
            ipSite[1] = ipSite[1].substring(0, ipSite[1].length()-1);
        }

        if(ipSite[0].contains(" ")){
            ipSite[0] = ipSite[0].replaceAll(" ", "");
        }
        return ipSite;
    }
}
