package vertx;

import Beans.XmlMapping;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;
import org.jdom2.JDOMException;

import java.io.IOException;

public class TemplateVerticle extends AbstractVerticle {
    Router router;
    ThymeleafTemplateEngine thymeleafTemplateEngine;
    XmlMapping xmlMapping;
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        xmlMapping= new XmlMapping();
        router = Router.router(vertx);
        thymeleafTemplateEngine = ThymeleafTemplateEngine.create(vertx);
        router.route("/exe").handler(req -> {
            var obj = new JsonObject();
            String string = xmlMapping.createElementString(xmlMapping.getElement("queryChest"));
            System.out.println(string);
            obj.put("name", string);
            obj.put("another", "<form >userId:<input type=text><input type=submit></form>");
            thymeleafTemplateEngine.render(obj,
                    "Templates/index.html",
                    bufferAsyncResult -> {
                        req.response()
                                .putHeader("content-type", "text/html")
                                .end(bufferAsyncResult.result());
                    });

        });

        vertx.createHttpServer().requestHandler(router).listen(8890, http -> {
            if (http.succeeded()) {
                startPromise.complete();
                System.out.println("HTTP server started on port 8888");
            } else {
                startPromise.fail(http.cause());
            }
        });
    }
}