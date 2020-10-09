package maquina1995.mapstruct.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Banda {

	private String nombre;
	private String estilo;
	private Integer numIntegrantes;
}
