package maquina1995.mapstruct.domain.musica;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Black extends AbstractMetal {

	private Boolean tematicaSatanica;
}
