package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppContext.class)
// @Import({AppContext1.class, AppContext2.class}) 와 같이 두 개의 클래스를 import 도 가능하다.
public class AppContextImport {
}
