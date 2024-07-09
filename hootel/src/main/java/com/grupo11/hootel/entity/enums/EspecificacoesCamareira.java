package com.grupo11.hootel.entity.enums;

public enum EspecificacoesCamareira {
    TROCAR_TOALHAS("Trocar toalhas"),
    TROCAR_ROUPA_CAMA("Trocar roupa de cama"),
    RETIRAR_LIXO("Retirar lixo"),
    LIMPAR_BANHEIRO("Limpar banheiro"),
    LIMPAR_CHAO("Limpar o chão"),
    REABASTECER_SUPRIMENTOS("Reabastecer suprimentos");

    private final String displayName;

    EspecificacoesCamareira(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}