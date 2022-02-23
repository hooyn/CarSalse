package mini_project.car_sales_company.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //cascade옵션을 주어서 orderCars에 변화가 있다면 반영을 해준다.
    //ALL옵션을 주어 만약 Order에 orderCars를 추가한다면 orderCars들을 DB에 저장시킨다.
    //ALL옵션을 주어 만약 Order가 삭제된다면 그에 해당하는 OrderCars들도 삭제한다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderCar> orderCars = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * 생성 메서드
     */
    public static Order createOrder(Member member, OrderCar... orderCars) {
        Order order = new Order();
        order.setMember(member);
        for (OrderCar orderCar : orderCars) {
            order.addOrderCar(orderCar);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    /**
     * 연관관계 편의 메서드
     * setMember
     */
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    /**
     * 연관관계 편의 메서드
     * addOrderCar
     */
    public void addOrderCar(OrderCar orderCar){
        orderCars.add(orderCar);
        orderCar.setOrder(this);
    }

    /**
     * 비즈니스 로직
     * 주문 취소
     */
    public void cancel(){
        this.setStatus(OrderStatus.CANCEL);
        for (OrderCar orderCar : orderCars) {
            orderCar.cancel();
        }
    }
}
