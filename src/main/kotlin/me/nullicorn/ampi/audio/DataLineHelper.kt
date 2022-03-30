package me.nullicorn.ampi.audio

import javax.sound.sampled.*

internal fun openOutputLine(format: AudioFormat) = getDataLine<SourceDataLine>(format).apply {
    open(format)
    start()
}

internal fun openInputLine(format: AudioFormat) = getDataLine<TargetDataLine>(format).apply {
    open(format)
    start()
}

private inline fun <reified L> getDataLine(format: AudioFormat) =
    AudioSystem.getLine(
        DataLine.Info(L::class.java, format)
    ) as L