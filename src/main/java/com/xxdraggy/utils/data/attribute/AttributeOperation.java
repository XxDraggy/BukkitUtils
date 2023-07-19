package com.xxdraggy.utils.data.attribute;

import org.bukkit.attribute.AttributeModifier;

public enum AttributeOperation {
    AddNumber(AttributeModifier.Operation.ADD_NUMBER),
    AddScalar(AttributeModifier.Operation.ADD_SCALAR),
    MultiplyScalar(AttributeModifier.Operation.MULTIPLY_SCALAR_1);

    AttributeOperation(AttributeModifier.Operation operation) {
        this.operation = operation;
    }

    private AttributeModifier.Operation operation;

    public AttributeModifier.Operation getBukkitOperation() {
        return this.operation;
    }
}
