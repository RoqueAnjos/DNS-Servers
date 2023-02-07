import java.io.IOException;

public class AutoritarioAmericanas extends NameServerDominio{
    public AutoritarioAmericanas(String url, String endereco) throws IOException {
        super(url, endereco, "arquivoAmericanas");
    }
}
