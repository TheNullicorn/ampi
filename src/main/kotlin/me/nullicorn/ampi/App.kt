package me.nullicorn.ampi

import javax.sound.sampled.*
import me.nullicorn.ampi.audio.*

// TODO: 3/30/22 Make these configurable at runtime. (via website? mobile app?)
private const val GAIN = 0.1f
private const val VOLUME = 1.0f
private const val CLIPPING = 0.01f

private const val CHUNK_SIZE = 256

fun main() {
    val format = AudioFormat(8000.0f, 16, 1, true, true)
    try {
        val microphone = openInputLine(format)
        val speakers = FilteredSourceDataLine(openOutputLine(format)).apply {
            gain = GAIN
            volume = VOLUME
            clipping = CLIPPING
        }

        val audioBuffer = ByteArray(microphone.bufferSize / 5)

        while (true) {
            // Read the audio data from the microphone & forward it directly to the speaker.
            // Filtering is applied by our delegate class before it's sent to the speaker.
            val totalBytesRead = microphone.read(audioBuffer, 0, CHUNK_SIZE)
            speakers.write(audioBuffer, offset = 0, length = totalBytesRead)
        }

        // TODO: 3/30/22 Run the 3 lines below in an exit-handler?
        //speakers.drain()
        //speakers.close()
        //microphone.close()
    } catch (e: LineUnavailableException) {
        e.printStackTrace()
    }
}

