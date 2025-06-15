package config;

import com.typesafe.config.Config;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppConfig {

    private final String videoProcessorURL;
    private final String uploadLocation;

    @Inject
    public AppConfig(Config config) {
        this.videoProcessorURL = config.getString("services.videoProcessor.url");
        this.uploadLocation = config.getString("app.uploadLocation");
    }

    public String getVideoProcessorURL() {
        return videoProcessorURL;
    }

    public String getUploadLocation() {
        return uploadLocation;
    }
}