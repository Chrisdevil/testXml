package vertx;

import Beans.XmlMapping;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;
import org.jdom2.JDOMException;

import javax.json.Json;
import javax.json.JsonValue;
import java.io.IOException;
import java.util.Map;

public class MainVertx extends AbstractVerticle {
    XmlMapping xmlMapping ;
    Router router = Router.router(vertx);
    ThymeleafTemplateEngine thymeleafTemplateEngine = ThymeleafTemplateEngine.create(vertx);



    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        xmlMapping = new XmlMapping();
        router.get("/exe/queryLogin.").handler(this::sendMessage);
        vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
            if (http.succeeded()) {
                startPromise.complete();
                System.out.println("HTTP server started on port 8888");
            } else {
                startPromise.fail(http.cause());
            }
        });

    }

    void sendMessage(RoutingContext ctx) {
        var obj = new JsonObject();
        obj.put("htmlString", "qqqqq");
        thymeleafTemplateEngine.render(obj, "Template/index.html", bufferAsyncResult -> {
            ctx.response().putHeader("content-type", "text/html").end("where is jekell");
        });
    }

}
