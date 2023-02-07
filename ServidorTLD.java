import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServidorTLD extends Servidor{

    protected String responsavel;

    public ServidorTLD(String endereco, String url, String nomeArquivo) throws Exception {
        super(endereco, nomeArquivo);

        String linha = leituraArquivo(url);
        procuraResponsavelTLD(linha, url);
    }

    public void procuraResponsavelTLD(String linha, String url) throws Exception {

        System.out.println("Procurando no Servidor TLD");

        String[] responsaveis = linha.split(",");
        responsavel = null;

        for(int i=0; i<responsaveis.length; i++){

            String[] serverResponsavel = responsaveis[i].split("=");

            serverResponsavel = formataString(serverResponsavel);

            if(serverResponsavel[1].equals(url)){
                responsavel = serverResponsavel[0];
                System.out.println("Encontrei o endereco do Servidor Autoritativo");
                break;
            }
        }
    }

    public String getEnderecoNameServerDomain(){
        return responsavel;
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