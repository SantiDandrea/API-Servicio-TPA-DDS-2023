package handlers;

import domain.sugerenciasFusion.RepoSugerencias;
import domain.sugerenciasFusion.Sugerencia;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;
import org.jetbrains.annotations.NotNull;

public class GetSugerenciasHandler implements Handler {
  @OpenApi(
      summary = "Obtener todas las sugerencias",
      operationId = "getSugerencias",
      path = "/api/sugerencias",
      methods = HttpMethod.GET,
      tags = {"Sugerencia"},
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Sugerencia[].class)})
      }
  )
  @Override
  public void handle(@NotNull Context context) throws Exception {
    context.json(RepoSugerencias.getInstance().getSugerencias());
  }
}
