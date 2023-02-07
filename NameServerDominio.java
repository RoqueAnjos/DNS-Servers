import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NameServerDominio extends Servidor{
	
	private String ipUrl, endereco, nomeArquivo;

	public NameServerDominio(String url, String endereco, String nomeArquivo) throws IOException {
		super(endereco, nomeArquivo);
		this.endereco = endereco;
		this.nomeArquivo = nomeArquivo;

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

    		if(ipSite[1].equals(url)){
    			ipUrl = ipSite[0];
    			break;
    		}else if(i==enderecos.length-1){
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