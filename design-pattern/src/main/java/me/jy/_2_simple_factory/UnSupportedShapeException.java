package me.jy._2_simple_factory;

/**
 * @author jy
 */
public class UnSupportedShapeException extends RuntimeException {

    private static final long serialVersionUID = 5979803456260725402L;

    public UnSupportedShapeException(String message) {
        super(message);
    }
}
