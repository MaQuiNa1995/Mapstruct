package maquina1995.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import maquina1995.mapstruct.domain.Banda;
import maquina1995.mapstruct.domain.BandaDto;

@Mapper(componentModel = "spring")
public interface BandaMapper {

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
	@Mapping(source = "banda.estilo",
	        target = "estiloMetal")
	@Mapping(source = "banda.numIntegrantes",
	        target = "numeroIntegrantes")
	BandaDto bandaToBandaDto(Banda banda);

	/**
	 * Método usado para mapear un {@link BandaDto} a una {@link Banda}
	 * 
	 * @param bandaDto {@link BandaDto} a ser transformado
	 * @return {@link Banda} transformada
	 */
	@Mapping(source = "bandaDto.estiloMetal",
	        target = "estilo")
	@Mapping(source = "bandaDto.numeroIntegrantes",
	        target = "numIntegrantes")
	Banda bandaDtoToBanda(BandaDto bandaDto);

}
