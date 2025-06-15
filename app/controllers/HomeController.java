package controllers;

import java.util.List;
import java.util.concurrent.CompletionStage;

import models.Request;

import play.mvc.*;
import services.FindAllRequestsUseCase;
import play.libs.ws.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }

}
