package com.example.spring5webfluxdemo;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import com.example.spring5webfluxdemo.handler.validator.RequestValidatorHandler;
import com.example.spring5webfluxdemo.repository.ProductRepository;
import com.example.spring5webfluxdemo.router.ProductRouter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.boot.test.context.SpringBootTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductRouterTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void basicTest() throws Exception {
        ProductHandler productHandler = new ProductHandler(null, null);
        RouterFunction<ServerResponse> productRoute = new ProductRouter().productRoute(productHandler);
        WebTestClient.RouterFunctionSpec routerFunctionSpec = WebTestClient.bindToRouterFunction(productRoute);
        webTestClient.get().uri("/products/hello").exchange().expectStatus().is5xxServerError();
    }
}
