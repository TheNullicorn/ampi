@file:Suppress("NOTHING_TO_INLINE")

package me.nullicorn.ampi.util

internal inline fun ByteArray.getShort(offset: Int) =
    ((this[offset].toInt() and 0xFF shl 8) or (this[offset + 1].toInt() and 0xFF)).toShort().toInt()

internal inline fun ByteArray.setShort(offset: Int, value: Int) {
    this[offset] = (value shr 8).toByte()
    this[offset + 1] = value.toByte()
}