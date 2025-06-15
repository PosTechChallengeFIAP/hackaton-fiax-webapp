package controllers;

import java.util.concurrent.CompletionStage;

import play.mvc.*;
import javax.inject.Inject;
import services.FindAllRequestsUseCase;

public class ListRequestsController extends Controller {
    private final FindAllRequestsUseCase findAllRequestsUseCase;

    @Inject
    public ListRequestsController(FindAllRequestsUseCase findAllRequestsUseCase) {
        this.findAllRequestsUseCase = findAllRequestsUseCase;
    }

    public CompletionStage<Result> listRequests() {
        return findAllRequestsUseCase.getRequests()
                .thenApply(json -> ok(json).as("application/json"));
    }
}
