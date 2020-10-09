package maquina1995.mapstruct.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BandaDto {
	private String nombre;
	private String estiloMetal;
	private Integer numeroIntegrantes;
}
