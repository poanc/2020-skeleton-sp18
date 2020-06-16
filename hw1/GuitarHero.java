/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static synthesizer.GuitarString genString(char c, int position) {
        double freqency = 440 * Math.pow(2, (double) (position - 24) / 12);
        return new synthesizer.GuitarString(freqency);
    }

    private static synthesizer.GuitarString[] charToStrings(String str) {
        synthesizer.GuitarString[] strings = new synthesizer.GuitarString[str.length()];

        for (int i = 0; i < str.length(); i++) {
            strings[i] = genString(str.charAt(i), i);
        }

        return strings;
    }

    private static double collectSample(synthesizer.GuitarString[] strings) {
        return strings[0].sample() + collectSample(strings, 1);
    }

    private static double collectSample(synthesizer.GuitarString[] strings, int index) {
        if (strings.length - 1 == index) {
            return 0;
        }
        return strings[index].sample() + collectSample(strings, index + 1);
    }

    private static void ticEachString(synthesizer.GuitarString[] strings) {
        for (synthesizer.GuitarString s : strings) {
            s.tic();
        }
    }

    public static void main(String[] args) {
        /* create guitar strings */
        synthesizer.GuitarString[] strings = charToStrings(keyboard);

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyIndex = keyboard.indexOf(key);
                if (keyIndex > -1) {
                    strings[keyIndex].pluck();
                }

            }

            /* compute the superposition of samples */
            double sample = collectSample(strings);

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            ticEachString(strings);
        }
    }
}

