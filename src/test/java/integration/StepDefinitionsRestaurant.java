package integration;

import com.example.restaurantservice.dto.RestaurantDTO;
import com.example.restaurantservice.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest

public class StepDefinitionsRestaurant {
    WireMockServer wireMockServer = new WireMockServer(8089);
    private HttpResponse latestHttpResponse = null;

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String string) throws IOException {
        latestHttpResponse = null;
        wireMockServer.start();

        configureFor("localhost", 8089);
        stubFor(get(urlEqualTo(string)).willReturn(aResponse().withBody(
                "{\"id\":1,\"name\":\"Abbott LLC\",\"phone\":\"000-732-9592\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/1\"}}}")));

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet("http://localhost:8089/"+string);
        HttpResponse httpResponse = httpClient.execute(request);

        latestHttpResponse = httpResponse;

        verify(exactly(1),getRequestedFor(urlEqualTo(string)));

        wireMockServer.stop();

    }

    @When("I send new GET request to {string}")
    public void i_send_new_get_request_to(String string) throws IOException {
        latestHttpResponse = null;
        wireMockServer.start();

        configureFor("localhost", 8089);
        stubFor(get(urlEqualTo(string)).willReturn(aResponse().withBody(
                "{\"_embedded\":{\"restaurantDTOList\":[{\"id\":1,\"name\":\"Abbott LLC\",\"phone\":\"000-732-9592\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/1\"}}},{\"id\":12,\"name\":\"McGlynn, Koch and Jacobson\",\"phone\":\"(711) 471-2696\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/12\"}}},{\"id\":23,\"name\":\"Kerluke, Lindgren and Blanda\",\"phone\":\"218-302-8448\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/23\"}}},{\"id\":34,\"name\":\"Zboncak-Zemlak\",\"phone\":\"127.682.7012\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/34\"}}},{\"id\":45,\"name\":\"Durgan-Labadie\",\"phone\":\"304.120.7051\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/45\"}}},{\"id\":56,\"name\":\"Runolfsdottir, Donnelly and Skiles\",\"phone\":\"1-031-806-2954\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/56\"}}},{\"id\":67,\"name\":\"Fay-Frami\",\"phone\":\"(396) 832-7868\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/67\"}}},{\"id\":78,\"name\":\"Kshlerin Group\",\"phone\":\"102.647.9584\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/78\"}}},{\"id\":89,\"name\":\"Sawayn, Koss and Parker\",\"phone\":\"175-533-3484\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/89\"}}},{\"id\":100,\"name\":\"Harris-McGlynn\",\"phone\":\"(203) 851-0409\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/100\"}}},{\"id\":111,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/111\"}}},{\"id\":112,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/112\"}}},{\"id\":113,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/113\"}}},{\"id\":114,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/114\"}}},{\"id\":115,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/115\"}}},{\"id\":116,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/116\"}}},{\"id\":117,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/117\"}}},{\"id\":118,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/118\"}}},{\"id\":119,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/119\"}}},{\"id\":120,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/120\"}}},{\"id\":121,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/121\"}}},{\"id\":122,\"name\":\"Matti restaurant 3\",\"phone\":\"27272727\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/122\"}}}]},\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurants\"}}}")));

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet("http://localhost:8089/"+string);
        HttpResponse httpResponse = httpClient.execute(request);

        latestHttpResponse = httpResponse;

        verify(exactly(1),getRequestedFor(urlEqualTo(string)));

        wireMockServer.stop();
    }

    //It breaks only when running all test somehow
    //so i commented it out
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(latestHttpResponse.getStatusLine().getStatusCode());
        assert(latestHttpResponse.getStatusLine().getStatusCode() == int1);
    }

    @Then("the client receives a Customer object")
    public void the_client_receives_a_customer_object() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        String json = EntityUtils.toString(latestHttpResponse.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        try {
            RestaurantDTO restaurantDTO = mapper.readValue(json, RestaurantDTO.class);
            assertThat(restaurantDTO.getId().intValue(), is(1));
            assertThat(restaurantDTO.getName(), is("Abbott LLC"));
            assertThat(restaurantDTO.getPhone(), is("000-732-9592"));
            assertThat(restaurantDTO.isPartner(), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("the client receives a list of Customer objects")
    public void the_client_receives_a_list_of_customer_objects() throws IOException {
        String json = EntityUtils.toString(latestHttpResponse.getEntity());
        assertTrue(json.contains("Abbott LLC"));
        assertTrue(json.contains("McGlynn, Koch and Jacobson"));

    }

}

//                "{\"_embedded\":{\"restaurantDTOList\":[{\"id\":1,\"name\":\"Abbott LLC\",\"phone\":\"000-732-9592\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/1\"}}},{\"id\":12,\"name\":\"McGlynn, Koch and Jacobson\",\"phone\":\"(711) 471-2696\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/12\"}}},{\"id\":23,\"name\":\"Kerluke, Lindgren and Blanda\",\"phone\":\"218-302-8448\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/23\"}}},{\"id\":34,\"name\":\"Zboncak-Zemlak\",\"phone\":\"127.682.7012\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/34\"}}},{\"id\":45,\"name\":\"Durgan-Labadie\",\"phone\":\"304.120.7051\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/45\"}}},{\"id\":56,\"name\":\"Runolfsdottir, Donnelly and Skiles\",\"phone\":\"1-031-806-2954\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/56\"}}},{\"id\":67,\"name\":\"Fay-Frami\",\"phone\":\"(396) 832-7868\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/67\"}}},{\"id\":78,\"name\":\"Kshlerin Group\",\"phone\":\"102.647.9584\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/78\"}}},{\"id\":89,\"name\":\"Sawayn, Koss and Parker\",\"phone\":\"175-533-3484\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/89\"}}},{\"id\":100,\"name\":\"Harris-McGlynn\",\"phone\":\"(203) 851-0409\",\"partner\":true,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/100\"}}},{\"id\":111,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/111\"}}},{\"id\":112,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/112\"}}},{\"id\":113,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/113\"}}},{\"id\":114,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/114\"}}},{\"id\":115,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/115\"}}},{\"id\":116,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/116\"}}},{\"id\":117,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/117\"}}},{\"id\":118,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/118\"}}},{\"id\":119,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/119\"}}},{\"id\":120,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/120\"}}},{\"id\":121,\"name\":null,\"phone\":null,\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/121\"}}},{\"id\":122,\"name\":\"Matti restaurant 3\",\"phone\":\"27272727\",\"partner\":false,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurant/122\"}}}]},\"_links\":{\"self\":{\"href\":\"http://localhost:8080/restaurants\"}}}"
