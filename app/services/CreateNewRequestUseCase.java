package services;

import config.AppConfig;
import models.MultipartFormUtil;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.pekko.util.ByteString;
import play.libs.ws.BodyWritable;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.WSClient;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;
import static play.mvc.Results.status;

@Singleton
public class CreateNewRequestUseCase implements WSBodyWritables{
    private final WSClient wsClient;
    private final AppConfig appConfig;

    @Inject
    public CreateNewRequestUseCase(WSClient wsClient, AppConfig appConfig) {
        this.wsClient = wsClient;
        this.appConfig = appConfig;
    }

    public CompletionStage<Result> forwardFileToBackend(File file) throws IOException {

        HttpEntity entity = MultipartFormUtil.buildMultipartBody(file, "file");
        byte[] multipartBytes = MultipartFormUtil.getBytes(entity);

        BodyWritable<ByteString> wsBody = this.body(multipartBytes, MultipartFormUtil.getContentType(entity));


        return wsClient.url(appConfig.getVideoProcessorURL() + "/process")
                .addHeader("Content-Type", MultipartFormUtil.getContentType(entity))
                .post(wsBody)
                .thenApply(response -> {
                    if (response.getStatus() == 200) {
                        // Optionally save zip or stream it to client
                        return ok("Video processed successfully.");
                    } else {
                        return status(response.getStatus(), "Backend error: " + response.getBody());
                    }
                });
    }


}
