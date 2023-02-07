import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.net.InetAddress;

public class Servidor{

	protected String endereco, nomeArquivo;

	public Servidor(String endereco, String nomeArquivo) throws IOException {
		this.endereco = endereco;
		this.nomeArquivo = nomeArquivo;
	}

	protected String leituraArquivo(String url) throws Exception {
		String linha=null;

		try {
			FileReader fileReader = new FileReader(nomeArquivo+".txt");
			BufferedReader leitor = new BufferedReader(fileReader);

			while (leitor.ready()) {
				linha = leitor.readLine();

				if (!linha.contains("="))
					break;
			}

			fileReader.close();
			leitor.close();

		} catch (IOException e) {}

		return linha;
	}

	public String getEndereco(){
		return endereco;
	}

}