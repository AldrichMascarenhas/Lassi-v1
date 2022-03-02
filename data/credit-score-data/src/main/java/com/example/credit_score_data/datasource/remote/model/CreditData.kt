package com.example.credit_score_data.datasource.remote.model

internal data class CreditData(
    val accountIDVStatus: String,
    val augmentedCreditScore: Any,
    val coachingSummary: CoachingSummary,
    val creditReportInfo: CreditReportInfo,
    val dashboardStatus: String,
    val personaType: String
)

internal data class CoachingSummary(
    val activeChat: Boolean,
    val activeTodo: Boolean,
    val numberOfCompletedTodoItems: Int,
    val numberOfTodoItems: Int,
    val selected: Boolean
)

internal data class CreditReportInfo(
    val changeInLongTermDebt: Int,
    val changeInShortTermDebt: Int,
    val changedScore: Int,
    val clientRef: String,
    val currentLongTermCreditLimit: Any,
    val currentLongTermCreditUtilisation: Any,
    val currentLongTermDebt: Int,
    val currentLongTermNonPromotionalDebt: Int,
    val currentShortTermCreditLimit: Int,
    val currentShortTermCreditUtilisation: Int,
    val currentShortTermDebt: Int,
    val currentShortTermNonPromotionalDebt: Int,
    val daysUntilNextReport: Int,
    val equifaxScoreBand: Int,
    val equifaxScoreBandDescription: String,
    val hasEverBeenDelinquent: Boolean,
    val hasEverDefaulted: Boolean,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val monthsSinceLastDefaulted: Int,
    val monthsSinceLastDelinquent: Int,
    val numNegativeScoreFactors: Int,
    val numPositiveScoreFactors: Int,
    val percentageCreditUsed: Int,
    val percentageCreditUsedDirectionFlag: Int,
    val score: Int,
    val scoreBand: Int,
    val status: String
)