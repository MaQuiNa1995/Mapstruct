package maquina1995.mapstruct;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import maquina1995.mapstruct.configuration.SpringConfig;
import maquina1995.mapstruct.domain.Banda;
import maquina1995.mapstruct.domain.musica.AbstractMetal;
import maquina1995.mapstruct.domain.musica.Black;
import maquina1995.mapstruct.domain.musica.Death;
import maquina1995.mapstruct.dto.BandaDto;
import maquina1995.mapstruct.dto.musica.AbstractMetalDto;
import maquina1995.mapstruct.dto.musica.BlackDto;
import maquina1995.mapstruct.dto.musica.DeathDto;
import maquina1995.mapstruct.mapper.BandaMapper;
import maquina1995.mapstruct.mapper.BlackMapper;
import maquina1995.mapstruct.mapper.DeathMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class })
class BandaMapperTest {

	private static final String NOMBRE = "Unleashed";
	private static final int NUMERO_INTEGRANTES = 3;
	private List<AbstractMetal> estilosMusica;
	private List<AbstractMetalDto> estilosMusicaDto;
	private List<String> estilosMusicaString = Arrays.asList("Death", "Black");

	/**
	 * mapper a probar (Mapper Under Test)
	 */
	@Autowired
	private BandaMapper mut;
	/**
	 * Los inyecto por vagancia de crear los dto en el constructor
	 */
	@Autowired
	private DeathMapper deathMapper;
	/**
	 * Los inyecto por vagancia de crear los dto en el constructor
	 */
	@Autowired
	private BlackMapper blackMapper;

	@BeforeEach
	public void setUp() {
		Death death = new Death();
		death.setBpmMedio(180);
		death.setMelodico(Boolean.TRUE);

		Black black = new Black();
		black.setBpmMedio(150);
		black.setTematicaSatanica(Boolean.FALSE);

		this.estilosMusica = Arrays.asList(death, black);

		DeathDto deathDto = deathMapper.entityToDto(death);
		BlackDto blackDto = blackMapper.entityToDto(black);
		this.estilosMusicaDto = Arrays.asList(deathDto, blackDto);
	}

	@Test
	void bandaToBandaDtoTest() {

		// Given
		Banda banda = new Banda();
		banda.setNombre(NOMBRE);
		banda.setNumIntegrantes(NUMERO_INTEGRANTES);
		banda.setTiposMusica(estilosMusica);

		// When
		BandaDto bandaDto = mut.entityToDto(banda);

		// Then
		Assertions.assertEquals(NOMBRE, bandaDto.getNombre());
		Assertions.assertEquals(NUMERO_INTEGRANTES, bandaDto.getNumeroIntegrantes());
		Assertions.assertEquals(estilosMusicaString, bandaDto.getEstilosNombre());
		Assertions.assertEquals(estilosMusicaDto, bandaDto.getEstilosMusicaCompleto());
	}

	@Test
	void bandaDtoToBandaTest() {

		// Given
		BandaDto bandaDto = new BandaDto();
		bandaDto.setNombre(NOMBRE);
		bandaDto.setNumeroIntegrantes(NUMERO_INTEGRANTES);
		bandaDto.setEstilosNombre(estilosMusicaString);
		bandaDto.setEstilosMusicaCompleto(null);

		// When
		Banda banda = mut.dtoToEntity(bandaDto);

		// Then
		Assertions.assertEquals(NOMBRE, banda.getNombre());
		Assertions.assertEquals(NUMERO_INTEGRANTES, banda.getNumIntegrantes());
		Assertions.assertEquals(estilosMusica, banda.getTiposMusica());
		Assertions.assertEquals(estilosMusicaString, bandaDto.getEstilosNombre());

	}
}
