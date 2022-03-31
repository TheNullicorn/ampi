# ampi (incomplete)

A barebones guitar amp that should run on any device with audio input & output. See the "Note" section below before use.

## Note

This was just intended for a little side-project with my family, and I wouldn't recommend using it in the wild as-is. More specifically, this ran on a Raspberry Pi Zero W, connected to a Guitar (via USB) and speaker (via bluetooth) as described below.

If you do wish to adapt this to your own setup, feel free to open an issue if you need any help with it.

## Environment Used

1. Guitar (1/4 inch Jack) --> USB
   1. Using [this adapter][product-1]
2. USB --> Micro USB
   1. Using [this adapter][product-2]
3. Micro USB --> [Raspberry Pi Zero W][product-3] data port
4. Raspberry Pi Zero W --> Speaker
   1. [This][product-4] bluetooth speaker was paired with the Pi for testing
   2. Could potentially be done via USB or GPIO pins as well

[product-1]: https://amzn.com/dp/B08GLRPXCJ

[product-2]: https://amzn.com/dp/B00N9S9Z0G

[product-3]: https://raspberrypi.com/products/raspberry-pi-zero-w

[product-4]: https://alteclansing.com/product/jacket-h20-2

## Attributions 💞

- [This][post-1] solution by [Nick][user-1] on StackOverflow, which the original code for reading &
  writing audio data was derived from.
- [This][post-2] article, for the guide on setting up fat-jars, which I can never recall how to do.
- [This][post-3] article, for helping me understand the difference between volume and gain.
- [This][post-4] Wikipedia page, which helped me better understand PCM.

[post-1]: https://stackoverflow.com/a/25813398/17453120

[user-1]: https://stackoverflow.com/u/1076471

[post-2]: https://www.baeldung.com/kotlin/gradle-executable-jar

[post-3]: https://www.musicianonamission.com/gain-vs-volume

[post-4]: https://en.wikipedia.org/wiki/Pulse-code_modulation
