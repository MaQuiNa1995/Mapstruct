package maquina1995.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import maquina1995.mapstruct.domain.musica.AbstractMetal;
import maquina1995.mapstruct.domain.musica.Black;
import maquina1995.mapstruct.domain.musica.Death;
import maquina1995.mapstruct.dto.musica.AbstractMetalDto;
import maquina1995.mapstruct.dto.musica.BlackDto;
import maquina1995.mapstruct.dto.musica.DeathDto;

/**
 * para poder hacer un mapeado polimorfico como todavía mapstruct no cuenta con
 * esta caracteristica hay que hacerlo a mano:
 * <a href="https://github.com/mapstruct/mapstruct/issues/2438">Como se muestra
 * aqui</a>
 * <p>
 * El link de la petición de esta característica es:
 * <a href="https://github.com/mapstruct/mapstruct/issues/131">Esta</a>
 * <p>
 * La propuesta ofrecida en el link es la expuesta en:
 * {@link #polymorphicMapping(AbstractMetal)} y
 * {@link #polymorphicMapping(AbstractMetalDto)}
 * <p>
 * y requiere de hacer el {@link @Autowired} de {@link BlackMapper} y
 * {@link DeathMapper}
 * 
 * @author MaQuiNa1995
 *
 */
@Mapper
public abstract class AbstractMetalMapper {

	@Autowired
	protected BlackMapper blackMapper;
	@Autowired
	protected DeathMapper deathMapper;

	protected AbstractMetal polymorphicMapping(AbstractMetalDto metalDto) {

		AbstractMetal abstractMetal;

		if (metalDto instanceof BlackDto) {
			abstractMetal = blackMapper.dtoToEntity((BlackDto) metalDto);
		} else if (metalDto instanceof DeathDto) {
			abstractMetal = deathMapper.dtoToEntity((DeathDto) metalDto);
		} else {
			throw new IllegalArgumentException(metalDto.getClass()
			        .getSimpleName() + " Not Allowed");
		}
		return abstractMetal;
	}

	protected AbstractMetalDto polymorphicMapping(AbstractMetal metal) {

		AbstractMetalDto abstractMetalDto;

		if (metal instanceof Black) {
			abstractMetalDto = blackMapper.entityToDto((Black) metal);
		} else if (metal instanceof Death) {
			abstractMetalDto = deathMapper.entityToDto((Death) metal);
		} else {
			throw new IllegalArgumentException(metal.getClass()
			        .getSimpleName() + " Not Allowed");
		}
		return abstractMetalDto;
	}

}
