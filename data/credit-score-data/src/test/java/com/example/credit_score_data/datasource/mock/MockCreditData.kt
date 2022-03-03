package com.example.credit_score_data.datasource.mock

import com.example.credit_score_data.datasource.remote.model.CoachingSummary
import com.example.credit_score_data.datasource.remote.model.CreditData
import com.example.credit_score_data.datasource.remote.model.CreditReportInfo

internal val mockCachingSummary = CoachingSummary(
    activeChat = false,
    activeTodo = false,
    numberOfCompletedTodoItems = 0,
    numberOfTodoItems = 0,
    selected = false
)

internal val mockCreditReportInfo = CreditReportInfo(
    changeInLongTermDebt = 0,
    changeInShortTermDebt = 0,
    changedScore = 0,
    clientRef = "",
    currentLongTermCreditLimit = null,
    currentLongTermCreditUtilisation = null,
    currentLongTermDebt = 0,
    currentLongTermNonPromotionalDebt = 0,
    currentShortTermCreditLimit = 0,
    currentShortTermCreditUtilisation = 0,
    currentShortTermDebt = 0,
    currentShortTermNonPromotionalDebt = 0,
    daysUntilNextReport = 0,
    equifaxScoreBand = 0,
    equifaxScoreBandDescription = "",
    hasEverBeenDelinquent = false,
    hasEverDefaulted = false,
    maxScoreValue = 0,
    minScoreValue = 0,
    monthsSinceLastDefaulted = 0,
    monthsSinceLastDelinquent = 0,
    numNegativeScoreFactors = 0,
    numPositiveScoreFactors = 0,
    percentageCreditUsed = 0,
    percentageCreditUsedDirectionFlag = 0,
    score = 0,
    scoreBand = 0,
    status = ""
)

internal val mockCreditData = CreditData(
    accountIDVStatus = "",
    augmentedCreditScore = "",
    dashboardStatus = "",
    personaType = "",
    coachingSummary = mockCachingSummary,
    creditReportInfo = mockCreditReportInfo
)