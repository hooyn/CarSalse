package mini_project.car_sales_company.domain;

import lombok.Getter;
import lombok.Setter;
import mini_project.car_sales_company.exception.StockException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Car {

    @Id @GeneratedValue
    @Column(name = "car_id")
    private Long id;

    private String brand;
    private String name;
    private int price;
    private int stockQuantity;



    /**
     * 비즈니스 로직
     * stock 증가
     */
    public void addStock(int count) {
        this.stockQuantity += count;
    }
    /**
     * 비즈니스 로직
     * stock 감소
     */
    public void removeStock(int count){
        int restStock = this.stockQuantity - count;
        if(restStock<0){
            throw new StockException("restStock is under zero");
        }
        this.stockQuantity = restStock;
    }
}
