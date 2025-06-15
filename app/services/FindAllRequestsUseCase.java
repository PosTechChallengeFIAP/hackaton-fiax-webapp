package services;

import config.AppConfig;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import javax.inject.Singleton;
import javax.inject.Inject;

@Singleton
public class FindAllRequestsUseCase {
    private final WSClient wsClient;
    private final AppConfig appConfig;

    @Inject
    public FindAllRequestsUseCase(WSClient wsClient, AppConfig appConfig) {
        this.wsClient = wsClient;
        this.appConfig = appConfig;
    }

    public CompletionStage<String> getRequests() {
        return wsClient.url(appConfig.getVideoProcessorURL() + "/process")
                 .get()
                 .thenApply(WSResponse::getBody);
    }
}