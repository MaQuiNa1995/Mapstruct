package maquina1995.mapstruct.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import maquina1995.mapstruct.dto.musica.AbstractMetalDto;

@Getter
@Setter
public class BandaDto {
	private String nombre;
	private List<AbstractMetalDto> estilosMusicaCompleto;
	private List<String> estilosNombre;
	private Integer numeroIntegrantes;
}
