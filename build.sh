#!/bin/bash
# Сборка: ./build.sh  -> build/SecondChance.apk
set -e
cd "$(dirname "$0")"
SDK=${ANDROID_SDK:-/home/claude/sdk}
B=$SDK/build-tools/34.0.0; AJ=$SDK/platforms/android-34/android.jar
rm -rf build; mkdir -p build/classes build/dex
$B/aapt2 compile --dir res -o build/res.zip
$B/aapt2 link -o build/base.apk -I $AJ --manifest AndroidManifest.xml -A assets build/res.zip --java build/gen
javac -encoding UTF-8 --release 8 -nowarn -classpath $AJ -d build/classes build/gen/com/claude/isekai/R.java $(find src -name '*.java')
$B/d8 --min-api 21 --lib $AJ --output build/dex $(find build/classes -name '*.class')
cp build/base.apk build/unsigned.apk && (cd build/dex && zip -q ../unsigned.apk classes.dex)
$B/zipalign -f 4 build/unsigned.apk build/aligned.apk
$B/apksigner sign --ks release-key.jks --ks-pass pass:android --out build/SecondChance.apk build/aligned.apk
$B/apksigner verify build/SecondChance.apk && echo "OK: build/SecondChance.apk"
