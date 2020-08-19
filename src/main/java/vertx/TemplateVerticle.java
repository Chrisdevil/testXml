package vertx;

import Beans.XmlMapping;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TemplateVerticle extends AbstractVerticle {
    Router router;
    ThymeleafTemplateEngine thymeleafTemplateEngine;
    XmlMapping xmlMapping;


    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        xmlMapping = new XmlMapping();
        router = Router.router(vertx);
        String asideString = xmlMapping.createAsideString("ssss");
        thymeleafTemplateEngine = ThymeleafTemplateEngine.create(vertx);
        router.route("/static/*").handler(StaticHandler.create());
        List<String>pageList = xmlMapping.createPageNameList();
        for(String pageRouter: pageList ){
            router.route("/main/"+pageRouter).handler(ctx->{
                var obj = new JsonObject();
                obj.put("sidePanal", asideString);
                obj.put("pagename",pageRouter);
                obj.put("name", xmlMapping.createElementString(xmlMapping.getElement(pageRouter)));
                thymeleafTemplateEngine.render(obj, "Templates/queryLogin.html", bufferAsyncResult -> {
                    ctx.response().putHeader("content-type", "text/html").end(bufferAsyncResult.result());
                });
            });
        }
        vertx.createHttpServer().requestHandler(router).listen(8890);
    }
}