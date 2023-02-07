import java.io.IOException;

public class AutoritarioPanama extends NameServerDominio{
    public AutoritarioPanama(String url, String endereco) throws IOException {
        super(url, endereco, "arquivoPanama");
    }
}
