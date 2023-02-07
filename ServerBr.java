public class ServerBr extends ServidorTLD{

    public ServerBr(String endereco, String url) throws Exception {
        super(endereco, url, "enderecosBr");
    }

    @Override
    public String getEnderecoNameServerDomain() {
        return responsavel;
    }
}