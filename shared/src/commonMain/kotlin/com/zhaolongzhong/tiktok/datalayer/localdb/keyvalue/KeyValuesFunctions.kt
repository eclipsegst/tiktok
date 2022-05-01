package com.zhaolongzhong.tiktok.datalayer.localdb.keyvalue

import mylocal.db.*

fun LocalDb.getKeyValue(keyName: String): KeyValue? {
    return keyValuesQueries.getKeyValueByKey(keyName = keyName, mapper = ::KeyValue).executeAsOneOrNull()
}

fun LocalDb.setKeyValue(keyName: String, content: String) {
    keyValuesQueries.transaction {
        keyValuesQueries.upsertKeyValue(keyName = keyName, content = content)
    }
}
