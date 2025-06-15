package services;

import config.AppConfig;
import play.api.mvc.Result;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

@Singleton
public class DownloadResultUseCase {
    private final WSClient wsClient;
    private final AppConfig appConfig;

    @Inject
    public DownloadResultUseCase(WSClient wsClient, AppConfig appConfig) {
        this.wsClient = wsClient;
        this.appConfig = appConfig;
    }

    public CompletionStage<WSResponse> execute(String id) {
        return wsClient.url(String.format(appConfig.getVideoProcessorURL() + "/process/%s/download", id))
                .get();
    }
}
