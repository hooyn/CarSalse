package mini_project.car_sales_company.service;

import lombok.RequiredArgsConstructor;
import mini_project.car_sales_company.domain.Car;
import mini_project.car_sales_company.domain.Member;
import mini_project.car_sales_company.domain.Order;
import mini_project.car_sales_company.domain.OrderCar;
import mini_project.car_sales_company.repository.CarRepository;
import mini_project.car_sales_company.repository.MemberRepository;
import mini_project.car_sales_company.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final CarRepository carRepository;

    /**
     * 주문
     */
    public Long order(Long memberId, Long carId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Car car = carRepository.findOne(carId);

        //주문상품 생성
        OrderCar orderCar = OrderCar.createOrderCar(car, car.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, orderCar);

        //주문 저장
        orderRepository.save(order);
        // -> order만 저장 해줘도 cascade.ALL옵션 때문에 orderCars를 다 persist해준다.

        return order.getId();
    }

    /**
     * 취소
     */
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);

        order.cancel();

    }
}
