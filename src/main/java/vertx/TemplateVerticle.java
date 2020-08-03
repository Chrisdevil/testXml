package vertx;

import Beans.XmlMapping;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
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
        //router.route("/static/*").handler(StaticHandler.create());
        router.route("/queryChest").handler(req -> {
            var obj = new JsonObject();
            String string = xmlMapping.createElementString(xmlMapping.getElement("queryChest"));
            //System.out.println(string);
            obj.put("sidePanal",xmlMapping.createAsideString());
            obj.put("name", string);
            thymeleafTemplateEngine.render(obj,
                    "Templates/queryChest.html",
                    bufferAsyncResult -> {
                        req.response()
                                .putHeader("content-type", "text/html")
                                .end(bufferAsyncResult.result());
                    });

        });
        router.route("/queryLogin").handler(req -> {
            var obj = new JsonObject();
            String string = xmlMapping.createElementString(xmlMapping.getElement("queryLogin"));
            //System.out.println(string);
            obj.put("sidePanal",xmlMapping.createAsideString());
            obj.put("name", string);
            thymeleafTemplateEngine.render(obj,
                    "Templates/queryChest.html",
                    bufferAsyncResult -> {
                        req.response()
                                .putHeader("content-type", "text/html")
                                .end(bufferAsyncResult.result());
                    });

        });
        router.route("/queryTrophies").handler(this::handler);
        vertx.createHttpServer().requestHandler(router).listen(8890);
    }
    void handler(RoutingContext context){
        var obj = new JsonObject();
        String string = xmlMapping.createElementString(xmlMapping.getElement("queryTrophies"));
        //System.out.println(string);
        obj.put("sidePanal",xmlMapping.createAsideString());
        obj.put("name", string);
        thymeleafTemplateEngine.render(obj, "Templates/queryChest.html", bufferAsyncResult -> {
            context.response().putHeader("content-type", "text/html").end(bufferAsyncResult.result());
        });
    }
}