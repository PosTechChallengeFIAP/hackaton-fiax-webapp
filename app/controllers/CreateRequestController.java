package controllers;

import config.AppConfig;
import play.libs.Files.TemporaryFile;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import services.CreateNewRequestUseCase;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CreateRequestController extends Controller{
    private final CreateNewRequestUseCase createNewRequestUseCase;
    private final AppConfig appConfig;

    @Inject
    public CreateRequestController(CreateNewRequestUseCase createNewRequestUseCase, AppConfig appConfig) {
        this.createNewRequestUseCase = createNewRequestUseCase;
        this.appConfig = appConfig;
    }

    public CompletionStage<Result> upload(Http.Request request) throws IOException{
        MultipartFormData<TemporaryFile> formData = request.body().asMultipartFormData();
        MultipartFormData.FilePart<TemporaryFile> filePart = formData.getFile("video");

        if (filePart != null) {
            TemporaryFile tempFile = filePart.getRef();
            String fileName = filePart.getFilename();
            File file = new File(String.format("%s/%s", appConfig.getUploadLocation(), fileName));
            tempFile.moveTo(file);

            return createNewRequestUseCase.forwardFileToBackend(file)
                .thenApply(response -> {
                    try {
                        return ok("Upload successful: " + response.body());
                    }catch (NullPointerException e){
                        return ok("Upload successful!");
                    }
                });
        } else {
            return CompletableFuture.supplyAsync(() -> {
                return badRequest("Missing file");
            });
        }

    }
}
