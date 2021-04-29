package maquina1995.mapstruct.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import maquina1995.mapstruct.domain.musica.AbstractMetal;

@Getter
@Setter
public class Banda {

	private String nombre;
	private List<AbstractMetal> tiposMusica;
	private Integer numIntegrantes;
}
