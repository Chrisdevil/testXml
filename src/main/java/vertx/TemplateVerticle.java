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
    XmlMapping xmlMapping = new XmlMapping();
    Router router;
    ThymeleafTemplateEngine thymeleafTemplateEngine;

    public TemplateVerticle() throws JDOMException, IOException {
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        router = Router.router(vertx);
        thymeleafTemplateEngine = ThymeleafTemplateEngine.create(vertx);
        router.route("/").handler(req -> {
            var obj = new JsonObject();
            obj.put("another","ccc");
            obj.put("name", xmlMapping.testSendMessage());
            thymeleafTemplateEngine.render(obj,
                    "Templates/index.html",
                    bufferAsyncResult -> {
                        if (bufferAsyncResult.succeeded()) {
                            req.response()
                                    .putHeader("content-type", "text/html")
                                    .end(bufferAsyncResult.result());
                        }
                    });

        });

        vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
            if (http.succeeded()) {
                startPromise.complete();
                System.out.println("HTTP server started on port 8888");
            } else {
                startPromise.fail(http.cause());
            }
        });
    }
}
