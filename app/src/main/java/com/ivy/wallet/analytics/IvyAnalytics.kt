package com.ivy.wallet.analytics

import com.ivy.wallet.BuildConfig
import com.ivy.wallet.base.ioThread
import com.ivy.wallet.model.analytics.AnalyticsEvent
import com.ivy.wallet.network.RestClient
import com.ivy.wallet.network.request.analytics.LogEventRequest
import com.ivy.wallet.persistence.SharedPrefs
import java.util.*

class IvyAnalytics(
    private val sharedPrefs: SharedPrefs,
    restClient: RestClient
) {
    private val service = restClient.analyticsService

    private lateinit var sessionId: UUID

    fun loadSession() {
        var sessionIdStr = sharedPrefs.getString(SharedPrefs.ANALYTICS_SESSION_ID, null)
        if (sessionIdStr == null) {
            sessionIdStr = UUID.randomUUID().toString()
            sharedPrefs.putString(SharedPrefs.ANALYTICS_SESSION_ID, sessionIdStr)
        }
        sessionId = UUID.fromString(sessionIdStr)
    }

    suspend fun logEvent(name: String) {
        if (BuildConfig.DEBUG) return

        ioThread {
            try {
                service.logEvent(
                    LogEventRequest(
                        event = AnalyticsEvent(
                            name = name,
                            sessionId = sessionId
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}