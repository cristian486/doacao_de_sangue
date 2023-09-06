package br.com.application.blooddonation.model.doador;

import br.com.application.blooddonation.infra.exception.DoacaoException;

public enum Sexo {
    MASCULINO {
        @Override
        public void verificarIntervalo(int mesesDesdeUltimaDoacaoDeSangue) {
            if (mesesDesdeUltimaDoacaoDeSangue < 2)
                throw new DoacaoException("Pessoas do sexo masculino devem respeitar o intervalo de 60 dias");
        }
    }, FEMININO {
        @Override
        public void verificarIntervalo(int mesesDesdeUltimaDoacaoDeSangue) {
            if (mesesDesdeUltimaDoacaoDeSangue < 3)
                throw new DoacaoException("Pessoas do sexo feminino devem respeitar o intervalo de 90 dias");
        }
    };

    public abstract void verificarIntervalo(int mesesDesdeUltimaDoacaoDeSangue);
}
