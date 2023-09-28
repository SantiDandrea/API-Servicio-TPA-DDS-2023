package handlers;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.sugerenciasFusion.RepoSugerencias;
import domain.sugerenciasFusion.Sugerencia;
import handlers.dto.ComunidadDTO;
import handlers.dto.MiembroDTO;
import handlers.dto.SugerenciaDTO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class GetSugerenciasHandler implements Handler {
  @OpenApi(
      summary = "Obtener todas las sugerencias",
      operationId = "getSugerencias",
      path = "/api/sugerencias",
      methods = HttpMethod.GET,
      tags = {"Sugerencia"},
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = SugerenciaDTO[].class)})
      }
  )
  @Override
  public void handle(@NotNull Context context) throws Exception {
    List<SugerenciaDTO> sugerenciasDTO = RepoSugerencias.getInstance().getSugerencias().stream()
        .map(this::mapSugerenciaToDTO)
        .collect(Collectors.toList());
    context.json(sugerenciasDTO);
  }

  private SugerenciaDTO mapSugerenciaToDTO(Sugerencia sugerencia) {
    ComunidadDTO comunidad1 = mapComunidadToDTO(sugerencia.getComunidad1());
    ComunidadDTO comunidad2 = mapComunidadToDTO(sugerencia.getComunidad2());
    SugerenciaDTO sugerenciaDTO = new SugerenciaDTO(sugerencia.getId(), comunidad1,
        comunidad2, sugerencia.getFecha());
    return sugerenciaDTO;
  }

  private ComunidadDTO mapComunidadToDTO(Comunidad comunidad) {
    ComunidadDTO comunidadDTO = new ComunidadDTO();
    comunidadDTO.setId(comunidad.getId());
    comunidadDTO.setNombre(comunidad.getNombre());
    List<MiembroDTO> afectadosDTO = comunidad.getAfectados().stream().map(this::mapMiembroToDTO).toList();
    List<MiembroDTO> observadoresDTO = comunidad.getObservadores().stream().map(this::mapMiembroToDTO).toList();
    comunidadDTO.setAfectados(afectadosDTO);
    comunidadDTO.setObservadores(observadoresDTO);
    return comunidadDTO;
  }

  private MiembroDTO mapMiembroToDTO(Miembro miembro){
     return new MiembroDTO(miembro.getId(), miembro.getNombre(), miembro.getApellido(), miembro.getCorreoElectronico());
  }

}
