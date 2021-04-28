package maquina1995.mapstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import maquina1995.mapstruct.configuration.SpringConfig;
import maquina1995.mapstruct.domain.Banda;
import maquina1995.mapstruct.domain.BandaDto;
import maquina1995.mapstruct.mapper.BandaMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class })
class BandaMapperTest {

	private static final String NOMBRE = "Unleashed";
	private static final int NUMERO_INTEGRANTES = 3;
	private static final String ESTILO = "Death";

	@Autowired
	private BandaMapper mut;

	@Test
	void bandaToBandaDtoTest() {

		// Given
		Banda banda = new Banda();
		banda.setNombre(NOMBRE);
		banda.setNumIntegrantes(NUMERO_INTEGRANTES);
		banda.setEstilo(ESTILO);

		// When
		BandaDto bandaDto = mut.bandaToBandaDto(banda);

		// Then
		// usamos assert para que si hay mas de 1 fallo en el mapeo nos avise de todos
		// los errores de una vez
		Assertions.assertAll(() -> Assertions.assertEquals(NOMBRE, bandaDto.getNombre()),
		        () -> Assertions.assertEquals(NUMERO_INTEGRANTES, bandaDto.getNumeroIntegrantes()),
		        () -> Assertions.assertEquals(ESTILO + " Metal", bandaDto.getEstiloMetal()));

	}

	@Test
	void bandaDtoToBandaTest() {

		// Given
		BandaDto bandaDto = new BandaDto();
		bandaDto.setNombre(NOMBRE);
		bandaDto.setNumeroIntegrantes(NUMERO_INTEGRANTES);
		bandaDto.setEstiloMetal(ESTILO);

		// When
		Banda banda = mut.bandaDtoToBanda(bandaDto);

		// Then
		Assertions.assertAll(() -> Assertions.assertEquals(NOMBRE, banda.getNombre()),
		        () -> Assertions.assertEquals(NUMERO_INTEGRANTES, banda.getNumIntegrantes()),
		        () -> Assertions.assertEquals(ESTILO, banda.getEstilo()));

	}
}
