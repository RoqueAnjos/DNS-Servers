import java.io.IOException;

public class AutoritarioGoogle extends NameServerDominio{

    public AutoritarioGoogle(String url, String endereco) throws IOException {
        super(url, endereco, "arquivoGoogle");
    }
}
