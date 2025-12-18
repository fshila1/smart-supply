package fs.smartsupply.supplier.soap.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import fs.smartsupply.supplier.soap.provider.security.UsernamePasswordCallbackHandler;

@Configuration
public class WsSecurityConfig {

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();

        // What we expect from client
        interceptor.setValidationActions("UsernameToken");

        interceptor.setValidationCallbackHandler(new UsernamePasswordCallbackHandler());
        interceptor.setTimestampStrict(false);

        return interceptor;
    }

    // @Bean
    // public SimpleUsernamePasswordValidationCallbackHandler usernamePasswordCallbackHandler() {
    //     SimpleUsernamePasswordValidationCallbackHandler handler =
    //             new SimpleUsernamePasswordValidationCallbackHandler();

    //     handler.setUsersMap(
    //             Map.of(
    //                 "supplier-user", "secret"
    //             )
    //     );
    //     return handler;
    // }
}
