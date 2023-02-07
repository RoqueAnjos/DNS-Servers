public class NameServerRaiz extends Servidor{

    private String url, enderecoTLD;
	public NameServerRaiz(String endereco, String url) throws Exception {
	    super(endereco, "raiz");
	    this.endereco = endereco;
	    try {
			String linha = leituraArquivo(url);
			procuraResponsavelRaiz(linha, url);
		}catch(Exception e){

		}
	}

	public void procuraResponsavelRaiz(String linha, String url) throws Exception {
		String[] responsaveis = linha.split(",");
		enderecoTLD= null;

		System.out.println("Procurando no Servidor Raiz");

		String sufixo = null;

		if(url.endsWith(".com"))	sufixo = ".com";
		if(url.endsWith(".edu"))	sufixo = ".edu";
		if(url.endsWith(".org"))	sufixo = ".org";
		if(url.endsWith(".br")) 	sufixo = ".br";

		for(int i=0; i<responsaveis.length; i++){

			String[] serverResponsavel = responsaveis[i].split("=");

			serverResponsavel = formataString(serverResponsavel);

			if(serverResponsavel[1].equals(sufixo)){
				enderecoTLD = serverResponsavel[0];
				System.out.println("Encontrei o endereco TLD");
				break;
			}
		}
	}

    public String getEnderecoTLD(){
        return enderecoTLD;
    }

	private static String[] formataString(String[] ipSite){
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