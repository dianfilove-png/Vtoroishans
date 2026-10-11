package com.claude.isekai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/* loaded from: classes.dex */
public final class MusicSynth {
    public static final int BARS = 32;
    public static final int SR = 22050;
    private final Random noise;
    private final float[] out;
    private final double step;
    private final int total;
    public static final double[] BPM = {112.0d, 100.0d, 120.0d, 126.72d};
    public static final int[] BARS_T = {32, 32, 32, 4};
    static final int[] ROOT = {9, 4, 2, 2};
    static final int[][] PROG_A = {new int[]{0, 5, 2, 6, 0, 5, 6, 4}, new int[]{0, 3, 5, 4, 0, 3, 6, 4}, new int[]{0, 6, 5, 4, 0, 6, 3, 4}, new int[]{0, 0, 5, 6, 0, 0, 3, 4}};
    static final int[][] PROG_B = {new int[]{3, 0, 6, 4, 3, 0, 4, 4}, new int[]{5, 3, 0, 4, 5, 3, 4, 4}, new int[]{5, 6, 0, 4, 5, 6, 4, 4}, new int[]{5, 5, 6, 6, 0, 0, 4, 4}};
    static final int[] LEAD = {0, 1, 2, 0};
    static final int[] STYLE = {1, 0, 1, 2};
    static final int[] SEED = {3, 11, 21, 33};
    static final int[] LEAD_OCT = {72, 74, 70, 72};
    static final int[] MINOR = {0, 2, 3, 5, 7, 8, 10};

    private MusicSynth(int i) {
        int frames = frames(i);
        this.total = frames;
        this.out = new float[frames];
        this.step = (60.0d / BPM[i]) / 4.0d;
        this.noise = new Random(SEED[i] * 31);
    }

    private void add(double d, float[] fArr, float f) {
        int round = (int) Math.round(d * this.step * 22050.0d);
        for (int i = 0; i < fArr.length; i++) {
            int i2 = (round + i) % this.total;
            float[] fArr2 = this.out;
            fArr2[i2] = fArr2[i2] + (fArr[i] * f);
        }
    }

    private short[] build(int i) {
        int[][][] iArr;
        int[][] iArr2;
        int[][][] iArr3;
        int[][][] iArr4;
        float[] fArr;
        float[] fArr2;
        int i2;
        int i3 = i;
        Random random = new Random(SEED[i3]);
        int i4 = STYLE[i3];
        int[][][] genMelody = genMelody(random, i, PROG_A[i3], 0, 0.55d);
        int[][][] genMelody2 = genMelody(random, i, PROG_B[i3], 5, 0.7d);
        float[] kick = kick();
        float[] click = click();
        int i5 = 0;
        while (i5 < 4) {
            int[] iArr5 = i5 == 2 ? PROG_B[i3] : PROG_A[i3];
            int[][][] iArr6 = i5 == 2 ? genMelody2 : genMelody;
            int i6 = 0;
            while (i6 < 8) {
                int i7 = (i5 * 8) + i6;
                int[] chord = chord(i3, iArr5[i6]);
                int[][] iArr7 = iArr6[i6];
                if (i5 == 3 && i6 == 7) {
                    iArr = genMelody2;
                    iArr2 = new int[][]{new int[]{0, 12, LEAD_OCT[i3] + ROOT[i3] + 12}};
                } else {
                    iArr = genMelody2;
                    iArr2 = iArr7;
                }
                int i8 = 0;
                while (true) {
                    iArr3 = genMelody;
                    iArr4 = iArr6;
                    if (i8 >= iArr2.length) {
                        break;
                    }
                    int[] iArr8 = iArr2[i8];
                    int i9 = iArr8[2] + ((i5 == 1 && i6 % 4 == 3 && i8 % 2 == 1) ? 2 : 0);
                    float[] fArr3 = kick;
                    float[] fArr4 = click;
                    int i10 = (int) (iArr8[1] * this.step * 22050.0d);
                    double d = (i7 * 16) + iArr8[0];
                    int[] iArr9 = LEAD;
                    int[] iArr10 = iArr5;
                    add(d, env(wave(iArr9[i3], hz(i9), i10, 0.25d, iArr9[i3] == 0 ? 0.004d : 0.0d), 0.004d, 0.07d), 0.11f);
                    i8++;
                    i3 = i;
                    genMelody = iArr3;
                    iArr6 = iArr4;
                    iArr5 = iArr10;
                    kick = fArr3;
                    click = fArr4;
                }
                float[] fArr5 = kick;
                float[] fArr6 = click;
                int[] iArr11 = iArr5;
                for (int i11 = 0; i11 < 16; i11++) {
                    if (i4 != 0 || i11 % 2 != 1) {
                        add((i7 * 16) + i11, env(wave(0, hz(chord[i11 % 3] + 12), (int) (this.step * 22050.0d * (i4 == 0 ? 2 : 1)), 0.125d, 0.0d), 0.002d, 0.05d), i5 == 2 ? 0.04f : 0.03f);
                    }
                }
                int i12 = i4 == 0 ? 4 : 2;
                int i13 = 0;
                while (i13 < 16) {
                    add((i7 * 16) + i13, env(wave(1, hz((chord[0] - 24) + ((i4 == 0 || i13 % 4 != 2) ? 0 : 12)), (int) (this.step * 22050.0d * i12), 0.0d, 0.0d), 0.003d, 0.04d), 0.25384617f);
                    i13 += i12;
                    i5 = i5;
                }
                int i14 = i5;
                int i15 = 0;
                while (i15 < 16) {
                    int i16 = i15 % 4;
                    if (i16 == 0) {
                        double d2 = (i7 * 16) + i15;
                        fArr = fArr5;
                        add(d2, fArr, 0.55f);
                        fArr2 = fArr6;
                        add(d2, fArr2, (i15 == 0 ? 1.0f : 0.75f) * 0.22f);
                    } else {
                        fArr = fArr5;
                        fArr2 = fArr6;
                    }
                    if (i15 != 4 && i15 != 12) {
                        fArr5 = fArr;
                        if (!(i4 == 0 || i15 % 2 == 0) && i16 != 0) {
                            add((i7 * 16) + i15, noiseHit(0.035d, 100.0d), i15 % 2 == 1 ? 0.06f : 0.08f);
                        }
                        if (i4 == 2 || i16 != 2) {
                            i2 = i14;
                        } else {
                            i2 = i14;
                            if (i2 != 2 && i2 != 3) {
                            }
                            add((i7 * 16) + i15, noiseHit(0.08d, 40.0d), 0.09f);
                        }
                        i15++;
                        i14 = i2;
                        fArr6 = fArr2;
                    }
                    fArr5 = fArr;
                    add((i7 * 16) + i15, noiseHit(0.13d, 28.0d), 0.16f);
                    if (!(i4 == 0 || i15 % 2 == 0)) {
                    }
                    if (i4 == 2) {
                    }
                    i2 = i14;
                    i15++;
                    i14 = i2;
                    fArr6 = fArr2;
                }
                int i17 = i14;
                float[] fArr7 = fArr6;
                if (i6 == 7) {
                    for (int i18 = 12; i18 < 16; i18++) {
                        add((i7 * 16) + i18, noiseHit(0.07d, 35.0d), 0.12f);
                    }
                }
                i6++;
                i5 = i17;
                genMelody = iArr3;
                click = fArr7;
                iArr6 = iArr4;
                genMelody2 = iArr;
                iArr5 = iArr11;
                kick = fArr5;
                i3 = i;
            }
            int[][][] iArr12 = genMelody2;
            float[] fArr8 = kick;
            float[] fArr9 = click;
            int i19 = i5;
            int[][][] iArr13 = genMelody;
            if (i19 == 2 && i4 == 2) {
                int i20 = (int) (this.step * 22050.0d * 8.0d);
                float[] fArr10 = new float[i20];
                double d3 = 0.0d;
                for (int i21 = 0; i21 < i20; i21++) {
                    double d4 = -(i21 / 22050.0d);
                    d3 += ((Math.exp(3.0d * d4) * 90.0d) + 38.0d) / 22050.0d;
                    fArr10[i21] = (float) (Math.sin(6.283185307179586d * d3) * Math.exp(d4 * 2.5d));
                }
                add(i19 * 8 * 16, fArr10, 0.4f);
            }
            i5 = i19 + 1;
            genMelody = iArr13;
            click = fArr9;
            genMelody2 = iArr12;
            kick = fArr8;
            i3 = i;
        }
        short[] sArr = new short[this.total];
        for (int i22 = 0; i22 < this.total; i22++) {
            sArr[i22] = (short) (Math.tanh(this.out[i22] * 1.25d) * 0.85d * 32767.0d);
        }
        return sArr;
    }

    public static int[] chord(int i, int i2) {
        int i3 = ((ROOT[i] + LEAD_OCT[i]) - 12) + 12;
        int[] iArr = new int[3];
        for (int i4 = 0; i4 < 3; i4++) {
            int i5 = (i4 * 2) + i2;
            iArr[i4] = (i3 - 12) + MINOR[i5 % 7] + ((i5 / 7) * 12);
        }
        return iArr;
    }

    public static int[] chordAt(int i, double d) {
        int floor = (int) Math.floor(d / (240.0d / BPM[i]));
        int i2 = BARS_T[i];
        int i3 = floor % i2;
        if (i3 < 0) {
            i3 += i2;
        }
        return chord(i, (i3 / 8 == 2 ? PROG_B[i] : PROG_A[i])[i3 % 8]);
    }

    private float[] click() {
        float[] fArr = new float[1102];
        for (int i = 0; i < 1102; i++) {
            double d = i / 22050.0d;
            fArr[i] = (float) ((Math.sin(7853.981633974483d * d) + (Math.sin(15707.963267948966d * d) * 0.5d)) * Math.exp((-d) * 90.0d));
        }
        return fArr;
    }

    private static float[] env(float[] fArr, double d, double d2) {
        int length = fArr.length;
        int max = Math.max(1, (int) (d * 22050.0d));
        int min = Math.min((int) (d2 * 22050.0d), length);
        for (int i = 0; i < Math.min(max, length); i++) {
            fArr[i] = fArr[i] * (i / max);
        }
        for (int i2 = 0; i2 < min; i2++) {
            int i3 = (length - min) + i2;
            fArr[i3] = fArr[i3] * (1.0f - (i2 / min));
        }
        return fArr;
    }

    public static int frames(int i) {
        return (int) Math.round(BARS_T[i] * 16 * ((60.0d / BPM[i]) / 4.0d) * 22050.0d);
    }

    private int[][][] genMelody(Random random, int i, int[] iArr, int i2, double d) {
        int i3 = i;
        int[][][] iArr2 = new int[8][][];
        int i4 = LEAD_OCT[i3];
        int i5 = ROOT[i3];
        int i6 = i4 + i5 + i2;
        int i7 = (i4 + i5) - 5;
        int i8 = i4 + i5 + 14 + i2;
        int[] iArr3 = STYLE[i3] == 0 ? new int[]{2, 2, 4, 4, 3} : new int[]{2, 2, 2, 4, 1, 1, 3};
        int i9 = 0;
        for (int i10 = 8; i9 < i10; i10 = 8) {
            int[] chord = chord(i3, iArr[i9]);
            ArrayList arrayList = new ArrayList();
            int i11 = 0;
            while (i11 < 16) {
                if (random.nextDouble() < d || i11 == 0) {
                    int min = Math.min(iArr3[random.nextInt(iArr3.length)], 16 - i11);
                    ArrayList arrayList2 = new ArrayList();
                    int i12 = i6 - 7;
                    while (i12 <= i6 + 7) {
                        int i13 = (((i12 - ROOT[i3]) % 12) + 12) % 12;
                        int[] iArr4 = MINOR;
                        int length = iArr4.length;
                        int[] iArr5 = iArr3;
                        int i14 = 0;
                        boolean z = false;
                        while (i14 < length) {
                            int i15 = length;
                            if (iArr4[i14] == i13) {
                                z = true;
                            }
                            i14++;
                            length = i15;
                        }
                        if (z) {
                            if (i11 % 4 == 0) {
                                boolean z2 = false;
                                for (int i16 : chord) {
                                    if ((((i16 - i12) % 12) + 12) % 12 == 0) {
                                        z2 = true;
                                    }
                                }
                                if (!z2) {
                                }
                            }
                            arrayList2.add(Integer.valueOf(i12));
                        }
                        i12++;
                        i3 = i;
                        iArr3 = iArr5;
                    }
                    int[] iArr6 = iArr3;
                    if (arrayList2.isEmpty()) {
                        arrayList2.add(Integer.valueOf(i6));
                    }
                    double d2 = 0.0d;
                    for (Iterator it = arrayList2.iterator(); it.hasNext(); it = it) {
                        d2 += 1.0d / (Math.abs(((Integer) it.next()).intValue() - i6) + 1);
                    }
                    double nextDouble = random.nextDouble() * d2;
                    int intValue = ((Integer) arrayList2.get(0)).intValue();
                    Iterator it2 = arrayList2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        int intValue2 = ((Integer) it2.next()).intValue();
                        int i17 = intValue;
                        int i18 = i6;
                        nextDouble -= 1.0d / (Math.abs(intValue2 - i6) + 1);
                        if (nextDouble <= 0.0d) {
                            intValue = intValue2;
                            break;
                        }
                        i6 = i18;
                        intValue = i17;
                    }
                    i6 = Math.max(i7, Math.min(i8, intValue));
                    arrayList.add(new int[]{i11, min, i6});
                    i11 += min;
                    i3 = i;
                    iArr3 = iArr6;
                } else {
                    i11++;
                }
            }
            iArr2[i9] = (int[][]) arrayList.toArray(new int[0][]);
            i9++;
            i3 = i;
        }
        return iArr2;
    }

    static double hz(int i) {
        return Math.pow(2.0d, (i - 69) / 12.0d) * 440.0d;
    }

    private float[] kick() {
        float[] fArr = new float[2646];
        double d = 0.0d;
        for (int i = 0; i < 2646; i++) {
            double d2 = -(i / 22050.0d);
            d += ((Math.exp(25.0d * d2) * 150.0d) + 45.0d) / 22050.0d;
            fArr[i] = (float) (Math.sin(6.283185307179586d * d) * Math.exp(d2 * 22.0d));
        }
        return fArr;
    }

    private float[] noiseHit(double d, double d2) {
        int i = (int) (d * 22050.0d);
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = (float) (((this.noise.nextFloat() * 2.0f) - 1.0f) * Math.exp(((-i2) / 22050.0d) * d2));
        }
        return fArr;
    }

    public static short[] pluck(int i) {
        float[] wave = wave(i, 440.0d, 4851, 0.25d, 0.0d);
        for (int i2 = 0; i2 < 4851; i2++) {
            wave[i2] = (float) (wave[i2] * Math.exp(((-i2) / 22050.0d) * 14.0d));
        }
        env(wave, 0.002d, 0.03d);
        short[] sArr = new short[4851];
        for (int i3 = 0; i3 < 4851; i3++) {
            sArr[i3] = (short) (wave[i3] * 0.8f * 32767.0f);
        }
        return sArr;
    }

    public static short[] render(int i) {
        return new MusicSynth(i).build(i);
    }

    private static float[] wave(int i, double d, int i2, double d2, double d3) {
        float[] fArr = new float[i2];
        double d4 = 0.0d;
        for (int i3 = 0; i3 < i2; i3++) {
            d4 += (((Math.sin((i3 / 22050.0d) * 34.55751918948772d) * d3) + 1.0d) * d) / 22050.0d;
            double floor = d4 - Math.floor(d4);
            if (i == 0) {
                fArr[i3] = floor < d2 ? 1.0f : -1.0f;
            } else if (i == 1) {
                fArr[i3] = (float) (((Math.abs((floor * 2.0d) - 1.0d) * 2.0d) - 1.0d) * 1.3d);
            } else {
                fArr[i3] = (float) (((floor * 2.0d) - 1.0d) * 0.8d);
            }
        }
        return fArr;
    }
}
