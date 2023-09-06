package br.com.application.blooddonation.model.tiposanguineo;

import java.util.List;

public enum TipoSanguineo {

    A_POSITIVO("A+") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(AB_POSITIVO, A_POSITIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(A_POSITIVO, A_NEGATIVO, O_POSITIVO, O_NEGATIVO);
        }
    },
    A_NEGATIVO("A-") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(A_POSITIVO, A_NEGATIVO, AB_POSITIVO, AB_NEGATIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(A_NEGATIVO, O_NEGATIVO);
        }
    },
    B_POSITIVO("B+") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(B_POSITIVO, AB_POSITIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(B_POSITIVO, B_NEGATIVO, O_POSITIVO, O_NEGATIVO);
        }
    },
    B_NEGATIVO("B-") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(B_POSITIVO, B_NEGATIVO, AB_POSITIVO, AB_NEGATIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(B_NEGATIVO, O_NEGATIVO);
        }
    },
    AB_POSITIVO("AB+") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(AB_POSITIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(A_POSITIVO, A_NEGATIVO, B_POSITIVO, B_NEGATIVO, AB_POSITIVO, AB_NEGATIVO, O_POSITIVO, O_NEGATIVO);
        }
    },
    AB_NEGATIVO("AB-") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(AB_POSITIVO, AB_NEGATIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(A_NEGATIVO, B_NEGATIVO, O_NEGATIVO, AB_NEGATIVO);
        }
    },
    O_POSITIVO("O+") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(A_POSITIVO, B_POSITIVO, O_POSITIVO, AB_POSITIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(O_POSITIVO, O_NEGATIVO);
        }
    },
    O_NEGATIVO("O-") {
        @Override
        public List<TipoSanguineo> podeDoarPara() {
            return List.of(A_POSITIVO, A_NEGATIVO, B_POSITIVO, B_NEGATIVO, AB_POSITIVO, AB_NEGATIVO, O_POSITIVO, O_NEGATIVO);
        }

        @Override
        public List<TipoSanguineo> podeReceberDe() {
            return List.of(O_NEGATIVO);
        }
    };

    TipoSanguineo(String nome) {
        this.nome = nome;
    }

    public abstract List<TipoSanguineo> podeDoarPara();
    public abstract List<TipoSanguineo> podeReceberDe();

    private final String nome;

    public String getNome() {
        return nome;
    }

    public static List<TipoSanguineo> getAll() {
        return List.of(A_POSITIVO, A_NEGATIVO, B_POSITIVO, B_NEGATIVO, AB_POSITIVO, AB_NEGATIVO, O_POSITIVO, O_NEGATIVO);
    }
}
