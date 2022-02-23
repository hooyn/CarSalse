package mini_project.car_sales_company.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderCar {

    @Id @GeneratedValue
    @Column(name = "order_car_id")
    private Long id;
    
    //LAZY로 설정해서 처음에는 프록시객체로 받아서 영속성 컨텍스트에 저장하고
    //만약 이 데이터에 접근 할 때만 DB에 Select Query를 날려서 영속석 컨택스트에 저장한다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //가격 정책이 바뀐다면 여기서 비즈니스 로직을 추가 할 수 있다.
    private int count;

    public static OrderCar createOrderCar(Car car, int price, int count) {
        OrderCar orderCar = new OrderCar();
        orderCar.setCar(car);
        orderCar.setOrderPrice(price);
        orderCar.setCount(count);

        car.removeStock(count);
        return orderCar;
    }

    public void cancel() {
        getCar().addStock(count);
    }
}
