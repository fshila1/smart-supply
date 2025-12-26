package fs.smartsupply.supplier.soap.provider.security;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class UsernamePasswordCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks)
            throws IOException, UnsupportedCallbackException {

        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback pc) {

                if ("supplier-user".equals(pc.getIdentifier())) {
                    pc.setPassword("secret");
                } else {
                    throw new UnsupportedCallbackException(callback, "Invalid user");
                }
            }
        }
    }
}
