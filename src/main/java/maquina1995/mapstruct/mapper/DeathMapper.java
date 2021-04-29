package maquina1995.mapstruct.mapper;

import org.mapstruct.Mapper;

import maquina1995.mapstruct.domain.musica.Death;
import maquina1995.mapstruct.dto.musica.DeathDto;

@Mapper
public interface DeathMapper extends GenericMapper<Death, DeathDto> {

}
