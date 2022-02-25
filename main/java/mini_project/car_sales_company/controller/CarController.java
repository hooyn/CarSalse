package mini_project.car_sales_company.controller;

import lombok.RequiredArgsConstructor;
import mini_project.car_sales_company.domain.Car;
import mini_project.car_sales_company.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    /**
     * 자동차 등록
     */
    @PostMapping("/cars/new")
    public String create(@RequestParam("brand") String brand,
                         @RequestParam("name") String name,
                         @RequestParam("price") int price,
                         @RequestParam("stockQuantity") int stockQuantity){

        Car car = new Car();
        car.setBrand(brand);
        car.setName(name);
        car.setPrice(price);
        car.setStockQuantity(stockQuantity);

        carService.saveCar(car);
        return "redirect:/";
    }

    /**
     * 자동차 내역 조회
     */
    @GetMapping("/cars")
    public String list(Model model){
        List<Car> cars = carService.findCars();
        model.addAttribute("cars", cars);
        return "cars/carList";
    }

    /**
     * 자동차 정보 수정
     */
    @PostMapping("cars/{carId}/edit")
    public String updateItem(@ModelAttribute("form") CarForm form){

        carService.updateCar(form.getId(), form.getBrand(), form.getName(), form.getPrice(), form.getStockQuantity());
        //변경감지를 이용한 업데이트

        return "redirect:/cars";
    }
}
