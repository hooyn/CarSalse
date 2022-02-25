import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarForm {
    private Long id;

    private String brand;
    private String name;

    private int price;
    private int stockQuantity;
}
