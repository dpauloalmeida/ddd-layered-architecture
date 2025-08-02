import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [com.example.WebApplication::class])
class WebApplicationTest {

    @Test
    fun contextLoads() {
        // The test will pass if the application context loads successfully
    }
}