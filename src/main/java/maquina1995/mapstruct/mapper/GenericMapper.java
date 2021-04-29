package maquina1995.mapstruct.mapper;

public interface GenericMapper<E, D> {

	D entityToDto(E entity);

	E dtoToEntity(D dto);
}
