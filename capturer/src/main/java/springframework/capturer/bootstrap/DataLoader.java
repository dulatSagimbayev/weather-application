package springframework.capturer.bootstrap;

import springframework.capturer.model.Cities;
import springframework.capturer.model.City;
import springframework.capturer.repo.CityRepo;
import springframework.capturer.response.OpenWeatherResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class DataLoader implements CommandLineRunner {
    private final CityRepo cityRepo;

    public DataLoader(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        initData();
    }

    private void initData() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for(Cities name :  Cities.values()) {
                    RestTemplate restTemplate = new RestTemplate();
                    final String url = "https://api.openweathermap.org/data/2.5/weather?q=" + name + "&APPID=8c77dcb05a496745763406ad87d0f1e1&units=metric";
                    ResponseEntity<OpenWeatherResponse> response = restTemplate
                            .getForEntity(url, OpenWeatherResponse.class);

                    final String temp = response.getBody().getMain().getTemp();
                    final String pressure = response.getBody().getMain().getPressure();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                    final String date = formatter.format(LocalDateTime.now());
                    Random rand = new Random();
                    City city = new City();
                    city.setDate(date);
                    city.setName(name.toString());
                    city.setTemperature(temp);
                    city.setPressure(pressure);

                    cityRepo.save(city);
                }
            }

        };
        Timer timer = new Timer ("Timer");
        long delay = 100L;
        long period = 60*60*100L*24;
        timer.scheduleAtFixedRate(task, delay, period);


    }
}
