package maquina1995.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.BeforeMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;

import maquina1995.mapstruct.domain.Banda;
import maquina1995.mapstruct.domain.musica.AbstractMetal;
import maquina1995.mapstruct.domain.musica.Black;
import maquina1995.mapstruct.domain.musica.Death;
import maquina1995.mapstruct.dto.BandaDto;
import maquina1995.mapstruct.dto.musica.AbstractMetalDto;
import maquina1995.mapstruct.dto.musica.BlackDto;
import maquina1995.mapstruct.dto.musica.DeathDto;

@Mapper(uses = { BlackMapper.class, DeathMapper.class })
public interface BandaMapper extends GenericMapper<Banda, BandaDto> {

	/**
	 * Método usado para mapear una {@link Banda} a un {@link BandaDto}
	 * <p>
	 * source hace referencia a (objeto pasado como parámetro . campo ) target hace
	 * referencia al campo del objeto que se devuelve
	 * <p>
	 * esto es necesario si hay discrepancias de nombres entre el Dto y el objeto o
	 * viceversa
	 * 
	 * @param banda {@link Banda} a ser transformada
	 * @return {@link BandaDto} transformado
	 */
	@Override
	@Mapping(source = "banda.numIntegrantes",
	        target = "numeroIntegrantes")
	@Mapping(source = "banda.tiposMusica",
	        target = "estilosMusicaCompleto")
	BandaDto entityToDto(Banda banda);

	/**
	 * Método usado para mapear un {@link BandaDto} a una {@link Banda}
	 * 
	 * @param bandaDto {@link BandaDto} a ser transformado
	 * @return {@link Banda} transformada
	 */
	@Override
	@InheritInverseConfiguration
	Banda dtoToEntity(BandaDto bandaDto);

	/**
	 * {@link org.mapstruct.BeforeMapping} y {@link org.mapstruct.AfterMapping} se
	 * usa para hacer tratamientos anteriores y posteriores respectivamente, al
	 * mapeo
	 * <p>
	 * En este caso mapstruct al ver que el parámetro {@link BandaDto} está anotado
	 * con {@link MappingTarget} sabe que tiene que aplicar la lógica de este método
	 * a los mapeos:
	 * <p>
	 * Banda -> BandaDto Osease por ejemplo {@link #bandaToBandaDto(Banda)}
	 * <p>
	 * En este caso cuando se generen TODOS los metodos que transformen
	 * {@link Banda} a {@link BandaDto} dichos métodos contendrán una llamada a este
	 * método
	 * <p>
	 * Se ejecutarán:
	 * <li>Antes: {@link org.mapstruct.BeforeMapping}</li>
	 * <li>Despues: {@link org.mapstruct.AfterMapping}</li>
	 * <p>
	 * 
	 * @see <b>Lecciones aprendidas:</b> No usar Builder porque como es normal no se
	 *      puede modificar una vez creado el objeto ninguna propiedad de este
	 *      <p>
	 *      <b>Nota:</b> Con {@link org.mapstruct.AfterMapping} solo hace falta
	 *      definir un método con 1 atributo ya que como es lógico al final del
	 *      mapper solo recibes el objeto "Destino"
	 *      <p>
	 *      y si hace falta en ese parámetro el {@link MappingTarget}
	 * 
	 * @param bandaDto
	 * @param banda
	 */
	@BeforeMapping
	default void metalToString(Banda banda, @MappingTarget BandaDto bandaDto) {

		List<String> tiposMusicaString = banda.getTiposMusica()
		        .stream()
		        .map(AbstractMetal::getClass)
		        .map(Class::getSimpleName)
		        .collect(Collectors.toList());

		bandaDto.setEstilosNombre(tiposMusicaString);
	}

	@ObjectFactory
	default AbstractMetal mapearAbstracto(AbstractMetalDto abstractMetalDto) {

		AbstractMetal metal = null;

		switch (abstractMetalDto.getClass()
		        .getSimpleName()) {
		case "BlackDto":
			metal = new Black();
			break;

		case "DeathDto":
			metal = new Death();
			break;
		}

		return metal;
	}

	@ObjectFactory()
	default AbstractMetalDto mapearAbstractoDto(AbstractMetal abstractMetal) {

		AbstractMetalDto metal = null;

		switch (abstractMetal.getClass()
		        .getSimpleName()) {
		case "Black":
			metal = new BlackDto();
			break;
		case "Death":
			metal = new DeathDto();
			break;
		}

		return metal;
	}

}
