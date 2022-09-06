package gatling.test.example.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static gatling.test.example.simulation.PerfTestConfig.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ExampleSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl(BASE_URL)
            .header("Content-Type", "text/plain");

    ScenarioBuilder scn = scenario ("Прелогин")
            .exec(http("Получаем токен")
            .post("/api/v1/test/token")
                    .queryParam("username","user")
                    .queryParam("password","pass")
            .check(status().is(200)));

    {
        setUp(scn.injectOpen(constantUsersPerSec(REQUEST_PER_SECOND).during(Duration.ofMinutes(DURATION_MIN))))
                .protocols(httpProtocol)
                .assertions(global().responseTime().percentile3().lt(P95_RESPONSE_TIME_MS),
                        global().successfulRequests().percent().gt(95.0))
        ;
    }
}