package com.rainyday.foodrun.feature.order.domain.model

enum class OrderStatus {
    PENDING,
    PREPARING,
    ON_THE_WAY,
    DELIVERED,
    CANCELLED;

    fun displayName(): String = when (this) {
        PENDING -> "Очікує підтвердження"
        PREPARING -> "Готується"
        ON_THE_WAY -> "В дорозі"
        DELIVERED -> "Доставлено"
        CANCELLED -> "Скасовано"
    }

    fun emoji(): String = when (this) {
        PENDING -> "🕐"
        PREPARING -> "👨‍🍳"
        ON_THE_WAY -> "🛵"
        DELIVERED -> "✅"
        CANCELLED -> "❌"
    }
}