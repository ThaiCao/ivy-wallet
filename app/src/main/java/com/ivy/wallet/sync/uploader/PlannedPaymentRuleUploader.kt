package com.ivy.wallet.sync.uploader

import com.ivy.wallet.model.entity.PlannedPaymentRule
import com.ivy.wallet.network.RestClient
import com.ivy.wallet.network.request.planned.DeletePlannedPaymentRuleRequest
import com.ivy.wallet.network.request.planned.UpdatePlannedPaymentRuleRequest
import com.ivy.wallet.persistence.dao.PlannedPaymentRuleDao
import com.ivy.wallet.session.IvySession
import timber.log.Timber
import java.util.*

class PlannedPaymentRuleUploader(
    private val dao: PlannedPaymentRuleDao,
    restClient: RestClient,
    private val ivySession: IvySession
) {
    private val service = restClient.plannedPaymentRuleService

    suspend fun sync(item: PlannedPaymentRule) {
        if (!ivySession.isLoggedIn()) return

        try {
            //update
            service.update(
                UpdatePlannedPaymentRuleRequest(
                    rule = item
                )
            )

            //flag as synced
            dao.save(
                item.copy(
                    isSynced = true
                )
            )
            Timber.d("PlannedPaymentRule updated: $item.")
        } catch (e: Exception) {
            Timber.e("Failed to update with error (${e.message}): $item")
            e.printStackTrace()
        }
    }


    suspend fun delete(id: UUID) {
        if (!ivySession.isLoggedIn()) return

        try {
            //Delete on server
            service.delete(
                DeletePlannedPaymentRuleRequest(
                    id = id
                )
            )

            //delete from local db
            dao.deleteById(id)
            Timber.d("PlannedPaymentRule deleted: $id.")
        } catch (e: Exception) {
            Timber.e("Failed to delete with error (${e.message}): $id")
            e.printStackTrace()

            //delete from local db
            dao.deleteById(id)
        }
    }

}