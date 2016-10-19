import play.Environment;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationStart {

    @Inject
    public ApplicationStart(Environment environment, Object yourInjectedService) {

        Logger.info("Application has started");
        if (environment.isTest()) {

        }
    }


}
