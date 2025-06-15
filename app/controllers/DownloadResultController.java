package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.DownloadResultUseCase;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class DownloadResultController extends Controller {
    private final DownloadResultUseCase downloadResultUseCase;

    @Inject
    public DownloadResultController(DownloadResultUseCase downloadResultUseCase) {
        this.downloadResultUseCase = downloadResultUseCase;
    }

    public CompletionStage<Result> download(String id) {
        return downloadResultUseCase.execute(id)
                .thenApply(file -> ok(file.asByteArray()).as("application/octet-stream"));
    }
}
