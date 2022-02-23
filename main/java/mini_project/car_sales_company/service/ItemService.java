package mini_project.car_sales_company.service;

import lombok.RequiredArgsConstructor;
import mini_project.car_sales_company.domain.Car;
import mini_project.car_sales_company.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final CarRepository carRepository;

    @Transactional
    public void saveCar(Car car){
        carRepository.save(car);
    }

    @Transactional
    public void updateCar(Long id, String brand, String name, int price, int stockQuantity){
        Car car = carRepository.findOne(id);
        car.setBrand(brand);
        car.setName(name);
        car.setPrice(price);
        car.setStockQuantity(stockQuantity);
        //변경감지를 이용한 업데이트
        //merger를 사용하지 않고 set을 이용해서 업데이트한다.
    }

    public List<Car> findCars(){
        return carRepository.findAll();
    }

    public Car findOnd(Long carId){
        return carRepository.findOne(carId);
    }
}
