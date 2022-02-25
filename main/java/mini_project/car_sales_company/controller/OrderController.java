package mini_project.car_sales_company.controller;

import lombok.RequiredArgsConstructor;
import mini_project.car_sales_company.domain.Order;
import mini_project.car_sales_company.service.CarService;
import mini_project.car_sales_company.service.MemberService;
import mini_project.car_sales_company.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final CarService carService;

    /**
     * 주문
     */
    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("carId") Long carId,
                        @RequestParam("count") int count){
        orderService.order(memberId, carId, count);
        return "redirect:/orders";
    }

    /**
     * 주문 내역 조회
     */
    @GetMapping("/orders")
    public String orderList(Model model){
        List<Order> orders = orderService.findOrders();
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    /**
     * 주문 취소
     */
    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
