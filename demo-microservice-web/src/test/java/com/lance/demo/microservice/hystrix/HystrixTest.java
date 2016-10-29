package com.lance.demo.microservice.hystrix;

import com.lance.demo.microservice.config.hystrix.CommandHelloWorld;
import com.lance.demo.microservice.config.hystrix.ErrorPropagationCommand;
import com.lance.demo.microservice.config.hystrix.FallbackCommand;
import com.lance.demo.microservice.config.hystrix.ObservableCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by perdonare on 2016/10/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HystrixTest {
    @Test
    public void testHelloWorld() {
        String s = new CommandHelloWorld("Bob").execute();
        System.out.println(s);
    }

    @Test
    public void testAsynchronous() throws Exception {
        String s = new CommandHelloWorld("Bob").queue().get();
        System.out.println(s);
    }

    @Test
    public void testObservable() {
        Observable<String> ho = new ObservableCommand("Bob").observe();
        ho.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void testFallback() {
        String word = new FallbackCommand("Bob").execute();
        System.out.println(word);
    }

    @Test
    public void testErrorPropagation() {
        ErrorPropagationCommand command = new ErrorPropagationCommand("Bob");
        command.execute();
        String errorMsg = command.getExecutionException().getMessage();
        System.out.println(errorMsg);
    }
}
