package exceptions;

public class HTMLGenerationException extends RuntimeException {
    public HTMLGenerationException(String message){
        super("The HTML files could not be generated due to an IOException: " + message);
    }
}
