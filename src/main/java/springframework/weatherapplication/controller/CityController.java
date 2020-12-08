package springframework.weatherapplication.controller;

import org.springframework.web.bind.annotation.*;
import springframework.weatherapplication.model.City;
import springframework.weatherapplication.service.CityService;

import java.util.*;

@RequestMapping("/city")
@RestController
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{name}")
    public Map<Object, Object> getInfo(@PathVariable(value = "name") String name){
        List<Integer> list = cityService.findAllTemperatures(name);
        List<String> listOfDates = cityService.findAllLabels(name);
        Map<Object,Object> map = new LinkedHashMap<>();
        map.put("xLabels",listOfDates);
        Map<Object,Object> mapOfDatasets= new LinkedHashMap<>();
        mapOfDatasets.put("label",name);
        List<Map<String,Object>> listOfData = new ArrayList<>();
        for(int i=0; i<list.size();i++){
            Map<String,Object> mapa = new HashMap<>();
            mapa.put("x",listOfDates.get(i));
            mapa.put("y",list.get(i));
            listOfData.add(mapa);
        }
        mapOfDatasets.put("data",listOfData);
        mapOfDatasets.put("backgroundColor","#08E3DE");
        List<Object> listt = new ArrayList<>();
        listt.add(mapOfDatasets);
        map.put("datasets",listt);
        return map;

    }
}
