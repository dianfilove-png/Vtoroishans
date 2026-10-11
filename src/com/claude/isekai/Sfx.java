package com.claude.isekai;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioTrack;
import android.media.SoundPool;
import android.os.Build;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class Sfx {
    public static final int BELL = 11;
    public static final int BOSS = 3;
    public static final int BUY = 4;
    public static final int CRASH = 10;
    public static final int CRIT = 1;
    public static final int FAIL = 8;
    public static final int FAIRY = 6;
    public static final int HONK = 9;
    public static final int KILL = 2;
    public static final int METEOR = 5;
    private static final float MUSIC_VOL = 0.6f;
    public static final int PLUCK = 12;
    public static final int REBORN = 7;
    static final int SR = 22050;
    public static final int TAP = 0;
    static final double[] TRACK_BPM = MusicSynth.BPM;
    private final Context ctx;
    private AudioTrack music;
    public boolean muted;
    private final Vibrator vib;
    private Thread writer;
    private final int[] ids = new int[15];
    private final long[] last = new long[15];
    public boolean musicOn = true;
    public boolean vibOn = true;
    private boolean musicFailed = false;
    final short[][] pcm = new short[4][];
    private volatile int wantTrack = 0;
    private volatile int curPlayTrack = -1;
    private volatile int prevPlayTrack = -1;
    private volatile long curStartFrame = 0;
    private volatile long prevStartFrame = 0;
    volatile boolean alive = false;
    private volatile float duck = 1.0f;
    private final SoundPool pool = new SoundPool.Builder().setMaxStreams(8).setAudioAttributes(new AudioAttributes.Builder().setUsage(14).setContentType(4).build()).build();

    static final class MusicThread extends Thread {
        final Sfx owner;

        MusicThread(Sfx sfx) {
            super("music");
            this.owner = sfx;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.owner.writeLoop();
        }
    }

    static final class PreRender extends Thread {
        final Sfx owner;

        PreRender(Sfx sfx) {
            super("music-pre");
            this.owner = sfx;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 4 && this.owner.alive; i++) {
                if (this.owner.pcm[i] == null) {
                    this.owner.pcm[i] = this.owner.decode(i);
                }
            }
        }
    }

    public Sfx(Context context) {
        this.ctx = context.getApplicationContext();
        int[] iArr = {R.raw.sfx_tap, R.raw.sfx_crit, R.raw.sfx_kill, R.raw.sfx_boss, R.raw.sfx_buy, R.raw.sfx_meteor, R.raw.sfx_fairy, R.raw.sfx_reborn, R.raw.sfx_fail, R.raw.sfx_honk, R.raw.sfx_crash, R.raw.sfx_bell, R.raw.sfx_pluck0, R.raw.sfx_pluck1, R.raw.sfx_pluck2};
        for (int i = 0; i < 15; i++) {
            this.ids[i] = this.pool.load(context, iArr[i], 1);
        }
        this.vib = (Vibrator) context.getSystemService("vibrator");
    }

    private void startWriter() {
        if (this.writer != null) {
            return;
        }
        AudioTrack audioTrack = new AudioTrack(3, 22050, 4, 2, Math.max(AudioTrack.getMinBufferSize(22050, 4, 2) * 2, 8192), 1);
        this.music = audioTrack;
        audioTrack.setStereoVolume(this.duck * MUSIC_VOL, this.duck * MUSIC_VOL);
        this.alive = true;
        this.writer = new MusicThread(this);
        PreRender preRender = new PreRender(this);
        preRender.setPriority(1);
        preRender.start();
        this.writer.setPriority(10);
        this.writer.start();
    }

    static int trackFrames(int i) {
        return MusicSynth.frames(i);
    }

    public double bpmNow() {
        int playingTrack = playingTrack();
        double[] dArr = TRACK_BPM;
        if (playingTrack < 0) {
            playingTrack = this.wantTrack;
        }
        return dArr[playingTrack];
    }

    public void buzz(long j) {
        if (!this.vibOn || this.vib == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                this.vib.vibrate(VibrationEffect.createOneShot(j, -1));
            } else {
                this.vib.vibrate(j);
            }
        } catch (Exception e) {
        }
    }

    short[] decode(int i) {
        try {
            return i == 3 ? loadWav(R.raw.mus_beat_user, MusicSynth.frames(3)) : MusicSynth.render(i);
        } catch (Throwable th) {
            return null;
        }
    }

    short[] loadWav(int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        InputStream openRawResource = this.ctx.getResources().openRawResource(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        while (true) {
            int read = openRawResource.read(bArr);
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        openRawResource.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int i6 = 12;
        while (true) {
            i4 = i6 + 8;
            if (i4 > byteArray.length) {
                i4 = -1;
                i5 = 0;
                break;
            }
            int i7 = (byteArray[i6 + 4] & 255) | ((byteArray[i6 + 5] & 255) << 8) | ((byteArray[i6 + 6] & 255) << 16) | ((byteArray[i6 + 7] & 255) << 24);
            if (byteArray[i6] == 100 && byteArray[i6 + 1] == 97 && byteArray[i6 + 2] == 116 && byteArray[i6 + 3] == 97) {
                i5 = Math.min(i7, byteArray.length - i4);
                break;
            }
            i6 += i7 + 8;
        }
        short[] sArr = new short[i2];
        if (i4 < 0) {
            return sArr;
        }
        for (i3 = 0; i3 < i2; i3++) {
            int i8 = (i3 * 2) + i4;
            int i9 = i8 + 1;
            if (i9 >= i4 + i5) {
                break;
            }
            sArr[i3] = (short) ((byteArray[i8] & 255) | (byteArray[i9] << 8));
        }
        return sArr;
    }

    public double loopSecNow() {
        int playingTrack = playingTrack();
        if (playingTrack < 0) {
            playingTrack = this.wantTrack;
        }
        return trackFrames(playingTrack) / 22050.0d;
    }

    public void musicPause() {
        try {
            AudioTrack audioTrack = this.music;
            if (audioTrack == null || audioTrack.getPlayState() != 3) {
                return;
            }
            this.music.pause();
        } catch (Throwable th) {
        }
    }

    public double musicPosSec() {
        int i;
        long j;
        try {
            AudioTrack audioTrack = this.music;
            if (audioTrack != null && audioTrack.getPlayState() == 3 && this.curPlayTrack >= 0) {
                long playbackHeadPosition = this.music.getPlaybackHeadPosition() & 4294967295L;
                if (playbackHeadPosition >= this.curStartFrame) {
                    i = this.curPlayTrack;
                    j = this.curStartFrame;
                } else {
                    if (this.prevPlayTrack < 0) {
                        return -1.0d;
                    }
                    i = this.prevPlayTrack;
                    j = this.prevStartFrame;
                }
                if (this.pcm[i] == null) {
                    return -1.0d;
                }
                long length = (playbackHeadPosition - j) % this.pcm[i].length;
                if (length < 0) {
                    length += this.pcm[i].length;
                }
                return length / 22050.0d;
            }
            return -1.0d;
        } catch (Throwable th) {
            return -1.0d;
        }
    }

    public void musicResume() {
        if (!this.musicOn) {
            musicPause();
            return;
        }
        try {
            startWriter();
            if (this.music.getPlayState() != 3) {
                this.music.play();
            }
        } catch (Throwable th) {
        }
    }

    public void play(int i, float f) {
        if (this.muted) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = (i == 0 || i == 2 || i == 4) ? 55L : 150L;
        long[] jArr = this.last;
        if (uptimeMillis - jArr[i] < j) {
            return;
        }
        jArr[i] = uptimeMillis;
        this.pool.play(this.ids[i], f, f, 1, 0, (i == 0 || i == 2) ? (((float) Math.random()) * 0.25f) + 0.9f : 1.0f);
    }

    public void playHit(int i, boolean z, double d) {
        int playingTrack = playingTrack();
        if (playingTrack < 0) {
            playingTrack = this.wantTrack;
        }
        double musicPosSec = musicPosSec();
        if (musicPosSec >= 0.0d) {
            d = musicPosSec;
        }
        int[] chordAt = MusicSynth.chordAt(playingTrack, d);
        int max = Math.max(0, i - 1) % 4;
        int i2 = max < 3 ? chordAt[max] : chordAt[0] + 12;
        while (i2 > 81) {
            i2 -= 12;
        }
        while (i2 < 58) {
            i2 += 12;
        }
        playRate(MusicSynth.LEAD[playingTrack] + 12, z ? 0.55f : 0.35f, (float) Math.pow(2.0d, (i2 - 69) / 12.0d));
    }

    public void playRate(int i, float f, float f2) {
        if (this.muted) {
            return;
        }
        this.pool.play(this.ids[i], f, f, 2, 0, Math.max(0.5f, Math.min(2.0f, f2)));
    }

    public int playingTrack() {
        try {
            AudioTrack audioTrack = this.music;
            if (audioTrack != null && audioTrack.getPlayState() == 3 && this.curPlayTrack >= 0) {
                return (((long) this.music.getPlaybackHeadPosition()) & 4294967295L) >= this.curStartFrame ? this.curPlayTrack : this.prevPlayTrack;
            }
            return -1;
        } catch (Throwable th) {
            return -1;
        }
    }

    public void release() {
        this.pool.release();
        this.alive = false;
        try {
            AudioTrack audioTrack = this.music;
            if (audioTrack != null) {
                audioTrack.pause();
                this.music.flush();
                this.music.stop();
                this.music.release();
            }
        } catch (Throwable th) {
        }
        this.music = null;
    }

    public void setDuck(float f) {
        if (f == this.duck) {
            return;
        }
        this.duck = f;
        try {
            AudioTrack audioTrack = this.music;
            if (audioTrack != null) {
                float f2 = f * MUSIC_VOL;
                audioTrack.setStereoVolume(f2, f2);
            }
        } catch (Throwable th) {
        }
    }

    public void setTrack(int i) {
        if (i < 0 || i >= 4) {
            return;
        }
        this.wantTrack = i;
    }

    void writeLoop() {
        int i = -1;
        long j = 0;
        int i2 = 0;
        while (this.alive) {
            int i3 = this.wantTrack;
            if (this.pcm[i3] == null) {
                short[] decode = decode(i3);
                if (decode == null) {
                    this.musicFailed = true;
                    decode = new short[trackFrames(i3)];
                }
                this.pcm[i3] = decode;
            }
            if (i3 != i) {
                this.prevPlayTrack = this.curPlayTrack;
                this.prevStartFrame = this.curStartFrame;
                this.curStartFrame = j;
                this.curPlayTrack = i3;
                i = i3;
                i2 = 0;
            }
            short[] sArr = this.pcm[i];
            try {
                int write = this.music.write(sArr, i2, Math.min(2048, sArr.length - i2));
                if (write <= 0) {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException e) {
                        return;
                    }
                } else {
                    i2 += write;
                    if (i2 >= sArr.length) {
                        i2 = 0;
                    }
                    j += write;
                }
            } catch (Throwable th) {
                return;
            }
        }
    }
}
