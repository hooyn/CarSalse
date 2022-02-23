package mini_project.car_sales_company.repository;

import lombok.RequiredArgsConstructor;
import mini_project.car_sales_company.domain.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepository {

    private final EntityManager em;

    public void save(Car car){
        em.persist(car);
    }

    public Car findOne(Long id){
        Car car = em.find(Car.class, id);
        return car;
    }

    public List<Car> findAll(){
        List<Car> select_c_from_car_c = em.createQuery("select c from Car c", Car.class)
                .getResultList();
        return select_c_from_car_c;
    }


}
