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
        router.route("/queryChest").handler(this::ChestHandler);
        router.route("/queryLogin").handler(this::LoginHandler);
        router.route("/queryTrophies").handler(this::trophiesHandler);
        vertx.createHttpServer().requestHandler(router).listen(8890);
    }
    void LoginHandler(RoutingContext context){
        var obj = new JsonObject();
        obj.put("sidePanal",xmlMapping.createAsideString());
        obj.put("name", xmlMapping.createElementString(xmlMapping.getElement("queryLogin")));
        thymeleafTemplateEngine.render(obj, "Templates/queryLogin.html", bufferAsyncResult -> {
            context.response().putHeader("content-type", "text/html").end(bufferAsyncResult.result());
        });
    }
    void ChestHandler(RoutingContext context){
        var obj = new JsonObject();
        obj.put("sidePanal",xmlMapping.createAsideString());
        obj.put("name", xmlMapping.createElementString(xmlMapping.getElement("queryChest")));
        thymeleafTemplateEngine.render(obj, "Templates/queryChest.html", bufferAsyncResult -> {
            context.response().putHeader("content-type", "text/html").end(bufferAsyncResult.result());
        });
    }
    void trophiesHandler(RoutingContext context){
        var obj = new JsonObject();
        obj.put("sidePanal",xmlMapping.createAsideString());
        obj.put("name", xmlMapping.createElementString(xmlMapping.getElement("queryTrophies")));
        thymeleafTemplateEngine.render(obj, "Templates/queryTrophies.html", bufferAsyncResult -> {
            context.response().putHeader("content-type", "text/html").end(bufferAsyncResult.result());
        });
    }
}