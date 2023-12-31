package com.ivy.wallet.sync.item

import com.ivy.wallet.base.timeNowUTC
import com.ivy.wallet.base.toEpochSeconds
import com.ivy.wallet.network.RestClient
import com.ivy.wallet.persistence.SharedPrefs
import com.ivy.wallet.persistence.dao.BudgetDao
import com.ivy.wallet.session.IvySession
import com.ivy.wallet.sync.uploader.BudgetUploader

class BudgetSync(
    private val sharedPrefs: SharedPrefs,
    private val dao: BudgetDao,
    restClient: RestClient,
    private val uploader: BudgetUploader,
    private val ivySession: IvySession
) {
    private val service = restClient.budgetService

    fun isSynced(): Boolean =
        dao.findByIsSyncedAndIsDeleted(synced = false, deleted = false).isEmpty() &&
            dao.findByIsSyncedAndIsDeleted(synced = false, deleted = true).isEmpty()

    suspend fun sync() {
        if (!ivySession.isLoggedIn()) return

        val syncStart = timeNowUTC().toEpochSeconds()

        uploadUpdated()
        deleteDeleted()
        fetchNew()

        sharedPrefs.putLong(SharedPrefs.LAST_SYNC_DATE_BUDGETS, syncStart)
    }

    private suspend fun uploadUpdated() {
        val toSync = dao.findByIsSyncedAndIsDeleted(
            synced = false,
            deleted = false
        )

        for (item in toSync) {
            uploader.sync(item)
        }
    }

    private suspend fun deleteDeleted() {
        val toDelete = dao.findByIsSyncedAndIsDeleted(
            synced = false,
            deleted = true
        )

        for (item in toDelete) {
            uploader.delete(item.id)
        }
    }

    private suspend fun fetchNew() {
        try {
            val afterTimestamp = sharedPrefs.getEpochSeconds(SharedPrefs.LAST_SYNC_DATE_BUDGETS)

            val response = service.get(after = afterTimestamp)

            response.budgets.forEach { item ->
                dao.save(
                    item.copy(
                        isSynced = true,
                        isDeleted = false
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}