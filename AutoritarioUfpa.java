import java.io.IOException;

public class AutoritarioUfpa extends NameServerDominio{
    public AutoritarioUfpa(String url, String endereco) throws IOException {
        super(url, endereco, "arquivoUfpa");
    }
}
