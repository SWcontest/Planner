package WeekRoute.planer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"WeekRoute.planer.mapper"})
@SpringBootApplication
public class PlanerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanerApplication.class, args);
	}

}
