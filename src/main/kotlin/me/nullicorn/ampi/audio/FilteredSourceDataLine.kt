package me.nullicorn.ampi.audio

import me.nullicorn.ampi.util.*
import javax.sound.sampled.SourceDataLine

class FilteredSourceDataLine(private val output: SourceDataLine) : SourceDataLine by output {

    /**
     * The amplitude % that samples will be scaled to before being processed.
     *
     * This value must be from `0.0` through `1.0`.
     */
    var gain: Float = 1.0f

    /**
     * The amplitude % that samples will be scaled to after being processing.
     *
     * This value must be from `0.0` through `1.0`.
     */
    var volume: Float = 1.0f
        set(value) {
            if (value !in 0.0..1.0)
                throw IllegalArgumentException("volume must be from 0.0 through 1.0, not $value")

            field = value
            maxOutputAmplitude = Short.MAX_VALUE * value
        }

    /**
     * The maximum amplitude % of a sample.
     *
     * This value must be from `0.0` through `1.0`.
     *
     * Any samples with a greater amplitude will be shrunk down to this value's percentage.
     */
    var clipping: Float = 1.0f
        set(value) {
            if (value !in 0.0..1.0)
                throw IllegalArgumentException("clipping must be from 0.0 through 1.0, not $value")

            field = value

            minClippingAmplitude = Short.MIN_VALUE * value
            maxClippingAmplitude = Short.MAX_VALUE * value
        }

    // The highest and lowest amplitude value for any sample.
    // Samples that exceed these limits will be coerced into them via Float.coerceIn().
    private var minClippingAmplitude: Float = Short.MIN_VALUE.toFloat()
    private var maxClippingAmplitude: Float = Short.MAX_VALUE.toFloat()

    // The maximum amplitude value of any sample output by this filter.
    private var maxOutputAmplitude: Float = Short.MAX_VALUE.toFloat()

    override fun write(data: ByteArray, offset: Int, length: Int): Int {
        val end = offset + length

        for (i in offset until end step 2) {
            var sample = data.getShort(i).toFloat()
            sample *= gain
            sample = sample.coerceIn(minClippingAmplitude, maxClippingAmplitude)
            sample = (sample / maxClippingAmplitude) * maxOutputAmplitude

            data.setShort(i, sample.toInt())
        }

        return output.write(data, offset, length)
    }
}