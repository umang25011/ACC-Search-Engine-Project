package GUI;

import io.javalin.http.staticfiles.Location;
import io.javalin.Javalin;

import SearchEngine.SearchEngine;

public class index {
  public static void main(String[] args) {
    Javalin app = Javalin.create(config -> {
      config.addStaticFiles("/", Location.CLASSPATH);
    }).start(7003);

	  final String RegularExprWord = "[[ ]*|[,]*|[)]*|[(]*|[\"]*|[;]*|[-]*|[:]*|[']*|[�]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+";
    SearchEngine searchEngine = new SearchEngine();
    // Run the Javalin Instance on the port 7003
    app.get("/main", ctx -> {
      ctx.render("/GUI/index.html");
    });
    // Run the Javalin Instance to build an API for Search
    app.post("/search", ctx -> {
      // ctx.result(Main.search(ctx.formParam("Search_Query")));
      System.out.println(ctx.formParam("Search_Query"));
      searchEngine.search(ctx.formParam("Search_Query").split(RegularExprWord));
    });
  }
}
