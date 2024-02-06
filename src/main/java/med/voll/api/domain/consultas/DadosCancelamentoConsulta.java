package med.voll.api.domain.consultas;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(

        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo
) {
}
