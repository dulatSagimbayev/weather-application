package springframework.weatherapplication.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import springframework.weatherapplication.model.City;
import springframework.weatherapplication.repo.CityRepository;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List getContent(){

        return cityRepository.findAll();
    }

    public City getById(Long id){
        City city = cityRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Client with id= "+id+ " not found"));

        return city;
    }

    public List<Integer> findAllTemperatures(String name){
        return cityRepository.findAllData(name);
    }
    public List<String> findAllLabels(String name){
        return cityRepository.findAllLabels(name);
    }
}
