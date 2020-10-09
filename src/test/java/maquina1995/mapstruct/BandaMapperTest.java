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

	private static final String NOMBRE = "Sic Zone";
	private static final int NUMERO_INTEGRANTES = 3;
	private static final String ESTILO = "Groove Metal";

	@Autowired
	private BandaMapper mut;

	@Test
	void bandaToBandaDtoTest() {

		// Given
		Banda banda = Banda.builder()
		        .nombre(NOMBRE)
		        .numIntegrantes(NUMERO_INTEGRANTES)
		        .estilo(ESTILO)
		        .build();

		// When
		BandaDto bandaDto = mut.bandaToBandaDto(banda);

		// Then
		// usamos assert para que si hay mas de 1 fallo en el mapeo nos avise de todos
		// los errores de una vez
		Assertions.assertAll(() -> Assertions.assertEquals(NOMBRE, bandaDto.getNombre()),
		        () -> Assertions.assertEquals(NUMERO_INTEGRANTES, bandaDto.getNumeroIntegrantes()),
		        () -> Assertions.assertEquals(ESTILO, bandaDto.getEstiloMetal()));

	}

	@Test
	void bandaDtoToBandaTest() {

		// Given
		BandaDto bandaDto = BandaDto.builder()
		        .nombre(NOMBRE)
		        .numeroIntegrantes(NUMERO_INTEGRANTES)
		        .estiloMetal(ESTILO)
		        .build();

		// When
		Banda banda = mut.bandaDtoToBanda(bandaDto);

		// Then
		Assertions.assertAll(() -> Assertions.assertEquals(NOMBRE, banda.getNombre()),
		        () -> Assertions.assertEquals(NUMERO_INTEGRANTES, banda.getNumIntegrantes()),
		        () -> Assertions.assertEquals(ESTILO, banda.getEstilo()));

	}
}
