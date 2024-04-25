package exceptions;

public class ClienteNaoExisteException extends Exception{
    public ClienteNaoExisteException(String msg){
        super(msg);
    }

}
