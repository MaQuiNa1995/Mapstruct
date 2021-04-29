package maquina1995.mapstruct.dto.musica;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeathDto extends AbstractMetalDto {

	private Boolean melodico;
}
