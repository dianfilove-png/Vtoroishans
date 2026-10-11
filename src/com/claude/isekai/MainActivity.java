package com.claude.isekai;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: classes.dex */
public class MainActivity extends Activity {
    private GameView game;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        GameView gameView = new GameView(this);
        this.game = gameView;
        setContentView(gameView);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.game.pauseGame();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.game.resume();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!this.game.onBack()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.game.releaseAudio();
    }
}
