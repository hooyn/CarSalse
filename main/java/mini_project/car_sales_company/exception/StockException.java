package mini_project.car_sales_company.exception;

public class StockException extends RuntimeException{
    public StockException() {
    }

    public StockException(String message) {
        super(message);
    }

    public StockException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockException(Throwable cause) {
        super(cause);
    }
}
