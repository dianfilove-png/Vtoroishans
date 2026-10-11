package com.claude.isekai;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory.Options;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import java.util.Map.Entry;

/* loaded from: classes.dex */
public class GameView extends View implements Runnable {
    static final int BEAST_N = 21;
    static final float BF_BOSS_MAX = 60.0f;
    static final double BF_GOOD = 0.18d;
    static final double BF_PERFECT = 0.09d;
    static final float DEATH_END = 3.8f;
    static final float IMPACT = 1.2f;
    static final int OV_ASC = 7;
    static final int OV_ASC_DONE = 8;
    static final int OV_CONFIRM = 2;
    static final int OV_DAILY = 4;
    static final int OV_DEATH = 6;
    static final int OV_MINI = 9;
    static final int OV_PARTY = 16;
    static final int OV_PORTRAIT = 13;
    static final int OV_REBORN = 3;
    static final int OV_RELIC = 10;
    static final int OV_RELICS = 5;
    static final int OV_SETTINGS = 14;
    static final int OV_STORY = 1;
    static final int OV_SUPER = 11;
    static final int OV_TOWER = 12;
    static final int REL_MAX = 10;
    static final float SUPER_END = 2.6f;
    static final float SUPER_IMPACT = 1.45f;
    static final double WIN_GOOD = 0.14d;
    static final double WIN_PERFECT = 0.075d;
    double BEAT;
    int H;
    double LOOP;
    int W;
    int achMask;
    float actB;
    float actT;
    int[] alt;
    double anchorPos;
    long anchorUp;
    int ascensions;
    final RectF askNo;
    final RectF askYes;
    String banner;
    int bannerColor;
    float bannerT;
    float battleB;
    float battleT;
    final RectF beatBtn;
    float beatPulse;
    int beatTrack;
    int beatWins;
    int bestCombo;
    int bestStage;
    boolean bestiaryView;
    float bfBossHp;
    float bfBossHurt;
    int bfCombo;
    final RectF bfExit;
    int bfGenBeat;
    float bfHeroHp;
    float bfHurt;
    String bfJudge;
    int bfJudgeCol;
    float bfJudgeT;
    float[] bfLaneFlash;
    int bfLastLane;
    double bfNow;
    long bfOpenAt;
    int bfPhase;
    double bfPrevPos;
    final RectF bfRetry;
    boolean bfSilent;
    boolean bfSilentAnnounced;
    double bfStartBeat;
    int bfState;
    String bfTalk;
    float bfTalkT;
    int bfTutorHits;
    double bfUnwrap;
    float bgS;
    long[] bk;
    Bitmap bmBeatmaker;
    Bitmap[] bmBg;
    Bitmap[] bmBoss;
    Bitmap bmCoin;
    Bitmap[] bmComp;
    Bitmap bmCow;
    Bitmap[] bmFairy;
    Bitmap bmFire;
    Bitmap[] bmHero;
    Bitmap bmMana;
    Bitmap bmMimic;
    Bitmap[] bmMob;
    Bitmap bmPiano;
    Bitmap bmPortrait;
    Bitmap[] bmPortraitC;
    Bitmap bmPrincess;
    Bitmap bmSoul;
    Bitmap bmTruck;
    final ArrayList<Fx> bolts;
    final RectF bossBtn;
    float bossIntroT;
    long bossKills;
    boolean bossLanded;
    float bossTime;
    final Paint bp;
    final RectF btn;
    int buyMode;
    long buys;
    int chapterMask;
    boolean chest;
    long chests;
    final ArrayList<Fx> chunks;
    final ArrayList<Fx> coins;
    int combo;
    boolean comboFrozen;
    int[] compLineIdx;
    int compMask;
    int compSel;
    int compStoryMask;
    boolean dailyPending;
    long dailyShownAt;
    String deathCap;
    boolean deathCrash;
    int deathKind;
    float deathT;
    int elFoe;
    float elTime;
    long fairies;
    float fairyDir;
    boolean fairyOn;
    float fairyT;
    float fairyX;
    float fairyY;
    float famShotT;
    final ArrayList<Fx> famShots;
    boolean farming;
    final Paint flashP;
    final ArrayList<Fx> floats;
    long frenzyUntil;
    long giftDay;
    double gold;
    float goldPulse;
    float groundY;
    final Handler handler;
    boolean hasPhones;
    float headerB;
    float heroCast;
    float heroX;
    int hintMask;
    int holdRow;
    long holdStart;
    float hu;
    Bitmap[] icAlt;
    Bitmap icPhones;
    Bitmap[] icUp;
    final RectF isekaiBtn;
    float isekaiGauge;
    boolean isekaiHit;
    float isekaiT;
    long isekais;
    int judgeCol;
    float judgeT;
    String judgeTxt;
    long kills;
    long lastAchCheck;
    long lastDay;
    long lastDayCheck;
    long lastFrame;
    long lastHoldBuy;
    double lastJudgeAt;
    long lastSave;
    int lifeNo;
    int lifeStageIdx;
    double lifetimeGold;
    int[] lvl;
    double mana;
    long meteorReadyAt;
    long meteors;
    String miniMsg;
    int miniMsgColor;
    float miniMsgT;
    int miniPhase;
    String miniReward;
    int miniRound;
    int miniScore;
    float miniT;
    int miniType;
    long minisWon;
    float mobHit;
    float mobR;
    float mobSpawn;
    float mobX;
    float mobY;
    double monsterHp;
    double monsterMax;
    float mu;
    final RectF muteBtn;
    long nextFairy;
    long nextPrincessLine;
    boolean noSave;
    boolean noShatter;
    final ArrayList<Note> notes;
    boolean optMusic;
    boolean optNum;
    boolean optSfx;
    boolean optVib;
    int overlay;
    long overlayAt;
    final Paint p;
    float pad;
    final ArrayList<Fx> parts;
    final RectF partyBtn;
    final RectF[] partyRows;
    final Path path;
    int pendingChapter;
    JSONObject pendingImport;
    double pendingMiniDmg;
    boolean pendingPrincess;
    double pendingSouls;
    final RectF phonesBtn;
    boolean phonesOn;
    Typeface pixTf;
    String portraitLine;
    float portraitPop;
    int portraitTaps;
    int portraitWho;
    final RectF pr;
    final SharedPreferences prefs;
    String princessLine;
    float princessSayT;
    long[] questBase;
    int questClaimed;
    long questDay;
    int[] questId;
    long questsDone;
    final RectF r;
    int[] rel;
    boolean relFly;
    float relFlyT;
    float relFromX;
    float relFromY;
    String relGetFrom;
    int relGetIdx;
    float relGetT;
    final RectF relicBtn;
    boolean relicNew;
    final Random rnd;
    float rowH;
    Bitmap rowIcon;
    int runeIdx;
    int runeLen;
    int runeLit;
    float runeLitT;
    int[] runeSeq;
    int runeShowIdx;
    boolean runeShowing;
    boolean running;
    long scrollAt;
    boolean scrollOn;
    float scrollT;
    int seenOverlay;
    int setAsk;
    long setAskAt;
    String setMsg;
    float setMsgT;
    final RectF[] setRows;
    final Sfx sfx;
    float shake;
    final Paint silP;
    double souls;
    double soulsThisAsc;
    int soundMode;
    final Rect src;
    int stage;
    int stars;
    String[] storyCur;
    int storyPage;
    long storyPageAt;
    boolean storySeen;
    String storyTitle;
    int streak;
    double superDmg;
    boolean superHit;
    boolean superKill;
    int superSlashDone;
    int superStars;
    float superT;
    int superType;
    float swDir;
    float swPos;
    float swSpeed;
    int tab;
    float tabB;
    float tabT;
    long taps;
    float time;
    final RectF towerBtn;
    final RectF towerClose;
    boolean towerFight;
    int towerFloor;
    final RectF towerGo;
    final Paint tp;
    float upT;
    Bitmap victimBmp;
    float victimT;
    float victimX;
    float victimY;
    final Paint vigP;
    float vigT;
    float vigW;
    float zoneL;
    float zoneW;
    static final String[] BEAST_DESC = {"Желе с амбициями. Ест всё, кроме салата.", "Мелкий, жадный, умеет считать до трёх.", "Воет на луну. Луна не отвечает.", "Пьёт молоко. Не помогает.", "Поёт фальшиво и очень громко.", "Сначала бьёт, потом не думает.", "Медленный. Очень. Ещё медленнее.", "Пишет гадости под мостом.", "Дракон на минималках.", "Хранит душу в банке из-под огурцов.", "Корона прилипла навсегда.", "Досчитал до четырёх — стал вождём.", "Знает имя Грузовика-куна.", "Тоже попаданец. Лет триста назад.", "Съел бы и грузовик, но тот невкусный.", "Холоднее только бывшая.", "Поёт лучше гарпий. Ненамного.", "Бюрократ преисподней. Всё по форме.", "Последний рубеж перед дорогой.", "Тот самый. Бип-бип.", "Внутри золото. И зубы."};
    static final String[] EL_ICON = {"🔥", "💧", "⚡", "🌿"};
    static final String[] EL_NAME = {"Огонь", "Вода", "Молния", "Природа"};
    static final String[] EL_FOE = {"Огненный дух", "Водяной", "Грозовик", "Леший"};
    static final int[] EL_COLOR = {-2076614, -12940576, -2047942, -11880352};
    static final String[] MINI_NAME = {"⚔ Удар мечом", "🔮 Руны заклинания", "🌀 Стихии"};
    static final String[] MINI_RULE = {"Тапни, когда метка в зелёной зоне", "Запомни порядок рун и повтори", "Выбери стихию, которая побеждает врага"};
    static final String[][] DEATH_CAPS = {new String[]{"Грузовик-кун снова на работе.", "Классика жанра.", "Опять этот грузовик...", "Он всегда возвращается.", "Надо было смотреть по сторонам."}, new String[]{"Рояль. Серьёзно?", "Ну хоть не грузовик.", "Музыкальная смерть."}, new String[]{"Откуда здесь корова?!", "Корова. С неба. Бывает.", "Му."}};
    static final String[] DEATH_BOOM = {"БДЫЩ!", "БАМ!", "МУУУ!"};
    static final String[] UP_NAME = {"Огненный шар", "Фамилиар", "Медитация", "Древний гримуар", "Благословение удачи"};
    static final boolean[] UP_MANA = {false, false, true, true, false};
    static final int[] UP_COLOR = {-34243, -10825217, -6587393, -11430, -8592501};
    static final String[] ALT_NAME = {"Память прошлой жизни", "Острый глаз", "Ярость предков", "Вечный фамилиар", "Звёздная кузня"};
    static final int OV_BEAT = 15;
    static final int TOWER_TOP = 20;
    static final int[] ALT_MAX = {OV_BEAT, TOWER_TOP, 10, 5, 8};
    static final int[] ALT_COLOR = {-8395009, -42406, -26051, -10825217, -11430};
    static final String[] ACH_NAME = {"Первая кровь", "Убийца боссов", "Гроза королей", "Быстрые пальцы", "Стальные пальцы", "Звездопад", "Друг фей", "Снова в путь", "Колесо сансары", "Полсотни", "Сотня", "Богач", "Охотник за сокровищами", "Верный путник", "Исполнитель", "Коллекционер", "Спаситель принцессы", "Ритм-маг"};
    static final String[] ACH_DESC = {"Убей 10 монстров", "Победи босса", "Победи 25 боссов", "1 000 тапов", "10 000 тапов", "25 метеоров", "Поймай 10 фей", "Переродись", "5 перерождений", "Дойди до этапа 50", "Дойди до этапа 100", "Заработай 1M золота", "Открой 5 сундуков-мимиков", "Серия входов 7 дней", "Выполни 15 заданий", "Найди все 6 реликвий", "Покори вершину башни", "Набери комбо 50"};
    static final String[] QUEST_NAME = {"Сделай %d тапов", "Победи %d монстров", "Победи %d босса", "Запусти %d метеоров", "Поймай %d феи", "Купи %d улучшений", "Открой %d сундук-мимик", "Пройди %d испытания"};
    static final int[] QUEST_GOAL = {500, 150, 3, 8, 2, 40, 1, 2};
    static final int[] QUEST_COLOR = {-1, -10758549, -2078616, -26051, -3416, -10825217, -11430, -5213953};
    static final String[] QUEST_ICON = {"👆", "⚔", "👑", "☄", "✨", "🛒", "💰", "📜"};
    static final String[] REL_NAME = {"Клык дракона", "Песочные часы", "Кошель гнома", "Слеза феи", "Осколок звезды", "Корона мимика"};
    static final String[] REL_ICON = {"🦷", "⏳", "👛", "💧", "⭐", "👑"};
    static final int[] REL_COLOR = {-34243, -16310, -11430, -8395009, -5213953, -2078616};
    static final String[] DAILY_ICON = {"🪙", "💧", "🪙", "🔥", "💧", "🪙", "👻"};
    static final int[] LIFE_AT = {1, 10, 25, 50, 100, 200};
    static final String[] LIFE_NAME = {"Младенец-вундеркинд", "Ученик мага", "Авантюрист", "Маг-рыцарь", "Архимаг", "Легенда мира"};
    static final int[] ROBE = {-4602152, -11568167, -12607894, -5227960, -9679919, -2052032};
    static final String[] MOB = {"Слизень", "Гоблин", "Лесной волк", "Скелет", "Гарпия", "Орк", "Каменный голем", "Тролль", "Виверна", "Лич"};
    static final int[] MOB_COLOR = {-10758549, -7359684, -7696487, -1514801, -3568672, -9728454, -6518156, -10584209, -2992833, -9576240};
    static final String[] BOSS = {"Король слизней", "Вождь гоблинов", "Древний дракон", "Повелитель тьмы", "Пожиратель миров", "Король метели", "Царица бурь", "Демон-генерал", "Император костей", "Грузовик-кун"};
    static final int OV_NONE = 0;
    static final int[] BOSS_TRAIT = {OV_NONE, OV_NONE, 1, 2, 3, 4, 1, 3, 2, 4};
    static final String[] TRAIT_DESC = {"", "♻ Регенерация: лечится 1,5% HP в секунду", "🛡 Барьер: автоурон −75%", "🪨 Каменная кожа: урон тапа −50%", "❄ Метель: времени на бой на 30% меньше"};
    static final String[] TRAIT_SHORT = {"", "♻ регенерация", "🛡 барьер", "🪨 каменная кожа", "❄ метель"};
    static final String[] STORY = {"Всю жизнь ты откладывал на потом.\nДиван, сериалы, «завтра начну»...", "А потом был грузовик.\nКлассика.", "Ты открываешь глаза.\nКрошечные руки. Незнакомое небо.\nИ магия, текущая в крови.", "Второй шанс.\nВ этот раз ты не будешь лениться.\n\nТапай по монстрам!"};
    static final String[] CH_TITLE = {"Глава 1. Первая кровь (ну, слизь)", "Глава 2. Странная карта", "Глава 3. Говорящий дракон", "Глава 4. Тёмная правда", "Глава 5. Трещина в небе", "Глава 6. Следы шин", "Глава 7. Песня сирены", "Глава 8. Армия", "Глава 9. Последний рубеж", "Глава 10. Конец пути?"};
    static final String[][] CH_TEXT = {new String[]{"Король слизней растёкся лужицей.\nДеревенские жители аплодируют.", "Старая гадалка шепчет:\n«Ты не первый, кого сюда занесло.\nГрузовик возит многих...»"}, new String[]{"У гоблина ты находишь карту.\nНа ней нарисован... грузовик?", "Подпись корявым почерком:\n«ОН ВЕРНЁТСЯ ЗА ТОБОЙ».\nЖутковато."}, new String[]{"Умирая, дракон хрипит:\n«Думаешь, это случайность?\nКаждого из нас привёз он...»", "«Владыка дорог. Грузовик-кун.\nОн собирает души героев».\nДракон затих."}, new String[]{"Повелитель тьмы смеётся:\n«Я тоже был попаданцем.\nЛет триста назад».", "«Мы все — его коллекция.\nУмрёшь — он привезёт тебя снова.\nДумаешь, почему ты перерождаешься?»"}, new String[]{"Пожиратель рассыпается в пыль.\nВ небе появляется трещина.", "Сквозь неё доносится звук.\nБи-бип.\nТы вздрагиваешь."}, new String[]{"На снегу — огромные следы шин.\nОни уходят на север, за горизонт.", "«Он проезжал здесь, — говорит путник. —\nКаждые сто лет.\nВсегда по расписанию»."}, new String[]{"Царица бурь поёт на прощание:\n«Найди его. Останови его.\nИли стань им».", "Последнюю часть ты не понял.\nНо звучит эпично."}, new String[]{"Демон-генерал роняет приказ:\n«Задержать героя любой ценой.\nПодпись: Г.-кун».", "У грузовика есть армия.\nИ, похоже, канцелярия."}, new String[]{"Император костей скрипит:\n«Дальше — только дорога.\nИ ОН».", "Вдали загораются фары.\nРевёт двигатель.\nПора."}, new String[]{"Грузовик-кун глохнет.\nВпервые за тысячу лет\nон стоит на месте.", "Из кабины выходит... никто.\nОн всегда ехал сам.\nОдинокий, как ты когда-то.", "Перед тобой — дорога к звёздам.\n\nОткрыто ✦ Вознесение!\n(вкладка «Алтарь»)"}};
    static final String[] TOWER_FOE = {"Каменный страж", "Призрак башни", "Рыцарь-скелет", "Горгулья", "Тёмный маг", "Железный голем", "Химера", "Вампир-лорд", "Костяной дракон", "Хранитель врат"};
    static final String[] PRINCESS_STORY = {"Ты врываешься на вершину башни.\nНа троне сидит принцесса.\nВ наушниках.", "«Ну наконец-то.\nЯ тут триста лет сижу.\nДаже вайфай не ловит».", "«Погоди... от тебя пахнет бензином.\nТоже грузовик, да?\nМда. Классика».", "«Ладно, пойду с тобой.\nНо я буду комментировать всё».\n\n💗 Принцесса присоединилась!\nУрон и золото ×2 навсегда\n(тапни на неё — увидишь портрет)"};
    static final String[] PRINCESS_LINES = {"Скучно...", "Тапай уже!", "Ну такое.", "Опять слизни? Серьёзно?", "Я вообще-то принцесса.", "Мило. Наверное.", "Хочу бабл-ти.", "Это был крит? Неплохо.", "Я бы справилась быстрее.", "Не умирай, мне лень искать нового.", "Опять грузовик снился..."};
    static final String[] PORTRAIT_LINES = {"Чего уставился?", "Да, я милая. Знаю.", "Корона не жмёт, спасибо что спросил.", "Наушники не сниму. Даже не проси.", "Ещё раз ткнёшь — укушу.", "Ладно, ты ничего так. Для попаданца.", "Грузовик-кун мне тоже снился. Бр-р.", "Триста лет в башне. Ты бы тоже стал таким.", "Это мой лучший ракурс, кстати."};
    static final String[] COMP_NAME = {"Принцесса Башни", "Юки", "Бетти", "Сарэн", "Агата"};
    static final String[] COMP_ROLE = {"вредная альтушка", "кицунэ-проказница", "механик-изобретательница", "тёмная эльфийка-лучница", "монахиня-целительница"};
    static final String[] COMP_BONUS = {"Урон и золото ×2", "Шанс крита +5%", "Золото +30%", "Фамилиар бьёт ×1,5", "+5 с на каждого босса"};
    static final String[] COMP_FILE = {"princess", "comp_yuki", "comp_betty", "comp_saren", "comp_agata"};
    static final int[] COMP_ORDER = {1, 2, 3, 4, OV_NONE};
    static final String[][] COMP_LINES = {new String[OV_NONE], new String[]{"Хи-хи, щекотно!", "Девять хвостов? Пока один. Но я расту!", "Феи вкусные? Шучу. Наверное.", "Тапнешь ещё — превращу в лягушку.", "Ты пахнешь бензином. Попаданец, да?", "Крит — это когда я подсказываю, куда бить.", "Хочешь, научу тебя хитрости? Нет? Ну и ладно."}, new String[]{"Не трогай, у меня гаечный ключ в руке!", "Я бы этот грузовик разобрала на запчасти.", "Мимики — просто плохо смазанные сундуки.", "Золото любит счёт. И меня.", "Если что-то взорвалось — это не я.", "Очки не для красоты. Ладно, и для красоты тоже.", "Дай мне час — и твой посох будет с турбонаддувом."}, new String[]{"Не мешай целиться.", "Люди так шумно дышат.", "Твой фамилиар бьёт сильнее тебя. Подумай об этом.", "Триста лет живу — такого попаданца не видела.", "Хм. Неплохой выстрел. Мой.", "Лес шепчет, что ты опять умрёшь. Лес часто прав.", "...ладно, ты не самый худший спутник."}, new String[]{"Да хранит тебя... ой, книжку уронила.", "Я помолюсь, чтобы босс подождал ещё пять секунд!", "Не ругайся при мне, пожалуйста.", "Грузовик-кун — это кара свыше? Надо почитать.", "Перекусим после боя? Я взяла пирожки.", "Ты снова переродился? Поставлю свечку.", "Принцесса опять ворчит? Я принесу ей чаю."}};
    static final String[][] COMP_STORY = {null, new String[]{"Двадцать пятая фея вдруг не улетает.\nОна... чихает. И превращается\nв девушку с лисьими ушками.", "«Хи-хи! Ты поймал меня, попаданец!\nЯ полгода притворялась феей —\nхотела посмотреть, какой ты».", "«Знаешь, кто так долго ловит фей?\nТолько очень упрямые.\nА мне нравятся упрямые».", "«Ладно, пойду с тобой. Буду\nподсказывать, куда бить.\nНо хвост не трогать!»\n\n🦊 Юки присоединилась!\nШанс крита +5%"}, new String[]{"Десятый мимик повержен.\nИз сундука раздаётся\nвозмущённый стук.", "Крышка отлетает. Внутри — девушка\nв гогглах, с гаечным ключом\nи очень недовольным лицом.", "«Я ЧИНИЛА этого мимика! Три дня!\nОн был почти ручной!\nНу всё, теперь ты мне должен».", "«Буду ходить с тобой, пока не отработаешь.\nЗаодно посчитаю твоё золото.\nКто-то же должен».\n\n🔧 Бетти присоединилась!\nЗолото +30%"}, new String[]{"На 75-м этапе в дерево рядом\nс твоей головой вонзается стрела.\nЕщё бы чуть-чуть — и новое перерождение.", "С ветки спрыгивает эльфийка\nс серебряными волосами.\n«Промахнулась. Нарочно».", "«Я наблюдаю за тобой с первого этапа.\nТы умираешь чаще всех, кого я видела.\nЭто даже впечатляет».", "«Пойду с тобой. Должен же кто-то\nприкрывать спину самому бестолковому\nгерою этого мира».\n\n🏹 Сарэн присоединилась!\nФамилиар бьёт ×1,5"}, new String[]{"После двадцать пятого босса\nк тебе подбегает монахиня\nс огромной книгой.", "«Простите! Я из храма за холмом.\nВы так шумно побеждаете боссов,\nчто у нас не слышно молитв».", "«Настоятельница велела присмотреть,\nчтобы вы не разрушили что-нибудь ещё.\nИ кормить вас. Вы ужасно худой».", "«Я помолюсь, чтобы каждый босс\nждал вас чуть дольше. Это работает,\nчестное слово!»\n\n📖 Агата присоединилась!\n+5 секунд на каждого босса"}};
    static final String[] EVENT_NAME = {"💰 Золотая лихорадка", "📦 Неделя мимиков", "✨ Нашествие фей", "☄ Звездопад", "🎒 Охота за реликвиями"};
    static final String[] EVENT_DESC = {"всё золото ×2", "мимики вдвое чаще", "феи прилетают в 3 раза чаще", "метеор вдвое дешевле и быстрее", "реликвии падают вдвое чаще"};
    static final int[] BELL_STEPS = {OV_NONE, 4, 7, 9, 12, 9, 7, 4, 2, 4, 7, 12};
    static final byte[] BM_KICK = {0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 3, 0, 0, 1, 0, 1, 0, 3, 0, 2, 0, 1, 0, 0};
    static final byte[] BM_SNARE = {0, 1, 0, 0, 2, 3, 0, 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0};
    static final byte[] BM_HAT = {2, 1, 2, 1, 2, 2, 3, 0, 1, 2, 1, 0, 3, 1, 2, 0, 3, 2, 3, 1, 2, 2, 3, 1, 2, 0, 2, 0, 3, 1, 1, 1};
    static final String[] BF_TAUNT = {"Мимо бита, лол", "Это что, джаз?", "Медленно!", "Ты точно слышишь музыку?", "Бит не ждёт!"};
    static final String[] BUY_LABEL = {"x1", "x10", "MAX"};

    static class Fx {
        int color;
        float life;
        float max;
        float size;
        String text;
        float vx;
        float vy;
        float x;
        float y;

        Fx() {
        }
    }

    static class Note {
        double beat;
        float hitT;
        int lane;
        int state;
        double travel;
        int type;

        Note() {
        }
    }

    public GameView(Context context) {
        super(context);
        this.stage = 1;
        this.lifeNo = 1;
        this.bestStage = 1;
        this.lvl = new int[5];
        this.alt = new int[5];
        this.lifeStageIdx = OV_NONE;
        this.meteorReadyAt = 0L;
        this.farming = false;
        this.bossTime = 0.0f;
        this.frenzyUntil = 0L;
        this.chest = false;
        this.questId = new int[3];
        this.questBase = new long[3];
        this.soundMode = OV_NONE;
        this.optMusic = true;
        this.optSfx = true;
        this.optVib = true;
        this.optNum = true;
        this.noSave = false;
        this.setAsk = OV_NONE;
        this.setMsg = "";
        this.setMsgT = 0.0f;
        this.setRows = new RectF[7];
        this.askYes = new RectF();
        this.askNo = new RectF();
        this.bk = new long[BEAST_N];
        this.bestiaryView = false;
        this.silP = new Paint();
        this.rel = new int[6];
        this.relicNew = false;
        this.famShotT = 0.0f;
        this.hintMask = OV_NONE;
        this.seenOverlay = OV_NONE;
        this.miniMsg = "";
        this.runeSeq = new int[5];
        this.runeLit = -1;
        this.storyCur = STORY;
        this.storyTitle = null;
        this.chapterMask = OV_NONE;
        this.pendingChapter = -1;
        this.stars = OV_NONE;
        this.ascensions = OV_NONE;
        this.soulsThisAsc = 0.0d;
        this.towerFloor = OV_NONE;
        this.towerFight = false;
        this.pendingPrincess = false;
        this.princessSayT = 0.0f;
        this.princessLine = "";
        this.compSel = -1;
        this.compMask = OV_NONE;
        this.compStoryMask = OV_NONE;
        this.portraitWho = OV_NONE;
        this.compLineIdx = new int[5];
        this.bmPortraitC = new Bitmap[5];
        this.bmComp = new Bitmap[5];
        this.partyBtn = new RectF();
        this.partyRows = new RectF[5];
        this.portraitTaps = OV_NONE;
        this.portraitLine = "";
        this.portraitPop = 0.0f;
        this.giftDay = -1L;
        this.handler = new Handler(Looper.getMainLooper());
        this.rnd = new Random();
        this.bossBtn = new RectF();
        this.muteBtn = new RectF();
        this.relicBtn = new RectF();
        this.towerBtn = new RectF();
        this.towerGo = new RectF();
        this.towerClose = new RectF();
        this.p = new Paint(1);
        this.tp = new Paint(1);
        this.r = new RectF();
        this.path = new Path();
        this.floats = new ArrayList<>();
        this.parts = new ArrayList<>();
        this.chunks = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.goldPulse = 0.0f;
        this.bossIntroT = 0.0f;
        this.bossLanded = true;
        this.BEAT = 0.5357142857142857d;
        this.LOOP = 68.57142857142857d;
        this.beatTrack = -2;
        this.anchorUp = 0L;
        this.anchorPos = -1.0d;
        this.beatPulse = 0.0f;
        this.combo = OV_NONE;
        this.bestCombo = OV_NONE;
        this.lastJudgeAt = 0.0d;
        this.comboFrozen = false;
        this.judgeT = 0.0f;
        this.judgeTxt = "";
        this.isekaiGauge = 0.0f;
        this.isekaiT = -1.0f;
        this.victimT = -1.0f;
        this.isekaiBtn = new RectF();
        this.isekais = 0L;
        this.hasPhones = false;
        this.phonesOn = false;
        this.beatWins = OV_NONE;
        this.beatBtn = new RectF();
        this.phonesBtn = new RectF();
        this.bfRetry = new RectF();
        this.bfExit = new RectF();
        this.bfLastLane = 1;
        this.bfTutorHits = OV_NONE;
        this.bfLaneFlash = new float[3];
        this.notes = new ArrayList<>();
        this.bfTalk = "";
        this.bfJudge = "";
        this.icUp = new Bitmap[5];
        this.icAlt = new Bitmap[5];
        this.pixTf = Typeface.DEFAULT_BOLD;
        this.pr = new RectF();
        this.vigP = new Paint();
        this.vigW = -1.0f;
        this.vigT = -1.0f;
        this.bolts = new ArrayList<>();
        this.famShots = new ArrayList<>();
        this.mobHit = 0.0f;
        this.mobSpawn = 1.0f;
        this.heroCast = 0.0f;
        this.shake = 0.0f;
        this.time = 0.0f;
        this.banner = null;
        this.bannerT = 0.0f;
        this.bannerColor = -5720;
        this.fairyOn = false;
        this.fairyT = 0.0f;
        this.fairyDir = 1.0f;
        this.overlay = OV_NONE;
        this.storyPage = OV_NONE;
        this.tab = OV_NONE;
        this.buyMode = OV_NONE;
        this.holdRow = -1;
        this.bmHero = new Bitmap[6];
        this.bmMob = new Bitmap[10];
        this.bmBoss = new Bitmap[10];
        this.bmBg = new Bitmap[3];
        this.bmFairy = new Bitmap[2];
        this.bp = new Paint();
        this.flashP = new Paint();
        this.src = new Rect();
        this.miniReward = "";
        this.pendingMiniDmg = 0.0d;
        this.relGetFrom = "";
        this.btn = new RectF();
        this.prefs = context.getSharedPreferences("isekai", OV_NONE);
        try {
            this.pixTf = Typeface.createFromAsset(context.getAssets(), "fonts/pixel.ttf");
        } catch (Exception e) {
        }
        this.silP.setColorFilter(new PorterDuffColorFilter(-12963760, PorterDuff.Mode.SRC_IN));
        this.silP.setFilterBitmap(false);
        int i = OV_NONE;
        while (true) {
            RectF[] rectFArr = this.setRows;
            if (i >= rectFArr.length) {
                break;
            }
            rectFArr[i] = new RectF();
            i++;
        }
        this.sfx = new Sfx(context);
        loadArt();
        load();
        if (!this.storySeen) {
            this.overlay = 1;
            this.storyPage = OV_NONE;
        }
        this.nextFairy = SystemClock.uptimeMillis() + 40000;
    }

    static String fmt(double d) {
        double d2 = d;
        if (Double.isNaN(d2) || d2 < 1000.0d) {
            return (d2 >= 10.0d || d2 == Math.floor(d)) ? String.valueOf((long) d2) : String.format("%.1f", Double.valueOf(d));
        }
        String[] strArr = {"K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp", "Oc", "No", "Dc"};
        int i = -1;
        while (d2 >= 1000.0d && i < 10) {
            d2 /= 1000.0d;
            i++;
        }
        return String.format(d2 < 10.0d ? "%.2f%s" : d2 < 100.0d ? "%.1f%s" : "%.0f%s", Double.valueOf(d2), strArr[i]);
    }

    static double ms(int i) {
        return Math.pow(2.0d, i / 25);
    }

    static String roman(int i) {
        return i <= 10 ? new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}[i] : String.valueOf(i);
    }

    static double soulPart(double d) {
        return Math.pow((d * 0.15d) + 1.0d, 0.8d);
    }

    static double starPart(int i) {
        return Math.pow((i * 0.5d) + 1.0d, 2.0d);
    }

    static long today() {
       long var0 = System.currentTimeMillis();
       return (var0 + (long)TimeZone.getDefault().getOffset(var0)) / 86400000L;
    }

    boolean achDone(int i) {
        switch (i) {
            case OV_NONE /* 0 */:
                return this.kills >= 10;
            case 1:
                return this.bossKills >= 1;
            case 2:
                return this.bossKills >= 25;
            case 3:
                return this.taps >= 1000;
            case 4:
                return this.taps >= 10000;
            case 5:
                return this.meteors >= 25;
            case 6:
                return this.fairies >= 10;
            case 7:
                return this.lifeNo >= 2;
            case 8:
                return this.lifeNo >= 6;
            case 9:
                return this.bestStage >= 50;
            case Sfx.CRASH /* 10 */:
                return this.bestStage >= 100;
            case 11:
                return this.lifetimeGold >= 1000000.0d;
            case 12:
                return this.chests >= 5;
            case OV_PORTRAIT /* 13 */:
                return this.streak >= 7;
            case OV_SETTINGS /* 14 */:
                return this.questsDone >= 15;
            case OV_BEAT /* 15 */:
                int[] iArr = this.rel;
                int length = iArr.length;
                for (int i2 = OV_NONE; i2 < length; i2++) {
                    if (iArr[i2] == 0) {
                        return false;
                    }
                }
                return true;
            case OV_PARTY /* 16 */:
                return princess();
            default:
                return this.bestCombo >= 50;
        }
    }

    double achMult() {
        return ((Integer.bitCount(this.achMask) * 0.05d) + 1.0d) * beastMult();
    }

    void addFloat(float f, float f2, String str, int i, float f3) {
        if (this.optNum || !(i == -1 || i == -42406)) {
            int i2 = i == -1 ? 4 : i == -42406 ? 2 : 3;
            Iterator<Fx> it = this.floats.iterator();
            int i3 = OV_NONE;
            while (it.hasNext()) {
                if (it.next().color == i) {
                    i3++;
                }
            }
            if (i3 >= i2) {
                int i4 = OV_NONE;
                while (true) {
                    if (i4 >= this.floats.size()) {
                        break;
                    }
                    if (this.floats.get(i4).color == i) {
                        this.floats.remove(i4);
                        break;
                    }
                    i4++;
                }
            }
            Fx fx = new Fx();
            fx.x = f + ((this.rnd.nextFloat() - 0.5f) * this.mobR * 1.8f);
            fx.y = f2 + ((this.rnd.nextFloat() - 0.5f) * this.mobR * 0.4f);
            fx.vy = (-this.H) * 0.15f;
            fx.max = i == -1 ? 0.6f : 0.9f;
            fx.text = str;
            fx.color = i;
            fx.size = f3;
            this.floats.add(fx);
            if (this.floats.size() > 12) {
                this.floats.remove(OV_NONE);
            }
        }
    }

    boolean allQuestsClaimed() {
        return (this.questClaimed & 7) == 7;
    }

    double altCost(int i) {
        return Math.ceil(new double[]{5.0d, 3.0d, 4.0d, 6.0d, 4.0d}[i] * Math.pow(new double[]{1.8d, 1.6d, 1.7d, 2.0d, 1.7d}[i], this.alt[i]));
    }

    String altEffect(int i) {
        switch (i) {
            case OV_NONE /* 0 */:
                return "Старт с этапа " + startStage();
            case 1:
                return "Шанс крита " + critChance() + "%";
            case 2:
                return "Время на босса " + ((int) bossLimit()) + "с";
            case 3:
                return "Офлайн-доход " + ((int) Math.round(offlineRate() * 100.0d)) + "%";
            default:
                return "Метеор ×" + fmt(meteorPower()) + ", откат " + (((double) meteorCd()) / 1000.0d > 9.95d ? String.valueOf((int) (meteorCd() / 1000)) : String.format("%.1f", Double.valueOf(meteorCd() / 1000.0d))) + "с";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    void applyCode(JSONObject jSONObject) {
        SharedPreferences.Editor edit = this.prefs.edit();
        edit.clear();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = jSONObject.optString(next);
            int indexOf = optString.indexOf(58);
            if (indexOf >= 0) {
                char c = 0;
                String substring = optString.substring(OV_NONE, indexOf);
                String substring2 = optString.substring(indexOf + 1);
                try {
                    switch (substring.hashCode()) {
                        case 98:
                            if (substring.equals("b")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 102:
                            if (substring.equals("f")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 105:
                            if (substring.equals("i")) {
                                break;
                            }
                            c = 65535;
                            break;
                        case 108:
                            if (substring.equals("l")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case OV_NONE /* 0 */:
                            edit.putInt(next, Integer.parseInt(substring2));
                            continue;
                        case 1:
                            edit.putLong(next, Long.parseLong(substring2));
                            continue;
                        case 2:
                            edit.putFloat(next, Float.parseFloat(substring2));
                            continue;
                        case 3:
                            edit.putBoolean(next, Boolean.parseBoolean(substring2));
                            continue;
                        default:
                            edit.putString(next, substring2);
                            continue;
                    }
                } catch (Exception e) {
                }
            }
        }
        edit.commit();
        restartGame();
    }

    void applyMini() {
        int i = this.miniScore;
        if (i == 0) {
            this.miniReward = "Не вышло... Повезёт в следующий раз!";
            return;
        }
        this.minisWon++;
        StringBuilder sb = new StringBuilder();
        if (!bossFight() || this.monsterHp <= 0.0d) {
            double d = i;
            double goldFor = goldFor(this.stage) * 12.0d * d;
            double manaTap = manaTap() * 25.0d * d;
            this.gold += goldFor;
            this.lifetimeGold += goldFor;
            this.mana += manaTap;
            sb.append("+").append(fmt(goldFor)).append(" 🪙  +").append(fmt(manaTap)).append(" 💧");
        } else {
            double min = Math.min(2.0d, ((comboMult() - 1.0d) * 0.5d) + 1.0d);
            double d2 = this.monsterMax * 0.1d * i * min;
            sb.append("Удар по боссу: −").append(fmt(d2)).append(" HP");
            if (this.combo >= 2) {
                sb.append("\nКомбо ").append(this.combo).append(" усилило удар ×").append(String.format(Locale.US, "%.2f", Double.valueOf(min)));
            }
            this.pendingMiniDmg = d2;
        }
        int i2 = i * 10;
        this.isekaiGauge = Math.min(100.0f, this.isekaiGauge + i2);
        sb.append("\n🚚 Грузовик-кун +").append(i2).append("% заряда");
        if (i == 3) {
            this.frenzyUntil = Math.max(this.frenzyUntil, SystemClock.uptimeMillis()) + 15000;
            sb.append("\nБез ошибок: Ярость +15с!");
        }
        this.miniReward = sb.toString();
    }

    void applySound() {
        this.sfx.muted = !this.optSfx;
        this.sfx.musicOn = this.optMusic;
        this.sfx.vibOn = this.optVib;
    }

    boolean ascUnlocked() {
        return this.bestStage > 100 || this.stars > 0;
    }

    void ascend() {
        this.stars += starsGain();
        this.ascensions++;
        this.souls = 0.0d;
        this.soulsThisAsc = 0.0d;
        int i = OV_NONE;
        while (true) {
            int[] iArr = this.alt;
            if (i >= iArr.length) {
                break;
            }
            iArr[i] = OV_NONE;
            i++;
        }
        this.lifeNo++;
        this.chest = false;
        this.towerFight = false;
        this.gold = 0.0d;
        this.mana = 0.0d;
        this.lifeStageIdx = OV_NONE;
        this.farming = false;
        this.frenzyUntil = 0L;
        int i2 = OV_NONE;
        while (true) {
            int[] iArr2 = this.lvl;
            if (i2 >= iArr2.length) {
                this.stage = 1;
                updateLifeStage(false);
                spawn();
                this.floats.clear();
                this.parts.clear();
                this.chunks.clear();
                this.sfx.play(7, 1.0f);
                this.sfx.buzz(400L);
                this.overlay = 8;
                save();
                return;
            }
            iArr2[i2] = OV_NONE;
            i2++;
        }
    }

    double autoDps() {
       int var9 = this.lvl[1];
       double var3 = (double)(var9 * 3);
       double var5 = Math.pow(1.04, (double)var9);
       double var7 = ms(this.lvl[1]);
       double var1;
       if (this.compUnlocked(3)) {
          var1 = 1.5;
       } else {
          var1 = 1.0;
       }

       return var3 * var5 * var7 * var1 * this.grimMult() * this.soulMult() * this.achMult() * this.frenzy();
    }

    double[] batch(int i) {
        int i2 = this.buyMode;
        int i3 = i2 == 0 ? 1 : i2 == 1 ? 10 : 1000;
        double wallet = wallet(i);
        double d = 0.0d;
        int i4 = OV_NONE;
        while (i4 < i3) {
            double costAt = costAt(i, this.lvl[i] + i4);
            if (this.buyMode == 2 && d + costAt > wallet) {
                break;
            }
            d += costAt;
            i4++;
        }
        return i4 == 0 ? new double[]{0.0d, costAt(i, this.lvl[i])} : new double[]{i4, d};
    }

    Bitmap beastBmp(int i) {
        return i < 10 ? this.bmMob[i] : i < TOWER_TOP ? this.bmBoss[i - 10] : this.bmMimic;
    }

    double beastMult() {
        return (beastsStudied() * 0.03d) + 1.0d;
    }

    String beastName(int i) {
        return i < 10 ? MOB[i] : i < TOWER_TOP ? BOSS[i - 10] : "Сундук-мимик";
    }

    int beastNeed(int i) {
        if (i < 10) {
            return 100;
        }
        return i < TOWER_TOP ? 3 : 5;
    }

    int beastsStudied() {
        int i = OV_NONE;
        for (int i2 = OV_NONE; i2 < BEAST_N; i2++) {
            if (this.bk[i2] >= beastNeed(i2)) {
                i++;
            }
        }
        return i;
    }

    void beatFightWon() {
        this.bfState = 2;
        this.notes.clear();
        this.bfOpenAt = SystemClock.uptimeMillis();
        bfSay("Ладно... ты чувствуешь музыку. Держи мои наушники.");
        this.shake = 1.0f;
        this.sfx.play(3, 1.0f);
        this.sfx.play(7, 0.8f);
        this.sfx.buzz(200L);
        this.beatWins++;
        boolean z = this.hasPhones;
        double d = z ? 3.0d : 10.0d;
        this.souls += d;
        this.soulsThisAsc += d;
        if (!z) {
            this.hasPhones = true;
            this.phonesOn = true;
        }
        save();
    }

    double beatPos() {
        long uptimeMillis = SystemClock.uptimeMillis();
        double musicPosSec = this.sfx.musicPosSec();
        int playingTrack = this.sfx.playingTrack();
        if (playingTrack != this.beatTrack) {
            this.beatTrack = playingTrack;
            this.anchorPos = -1.0d;
        }
        if (musicPosSec < 0.0d) {
            this.anchorPos = -1.0d;
            return uptimeMillis / 1000.0d;
        }
        double d = musicPosSec - 0.04d;
        double d2 = this.anchorPos;
        if (d2 < 0.0d) {
            this.anchorPos = d;
            this.anchorUp = uptimeMillis;
            return d;
        }
        double d3 = d2 + ((uptimeMillis - this.anchorUp) / 1000.0d);
        double d4 = d - d3;
        if (Math.abs(d4 - (Math.rint(d4 / this.LOOP) * this.LOOP)) <= 0.045d) {
            return d3;
        }
        this.anchorPos = d;
        this.anchorUp = uptimeMillis;
        return d;
    }

    void beatTap(float f, float f2, boolean z) {
        int floor;
        long uptimeMillis = SystemClock.uptimeMillis();
        int i = this.bfState;
        if (i == 2) {
            if (!z || uptimeMillis - this.bfOpenAt <= 1200) {
                return;
            }
            this.overlay = OV_NONE;
            this.sfx.setDuck(1.0f);
            return;
        }
        if (i == 3) {
            if (!z || uptimeMillis - this.bfOpenAt < 600) {
                return;
            }
            if (this.bfRetry.contains(f, f2)) {
                startBeatFight();
                return;
            } else {
                if (this.bfExit.contains(f, f2)) {
                    this.overlay = OV_NONE;
                    this.sfx.setDuck(1.0f);
                    return;
                }
                return;
            }
        }
        if (z && this.bfExit.contains(f, f2)) {
            this.overlay = OV_NONE;
            this.sfx.setDuck(1.0f);
            this.notes.clear();
            return;
        }
        if (this.bfState == 1) {
            if (f2 >= bfTopY() - (this.pad * 2.0f) && (floor = (int) Math.floor((f - bfLaneL()) / bfLaneW())) >= 0 && floor <= 2) {
                this.bfLaneFlash[floor] = 1.0f;
                Iterator<Note> it = this.notes.iterator();
                Note note = null;
                double d = 9.0d;
                while (it.hasNext()) {
                    Note next = it.next();
                    if (next.state == 0 && next.lane == floor) {
                        double abs = Math.abs(next.beat - this.bfNow) * this.BEAT;
                        if (abs < d) {
                            note = next;
                            d = abs;
                        }
                    }
                }
                if (note == null || d > BF_GOOD) {
                    bfJudgeSet("мимо", -7569238);
                    return;
                }
                note.state = 1;
                note.hitT = 0.0f;
                if (note.type == 1) {
                    this.bfHeroHp -= 15.0f;
                    this.bfHurt = 1.0f;
                    this.bfCombo = OV_NONE;
                    this.shake = 0.8f;
                    bfJudgeSet("БОМБА!", -46518);
                    this.sfx.play(10, 0.7f);
                    this.sfx.buzz(150L);
                    bfSay("Бум! Я же предупреждал 😎");
                    return;
                }
                boolean z2 = d <= BF_PERFECT;
                this.bfBossHp -= z2 ? 2.0f : 1.3f;
                this.bfBossHurt = 1.0f;
                this.bfCombo++;
                this.bfTutorHits++;
                this.bfHeroHp = Math.min(100.0f, this.bfHeroHp + 1.5f);
                bfJudgeSet(z2 ? "ИДЕАЛЬНО!" : "ХОРОШО", z2 ? -11430 : -6295297);
                this.sfx.playHit(this.bfCombo, z2, beatPos());
                int i2 = this.bfCombo;
                if (i2 == 10 || i2 == 25) {
                    bfSay(i2 == 10 ? "Хм. Неплохо." : "Э-э-э, полегче!");
                }
            }
        }
    }

    boolean beatUnlocked() {
        return this.bestStage >= 25 || this.hasPhones;
    }

    void bfAddNote(double d, int i, double d2, int i2) {
        Note note = new Note();
        note.beat = d;
        note.type = i;
        note.travel = d2;
        note.lane = i2;
        this.notes.add(note);
    }

    double bfBeats() {
        double beatPos = beatPos();
        double d = this.bfPrevPos;
        if (d >= 0.0d) {
            double d2 = this.LOOP;
            if (beatPos < d - (d2 / 2.0d)) {
                this.bfUnwrap += d2;
            }
        }
        if (d >= 0.0d) {
            double d3 = this.LOOP;
            if (beatPos > d + (d3 / 2.0d)) {
                this.bfUnwrap -= d3;
            }
        }
        this.bfPrevPos = beatPos;
        return (beatPos + this.bfUnwrap) / this.BEAT;
    }

    float bfHitY() {
        return this.H * 0.79f;
    }

    void bfJudgeSet(String str, int i) {
        this.bfJudge = str;
        this.bfJudgeCol = i;
        this.bfJudgeT = 0.6f;
    }

    float bfLaneL() {
        return this.W * 0.06f;
    }

    float bfLaneW() {
        return (this.W * 0.88f) / 3.0f;
    }

    int bfPickLane(Random random) {
        int nextInt = random.nextInt(3);
        if (nextInt == this.bfLastLane && random.nextFloat() < 0.6f) {
            nextInt = ((nextInt + 1) + random.nextInt(2)) % 3;
        }
        this.bfLastLane = nextInt;
        return nextInt;
    }

    void bfSay(String str) {
        this.bfTalk = str;
        this.bfTalkT = 2.5f;
    }

    float bfTopY() {
        return this.H * 0.3f;
    }

    int biome() {
        return ((this.stage - 1) / 30) % 3;
    }

    void blit(Canvas canvas, Bitmap bitmap, float f, float f2, float f3, float f4, float f5, Paint paint) {
        float width = ((bitmap.getWidth() * f3) * f4) / 2.0f;
        this.r.set(f - width, f2 - ((bitmap.getHeight() * f3) * f5), f + width, f2);
        this.src.set(OV_NONE, OV_NONE, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, this.src, this.r, paint);
    }

    Bitmap bm(String str) {
        int identifier = getResources().getIdentifier(str, "drawable", getContext().getPackageName());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        return BitmapFactory.decodeResource(getResources(), identifier, options);
    }

    void bossFailed() {
        if (this.towerFight) {
            towerLose();
            return;
        }
        this.stage--;
        this.farming = true;
        spawn();
        showBanner("Босс слишком силён! Прокачайся и вызови снова", -30070);
        this.sfx.play(8, 0.8f);
        this.sfx.buzz(200L);
    }

    boolean bossFight() {
        return this.towerFight || isBoss(this.stage);
    }

    int bossIdx() {
        return Math.max(OV_NONE, ((this.stage / 10) - 1) % 10);
    }

    float bossLimit() {
        return (this.alt[2] * 5) + 30 + (this.rel[1] * 2) + (compUnlocked(4) ? 5 : OV_NONE);
    }

    float bossTimeMax() {
        return bossLimit() * (trait() == 4 ? 0.7f : 1.0f);
    }

    void burst(int i, int i2) {
        burstAt(this.mobX, this.mobY, i, i2);
    }

    void burstAt(float f, float f2, int i, int i2) {
        float f3 = this.W / 1080.0f;
        for (int i3 = OV_NONE; i3 < i; i3++) {
            Fx fx = new Fx();
            fx.x = f;
            fx.y = f2;
            double nextDouble = this.rnd.nextDouble() * 3.141592653589793d * 2.0d;
            double nextDouble2 = ((this.rnd.nextDouble() * 500.0d) + 200.0d) * f3;
            fx.vx = (float) (Math.cos(nextDouble) * nextDouble2);
            fx.vy = ((float) (Math.sin(nextDouble) * nextDouble2)) - (300.0f * f3);
            fx.max = (this.rnd.nextFloat() * 0.4f) + 0.6f;
            fx.size = this.pad * ((this.rnd.nextFloat() * 0.6f) + 0.4f);
            fx.color = this.rnd.nextInt(3) == 0 ? -11430 : i2;
            this.parts.add(fx);
        }
        while (this.parts.size() > 250) {
            this.parts.remove(OV_NONE);
        }
    }

    void catchFairy() {
        this.fairyOn = false;
        this.nextFairy = SystemClock.uptimeMillis() + fairyGap();
        this.fairies++;
        this.sfx.play(6, 1.0f);
        this.sfx.buzz(40L);
        int nextInt = this.rnd.nextInt(3);
        if (nextInt == 0) {
            double max = Math.max(goldFor(this.stage) * 15.0d, ((autoDps() * 60.0d) / hpFor(this.stage)) * goldFor(this.stage));
            this.gold += max;
            this.lifetimeGold += max;
            showBanner("✨ Фея дарит " + fmt(max) + " золота!", -11430);
        } else if (nextInt == 1) {
            double manaSec = (manaSec() * 60.0d) + (manaTap() * 150.0d);
            this.mana += manaSec;
            showBanner("✨ Фея дарит " + fmt(manaSec) + " маны!", -4609793);
        } else {
            this.frenzyUntil = Math.max(this.frenzyUntil, SystemClock.uptimeMillis()) + 30000;
            showBanner("✨ Ярость феи: урон ×3 на 30 секунд!", -25990);
        }
        for (int i = OV_NONE; i < 25; i++) {
            Fx fx = new Fx();
            fx.x = this.fairyX;
            fx.y = this.fairyY;
            double nextDouble = this.rnd.nextDouble() * 3.141592653589793d * 2.0d;
            double nextDouble2 = (this.W / 1080.0f) * 300.0f * this.rnd.nextDouble();
            fx.vx = (float) (Math.cos(nextDouble) * nextDouble2);
            fx.vy = (float) (Math.sin(nextDouble) * nextDouble2);
            fx.max = 0.8f;
            fx.size = this.pad * 0.5f;
            fx.color = -3416;
            this.parts.add(fx);
        }
    }

    void checkAch() {
        int i = OV_NONE;
        while (true) {
            String[] strArr = ACH_NAME;
            if (i >= strArr.length) {
                return;
            }
            int i2 = 1 << i;
            if ((this.achMask & i2) == 0 && achDone(i)) {
                this.achMask |= i2;
                showBanner("🏆 Достижение: " + strArr[i] + " (+5% урона)", -11430);
                this.sfx.play(3, 0.7f);
                return;
            }
            i++;
        }
    }

    void checkComps() {
        for (int i = OV_NONE; i < 5; i++) {
            if (compUnlocked(i)) {
                int i2 = this.compMask;
                int i3 = 1 << i;
                if ((i2 & i3) == 0) {
                    this.compMask = i2 | i3;
                    if (i != 0) {
                        showBanner("👭 Новая спутница: " + COMP_NAME[i] + "! " + COMP_BONUS[i], -25909);
                    }
                    if (this.compSel < 0) {
                        this.compSel = i;
                    }
                    save();
                }
            }
        }
    }

    void checkDay() {
        long j = today();
        long j2 = this.lastDay;
        if (j != j2) {
            this.streak = j2 == j - 1 ? this.streak + 1 : 1;
            this.lastDay = j;
            this.dailyPending = true;
        }
        if (this.questDay != j) {
            newQuests(j);
        }
    }

    void checkHints() {
        if (this.bannerT > 0.3f) {
            return;
        }
        if (this.lvl[OV_NONE] == 0 && this.taps >= 5 && this.gold >= costAt(OV_NONE, OV_NONE)) {
            showHint(OV_NONE, "Купи «Огненный шар» внизу — урон тапа вырастет");
            return;
        }
        if (this.meteors == 0 && this.mana >= meteorCost() && this.stage >= 3) {
            showHint(1, "Хватает маны на ☄ Метеор — жми кнопку под полем боя");
            return;
        }
        if (this.lvl[1] == 0 && this.stage >= 5 && this.gold >= costAt(1, OV_NONE)) {
            showHint(3, "Найми «Фамилиара» — он бьёт сам, даже когда ты не в игре");
            return;
        }
        if (this.lifeNo >= 2) {
            int[] iArr = this.alt;
            if (iArr[OV_NONE] + iArr[1] + iArr[2] + iArr[3] + iArr[4] == 0 && this.souls >= 3.0d) {
                showHint(4, "Вкладка «Алтарь»: обменяй души на вечные умения");
                return;
            }
        }
        if (towerUnlocked() && this.towerFloor == 0 && !this.towerFight) {
            showHint(9, "🏰 Открылась Башня Принцессы! Кнопка слева на поле боя");
            return;
        }
        int i = this.compSel;
        if (i > 0 && compUnlocked(i)) {
            showHint(OV_PORTRAIT, "👭 С тобой спутница! Тапни на неё — поболтаете. Выбрать другую — кнопка 👭 вверху");
            return;
        }
        if (beatUnlocked() && !this.hasPhones) {
            showHint(12, "🎧 Где-то гремит музыка... Жми 🎧 на поле боя — вызови Битмейкера на ритм-дуэль!");
            return;
        }
        if (rhythmOn() && this.taps >= 40) {
            showHint(11, "🎵 Тапай В РИТМ — когда кольцо сжимается к монстру. Комбо умножает ВЕСЬ урон!");
            return;
        }
        if (this.lvl[3] == 0 && this.mana >= costAt(3, OV_NONE) && this.stage >= 8) {
            showHint(10, "📕 Древний гримуар умножает ВЕСЬ урон ×1,6 за уровень — не копи ману, покупай!");
            return;
        }
        int i2 = this.lvl[OV_NONE];
        if (i2 < TOWER_TOP || i2 >= 25) {
            return;
        }
        showHint(5, "Каждые 25 уровней улучшение даёт веху: эффект ×2");
    }

    int chestChance() {
        return Math.min(12, (this.rel[5] + 4) * (eventIdx() == 1 ? 2 : 1));
    }

    void claimDaily() {
        int dailyDay = dailyDay();
        switch (dailyDay) {
            case 1:
            case 3:
            case 6:
                double goldFor = goldFor(this.stage) * (dailyDay == 1 ? TOWER_TOP : dailyDay == 3 ? 50 : 100);
                this.gold += goldFor;
                this.lifetimeGold += goldFor;
                break;
            case 2:
                this.mana += (manaTap() * 150.0d) + (manaSec() * 90.0d);
                break;
            case 4:
                this.frenzyUntil = Math.max(this.frenzyUntil, SystemClock.uptimeMillis()) + 60000;
                break;
            case 5:
                this.mana += ((manaTap() * 150.0d) + (manaSec() * 90.0d)) * 3.0d;
                break;
            default:
                this.souls += (this.bestStage / 30) + 2;
                break;
        }
        showBanner("🎁 " + dailyDesc(dailyDay).replace("+", "Получено: +"), -11430);
        this.dailyPending = false;
        this.sfx.play(7, 0.8f);
        this.sfx.buzz(60L);
        save();
    }

    void closeMini() {
        if (this.pendingMiniDmg <= 0.0d || !bossFight()) {
            this.pendingMiniDmg = 0.0d;
            this.overlay = OV_NONE;
            return;
        }
        this.superDmg = this.pendingMiniDmg;
        this.pendingMiniDmg = 0.0d;
        this.superType = this.miniType;
        this.superStars = this.miniScore;
        this.superT = 0.0f;
        this.superSlashDone = OV_NONE;
        this.superKill = false;
        this.superHit = false;
        this.overlay = 11;
        this.sfx.play(7, 0.7f);
    }

    String codeInfo(JSONObject jSONObject) {
        String optString = jSONObject.optString("stage", "i:?");
        String optString2 = jSONObject.optString("life", "i:?");
        return "этап " + optString.substring(optString.indexOf(58) + 1) + ", жизнь №" + optString2.substring(optString2.indexOf(58) + 1);
    }

    double comboMult() {
        if (rhythmOn()) {
            return 1.0d + (Math.min(this.combo, 50) * 0.04d);
        }
        return 1.0d;
    }

    String compLine(int i) {
        if (i == 0) {
            String[] strArr = PORTRAIT_LINES;
            return strArr[this.rnd.nextInt(strArr.length)];
        }
        String[] strArr2 = COMP_LINES[i];
        int[] iArr = this.compLineIdx;
        int i2 = iArr[i];
        iArr[i] = i2 + 1;
        return strArr2[i2 % strArr2.length];
    }

    String compProgress(int i) {
        switch (i) {
            case OV_NONE /* 0 */:
                return "Спаси её на 20-м этаже башни (" + Math.min(this.towerFloor, TOWER_TOP) + "/20)";
            case 1:
                return "Поймай 25 фей (" + Math.min(this.fairies, 25L) + "/25)";
            case 2:
                return "Открой 10 мимиков (" + Math.min(this.chests, 10L) + "/10)";
            case 3:
                return "Дойди до 75-го этапа (" + Math.min(this.bestStage, 75) + "/75)";
            default:
                return "Победи 25 боссов (" + Math.min(this.bossKills, 25L) + "/25)";
        }
    }

    boolean compUnlocked(int i) {
        switch (i) {
            case OV_NONE /* 0 */:
                return princess();
            case 1:
                return this.fairies >= 25;
            case 2:
                return this.chests >= 10;
            case 3:
                return this.bestStage >= 75;
            default:
                return this.bossKills >= 25;
        }
    }

    double costAt(int i, int i2) {
        switch (i) {
            case OV_NONE /* 0 */:
                return Math.pow(1.14d, i2) * 10.0d;
            case 1:
                return Math.pow(1.16d, i2) * 40.0d;
            case 2:
                return Math.pow(1.18d, i2) * 15.0d;
            case 3:
                return Math.pow(5.0d, i2) * 300.0d;
            default:
                return Math.pow(1.6d, i2) * 150.0d;
        }
    }

    int critChance() {
        return (this.alt[1] * 2) + 8 + (compUnlocked(1) ? 5 : OV_NONE);
    }

    int dailyDay() {
        return ((Math.max(1, this.streak) - 1) % 7) + 1;
    }

    String dailyDesc(int i) {
        switch (i) {
            case 1:
                return "+" + fmt(goldFor(this.stage) * 20.0d) + " золота";
            case 2:
                return "+" + fmt((manaTap() * 150.0d) + (manaSec() * 90.0d)) + " маны";
            case 3:
                return "+" + fmt(goldFor(this.stage) * 50.0d) + " золота";
            case 4:
                return "Ярость: урон ×3 на 60 секунд";
            case 5:
                return "+" + fmt(((manaTap() * 150.0d) + (manaSec() * 90.0d)) * 3.0d) + " маны";
            case 6:
                return "+" + fmt(goldFor(this.stage) * 100.0d) + " золота";
            default:
                return "+" + fmt((this.bestStage / 30) + 2) + " душ";
        }
    }

    void damage(double d, boolean z) {
        this.monsterHp -= d;
        if (z) {
            this.mobHit = 1.0f;
        }
        int i = OV_NONE;
        while (true) {
            double d2 = this.monsterHp;
            if (d2 > 0.0d) {
                break;
            }
            int i2 = i + 1;
            if (i >= 50) {
                break;
            }
            double d3 = -d2;
            boolean bossFight = bossFight();
            kill();
            if (bossFight) {
                break;
            }
            this.monsterHp -= d3 * 0.5d;
            i = i2;
        }
        if (this.monsterHp <= 0.0d) {
            this.monsterHp = this.monsterMax;
        }
    }

    void drawAchievements(Canvas canvas) {
        int length = (ACH_NAME.length + 1) / 2;
        float f = this.upT;
        float f2 = this.pad;
        float f3 = f + (4.6f * f2);
        float f4 = ((this.H * 0.99f) - f3) / length;
        float f5 = (this.W - (f2 * 3.0f)) / 2.0f;
        int i = OV_NONE;
        while (true) {
            String[] strArr = ACH_NAME;
            if (i >= strArr.length) {
                return;
            }
            float f6 = this.pad;
            float f7 = (1.5f * f6) + ((i % 2) * f5);
            float f8 = ((i / 2) * f4) + f3;
            boolean z = (this.achMask & (1 << i)) != 0;
            this.r.set((f6 * 0.3f) + f7, f8, (f7 + f5) - (f6 * 0.3f), (f8 + f4) - (f6 * 0.5f));
            this.p.setColor(z ? -12963304 : -14805453);
            RectF rectF = this.r;
            float f9 = this.pad;
            canvas.drawRoundRect(rectF, f9, f9, this.p);
            this.tp.setTextAlign(Paint.Align.LEFT);
            this.tp.setTextSize(Math.min(this.pad * 1.7f, 0.3f * f4));
            this.tp.setFakeBoldText(true);
            this.tp.setColor(z ? -11430 : -7569238);
            drawFit(canvas, (z ? "🏆 " : "🔒 ") + strArr[i], this.r.left + this.pad, this.r.top + (0.42f * f4), this.r.width() - (this.pad * 2.0f));
            this.tp.setFakeBoldText(false);
            this.tp.setTextSize(Math.min(this.pad * 1.3f, 0.24f * f4));
            this.tp.setColor(z ? -2570096 : -9543024);
            drawFit(canvas, ACH_DESC[i] + " · +5% урона", this.r.left + this.pad, this.r.top + (0.78f * f4), this.r.width() - (this.pad * 2.0f));
            i++;
        }
    }

    void drawActions(Canvas canvas, long j) {
        float f = this.W / 3.0f;
        float f2 = this.pad * 0.6f;
        boolean z = j >= this.meteorReadyAt;
        boolean z2 = z && this.mana >= meteorCost();
        this.r.set(this.pad * 1.5f, this.actT, f - f2, this.actB);
        panel(canvas, this.r, this.pad * IMPACT, z2 ? -4700630 : -12964796);
        if (!z) {
            this.p.setColor(1426063360);
            RectF rectF = new RectF(this.r.left, this.r.top, this.r.left + (this.r.width() * ((this.meteorReadyAt - j) / meteorCd())), this.r.bottom);
            float f3 = this.pad;
            canvas.drawRoundRect(rectF, f3 * IMPACT, f3 * IMPACT, this.p);
        }
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setColor(-1);
        this.tp.setTextSize(this.pad * 1.9f);
        this.tp.setFakeBoldText(true);
        canvas.drawText("☄ Метеор", this.r.centerX(), this.r.centerY() - (this.pad * 0.2f), this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.35f);
        this.tp.setColor(-2043649);
        canvas.drawText((z ? new StringBuilder().append(fmt(meteorCost())).append(" маны") : new StringBuilder().append("откат ").append(((this.meteorReadyAt - j) / 1000) + 1).append("с")).toString(), this.r.centerX(), this.r.centerY() + (this.pad * 1.8f), this.tp);
        float f4 = f * 2.0f;
        this.r.set(f + f2, this.actT, f4 - f2, this.actB);
        panel(canvas, this.r, this.pad * IMPACT, -13746322);
        this.tp.setColor(-1);
        this.tp.setTextSize(this.pad * 2.2f);
        this.tp.setFakeBoldText(true);
        canvas.drawText("🛒 " + BUY_LABEL[this.buyMode], this.r.centerX(), this.r.centerY() - (this.pad * 0.1f), this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.35f);
        this.tp.setColor(-4140801);
        canvas.drawText("режим покупки", this.r.centerX(), this.r.centerY() + (this.pad * 1.8f), this.tp);
        double soulsGain = soulsGain();
        this.r.set(f4 + f2, this.actT, this.W - (this.pad * 1.5f), this.actB);
        if (soulsGain > 0.0d) {
            float sin = (float) ((Math.sin(this.time * 3.0f) * 0.5d) + 0.5d);
            float f5 = 40.0f * sin;
            panel(canvas, this.r, this.pad * IMPACT, Color.rgb((int) (BF_BOSS_MAX + f5), (int) ((sin * 50.0f) + 130.0f), (int) (f5 + 200.0f)));
        } else {
            panel(canvas, this.r, this.pad * IMPACT, -12964796);
        }
        this.tp.setColor(-1);
        this.tp.setTextSize(this.pad * 1.8f);
        this.tp.setFakeBoldText(true);
        canvas.drawText("👻 Переродиться", this.r.centerX(), this.r.centerY() - (this.pad * 0.2f), this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.35f);
        this.tp.setColor(-2043649);
        canvas.drawText(soulsGain > 0.0d ? "+" + fmt(soulsGain) + " душ" : "с этапа 30", this.r.centerX(), this.r.centerY() + (this.pad * 1.8f), this.tp);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void drawAltar(Canvas var1) {
       float var4 = this.rowH;
       boolean var10 = this.ascUnlocked();
       if (var10) {
          this.rowH = 5.0F * var4 / 6.0F;
       }

       for (int var5 = 0; var5 < 5; var5++) {
          boolean var6;
          if (this.alt[var5] >= ALT_MAX[var5]) {
             var6 = true;
          } else {
             var6 = false;
          }

          double var2 = this.altCost(var5);
          this.rowIcon = this.icAlt[var5];
          int var7 = ALT_COLOR[var5];
          int var8 = this.alt[var5];
          String var14 = ALT_NAME[var5];
          String var13 = this.altEffect(var5);
          String var11;
          if (var6) {
             var11 = "МАКС";
          } else {
             var11 = fmt(var2);
          }

          boolean var9;
          if (!var6 && this.souls >= var2) {
             var9 = true;
          } else {
             var9 = false;
          }

          Bitmap var12;
          if (var6) {
             var12 = null;
          } else {
             var12 = this.bmSoul;
          }

          this.rowBox(var1, var5, false, var7, String.valueOf(var8), var14, var13, var11, var9, -13664328, var12);
       }

       if (var10) {
          int var15 = this.starsGain();
          boolean var16;
          if (var15 > 0) {
             var16 = true;
          } else {
             var16 = false;
          }

          String var20 = "✦" + this.stars;
          String var19 = "Звёзды: урон ×" + fmt(this.starMult()) + " · сбросит души и Алтарь";
          String var18;
          if (var15 > 0) {
             var18 = "+" + var15 + " ✦";
          } else {
             var18 = "мало душ";
          }

          if (var15 > 0) {
             var10 = true;
          } else {
             var10 = false;
          }

          this.rowBox(var1, 5, var16, -5720, var20, "Вознесение", var19, var18, var10, -7712808, null);
       }

       this.rowH = var4;
       this.tp.setTextAlign(Align.CENTER);
       this.tp.setTextSize(this.pad * 1.3F);
       this.tp.setColor(-9543024);
       var1.drawText("Потраченные души больше не усиливают урон — выбирай с умом", (float)this.W / 2.0F, (float)this.H * 0.992F, this.tp);
    }

    void drawAmbient(Canvas var1) {
       int var17 = this.biome();
       float var9 = this.groundY - this.battleT;
       int var14;
       if (var17 == 2) {
          var14 = -20320;
       } else if (var17 == 1) {
          var14 = -2035457;
       } else {
          var14 = -3622657;
       }

       for (int var15 = 0; var15 < 4; var15++) {
          float var10 = this.bgS;
          int var16 = var15 % 2;
          float var8 = ((float)var16 * 0.5F + 1.2F) * var10;
          float var12 = 26.0F * var8;
          double var2 = (double)var15;
          int var18 = this.W;
          float var11 = (float)((0.37 * var2 * (double)var18 + (double)this.time * (var2 * 2.2 + 3.0) * (double)var10) % (double)((float)var18 + 2.0F * var12))
             - var12;
          var10 = this.battleT + ((float)var15 * 0.09F + 0.07F) * var9;
          this.p.setColor(var14);
          Paint var19 = this.p;
          byte var55;
          if (var16 == 0) {
             var55 = 55;
          } else {
             var55 = 38;
          }

          var19.setAlpha(var55);
          float var13 = 4.0F * var8;
          var1.drawRect(var11, var10, var11 + var12, var10 + var13, this.p);
          var12 = var10 - 3.0F * var8;
          var1.drawRect(var11 + var13, var12, var11 + 15.0F * var8, var10, this.p);
          var13 = var11 + 12.0F * var8;
          var1.drawRect(var13, var10 - 5.0F * var8, var11 + 20.0F * var8, var12, this.p);
          var1.drawRect(var13, var12, var11 + var8 * 22.0F, var10, this.p);
       }

       this.p.setAlpha(255);
       if (var17 == 0) {
          for (int var49 = 0; var49 < 12; var49++) {
             double var20 = (double)var49;
             float var32 = (float)((0.13 * var20 + 0.04) * (double)this.W + Math.sin((double)this.time * 0.6 + 1.7 * var20) * (double)this.W * 0.05);
             float var25 = this.battleT
                + ((float)(var49 * 37 % 100) * 0.5F / 100.0F + 0.35F) * var9
                + (float)Math.sin((double)this.time * 0.9 + var20) * var9 * 0.05F;
             float var37 = (float)(Math.sin((double)this.time * 2.2 + var20 * 2.1) * 0.5 + 0.5);
             this.p.setColor(-2556048);
             this.p.setAlpha((int)(60.0F * var37));
             var1.drawCircle(var32, var25, this.bgS * 4.0F, this.p);
             this.p.setAlpha((int)(var37 * 255.0F));
             var37 = this.bgS;
             var1.drawRect(var32 - var37 * 0.6F, var25 - var37 * 0.6F, var32 + var37 * 0.6F, var25 + var37 * 0.6F, this.p);
          }
       } else if (var17 == 1) {
          for (int var50 = 0; var50 < 34; var50++) {
             float var39 = (float)(var50 % 5 * 7 + 14);
             float var45 = this.bgS;
             float var33 = this.battleT;
             double var21 = (double)var50;
             double var4 = (double)var9;
             float var26 = this.time;
             var33 += (float)((0.071 * var21 * var4 + (double)(var39 * var45 * var26)) % var4);
             var4 = (double)this.W;
             double var6 = Math.sin((double)var26 * 0.8 + var21);
             var45 = this.bgS;
             var39 = (float)((0.097 * var21 * var4 + var6 * (double)var45 * 6.0) % (double)this.W);
             int var52 = var50 % 3;
             if (var52 == 0) {
                var26 = 1.0F;
             } else {
                var26 = 0.6F;
             }

             var26 = var45 * var26;
             this.p.setColor(-1);
             Paint var56 = this.p;
             short var53;
             if (var52 == 0) {
                var53 = 220;
             } else {
                var53 = 140;
             }

             var56.setAlpha(var53);
             var1.drawRect(var39, var33, var39 + var26, var33 + var26, this.p);
          }
       } else {
          for (int var51 = 0; var51 < 24; var51++) {
             float var29 = (float)(var51 % 4 * 9 + 16);
             float var41 = this.bgS;
             float var47 = this.groundY;
             double var22 = (double)var51;
             double var24 = (double)var9;
             float var35 = this.time;
             var29 = var47 - (float)((0.083 * var22 * var24 + (double)(var29 * var41 * var35)) % var24);
             var35 = (float)((0.113 * var22 * (double)this.W + Math.sin((double)var35 * 1.3 + var22) * (double)this.bgS * 5.0) % (double)this.W);
             var41 = (var29 - this.battleT) / var9;
             Paint var57 = this.p;
             int var54;
             if (var51 % 3 == 0) {
                var54 = -11430;
             } else {
                var54 = -38342;
             }

             var57.setColor(var54);
             this.p.setAlpha((int)((double)(var41 * 230.0F) * (Math.sin((double)(this.time * 9.0F + (float)var51)) * 0.4 + 0.6)));
             var41 = this.bgS;
             var1.drawRect(var35, var29, var35 + var41 * 0.8F, var29 + var41 * 0.8F, this.p);
          }
       }

       this.p.setAlpha(255);
    }

    void drawBattle(Canvas canvas, long j) {
        int i;
        int i2;
        float f = 0.0f;
        if (this.taps < 5 || !rhythmOn()) {
            this.beatPulse = 0.0f;
        } else {
            double beatPos = beatPos() / this.BEAT;
            this.beatPulse = (float) Math.max(0.0d, 1.0d - ((beatPos - Math.floor(beatPos)) / 0.2d));
        }
        int i3 = 1;
        boolean z = j < this.frenzyUntil;
        Bitmap bitmap = this.bmBg[biome()];
        canvas.save();
        canvas.clipRect(0.0f, this.battleT, this.W, this.battleB);
        this.p.setColor(bitmap.getPixel(OV_NONE, OV_NONE));
        canvas.drawRect(0.0f, this.battleT, this.W, this.battleB, this.p);
        float f2 = this.groundY - (this.bgS * 96.0f);
        this.src.set(OV_NONE, OV_NONE, bitmap.getWidth(), bitmap.getHeight());
        this.r.set(0.0f, f2, this.W, (bitmap.getHeight() * this.bgS) + f2);
        canvas.drawBitmap(bitmap, this.src, this.r, this.bp);
        this.p.setColor(bitmap.getPixel(OV_NONE, bitmap.getHeight() - 1));
        canvas.drawRect(0.0f, this.r.bottom, this.W, this.battleB, this.p);
        drawAmbient(canvas);
        if (z) {
            this.p.setColor(872364602);
            canvas.drawRect(0.0f, this.battleT, this.W, this.battleB, this.p);
        }
        int i4 = this.compSel;
        if (i4 >= 0 && compUnlocked(i4)) {
            drawPrincess(canvas);
        }
        drawHero(canvas);
        drawFamiliar(canvas);
        drawMonster(canvas);
        if (this.overlay == 6) {
            drawDeath(canvas);
        }
        drawBeatRing(canvas);
        drawIsekai(canvas);
        drawPrincessBubble(canvas);
        Iterator<Fx> it = this.famShots.iterator();
        while (it.hasNext()) {
            Fx next = it.next();
            float f3 = next.life / next.max;
            float famX = famX();
            float famY = famY();
            float f4 = famX + ((this.mobX - famX) * f3);
            float f5 = famY + ((this.mobY - famY) * f3);
            this.p.setColor(1717228031);
            canvas.drawCircle(f4, f5, this.hu * 2.2f, this.p);
            this.p.setColor(-2033409);
            float f6 = this.hu;
            canvas.drawRect(f4 - f6, f5 - f6, f4 + f6, f5 + f6, this.p);
        }
        Iterator<Fx> it2 = this.bolts.iterator();
        while (it2.hasNext()) {
            Fx next2 = it2.next();
            float f7 = next2.life / next2.max;
            float f8 = this.heroX;
            float f9 = this.hu;
            float f10 = f8 + (13.0f * f9);
            float f11 = this.groundY - (f9 * 26.0f);
            int i5 = 3;
            while (i5 >= i3) {
                float f12 = i5;
                float f13 = f7 - (0.09f * f12);
                if (f13 <= f) {
                    i2 = i5;
                } else {
                    float f14 = ((this.mobX - f10) * f13) + f10;
                    float sin = (f11 + ((this.mobY - f11) * f13)) - ((((float) Math.sin(f13 * 3.141592653589793d)) * this.mobR) * 0.6f);
                    float f15 = this.hu * (1.5f - (f12 * 0.35f));
                    this.p.setColor(i5 == 1 ? -11430 : i5 == 2 ? -26051 : -2076614);
                    this.p.setAlpha(220 - (i5 * 50));
                    i2 = i5;
                    canvas.drawRect(f14 - f15, sin - f15, f14 + f15, sin + f15, this.p);
                }
                i5 = i2 - 1;
                f = 0.0f;
                i3 = 1;
            }
            this.p.setAlpha(255);
            f = 0.0f;
            i3 = 1;
        }
        Iterator<Fx> it3 = this.bolts.iterator();
        while (it3.hasNext()) {
            Fx next3 = it3.next();
            float f16 = next3.life / next3.max;
            float f17 = this.heroX;
            float f18 = this.hu;
            float f19 = f17 + (13.0f * f18);
            float f20 = this.groundY - (f18 * 26.0f);
            float f21 = f19 + ((this.mobX - f19) * f16);
            float sin2 = (f20 + ((this.mobY - f20) * f16)) - ((((float) Math.sin(f16 * 3.141592653589793d)) * this.mobR) * 0.6f);
            this.p.setColor(1157593661);
            canvas.drawCircle(f21, sin2, this.hu * 3.5f, this.p);
            Bitmap bitmap2 = this.bmFire;
            float height = bitmap2.getHeight();
            float f22 = this.hu;
            blit(canvas, bitmap2, f21, sin2 + (height * f22 * 0.42f), f22 * 0.85f, 1.0f, 1.0f, this.bp);
        }
        if (this.fairyOn) {
            drawFairy(canvas);
        }
        float f23 = 1.0f;
        if (this.scrollOn) {
            float scrollX = scrollX();
            float scrollY = scrollY();
            float f24 = (this.beatPulse * 0.18f) + 1.0f;
            this.p.setColor(-5213953);
            this.p.setAlpha((int) ((this.beatPulse * BF_BOSS_MAX) + 110.0f));
            canvas.drawCircle(scrollX, scrollY, this.pad * 4.4f * f24, this.p);
            this.p.setStyle(Paint.Style.STROKE);
            this.p.setStrokeWidth(this.pad * 0.3f);
            this.p.setColor(-2045697);
            this.p.setAlpha((int) ((1.0f - ((this.time * IMPACT) % 1.0f)) * 200.0f));
            canvas.drawCircle(scrollX, scrollY, this.pad * ((((this.time * IMPACT) % 1.0f) * 3.0f) + 4.4f), this.p);
            this.p.setStyle(Paint.Style.FILL);
            this.p.setAlpha(255);
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 4.5f * f24);
            this.tp.setColor(-1);
            this.tp.setAlpha((int) 255.0f);
            canvas.drawText("📜", scrollX, (this.pad * 1.6f) + scrollY, this.tp);
            this.tp.setTypeface(this.pixTf);
            this.tp.setTextSize(this.pad * 1.1f);
            this.tp.setColor(-15331286);
            float f25 = this.pad;
            canvas.drawText("ИСПЫТАНИЕ!", (0.15f * f25) + scrollX, (f25 * 6.35f) + scrollY, this.tp);
            this.tp.setColor(-2045697);
            canvas.drawText("ИСПЫТАНИЕ!", scrollX, scrollY + (this.pad * 6.2f), this.tp);
            this.tp.setTypeface(Typeface.DEFAULT);
            this.tp.setAlpha(255);
        }
        Iterator<Fx> it4 = this.parts.iterator();
        while (it4.hasNext()) {
            Fx next4 = it4.next();
            this.p.setColor(next4.color);
            this.p.setAlpha((int) (Math.max(0.0f, f23 - (next4.life / next4.max)) * 255.0f));
            float f26 = next4.size * 0.9f;
            canvas.drawRect(next4.x - f26, next4.y - f26, next4.x + f26, next4.y + f26, this.p);
            f23 = 1.0f;
        }
        Iterator<Fx> it5 = this.chunks.iterator();
        while (it5.hasNext()) {
            Fx next5 = it5.next();
            float f27 = next5.life / next5.max;
            this.p.setColor(next5.color);
            this.p.setAlpha((int) ((f27 < 0.65f ? 1.0f : Math.max(0.0f, (1.0f - f27) / 0.35f)) * 255.0f));
            float f28 = next5.size * 0.5f;
            canvas.drawRect(next5.x - f28, next5.y - f28, next5.x + f28, next5.y + f28, this.p);
        }
        this.p.setAlpha(255);
        drawGrass(canvas);
        drawVignette(canvas);
        drawGroundHud(canvas, j);
        if (this.bossIntroT > 0.0f && bossFight()) {
            float f29 = 1.6f - this.bossIntroT;
            float min = Math.min(1.0f, f29 / 0.25f);
            float min2 = min * Math.min(1.0f, this.bossIntroT / 0.3f);
            float f30 = this.battleB;
            float f31 = this.battleT;
            float f32 = f31 + ((f30 - f31) * 0.38f);
            float f33 = (1.0f - min) * this.W;
            this.p.setColor(-870969302);
            this.p.setAlpha((int) (210.0f * min2));
            float f34 = ((f30 - f31) * 0.18f) / 2.0f;
            float f35 = f32 - f34;
            float f36 = f32 + f34;
            canvas.drawRect(0.0f, f35, this.W, f36, this.p);
            this.p.setColor(this.towerFight ? -24512 : -2078616);
            int i6 = (int) (min2 * 255.0f);
            this.p.setAlpha(i6);
            canvas.drawRect(-f33, f35, this.W - f33, f35 + (this.pad * 0.5f), this.p);
            canvas.drawRect(f33, f36 - (this.pad * 0.5f), this.W + f33, f36, this.p);
            for (int i7 = OV_NONE; i7 < 8; i7++) {
                int i8 = this.W;
                float f37 = this.pad;
                float f38 = ((((i7 * i8) / 8.0f) + ((i8 * f29) * 0.6f)) % (i8 + (6.0f * f37))) - (f37 * 3.0f);
                this.p.setAlpha((int) (45.0f * min2));
                float f39 = this.pad;
                canvas.drawRect(f38, f35 + (f39 * 0.5f), (1.5f * f39) + f38, f36 - (f39 * 0.5f), this.p);
            }
            this.p.setAlpha(255);
            this.tp.setTypeface(this.pixTf);
            this.tp.setFakeBoldText(false);
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 3.2f);
            this.tp.setColor(-15331286);
            this.tp.setAlpha(i6);
            String str = this.towerFight ? "СТРАЖ!" : "БОСС!";
            float f40 = f33 * 0.5f;
            float f41 = this.pad;
            canvas.drawText(str, (this.W / 2.0f) + f40 + (f41 * 0.3f), f32 + (f41 * 0.3f) + (f41 * 0.3f), this.tp);
            this.tp.setColor(this.towerFight ? -16310 : -42390);
            this.tp.setAlpha(i6);
            canvas.drawText(str, (this.W / 2.0f) + f40, f32 + (this.pad * 0.3f), this.tp);
            this.tp.setTypeface(Typeface.DEFAULT);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setColor(-1);
            this.tp.setAlpha(i6);
            String mobName = mobName();
            int i9 = this.W;
            float f42 = this.pad;
            drawFit(canvas, mobName, (i9 / 2.0f) - f40, f36 - (f42 * IMPACT), i9 - (f42 * 4.0f));
            this.tp.setAlpha(255);
        }
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setFakeBoldText(false);
        this.tp.setTypeface(this.pixTf);
        Iterator<Fx> it6 = this.floats.iterator();
        while (it6.hasNext()) {
            Fx next6 = it6.next();
            float f43 = next6.life / next6.max;
            this.tp.setTextSize(next6.size * 0.62f * (f43 < 0.12f ? ((f43 / 0.12f) * 0.45f) + 0.7f : 1.15f - Math.min(0.15f, f43 - 0.12f)));
            int min3 = (int) (Math.min(1.0f, (1.0f - f43) * 2.0f) * 255.0f);
            this.tp.setColor(-15331286);
            this.tp.setAlpha(min3);
            canvas.drawText(next6.text, next6.x + (this.pad * 0.25f), next6.y + (this.pad * 0.25f), this.tp);
            this.tp.setColor(next6.color);
            this.tp.setAlpha(min3);
            canvas.drawText(next6.text, next6.x, next6.y, this.tp);
        }
        this.tp.setTypeface(Typeface.DEFAULT);
        this.tp.setAlpha(255);
        this.tp.setAlpha(255);
        this.tp.setFakeBoldText(false);
        canvas.restore();
        if (this.farming) {
            this.p.setColor(Color.rgb((int) ((((float) ((Math.sin(this.time * 4.0f) * 0.5d) + 0.5d)) * BF_BOSS_MAX) + 150.0f), 50, 60));
            RectF rectF = this.bossBtn;
            float f44 = this.pad;
            canvas.drawRoundRect(rectF, f44, f44, this.p);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 1.8f);
            this.tp.setFakeBoldText(true);
            canvas.drawText("⚔ К боссу", this.bossBtn.centerX(), this.bossBtn.centerY() + (this.pad * 0.65f), this.tp);
            this.tp.setFakeBoldText(false);
        }
        if (beatUnlocked()) {
            float sin3 = this.hasPhones ? 0.0f : (float) ((Math.sin(this.time * 5.0f) * 0.5d) + 0.5d);
            this.p.setColor(this.hasPhones ? -869654456 : Color.rgb((int) ((80.0f * sin3) + 150.0f), 50, (int) ((sin3 * BF_BOSS_MAX) + 120.0f)));
            RectF rectF2 = this.beatBtn;
            float f45 = this.pad;
            canvas.drawRoundRect(rectF2, f45, f45, this.p);
            float min4 = (Math.min(this.beatBtn.width(), this.beatBtn.height()) * 0.7f) / this.icPhones.getWidth();
            i = -1;
            blit(canvas, this.icPhones, this.beatBtn.centerX(), this.beatBtn.centerY() + ((this.icPhones.getHeight() * min4) / 2.0f), min4, 1.0f, 1.0f, this.bp);
        } else {
            i = -1;
        }
        if (towerUnlocked()) {
            boolean z2 = this.towerFight;
            this.p.setColor(z2 ? -863348198 : this.towerFloor == 0 ? Color.rgb((int) ((Math.sin(this.time * 4.0f) * 50.0d) + 120.0d), 80, 40) : -869654456);
            RectF rectF3 = this.towerBtn;
            float f46 = this.pad;
            canvas.drawRoundRect(rectF3, f46, f46, this.p);
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setColor(i);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setFakeBoldText(true);
            drawFit(canvas, z2 ? "🏰 бой" : "🏰 " + Math.min(this.towerFloor, 999), this.towerBtn.centerX(), this.towerBtn.centerY() + (this.pad * 0.6f), this.towerBtn.width() - this.pad);
            this.tp.setFakeBoldText(false);
        }
        if (hasRelics() || this.hasPhones) {
            this.p.setColor(this.relicNew ? Color.rgb((int) ((Math.sin(this.time * 5.0f) * 60.0d) + 150.0d), 110, 40) : -869654456);
            RectF rectF4 = this.relicBtn;
            float f47 = this.pad;
            canvas.drawRoundRect(rectF4, f47, f47, this.p);
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setColor(i);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setFakeBoldText(true);
            int[] iArr = this.rel;
            int length = iArr.length;
            int i10 = OV_NONE;
            for (int i11 = OV_NONE; i11 < length; i11++) {
                i10 += iArr[i11];
            }
            drawFit(canvas, "🎒 Реликвии " + i10, this.relicBtn.centerX(), this.relicBtn.centerY() + (this.pad * 0.6f), this.relicBtn.width() - this.pad);
            this.tp.setFakeBoldText(false);
        }
        if (this.taps >= 5 || this.overlay != 0) {
            return;
        }
        this.tp.setTextSize(this.pad * 2.4f);
        this.tp.setColor(i);
        this.tp.setAlpha((int) ((Math.sin(this.time * 5.0f) * 100.0d) + 150.0d));
        canvas.drawText("👆 Тапай по экрану!", this.W / 2.0f, this.battleB - (this.pad * IMPACT), this.tp);
        this.tp.setAlpha(255);
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x051d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void drawBeatFight(Canvas var1) {
       for (int var19 = 0; var19 < 24; var19++) {
          float var6 = (float)var19 / 24.0F;
          this.p.setColor(Color.rgb((int)(20.0F * var6 + 26.0F), (int)(6.0F * var6 + 10.0F), (int)(30.0F * var6 + 40.0F)));
          int var20 = this.H;
          var1.drawRect(0.0F, (float)var20 * var6, (float)this.W, (float)var20 * (var6 + 0.041666668F) + 1.0F, this.p);
       }

       double var2 = this.bfNow;
       double var4 = Math.floor(var2);
       float var28;
       if (this.bfState == 1) {
          var28 = (float)Math.max(0.0, 1.0 - (var2 - var4) / 0.25);
       } else {
          var28 = 0.0F;
       }

       for (int var93 = 0; var93 < 4; var93++) {
          float var8 = (float)this.W;
          float var7;
          if (var93 < 2) {
             var7 = 0.05F;
          } else {
             var7 = 0.95F;
          }

          var8 *= var7;
          float var9 = (float)Math.sin((double)this.time * 0.7 + (double)var93 * 1.7);
          if (var93 < 2) {
             var7 = 0.45F;
          } else {
             var7 = -0.45F;
          }

          Path var21 = new Path();
          float var11 = (float)this.H * 0.5F;
          float var10 = (float)this.W * 0.08F;
          var2 = (double)(var9 * 0.5F + var7);
          var7 = (float)Math.sin(var2) * var11 + var8;
          var9 = (float)this.H * 0.04F + (float)Math.cos(var2) * var11;
          var21.moveTo(var8, (float)this.H * 0.04F);
          var21.lineTo(var7 - var10, var9);
          var21.lineTo(var7 + var10, var9);
          var21.close();
          this.p.setColor(new int[]{-45149, -10825217, -11430, -8592501}[var93]);
          this.p.setAlpha((int)(40.0F * var28 + 22.0F));
          var1.drawPath(var21, this.p);
       }

       this.p.setAlpha(255);
       float var83 = (float)this.W * 0.2F / (float)this.bmBeatmaker.getWidth();
       float var77 = (float)this.W;
       float var51 = (float)this.H * 0.24F;
       if (this.bfBossHurt > 0.0F) {
          var1.save();
          var1.translate((this.rnd.nextFloat() - 0.5F) * this.pad * this.bfBossHurt, 0.0F);
       }

       Bitmap var22 = this.bmBeatmaker;
       float var67 = this.pad;
       Paint var122;
       if (this.bfBossHurt > 0.6F) {
          var122 = this.flashP;
       } else {
          var122 = this.bp;
       }

       this.blit(var1, var22, var77 * 0.5F, var51 + var67 * var28 * 0.5F, var83, 1.0F, 1.0F, var122);
       if (this.bfBossHurt > 0.0F) {
          var1.restore();
       }

       RectF var123 = this.r;
       int var94 = this.W;
       var77 = (float)var94;
       var67 = this.pad;
       var123.set(var77 * 0.3F, var51 - var67 * 1.5F, (float)var94 * 0.7F, var67 * 2.2F + var51);
       this.panel(var1, this.r, this.pad, -14015424);

       for (int var95 = 0; var95 < 2; var95++) {
          var77 = (float)this.W;
          var67 = (float)var95;
          var77 = (0.26F * var67 + 0.37F) * var77;
          var83 = var51 + this.pad * 0.3F;
          this.p.setColor(-15857128);
          var1.drawCircle(var77, var83, this.pad * 1.6F, this.p);
          this.p.setColor(-45149);
          var1.drawCircle(var77, var83, this.pad * 0.45F, this.p);
          this.p.setColor(1442840575);
          this.p.setStrokeWidth(this.pad * 0.2F);
          var2 = (double)(this.time * 5.0F + var67);
          var1.drawLine(var77, var83, (float)Math.cos(var2) * this.pad * 1.4F + var77, var83 + (float)Math.sin(var2) * this.pad * 1.4F, this.p);
       }

       var77 = this.bfLaneL();
       float var13 = this.bfLaneW();
       var83 = this.bfTopY();
       float var12 = this.bfHitY();
       var51 = (float)this.H;
       int[] var151 = new int[]{-45149, -10825217, -11430};

       for (int var96 = 0; var96 < 3; var96++) {
          float var86 = (float)var96 * var13 + var77;
          RectF var124 = this.r;
          var67 = this.pad;
          var124.set(var86 + var67 * 0.3F, var83, var86 + var13 - var67 * 0.3F, (float)this.H * 0.9F);
          this.p.setColor(-15331546);
          this.p.setAlpha(200);
          RectF var125 = this.r;
          var67 = this.pad;
          var1.drawRoundRect(var125, var67, var67, this.p);
          this.p.setColor(var151[var96]);
          this.p.setAlpha((int)(this.bfLaneFlash[var96] * 90.0F + 25.0F));
          RectF var126 = this.r;
          var67 = this.pad;
          var1.drawRoundRect(var126, var67, var67, this.p);
          this.p.setAlpha(255);
       }

       RectF var127 = this.r;
       var51 = var51 * 0.075F / 2.0F;
       var67 = var12 - var51;
       var51 = var12 + var51;
       var127.set(var77, var67, var13 * 3.0F + var77, var51);
       this.p.setColor(872415231);
       var1.drawRect(this.r, this.p);
       this.p.setStyle(Style.STROKE);
       this.p.setStrokeWidth(this.pad * (0.35F * var28 + 0.35F));
       this.p.setColor(-1);
       this.p.setAlpha((int)(var28 * 95.0F + 160.0F));
       var1.drawRect(this.r, this.p);
       this.p.setStyle(Style.FILL);
       this.p.setAlpha(255);

       for (int var97 = 0; var97 < 3; var97++) {
          var28 = (float)var97 * var13 + var77;
          RectF var128 = this.r;
          float var87 = this.pad;
          var128.set(var28 + var87 * 0.6F, 0.8F * var87 + var51, var28 + var13 - var87 * 0.6F, (float)this.H * 0.9F - var87 * 0.4F);
          RectF var129 = this.r;
          var28 = this.pad;
          int var117;
          if (this.bfLaneFlash[var97] > 0.3F) {
             var117 = -1;
          } else {
             var117 = var151[var97];
          }

          this.panel(var1, var129, var28, var117);
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setTypeface(this.pixTf);
          this.tp.setTextSize(this.pad * 1.15F);
          this.tp.setColor(-15331286);
          String var130;
          if (var97 == 0) {
             var130 = "БОЧКА";
          } else if (var97 == 1) {
             var130 = "СНЕЙР";
          } else {
             var130 = "ХЭТ";
          }

          var1.drawText(var130, this.r.centerX(), this.r.centerY() + this.pad * 0.5F, this.tp);
          this.tp.setTypeface(Typeface.DEFAULT);
       }

       float var14 = (float)this.H * 0.06F;

       for (GameView.Note var23 : this.notes) {
          var28 = (float)((var23.beat - this.bfNow) / var23.travel);
          float var15 = (float)var23.lane * var13 + var77;
          float var88 = var12 - (var12 - var83) * var28;
          if (var23.state == 0) {
             if (var28 > 1.02F) {
                continue;
             }

             var28 = 1.0F;
             var51 = 0.0F;
          } else if (var23.state == 1) {
             var28 = Math.min(1.0F, var23.hitT / 0.3F);
             var51 = var28;
             var28 = 1.0F - var28;
             var88 = var12;
          } else {
             var28 = Math.max(0.0F, 1.0F - var23.hitT * 2.5F);
             var51 = 0.0F;
          }

          if (!(var88 < var83 - var14)) {
             int var98;
             if (var23.state == 0 && Math.abs(var23.beat - this.bfNow) * this.BEAT <= 0.18) {
                var98 = 1;
             } else {
                var98 = 0;
             }

             RectF var24 = this.r;
             float var17 = this.pad;
             float var16 = var14 / 2.0F;
             var24.set(
                var15 + var17 * 0.8F - var51 * var17 * 2.0F,
                var88 - var16 - var51 * var17 * 2.0F,
                var15 + var13 - var17 * 0.8F + var51 * var17 * 2.0F,
                var88 + var16 + var51 * var17 * 2.0F
             );
             if (var23.type == 1) {
                this.p.setColor(-15331286);
                Paint var153 = this.p;
                var98 = (int)(255.0F * var28);
                var153.setAlpha(var98);
                RectF var154 = this.r;
                var28 = this.pad;
                var1.drawRoundRect(var154, var28, var28, this.p);
                this.p.setStyle(Style.STROKE);
                this.p.setStrokeWidth(this.pad * 0.3F);
                this.p.setColor(-46518);
                this.p.setAlpha(var98);
                RectF var155 = this.r;
                var28 = this.pad;
                var1.drawRoundRect(var155, var28, var28, this.p);
                this.p.setStyle(Style.FILL);
                this.tp.setTextAlign(Align.CENTER);
                this.tp.setTextSize(var14 * 0.6F);
                this.tp.setAlpha(var98);
                var1.drawText("\ud83d\udca3", this.r.centerX(), this.r.centerY() + 0.22F * var14, this.tp);
                this.tp.setAlpha(255);
             } else {
                int var118;
                if (var23.state == 1) {
                   var118 = -1;
                } else {
                   var118 = var151[var23.lane];
                }

                if (var98 != 0) {
                   this.p.setColor(var118);
                   this.p.setAlpha((int)(90.0F * var28));
                   this.pr.set(this.r.left - this.pad, this.r.top - this.pad, this.r.right + this.pad, this.r.bottom + this.pad);
                   RectF var156 = this.pr;
                   var51 = this.pad;
                   var1.drawRoundRect(var156, var51 * 1.5F, var51 * 1.5F, this.p);
                }

                this.p.setColor(var118);
                Paint var157 = this.p;
                var98 = (int)(255.0F * var28);
                var157.setAlpha(var98);
                RectF var158 = this.r;
                var51 = this.pad;
                var1.drawRoundRect(var158, var51, var51, this.p);
                this.p.setColor(1442840575);
                this.p.setAlpha((int)(85.0F * var28));
                var88 = this.r.left;
                var51 = this.pad;
                var15 = this.r.top;
                float var18 = this.pad;
                var28 = this.r.right;
                var16 = this.pad;
                var17 = this.r.top;
                var1.drawRect(var51 + var88, var18 * 0.3F + var15, var28 - var16, this.pad * 0.8F + var17, this.p);
                this.tp.setTextAlign(Align.CENTER);
                this.tp.setTextSize(var14 * 0.55F);
                this.tp.setColor(-15331286);
                this.tp.setAlpha(var98);
                var1.drawText("♪", this.r.centerX(), this.r.centerY() + 0.2F * var14, this.tp);
                this.tp.setAlpha(255);
             }
          }
       }

       this.p.setAlpha(255);
       this.tp.setTypeface(this.pixTf);
       this.tp.setFakeBoldText(false);
       this.tp.setTextAlign(Align.CENTER);
       this.tp.setTextSize(this.pad * 1.5F);
       this.tp.setColor(-45149);
       var1.drawText("БИТМЕЙКЕР", (float)this.W / 2.0F, (float)this.H * 0.06F, this.tp);
       RectF var132 = this.r;
       int var119 = this.W;
       var28 = (float)var119;
       var94 = this.H;
       var132.set(var28 * 0.12F, (float)var94 * 0.07F, (float)var119 * 0.88F, (float)var94 * 0.07F + this.pad * 1.4F);
       this.p.setColor(-15331286);
       RectF var133 = this.r;
       var28 = this.pad;
       var1.drawRoundRect(var133, var28 * 0.6F, var28 * 0.6F, this.p);
       RectF var134 = this.r;
       var134.right = var134.left + (float)this.W * 0.76F * Math.max(0.0F, this.bfBossHp / 60.0F);
       this.p.setColor(-45149);
       RectF var135 = this.r;
       var28 = this.pad;
       var1.drawRoundRect(var135, var28 * 0.6F, var28 * 0.6F, this.p);
       this.tp.setTextSize(this.pad * 1.0F);
       this.tp.setColor(-4609816);
       String var136;
       if (this.bfState == 1) {
          var136 = "ФАЗА " + (this.bfPhase + 1);
       } else {
          var136 = "";
       }

       var1.drawText(var136, (float)this.W / 2.0F, (float)this.H * 0.07F + this.pad * 2.8F, this.tp);
       this.tp.setTextAlign(Align.LEFT);
       this.tp.setTextSize(this.pad * 1.2F);
       this.tp.setColor(-1);
       var1.drawText("ТЫ", this.pad * 2.0F, (float)this.H * 0.935F, this.tp);
       RectF var137 = this.r;
       var28 = this.pad;
       var94 = this.H;
       var137.set(var28 * 5.0F, (float)var94 * 0.92F, (float)this.W * 0.6F, (float)var94 * 0.92F + var28 * 1.3F);
       this.p.setColor(-15331286);
       RectF var138 = this.r;
       var28 = this.pad;
       var1.drawRoundRect(var138, var28 * 0.5F, var28 * 0.5F, this.p);
       RectF var139 = this.r;
       var139.right = var139.left + ((float)this.W * 0.6F - this.pad * 5.0F) * Math.max(0.0F, this.bfHeroHp / 100.0F);
       var122 = this.p;
       if (this.bfHeroHp > 35.0F) {
          var94 = -8592501;
       } else {
          var94 = -38294;
       }

       var122.setColor(var94);
       RectF var141 = this.r;
       var28 = this.pad;
       var1.drawRoundRect(var141, var28 * 0.5F, var28 * 0.5F, this.p);
       if (this.bfHurt > 0.0F) {
          this.p.setColor(-65536);
          this.p.setAlpha((int)(this.bfHurt * 70.0F));
          var1.drawRect(0.0F, 0.0F, (float)this.W, (float)this.H, this.p);
          this.p.setAlpha(255);
       }

       if (this.bfCombo >= 2) {
          this.tp.setTextAlign(Align.RIGHT);
          this.tp.setColor(-11430);
          var1.drawText("КОМБО " + this.bfCombo, (float)this.W - this.pad * 2.0F, (float)this.H * 0.935F, this.tp);
       }

       if (this.bfJudgeT > 0.0F) {
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setTextSize(this.pad * 2.0F);
          this.tp.setColor(this.bfJudgeCol);
          this.tp.setAlpha((int)(Math.min(1.0F, this.bfJudgeT * 3.0F) * 255.0F));
          var1.drawText(this.bfJudge, (float)this.W / 2.0F, var67 - this.pad * 1.5F, this.tp);
          this.tp.setAlpha(255);
       }

       if (this.bfTalkT > 0.0F) {
          this.tp.setTypeface(Typeface.DEFAULT);
          this.tp.setFakeBoldText(true);
          this.tp.setTextSize(this.pad * 1.5F);
          var28 = Math.min((float)this.W * 0.5F, this.tp.measureText(this.bfTalk) + this.pad * 2.0F);
          var94 = this.W;
          var67 = (float)var94 * 0.72F;
          var51 = (float)this.H * 0.12F;
          this.r
             .set(Math.min(var67, (float)var94 - this.pad - var28), var51, Math.min(var67, (float)this.W - this.pad - var28) + var28, this.pad * 3.0F + var51);
          this.p.setColor(-1);
          this.p.setAlpha((int)(Math.min(1.0F, this.bfTalkT * 3.0F) * 240.0F));
          RectF var142 = this.r;
          var51 = this.pad;
          var1.drawRoundRect(var142, 1.2F * var51, var51 * 1.2F, this.p);
          this.p.setAlpha(255);
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setColor(-14017990);
          String var143 = this.bfTalk;
          var51 = this.r.centerX();
          var77 = this.r.centerY();
          var67 = this.pad;
          this.drawFit(var1, var143, var51, var77 + var67 * 0.55F, var28 - var67);
          this.tp.setFakeBoldText(false);
          this.tp.setTypeface(this.pixTf);
       }

       this.tp.setTextAlign(Align.CENTER);
       if (this.bfState == 0) {
          var2 = this.bfStartBeat;
          String var144;
          if (var2 < 0.0) {
             var144 = "Битмейкер настраивает пульт...";
          } else {
             var94 = (int)Math.ceil(var2 - this.bfNow);
             if (var94 >= 4) {
                var144 = "Приготовься...";
             } else if (var94 > 0) {
                var144 = String.valueOf(var94);
             } else {
                var144 = "ПОЕХАЛИ!";
             }
          }

          Paint var152 = this.tp;
          var51 = this.pad;
          if (var144.length() <= 2) {
             var28 = 6.0F;
          } else {
             var28 = 1.8F;
          }

          var152.setTextSize(var51 * var28);
          this.tp.setColor(-1);
          var94 = this.W;
          this.drawFit(var1, var144, (float)var94 / 2.0F, (float)this.H * 0.5F, (float)var94 - this.pad * 4.0F);
       }

       this.tp.setTypeface(Typeface.DEFAULT);
       var94 = this.bfState;
       if (var94 <= 1 && this.bfTutorHits < 3 && this.bfNow < this.bfStartBeat + 10.0) {
          this.tp.setTextSize(this.pad * 1.9F);
          this.tp.setFakeBoldText(true);
          this.tp.setColor(-1);
          this.tp.setAlpha((int)(Math.sin((double)(this.time * 5.0F)) * 75.0 + 180.0));
          var1.drawText("Тапни дорожку, когда плитка", (float)this.W / 2.0F, (float)this.H * 0.42F, this.tp);
          var1.drawText("в светящейся полосе ✨", (float)this.W / 2.0F, (float)this.H * 0.46F, this.tp);
          this.tp.setAlpha(255);
          this.tp.setFakeBoldText(false);
       } else if (var94 <= 1) {
          this.tp.setTextSize(this.pad * 1.4F);
          this.tp.setColor(-7569238);
          var1.drawText("Плитка в полосе — тапай её дорожку · \ud83d\udca3 не трогай!", (float)this.W / 2.0F, (float)this.H * 0.975F, this.tp);
       }

       RectF var145 = this.bfExit;
       var119 = this.W;
       var28 = (float)var119;
       var51 = this.pad;
       var94 = this.H;
       var145.set(var28 - 6.0F * var51, (float)var94 * 0.02F, (float)var119 - var51 * 1.5F, (float)var94 * 0.02F + var51 * 4.0F);
       if (this.bfState <= 1) {
          this.tp.setTextSize(this.pad * 2.4F);
          this.tp.setColor(-7569238);
          var1.drawText("✕", this.bfExit.centerX(), this.bfExit.centerY() + this.pad * 0.9F, this.tp);
       }

       var94 = this.bfState;
       if (var94 == 2) {
          this.p.setColor(-586545644);
          var1.drawRect(0.0F, 0.0F, (float)this.W, (float)this.H, this.p);
          var51 = (float)this.W * 0.4F / (float)this.icPhones.getWidth();
          var28 = (float)this.H * 0.36F;
          this.p.setColor(-45149);
          this.p.setAlpha((int)(Math.sin((double)(this.time * 3.0F)) * 30.0 + 60.0));
          var94 = this.W;
          var1.drawCircle((float)var94 / 2.0F, var28, (float)var94 * 0.3F, this.p);
          this.p.setAlpha(255);
          Bitmap var146 = this.icPhones;
          this.blit(var1, var146, (float)this.W / 2.0F, var28 + (float)var146.getHeight() * var51 / 2.0F, var51, 1.0F, 1.0F, this.bp);
          this.tp.setFakeBoldText(true);
          this.tp.setTextSize(this.pad * 2.8F);
          this.tp.setColor(-11430);
          String var147;
          if (this.beatWins == 1) {
             var147 = "Наушники Битмейкера!";
          } else {
             var147 = "Победа над Битмейкером!";
          }

          var1.drawText(var147, (float)this.W / 2.0F, (float)this.H * 0.6F, this.tp);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(this.pad * 1.7F);
          this.tp.setColor(-1);
          if (this.beatWins == 1) {
             var94 = this.W;
             this.drawFit(
                var1, "Теперь ты слышишь ритм боя: кольцо, комбо и Грузовик-кун", (float)var94 / 2.0F, (float)this.H * 0.65F, (float)var94 - this.pad * 4.0F
             );
             var94 = this.W;
             this.drawFit(var1, "Надеть/снять — в рюкзаке \ud83c\udf92 · +10 душ", (float)var94 / 2.0F, (float)this.H * 0.69F, (float)var94 - this.pad * 4.0F);
          } else {
             var94 = this.W;
             this.drawFit(var1, "+3 души за реванш", (float)var94 / 2.0F, (float)this.H * 0.65F, (float)var94 - this.pad * 4.0F);
          }

          this.tp.setColor(-7569238);
          this.tp.setTextSize(this.pad * 1.5F);
          var1.drawText("тап — вернуться", (float)this.W / 2.0F, (float)this.H * 0.85F, this.tp);
       } else if (var94 == 3) {
          this.p.setColor(-586545644);
          var1.drawRect(0.0F, 0.0F, (float)this.W, (float)this.H, this.p);
          this.tp.setFakeBoldText(true);
          this.tp.setTextSize(this.pad * 3.0F);
          this.tp.setColor(-38294);
          var1.drawText("Пальцы не успели...", (float)this.W / 2.0F, (float)this.H * 0.42F, this.tp);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(this.pad * 1.7F);
          this.tp.setColor(-4609816);
          String var148 = "Битмейкер: «" + this.bfTalk + "»";
          var94 = this.W;
          this.drawFit(var1, var148, (float)var94 / 2.0F, (float)this.H * 0.47F, (float)var94 - this.pad * 4.0F);
          RectF var149 = this.bfRetry;
          var94 = this.W;
          var28 = (float)var94 / 2.0F;
          var51 = this.pad;
          var119 = this.H;
          var149.set(var28 + var51, (float)var119 * 0.55F, (float)var94 - var51 * 4.0F, (float)var119 * 0.63F);
          RectF var150 = this.bfExit;
          var28 = this.pad;
          var94 = this.H;
          var150.set(4.0F * var28, (float)var94 * 0.55F, (float)this.W / 2.0F - var28, (float)var94 * 0.63F);
          this.panel(var1, this.bfExit, this.pad * 1.5F, -12963760);
          this.panel(var1, this.bfRetry, this.pad * 1.5F, -4700630);
          this.tp.setColor(-1);
          this.tp.setTextSize(this.pad * 2.1F);
          var1.drawText("Выйти", this.bfExit.centerX(), this.bfExit.centerY() + this.pad * 0.75F, this.tp);
          var1.drawText("Ещё раз!", this.bfRetry.centerX(), this.bfRetry.centerY() + this.pad * 0.75F, this.tp);
       }
    }

    void drawBeatRing(Canvas canvas) {
        if (this.overlay == 0 && this.taps >= 5 && rhythmOn()) {
            double beatPos = beatPos() / this.BEAT;
            float floor = (float) (1.0d - (beatPos - Math.floor(beatPos)));
            float f = this.mobR * 1.25f;
            float f2 = ((1.3f * floor) + 1.0f) * f;
            this.p.setStyle(Paint.Style.STROKE);
            int i = this.combo;
            int i2 = i >= 25 ? -26051 : i >= 10 ? -11430 : i > 0 ? -6295297 : -1;
            float max = (float) Math.max(0.0d, 1.0d - ((beatPos - Math.floor(beatPos)) / BF_GOOD));
            this.p.setColor(i2);
            this.p.setAlpha((int) ((150.0f * max) + 55.0f));
            this.p.setStrokeWidth(this.pad * ((max * 0.4f) + 0.3f));
            canvas.drawCircle(this.mobX, this.mobY, f, this.p);
            this.p.setAlpha((int) (((1.0f - floor) * 170.0f) + 40.0f));
            this.p.setStrokeWidth(this.pad * 0.35f);
            canvas.drawCircle(this.mobX, this.mobY, f2, this.p);
            this.p.setStyle(Paint.Style.FILL);
            this.p.setAlpha(255);
            if (this.judgeT > 0.0f) {
                this.tp.setTypeface(this.pixTf);
                this.tp.setFakeBoldText(false);
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setTextSize(this.pad * 1.5f * (((this.judgeT - 0.3f) * 0.6f) + 1.0f));
                int min = (int) (Math.min(1.0f, this.judgeT * 3.0f) * 255.0f);
                this.tp.setColor(-15331286);
                this.tp.setAlpha(min);
                String str = this.judgeTxt;
                float f3 = this.mobX;
                float f4 = this.pad;
                canvas.drawText(str, f3 + (0.2f * f4), (this.mobY - f) - (f4 * 0.6f), this.tp);
                this.tp.setColor(this.judgeCol);
                this.tp.setAlpha(min);
                canvas.drawText(this.judgeTxt, this.mobX, (this.mobY - f) - (this.pad * 0.8f), this.tp);
                this.tp.setAlpha(255);
                this.tp.setTypeface(Typeface.DEFAULT);
            }
        }
    }

    void drawBestiary(Canvas var1) {
       float var2 = this.upT;
       float var4 = this.pad;
       var2 += 4.6F * var4;
       float var3 = ((float)this.H * 0.99F - var2) / 7.0F;
       float var5 = ((float)this.W - var4 * 3.0F) / 3.0F;

       for (int var14 = 0; var14 < 21; var14++) {
          int var13 = var14 / 3;
          float var7 = this.pad * 1.5F + (float)(var14 % 3) * var5;
          var4 = var2 + (float)var13 * var3;
          long var17 = this.bk[var14];
          boolean var15;
          if (var17 > 0L) {
             var15 = true;
          } else {
             var15 = false;
          }

          boolean var16;
          if (var17 >= (long)this.beastNeed(var14)) {
             var16 = true;
          } else {
             var16 = false;
          }

          RectF var19 = this.r;
          float var6 = this.pad;
          var19.set(var6 * 0.25F + var7, var4, var7 + var5 - 0.25F * var6, var4 + var3 - var6 * 0.4F);
          Paint var32 = this.p;
          if (var16) {
             var13 = -14009808;
          } else {
             var13 = -14805453;
          }

          var32.setColor(var13);
          var19 = this.r;
          var6 = this.pad;
          var1.drawRoundRect(var19, var6, var6, this.p);
          Bitmap var20 = this.beastBmp(var14);
          float var9 = var5 * 0.34F;
          float var12 = 0.78F * var3;
          var7 = Math.min(var9 / (float)var20.getWidth(), var12 / (float)var20.getHeight());
          float var11 = this.r.left;
          float var10 = this.pad;
          var6 = var9 / 2.0F;
          float var8 = this.r.bottom;
          var12 = (var3 - this.pad * 0.4F - var12) / 2.0F;
          Paint var34;
          if (var15) {
             var34 = this.bp;
          } else {
             var34 = this.silP;
          }

          this.blit(var1, var20, var6 + var11 + var10 * 0.4F, var8 - var12, var7, 1.0F, 1.0F, var34);
          this.tp.setTextAlign(Align.LEFT);
          this.tp.setFakeBoldText(true);
          this.tp.setTextSize(Math.min(this.pad * 1.35F, 0.22F * var3));
          Paint var35 = this.tp;
          if (var16) {
             var13 = -6295297;
          } else if (var15) {
             var13 = -1;
          } else {
             var13 = -9543024;
          }

          var35.setColor(var13);
          var7 = this.r.left + var9 + this.pad * 0.8F;
          var6 = this.r.right - var7 - this.pad * 0.4F;
          String var36;
          if (var15) {
             var36 = this.beastName(var14);
          } else {
             var36 = "???";
          }

          this.drawFit(var1, var36, var7, var4 + 0.42F * var3, var6);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(Math.min(this.pad * 1.15F, 0.19F * var3));
          Paint var37 = this.tp;
          if (var16) {
             var13 = -8592501;
          } else {
             var13 = -7569238;
          }

          var37.setColor(var13);
          String var38;
          if (var16) {
             var38 = "✓ изучен · +3%";
          } else if (var15) {
             var38 = fmt((double)this.bk[var14]) + "/" + this.beastNeed(var14);
          } else {
             var38 = "не встречен";
          }

          this.drawFit(var1, var38, var7, var4 + 0.72F * var3, var6);
       }
    }

    void drawCoins(Canvas canvas) {
        float f = this.hu * 0.42f;
        Iterator<Fx> it = this.coins.iterator();
        while (it.hasNext()) {
            Fx next = it.next();
            blit(canvas, this.bmCoin, next.x, ((this.bmCoin.getHeight() * f) / 2.0f) + next.y, f, Math.max(0.25f, (float) Math.abs(Math.cos((next.life * 9.0f) + next.size))), 1.0f, this.bp);
        }
    }

    void drawDeath(Canvas canvas) {
        float f;
        float f2;
        float f3 = this.deathT;
        int i = this.deathKind;
        if (i == 0) {
            float f4 = this.hu * 1.6f;
            float width = this.bmTruck.getWidth() * f4;
            float f5 = width / 2.0f;
            float f6 = this.W + f5;
            float f7 = f3 < 0.4f ? f6 + width : f6 - ((f3 - 0.4f) * ((f6 - (this.heroX + (width * 0.3f))) / 0.8000001f));
            float sin = (float) (Math.sin(40.0f * f3) * f4 * 0.3d);
            this.p.setColor(-1426063361);
            int i2 = OV_NONE;
            while (i2 < 5) {
                float f8 = this.groundY - (((i2 * 3.5f) + 3.0f) * f4);
                float f9 = ((i2 % 2) * f4 * 4.0f) + f7 + f5 + (f4 * 2.0f);
                canvas.drawRect(f9, f8, f9 + ((((i2 * 7) % 10) + 8) * f4), f8 + (0.6f * f4), this.p);
                i2++;
                sin = sin;
                f4 = f4;
            }
            float f10 = f4;
            f = 1.0f;
            blit(canvas, this.bmTruck, f7, this.groundY + (f10 * 1.5f) + sin, f10, 1.0f, 1.0f, this.bp);
            if (f3 > 0.2f && f3 < IMPACT) {
                this.p.setColor(1442837160);
                canvas.drawCircle(f7 - f5, this.groundY - (f10 * 9.0f), f10 * 5.0f, this.p);
            }
        } else {
            f = 1.0f;
            Bitmap bitmap = i == 1 ? this.bmPiano : this.bmCow;
            float f11 = this.hu * 1.5f;
            float height = bitmap.getHeight() * f11;
            float min = Math.min(1.0f, Math.max(0.0f, (f3 - 0.3f) / 0.90000004f));
            float heroTop = heroTop() + ((this.groundY - heroTop()) * 0.78f);
            boolean z = this.deathCrash;
            float f12 = z ? heroTop + (0.05f * height) : this.groundY;
            float f13 = this.battleT;
            float f14 = (f13 - height) + ((f12 - (f13 - height)) * min * min);
            if (z) {
                float f15 = f3 - IMPACT;
                f2 = f12 - ((((float) Math.abs(Math.sin(12.0f * f15) * Math.exp((-f15) * 6.0f))) * f11) * 4.0f);
            } else {
                f2 = f14;
            }
            if (!this.deathCrash) {
                this.p.setColor(1711276032);
                RectF rectF = this.r;
                float f16 = this.heroX;
                float f17 = 10.0f * f11 * min;
                float f18 = this.groundY;
                float f19 = min * f11;
                rectF.set(f16 - f17, f18 - f19, f16 + f17, f18 + f19);
                canvas.drawOval(this.r, this.p);
            }
            canvas.save();
            if (this.deathKind == 2) {
                canvas.rotate((this.deathCrash ? 0.0f : f3 * 200.0f) + 180.0f, this.heroX, f2 - (height / 2.0f));
            }
            blit(canvas, bitmap, this.heroX, f2, f11, 1.0f, 1.0f, this.bp);
            canvas.restore();
        }
        if (this.deathCrash) {
            float f20 = f3 - IMPACT;
            float max = Math.max(0.0f, f - (f20 * 3.0f));
            if (max > 0.0f) {
                this.p.setColor(-1);
                this.p.setAlpha((int) (max * 230.0f));
                canvas.drawRect(0.0f, this.battleT, this.W, this.battleB, this.p);
                this.p.setAlpha(255);
            }
            if (f20 < 1.4f) {
                float min2 = f20 < 0.15f ? (f20 / 0.15f) * 1.3f : 1.3f - Math.min(0.3f, f20 - 0.15f);
                canvas.save();
                float f21 = this.battleT;
                canvas.rotate(-8.0f, this.W / 2.0f, f21 + ((this.battleB - f21) * 0.4f));
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setFakeBoldText(false);
                this.tp.setTypeface(this.pixTf);
                this.tp.setTextSize(this.pad * 4.0f * min2);
                this.tp.setColor(-15331286);
                float f22 = (1.4f - f20) * 3.0f;
                this.tp.setAlpha((int) (Math.min(f, f22) * 255.0f));
                String[] strArr = DEATH_BOOM;
                float f23 = this.pad;
                float f24 = this.battleT;
                canvas.drawText(strArr[this.deathKind], (this.W / 2.0f) + (f23 * 0.5f), f24 + ((this.battleB - f24) * 0.4f) + (f23 * 0.5f), this.tp);
                this.tp.setColor(-11430);
                this.tp.setAlpha((int) (Math.min(f, f22) * 255.0f));
                float f25 = this.battleT;
                canvas.drawText(strArr[this.deathKind], this.W / 2.0f, f25 + ((this.battleB - f25) * 0.4f), this.tp);
                this.tp.setAlpha(255);
                this.tp.setFakeBoldText(false);
                this.tp.setTypeface(Typeface.DEFAULT);
                canvas.restore();
            }
        }
    }

    void drawFairy(Canvas canvas) {
        float f = this.fairyX;
        float f2 = this.fairyY;
        this.p.setColor(1442837160);
        canvas.drawCircle(f, f2, this.hu * 7.0f, this.p);
        this.p.setColor(872411816);
        canvas.drawCircle(f, f2, this.hu * 11.0f, this.p);
        Bitmap bitmap = this.bmFairy[((int) (this.time * 10.0f)) % 2];
        canvas.save();
        if (this.fairyDir < 0.0f) {
            canvas.scale(-1.0f, 1.0f, f, f2);
        }
        float height = bitmap.getHeight();
        float f3 = this.hu;
        blit(canvas, bitmap, f, f2 + (height * f3 * 0.55f), f3 * 1.1f, 1.0f, 1.0f, this.bp);
        canvas.restore();
        if (this.rnd.nextInt(3) == 0) {
            Fx fx = new Fx();
            fx.x = f;
            fx.y = f2;
            fx.vx = (-this.fairyDir) * this.pad * 5.0f;
            fx.vy = this.pad * 3.0f;
            fx.max = 0.6f;
            fx.size = this.hu * 0.6f;
            fx.color = -3416;
            this.parts.add(fx);
        }
    }

    void drawFamiliar(Canvas canvas) {
        if (this.lvl[1] <= 0) {
            return;
        }
        float famX = famX();
        float famY = famY();
        int min = Math.min(3, this.lvl[1] / 25);
        float f = this.hu * ((min * 0.06f) + 0.7f);
        int i = min >= 3 ? -4158721 : min >= 2 ? -8592501 : min >= 1 ? -14246 : -8398593;
        this.p.setColor(i);
        this.p.setAlpha((int) ((Math.sin(this.time * 5.0f) * 14.0d) + 28.0d));
        canvas.drawCircle(famX, famY, 4.8f * f, this.p);
        this.p.setAlpha(255);
        for (int i2 = 3; i2 >= 1; i2--) {
            float f2 = i2;
            float f3 = f2 * f;
            this.p.setColor(i);
            this.p.setAlpha(255 - (i2 * 55));
            canvas.drawCircle((1.3f * f3) + famX + (((float) Math.sin((this.time * 6.0f) + f2)) * f * 0.5f), (f3 * 0.9f) + famY, (2.2f - (f2 * 0.45f)) * f, this.p);
        }
        this.p.setAlpha(255);
        Path path = new Path();
        this.p.setColor(i);
        float f4 = SUPER_END * f;
        float f5 = 1.6f * f;
        float f6 = famY - f5;
        path.moveTo(famX - f4, f6);
        float f7 = 2.2f * f;
        float f8 = famY - (4.4f * f);
        path.lineTo(famX - f7, f8);
        float f9 = 0.6f * f;
        float f10 = famY - (2.8f * f);
        path.lineTo(famX - f9, f10);
        path.close();
        path.moveTo(f4 + famX, f6);
        path.lineTo(f7 + famX, f8);
        path.lineTo(famX + f9, f10);
        path.close();
        canvas.drawPath(path, this.p);
        this.p.setColor(-15331286);
        canvas.drawCircle(famX, famY, 3.3f * f, this.p);
        this.p.setColor(i);
        canvas.drawCircle(famX, famY, 3.0f * f, this.p);
        this.p.setColor(1442840575);
        float f11 = 1.1f * f;
        canvas.drawCircle(famX - f11, famY - (IMPACT * f), f11, this.p);
        boolean z = this.time % 3.5f < 0.12f;
        this.p.setColor(-15331286);
        if (z) {
            float f12 = famY - (0.1f * f);
            float f13 = f * 0.5f;
            float f14 = famY + (f * 0.35f);
            canvas.drawRect(famX - f5, f12, famX - f13, f14, this.p);
            canvas.drawRect(famX + f13, f12, famX + f5, f14, this.p);
            return;
        }
        float f15 = 1.05f * f;
        canvas.drawCircle(famX - f15, famY, f9, this.p);
        canvas.drawCircle(f15 + famX, famY, f9, this.p);
        this.p.setColor(-1);
        float f16 = famY - (0.25f * f);
        float f17 = 0.22f * f;
        canvas.drawCircle(famX - (0.85f * f), f16, f17, this.p);
        canvas.drawCircle(famX + (f * 1.25f), f16, f17, this.p);
    }

    void drawFit(Canvas canvas, String str, float f, float f2, float f3) {
        float textSize = this.tp.getTextSize();
        while (this.tp.measureText(str) > f3 && this.tp.getTextSize() > 8.0f) {
            Paint paint = this.tp;
            paint.setTextSize(paint.getTextSize() * 0.92f);
        }
        canvas.drawText(str, f, f2, this.tp);
        this.tp.setTextSize(textSize);
    }

    void drawGrass(Canvas canvas) {
        int biome = biome();
        int i = biome == 1 ? -7687984 : biome == 2 ? -9819616 : -13735382;
        int i2 = biome == 1 ? -2297601 : biome == 2 ? -4698054 : -10764726;
        float f = this.bgS;
        for (int i3 = OV_NONE; i3 < 22; i3++) {
            float f2 = (((i3 + 0.5f) * this.W) / 22) + ((((i3 * 37) % 7) - 3) * f);
            float sin = ((float) Math.sin((this.time * 1.8d) + (i3 * 0.9d))) * f;
            float f3 = f * (((i3 * OV_PORTRAIT) % 3) + 3);
            float f4 = this.groundY + (2.2f * f);
            this.p.setColor(i);
            float f5 = f2 - f;
            float f6 = f4 - f3;
            canvas.drawRect(f5, f6, f2, f4, this.p);
            float f7 = f * 2.0f;
            canvas.drawRect((f2 - f7) + sin, f6 - f, f5 + sin, f6 + f, this.p);
            this.p.setColor(i2);
            float f8 = f4 - (f3 * 0.8f);
            float f9 = f2 + f;
            canvas.drawRect(f2, f8, f9, f4, this.p);
            canvas.drawRect(f9 + sin, f8 - f, f2 + f7 + sin, f8 + (0.5f * f), this.p);
        }
    }

    void drawGroundHud(Canvas canvas, long j) {
        float f;
        if (rhythmOn()) {
            float f2 = this.groundY;
            float f3 = this.pad;
            float f4 = f2 + (f3 * 1.6f);
            float f5 = this.battleB - (f3 * 0.6f);
            float f6 = (f4 + f5) / 2.0f;
            this.tp.setTypeface(this.pixTf);
            this.tp.setFakeBoldText(false);
            if (this.combo >= 2) {
                this.tp.setTextAlign(Paint.Align.LEFT);
                this.tp.setTextSize(this.pad * 1.25f);
                this.tp.setColor(-15331286);
                String str = "КОМБО " + this.combo;
                float f7 = this.pad;
                canvas.drawText(str, 1.7f * f7, f6 - (f7 * 0.2f), this.tp);
                this.tp.setColor(-1);
                String str2 = "КОМБО " + this.combo;
                float f8 = this.pad;
                canvas.drawText(str2, f8 * 1.5f, f6 - (f8 * 0.4f), this.tp);
                this.tp.setTextSize(this.pad * 1.6f);
                this.tp.setColor(this.combo >= 25 ? -26051 : -11430);
                String str3 = "x" + String.format(Locale.US, "%.2f", Double.valueOf(comboMult()));
                float f9 = this.pad;
                canvas.drawText(str3, f9 * 1.5f, (f9 * 1.8f) + f6, this.tp);
            }
            float f10 = 1.0f;
            if (this.taps < 5 || this.overlay != 0) {
                f = 1.0f;
            } else {
                int floor = (int) (Math.floor(beatPos() / this.BEAT) % 4.0d);
                int i = OV_NONE;
                while (i < 4) {
                    float f11 = this.pad;
                    float f12 = (this.W * 0.29f) + (i * f11 * 2.4f);
                    float f13 = f11 * (i == 0 ? 0.75f : 0.55f);
                    boolean z = i == floor;
                    this.p.setColor(z ? i == 0 ? -11430 : -1 : 1442840575);
                    if (z) {
                        f13 *= (this.beatPulse * 0.6f) + f10;
                    }
                    canvas.drawRect(f12 - f13, f6 - f13, f12 + f13, f13 + f6, this.p);
                    i++;
                    floor = floor;
                    f10 = 1.0f;
                }
                f = 1.0f;
            }
            boolean z2 = this.isekaiGauge >= 100.0f;
            RectF rectF = this.isekaiBtn;
            int i2 = this.W;
            rectF.set(i2 * 0.5f, f4, i2 - (this.pad * 1.5f), f5);
            if (z2 && this.isekaiT < 0.0f) {
                float sin = (float) ((Math.sin(this.time * 6.0f) * 0.5d) + 0.5d);
                panel(canvas, this.isekaiBtn, this.pad, Color.rgb((int) ((50.0f * sin) + 200.0f), (int) ((sin * 40.0f) + 120.0f), 40));
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setTextSize(this.pad * 1.5f);
                this.tp.setColor(-1);
                float centerX = this.isekaiBtn.centerX();
                float f14 = this.pad;
                canvas.drawText("ИСЕКАЙ!", centerX + (1.5f * f14), (f14 * 0.7f) + f6, this.tp);
                float f15 = this.pad * 0.11f;
                canvas.save();
                canvas.scale(-1.0f, f, this.isekaiBtn.left + (this.pad * SUPER_END), 0.0f);
                blit(canvas, this.bmTruck, this.isekaiBtn.left + (this.pad * SUPER_END), f6 + ((this.bmTruck.getHeight() * f15) / 2.0f), f15, 1.0f, 1.0f, this.bp);
                canvas.restore();
            } else if (this.isekaiGauge > 0.0f || this.taps > 30) {
                float f16 = this.isekaiBtn.left + (this.pad * 4.0f);
                float f17 = this.isekaiBtn.right;
                float f18 = this.pad * 1.1f;
                this.p.setColor(-1441394646);
                float f19 = f18 / 2.0f;
                float f20 = f6 - f19;
                float f21 = f6 + f19;
                canvas.drawRect(f16, f20, f17, f21, this.p);
                this.p.setColor(-24512);
                float f22 = f17 - f16;
                canvas.drawRect(f16, f20, f16 + ((this.isekaiGauge * f22) / 100.0f), f21, this.p);
                this.p.setColor(1442840575);
                canvas.drawRect(f16, f20, f16 + ((f22 * this.isekaiGauge) / 100.0f), f20 + (this.pad * 0.3f), this.p);
                float f23 = this.pad * 0.09f;
                canvas.save();
                canvas.scale(-1.0f, f, this.isekaiBtn.left + (this.pad * 2.0f), 0.0f);
                blit(canvas, this.bmTruck, this.isekaiBtn.left + (this.pad * 2.0f), f6 + ((this.bmTruck.getHeight() * f23) / 2.0f), f23, 1.0f, 1.0f, this.bp);
                canvas.restore();
            }
            this.tp.setTypeface(Typeface.DEFAULT);
        }
    }

    void drawHeader(Canvas canvas, long j) {
        this.p.setColor(-14937037);
        canvas.drawRect(0.0f, 0.0f, this.W, this.headerB, this.p);
        this.tp.setTextAlign(Paint.Align.LEFT);
        this.tp.setColor(-1);
        this.tp.setTextSize(this.pad * 2.2f);
        this.tp.setFakeBoldText(true);
        canvas.drawText(LIFE_NAME[this.lifeStageIdx], this.pad * 2.0f, this.headerB * 0.33f, this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setTextAlign(Paint.Align.RIGHT);
        this.tp.setColor(-4609816);
        this.tp.setTextSize(this.pad * 1.7f);
        canvas.drawText("Жизнь №" + this.lifeNo + " · Этап " + this.stage, this.W - (this.pad * 13.5f), this.headerB * 0.33f, this.tp);
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setTextSize(this.pad * 2.4f);
        this.tp.setColor(-1);
        canvas.drawText("⚙", this.muteBtn.centerX(), this.headerB * 0.34f, this.tp);
        canvas.drawText("👭", this.partyBtn.centerX(), this.headerB * 0.34f, this.tp);
        if (Integer.bitCount(this.compMask) > 0 && this.compSel < 0) {
            this.p.setColor(-46502);
            float f = this.partyBtn.right;
            float f2 = this.pad;
            canvas.drawCircle(f - f2, 1.4f * f2, f2 * 0.6f, this.p);
        }
        this.tp.setTextSize(this.pad * 2.3f);
        this.tp.setTextAlign(Paint.Align.LEFT);
        this.tp.setFakeBoldText(true);
        float f3 = this.headerB * 0.66f;
        float f4 = this.pad;
        float f5 = f3 + (0.25f * f4);
        float f6 = f4 * 0.3f;
        blit(canvas, this.bmCoin, f4 * 3.0f, f5, f6, 1.0f, 1.0f, this.bp);
        Paint paint = this.tp;
        float f7 = this.goldPulse;
        paint.setColor(f7 > 0.0f ? Color.rgb(255, ((int) (44.0f * f7)) + 211, ((int) (f7 * 165.0f)) + 90) : -11430);
        this.tp.setTypeface(this.pixTf);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.75f * ((this.goldPulse * 0.18f) + 1.0f));
        canvas.drawText(fmt(this.gold), this.pad * 5.3f, this.headerB * 0.66f, this.tp);
        this.tp.setTextSize(this.pad * 1.75f);
        blit(canvas, this.bmMana, this.W * 0.38f, f5, f6, 1.0f, 1.0f, this.bp);
        this.tp.setColor(-4609793);
        canvas.drawText(fmt(this.mana), (this.W * 0.38f) + ((this.bmMana.getWidth() * f6) / 2.0f) + (this.pad * 0.6f), this.headerB * 0.66f, this.tp);
        this.tp.setColor(-8395009);
        this.tp.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(fmt(this.souls), this.W - (this.pad * 2.0f), this.headerB * 0.66f, this.tp);
        blit(canvas, this.bmSoul, ((this.W - (this.pad * SUPER_END)) - this.tp.measureText(fmt(this.souls))) - ((this.bmSoul.getWidth() * f6) / 2.0f), f5, f6, 1.0f, 1.0f, this.bp);
        this.tp.setFakeBoldText(false);
        this.tp.setTypeface(Typeface.DEFAULT);
        this.tp.setTextSize(this.pad * SUPER_IMPACT);
        this.tp.setTextAlign(Paint.Align.LEFT);
        boolean z = j < this.frenzyUntil;
        this.tp.setColor(z ? -25990 : -7569238);
        String str = "+" + fmt(autoDps()) + " урона/с   +" + fmt(manaSec()) + " маны/с   сила ×" + fmt(soulMult() * achMult()) + (this.stars > 0 ? "  ✦" + this.stars : "");
        if (z) {
            str = "🔥 ЯРОСТЬ ×3 (" + (((this.frenzyUntil - j) / 1000) + 1) + "с)   " + str;
        }
        String str2 = str;
        float f8 = this.pad;
        drawFit(canvas, str2, f8 * 2.0f, 0.9f * this.headerB, this.W - (f8 * 4.0f));
    }

    void drawHero(Canvas var1) {
       var1.save();
       if (this.isekaiT >= 0.0F) {
          float var2 = (float)this.bmTruck.getWidth();
          float var3 = this.hu;
          var2 = Math.abs(this.truckX() - this.heroX) / (var2 * var3 * 1.6F * 0.9F);
          if (var2 < 1.0F) {
             var1.translate(0.0F, -((float)Math.cos((double)var2 * Math.PI / 2.0)) * this.hu * 16.0F);
          }
       }

       if (this.overlay == 11 && this.superType == 0) {
          float var9 = this.superT;
          if (var9 < 0.3F) {
             var9 = 0.0F;
          } else if (var9 < 0.45F) {
             var9 = (var9 - 0.3F) / 0.15F;
          } else if (var9 < 1.75F) {
             var9 = 1.0F;
          } else {
             var9 = Math.max(0.0F, 1.0F - (var9 - 1.75F) / 0.4F);
          }

          var1.translate((this.mobX - this.heroX - this.mobR * 1.6F) * var9, 0.0F);
       }

       if (this.overlay == 6) {
          float var5 = (this.heroTop() + this.groundY) / 2.0F;
          if (!this.deathCrash) {
             float var11 = this.deathT;
             if (var11 > 0.4F) {
                var1.translate((float)Math.sin((double)(var11 * 60.0F)) * this.hu * 0.6F, 0.0F);
             }
          } else {
             float var6 = this.deathT - 1.2F;
             if (this.deathKind == 0) {
                float var14 = -var6;
                float var7 = (float)this.W;
                float var12 = this.battleB;
                float var4 = this.battleT;
                var1.translate(var7 * var14 * 0.8F, (var12 - var4) * var14 * 1.6F + var6 * var6 * (var12 - var4) * 0.9F);
                var1.rotate(var14 * 900.0F, this.heroX, var5);
             } else {
                float var13 = Math.min(1.0F, var6 * 8.0F);
                var1.scale(0.7F * var13 + 1.0F, 1.0F - var13 * 0.78F, this.heroX, this.groundY);
             }
          }
       }

       this.drawHeroInner(var1);
       var1.restore();
       if (this.overlay == 6 && !this.deathCrash && this.deathT > 0.4F) {
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setTextSize(this.pad * 4.0F);
          this.tp.setFakeBoldText(true);
          this.tp.setColor(-46518);
          var1.drawText("!", this.heroX, this.heroTop() - this.pad * 2.0F, this.tp);
          this.tp.setFakeBoldText(false);
       }
    }

    void drawHeroInner(Canvas canvas) {
        float f = this.hu * ((this.lifeStageIdx * 0.05f) + 0.8f);
        float round = ((Math.round(Math.sin(this.time * 3.0f) * 1.2000000476837158d) * f) * 0.5f) - ((this.beatPulse * f) * IMPACT);
        Bitmap[] bitmapArr = this.bmHero;
        int i = this.lifeStageIdx;
        Bitmap bitmap = bitmapArr[i];
        if (i >= 3) {
            this.p.setColor(ROBE[i]);
            this.p.setAlpha((int) ((Math.sin(this.time * 4.0f) * 30.0d) + 45.0d));
            canvas.drawCircle(this.heroX, this.groundY - ((bitmap.getHeight() * f) * 0.45f), bitmap.getHeight() * f * 0.7f, this.p);
            this.p.setAlpha(255);
        }
        this.p.setColor(1426063360);
        RectF rectF = this.r;
        float f2 = this.heroX;
        float f3 = f * 7.0f;
        float f4 = this.groundY;
        rectF.set(f2 - f3, f4 - f, f2 + f3, f4 + f);
        canvas.drawOval(this.r, this.p);
        int i2 = this.lifeStageIdx;
        this.p.setColor(i2 == 0 ? -5720 : i2 >= 4 ? -5213953 : -19906);
        this.p.setAlpha((int) ((this.heroCast * 50.0f) + 30.0f + (Math.sin(this.time * 5.0f) * 10.0d)));
        RectF rectF2 = this.r;
        float f5 = this.heroX;
        float f6 = this.groundY;
        float f7 = 1.8f * f;
        rectF2.set((f * 2.0f) + f5, f6 - f7, f5 + (22.0f * f), f6 + f7);
        canvas.drawOval(this.r, this.p);
        this.p.setAlpha(255);
        float f8 = this.heroCast;
        float f9 = 0.06f * f8;
        float f10 = f8 * f * 1.6f;
        float f11 = 1.0f - f9;
        blit(canvas, bitmap, this.heroX + f10, this.groundY + f + round, f, f9 + 1.0f, f11, this.bp);
        if (rhythmOn()) {
            float f12 = this.heroX + f10;
            float height = ((this.groundY + f) + round) - ((bitmap.getHeight() * f) * f11);
            this.p.setColor(-3921286);
            float f13 = f * 8.0f;
            float f14 = f * 6.0f;
            float f15 = height + f14;
            float f16 = height + (11.0f * f);
            canvas.drawRect(f12 - f13, f15, f12 - f14, f16, this.p);
            canvas.drawRect(f12 + f14, f15, f12 + f13, f16, this.p);
            this.p.setColor(-45149);
            float f17 = f * 7.5f;
            float f18 = height + f3;
            float f19 = f * 6.5f;
            float f20 = height + (f * 10.0f);
            canvas.drawRect(f12 - f17, f18, f12 - f19, f20, this.p);
            canvas.drawRect(f12 + f19, f18, f12 + f17, f20, this.p);
        }
        float f21 = this.heroX + (10.0f * f) + (f10 * 1.3f);
        float f22 = (this.groundY - (24.0f * f)) - ((this.heroCast * f) * 2.0f);
        this.p.setColor(-15331286);
        float f23 = f * 1.5f;
        float f24 = f21 - f23;
        float f25 = f21 + f23;
        canvas.drawRect(f24, f22, f25, this.groundY + f, this.p);
        this.p.setColor(-7644629);
        float f26 = f * 0.5f;
        float f27 = f21 - f26;
        canvas.drawRect(f27, f22, f21 + f26, this.groundY, this.p);
        int i3 = this.lifeStageIdx;
        int i4 = i3 == 0 ? -5720 : i3 >= 4 ? -5213953 : -19906;
        this.p.setColor(i4);
        this.p.setAlpha((int) ((this.heroCast * 120.0f) + 70.0f + (Math.sin(this.time * 5.0f) * 30.0d)));
        canvas.drawCircle(f21, f22, ((this.heroCast * 3.0f) + 4.0f) * f, this.p);
        this.p.setAlpha(255);
        this.p.setColor(-15331286);
        float f28 = 2.5f * f;
        canvas.drawRect(f21 - f28, f22 - f28, f21 + f28, f22 + f28, this.p);
        this.p.setColor(i4);
        float f29 = f22 - f23;
        canvas.drawRect(f24, f29, f25, f22 + f23, this.p);
        this.p.setColor(-1);
        canvas.drawRect(f24, f29, f27, f22 - f26, this.p);
        float f30 = this.heroCast;
        if (f30 > 0.35f) {
            float f31 = f * ((f30 * 9.0f) + 3.0f);
            float f32 = f * 0.7f;
            this.p.setColor(-1);
            this.p.setAlpha((int) (Math.min(1.0f, (this.heroCast - 0.35f) * 2.2f) * 255.0f));
            canvas.drawRect(f21 - f31, f22 - f32, f21 + f31, f22 + f32, this.p);
            canvas.drawRect(f21 - f32, f22 - f31, f21 + f32, f22 + f31, this.p);
            this.p.setColor(i4);
            this.p.setAlpha((int) (this.heroCast * 200.0f));
            float f33 = f31 * 0.5f;
            float f34 = f21 - f33;
            float f35 = f22 - f33;
            float f36 = f32 * 2.0f;
            canvas.drawRect(f34, f35, f34 + f36, f35 + f36, this.p);
            float f37 = f21 + f33;
            float f38 = f22 + f33;
            canvas.drawRect(f37 - f36, f38 - f36, f37, f38, this.p);
            this.p.setAlpha(255);
        }
    }

    void drawIsekai(Canvas var1) {
       float var2 = this.victimT;
       if (var2 >= 0.0F) {
          var2 /= 0.9F;
          float var3 = var2 * var2 * (3.0F - var2 * 2.0F);
          float var5 = (float)this.W * 0.86F;
          float var4 = this.battleT;
          var4 += (this.groundY - var4) * 0.14F;
          this.p.setStyle(Style.STROKE);
          this.p.setStrokeWidth(this.pad * 0.5F);

          for (int var8 = 0; var8 < 3; var8++) {
             float var6 = this.pad * (5.0F - (float)var8 * 1.4F) * (Math.min(1.0F, var2 * 3.0F) * 0.7F + 0.3F);
             Paint var11 = this.p;
             int var10 = var8 % 2;
             int var9;
             if (var10 == 0) {
                var9 = -5213953;
             } else {
                var9 = -8395009;
             }

             var11.setColor(var9);
             this.p.setAlpha(200);
             var1.save();
             float var7 = this.time;
             byte var26;
             if (var10 == 0) {
                var26 = 1;
             } else {
                var26 = -1;
             }

             var1.rotate(var7 * 200.0F * (float)var26, var5, var4);
             RectF var27 = this.r;
             var7 = 0.75F * var6;
             var27.set(var5 - var6, var4 - var7, var6 + var5, var7 + var4);
             var1.drawOval(this.r, this.p);
             var1.restore();
          }

          this.p.setStyle(Style.FILL);
          this.p.setAlpha(255);
          float var21 = this.victimX;
          var5 = var21 + (var5 - var21) * var3;
          var21 = this.victimY;
          var4 = var21 + (var4 - var21) * var3 - (float)Math.sin((double)var2 * Math.PI) * this.pad * 6.0F;
          var3 = this.mu * (1.0F - var3 * 0.85F);
          var1.save();
          var1.rotate(var2 * 900.0F, var5, var4);
          Bitmap var28 = this.victimBmp;
          this.blit(var1, var28, var5, var4 + (float)var28.getHeight() * var3 / 2.0F, var3, 1.0F, 1.0F, this.bp);
          var1.restore();
       }

       if (this.isekaiT >= 0.0F) {
          float var23 = this.hu * 1.6F;
          float var20 = (float)this.bmTruck.getWidth() * var23;
          float var15 = this.truckX();
          this.p.setColor(-1426063361);

          for (int var25 = 0; var25 < 5; var25++) {
             var2 = this.groundY - ((float)var25 * 3.5F + 3.0F) * var23;
             float var18 = var15 - var20 / 2.0F - var23 * 2.0F - (float)(var25 % 2) * var23 * 4.0F;
             var1.drawRect(var18 - (float)(var25 * 7 % 10 + 8) * var23, var2, var18, var2 + 0.6F * var23, this.p);
          }

          var1.save();
          var1.scale(-1.0F, 1.0F, var15, 0.0F);
          this.blit(
             var1, this.bmTruck, var15, this.groundY + 1.5F * var23 + (float)Math.sin((double)(this.isekaiT * 40.0F)) * var23 * 0.3F, var23, 1.0F, 1.0F, this.bp
          );
          var1.restore();
          this.p.setColor(1442837160);
          var1.drawCircle(var15 + var20 / 2.0F, this.groundY - 9.0F * var23, var23 * 5.0F, this.p);
       }
    }

    void drawMini(Canvas canvas) {
        int i;
        String str;
        StringBuilder append;
        int i2;
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setColor(-11430);
        this.tp.setTextSize(this.pad * 3.0f);
        this.tp.setFakeBoldText(true);
        canvas.drawText(MINI_NAME[this.miniType], this.W / 2.0f, this.H * 0.17f, this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setColor(-4609816);
        this.tp.setTextSize(this.pad * 1.8f);
        canvas.drawText(MINI_RULE[this.miniType], this.W / 2.0f, this.H * 0.215f, this.tp);
        int i3 = OV_NONE;
        while (i3 < 3) {
            float f = (this.W / 2.0f) + ((i3 - 1) * this.pad * 4.0f);
            this.p.setColor(i3 < this.miniRound ? -11430 : -12963760);
            canvas.drawCircle(f, this.H * 0.25f, this.pad * 1.0f, this.p);
            i3++;
        }
        int i4 = this.miniPhase;
        if (i4 == 0) {
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 3.5f);
            this.tp.setFakeBoldText(true);
            canvas.drawText(this.miniT < 0.7f ? "Приготовься..." : "Начали!", this.W / 2.0f, this.H * 0.5f, this.tp);
            this.tp.setFakeBoldText(false);
            if (bossFight()) {
                this.tp.setColor(-25958);
                this.tp.setTextSize(this.pad * 1.8f);
                canvas.drawText("Каждая ★ — супер-атака на 10% здоровья босса!", this.W / 2.0f, this.H * 0.57f, this.tp);
                if (this.combo >= 2) {
                    this.tp.setColor(-11430);
                    canvas.drawText("Комбо " + this.combo + " сохранено и усилит удар!", this.W / 2.0f, this.H * 0.61f, this.tp);
                    return;
                }
                return;
            }
            return;
        }
        if (i4 == 2) {
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * SUPER_END);
            canvas.drawText("Результат", this.W / 2.0f, this.H * 0.38f, this.tp);
            this.tp.setTextSize(this.pad * 5.0f);
            this.tp.setColor(-11430);
            StringBuilder sb = new StringBuilder();
            int i5 = OV_NONE;
            while (i5 < 3) {
                sb.append(i5 < this.miniScore ? "★" : "☆");
                i5++;
            }
            canvas.drawText(sb.toString(), this.W / 2.0f, this.H * 0.47f, this.tp);
            this.tp.setTextSize(this.pad * 2.0f);
            this.tp.setColor(-1);
            String[] split = this.miniReward.split("\n");
            float f2 = this.H * 0.55f;
            int i6 = OV_NONE;
            for (int length = split.length; i6 < length; length = length) {
                String str2 = split[i6];
                int i7 = this.W;
                drawFit(canvas, str2, i7 / 2.0f, f2, i7 - (this.pad * 4.0f));
                f2 += this.pad * 3.2f;
                i6++;
            }
            if (this.miniT > 0.9f) {
                this.tp.setTextSize(this.pad * 1.7f);
                this.tp.setColor(-7569238);
                boolean z = this.miniScore > 0 && bossFight();
                if (z) {
                    this.tp.setColor(-11430);
                    this.tp.setAlpha((int) ((Math.sin(this.time * 6.0f) * 85.0d) + 170.0d));
                    this.tp.setFakeBoldText(true);
                    this.tp.setTextSize(this.pad * 2.2f);
                }
                canvas.drawText(z ? "⚡ Тапни — обрушить супер-атаку!" : "тап — продолжить бой", this.W / 2.0f, this.H * 0.85f, this.tp);
                this.tp.setAlpha(255);
                this.tp.setFakeBoldText(false);
                return;
            }
            return;
        }
        int i8 = this.miniType;
        if (i8 == 0) {
            int i9 = this.W;
            float f3 = i9 * 0.1f;
            float f4 = i9 * 0.8f;
            float f5 = this.H * 0.48f;
            float f6 = 4.0f * this.pad;
            float f7 = f6 / 2.0f;
            float f8 = f5 - f7;
            float f9 = f5 + f7;
            this.r.set(f3, f8, f3 + f4, f9);
            this.p.setColor(-14016440);
            RectF rectF = this.r;
            float f10 = this.pad;
            canvas.drawRoundRect(rectF, f10, f10, this.p);
            if (this.miniPhase == 1) {
                RectF rectF2 = this.r;
                float f11 = this.zoneL;
                rectF2.set((f4 * f11) + f3, f8, ((f11 + this.zoneW) * f4) + f3, f9);
                this.p.setColor(-12673970);
                canvas.drawRect(this.r, this.p);
                float f12 = ((this.zoneL + (this.zoneW / 2.0f)) * f4) + f3;
                this.p.setColor(-11430);
                float f13 = 0.035f * f4;
                i2 = -1;
                canvas.drawRect(f12 - f13, f8, f13 + f12, f9, this.p);
                float f14 = f3 + (f4 * this.swPos);
                this.p.setColor(-1);
                float f15 = this.pad;
                float f16 = f6 * 0.85f;
                canvas.drawRect(f14 - (f15 * 0.45f), f5 - f16, f14 + (f15 * 0.45f), f5 + f16, this.p);
            } else {
                i2 = -1;
            }
            this.tp.setTextSize(this.pad * 6.0f);
            this.tp.setColor(i2);
            canvas.drawText("🗡", this.W / 2.0f, this.H * 0.4f, this.tp);
            this.tp.setTextSize(this.pad * 1.8f);
            this.tp.setColor(-7569238);
            canvas.drawText("тапни в любом месте", this.W / 2.0f, this.H * 0.56f, this.tp);
        } else {
            boolean z2 = i8 == 2;
            if (z2 && i4 == 1) {
                this.tp.setTextSize(this.pad * 2.0f);
                this.tp.setColor(-1);
                canvas.drawText("Враг: " + EL_FOE[this.elFoe], this.W / 2.0f, this.H * 0.3f, this.tp);
                this.tp.setTextSize(this.pad * 5.0f);
                canvas.drawText(EL_ICON[this.elFoe], this.W / 2.0f, this.H * 0.37f, this.tp);
                float max = Math.max(0.0f, this.elTime / (3.6f - (Math.min(this.miniRound, 2) * 0.8f)));
                this.p.setColor(-14016440);
                int i10 = this.W;
                int i11 = this.H;
                i = -4609816;
                canvas.drawRect(i10 * 0.2f, i11 * 0.39f, i10 * 0.8f, (i11 * 0.39f) + this.pad, this.p);
                this.p.setColor(max > 0.35f ? -10825217 : -42406);
                int i12 = this.W;
                int i13 = this.H;
                canvas.drawRect(i12 * 0.2f, i13 * 0.39f, (i12 * 0.2f) + (i12 * 0.6f * max), (i13 * 0.39f) + this.pad, this.p);
            } else {
                i = -4609816;
                if (!z2) {
                    this.tp.setTextSize(this.pad * 2.2f);
                    this.tp.setColor(-1);
                    int i14 = this.miniPhase;
                    if (i14 == 1 && this.runeShowing) {
                        append = new StringBuilder().append("Смотри... ").append(this.runeShowIdx).append("/").append(this.runeLen);
                    } else if (i14 == 1) {
                        append = new StringBuilder().append("Повтори! (").append(this.runeIdx).append("/").append(this.runeLen).append(")");
                    } else {
                        append = new StringBuilder();
                    }
                    str = append.toString();
                    canvas.drawText(str, this.W / 2.0f, this.H * 0.35f, this.tp);
                }
            }
            int i15 = OV_NONE;
            while (i15 < 4) {
                float padCX = padCX(i15);
                float padCY = padCY(i15);
                RectF rectF3 = this.r;
                int i16 = this.W;
                int i17 = this.H;
                rectF3.set(padCX - (i16 * 0.17f), padCY - (i17 * 0.07f), (i16 * 0.17f) + padCX, (i17 * 0.07f) + padCY);
                boolean z3 = !z2 && this.runeLit == i15;
                this.p.setColor(z3 ? -1 : EL_COLOR[i15]);
                if (!z2 && !z3) {
                    this.p.setAlpha(150);
                }
                RectF rectF4 = this.r;
                float f17 = this.pad;
                canvas.drawRoundRect(rectF4, f17 * 2.0f, f17 * 2.0f, this.p);
                this.p.setAlpha(255);
                this.tp.setTextSize(this.pad * 4.5f);
                this.tp.setColor(-1);
                canvas.drawText(EL_ICON[i15], padCX, (this.pad * IMPACT) + padCY, this.tp);
                if (z2) {
                    this.tp.setTextSize(this.pad * 1.5f);
                    canvas.drawText(EL_NAME[i15], padCX, padCY + (this.H * 0.055f), this.tp);
                }
                i15++;
            }
            if (z2) {
                this.tp.setTextSize(this.pad * 1.6f);
                this.tp.setColor(i);
                canvas.drawText("💧 гасит 🔥   ⚡ бьёт 💧", this.W / 2.0f, this.H * 0.76f, this.tp);
                canvas.drawText("🌿 заземляет ⚡   🔥 сжигает 🌿", this.W / 2.0f, this.H * 0.795f, this.tp);
            }
        }
        if (this.miniMsgT > 0.0f) {
            this.tp.setTextSize(this.pad * 3.0f);
            this.tp.setFakeBoldText(true);
            this.tp.setColor(this.miniMsgColor);
            this.tp.setAlpha((int) (Math.min(1.0f, this.miniMsgT * 2.0f) * 255.0f));
            canvas.drawText(this.miniMsg, this.W / 2.0f, this.H * 0.88f, this.tp);
            this.tp.setAlpha(255);
            this.tp.setFakeBoldText(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void drawMonster(Canvas var1) {
       boolean var16 = this.bossFight();
       float var7 = this.mu;
       float var6;
       if (var16) {
          var6 = 1.3F;
       } else {
          var6 = 1.0F;
       }

       var7 *= var6;
       var6 = var7;
       if (this.mobBitmap().getWidth() > 24) {
          var6 = var7 * 0.62F;
       }

       float var9;
       float var10;
       Bitmap var18;
       label99: {
          var9 = this.mobSpawn * 0.7F + 0.3F;
          var10 = (float)Math.sin((double)(this.time * 4.0F)) * 0.04F + this.mobHit * 0.14F + this.beatPulse * 0.07F;
          var18 = this.mobBitmap();
          if (var16) {
             var7 = this.bossIntroT;
             if (var7 > 1.0F) {
                float var8 = (1.6F - var7) / 0.6F;
                var7 = -(this.groundY - this.battleT);
                var8 = 1.0F - var8;
                var7 = var7 * var8 * var8;
                break label99;
             }
          }

          var7 = 0.0F;
       }

       if (var16) {
          for (int var14 = 0; var14 < 12; var14++) {
             float var11 = this.time;
             double var4 = (double)var11;
             double var2 = (double)var14;
             float var32 = (float)((var4 * 0.5 + var2 / 12.0) % 1.0);
             float var12 = this.mobX;
             float var13 = (float)Math.sin(var2 * 2.4 + (double)var11);
             var11 = this.mobR;
             var12 += var13 * var11 * 0.9F;
             var13 = this.groundY - var11 * var32 * 2.6F + var7;
             var11 = this.hu * (1.6F - 1.1F * var32);
             Paint var17 = this.p;
             int var15;
             if (this.towerFight) {
                var15 = -24512;
             } else {
                var15 = -5234614;
             }

             var17.setColor(var15);
             this.p.setAlpha((int)((1.0F - var32) * 150.0F));
             var1.drawRect(var12 - var11, var13 - var11, var12 + var11, var13 + var11, this.p);
          }

          this.p.setAlpha(255);
       }

       this.p.setColor(1426063360);
       float var33;
       if (var7 < 0.0F) {
          var33 = Math.max(0.2F, var7 / (this.groundY - this.battleT) + 1.0F);
       } else {
          var33 = 1.0F;
       }

       RectF var61 = this.r;
       float var51 = this.mobX;
       float var54 = var6 * 8.0F * var9 * var33;
       var33 = this.groundY;
       var61.set(var51 - var54, var33 - var6, var51 + var54, var33 + var6);
       var1.drawOval(this.r, this.p);
       var33 = this.mobX;
       var51 = this.groundY;
       Paint var62;
       if (this.mobHit > 0.7F) {
          var62 = this.flashP;
       } else {
          var62 = this.bp;
       }

       this.blit(var1, var18, var33, var51 + var6 + var7, var6 * var9, var10 + 1.0F, 1.0F - var10, var62);
       if (this.overlay != 11 || this.superHit) {
          if (!var16 || !(this.bossIntroT > 0.25F)) {
             var6 = (float)this.W * 0.42F;
             var7 = this.mobX - var6 / 2.0F;
             var33 = this.battleT + this.pad * 8.0F;
             this.tp.setTextAlign(Align.CENTER);
             this.tp.setTextSize(this.pad * 2.0F);
             this.tp.setFakeBoldText(true);
             StringBuilder var77 = new StringBuilder();
             String var63;
             if (var16) {
                var63 = "\ud83d\udc51 ";
             } else {
                var63 = "";
             }

             String var64 = var77.append(var63).append(this.mobName()).toString();
             this.tp.setColor(-872415232);
             var10 = this.mobX;
             var9 = this.pad;
             var1.drawText(var64, var10 + 0.25F * var9, var33 - var9 * 0.55F, this.tp);
             Paint var78 = this.tp;
             int var56;
             if (var16) {
                var56 = -38278;
             } else if (this.chest) {
                var56 = -11430;
             } else {
                var56 = -1;
             }

             var78.setColor(var56);
             var1.drawText(var64, this.mobX, var33 - this.pad * 0.8F, this.tp);
             this.tp.setFakeBoldText(false);
             this.p.setColor(-16777216);
             var61 = this.r;
             var9 = var7 + var6;
             var61.set(var7, var33, var9, this.pad * 1.6F + var33);
             var61 = this.r;
             var10 = this.pad;
             var1.drawRoundRect(var61, var10 * 0.8F, var10 * 0.8F, this.p);
             var10 = (float)Math.max(0.0, Math.min(1.0, this.monsterHp / this.monsterMax));
             Paint var67 = this.p;
             if (var16) {
                var56 = -2078616;
             } else {
                var56 = -10822550;
             }

             var67.setColor(var56);
             this.r.set(var7, var33, var10 * var6 + var7, this.pad * 1.6F + var33);
             var61 = this.r;
             var10 = this.pad;
             var1.drawRoundRect(var61, var10 * 0.8F, var10 * 0.8F, this.p);
             this.tp.setTextSize(this.pad * 1.3F);
             this.tp.setColor(-1);
             var1.drawText(fmt(Math.max(0.0, this.monsterHp)) + " / " + fmt(this.monsterMax), this.mobX, this.pad * 1.2F + var33, this.tp);
             if (var16) {
                var10 = Math.max(0.0F, this.bossTime / this.bossTimeMax());
                var33 += this.pad * 2.2F;
                this.p.setColor(-16777216);
                this.r.set(var7, var33, var9, this.pad * 0.9F + var33);
                var61 = this.r;
                var9 = this.pad;
                var1.drawRoundRect(var61, var9 * 0.45F, var9 * 0.45F, this.p);
                Paint var70 = this.p;
                float var80;
                int var60 = (var80 = var10 - 0.3F) == 0.0F ? 0 : (var80 < 0.0F ? -1 : 1);
                if (var60 < 0) {
                   var56 = -46518;
                } else {
                   var56 = -16310;
                }

                var70.setColor(var56);
                this.r.set(var7, var33, var6 * var10 + var7, this.pad * 0.9F + var33);
                var61 = this.r;
                var6 = this.pad;
                var1.drawRoundRect(var61, var6 * 0.45F, var6 * 0.45F, this.p);
                this.tp.setTextSize(this.pad * 1.4F);
                Paint var72 = this.tp;
                if (var60 < 0) {
                   var56 = -38294;
                } else {
                   var56 = -8032;
                }

                var72.setColor(var56);
                StringBuilder var79 = new StringBuilder().append("⏳ ").append((int)Math.ceil((double)Math.max(0.0F, this.bossTime))).append("с");
                String var73;
                if (this.trait() > 0) {
                   var73 = "  " + TRAIT_SHORT[this.trait()];
                } else {
                   var73 = "";
                }

                var1.drawText(var79.append(var73).toString(), this.mobX, this.pad * 2.5F + var33, this.tp);
                if (this.storyBoss()) {
                   this.tp.setTextSize(this.pad * 1.45F);
                   this.tp.setFakeBoldText(true);
                   var9 = this.tp.measureText("\ud83d\udcd6 Сюжетный босс");
                   var7 = this.pad;
                   var6 = var33 + 3.6F * var7;
                   var61 = this.r;
                   var33 = this.mobX;
                   var9 = (var9 + var7 * 2.0F) / 2.0F;
                   var61.set(var33 - var9, var6, var33 + var9, var7 * 2.4F + var6);
                   this.p.setColor(-583390704);
                   var61 = this.r;
                   var7 = this.pad;
                   var1.drawRoundRect(var61, var7 * 1.2F, var7 * 1.2F, this.p);
                   this.p.setStyle(Style.STROKE);
                   this.p.setStrokeWidth(this.pad * 0.18F);
                   this.p.setColor(-11430);
                   this.p.setAlpha((int)(Math.sin((double)(this.time * 3.0F)) * 85.0 + 170.0));
                   var61 = this.r;
                   var7 = this.pad;
                   var1.drawRoundRect(var61, var7 * 1.2F, var7 * 1.2F, this.p);
                   this.p.setStyle(Style.FILL);
                   this.p.setAlpha(255);
                   this.tp.setColor(-11430);
                   var1.drawText("\ud83d\udcd6 Сюжетный босс", this.mobX, var6 + this.pad * 1.7F, this.tp);
                   this.tp.setFakeBoldText(false);
                }
             }
          }
       }
    }

    void drawOverlay(Canvas canvas) {
        int i;
        int i2;
        int i3 = this.overlay;
        if (i3 == OV_BEAT) {
            drawBeatFight(canvas);
            return;
        }
        if (i3 == 11) {
            drawSuper(canvas);
            return;
        }
        if (i3 == 10) {
            drawRelicGet(canvas);
            return;
        }
        if (i3 == 6) {
            float max = Math.max(0.0f, Math.min(1.0f, (this.deathT - 2.2f) / 0.9f));
            this.p.setColor(-16777216);
            this.p.setAlpha((int) (max * 255.0f));
            canvas.drawRect(0.0f, 0.0f, this.W, this.H, this.p);
            this.p.setAlpha(255);
            float f = this.deathT;
            if (f > 2.5f) {
                float min = Math.min(1.0f, (f - 2.5f) * 3.0f);
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setTextSize(this.pad * 2.8f);
                this.tp.setFakeBoldText(true);
                this.tp.setColor(-1);
                int i4 = (int) (min * 255.0f);
                this.tp.setAlpha(i4);
                String str = this.deathCap;
                int i5 = this.W;
                drawFit(canvas, str, i5 / 2.0f, 0.45f * this.H, i5 - (this.pad * 4.0f));
                this.tp.setFakeBoldText(false);
                this.tp.setTextSize(this.pad * 1.8f);
                this.tp.setColor(-4609816);
                this.tp.setAlpha(i4);
                canvas.drawText("Жизнь №" + this.lifeNo + " окончена", this.W / 2.0f, this.H * 0.51f, this.tp);
                this.tp.setAlpha(255);
            }
            if (this.deathCrash) {
                float f2 = this.deathT;
                if (f2 <= 1.6f || f2 >= 2.2f) {
                    return;
                }
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setTextSize(this.pad * 1.5f);
                this.tp.setColor(-1711276033);
                canvas.drawText("тап — пропустить", this.W / 2.0f, this.battleB - (this.pad * IMPACT), this.tp);
                return;
            }
            return;
        }
        this.p.setColor(-301332972);
        canvas.drawRect(0.0f, 0.0f, this.W, this.H, this.p);
        this.tp.setTextAlign(Paint.Align.CENTER);
        int i6 = this.overlay;
        if (i6 == 1) {
            if (this.storyTitle != null) {
                this.tp.setColor(-11430);
                this.tp.setTextSize(this.pad * 2.4f);
                this.tp.setFakeBoldText(true);
                String str2 = this.storyTitle;
                int i7 = this.W;
                i2 = -7569238;
                drawFit(canvas, str2, i7 / 2.0f, 0.2f * this.H, i7 - (this.pad * 4.0f));
                this.tp.setFakeBoldText(false);
            } else {
                i2 = -7569238;
            }
            String[] strArr = this.storyCur;
            String[] split = strArr[Math.min(this.storyPage, strArr.length - 1)].split("\n");
            long uptimeMillis = SystemClock.uptimeMillis();
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * SUPER_END);
            this.tp.setAlpha((int) (Math.min(1.0f, (uptimeMillis - this.storyPageAt) / 400.0f) * 255.0f));
            float length = (this.H * 0.42f) - ((split.length * this.pad) * 2.0f);
            int length2 = split.length;
            float f3 = length;
            int i8 = OV_NONE;
            while (i8 < length2) {
                String str3 = split[i8];
                int i9 = this.W;
                drawFit(canvas, str3, i9 / 2.0f, f3, i9 - (this.pad * 3.0f));
                f3 += this.pad * 4.2f;
                i8++;
                uptimeMillis = uptimeMillis;
            }
            this.tp.setAlpha(255);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setColor(i2);
            this.tp.setAlpha(storyTapReady(uptimeMillis) ? (int) ((Math.sin(this.time * 4.0f) * 100.0d) + 150.0d) : OV_NONE);
            canvas.drawText("тап — дальше   (" + (this.storyPage + 1) + "/" + this.storyCur.length + ")", this.W / 2.0f, this.H * 0.85f, this.tp);
            this.tp.setAlpha(255);
            return;
        }
        if (i6 == 2) {
            this.tp.setColor(-8395009);
            this.tp.setTextSize(this.pad * 3.2f);
            this.tp.setFakeBoldText(true);
            canvas.drawText("Реинкарнация", this.W / 2.0f, this.H * 0.28f, this.tp);
            this.tp.setFakeBoldText(false);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.0f);
            canvas.drawText("Ты потеряешь золото, ману и улучшения,", this.W / 2.0f, this.H * 0.36f, this.tp);
            canvas.drawText("но получишь " + fmt(this.pendingSouls) + " душ.", this.W / 2.0f, this.H * 0.4f, this.tp);
            this.tp.setColor(-11430);
            canvas.drawText("Души навсегда усиливают урон (и немного золото)", this.W / 2.0f, this.H * 0.45f, this.tp);
            canvas.drawText("Сила: ×" + fmt(soulMult()) + " → ×" + fmt(soulPart(this.souls + this.pendingSouls) * starMult()), this.W / 2.0f, this.H * 0.49f, this.tp);
            if (this.alt[OV_NONE] > 0) {
                this.tp.setColor(-8395009);
                canvas.drawText("Новая жизнь начнётся с этапа " + startStage(), this.W / 2.0f, this.H * 0.53f, this.tp);
            }
            RectF rectF = this.r;
            float f4 = this.pad;
            int i10 = this.H;
            rectF.set(f4 * 3.0f, i10 * 0.6f, (this.W / 2.0f) - (f4 * 1.5f), i10 * 0.7f);
            this.p.setColor(-12963760);
            RectF rectF2 = this.r;
            float f5 = this.pad;
            canvas.drawRoundRect(rectF2, f5 * 1.5f, f5 * 1.5f, this.p);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.2f);
            canvas.drawText("Ещё поживу", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            RectF rectF3 = this.r;
            int i11 = this.W;
            float f6 = this.pad;
            int i12 = this.H;
            rectF3.set((i11 / 2.0f) + (f6 * 1.5f), i12 * 0.6f, i11 - (f6 * 3.0f), i12 * 0.7f);
            this.p.setColor(-11563047);
            RectF rectF4 = this.r;
            float f7 = this.pad;
            canvas.drawRoundRect(rectF4, f7 * 1.5f, f7 * 1.5f, this.p);
            canvas.drawText("Переродиться", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            return;
        }
        if (i6 == 5) {
            this.tp.setColor(-11430);
            this.tp.setTextSize(this.pad * 3.2f);
            this.tp.setFakeBoldText(true);
            canvas.drawText("🎒 Реликвии", this.W / 2.0f, this.H * 0.14f, this.tp);
            this.tp.setFakeBoldText(false);
            this.tp.setColor(-4609816);
            this.tp.setTextSize(this.pad * 1.7f);
            canvas.drawText("Выпадают с боссов (шанс " + relicChance() + "%) и остаются навсегда", this.W / 2.0f, this.H * 0.18f, this.tp);
            float f8 = (this.W - (this.pad * 5.0f)) / 2.0f;
            float f9 = this.H * 0.19f;
            for (int i13 = OV_NONE; i13 < 6; i13++) {
                float f10 = this.pad;
                float f11 = (f10 * 2.0f) + ((i13 % 2) * (f8 + f10));
                float f12 = (this.H * 0.21f) + ((i13 / 2) * (f10 + f9));
                boolean z = this.rel[i13] > 0;
                this.r.set(f11, f12, f11 + f8, f12 + f9);
                this.p.setColor(z ? -13753520 : -15068112);
                RectF rectF5 = this.r;
                float f13 = this.pad;
                canvas.drawRoundRect(rectF5, f13 * IMPACT, f13 * IMPACT, this.p);
                if (z) {
                    this.p.setColor(REL_COLOR[i13]);
                    this.p.setAlpha(60);
                    canvas.drawCircle(this.r.centerX(), (f9 * 0.3f) + f12, 0.2f * f9, this.p);
                    this.p.setAlpha(255);
                }
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setTextSize(0.25f * f9);
                this.tp.setColor(-1);
                canvas.drawText(z ? REL_ICON[i13] : "❔", this.r.centerX(), (0.4f * f9) + f12, this.tp);
                this.tp.setTextSize(this.pad * 1.9f);
                this.tp.setFakeBoldText(true);
                this.tp.setColor(z ? REL_COLOR[i13] : -9543024);
                drawFit(canvas, z ? REL_NAME[i13] : "???", this.r.centerX(), f12 + (0.62f * f9), f8 - (this.pad * 2.0f));
                this.tp.setFakeBoldText(false);
                this.tp.setTextSize(this.pad * 1.5f);
                this.tp.setColor(z ? -1 : -9543024);
                drawFit(canvas, z ? relDesc(i13) : "Ещё не найдена", this.r.centerX(), f12 + (0.78f * f9), f8 - (this.pad * 2.0f));
                if (z) {
                    this.tp.setColor(this.rel[i13] >= 10 ? -11430 : -4609816);
                    this.tp.setTextSize(this.pad * 1.4f);
                    canvas.drawText(this.rel[i13] >= 10 ? "МАКС" : "ур. " + this.rel[i13] + "/10", this.r.centerX(), f12 + (0.92f * f9), this.tp);
                }
            }
            if (this.hasPhones) {
                RectF rectF6 = this.phonesBtn;
                float f14 = this.pad;
                int i14 = this.H;
                rectF6.set(f14 * 2.0f, i14 * 0.83f, this.W - (f14 * 2.0f), i14 * 0.89f);
                panel(canvas, this.phonesBtn, this.pad * IMPACT, this.phonesOn ? -7722390 : -13753520);
                float height = (this.phonesBtn.height() * 0.7f) / this.icPhones.getWidth();
                i = -7569238;
                blit(canvas, this.icPhones, this.phonesBtn.left + (this.pad * 3.5f), this.phonesBtn.centerY() + ((this.icPhones.getHeight() * height) / 2.0f), height, 1.0f, 1.0f, this.bp);
                this.tp.setTextAlign(Paint.Align.LEFT);
                this.tp.setTextSize(this.pad * 1.8f);
                this.tp.setColor(-1);
                this.tp.setFakeBoldText(true);
                canvas.drawText("Наушники Битмейкера", this.phonesBtn.left + (this.pad * 7.0f), this.phonesBtn.centerY() - (this.pad * 0.2f), this.tp);
                this.tp.setFakeBoldText(false);
                this.tp.setTextSize(this.pad * 1.4f);
                this.tp.setColor(this.phonesOn ? -20264 : -7569238);
                canvas.drawText(this.phonesOn ? "НАДЕТЫ — ритм-режим включён (тап — снять)" : "СНЯТЫ — тап, чтобы надеть", this.phonesBtn.left + (this.pad * 7.0f), this.phonesBtn.centerY() + (this.pad * 1.7f), this.tp);
                this.tp.setTextAlign(Paint.Align.CENTER);
            } else {
                i = -7569238;
            }
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setColor(i);
            canvas.drawText("тап — закрыть", this.W / 2.0f, this.H * 0.94f, this.tp);
            return;
        }
        if (i6 == 4) {
            int dailyDay = dailyDay();
            this.tp.setColor(-11430);
            this.tp.setTextSize(this.pad * 3.2f);
            this.tp.setFakeBoldText(true);
            canvas.drawText("🎁 Ежедневный подарок", this.W / 2.0f, this.H * 0.25f, this.tp);
            this.tp.setFakeBoldText(false);
            this.tp.setColor(-4609816);
            this.tp.setTextSize(this.pad * 1.9f);
            StringBuilder append = new StringBuilder().append("Серия входов: ").append(this.streak).append(" ");
            int i15 = this.streak;
            canvas.drawText(append.append((i15 % 10 != 1 || i15 % 100 == 11) ? (i15 % 10 < 2 || i15 % 10 > 4 || (i15 % 100 >= 12 && i15 % 100 <= OV_SETTINGS)) ? "дней" : "дня" : "день").toString(), this.W / 2.0f, this.H * 0.3f, this.tp);
            float f15 = (this.W - (this.pad * 4.0f)) / 7.0f;
            int i16 = 1;
            while (i16 <= 7) {
                RectF rectF7 = this.r;
                float f16 = this.pad;
                int i17 = i16 - 1;
                int i18 = this.H;
                rectF7.set((f16 * 2.0f) + (i17 * f15) + (f16 * 0.3f), i18 * 0.35f, ((f16 * 2.0f) + (i16 * f15)) - (f16 * 0.3f), (i18 * 0.35f) + (1.4f * f15));
                this.p.setColor(i16 == dailyDay ? -4158934 : i16 < dailyDay ? -14015424 : -14411203);
                RectF rectF8 = this.r;
                float f17 = this.pad;
                canvas.drawRoundRect(rectF8, f17, f17, this.p);
                if (i16 == dailyDay) {
                    this.p.setStyle(Paint.Style.STROKE);
                    this.p.setStrokeWidth(this.pad * 0.4f);
                    this.p.setColor(-5720);
                    this.p.setAlpha((int) ((Math.sin(this.time * 5.0f) * 100.0d) + 150.0d));
                    RectF rectF9 = this.r;
                    float f18 = this.pad;
                    canvas.drawRoundRect(rectF9, f18, f18, this.p);
                    this.p.setStyle(Paint.Style.FILL);
                    this.p.setAlpha(255);
                }
                this.tp.setTextSize(this.pad * 1.3f);
                this.tp.setColor(i16 == dailyDay ? -1 : -7569238);
                canvas.drawText("День " + i16, this.r.centerX(), this.r.top + (this.pad * 2.0f), this.tp);
                this.tp.setTextSize(this.pad * SUPER_END);
                this.tp.setColor(-1);
                canvas.drawText(i16 < dailyDay ? "✓" : DAILY_ICON[i17], this.r.centerX(), this.r.bottom - (this.pad * 1.6f), this.tp);
                i16++;
            }
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.2f);
            this.tp.setFakeBoldText(true);
            String str4 = "Сегодня: " + dailyDesc(dailyDay);
            int i19 = this.W;
            float f19 = (this.H * 0.35f) + (f15 * 1.4f);
            float f20 = this.pad;
            drawFit(canvas, str4, i19 / 2.0f, f19 + (4.5f * f20), i19 - (f20 * 4.0f));
            this.tp.setFakeBoldText(false);
            RectF rectF10 = this.r;
            int i20 = this.W;
            int i21 = this.H;
            rectF10.set(i20 * 0.25f, i21 * 0.6f, i20 * 0.75f, i21 * 0.68f);
            this.p.setColor(-4158934);
            RectF rectF11 = this.r;
            float f21 = this.pad;
            canvas.drawRoundRect(rectF11, f21 * 1.5f, f21 * 1.5f, this.p);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.4f);
            this.tp.setFakeBoldText(true);
            canvas.drawText("Забрать", this.r.centerX(), this.r.centerY() + (this.pad * 0.85f), this.tp);
            this.tp.setFakeBoldText(false);
            this.tp.setColor(-7569238);
            this.tp.setTextSize(this.pad * 1.6f);
            canvas.drawText("Заходи каждый день — на 7-й день дают души.", this.W / 2.0f, this.H * 0.75f, this.tp);
            canvas.drawText("Пропустишь день — серия начнётся заново.", this.W / 2.0f, this.H * 0.78f, this.tp);
            this.tp.setColor(-11430);
            this.tp.setTextSize(this.pad * 1.8f);
            String str5 = "Событие недели: " + EVENT_NAME[eventIdx()] + " — " + EVENT_DESC[eventIdx()];
            int i22 = this.W;
            drawFit(canvas, str5, i22 / 2.0f, 0.84f * this.H, i22 - (this.pad * 3.0f));
            return;
        }
        if (i6 == 9) {
            drawMini(canvas);
            return;
        }
        if (i6 == 12) {
            drawTower(canvas);
            return;
        }
        if (i6 == OV_PORTRAIT) {
            drawPortrait(canvas);
            return;
        }
        if (i6 == OV_SETTINGS) {
            drawSettings(canvas);
            return;
        }
        if (i6 == OV_PARTY) {
            drawParty(canvas);
            return;
        }
        if (i6 == 7) {
            int starsGain = starsGain();
            this.tp.setColor(-5720);
            this.tp.setTextSize(this.pad * 3.4f);
            this.tp.setFakeBoldText(true);
            canvas.drawText("✦ Вознесение", this.W / 2.0f, this.H * 0.26f, this.tp);
            this.tp.setFakeBoldText(false);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 1.9f);
            canvas.drawText("Ты потеряешь ВСЁ: золото, улучшения,", this.W / 2.0f, this.H * 0.34f, this.tp);
            canvas.drawText("души и умения Алтаря. Начнёшь с этапа 1.", this.W / 2.0f, this.H * 0.38f, this.tp);
            this.tp.setColor(-6295297);
            canvas.drawText("Реликвии, достижения и звёзды останутся.", this.W / 2.0f, this.H * 0.42f, this.tp);
            this.tp.setColor(-11430);
            this.tp.setTextSize(this.pad * 2.2f);
            this.tp.setFakeBoldText(true);
            canvas.drawText("Получишь: +" + starsGain + " ✦", this.W / 2.0f, this.H * 0.49f, this.tp);
            this.tp.setFakeBoldText(false);
            this.tp.setTextSize(this.pad * 1.9f);
            canvas.drawText("Сила сейчас: ×" + fmt(soulMult()) + " (души + звёзды)", this.W / 2.0f, this.H * 0.54f, this.tp);
            canvas.drawText("После вознесения: ×" + fmt(starPart(this.stars + starsGain) * (princess() ? 2 : 1)) + " и души заново", this.W / 2.0f, this.H * 0.58f, this.tp);
            this.tp.setColor(-4609816);
            this.tp.setTextSize(this.pad * 1.6f);
            int i23 = this.W;
            drawFit(canvas, "Выгодно, когда «после» больше «сейчас»", i23 / 2.0f, 0.615f * this.H, i23 - (this.pad * 4.0f));
            String str6 = "Бонус к душам с перерождений: +" + (Math.min(this.stars + starsGain, TOWER_TOP) * 10) + "%";
            int i24 = this.W;
            drawFit(canvas, str6, i24 / 2.0f, 0.645f * this.H, i24 - (this.pad * 4.0f));
            RectF rectF12 = this.r;
            float f22 = this.pad;
            int i25 = this.H;
            rectF12.set(f22 * 3.0f, i25 * 0.66f, (this.W / 2.0f) - (f22 * 1.5f), i25 * 0.76f);
            this.p.setColor(-12963760);
            RectF rectF13 = this.r;
            float f23 = this.pad;
            canvas.drawRoundRect(rectF13, f23 * 1.5f, f23 * 1.5f, this.p);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.2f);
            canvas.drawText("Не сейчас", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            RectF rectF14 = this.r;
            int i26 = this.W;
            float f24 = this.pad;
            int i27 = this.H;
            rectF14.set((i26 / 2.0f) + (f24 * 1.5f), i27 * 0.66f, i26 - (f24 * 3.0f), i27 * 0.76f);
            this.p.setColor(-7712808);
            RectF rectF15 = this.r;
            float f25 = this.pad;
            canvas.drawRoundRect(rectF15, f25 * 1.5f, f25 * 1.5f, this.p);
            canvas.drawText("Вознестись", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            return;
        }
        if (i6 != 8) {
            if (i6 == 3) {
                this.tp.setColor(-1);
                this.tp.setTextSize(this.pad * 2.4f);
                canvas.drawText("Тьма... тишина...", this.W / 2.0f, this.H * 0.36f, this.tp);
                canvas.drawText("И снова — первый вдох.", this.W / 2.0f, this.H * 0.41f, this.tp);
                this.tp.setColor(-8395009);
                this.tp.setTextSize(this.pad * 3.0f);
                this.tp.setFakeBoldText(true);
                canvas.drawText("Жизнь №" + this.lifeNo, this.W / 2.0f, this.H * 0.5f, this.tp);
                this.tp.setFakeBoldText(false);
                this.tp.setColor(-11430);
                this.tp.setTextSize(this.pad * 2.0f);
                canvas.drawText("Душ: " + fmt(this.souls) + "  (сила ×" + fmt(soulMult()) + ")", this.W / 2.0f, this.H * 0.56f, this.tp);
                this.tp.setColor(-4609816);
                this.tp.setTextSize(this.pad * 1.7f);
                canvas.drawText("Загляни на Алтарь — там души превращаются в вечные умения", this.W / 2.0f, this.H * 0.62f, this.tp);
                this.tp.setTextSize(this.pad * 1.7f);
                this.tp.setColor(-7569238);
                canvas.drawText("тап — начать новую жизнь", this.W / 2.0f, this.H * 0.85f, this.tp);
                return;
            }
            return;
        }
        for (int i28 = OV_NONE; i28 < 40; i28++) {
            double d = i28;
            float sin = (((float) (((Math.sin(12.9898d * d) * 43758.5453d) % 1.0d) + 1.0d)) % 1.0f) * this.W;
            float sin2 = (((float) (((Math.sin(d * 78.233d) * 12345.678d) % 1.0d) + 1.0d)) % 1.0f) * this.H;
            this.p.setColor(-5720);
            this.p.setAlpha((int) ((Math.sin((this.time * 3.0f) + i28) * 120.0d) + 120.0d));
            canvas.drawCircle(sin, sin2, this.pad * 0.3f, this.p);
        }
        this.p.setAlpha(255);
        this.tp.setColor(-1);
        this.tp.setTextSize(this.pad * 2.3f);
        canvas.drawText("Ты поднимаешься над дорогой,", this.W / 2.0f, this.H * 0.34f, this.tp);
        canvas.drawText("над миром, над самой смертью.", this.W / 2.0f, this.H * 0.39f, this.tp);
        this.tp.setColor(-5720);
        this.tp.setTextSize(this.pad * 3.4f);
        this.tp.setFakeBoldText(true);
        StringBuilder append2 = new StringBuilder().append("✦ ").append(this.stars).append(" ");
        int i29 = this.stars;
        canvas.drawText(append2.append((i29 % 10 != 1 || i29 % 100 == 11) ? (i29 % 10 < 2 || i29 % 10 > 4 || (i29 % 100 >= 12 && i29 % 100 <= OV_SETTINGS)) ? "звёзд" : "звезды" : "звезда").toString(), this.W / 2.0f, this.H * 0.49f, this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 2.0f);
        this.tp.setColor(-11430);
        canvas.drawText("Всё сильнее в ×" + fmt(starMult()) + " раз", this.W / 2.0f, this.H * 0.55f, this.tp);
        this.tp.setTextSize(this.pad * 1.7f);
        this.tp.setColor(-7569238);
        canvas.drawText("тап — начать заново, но уже легендой", this.W / 2.0f, this.H * 0.85f, this.tp);
    }

    void drawParty(Canvas var1) {
       this.tp.setTextAlign(Align.CENTER);
       this.tp.setColor(-25909);
       this.tp.setTextSize(this.pad * 3.2F);
       this.tp.setFakeBoldText(true);
       var1.drawText("\ud83d\udc6d Спутницы", (float)this.W / 2.0F, (float)this.H * 0.1F, this.tp);
       this.tp.setFakeBoldText(false);
       this.tp.setTextSize(this.pad * 1.55F);
       this.tp.setColor(-4609816);
       int var9 = this.W;
       this.drawFit(var1, "Бонусы открытых спутниц действуют всегда.", (float)var9 / 2.0F, 0.135F * (float)this.H, (float)var9 - this.pad * 4.0F);
       var9 = this.W;
       this.drawFit(
          var1, "Тап — взять с собой · ещё тап по выбранной — история знакомства", (float)var9 / 2.0F, 0.16F * (float)this.H, (float)var9 - this.pad * 4.0F
       );
       var9 = this.H;
       float var4 = (float)var9 * 0.13F;
       float var2 = (float)var9 * 0.18F;
       int var10 = 0;

       while (var10 < 5) {
          int var13 = COMP_ORDER[var10];
          boolean var14 = this.compUnlocked(var13);
          boolean var11;
          if (this.compSel == var13) {
             var11 = true;
          } else {
             var11 = false;
          }

          RectF var15 = this.partyRows[var10];
          float var5 = this.pad;
          float var6 = (float)this.W;
          float var3 = var2 + var4;
          var15.set(var5 * 2.0F, var2, var6 - var5 * 2.0F, var3 - var5);
          var15 = this.partyRows[var10];
          var2 = this.pad;
          if (var11) {
             var9 = -11916710;
          } else if (var14) {
             var9 = -13753520;
          } else {
             var9 = -15068112;
          }

          this.panel(var1, var15, var2 * 1.4F, var9);
          Bitmap var16 = this.bmComp[var13];
          float var7 = (var4 - this.pad * 2.5F) / (float)var16.getHeight();
          var5 = this.partyRows[var10].left;
          float var8 = this.pad;
          var6 = this.partyRows[var10].bottom;
          var2 = this.pad;
          Paint var32;
          if (var14) {
             var32 = this.bp;
          } else {
             var32 = this.silP;
          }

          var9 = -9543024;
          this.blit(var1, var16, var5 + var8 * 5.0F, var6 - var2 * 0.8F, var7, 1.0F, 1.0F, var32);
          var6 = this.partyRows[var10].left + this.pad * 10.0F;
          this.tp.setTextAlign(Align.LEFT);
          this.tp.setFakeBoldText(true);
          this.tp.setTextSize(this.pad * 2.1F);
          Paint var33 = this.tp;
          byte var12 = -1;
          if (var14) {
             var9 = -1;
          }

          var33.setColor(var9);
          String var34;
          if (var14) {
             var34 = COMP_NAME[var13];
          } else {
             var34 = "???";
          }

          var5 = this.partyRows[var10].top;
          var2 = var4 * 0.3F;
          var1.drawText(var34, var6, var5 + var2, this.tp);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(this.pad * 1.45F);
          Paint var35 = this.tp;
          if (var14) {
             var9 = -4609816;
          } else {
             var9 = -10858384;
          }

          var35.setColor(var9);
          String var36;
          if (var14) {
             var36 = COMP_ROLE[var13];
          } else {
             var36 = "ещё не встречена";
          }

          var1.drawText(var36, var6, this.partyRows[var10].top + 0.48F * var4, this.tp);
          Paint var37 = this.tp;
          if (var14) {
             var9 = -11430;
          } else {
             var9 = -7569238;
          }

          var37.setColor(var9);
          this.tp.setTextSize(this.pad * 1.5F);
          StringBuilder var38 = new StringBuilder();
          StringBuilder var39;
          String var42;
          if (var14) {
                          var39 = var38.append("★ ");
             var42 = COMP_BONUS[var13];
          } else {
                          var39 = var38.append("\ud83d\udd12 ");
             var42 = this.compProgress(var13);
          }

          this.drawFit(var1, var39.append(var42).toString(), var6, this.partyRows[var10].top + 0.68F * var4, this.partyRows[var10].right - var6 - this.pad);
          if (var14) {
             this.tp.setTextAlign(Align.RIGHT);
             this.tp.setTextSize(this.pad * 1.6F);
             this.tp.setFakeBoldText(true);
             Paint var40 = this.tp;
             var9 = var12;
             if (var11) {
                var9 = -8592501;
             }

             var40.setColor(var9);
             String var41;
             if (var11) {
                var41 = "✓ рядом";
             } else {
                var41 = "взять";
             }

             var1.drawText(var41, this.partyRows[var10].right - this.pad * 1.5F, this.partyRows[var10].top + var2, this.tp);
             this.tp.setFakeBoldText(false);
          }

          var10++;
          var2 = var3;
       }

       this.tp.setTextAlign(Align.CENTER);
       this.tp.setTextSize(this.pad * 1.5F);
       this.tp.setColor(-9543024);
       var1.drawText("тап мимо — закрыть", (float)this.W / 2.0F, (float)this.H * 0.86F, this.tp);
    }

    void drawPortrait(Canvas canvas) {
        float f = this.W * 0.8f;
        float f2 = this.H * 0.12f;
        float f3 = (this.portraitPop * 0.03f) + 1.0f;
        Bitmap bitmap = this.bmPortraitC[this.portraitWho];
        if (bitmap == null) {
            bitmap = this.bmPortrait;
        }
        if (bitmap == null) {
            this.overlay = OV_NONE;
            return;
        }
        float f4 = this.W / 2.0f;
        float sin = ((float) Math.sin(this.time * 1.6f)) * this.pad * 0.4f;
        this.p.setColor(-45149);
        this.p.setAlpha((int) ((Math.sin(this.time * 2.0f) * 25.0d) + 40.0d));
        float f5 = f2 + (f / 2.0f);
        canvas.drawCircle(f4, f5, 0.62f * f, this.p);
        this.p.setAlpha(255);
        float f6 = f2 + f;
        blit(canvas, bitmap, f4, sin + f6 + (((f3 - 1.0f) * f) / 2.0f), (f / bitmap.getWidth()) * f3, 1.0f, 1.0f, this.bp);
        for (int i = OV_NONE; i < 6; i++) {
            double d = (this.time * 0.7d) + ((i * 3.141592653589793d) / 3.0d);
            float cos = (((float) Math.cos(d)) * f * 0.56f) + f4;
            float sin2 = (((float) Math.sin(d)) * f * 0.56f) + f5;
            this.p.setColor(-25909);
            this.p.setAlpha((int) ((Math.sin((this.time * 4.0f) + i) * 100.0d) + 150.0d));
            float f7 = this.pad;
            canvas.drawRect(cos - (f7 * 0.35f), sin2 - (f7 * 0.35f), (f7 * 0.35f) + cos, sin2 + (f7 * 0.35f), this.p);
        }
        this.p.setAlpha(255);
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setColor(-25909);
        this.tp.setTextSize(this.pad * 3.0f);
        this.tp.setFakeBoldText(true);
        canvas.drawText(COMP_NAME[this.portraitWho], f4, f6 + (this.pad * 5.0f), this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setColor(-4609816);
        this.tp.setTextSize(this.pad * 1.7f);
        int i2 = this.portraitWho;
        canvas.drawText(i2 == 0 ? "Спасена с 20-го этажа · сидела там 300 лет" : COMP_ROLE[i2], f4, f6 + (this.pad * 7.6f), this.tp);
        this.tp.setColor(-11430);
        this.tp.setTextSize(this.pad * 2.0f);
        this.tp.setFakeBoldText(true);
        canvas.drawText((this.portraitWho == 0 ? "💗 " : "★ ") + COMP_BONUS[this.portraitWho], f4, f6 + (this.pad * 10.4f), this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.9f);
        this.tp.setFakeBoldText(true);
        float min = Math.min(this.W - (this.pad * 4.0f), this.tp.measureText(this.portraitLine) + (this.pad * 3.0f));
        float f8 = this.pad;
        float f9 = f6 + (12.6f * f8);
        float f10 = min / 2.0f;
        this.r.set(f4 - f10, f9, f10 + f4, (f8 * 3.6f) + f9);
        this.p.setColor(-1);
        RectF rectF = this.r;
        float f11 = this.pad;
        canvas.drawRoundRect(rectF, f11 * 1.5f, f11 * 1.5f, this.p);
        Path path = new Path();
        path.moveTo(f4 - this.pad, f9);
        path.lineTo(this.pad + f4, f9);
        path.lineTo(f4, f9 - (this.pad * 1.3f));
        path.close();
        canvas.drawPath(path, this.p);
        this.tp.setColor(-14017990);
        String str = this.portraitLine;
        float f12 = this.pad;
        drawFit(canvas, str, f4, (2.4f * f12) + f9, min - (f12 * 2.0f));
        this.tp.setFakeBoldText(false);
        this.tp.setColor(-7569238);
        this.tp.setTextSize(this.pad * 1.5f);
        canvas.drawText((this.portraitWho != 0 || this.giftDay == today()) ? "тапни портрет — поболтать · мимо — закрыть" : "тапай портрет... может, что-то подарит", f4, this.H * 0.95f, this.tp);
    }

    void drawPrincess(Canvas canvas) {
        float f = this.hu * 0.6f;
        float princessX = princessX();
        float abs = ((float) Math.abs(Math.sin(this.time * 2.0f))) * f * 0.6f;
        this.p.setColor(1426063360);
        float f2 = 6.0f * f;
        float f3 = this.groundY;
        this.r.set(princessX - f2, f3 - f, f2 + princessX, f3 + f);
        canvas.drawOval(this.r, this.p);
        blit(canvas, this.bmComp[this.compSel], princessX, (this.groundY + f) - abs, f, 1.0f, 1.0f, this.bp);
    }

    void drawPrincessBubble(Canvas canvas) {
        int i = this.compSel;
        if (i < 0 || !compUnlocked(i)) {
            return;
        }
        float f = this.hu * 0.6f;
        float princessX = princessX();
        float f2 = this.princessSayT;
        if (f2 <= 0.0f || this.overlay != 0) {
            return;
        }
        float min = Math.min(1.0f, f2 * 3.0f);
        this.tp.setTextSize(this.pad * 1.5f);
        this.tp.setFakeBoldText(true);
        float measureText = this.tp.measureText(this.princessLine);
        float f3 = this.pad;
        float f4 = measureText + (2.0f * f3);
        float max = Math.max(f3, princessX - (0.3f * f4));
        float height = this.groundY - (this.bmPrincess.getHeight() * f);
        float f5 = this.pad;
        float f6 = height - (4.2f * f5);
        this.r.set(max, f6, f4 + max, (f5 * 2.8f) + f6);
        this.p.setColor(-1);
        this.p.setAlpha((int) (235.0f * min));
        RectF rectF = this.r;
        float f7 = this.pad;
        canvas.drawRoundRect(rectF, f7 * IMPACT, f7 * IMPACT, this.p);
        Path path = new Path();
        path.moveTo(princessX - (this.pad * 0.5f), this.r.bottom);
        path.lineTo((this.pad * 0.8f) + princessX, this.r.bottom);
        path.lineTo(princessX, this.r.bottom + (this.pad * IMPACT));
        path.close();
        canvas.drawPath(path, this.p);
        this.p.setAlpha(255);
        this.tp.setTextAlign(Paint.Align.LEFT);
        this.tp.setColor(-14017990);
        this.tp.setAlpha((int) (min * 255.0f));
        String str = this.princessLine;
        float f8 = this.pad;
        canvas.drawText(str, max + f8, f6 + (f8 * 1.95f), this.tp);
        this.tp.setAlpha(255);
        this.tp.setFakeBoldText(false);
        this.tp.setTextAlign(Paint.Align.CENTER);
    }

    void drawQuests(Canvas var1) {
       int var5 = 0;

       while (true) {
          String var14 = "Забрать";
          boolean var10 = true;
          if (var5 >= 3) {
             int var22;
             if ((this.questClaimed & 8) != 0) {
                var22 = 1;
             } else {
                var22 = 0;
             }

             var10 = this.allQuestsClaimed();
             boolean var26;
             if (var10 && var22 == 0) {
                var26 = true;
             } else {
                var26 = false;
             }

             int var25;
             if (var22 != 0) {
                var25 = -10858384;
             } else {
                var25 = -8395009;
             }

             String var35;
             if (var22 != 0) {
                var35 = "Получена — до завтра!";
             } else {
                var35 = "Выполни все 3 задания: +" + fmt(this.questSouls()) + " душ";
             }

             if (var22 != 0) {
                var14 = "✓";
             } else if (!var10) {
                var14 = "\ud83d\udd12";
             }

             if (var10 && var22 == 0) {
                var10 = true;
             } else {
                var10 = false;
             }

             this.rowBox(var1, 3, var26, var25, "\ud83c\udf81", "Награда дня", var35, var14, var10, -13664328, null);
             long var12 = System.currentTimeMillis();
             var12 = 86400000L - (var12 + (long)TimeZone.getDefault().getOffset(var12)) % 86400000L;
             this.tp.setTextAlign(Align.CENTER);
             this.tp.setTextSize(this.pad * 1.7F);
             this.tp.setColor(-7569238);
             float var3 = this.upT;
             float var2 = this.rowH;
             var2 = var3 + var2 * 4.0F + var2 * 0.4F;
             var1.drawText("Новые задания через " + var12 / 3600000L + "ч " + var12 / 60000L % 60L + "м", (float)this.W / 2.0F, var2, this.tp);
             this.tp.setColor(-11430);
             this.tp.setTextSize(this.pad * 1.8F);
             this.tp.setFakeBoldText(true);
             var14 = "Событие недели: " + EVENT_NAME[this.eventIdx()];
             var22 = this.W;
             float var4 = (float)var22 / 2.0F;
             var3 = this.pad;
             this.drawFit(var1, var14, var4, 2.6F * var3 + var2, (float)var22 - var3 * 4.0F);
             this.tp.setFakeBoldText(false);
             this.tp.setTextSize(this.pad * 1.5F);
             var14 = EVENT_DESC[this.eventIdx()] + " · серия входов: " + this.streak + " дн.";
             var22 = this.W;
             var4 = (float)var22 / 2.0F;
             var3 = this.pad;
             this.drawFit(var1, var14, var4, 4.8F * var3 + var2, (float)var22 - var3 * 4.0F);
             return;
          }

          int var8 = this.questId[var5];
          boolean var6;
          if ((this.questClaimed & 1 << var5) != 0) {
             var6 = true;
          } else {
             var6 = false;
          }

          boolean var11 = this.questDone(var5);
          String var15 = QUEST_NAME[var8];
          int[] var30 = QUEST_GOAL;
          String var16 = String.format(var15, var30[var8]);
          if (var6) {
             var15 = "Награда получена";
          } else {
             var15 = "Прогресс: "
                + this.questProg(var5)
                + "/"
                + var30[var8]
                + " · "
                + fmt(this.questGold())
                + " \ud83e\ude99 + "
                + fmt(this.questMana())
                + " \ud83d\udca7";
          }

          boolean var9;
          if (var11 && !var6) {
             var9 = true;
          } else {
             var9 = false;
          }

          int var7;
          if (var6) {
             var7 = -10858384;
          } else {
             var7 = QUEST_COLOR[var8];
          }

          String var17 = QUEST_ICON[var8];
          if (var6) {
             var14 = "✓";
          } else if (var11) {
             var14 = "Забрать";
          } else {
             var14 = this.questProg(var5) * 100L / (long)var30[var8] + "%";
          }

          if (!var11 || var6) {
             var10 = false;
          }

          this.rowBox(var1, var5, var9, var7, var17, var16, var15, var14, var10, -4158934, null);
          var5++;
       }
    }

    void drawRelicGet(Canvas var1) {
       float var13 = this.relGetT;
       float var6;
       if (this.relFly) {
          var6 = Math.min(1.0F, this.relFlyT / 0.6F);
       } else {
          var6 = 0.0F;
       }

       float var7 = 1.0F - var6;
       float var8 = 1.0F - var7 * var7;
       float var9 = Math.min(0.8F, 1.4F * var13);
       this.p.setColor(-16120300);
       this.p.setAlpha((int)(var9 * var7 * 255.0F));
       var1.drawRect(0.0F, 0.0F, (float)this.W, (float)this.H, this.p);
       this.p.setAlpha(255);
       var9 = (float)this.W / 2.0F;
       var7 = (float)this.H * 0.36F;
       float var14 = Math.min(1.0F, var13 / 0.8F);
       float var10 = 1.0F - var14;
       float var11 = 1.0F - var10 * var10 * var10;
       var10 = this.relFromX;
       var10 += (var9 - var10) * var11;
       float var12 = this.relFromY;
       var12 = var12 + (var7 - var12) * var11 - (float)Math.sin((double)var14 * Math.PI) * this.pad * 8.0F;
       var11 = var11 * 0.65F + 0.35F;
       if (this.relFly) {
          var9 += (this.relicBtn.centerX() - var9) * var8;
          var7 += (this.relicBtn.centerY() - var7) * var8;
          var8 = 1.0F - var8 * 0.75F;
       } else {
          var8 = var11;
          var9 = var10;
          var7 = var12;
          if (var14 >= 1.0F) {
             var7 = var12 + (float)Math.sin((double)(var13 * 2.5F)) * this.pad * 0.8F;
             var9 = var10;
             var8 = var11;
          }
       }

       int var17 = REL_COLOR[this.relGetIdx];
       if (!this.relFly && var14 > 0.5F) {
          var10 = Math.min(1.0F, (var14 - 0.5F) * 2.0F);
          Path var18 = new Path();
          var1.save();
          var1.rotate(this.time * 25.0F, var9, var7);

          for (int var15 = 0; var15 < 12; var15++) {
             var18.reset();
             double var4 = (double)var15 * (Math.PI * 2) / 12.0;
             double var2 = var4 + 0.13;
             var11 = this.pad * 16.0F * var8;
             var18.moveTo(var9, var7);
             var18.lineTo((float)Math.cos(var4) * var11 + var9, (float)Math.sin(var4) * var11 + var7);
             var18.lineTo((float)Math.cos(var2) * var11 + var9, (float)Math.sin(var2) * var11 + var7);
             var18.close();
             this.p.setColor(var17);
             this.p.setAlpha((int)(70.0F * var10));
             var1.drawPath(var18, this.p);
          }

          var1.restore();
          this.p.setAlpha(255);
       }

       for (int var37 = 3; var37 >= 1; var37--) {
          this.p.setColor(var17);
          Paint var42 = this.p;
          byte var16;
          if (this.relFly) {
             var16 = 40;
          } else {
             var16 = 60;
          }

          var42.setAlpha((int)((float)var16 * (1.0F - var6 * 0.5F)));
          var10 = this.pad;
          var11 = (float)var37;
          var1.drawCircle(var9, var7, var10 * (1.8F * var11 + 2.5F) * var8 * ((float)Math.sin((double)(this.time * 6.0F + var11)) * 0.06F + 1.0F), this.p);
       }

       this.p.setAlpha(255);
       if (!this.relFly) {
          for (int var38 = 0; var38 < 10; var38++) {
             var6 = this.time;
             double var19 = (double)var6;
             var19 = (double)var38 * Math.PI / 5.0 + var19 * 1.8;
             var6 = this.pad * ((float)Math.sin((double)(var6 * 4.0F + (float)var38)) * 1.5F + 9.0F) * var8;
             this.p.setColor(-3392);
             this.p.setAlpha((int)(Math.min(1.0F, var13 * 2.0F) * 200.0F));
             var1.drawCircle((float)Math.cos(var19) * var6 + var9, (float)Math.sin(var19) * var6 + var7, this.pad * 0.35F, this.p);
          }
       }

       this.p.setAlpha(255);
       this.tp.setTextAlign(Align.CENTER);
       this.tp.setTextSize(this.pad * 9.0F * var8);
       this.tp.setColor(-1);
       var1.drawText(REL_ICON[this.relGetIdx], var9, var7 + this.pad * 3.2F * var8, this.tp);
       if (!this.relFly && var13 > 0.8F) {
          var6 = Math.min(1.0F, (var13 - 0.8F) * 3.0F);
          int var41 = this.rel[this.relGetIdx];
          this.tp.setFakeBoldText(true);
          this.tp.setTextSize(this.pad * 3.0F);
          this.tp.setColor(-11430);
          Paint var43 = this.tp;
          int var39 = (int)(var6 * 255.0F);
          var43.setAlpha(var39);
          String var44;
          if (var41 > 1) {
             var44 = "РЕЛИКВИЯ УСИЛЕНА!";
          } else {
             var44 = "НОВАЯ РЕЛИКВИЯ!";
          }

          var1.drawText(var44, (float)this.W / 2.0F, (float)this.H * 0.18F, this.tp);
          this.tp.setTextSize(this.pad * 2.8F);
          this.tp.setColor(var17);
          this.tp.setAlpha(var39);
          var1.drawText(REL_NAME[this.relGetIdx], (float)this.W / 2.0F, (float)this.H * 0.5F, this.tp);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(this.pad * 2.0F);
          this.tp.setColor(-1);
          this.tp.setAlpha(var39);
          var1.drawText(this.relDesc(this.relGetIdx), (float)this.W / 2.0F, (float)this.H * 0.545F, this.tp);
          this.tp.setTextSize(this.pad * 1.7F);
          this.tp.setColor(-4609816);
          this.tp.setAlpha(var39);
          String var45;
          if (var41 >= 10) {
             var45 = "Максимальный уровень!";
          } else {
             var45 = "Уровень " + var41 + " из " + 10;
          }

          var1.drawText(var45, (float)this.W / 2.0F, (float)this.H * 0.58F, this.tp);
          String var46 = "Выпала с босса «" + this.relGetFrom + "» · хранится в \ud83c\udf92 навсегда";
          var39 = this.W;
          this.drawFit(var1, var46, (float)var39 / 2.0F, (float)this.H * 0.62F, (float)var39 - this.pad * 4.0F);
          if (var13 > 1.3F) {
             this.tp.setColor(-7569238);
             this.tp.setAlpha((int)(Math.sin((double)(this.time * 4.0F)) * 100.0 + 150.0));
             var1.drawText("тап — забрать в рюкзак", (float)this.W / 2.0F, (float)this.H * 0.7F, this.tp);
          }

          this.tp.setAlpha(255);
       }
    }

    void drawSettings(Canvas canvas) {
        String str;
        String str2;
        String str3;
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setColor(-1);
        float f = 3.2f;
        this.tp.setTextSize(this.pad * 3.2f);
        this.tp.setFakeBoldText(true);
        canvas.drawText("⚙ Настройки", this.W / 2.0f, this.H * 0.13f, this.tp);
        this.tp.setFakeBoldText(false);
        String[] strArr = {"🎵 Музыка", "🔊 Звуки", "📳 Вибрация", "🔢 Цифры урона", "📤 Скопировать код сохранения", "📥 Загрузить сохранение", "🗑 Сбросить прогресс"};
        int i = 4;
        boolean[] zArr = {this.optMusic, this.optSfx, this.optVib, this.optNum};
        int i2 = this.H;
        float f2 = i2 * 0.07f;
        float f3 = i2 * 0.014f;
        float f4 = i2 * 0.18f;
        int i3 = OV_NONE;
        while (true) {
            RectF[] rectFArr = this.setRows;
            if (i3 >= rectFArr.length) {
                break;
            }
            if (i3 == i) {
                f4 += f3 * 2.0f;
            }
            RectF rectF = rectFArr[i3];
            float f5 = this.pad;
            rectF.set(f5 * 3.0f, f4, this.W - (f5 * 3.0f), f4 + f2);
            this.p.setColor(i3 == 6 ? -11920854 : i3 >= i ? -13753520 : -14411203);
            RectF rectF2 = this.setRows[i3];
            float f6 = this.pad;
            canvas.drawRoundRect(rectF2, f6 * 1.4f, f6 * 1.4f, this.p);
            this.tp.setTextAlign(i3 < i ? Paint.Align.LEFT : Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 2.0f);
            this.tp.setColor(i3 == 6 ? -30070 : -1);
            String str4 = strArr[i3];
            RectF[] rectFArr2 = this.setRows;
            canvas.drawText(str4, i3 < i ? rectFArr2[i3].left + (this.pad * 2.0f) : rectFArr2[i3].centerX(), this.setRows[i3].centerY() + (this.pad * 0.7f), this.tp);
            if (i3 < i) {
                float f7 = this.pad;
                float f8 = 6.0f * f7;
                float f9 = f7 * f;
                float f10 = (this.setRows[i3].right - (this.pad * 2.0f)) - f8;
                float f11 = f9 / 2.0f;
                float centerY = this.setRows[i3].centerY() - f11;
                float f12 = f8 + f10;
                this.r.set(f10, centerY, f12, centerY + f9);
                this.p.setColor(zArr[i3] ? -12673970 : -11911072);
                canvas.drawRoundRect(this.r, f11, f11, this.p);
                this.p.setColor(-1);
                canvas.drawCircle(zArr[i3] ? f12 - f11 : f10 + f11, centerY + f11, f9 * 0.4f, this.p);
            }
            f4 += f2 + f3;
            i3++;
            i = 4;
            f = 3.2f;
        }
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setTextSize(this.pad * 1.5f);
        this.tp.setColor(-7569238);
        int i4 = this.W;
        float f13 = this.pad;
        drawFit(canvas, "Код сохранения — это весь твой прогресс.", i4 / 2.0f, f4 + (f13 * 2.0f), i4 - (f13 * 4.0f));
        int i5 = this.W;
        float f14 = this.pad;
        drawFit(canvas, "Сохрани его, чтобы перенести игру на новый телефон.", i5 / 2.0f, f4 + (4.2f * f14), i5 - (f14 * 4.0f));
        if (this.setMsgT > 0.0f) {
            this.tp.setColor(-6295297);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setFakeBoldText(true);
            String str5 = this.setMsg;
            int i6 = this.W;
            float f15 = this.pad;
            drawFit(canvas, str5, i6 / 2.0f, f4 + (7.5f * f15), i6 - (f15 * 4.0f));
            this.tp.setFakeBoldText(false);
        }
        try {
            str = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), OV_NONE).versionName;
        } catch (Exception e) {
            str = "";
        }
        this.tp.setColor(-9543024);
        this.tp.setTextSize(this.pad * 1.4f);
        canvas.drawText("Второй шанс · версия " + str + " · тап мимо — закрыть", this.W / 2.0f, this.H * 0.96f, this.tp);
        if (this.setAsk > 0) {
            this.p.setColor(-267778540);
            canvas.drawRect(0.0f, 0.0f, this.W, this.H, this.p);
            RectF rectF3 = this.r;
            float f16 = this.pad;
            int i7 = this.H;
            rectF3.set(f16 * 3.0f, i7 * 0.3f, this.W - (f16 * 3.0f), i7 * 0.62f);
            this.p.setColor(-14411203);
            RectF rectF4 = this.r;
            float f17 = this.pad;
            canvas.drawRoundRect(rectF4, f17 * 2.0f, f17 * 2.0f, this.p);
            int i8 = this.setAsk;
            if (i8 == 1) {
                str3 = codeInfo(this.pendingImport) + ". Текущий прогресс заменится.";
                str2 = "Загрузить сохранение?";
            } else if (i8 == 2) {
                str2 = "Сбросить весь прогресс?";
                str3 = "Всё начнётся с нуля: этапы, души, реликвии, башня.";
            } else {
                str2 = "Точно-точно?";
                str3 = "Это нельзя отменить. Совсем.";
            }
            this.tp.setColor(this.setAsk == 1 ? -1 : -30070);
            this.tp.setTextSize(this.pad * SUPER_END);
            this.tp.setFakeBoldText(true);
            int i9 = this.W;
            drawFit(canvas, str2, i9 / 2.0f, 0.38f * this.H, i9 - (this.pad * 8.0f));
            this.tp.setFakeBoldText(false);
            this.tp.setColor(-4609816);
            this.tp.setTextSize(this.pad * 1.7f);
            int i10 = this.W;
            drawFit(canvas, str3, i10 / 2.0f, this.H * 0.44f, i10 - (this.pad * 8.0f));
            RectF rectF5 = this.askNo;
            float f18 = this.pad;
            int i11 = this.H;
            rectF5.set(f18 * 5.0f, i11 * 0.5f, (this.W / 2.0f) - f18, i11 * 0.58f);
            RectF rectF6 = this.askYes;
            int i12 = this.W;
            float f19 = this.pad;
            int i13 = this.H;
            rectF6.set((i12 / 2.0f) + f19, i13 * 0.5f, i12 - (f19 * 5.0f), i13 * 0.58f);
            this.p.setColor(-12963760);
            RectF rectF7 = this.askNo;
            float f20 = this.pad;
            canvas.drawRoundRect(rectF7, f20 * 1.5f, f20 * 1.5f, this.p);
            this.p.setColor(this.setAsk == 1 ? -12673970 : -4703670);
            RectF rectF8 = this.askYes;
            float f21 = this.pad;
            canvas.drawRoundRect(rectF8, f21 * 1.5f, f21 * 1.5f, this.p);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.1f);
            canvas.drawText("Отмена", this.askNo.centerX(), this.askNo.centerY() + (this.pad * 0.75f), this.tp);
            int i14 = this.setAsk;
            canvas.drawText(i14 == 1 ? "Загрузить" : i14 == 2 ? "Сбросить" : "Да, сбросить", this.askYes.centerX(), this.askYes.centerY() + (this.pad * 0.75f), this.tp);
        }
    }

    void drawSuper(Canvas var1) {
       float var8 = this.superT;
       this.p.setColor(-871758316);
       var1.drawRect(0.0F, 0.0F, (float)this.W, this.battleT, this.p);
       var1.drawRect(0.0F, this.battleB, (float)this.W, (float)this.H, this.p);
       float var88;
       int var16 = (var88 = var8 - 1.45F) == 0.0F ? 0 : (var88 < 0.0F ? -1 : 1);
       if (var16 < 0) {
          this.p.setColor(-16777216);
          this.p.setAlpha((int)(Math.min(1.0F, var8 * 3.0F) * 110.0F));
          var1.drawRect(0.0F, this.battleT, (float)this.W, this.battleB, this.p);
          this.p.setAlpha(255);
       }

       float var5 = this.heroX;
       float var4 = this.hu;
       var5 += 13.0F * var4;
       float var6 = this.groundY - var4 * 26.0F;
       this.p.setStyle(Style.STROKE);
       this.p.setStrokeCap(Cap.ROUND);
       int var13 = this.superType;
       if (var13 == 0) {
          var13 = 0;

          while (true) {
             int var14 = this.superStars;
             if (var13 >= var14 + 1) {
                break;
             }

             boolean var75;
             if (var13 == var14) {
                var75 = true;
             } else {
                var75 = false;
             }

             if (var75) {
                var4 = 1.3F;
             } else {
                var4 = (float)var13 * 0.25F + 0.55F;
             }

             var4 = var8 - var4;
             if (!(var4 < 0.0F) && !(var4 > 0.45F)) {
                float var9 = Math.min(1.0F, var4 / 0.1F);
                if (var4 < 0.1F) {
                   var5 = 1.0F;
                } else {
                   var5 = 1.0F - (var4 - 0.1F) / 0.35F;
                }

                var6 = this.mobR;
                if (var75) {
                   var4 = 2.6F;
                } else {
                   var4 = 1.9F;
                }

                var4 = var6 * var4;
                Path var17 = new Path();
                int var15;
                if (var13 % 2 == 1) {
                   var15 = 1;
                } else {
                   var15 = 0;
                }

                float var10 = this.mobX;
                float var7;
                if (var15 != 0) {
                   var7 = var4;
                } else {
                   var7 = -var4;
                }

                float var11 = this.mobY;
                if (var15 != 0) {
                   var6 = -var4;
                } else {
                   var6 = var4;
                }

                var17.moveTo(var7 + var10, var11 - 0.8F * var4);
                float var12 = this.mobX;
                if (var15 != 0) {
                   var7 = var4;
                } else {
                   var7 = -var4;
                }

                var17.quadTo(var12 + var7 * 0.2F, this.mobY + var4 * 0.5F, var10 + var6, var11 + var4 * 0.7F);
                PathMeasure var18 = new PathMeasure(var17, false);
                var17 = new Path();
                var18.getSegment(0.0F, var18.getLength() * var9, var17, true);
                Paint var83 = this.p;
                if (var75) {
                   var15 = -26051;
                } else {
                   var15 = -11430;
                }

                var83.setColor(var15);
                this.p.setAlpha((int)(140.0F * var5));
                Paint var84 = this.p;
                var6 = this.pad;
                if (var75) {
                   var4 = 3.2F;
                } else {
                   var4 = 2.2F;
                }

                var84.setStrokeWidth(var6 * var4);
                var1.drawPath(var17, this.p);
                this.p.setColor(-1);
                this.p.setAlpha((int)(var5 * 255.0F));
                Paint var85 = this.p;
                var5 = this.pad;
                if (var75) {
                   var4 = 1.3F;
                } else {
                   var4 = 0.8F;
                }

                var85.setStrokeWidth(var5 * var4);
                var1.drawPath(var17, this.p);
             }

             var13++;
          }
       } else if (var13 == 1) {
          var6 = Math.min(1.0F, Math.max(0.0F, (var8 - 0.3F) / 0.5F));
          var5 = this.mobR * 2.4F * var6;
          var4 = var5 * 0.3F;
          RectF var78 = this.r;
          float var53 = this.mobX;
          float var59 = this.groundY;
          var78.set(var53 - var5, var59 - var4, var53 + var5, var59 + var4);
          this.p.setColor(-5213953);
          this.p.setStrokeWidth(this.pad * 0.5F);
          this.p.setAlpha((int)(230.0F * var6));
          var1.drawOval(this.r, this.p);
          var78 = this.r;
          float var62 = this.mobX;
          var59 = var5 * 0.7F;
          var53 = this.groundY;
          float var64 = 0.7F * var4;
          var78.set(var62 - var59, var53 - var64, var62 + var59, var53 + var64);
          this.p.setColor(-2045697);
          this.p.setStrokeWidth(this.pad * 0.25F);
          var1.drawOval(this.r, this.p);
          this.p.setStyle(Style.FILL);
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setTextSize(this.pad * 1.8F);
          this.tp.setColor(-2045697);
          this.tp.setAlpha((int)(var6 * 255.0F));

          for (int var69 = 0; var69 < 8; var69++) {
             double var2 = (double)this.time * 1.5 + (double)var69 * Math.PI / 4.0;
             var1.drawText(
                new String[]{"✦", "◆", "✧", "❖", "✦", "◇", "✧", "❖"}[var69],
                this.mobX + (float)Math.cos(var2) * var5 * 0.85F,
                this.groundY + (float)Math.sin(var2) * var4 * 0.85F + this.pad * 0.6F,
                this.tp
             );
          }

          this.tp.setAlpha(255);
          if (var8 > 0.85F && var8 < 1.95F) {
             if (var16 < 0) {
                var4 = (var8 - 0.85F) / 0.6F;
             } else {
                var4 = 1.0F - (var8 - 1.45F) / 0.5F;
             }

             var5 = this.mobR * (1.4F * var4 + 0.4F) * ((float)Math.sin((double)(this.time * 40.0F)) * 0.08F + 1.0F);
             this.p.setColor(-5213953);
             this.p.setAlpha((int)(110.0F * var4));
             var6 = this.mobX;
             var1.drawRect(var6 - var5, this.battleT, var6 + var5, this.groundY, this.p);
             this.p.setColor(-1517313);
             this.p.setAlpha((int)(190.0F * var4));
             var6 = this.mobX;
             var53 = 0.55F * var5;
             var1.drawRect(var6 - var53, this.battleT, var6 + var53, this.groundY, this.p);
             this.p.setColor(-1);
             this.p.setAlpha((int)(var4 * 255.0F));
             var4 = this.mobX;
             var5 *= 0.22F;
             var1.drawRect(var4 - var5, this.battleT, var4 + var5, this.groundY, this.p);
             this.p.setAlpha(255);
          }

          this.p.setStyle(Style.STROKE);
       } else {
          if (var8 > 0.45F && var8 < 1.8000001F) {
             if (var16 < 0) {
                var4 = Math.min(1.0F, (var8 - 0.45F) / 0.5F);
             } else {
                var4 = 1.0F - (var8 - 1.45F) / 0.35F;
             }

             float var56 = this.pad * (2.5F * var4 + 1.0F) * ((float)Math.sin((double)(this.time * 50.0F)) * 0.15F + 1.0F);
             var13 = EL_COLOR[(int)(8.0F * var8) % 4];
             this.p.setColor(var13);
             this.p.setAlpha((int)(120.0F * var4));
             this.p.setStrokeWidth(var56 * 2.2F);
             var1.drawLine(var5, var6, this.mobX, this.mobY, this.p);
             this.p.setColor(-1);
             this.p.setAlpha((int)(230.0F * var4));
             this.p.setStrokeWidth(var56 * 0.7F);
             var1.drawLine(var5, var6, this.mobX, this.mobY, this.p);
             this.p.setStyle(Style.FILL);

             for (int var71 = 0; var71 < 8; var71++) {
                float var65 = (float)var71;
                float var61 = (1.6F * var8 + var65 / 8.0F) % 1.0F;
                var56 = this.mobX;
                float var63 = this.mobY;
                var65 = (float)Math.sin((double)(var61 * 12.0F + this.time * 10.0F + var65));
                float var67 = this.pad;
                this.p.setColor(EL_COLOR[var71 % 4]);
                this.p.setAlpha((int)(var4 * 255.0F));
                var1.drawCircle((var56 - var5) * var61 + var5, (var63 - var6) * var61 + var6 + var65 * var67 * 2.2F * var4, this.pad * 0.9F, this.p);
             }

             this.p.setStyle(Style.STROKE);
          }

          if (var8 < 0.6F) {
             this.p.setStyle(Style.FILL);
             this.p.setColor(EL_COLOR[(int)(10.0F * var8) % 4]);
             this.p.setAlpha(180);
             var1.drawCircle(var5, var6, this.pad * (4.0F * var8 + 1.0F), this.p);
             this.p.setStyle(Style.STROKE);
          }
       }

       this.p.setStyle(Style.FILL);
       this.p.setAlpha(255);
       this.p.setStrokeWidth(0.0F);
       this.tp.setTextAlign(Align.CENTER);
       if (var16 < 0) {
          var4 = Math.min(1.0F, var8 / 0.2F);
          this.tp.setFakeBoldText(true);
          this.tp.setTextSize(this.pad * 3.4F * (var4 * 0.4F + 0.6F));
          var13 = this.superType;
          String var80;
          if (var13 == 0) {
             var80 = "⚔ СУПЕРУДАР!";
          } else if (var13 == 1) {
             var80 = "\ud83d\udd2e СВЯЩЕННЫЙ СТОЛП!";
          } else {
             var80 = "\ud83c\udf00 ЛУЧ СТИХИЙ!";
          }

          this.tp.setColor(-15331286);
          var4 = (float)this.W / 2.0F;
          var5 = this.pad;
          var1.drawText(var80, var4 + var5 * 0.3F, this.battleT + var5 * 6.3F, this.tp);
          this.tp.setColor(-11430);
          var1.drawText(var80, (float)this.W / 2.0F, this.battleT + this.pad * 6.0F, this.tp);
          StringBuilder var86 = new StringBuilder();

          for (int var73 = 0; var73 < 3; var73++) {
             if (var73 < this.superStars) {
                var80 = "★";
             } else {
                var80 = "☆";
             }

             var86.append(var80);
          }

          this.tp.setTextSize(this.pad * 2.4F);
          var1.drawText(var86.toString(), (float)this.W / 2.0F, this.battleT + this.pad * 9.0F, this.tp);
          this.tp.setFakeBoldText(false);
       }

       if (this.superHit) {
          var5 = var8 - 1.45F;
          var4 = Math.max(0.0F, 1.0F - 4.0F * var5);
          if (var4 > 0.0F) {
             this.p.setColor(-1);
             this.p.setAlpha((int)(var4 * 220.0F));
             var1.drawRect(0.0F, this.battleT, (float)this.W, this.battleB, this.p);
             this.p.setAlpha(255);
          }

          if (var5 < 0.12F) {
             var4 = var5 / 0.12F * 0.8F + 0.5F;
          } else {
             var4 = 1.3F - Math.min(0.3F, (var5 - 0.12F) * 1.5F);
          }

          var5 = this.mobY - this.mobR * 0.6F - var5 * this.pad * 3.0F;
          var6 = Math.min(1.0F, (2.6F - var8) * 3.0F);
          var1.save();
          var1.rotate(-6.0F, this.mobX, var5);
          this.tp.setFakeBoldText(false);
          this.tp.setTypeface(this.pixTf);
          this.tp.setTextSize(this.pad * 3.4F * var4);
          String var87 = "-" + fmt(this.superDmg);
          this.tp.setColor(-15331286);
          Paint var82 = this.tp;
          var13 = (int)(var6 * 255.0F);
          var82.setAlpha(var13);
          var6 = this.mobX;
          float var58 = this.pad;
          var1.drawText(var87, var6 - var58 * 2.0F + var58 * 0.4F, var58 * 0.4F + var5, this.tp);
          this.tp.setColor(-11430);
          this.tp.setAlpha(var13);
          var1.drawText(var87, this.mobX - this.pad * 2.0F, var5, this.tp);
          if (this.superKill) {
             this.tp.setTextSize(this.pad * 3.2F * var4);
             this.tp.setColor(-42406);
             this.tp.setAlpha(var13);
             var4 = this.mobX;
             var6 = this.pad;
             var1.drawText("ДОБИТ!", var4 - 2.0F * var6, var5 + var6 * 4.5F, this.tp);
          }

          var1.restore();
          this.tp.setAlpha(255);
          this.tp.setFakeBoldText(false);
          this.tp.setTypeface(Typeface.DEFAULT);
       }
    }

    void drawTab3(Canvas canvas) {
        float f = this.pad;
        float f2 = DEATH_END * f;
        float f3 = (this.W - (f * 4.0f)) / 2.0f;
        String[] strArr = {"🏆 Достижения " + Integer.bitCount(this.achMask) + "/" + ACH_NAME.length, "📖 Бестиарий " + beastsStudied() + "/" + BEAST_N};
        int i = OV_NONE;
        while (i < 2) {
            RectF rectF = this.r;
            float f4 = this.pad;
            float f5 = i;
            float f6 = this.upT;
            rectF.set((f4 * 1.5f) + ((f3 + f4) * f5), f6, (1.5f * f4) + (f5 * (f4 + f3)) + f3, f6 + f2);
            boolean z = (i == 1) == this.bestiaryView;
            this.p.setColor(z ? -11912576 : -14805453);
            RectF rectF2 = this.r;
            float f7 = this.pad;
            canvas.drawRoundRect(rectF2, f7, f7, this.p);
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 1.6f);
            this.tp.setFakeBoldText(z);
            this.tp.setColor(z ? -1 : -7569238);
            String str = strArr[i];
            float centerX = this.r.centerX();
            float centerY = this.r.centerY();
            float f8 = this.pad;
            drawFit(canvas, str, centerX, centerY + (0.55f * f8), f3 - f8);
            this.tp.setFakeBoldText(false);
            i++;
        }
        if (this.bestiaryView) {
            drawBestiary(canvas);
        } else {
            drawAchievements(canvas);
        }
    }

    void drawTabs(Canvas canvas) {
        int i = OV_NONE;
        for (int i2 = OV_NONE; i2 < 3; i2++) {
            if ((this.questClaimed & (1 << i2)) == 0 && questDone(i2)) {
                i++;
            }
        }
        int i3 = (allQuestsClaimed() && (this.questClaimed & 8) == 0) ? i + 1 : i;
        String[] strArr = {"⚔ Улучш.", "👻 Алтарь", "📜 Задания", "🏆 " + Integer.bitCount(this.achMask) + "/" + ACH_NAME.length};
        float f = (this.W - (this.pad * 3.0f)) / 4.0f;
        int i4 = OV_NONE;
        while (i4 < 4) {
            RectF rectF = this.r;
            float f2 = this.pad;
            int i5 = i4 + 1;
            rectF.set((f2 * 1.5f) + (i4 * f) + (f2 * 0.3f), this.tabT, ((1.5f * f2) + (i5 * f)) - (f2 * 0.3f), this.tabB);
            this.p.setColor(this.tab == i4 ? -11912582 : -14805453);
            RectF rectF2 = this.r;
            float f3 = this.pad;
            canvas.drawRoundRect(rectF2, f3, f3, this.p);
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 1.8f);
            this.tp.setColor(this.tab == i4 ? -1 : -7569238);
            this.tp.setFakeBoldText(this.tab == i4);
            drawFit(canvas, strArr[i4], this.r.centerX(), this.r.centerY() + (this.pad * 0.65f), this.r.width() - this.pad);
            if (i4 == 2 && i3 > 0) {
                this.p.setColor(-46502);
                float f4 = this.r.right - (this.pad * 1.1f);
                float f5 = this.r.top;
                float f6 = this.pad;
                canvas.drawCircle(f4, f5 + (f6 * 1.1f), f6 * 1.1f, this.p);
                this.tp.setColor(-1);
                this.tp.setTextSize(this.pad * 1.3f);
                this.tp.setFakeBoldText(true);
                canvas.drawText(String.valueOf(i3), this.r.right - (this.pad * 1.1f), this.r.top + (this.pad * 1.55f), this.tp);
                this.tp.setTextSize(this.pad * 1.8f);
            }
            i4 = i5;
        }
        this.tp.setFakeBoldText(false);
    }

    void drawTower(Canvas canvas) {
        int i;
        boolean z;
        int i2;
        String str;
        int i3 = towerNext();
        boolean z2 = this.towerFloor >= TOWER_TOP;
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setColor(-16310);
        this.tp.setTextSize(this.pad * 3.2f);
        this.tp.setFakeBoldText(true);
        canvas.drawText(z2 ? "🏰 Бесконечная башня" : "🏰 Башня Принцессы", this.W / 2.0f, this.H * 0.1f, this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setColor(-4609816);
        this.tp.setTextSize(this.pad * 1.8f);
        canvas.drawText(z2 ? "Принцесса спасена 💗 — но башня растёт дальше" : "На вершине томится принцесса. Говорят, она что-то даёт...", this.W / 2.0f, this.H * 0.135f, this.tp);
        int i4 = this.W;
        float f = i4 * 0.3f;
        float f2 = i4 * 0.24f;
        int i5 = this.H;
        float f3 = i5 * 0.042f;
        float f4 = i5 * 0.66f;
        int max = Math.max(1, i3 - 3);
        int min = z2 ? i3 + 5 : Math.min(TOWER_TOP, max + 10);
        int i6 = max;
        while (true) {
            if (i6 > min) {
                break;
            }
            float f5 = f4 - ((i6 - max) * f3);
            float f6 = f2 / 2.0f;
            this.r.set(f - f6, (f5 - f3) + (this.pad * 0.25f), f + f6, f5);
            boolean z3 = i6 <= this.towerFloor;
            boolean z4 = i6 == i3;
            Paint paint = this.p;
            if (z3) {
                i2 = -9807238;
                z = z2;
            } else if (z4) {
                z = z2;
                i2 = Color.rgb((int) ((Math.sin(this.time * 5.0f) * 60.0d) + 150.0d), 110, 50);
            } else {
                z = z2;
                i2 = -12963760;
            }
            paint.setColor(i2);
            canvas.drawRect(this.r, this.p);
            this.p.setColor(855638016);
            int i7 = i6;
            int i8 = min;
            float f7 = f;
            canvas.drawRect(this.r.left, this.r.bottom - (this.pad * 0.4f), this.r.right, this.r.bottom, this.p);
            this.tp.setTextSize(this.pad * 1.5f);
            this.tp.setColor(z3 ? -2041616 : z4 ? -1 : -7569238);
            StringBuilder sb = new StringBuilder();
            if (z3) {
                str = "✓ ";
            } else if (z4) {
                str = "⚔ ";
            } else {
                str = "";
            }
            canvas.drawText(sb.append(str).append(i7).toString(), f7, f5 - (f3 * 0.3f), this.tp);
            i6 = i7 + 1;
            min = i8;
            f = f7;
            z2 = z;
        }
        int i9 = min;
        boolean z5 = z2;
        float f8 = f;
        float f9 = f4 - (((i9 - max) + 1) * f3);
        if (z5 || i9 != TOWER_TOP) {
            i = -4609816;
        } else {
            Path path = new Path();
            float f10 = f2 * 0.6f;
            path.moveTo(f8 - f10, f9 + (this.pad * 0.3f));
            path.lineTo(f8, f9 - (2.4f * f3));
            path.lineTo(f8 + f10, f9 + (this.pad * 0.3f));
            path.close();
            this.p.setColor(-7722422);
            canvas.drawPath(path, this.p);
            float f11 = this.hu * 0.55f;
            this.p.setColor(-5720);
            this.p.setAlpha((int) ((Math.sin(this.time * 3.0f) * 50.0d) + 90.0d));
            canvas.drawCircle(f8, f9 - (0.9f * f3), 9.0f * f11, this.p);
            this.p.setAlpha(255);
            i = -4609816;
            blit(canvas, this.bmPrincess, f8, f9 - (f3 * 0.1f), f11, 1.0f, 1.0f, this.bp);
            this.tp.setTextSize(this.pad * 2.0f);
            canvas.drawText("💗", f8 + (f11 * 10.0f), f9 - (f3 * 1.6f), this.tp);
        }
        float f12 = this.W * 0.71f;
        this.tp.setTextSize(this.pad * 1.7f);
        this.tp.setColor(-7569238);
        canvas.drawText("Этаж " + i3 + (z5 ? "" : " из 20"), f12, this.H * 0.25f, this.tp);
        blit(canvas, this.bmBoss[towerSprite(i3)], f12, this.H * 0.37f, this.hu * IMPACT, 1.0f, 1.0f, this.bp);
        this.tp.setFakeBoldText(true);
        this.tp.setTextSize(this.pad * 2.0f);
        this.tp.setColor(-30070);
        drawFit(canvas, towerFoe(i3), f12, this.H * 0.41f, this.W * 0.5f);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.6f);
        this.tp.setColor(-1);
        canvas.drawText("HP: " + fmt(towerHp(i3)), f12, this.H * 0.445f, this.tp);
        canvas.drawText("Время: " + ((int) bossLimit()) + "с", f12, this.H * 0.475f, this.tp);
        double tapDmg = (((tapDmg() * 5.0d) + autoDps()) * bossLimit()) / towerHp(i3);
        String str2 = tapDmg > 1.5d ? "Справишься легко" : tapDmg > 0.8d ? "Будет жарко" : tapDmg > 0.4d ? "Нужно испытание 📜" : "Пока слишком силён";
        this.tp.setColor(tapDmg > 1.5d ? -8592501 : tapDmg > 0.8d ? -11430 : tapDmg > 0.4d ? -24486 : -38294);
        this.tp.setFakeBoldText(true);
        canvas.drawText(str2, f12, this.H * 0.515f, this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setColor(i);
        this.tp.setTextSize(this.pad * 1.5f);
        canvas.drawText("Награда: +" + fmt(towerSouls(i3)) + " душ", f12, this.H * 0.56f, this.tp);
        if (i3 == TOWER_TOP) {
            this.tp.setColor(-25909);
            canvas.drawText("+ спасение принцессы 💗", f12, this.H * 0.59f, this.tp);
        } else if (i3 % 5 == 0) {
            this.tp.setColor(-11430);
            canvas.drawText("+ гарантированная реликвия", f12, this.H * 0.59f, this.tp);
        }
        this.tp.setColor(-7569238);
        this.tp.setTextSize(this.pad * 1.4f);
        canvas.drawText("Прогресс башни не сбрасывается", this.W / 2.0f, this.H * 0.71f, this.tp);
        RectF rectF = this.towerClose;
        float f13 = this.pad;
        int i10 = this.H;
        rectF.set(f13 * 3.0f, i10 * 0.74f, (this.W / 2.0f) - (f13 * 1.5f), i10 * 0.82f);
        this.p.setColor(-12963760);
        RectF rectF2 = this.towerClose;
        float f14 = this.pad;
        canvas.drawRoundRect(rectF2, f14 * 1.5f, f14 * 1.5f, this.p);
        this.tp.setColor(-1);
        this.tp.setTextSize(this.pad * 2.2f);
        canvas.drawText("Назад", this.towerClose.centerX(), this.towerClose.centerY() + (this.pad * 0.8f), this.tp);
        RectF rectF3 = this.towerGo;
        int i11 = this.W;
        float f15 = this.pad;
        int i12 = this.H;
        rectF3.set((i11 / 2.0f) + (f15 * 1.5f), i12 * 0.74f, i11 - (f15 * 3.0f), i12 * 0.82f);
        this.p.setColor(-4688342);
        RectF rectF4 = this.towerGo;
        float f16 = this.pad;
        canvas.drawRoundRect(rectF4, f16 * 1.5f, f16 * 1.5f, this.p);
        this.tp.setFakeBoldText(true);
        canvas.drawText("⚔ Штурм!", this.towerGo.centerX(), this.towerGo.centerY() + (this.pad * 0.8f), this.tp);
        this.tp.setFakeBoldText(false);
    }

    void drawUpgrades(Canvas canvas) {
        int i = OV_NONE;
        while (i < 5) {
            double[] batch = batch(i);
            boolean z = batch[OV_NONE] > 0.0d && wallet(i) >= batch[1];
            int i2 = this.buyMode;
            String str = (i2 != 2 || batch[OV_NONE] <= 0.0d) ? i2 == 1 ? "+10 ур." : null : "+" + ((int) batch[OV_NONE]) + " ур.";
            this.rowIcon = this.icUp[i];
            boolean z2 = this.holdRow == i;
            int i3 = UP_COLOR[i];
            String valueOf = String.valueOf(this.lvl[i]);
            String str2 = UP_NAME[i];
            String effect = effect(i);
            String fmt = fmt(batch[1]);
            boolean z3 = UP_MANA[i];
            rowBox(canvas, i, z2, i3, valueOf, str2, effect, fmt, z, z3 ? -9809960 : -12673970, z3 ? this.bmMana : this.bmCoin, str);
            i++;
        }
    }

    void drawVignette(Canvas canvas) {
        float f = this.vigW;
        int i = this.W;
        if (f != i || this.vigT != this.battleT) {
            this.vigW = i;
            float f2 = this.battleT;
            this.vigT = f2;
            float f3 = (f2 + this.battleB) / 2.0f;
            Paint paint = this.vigP;
            int i2 = this.W;
            paint.setShader(new RadialGradient(i2 / 2.0f, f3, Math.max(i2, this.battleB - this.battleT) * 0.78f, new int[]{OV_NONE, OV_NONE, 1996488704}, new float[]{0.0f, 0.62f, 1.0f}, Shader.TileMode.CLAMP));
        }
        canvas.drawRect(0.0f, this.battleT, this.W, this.battleB, this.vigP);
    }

    void dropRelic() {
        dropRelic(false);
    }

    void dropRelic(boolean z) {
        if (z || this.rnd.nextInt(100) < relicChance()) {
            int[] iArr = new int[6];
            int i = OV_NONE;
            for (int i2 = OV_NONE; i2 < 6; i2++) {
                if (this.rel[i2] < 10) {
                    iArr[i] = i2;
                    i++;
                }
            }
            if (i == 0) {
                return;
            }
            int i3 = iArr[this.rnd.nextInt(i)];
            int[] iArr2 = this.rel;
            iArr2[i3] = iArr2[i3] + 1;
            this.relicNew = true;
            this.relGetIdx = i3;
            this.relGetT = 0.0f;
            this.relFlyT = 0.0f;
            this.relFly = false;
            this.relFromX = this.mobX;
            this.relFromY = this.mobY;
            this.relGetFrom = mobName();
            this.overlay = 10;
            this.sfx.play(6, 1.0f);
            this.sfx.buzz(80L);
        }
    }

    double econMult() {
        return Math.pow((this.souls * 0.15d) + 1.0d, 0.2d) * Math.sqrt(starPart(this.stars)) * (princess() ? 2 : 1);
    }

    String effect(int i) {
        switch (i) {
            case OV_NONE /* 0 */:
                return "Урон тапа: " + fmt(tapDmg()) + msHint(OV_NONE);
            case 1:
                return "Автоурон: " + fmt(autoDps()) + "/с" + msHint(1);
            case 2:
                return "Мана: " + fmt(manaSec()) + "/с" + msHint(2);
            case 3:
                return "ВЕСЬ урон ×" + fmt(grimMult()) + " → ×" + fmt(grimMult() * 1.6d) + " за уровень";
            default:
                return "Золото ×" + fmt((this.lvl[4] * 0.2d) + 1.0d);
        }
    }

    void enterTower() {
        int i = towerNext();
        this.towerFight = true;
        this.farming = false;
        this.chest = false;
        double d = towerHp(i);
        this.monsterMax = d;
        this.monsterHp = d;
        this.mobSpawn = 0.0f;
        this.bossTime = bossLimit();
        this.scrollOn = false;
        this.scrollAt = SystemClock.uptimeMillis() + 2500;
        this.overlay = OV_NONE;
        this.bossIntroT = 1.6f;
        this.bossLanded = false;
        showBanner("🏰 Этаж " + i + ": " + towerFoe(i) + "!", -16310);
        this.sfx.play(3, 0.8f);
    }

    int eventIdx() {
        return (int) ((today() / 7) % EVENT_NAME.length);
    }

    String exportCode() {
        String str;
        try {
            save();
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, ?> entry : this.prefs.getAll().entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Integer) {
                    str = "i";
                } else if (value instanceof Long) {
                    str = "l";
                } else if (value instanceof Float) {
                    str = "f";
                } else if (value instanceof Boolean) {
                    str = "b";
                } else if (value instanceof String) {
                    str = "s";
                } else {
                    continue;
                }
                jSONObject.put(entry.getKey(), str + ":" + value);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(jSONObject.toString().getBytes("UTF-8"));
            gZIPOutputStream.close();
            return "SC1:" + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (Exception e) {
            return null;
        }
    }

    long fairyGap() {
        return (long) (((this.rnd.nextInt(60000) + 45000) * (1.0d - (this.rel[3] * 0.06d))) / (eventIdx() != 2 ? 1 : 3));
    }

    float famX() {
        return (this.heroX - (this.hu * 9.0f)) + (((float) Math.sin(this.time * 1.3f)) * this.hu * 2.0f);
    }

    float famY() {
        return (this.groundY - (this.hu * 25.0f)) + (((float) Math.sin(this.time * SUPER_END)) * this.hu * 2.5f);
    }

    double frenzy() {
        return SystemClock.uptimeMillis() < this.frenzyUntil ? 3.0d : 1.0d;
    }

    double getD(String str) {
        return Double.longBitsToDouble(this.prefs.getLong(str, Double.doubleToRawLongBits(0.0d)));
    }

    double goldFor(int i) {
        return Math.pow(1.115d, i - 1) * 4.0d * (isBoss(i) ? 5 : 1) * goldMult();
    }

    double goldMult() {
        return ((this.lvl[4] * 0.2d) + 1.0d) * ((this.rel[2] * 0.1d) + 1.0d) * (compUnlocked(2) ? 1.3d : 1.0d) * econMult() * (eventIdx() != 0 ? 1 : 2);
    }

    double grimMult() {
        return Math.pow(1.6d, this.lvl[3]);
    }

    boolean hasRelics() {
        int[] iArr = this.rel;
        int length = iArr.length;
        for (int i = OV_NONE; i < length; i++) {
            if (iArr[i] > 0) {
                return true;
            }
        }
        return false;
    }

    float heroTop() {
        return this.groundY - ((this.bmHero[this.lifeStageIdx].getHeight() * this.hu) * ((this.lifeStageIdx * 0.05f) + 0.8f));
    }

    double hpFor(int i) {
        return Math.pow(1.15d, i - 1) * 26.0d * (isBoss(i) ? 8 : 1);
    }

    boolean isBoss(int i) {
        return i % 10 == 0;
    }

    void isekaiHitNow() {
        this.isekaiHit = true;
        this.isekais++;
        this.shake = 1.0f;
        this.sfx.play(10, 1.0f);
        this.sfx.buzz(250L);
        burst(40, -5720);
        if (bossFight()) {
            double d = this.monsterMax * 0.25d;
            addFloat(this.mobX, this.mobY - (this.mobR * 1.3f), "ИСЕКАЙ! -" + fmt(d), -16310, 3.4f * this.pad);
            damage(d, false);
            return;
        }
        double goldFor = goldFor(this.stage) * 19.0d;
        this.gold += goldFor;
        this.lifetimeGold += goldFor;
        this.victimBmp = mobBitmap();
        this.victimT = 0.0f;
        this.victimX = this.mobX;
        this.victimY = this.mobY;
        showBanner("🚚 Монстр отправлен в другой мир! +" + fmt(goldFor + goldFor(this.stage)) + " золота", -16310);
        this.noShatter = true;
        damage(this.monsterHp + 1.0d, false);
        this.noShatter = false;
    }

    void judge(String str, int i) {
        this.judgeTxt = str;
        this.judgeCol = i;
        this.judgeT = 0.5f;
    }

    int judgeTap() {
        if (!rhythmOn()) {
            return OV_NONE;
        }
        double beatPos = beatPos() / this.BEAT;
        double abs = Math.abs(beatPos - Math.rint(beatPos)) * this.BEAT;
        double uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - this.lastJudgeAt < 220.0d || this.bossIntroT > 0.0f) {
            return OV_NONE;
        }
        if (abs > WIN_GOOD) {
            int i = this.combo;
            if (i <= 0) {
                return OV_NONE;
            }
            this.combo = i - Math.max(1, i / 3);
            judge("МИМО", -38294);
            return -1;
        }
        this.lastJudgeAt = uptimeMillis;
        boolean z = abs <= WIN_PERFECT;
        int i2 = this.combo + 1;
        this.combo = i2;
        if (i2 > this.bestCombo) {
            this.bestCombo = i2;
        }
        float f = this.isekaiGauge;
        boolean z2 = f >= 100.0f;
        this.isekaiGauge = Math.min(100.0f, f + (z ? 3.2f : 1.6f));
        judge(z ? "ИДЕАЛЬНО!" : "ХОРОШО", z ? -11430 : -6295297);
        int i3 = this.combo;
        if (i3 == 10 || i3 == 25 || i3 == 50) {
            showBanner("🎵 Комбо " + this.combo + "! Весь урон ×" + fmt(comboMult()), -11430);
        }
        if (!z2 && this.isekaiGauge >= 100.0f) {
            showBanner("🚚 Грузовик-кун заряжен! Жми «ИСЕКАЙ» внизу", -16310);
            this.sfx.play(9, 0.5f);
        }
        this.sfx.playHit(this.combo, z, beatPos());
        return z ? 2 : 1;
    }

    void kill() {
        if (!this.noShatter) {
            shatter();
        }
        if (this.towerFight) {
            towerWin();
            return;
        }
        boolean z = this.chest;
        double goldFor = goldFor(this.stage) * (z ? OV_BEAT : 1);
        this.gold += goldFor;
        this.lifetimeGold += goldFor;
        this.kills++;
        spawnCoins(z ? 7 : isBoss(this.stage) ? 6 : 2);
        int bossIdx = z ? TOWER_TOP : isBoss(this.stage) ? bossIdx() + 10 : ((this.stage - 1) / 3) % MOB.length;
        long[] jArr = this.bk;
        long j = jArr[bossIdx] + 1;
        jArr[bossIdx] = j;
        if (j == beastNeed(bossIdx)) {
            showBanner("📖 Изучен вид: " + beastName(bossIdx) + "! Урон +3%", -6295297);
        }
        if (z) {
            this.chests++;
            this.chest = false;
            double manaTap = manaTap() * 30.0d;
            this.mana += manaTap;
            burst(30, -11430);
            showBanner("💰 Мимик повержен: +" + fmt(goldFor) + " золота, +" + fmt(manaTap) + " маны", -11430);
            this.sfx.play(3, 0.8f);
            this.sfx.buzz(60L);
        }
        boolean isBoss = isBoss(this.stage);
        addFloat(this.mobX, this.mobY - (this.mobR * IMPACT), "+" + fmt(goldFor), -11430, this.pad * 2.4f);
        burst(isBoss ? 40 : OV_PARTY, mobColor());
        if (isBoss) {
            this.bossKills++;
            this.shake = 1.0f;
            showBanner("Босс повержен: " + mobName() + "!", -5720);
            dropRelic();
            int bossIdx2 = bossIdx();
            if (this.stage <= 100 && (this.chapterMask & (1 << bossIdx2)) == 0) {
                this.pendingChapter = bossIdx2;
            }
            this.sfx.play(3, 1.0f);
            this.sfx.buzz(120L);
        } else {
            this.sfx.play(2, 0.55f);
        }
        if (this.farming && isBoss(this.stage + 1)) {
            spawn();
            return;
        }
        int i = this.stage + 1;
        this.stage = i;
        if (i > this.bestStage) {
            this.bestStage = i;
        }
        spawn();
        updateLifeStage(true);
        if (this.stage == 30 && this.souls == 0.0d && this.lifeNo == 1) {
            showBanner("Доступна реинкарнация! Умри, чтобы стать сильнее", -8395009);
        }
    }

    void leaveTower() {
        this.towerFight = false;
        if (isBoss(this.stage)) {
            this.stage--;
            this.farming = true;
        }
        spawn();
    }

    void load() {
       this.gold = this.getD("gold");
       this.mana = this.getD("mana");
       this.souls = this.getD("souls");
       this.lifetimeGold = this.getD("lgold");
       this.stage = this.prefs.getInt("stage", 1);
       this.lifeNo = this.prefs.getInt("life", 1);
       this.bestStage = this.prefs.getInt("best", 1);
       SharedPreferences var12 = this.prefs;
       this.taps = var12.getLong("taps2", (long)var12.getInt("taps", 0));
       this.kills = this.prefs.getLong("kills", 0L);
       this.bossKills = this.prefs.getLong("bkills", 0L);
       this.fairies = this.prefs.getLong("fairies", 0L);
       this.meteors = this.prefs.getLong("meteors", 0L);
       this.achMask = this.prefs.getInt("ach", 0);
       this.lifeStageIdx = this.prefs.getInt("lifeIdx", 0);
       this.storySeen = this.prefs.getBoolean("story", false);
       this.farming = this.prefs.getBoolean("farming", false);
       this.sfx.muted = this.prefs.getBoolean("muted", false);
       this.buyMode = this.prefs.getInt("buyMode", 0);

       for (int var7 = 0; var7 < 5; var7++) {
          this.lvl[var7] = this.prefs.getInt("lvl" + var7, 0);
          this.alt[var7] = this.prefs.getInt("alt" + var7, 0);
       }

       this.buys = this.prefs.getLong("buys", 0L);
       this.chests = this.prefs.getLong("chests", 0L);
       this.questsDone = this.prefs.getLong("qdone", 0L);
       this.lastDay = this.prefs.getLong("lastDay", 0L);
       this.streak = this.prefs.getInt("streak", 0);
       this.dailyPending = this.prefs.getBoolean("dailyP", false);
       this.chapterMask = this.prefs.getInt("chap", 0);
       this.minisWon = this.prefs.getLong("minis", 0L);
       this.compMask = this.prefs.getInt("compMask", 0);
       this.hasPhones = this.prefs.getBoolean("phones", false);
       this.phonesOn = this.prefs.getBoolean("phonesOn", false);
       this.beatWins = this.prefs.getInt("beatWins", 0);
       this.isekaiGauge = this.prefs.getFloat("isekaiG", 0.0F);
       this.bestCombo = this.prefs.getInt("bestCombo", 0);
       this.isekais = this.prefs.getLong("isekais", 0L);
       this.towerFloor = this.prefs.getInt("tower", 0);
       this.giftDay = this.prefs.getLong("giftDay", -1L);
       this.stars = this.prefs.getInt("stars", 0);
       this.ascensions = this.prefs.getInt("ascs", 0);
       this.soulsThisAsc = (double)this.prefs.getFloat("soulsAsc", (float)(this.souls + (double)(this.lifeNo * 20)));
       var12 = this.prefs;
       short var15;
       if (this.storySeen) {
          var15 = 255;
       } else {
          var15 = 0;
       }

       this.hintMask = var12.getInt("hints", var15);
       var12 = this.prefs;
       int var16;
       if (this.sfx.muted) {
          var16 = 2;
       } else {
          var16 = 0;
       }

       this.soundMode = var12.getInt("sndMode", var16);
       this.relicNew = this.prefs.getBoolean("relNew", false);
       var12 = this.prefs;
       boolean var9;
       if (this.soundMode == 0) {
          var9 = true;
       } else {
          var9 = false;
       }

       this.optMusic = var12.getBoolean("optMusic", var9);
       var12 = this.prefs;
       if (this.soundMode != 2) {
          var9 = true;
       } else {
          var9 = false;
       }

       this.optSfx = var12.getBoolean("optSfx", var9);
       var12 = this.prefs;
       if (this.soundMode != 2) {
          var9 = true;
       } else {
          var9 = false;
       }

       this.optVib = var12.getBoolean("optVib", var9);
       this.optNum = this.prefs.getBoolean("optNum", true);

       for (int var17 = 0; var17 < 21; var17++) {
          this.bk[var17] = this.prefs.getLong("bk" + var17, 0L);
       }

       for (int var18 = 0; var18 < 6; var18++) {
          this.rel[var18] = this.prefs.getInt("rel" + var18, 0);
       }

       this.applySound();
       this.questDay = this.prefs.getLong("qday", 0L);
       this.questClaimed = this.prefs.getInt("qclaim", 0);

       for (int var19 = 0; var19 < 3; var19++) {
          this.questId[var19] = this.prefs.getInt("qid" + var19, var19);
          this.questBase[var19] = this.prefs.getLong("qbase" + var19, 0L);
       }

       if (this.isBoss(this.stage)) {
          this.stage--;
          this.farming = true;
       }

       if (this.stage < 1) {
          this.stage = 1;
       }

       this.monsterMax = this.hpFor(this.stage);
       double var3 = this.getD("hp");
       this.monsterHp = var3;
       double var1 = 0.0;
       if (var3 <= 0.0 || var3 > this.monsterMax) {
          this.monsterHp = this.monsterMax;
       }

       long var10 = this.prefs.getLong("time", 0L);
       if (var10 > 0L) {
          double var5 = Math.min((double)(System.currentTimeMillis() - var10) / 1000.0, 28800.0);
          if (var5 > 60.0) {
             var3 = this.autoDps() * var5 / this.hpFor(this.stage) * this.goldFor(this.stage) * this.offlineRate();
             var5 = this.manaSec() * var5 * this.offlineRate();
             if (var3 + var5 > 0.0) {
                this.gold += var3;
                this.mana += var5;
                this.lifetimeGold += var3;
                this.showBanner("Пока тебя не было: +" + fmt(var3) + " \ud83e\ude99  +" + fmt(var5) + " \ud83d\udca7", -5720);
             }
          }
       }

       this.checkDay();
       this.loadCompSel();
       int var8 = this.alt[0];
       var16 = ALT_MAX[0];
       if (var8 > var16) {
          while (true) {
             int[] var28 = this.alt;
             if (var16 >= var28[0]) {
                var28[0] = ALT_MAX[0];
                this.souls += var1;
                this.showBanner("⚖ Ребаланс: за лишние уровни «Памяти» возвращено " + fmt(var1) + " душ", -6295297);
                break;
             }

             var1 += Math.ceil(Math.pow(1.8, (double)var16) * 5.0);
             var16++;
          }
       }

       if (!this.prefs.getBoolean("rebal1", false)) {
          this.prefs.edit().putBoolean("rebal1", true).apply();
          if (this.alt[0] <= ALT_MAX[0]) {
             this.showBanner("⚖ Великая ребалансировка! Души и звёзды пересчитаны", -6295297);
          }
       }
    }

    void loadArt() {
        for (int i = OV_NONE; i < 6; i++) {
            this.bmHero[i] = bm("hero_" + i);
        }
        for (int i2 = OV_NONE; i2 < 10; i2++) {
            this.bmMob[i2] = bm("mob_" + i2);
        }
        for (int i3 = OV_NONE; i3 < 9; i3++) {
            this.bmBoss[i3] = bm("boss_" + i3);
        }
        this.bmBoss[9] = bm("truck");
        for (int i4 = OV_NONE; i4 < 3; i4++) {
            this.bmBg[i4] = bm("bg_" + i4);
        }
        for (int i5 = OV_NONE; i5 < 2; i5++) {
            this.bmFairy[i5] = bm("fairy_" + i5);
        }
        this.bmFire = bm("fireball");
        this.bmCoin = bm("ic_coin");
        this.bmMana = bm("ic_mana");
        this.bmSoul = bm("ic_soul");
        this.bmMimic = bm("mimic");
        for (int i6 = OV_NONE; i6 < 5; i6++) {
            this.icUp[i6] = bm("ic_up" + i6);
            this.icAlt[i6] = bm("ic_alt" + i6);
        }
        for (int i7 = OV_NONE; i7 < 5; i7++) {
            this.bmComp[i7] = bm(COMP_FILE[i7]);
            this.partyRows[i7] = new RectF();
        }
        for (int i8 = 1; i8 < 5; i8++) {
            this.bmPortraitC[i8] = bm("portrait_c" + i8);
        }
        this.bmTruck = bm("truck");
        this.bmBeatmaker = bm("beatmaker");
        this.icPhones = bm("ic_phones");
        this.bmPrincess = bm("princess");
        Bitmap bm = bm("princess_portrait");
        this.bmPortrait = bm;
        this.bmPortraitC[OV_NONE] = bm;
        this.bmPiano = bm("piano");
        this.bmCow = bm("cow");
        this.bp.setFilterBitmap(false);
        this.bp.setAntiAlias(false);
        this.bp.setDither(false);
        this.flashP.setFilterBitmap(false);
        this.flashP.setColorFilter(new LightingColorFilter(16777215, 5263440));
    }

    void loadCompSel() {
        int i = this.prefs.getInt("compSel", -2);
        this.compSel = i;
        if (i == -2) {
            this.compSel = princess() ? OV_NONE : -1;
        }
        this.compStoryMask = this.prefs.getInt("compStory", princess() ? 1 : 0);
    }

    double manaSec() {
        int i = this.lvl[2];
        return i * 1.5d * Math.pow(1.04d, i) * ms(this.lvl[2]) * econMult();
    }

    double manaTap() {
        return ((this.stage * 0.05d) + 1.0d) * econMult();
    }

    void meteor(long j) {
        if (j < this.meteorReadyAt) {
            return;
        }
        if (this.mana < meteorCost()) {
            showBanner("Не хватает маны на метеор", -4609816);
            return;
        }
        this.mana -= meteorCost();
        this.meteorReadyAt = j + meteorCd();
        this.meteors++;
        double tapDmg = ((tapDmg() * 40.0d) + (autoDps() * 10.0d)) * meteorPower() * comboMult();
        this.shake = 1.0f;
        this.sfx.play(5, 1.0f);
        this.sfx.buzz(150L);
        addFloat(this.mobX, this.mobY - this.mobR, "МЕТЕОР! " + fmt(tapDmg), -26051, this.pad * 3.2f);
        burst(30, -34243);
        damage(tapDmg, true);
    }

    long meteorCd() {
        return (long) (Math.pow(0.92d, this.alt[4]) * 12000.0d * (eventIdx() == 3 ? 0.5d : 1.0d));
    }

    double meteorCost() {
        return ((this.stage * 3) + 25) * (eventIdx() == 3 ? 0.5d : 1.0d);
    }

    double meteorPower() {
        return ((this.alt[4] * 0.25d) + 1.0d) * ((this.rel[4] * 0.2d) + 1.0d);
    }

    void miniMsg(String str, int i) {
        this.miniMsg = str;
        this.miniMsgT = 0.9f;
        this.miniMsgColor = i;
    }

    void miniResult(boolean z) {
        if (z) {
            this.miniScore++;
            this.sfx.play(2, 1.0f);
            this.sfx.buzz(40L);
        } else {
            this.sfx.play(8, 0.6f);
            this.sfx.buzz(120L);
        }
        int i = this.miniRound + 1;
        this.miniRound = i;
        if (i < 3) {
            this.miniPhase = 3;
            this.miniT = 0.0f;
        } else {
            this.miniPhase = 2;
            this.miniT = 0.0f;
            applyMini();
        }
    }

    void miniRoundStart() {
        int nextInt;
        int i;
        this.miniT = 0.0f;
        int i2 = this.miniType;
        if (i2 == 0) {
            this.swPos = 0.0f;
            this.swDir = 1.0f;
            int i3 = this.miniRound;
            this.swSpeed = (i3 * 0.35f) + 0.85f;
            this.zoneW = 0.22f - (i3 * 0.04f);
            this.zoneL = (this.rnd.nextFloat() * (0.7f - this.zoneW)) + 0.25f;
            return;
        }
        if (i2 != 1) {
            do {
                nextInt = this.rnd.nextInt(4);
                i = this.miniRound;
                if (i <= 0) {
                    break;
                }
            } while (nextInt == this.elFoe);
            this.elFoe = nextInt;
            this.elTime = 3.6f - (i * 0.8f);
            return;
        }
        this.runeLen = this.miniRound + 3;
        for (int i4 = OV_NONE; i4 < this.runeLen; i4++) {
            this.runeSeq[i4] = this.rnd.nextInt(4);
        }
        this.runeIdx = OV_NONE;
        this.runeShowing = true;
        this.runeLit = -1;
    }

    void miniTouch(float f, float f2) {
        boolean z;
        String str;
        int i = this.miniPhase;
        if (i == 2) {
            if (this.miniT > 0.9f) {
                closeMini();
                return;
            }
            return;
        }
        if (i != 1) {
            return;
        }
        if (this.miniType == 0) {
            if (this.miniT < 0.25f) {
                return;
            }
            float f3 = this.zoneL;
            float f4 = this.zoneW;
            float f5 = (f4 / 2.0f) + f3;
            float f6 = this.swPos;
            z = f6 >= f3 && f6 <= f3 + f4;
            if (z && Math.abs(f6 - f5) < 0.035f) {
                miniMsg("ИДЕАЛЬНО!", -11430);
            } else if (z) {
                miniMsg("Попал!", -8592501);
            } else {
                miniMsg("Мимо!", -30070);
            }
            miniResult(z);
            return;
        }
        int i2 = -1;
        for (int i3 = OV_NONE; i3 < 4; i3++) {
            if (Math.abs(f - padCX(i3)) < this.W * 0.18f && Math.abs(f2 - padCY(i3)) < this.H * 0.075f) {
                i2 = i3;
            }
        }
        if (i2 < 0) {
            return;
        }
        if (this.miniType != 1) {
            z = i2 == (this.elFoe + 1) % 4;
            if (z) {
                StringBuilder sb = new StringBuilder();
                String[] strArr = EL_ICON;
                str = sb.append(strArr[i2]).append(" побеждает ").append(strArr[this.elFoe]).append("!").toString();
            } else {
                str = "Не сработало!";
            }
            miniMsg(str, z ? -8592501 : -30070);
            miniResult(z);
            return;
        }
        if (this.runeShowing) {
            return;
        }
        this.runeLit = i2;
        this.runeLitT = 0.25f;
        if (this.runeSeq[this.runeIdx] != i2) {
            miniMsg("Не та руна!", -30070);
            miniResult(false);
            return;
        }
        this.sfx.play(OV_NONE, 0.6f);
        int i4 = this.runeIdx + 1;
        this.runeIdx = i4;
        if (i4 >= this.runeLen) {
            miniMsg("Заклинание сработало!", -8592501);
            miniResult(true);
        }
    }

    Bitmap mobBitmap() {
        return this.towerFight ? this.bmBoss[towerSprite(towerNext())] : isBoss(this.stage) ? this.bmBoss[bossIdx()] : this.chest ? this.bmMimic : this.bmMob[((this.stage - 1) / 3) % MOB.length];
    }

    int mobColor() {
        if (bossFight()) {
            return -2078616;
        }
        if (this.chest) {
            return -11430;
        }
        return MOB_COLOR[((this.stage - 1) / 3) % MOB.length];
    }

    String mobName() {
        if (this.towerFight) {
            return "🏰 " + towerFoe(towerNext());
        }
        if (isBoss(this.stage)) {
            return BOSS[bossIdx()] + (this.stage > 100 ? " " + roman(((this.stage - 1) / 100) + 1) : "");
        }
        if (this.chest) {
            return "Сундук-мимик";
        }
        String[] strArr = MOB;
        return strArr[((this.stage - 1) / 3) % strArr.length];
    }

    String msHint(int i) {
        return " · ×2 через " + (25 - (this.lvl[i] % 25));
    }

    void newQuests(long j) {
        Random random = new Random((7919 * j) + 17);
        int[] iArr = {OV_NONE, 1, 2, 3, 4, 5, 6, 7};
        for (int i = 7; i > 0; i--) {
            int nextInt = random.nextInt(i + 1);
            int i2 = iArr[i];
            iArr[i] = iArr[nextInt];
            iArr[nextInt] = i2;
        }
        for (int i3 = OV_NONE; i3 < 3; i3++) {
            this.questId[i3] = iArr[i3];
            this.questBase[i3] = questStat(iArr[i3]);
        }
        this.questClaimed = OV_NONE;
        this.questDay = j;
    }

    double offlineRate() {
        return (this.alt[3] * 0.1d) + 0.5d;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i = this.overlay;
        if (i != this.seenOverlay) {
            this.seenOverlay = i;
            long uptimeMillis = SystemClock.uptimeMillis();
            this.overlayAt = uptimeMillis;
            this.storyPageAt = uptimeMillis;
        }
        if (this.W == 0) {
            return;
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        canvas.drawColor(-15594462);
        canvas.save();
        if (this.shake > 0.0f) {
            canvas.translate((this.rnd.nextFloat() - 0.5f) * this.shake * this.pad * 2.0f, (this.rnd.nextFloat() - 0.5f) * this.shake * this.pad * 2.0f);
        }
        drawBattle(canvas, uptimeMillis2);
        canvas.restore();
        drawHeader(canvas, uptimeMillis2);
        drawCoins(canvas);
        drawActions(canvas, uptimeMillis2);
        drawTabs(canvas);
        int i2 = this.tab;
        if (i2 == 0) {
            drawUpgrades(canvas);
        } else if (i2 == 1) {
            drawAltar(canvas);
        } else if (i2 == 2) {
            drawQuests(canvas);
        } else {
            drawTab3(canvas);
        }
        float f = this.bannerT;
        if (f > 0.0f && this.banner != null) {
            float min = Math.min(1.0f, f);
            this.p.setColor(-534768320);
            this.p.setAlpha((int) (224.0f * min));
            RectF rectF = this.r;
            float f2 = this.pad;
            float f3 = this.battleT;
            rectF.set(f2 * 2.0f, f3 + f2, this.W - (f2 * 2.0f), f3 + (f2 * 5.5f));
            RectF rectF2 = this.r;
            float f4 = this.pad;
            canvas.drawRoundRect(rectF2, f4, f4, this.p);
            this.tp.setColor(this.bannerColor);
            this.tp.setAlpha((int) (min * 255.0f));
            this.tp.setTextSize(this.pad * 2.0f);
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setFakeBoldText(true);
            String str = this.banner;
            int i3 = this.W;
            float f5 = this.battleT;
            float f6 = this.pad;
            drawFit(canvas, str, i3 / 2.0f, f5 + (4.0f * f6), i3 - (f6 * 6.0f));
            this.tp.setAlpha(255);
            this.tp.setFakeBoldText(false);
        }
        if (this.overlay != 0) {
            drawOverlay(canvas);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.W = i;
        this.H = i2;
        float f = i;
        float f2 = f / BF_BOSS_MAX;
        this.pad = f2;
        float f3 = i2;
        float f4 = f3 * 0.12f;
        this.headerB = f4;
        this.battleT = f4;
        float f5 = 0.5f * f3;
        this.battleB = f5;
        this.actT = 0.51f * f3;
        this.actB = 0.575f * f3;
        this.tabT = 0.585f * f3;
        this.tabB = 0.63f * f3;
        float f6 = 0.64f * f3;
        this.upT = f6;
        this.rowH = ((0.975f * f3) - f6) / 5.0f;
        float f7 = f5 - (f3 * 0.055f);
        this.groundY = f7;
        this.heroX = 0.22f * f;
        float f8 = f / 128.0f;
        this.bgS = f8;
        float f9 = 1.55f * f8;
        this.mu = f9;
        this.hu = f8 * 1.3f;
        this.mobX = 0.68f * f;
        float f10 = f9 * 9.0f;
        this.mobR = f10;
        this.mobY = f7 - f10;
        this.muteBtn.set(f - (f2 * 7.0f), 0.0f, f, f4 * 0.45f);
        RectF rectF = this.partyBtn;
        float f11 = this.pad;
        rectF.set(f - (13.0f * f11), 0.0f, f - (f11 * 7.0f), this.headerB * 0.45f);
        RectF rectF2 = this.bossBtn;
        float f12 = this.pad;
        float f13 = this.battleT;
        rectF2.set(f12 * 1.5f, (7.0f * f12) + f13, (f12 * 1.5f) + (0.3f * f), f13 + (f12 * 11.5f));
        RectF rectF3 = this.relicBtn;
        float f14 = this.pad;
        float f15 = this.battleT;
        rectF3.set(f14 * 1.5f, (f14 * 12.5f) + f15, (1.5f * f14) + (0.25f * f), f15 + (f14 * 17.0f));
        RectF rectF4 = this.towerBtn;
        float f16 = this.relicBtn.right;
        float f17 = this.pad;
        float f18 = f16 + (f17 * 0.8f);
        float f19 = this.battleT + (f17 * 12.5f);
        float f20 = this.relicBtn.right;
        float f21 = this.pad;
        rectF4.set(f18, f19, f20 + (f21 * 0.8f) + (0.2f * f), this.battleT + (f21 * 17.0f));
        RectF rectF5 = this.beatBtn;
        float f22 = this.towerBtn.right;
        float f23 = this.pad;
        float f24 = f22 + (f23 * 0.8f);
        float f25 = this.battleT + (f23 * 12.5f);
        float f26 = this.towerBtn.right;
        float f27 = this.pad;
        rectF5.set(f24, f25, f26 + (0.8f * f27) + (f * 0.12f), this.battleT + (f27 * 17.0f));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        long uptimeMillis = SystemClock.uptimeMillis();
        if (actionMasked == 1 || actionMasked == 3) {
            this.holdRow = -1;
            return true;
        }
        if (actionMasked != 0 && actionMasked != 5) {
            return true;
        }
        int actionIndex = motionEvent.getActionIndex();
        float x = motionEvent.getX(actionIndex);
        float y = motionEvent.getY(actionIndex);
        boolean z = actionMasked == 0;
        int i2 = this.overlay;
        if (i2 == 1) {
            if (!z || !storyTapReady(uptimeMillis)) {
                return true;
            }
            int i3 = this.storyPage + 1;
            this.storyPage = i3;
            this.storyPageAt = uptimeMillis;
            String[] strArr = this.storyCur;
            if (i3 >= strArr.length) {
                this.overlay = OV_NONE;
                String[] strArr2 = STORY;
                if (strArr == strArr2) {
                    this.storySeen = true;
                }
                this.storyCur = strArr2;
                this.storyTitle = null;
                save();
            }
            return true;
        }
        if (i2 == 2) {
            if (!z) {
                return true;
            }
            int i4 = this.H;
            if (y > i4 * 0.6f && y < i4 * 0.7f) {
                if (x < this.W / 2.0f) {
                    this.overlay = OV_NONE;
                } else {
                    startDeath();
                }
            }
            return true;
        }
        if (i2 == 3 || i2 == 8) {
            if (z && uptimeMillis - this.overlayAt > 1000) {
                this.overlay = OV_NONE;
            }
            return true;
        }
        if (i2 == 9) {
            if (z) {
                miniTouch(x, y);
            }
            return true;
        }
        if (i2 == 11) {
            return true;
        }
        if (i2 == OV_PARTY) {
            if (!z) {
                return true;
            }
            for (int i5 = OV_NONE; i5 < 5; i5++) {
                if (this.partyRows[i5].contains(x, y)) {
                    int i6 = COMP_ORDER[i5];
                    if (!compUnlocked(i6)) {
                        this.sfx.play(8, 0.3f);
                    } else {
                        if (this.compSel == i6) {
                            playCompStory(i6);
                            return true;
                        }
                        this.compSel = i6;
                        this.sfx.play(4, 0.6f);
                        save();
                    }
                    return true;
                }
            }
            this.overlay = OV_NONE;
            return true;
        }
        if (i2 == OV_BEAT) {
            beatTap(x, y, z);
            return true;
        }
        if (i2 == OV_SETTINGS) {
            if (z) {
                settingsTap(x, y);
            }
            return true;
        }
        if (i2 == OV_PORTRAIT) {
            if (z) {
                portraitTap(x, y);
            }
            return true;
        }
        if (i2 == 12) {
            if (!z) {
                return true;
            }
            if (this.towerGo.contains(x, y)) {
                enterTower();
            } else if (this.towerClose.contains(x, y) || y < this.H * 0.1f) {
                this.overlay = OV_NONE;
            }
            return true;
        }
        if (i2 == 10) {
            if (z && this.relGetT > 1.3f && !this.relFly) {
                this.relFly = true;
                this.sfx.play(4, 0.8f);
            }
            return true;
        }
        if (i2 == 7) {
            if (!z) {
                return true;
            }
            int i7 = this.H;
            if (y > i7 * 0.66f && y < i7 * 0.76f) {
                if (x < this.W / 2.0f) {
                    this.overlay = OV_NONE;
                } else {
                    ascend();
                }
            }
            return true;
        }
        if (i2 == 4) {
            if (z && uptimeMillis - this.dailyShownAt > 700) {
                claimDaily();
                this.overlay = OV_NONE;
            }
            return true;
        }
        if (i2 == 5) {
            if (z) {
                if (this.hasPhones && this.phonesBtn.contains(x, y)) {
                    boolean z2 = !this.phonesOn;
                    this.phonesOn = z2;
                    if (!z2) {
                        this.combo = OV_NONE;
                    }
                    this.sfx.play(11, 0.6f);
                    save();
                } else {
                    this.overlay = OV_NONE;
                }
            }
            return true;
        }
        if (i2 == 6) {
            if (z && this.deathCrash) {
                float f = this.deathT;
                if (f > 1.6f) {
                    this.deathT = Math.max(f, 3.79f);
                }
            }
            return true;
        }
        if (this.partyBtn.contains(x, y) && z && this.overlay == 0) {
            this.overlay = OV_PARTY;
            return true;
        }
        if (this.muteBtn.contains(x, y) && z && this.overlay == 0) {
            this.overlay = OV_SETTINGS;
            this.setAsk = OV_NONE;
            this.setMsgT = 0.0f;
            return true;
        }
        if (towerUnlocked() && !this.towerFight && this.towerBtn.contains(x, y) && z && this.overlay == 0) {
            this.overlay = 12;
            return true;
        }
        if ((hasRelics() || this.hasPhones) && this.relicBtn.contains(x, y) && z) {
            this.overlay = 5;
            this.relicNew = false;
            return true;
        }
        if (y >= this.battleT && y <= this.battleB) {
            if (z && rhythmOn() && this.isekaiGauge >= 100.0f && this.isekaiT < 0.0f && this.isekaiBtn.contains(x, y)) {
                startIsekai();
                return true;
            }
            if (z && beatUnlocked() && this.beatBtn.contains(x, y)) {
                startBeatFight();
                return true;
            }
            if (this.fairyOn && Math.hypot(x - this.fairyX, y - this.fairyY) < this.pad * 6.0f) {
                catchFairy();
                return true;
            }
            if (this.scrollOn && Math.hypot(x - scrollX(), y - scrollY()) < this.pad * 5.0f) {
                startMini();
                return true;
            }
            int i8 = this.compSel;
            if (i8 >= 0 && compUnlocked(i8) && z) {
                float abs = Math.abs(x - princessX());
                float f2 = this.hu;
                if (abs < 6.0f * f2) {
                    float f3 = this.groundY;
                    if (y > f3 - (24.0f * f2) && y < f3 + (f2 * 2.0f)) {
                        openPortrait(this.compSel);
                        return true;
                    }
                }
            }
            if (!this.farming || !this.bossBtn.contains(x, y)) {
                tapAttack();
                return true;
            }
            this.farming = false;
            this.stage++;
            spawn();
            showBanner("⚔ Вызов босса!", -30070);
            return true;
        }
        if (!z) {
            return true;
        }
        if (y >= this.actT && y <= this.actB) {
            float f4 = this.W / 3.0f;
            if (x < f4) {
                meteor(uptimeMillis);
            } else if (x < f4 * 2.0f) {
                this.buyMode = (this.buyMode + 1) % 3;
                this.tab = OV_NONE;
            } else if (this.towerFight) {
                showBanner("Сначала закончи бой в башне", -4609816);
            } else if (soulsGain() > 0.0d) {
                this.pendingSouls = soulsGain();
                this.overlay = 2;
            } else {
                showBanner("Реинкарнация откроется с этапа 30", -4609816);
            }
            return true;
        }
        if (y >= this.tabT && y <= this.tabB) {
            float f5 = this.pad;
            int min = Math.min(3, (int) ((x - (1.5f * f5)) / ((this.W - (f5 * 3.0f)) / 4.0f)));
            this.tab = min;
            if (min < 0) {
                this.tab = OV_NONE;
            }
            return true;
        }
        float f6 = this.upT;
        if (y < f6 || this.tab != 3) {
            if (y >= f6 && (i = this.tab) != 3) {
                int i9 = (i == 1 && ascUnlocked()) ? 6 : 5;
                int i10 = (int) ((y - this.upT) / ((this.rowH * 5.0f) / i9));
                if (i10 >= 0 && i10 < i9) {
                    tapRow(i10, false);
                    if (this.tab == 0) {
                        this.holdRow = i10;
                        this.holdStart = uptimeMillis;
                        this.lastHoldBuy = uptimeMillis;
                    }
                }
            }
            return true;
        }
        if (!z) {
            return true;
        }
        float f7 = this.pad;
        if (y < (4.2f * f7) + f6) {
            this.bestiaryView = x > ((float) this.W) / 2.0f;
            return true;
        }
        if (this.bestiaryView) {
            float f8 = f6 + (4.6f * f7);
            int i11 = (int) ((x - (f7 * 1.5f)) / ((this.W - (f7 * 3.0f)) / 3.0f));
            int i12 = (((int) ((y - f8) / (((this.H * 0.99f) - f8) / 7.0f))) * 3) + i11;
            if (i11 >= 0 && i11 < 3 && i12 >= 0 && i12 < BEAST_N) {
                if (this.bk[i12] > 0) {
                    showBanner("📖 " + beastName(i12) + ": " + BEAST_DESC[i12], -6295297);
                } else {
                    showBanner("❔ Ещё не встречен. Иди дальше!", -4609816);
                }
            }
        }
        return true;
    }

    void openPortrait() {
        openPortrait(OV_NONE);
    }

    void openPortrait(int i) {
        this.portraitWho = i;
        this.overlay = OV_PORTRAIT;
        this.portraitTaps = OV_NONE;
        this.portraitPop = 1.0f;
        this.portraitLine = compLine(i);
        this.sfx.play(6, 0.7f);
    }

    float padCX(int var1) {
       int var3 = this.W;
       float var2 = (float)var3 / 2.0F;
       byte var4;
       if (var1 % 2 == 0) {
          var4 = -1;
       } else {
          var4 = 1;
       }

       return var2 + (float)(var4 * var3) * 0.2F;
    }

    float padCY(int i) {
        int i2 = this.H;
        return (i2 * 0.48f) + (i < 2 ? 0.0f : i2 * 0.17f);
    }

    void panel(Canvas canvas, RectF rectF, float f, int i) {
        this.pr.set(rectF.left, rectF.top + (this.pad * 0.35f), rectF.right, rectF.bottom + (this.pad * 0.35f));
        this.p.setColor(1711276032);
        canvas.drawRoundRect(this.pr, f, f, this.p);
        this.p.setColor(i);
        canvas.drawRoundRect(rectF, f, f, this.p);
        this.p.setColor(788529151);
        float f2 = f * 0.7f;
        this.pr.set(rectF.left + f2, rectF.top + (this.pad * 0.18f), rectF.right - f2, rectF.top + (this.pad * 0.42f));
        canvas.drawRect(this.pr, this.p);
        this.p.setColor(939524096);
        this.pr.set(rectF.left + f2, rectF.bottom - (this.pad * 0.42f), rectF.right - f2, rectF.bottom - (this.pad * 0.18f));
        canvas.drawRect(this.pr, this.p);
    }

    JSONObject parseCode(String str) {
        try {
            int indexOf = str.indexOf("SC1:");
            if (indexOf < 0) {
                return null;
            }
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(Base64.decode(str.substring(indexOf + 4).trim().split("\\s")[OV_NONE], OV_NONE)));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = gZIPInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, OV_NONE, read);
            }
            JSONObject jSONObject = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
            if (jSONObject.has("stage")) {
                return jSONObject;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void pauseGame() {
        this.running = false;
        this.handler.removeCallbacks(this);
        this.sfx.musicPause();
        this.anchorPos = -1.0d;
        save();
    }

    public boolean onBack() {
        int i = this.overlay;
        if (i == OV_SETTINGS && this.setAsk > 0) {
            this.setAsk = OV_NONE;
            this.pendingImport = null;
            return true;
        }
        if (i == OV_CONFIRM || i == OV_PARTY || i == OV_SETTINGS || i == OV_PORTRAIT) {
            this.overlay = OV_NONE;
            return true;
        }
        return false;
    }

    public void releaseAudio() {
        pauseGame();
        this.sfx.release();
    }

    int pendingCompStory() {
        for (int i = 1; i < 5; i++) {
            if (compUnlocked(i)) {
                int i2 = 1 << i;
                if ((this.compMask & i2) != 0 && (this.compStoryMask & i2) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    void playCompStory(int i) {
        this.storyCur = i == 0 ? PRINCESS_STORY : COMP_STORY[i];
        this.storyTitle = i == 0 ? "💗 Спасение принцессы" : "👭 Знакомство: " + COMP_NAME[i];
        this.storyPage = OV_NONE;
        this.overlay = 1;
        this.compStoryMask = (1 << i) | this.compStoryMask;
        save();
    }

    void portraitTap(float f, float f2) {
        String compLine;
        int i = this.W;
        float f3 = i * 0.8f;
        float f4 = this.H * 0.12f;
        if (!(f > (((float) i) - f3) / 2.0f && f < (((float) i) + f3) / 2.0f && f2 > f4 && f2 < f4 + f3)) {
            if (SystemClock.uptimeMillis() - this.overlayAt > 500) {
                this.overlay = OV_NONE;
                return;
            }
            return;
        }
        int i2 = this.portraitTaps + 1;
        this.portraitTaps = i2;
        this.portraitPop = 1.0f;
        if (this.portraitWho != 0 || i2 < 7 || this.giftDay == today()) {
            do {
                compLine = compLine(this.portraitWho);
                if (!compLine.equals(this.portraitLine)) {
                    break;
                }
            } while (this.portraitWho == 0);
            this.portraitLine = compLine;
            this.sfx.play(OV_NONE, 0.7f);
            return;
        }
        this.giftDay = today();
        this.frenzyUntil = Math.max(this.frenzyUntil, SystemClock.uptimeMillis()) + 30000;
        this.portraitLine = "...ладно, держи бабл-ти. Ярость на 30 секунд 💗";
        this.sfx.play(7, 0.8f);
        this.sfx.buzz(60L);
        save();
    }

    boolean princess() {
        return this.towerFloor >= TOWER_TOP;
    }

    float princessX() {
        return this.heroX - (this.hu * 13.0f);
    }

    void putD(SharedPreferences.Editor editor, String str, double d) {
        editor.putLong(str, Double.doubleToRawLongBits(d));
    }

    boolean questDone(int i) {
        return questProg(i) >= ((long) QUEST_GOAL[this.questId[i]]);
    }

    double questGold() {
        return goldFor(this.stage) * 25.0d;
    }

    double questMana() {
        return (manaTap() * 100.0d) + (manaSec() * 120.0d);
    }

    long questProg(int var1) {
       int[] var3 = QUEST_GOAL;
       int var2 = this.questId[var1];
       return Math.min((long)var3[var2], Math.max(0L, this.questStat(var2) - this.questBase[var1]));
    }

    double questSouls() {
        return (this.bestStage / 40) + 1;
    }

    long questStat(int i) {
        switch (i) {
            case OV_NONE /* 0 */:
                return this.taps;
            case 1:
                return this.kills;
            case 2:
                return this.bossKills;
            case 3:
                return this.meteors;
            case 4:
                return this.fairies;
            case 5:
                return this.buys;
            case 6:
                return this.chests;
            default:
                return this.minisWon;
        }
    }

    void reincarnate() {
        double d = this.souls;
        double d2 = this.pendingSouls;
        this.souls = d + d2;
        this.soulsThisAsc += d2;
        this.lifeNo++;
        this.chest = false;
        this.towerFight = false;
        this.gold = 0.0d;
        this.mana = 0.0d;
        this.lifeStageIdx = OV_NONE;
        this.farming = false;
        this.frenzyUntil = 0L;
        int i = OV_NONE;
        while (true) {
            int[] iArr = this.lvl;
            if (i >= iArr.length) {
                this.stage = startStage();
                updateLifeStage(false);
                spawn();
                this.floats.clear();
                this.parts.clear();
                this.chunks.clear();
                this.sfx.play(7, 1.0f);
                save();
                return;
            }
            iArr[i] = OV_NONE;
            i++;
        }
    }

    String relDesc(int i) {
        int i2 = this.rel[i];
        switch (i) {
            case OV_NONE /* 0 */:
                return "Урон тапа +" + (i2 * OV_BEAT) + "%";
            case 1:
                return "Время на босса +" + (i2 * 2) + "с";
            case 2:
                return "Золото +" + (i2 * 10) + "%";
            case 3:
                return "Феи чаще на " + (i2 * 6) + "%";
            case 4:
                return "Метеор +" + (i2 * TOWER_TOP) + "%";
            default:
                return "Шанс мимика " + chestChance() + "%";
        }
    }

    int relicChance() {
        if (this.bossKills <= 1) {
            return 100;
        }
        return (eventIdx() == 4 ? 2 : 1) * 25;
    }

    void restartGame() {
        this.noSave = true;
        this.running = false;
        this.handler.removeCallbacks(this);
        this.sfx.musicPause();
        try {
            ((Activity) getContext()).recreate();
        } catch (Exception e) {
        }
    }

    public void resume() {
        this.running = true;
        this.lastFrame = SystemClock.uptimeMillis();
        this.handler.removeCallbacks(this);
        this.handler.post(this);
        this.sfx.musicResume();
    }

    boolean rhythmOn() {
        return this.hasPhones && this.phonesOn;
    }

    void rowBox(Canvas canvas, int i, boolean z, int i2, String str, String str2, String str3, String str4, boolean z2, int i3, Bitmap bitmap) {
        rowBox(canvas, i, z, i2, str, str2, str3, str4, z2, i3, bitmap, null);
    }

    void rowBox(
       Canvas var1, int var2, boolean var3, int var4, String var5, String var6, String var7, String var8, boolean var9, int var10, Bitmap var11, String var12
    ) {
       float var15 = this.upT;
       float var13 = (float)var2;
       float var14 = this.rowH;
       var13 = var15 + var13 * var14;
       var15 = this.pad;
       var14 = var14 + var13 - 0.7F * var15;
       this.r.set(var15 * 1.5F, var13, (float)this.W - var15 * 1.5F, var14);
       RectF var20 = this.r;
       var15 = this.pad;
       if (var3) {
          var2 = -12964514;
       } else {
          var2 = -14411203;
       }

       this.panel(var1, var20, var15 * 1.2F, var2);
       float var16 = this.pad * 5.0F;
       var15 = (var13 + var14) / 2.0F;
       if (this.rowIcon != null) {
          float var17 = this.rowH * 0.3F;
          this.p.setColor(var4);
          this.p.setAlpha(55);
          var1.drawCircle(var16, var15, var17, this.p);
          this.p.setAlpha(255);
          float var18 = 1.75F * var17 / (float)this.rowIcon.getWidth();
          Bitmap var53 = this.rowIcon;
          this.blit(var1, var53, var16, (float)var53.getHeight() * var18 / 2.0F + var15 - 0.12F * var17, var18, 1.0F, 1.0F, this.bp);
          this.tp.setTypeface(this.pixTf);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(this.pad * 1.05F);
          var18 = Math.max(this.pad * 2.2F, this.tp.measureText(var5) + this.pad * 0.9F);
          var20 = this.pr;
          var18 /= 2.0F;
          var17 = var15 + 0.55F * var17;
          var20.set(var16 - var18, var17, var18 + var16, this.pad * 1.7F + var17);
          this.p.setColor(-15331286);
          var20 = this.pr;
          var16 = this.pad;
          var1.drawRoundRect(var20, var16 * 0.6F, var16 * 0.6F, this.p);
          this.p.setColor(var4);
          this.p.setStyle(Style.STROKE);
          this.p.setStrokeWidth(this.pad * 0.18F);
          var20 = this.pr;
          var16 = this.pad;
          var1.drawRoundRect(var20, var16 * 0.6F, var16 * 0.6F, this.p);
          this.p.setStyle(Style.FILL);
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setColor(-1);
          var1.drawText(var5, this.pr.centerX(), this.pr.centerY() + this.pad * 0.5F, this.tp);
          this.tp.setTypeface(Typeface.DEFAULT);
          this.tp.setFakeBoldText(true);
          this.rowIcon = null;
       } else {
          this.p.setColor(var4);
          var1.drawCircle(var16, var15, this.rowH * 0.28F, this.p);
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setColor(-15069136);
          this.tp.setTextSize(this.rowH * 0.26F);
          this.tp.setFakeBoldText(true);
          var1.drawText(var5, var16, var15 + this.rowH * 0.09F, this.tp);
       }

       this.tp.setTextAlign(Align.LEFT);
       this.tp.setColor(-1);
       this.tp.setTextSize(this.pad * 1.9F);
       var16 = this.pad;
       this.drawFit(var1, var6, var16 * 8.5F, var15 - var16 * 0.3F, (float)this.W * 0.5F);
       this.tp.setFakeBoldText(false);
       this.tp.setColor(-5728052);
       this.tp.setTextSize(this.pad * 1.45F);
       var16 = this.pad;
       this.drawFit(var1, var7, var16 * 8.5F, var15 + var16 * 1.8F, (float)this.W * 0.5F);
       var2 = this.W;
       var15 = (float)var2 * 0.26F;
       RectF var26 = this.btn;
       float var48 = (float)var2;
       var16 = this.pad;
       var26.set(var48 - var16 * 2.3F - var15, var13 + var16 * 0.8F, (float)var2 - 2.3F * var16, var14 - var16 * 0.8F);
       RectF var27 = this.btn;
       var13 = this.pad;
       if (!var9) {
          var10 = -12963760;
       }

       this.panel(var1, var27, var13, var10);
       var13 = this.btn.centerY();
       var14 = this.pad;
       byte var25 = -1;
       if (var12 != null) {
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(this.pad * 1.25F);
          Paint var28 = this.tp;
          if (var9) {
             var2 = -570425345;
          } else {
             var2 = -7569238;
          }

          var28.setColor(var2);
          var1.drawText(var12, this.btn.centerX(), this.btn.top + this.pad * 1.7F, this.tp);
          var13 = this.btn.centerY() + this.pad * 1.35F;
       } else {
          var13 += var14 * 0.65F;
       }

       this.tp.setTextAlign(Align.CENTER);
       Paint var29 = this.tp;
       if (var9) {
          var2 = var25;
       } else {
          var2 = -7569238;
       }

       var29.setColor(var2);
       this.tp.setTextSize(this.pad * 1.8F);
       this.tp.setFakeBoldText(true);
       if (var11 != null) {
          float var52 = this.pad * 0.24F;
          var48 = Math.min(this.tp.measureText(var8), var15 - this.pad * 4.0F);
          var14 = (float)var11.getWidth() * var52;
          var16 = this.pad * 0.5F;
          float var19 = this.btn.centerX() - (var14 + var16 + var48) / 2.0F;
          this.blit(var1, var11, var19 + var14 / 2.0F, var13 + this.pad * 0.15F, var52, 1.0F, 1.0F, this.bp);
          this.drawFit(var1, var8, var19 + var14 + var16 + var48 / 2.0F, var13, var15 - this.pad * 4.0F);
       } else {
          this.drawFit(var1, var8, this.btn.centerX(), var13, var15 - this.pad);
       }

       this.tp.setFakeBoldText(false);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.running) {
            long uptimeMillis = SystemClock.uptimeMillis();
            float min = Math.min((uptimeMillis - this.lastFrame) / 1000.0f, 0.1f);
            this.lastFrame = uptimeMillis;
            update(min, uptimeMillis);
            invalidate();
            this.handler.postDelayed(this, 16L);
        }
    }

    void save() {
        if (this.noSave) {
            return;
        }
        SharedPreferences.Editor edit = this.prefs.edit();
        putD(edit, "gold", this.gold);
        putD(edit, "mana", this.mana);
        putD(edit, "souls", this.souls);
        putD(edit, "hp", this.monsterHp);
        putD(edit, "lgold", this.lifetimeGold);
        edit.putInt("stage", this.stage);
        edit.putInt("life", this.lifeNo);
        edit.putInt("best", this.bestStage);
        edit.putLong("taps2", this.taps);
        edit.putLong("kills", this.kills);
        edit.putLong("bkills", this.bossKills);
        edit.putLong("fairies", this.fairies);
        edit.putLong("meteors", this.meteors);
        edit.putInt("ach", this.achMask);
        edit.putInt("lifeIdx", this.lifeStageIdx);
        edit.putBoolean("story", this.storySeen);
        edit.putBoolean("farming", this.farming);
        edit.putBoolean("muted", this.sfx.muted);
        edit.putInt("buyMode", this.buyMode);
        for (int i = OV_NONE; i < 5; i++) {
            edit.putInt("lvl" + i, this.lvl[i]);
            edit.putInt("alt" + i, this.alt[i]);
        }
        edit.putLong("buys", this.buys);
        edit.putLong("chests", this.chests);
        edit.putLong("qdone", this.questsDone);
        edit.putLong("lastDay", this.lastDay);
        edit.putInt("streak", this.streak);
        edit.putBoolean("dailyP", this.dailyPending);
        edit.putInt("compSel", this.compSel);
        edit.putInt("compMask", this.compMask);
        edit.putInt("compStory", this.compStoryMask);
        edit.putBoolean("phones", this.hasPhones);
        edit.putBoolean("phonesOn", this.phonesOn);
        edit.putInt("beatWins", this.beatWins);
        edit.putFloat("isekaiG", this.isekaiGauge);
        edit.putInt("bestCombo", this.bestCombo);
        edit.putLong("isekais", this.isekais);
        edit.putLong("minis", this.minisWon);
        edit.putInt("tower", this.towerFloor);
        edit.putLong("giftDay", this.giftDay);
        edit.putInt("hints", this.hintMask);
        edit.putInt("chap", this.chapterMask);
        edit.putInt("stars", this.stars);
        edit.putInt("ascs", this.ascensions);
        edit.putFloat("soulsAsc", (float) this.soulsThisAsc);
        edit.putInt("sndMode", this.soundMode);
        edit.putBoolean("relNew", this.relicNew);
        edit.putBoolean("optMusic", this.optMusic);
        edit.putBoolean("optSfx", this.optSfx);
        edit.putBoolean("optVib", this.optVib);
        edit.putBoolean("optNum", this.optNum);
        for (int i2 = OV_NONE; i2 < BEAST_N; i2++) {
            edit.putLong("bk" + i2, this.bk[i2]);
        }
        for (int i3 = OV_NONE; i3 < 6; i3++) {
            edit.putInt("rel" + i3, this.rel[i3]);
        }
        edit.putLong("qday", this.questDay);
        edit.putInt("qclaim", this.questClaimed);
        for (int i4 = OV_NONE; i4 < 3; i4++) {
            edit.putInt("qid" + i4, this.questId[i4]);
            edit.putLong("qbase" + i4, this.questBase[i4]);
        }
        edit.putLong("time", System.currentTimeMillis());
        edit.apply();
    }

    float scrollX() {
        return (this.W * 0.44f) + (((float) Math.sin(this.scrollT * 1.7f)) * this.pad * 2.0f);
    }

    float scrollY() {
        float f = this.battleT;
        return f + ((this.battleB - f) * 0.42f) + (((float) Math.sin(this.scrollT * SUPER_END)) * this.pad * 1.5f);
    }

    void settingsMsg(String str) {
        this.setMsg = str;
        this.setMsgT = 4.0f;
    }

    void settingsTap(float f, float f2) {
        JSONObject jSONObject;
        String str = null;
        if (this.setAsk > 0) {
            if (this.askNo.contains(f, f2)) {
                this.setAsk = OV_NONE;
                this.pendingImport = null;
                return;
            }
            if (this.askYes.contains(f, f2)) {
                int i = this.setAsk;
                if (i == 1 && (jSONObject = this.pendingImport) != null) {
                    applyCode(jSONObject);
                    return;
                }
                if (i == 2) {
                    this.setAsk = 3;
                    this.setAskAt = SystemClock.uptimeMillis();
                    return;
                } else {
                    if (i == 3 && SystemClock.uptimeMillis() - this.setAskAt > 600) {
                        this.prefs.edit().clear().commit();
                        restartGame();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        int i2 = -1;
        int i3 = OV_NONE;
        while (true) {
            RectF[] rectFArr = this.setRows;
            if (i3 >= rectFArr.length) {
                break;
            }
            if (rectFArr[i3].contains(f, f2)) {
                i2 = i3;
            }
            i3++;
        }
        if (i2 < 0) {
            this.overlay = OV_NONE;
            return;
        }
        this.sfx.play(OV_NONE, 0.6f);
        switch (i2) {
            case OV_NONE /* 0 */:
                this.optMusic = !this.optMusic;
                applySound();
                break;
            case 1:
                this.optSfx = !this.optSfx;
                applySound();
                break;
            case 2:
                this.optVib = !this.optVib;
                applySound();
                if (this.optVib) {
                    this.sfx.buzz(60L);
                }
                break;
            case 3:
                this.optNum = !this.optNum;
                break;
            case 4:
                String exportCode = exportCode();
                if (exportCode != null) {
                    try {
                        ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Второй шанс — сохранение", exportCode));
                        settingsMsg("✅ Код скопирован! Вставь его в заметки или отправь себе в Телеграм");
                        break;
                    } catch (Exception e) {
                        settingsMsg("Не удалось скопировать код");
                        break;
                    }
                } else {
                    settingsMsg("Не получилось создать код 😔");
                    break;
                }
            case 5:
                try {
                    ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
                    if (clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClip().getItemCount() > 0) {
                        str = String.valueOf(clipboardManager.getPrimaryClip().getItemAt(OV_NONE).coerceToText(getContext()));
                    }
                } catch (Exception e2) {
                }
                if (str != null && str.contains("SC1:")) {
                    JSONObject parseCode = parseCode(str);
                    if (parseCode != null) {
                        this.pendingImport = parseCode;
                        this.setAsk = 1;
                        break;
                    } else {
                        settingsMsg("Код повреждён — скопируй его целиком");
                        break;
                    }
                } else {
                    settingsMsg("В буфере нет кода. Скопируй код сохранения и нажми снова");
                    break;
                }
            case 6:
                this.setAsk = 2;
                break;
        }
        applySound();
        this.sfx.musicResume();
        save();
    }

    void shatter() {
        Bitmap mobBitmap = mobBitmap();
        float f = this.mu * (bossFight() ? 1.3f : 1.0f);
        if (mobBitmap.getWidth() > 24) {
            f *= 0.62f;
        }
        float f2 = f * ((this.mobSpawn * 0.7f) + 0.3f);
        float width = this.mobX - ((mobBitmap.getWidth() * f2) / 2.0f);
        float height = (this.groundY + f2) - (mobBitmap.getHeight() * f2);
        int i = mobBitmap.getWidth() > 24 ? 3 : 2;
        float f3 = this.W / 1080.0f;
        for (int i2 = OV_NONE; i2 < mobBitmap.getHeight(); i2 += i) {
            for (int i3 = OV_NONE; i3 < mobBitmap.getWidth(); i3 += i) {
                int pixel = mobBitmap.getPixel(i3, i2);
                if ((pixel >>> 24) >= 128) {
                    Fx fx = new Fx();
                    float f4 = i;
                    float f5 = f4 / 2.0f;
                    fx.x = ((i3 + f5) * f2) + width;
                    fx.y = ((i2 + f5) * f2) + height;
                    fx.vx = ((fx.x - this.mobX) * 4.0f) + ((this.rnd.nextFloat() - 0.5f) * 420.0f * f3);
                    fx.vy = ((fx.y - this.mobY) * 2.5f) - (((this.rnd.nextFloat() * 520.0f) + 380.0f) * f3);
                    fx.size = f4 * f2;
                    fx.color = pixel;
                    fx.max = (this.rnd.nextFloat() * 0.6f) + 0.9f;
                    this.chunks.add(fx);
                }
            }
        }
        while (this.chunks.size() > 450) {
            this.chunks.remove(OV_NONE);
        }
    }

    void showBanner(String str, int i) {
        this.banner = str;
        this.bannerT = 3.0f;
        this.bannerColor = i;
    }

    void showHint(int i, String str) {
        int i2 = this.hintMask;
        int i3 = 1 << i;
        if ((i2 & i3) != 0) {
            return;
        }
        this.hintMask = i3 | i2;
        this.banner = "💡 " + str;
        this.bannerT = 5.5f;
        this.bannerColor = -6295297;
    }

    double soulMult() {
        return soulPart(this.souls) * starMult();
    }

    double soulsGain() {
        int i = this.stage;
        if (i < 30) {
            return 0.0d;
        }
        return Math.floor(Math.pow(i / 10.0d, 1.6d) * ((Math.min(this.stars, TOWER_TOP) * 0.1d) + 1.0d));
    }

    void spawn() {
        this.chest = !isBoss(this.stage) && this.stage >= 3 && this.rnd.nextInt(100) < chestChance();
        double hpFor = hpFor(this.stage) * (this.chest ? 3 : 1);
        this.monsterMax = hpFor;
        this.monsterHp = hpFor;
        this.mobSpawn = 0.0f;
        if (isBoss(this.stage)) {
            this.bossTime = bossTimeMax();
            this.bossIntroT = 1.6f;
            this.bossLanded = false;
            if (!this.scrollOn) {
                this.scrollAt = SystemClock.uptimeMillis() + 2500;
            }
            if (trait() > 0) {
                showBanner("👑 " + mobName() + " — " + TRAIT_DESC[trait()], -25958);
            } else if (storyBoss() && this.bossKills > 0) {
                showBanner("📖 Сюжетный босс! После победы — новая глава истории", -11430);
            } else if (this.bossKills == 0) {
                showHint(2, "👑 Босс! Убей его, пока не кончился таймер");
            }
        }
        if (this.chest) {
            showBanner("💰 Сундук-мимик! Добей его — внутри сокровища", -11430);
        }
    }

    void spawnCoins(int i) {
        float f = this.W / 1080.0f;
        for (int i2 = OV_NONE; i2 < i; i2++) {
            Fx fx = new Fx();
            fx.x = this.mobX + ((this.rnd.nextFloat() - 0.5f) * this.mobR);
            fx.y = this.mobY;
            fx.vx = (this.rnd.nextFloat() - 0.5f) * 500.0f * f;
            fx.vy = (-((this.rnd.nextFloat() * 400.0f) + 500.0f)) * f;
            fx.max = (this.rnd.nextFloat() * 0.15f) + 0.28f;
            fx.size = this.rnd.nextFloat() * 6.0f;
            this.coins.add(fx);
        }
        while (this.coins.size() > 18) {
            this.coins.remove(OV_NONE);
        }
    }

    double starMult() {
        return starPart(this.stars) * (princess() ? 2 : 1);
    }

    int starsGain() {
        return (int) Math.floor(Math.pow(this.soulsThisAsc / 30.0d, 0.6d));
    }

    void startBeatFight() {
        this.overlay = OV_BEAT;
        this.bfOpenAt = SystemClock.uptimeMillis();
        int i = OV_NONE;
        this.bfState = OV_NONE;
        this.notes.clear();
        this.bfBossHp = BF_BOSS_MAX;
        this.bfHeroHp = 100.0f;
        this.bfCombo = OV_NONE;
        this.bfPhase = OV_NONE;
        this.bfPrevPos = -1.0d;
        this.bfUnwrap = 0.0d;
        this.bfStartBeat = -1.0d;
        this.bfSilent = false;
        this.bfSilentAnnounced = false;
        boolean z = this.hasPhones;
        this.bfTalk = z ? "Опять ты? Ну давай, качнём!" : "Йо, попаданец! Проверим твои пальцы!";
        this.bfTalkT = 3.5f;
        if (z) {
            i = 3;
        }
        this.bfTutorHits = i;
        this.bfLastLane = 1;
        this.sfx.setTrack(3);
    }

    void startDeath() {
        this.overlay = 6;
        this.deathT = 0.0f;
        int i = OV_NONE;
        this.deathCrash = false;
        this.bannerT = 0.0f;
        int nextInt = this.rnd.nextInt(100);
        int i2 = this.lifeNo;
        if (i2 != 1 && nextInt >= 70) {
            i = nextInt < 85 ? 1 : 2;
        }
        this.deathKind = i;
        String[] strArr = DEATH_CAPS[i];
        this.deathCap = (i2 == 1 && i == 0) ? strArr[1] : strArr[this.rnd.nextInt(strArr.length)];
        if (this.deathKind == 0) {
            this.sfx.play(9, 1.0f);
        }
    }

    void startIsekai() {
        this.isekaiT = 0.0f;
        this.isekaiHit = false;
        this.isekaiGauge = 0.0f;
        this.sfx.play(9, 1.0f);
        this.sfx.buzz(60L);
    }

    void startMini() {
        this.scrollOn = false;
        this.miniType = this.rnd.nextInt(3);
        this.miniPhase = OV_NONE;
        this.miniT = 0.0f;
        this.miniRound = OV_NONE;
        this.miniScore = OV_NONE;
        this.miniMsgT = 0.0f;
        this.overlay = 9;
        this.sfx.play(4, 0.8f);
    }

    int startStage() {
        return (this.alt[OV_NONE] * 3) + 1;
    }

    void stepCoins(float f) {
        float f2 = this.pad * 3.0f;
        float f3 = this.headerB * 0.55f;
        float f4 = this.W / 1080.0f;
        Iterator<Fx> it = this.coins.iterator();
        while (it.hasNext()) {
            Fx next = it.next();
            next.life += f;
            if (next.life < next.max) {
                next.x += next.vx * f;
                next.y += next.vy * f;
                next.vy += 2000.0f * f4 * f;
            } else {
                float min = ((Math.min(1.0f, (next.life - next.max) * 3.2f) * 2600.0f) + 900.0f) * f4 * f;
                float f5 = f2 - next.x;
                float f6 = f3 - next.y;
                float hypot = (float) Math.hypot(f5, f6);
                if (hypot < this.pad + min) {
                    it.remove();
                    this.goldPulse = 1.0f;
                } else {
                    next.x += (f5 / hypot) * min;
                    next.y += (f6 / hypot) * min;
                }
            }
        }
    }

    void stepFx(ArrayList<Fx> arrayList, float f, float f2) {
        Iterator<Fx> it = arrayList.iterator();
        while (it.hasNext()) {
            Fx next = it.next();
            next.life += f;
            next.x += next.vx * f;
            next.y += next.vy * f;
            next.vy += f2 * f;
            if (next.life >= next.max) {
                it.remove();
            }
        }
    }

    boolean storyBoss() {
        return !this.towerFight && isBoss(this.stage) && this.stage <= 100 && (this.chapterMask & (1 << bossIdx())) == 0;
    }

    boolean storyTapReady(long j) {
        long j2;
        if (this.storyPage == 0) {
            j2 = this.storyCur == STORY ? 600 : 1300;
        } else {
            j2 = 650;
        }
        return j - this.storyPageAt > j2;
    }

    void tapAttack() {
        this.taps++;
        int judgeTap = judgeTap();
        double tapDmg = tapDmg() * comboMult();
        boolean z = this.rnd.nextInt(100) < critChance();
        if (z) {
            tapDmg *= 5.0d;
        }
        if (trait() == 3) {
            tapDmg *= 0.5d;
        }
        this.mana += manaTap();
        addFloat(this.mobX, this.mobY - (this.mobR * 0.5f), (z ? "КРИТ! " : "") + fmt(tapDmg), z ? -42406 : -1, this.pad * (z ? 2.8f : 1.9f));
        Fx fx = new Fx();
        fx.max = 0.22f;
        this.bolts.add(fx);
        this.heroCast = 1.0f;
        if (z) {
            this.sfx.play(1, judgeTap > 0 ? 0.4f : 0.6f);
            this.sfx.buzz(25L);
        } else if (judgeTap <= 0) {
            this.sfx.play(OV_NONE, 0.25f);
        }
        damage(tapDmg, true);
    }

    double tapDmg() {
       int var1 = this.lvl[0];
       return (double)(var1 * 2 + 1) * ms(var1) * ((double)this.rel[0] * 0.15 + 1.0) * this.grimMult() * this.soulMult() * this.achMult() * this.frenzy();
    }

    boolean tapRow(int i, boolean z) {
        int i2 = this.tab;
        if (i2 == 0) {
            double[] batch = batch(i);
            if (batch[OV_NONE] > 0.0d) {
                double wallet = wallet(i);
                double d = batch[1];
                if (wallet >= d) {
                    if (UP_MANA[i]) {
                        this.mana -= d;
                    } else {
                        this.gold -= d;
                    }
                    int[] iArr = this.lvl;
                    int i3 = iArr[i];
                    int i4 = ((int) batch[OV_NONE]) + i3;
                    iArr[i] = i4;
                    if (i <= 2 && i4 / 25 > i3 / 25) {
                        showBanner("⭐ Веха! " + UP_NAME[i] + ": эффект ×2", -11430);
                        this.sfx.play(3, 0.6f);
                    }
                    this.buys += (int) batch[OV_NONE];
                    this.sfx.play(4, 0.5f);
                    return true;
                }
            }
            return false;
        }
        if (i2 != 2) {
            if (i2 != 1 || z) {
                return false;
            }
            if (i == 5) {
                if (!ascUnlocked()) {
                    return false;
                }
                if (starsGain() < 1) {
                    showBanner("Набери больше душ в этом цикле, чтобы получить звезду", -4609816);
                    return false;
                }
                this.overlay = 7;
                return true;
            }
            if (this.alt[i] >= ALT_MAX[i]) {
                showBanner("Максимальный уровень", -4609816);
                return false;
            }
            double altCost = altCost(i);
            double d2 = this.souls;
            if (d2 < altCost) {
                showBanner("Нужно " + fmt(altCost) + " душ", -4609816);
                return false;
            }
            this.souls = d2 - altCost;
            int[] iArr2 = this.alt;
            iArr2[i] = iArr2[i] + 1;
            this.sfx.play(4, 0.7f);
            return true;
        }
        if (z) {
            return false;
        }
        if (i >= 3) {
            if (i != 3 || (this.questClaimed & 8) != 0) {
                return false;
            }
            if (!allQuestsClaimed()) {
                showBanner("Сначала забери награды за все 3 задания", -4609816);
                return false;
            }
            double questSouls = questSouls();
            this.souls += questSouls;
            this.questClaimed |= 8;
            showBanner("👻 Награда дня: +" + fmt(questSouls) + " душ!", -8395009);
            this.sfx.play(7, 0.8f);
            this.sfx.buzz(60L);
            save();
            return true;
        }
        int i5 = 1 << i;
        if ((this.questClaimed & i5) != 0) {
            return false;
        }
        if (!questDone(i)) {
            showBanner("Задание ещё не выполнено", -4609816);
            return false;
        }
        double questGold = questGold();
        double questMana = questMana();
        this.gold += questGold;
        this.lifetimeGold += questGold;
        this.mana += questMana;
        this.questClaimed = i5 | this.questClaimed;
        this.questsDone++;
        showBanner("📜 Задание выполнено: +" + fmt(questGold) + " 🪙  +" + fmt(questMana) + " 💧", -11430);
        this.sfx.play(4, 0.9f);
        return true;
    }

    String towerFoe(int i) {
        if (i == TOWER_TOP) {
            return "Дракон-хранитель";
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = TOWER_FOE;
        return sb.append(strArr[(i - 1) % strArr.length]).append(i > 10 ? " II" : "").toString();
    }

    double towerHp(int i) {
        return hpFor((i * 5) + 5) * 8.0d * 1.5d;
    }

    void towerLose() {
        showBanner("🏰 Страж оказался сильнее. Прокачайся и возвращайся!", -30070);
        this.sfx.play(8, 0.8f);
        this.sfx.buzz(200L);
        leaveTower();
    }

    int towerNext() {
        return this.towerFloor + 1;
    }

    double towerSouls(int i) {
        return Math.max(1L, Math.round((i * 0.6d) + 1.0d));
    }

    int towerSprite(int i) {
        if (i == TOWER_TOP) {
            return 2;
        }
        return (i - 1) % 9;
    }

    boolean towerUnlocked() {
        return this.bestStage >= OV_BEAT || this.towerFloor > 0;
    }

    void towerWin() {
        int i = towerNext();
        this.towerFloor = i;
        double d = towerSouls(i);
        this.souls += d;
        this.soulsThisAsc += d;
        this.shake = 1.0f;
        burst(40, -16310);
        this.sfx.play(3, 1.0f);
        this.sfx.buzz(150L);
        showBanner("🏰 Этаж " + i + " покорён! +" + fmt(d) + " душ", -16310);
        leaveTower();
        if (i == TOWER_TOP) {
            this.pendingPrincess = true;
        } else if (i % 5 == 0) {
            dropRelic(true);
        }
        save();
    }

    int trait() {
        return (this.towerFight || !isBoss(this.stage)) ? OV_NONE : BOSS_TRAIT[bossIdx()];
    }

    float truckX() {
        float width = this.bmTruck.getWidth() * this.hu * 1.6f;
        return (-width) + ((this.W + (width * 2.0f)) * (this.isekaiT / 1.3f));
    }

    /* JADX WARN: Removed duplicated region for block: B:162:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x05af  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x05d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void update(float var1, long var2) {
       this.time += var1;
       if (this.overlay == 0) {
          double var4 = this.mana;
          double var8 = this.manaSec();
          double var6 = (double)var1;
          this.mana = var4 + var8 * var6;
          var8 = this.autoDps();
          if (this.trait() == 2) {
             var4 = 0.25;
          } else {
             var4 = 1.0;
          }

          var8 = var8 * var6 * var4 * this.comboMult();
          if (this.trait() == 1) {
             var4 = this.monsterHp;
             if (var4 > 0.0) {
                double var10 = this.monsterMax;
                this.monsterHp = Math.min(var10, var4 + 0.015 * var10 * var6);
             }
          }

          if (var8 > 0.0) {
             this.damage(var8, false);
             float var12 = this.famShotT + var1;
             this.famShotT = var12;
             if (var12 > 0.55F) {
                this.famShotT = 0.0F;
                GameView.Fx var21 = new GameView.Fx();
                var21.max = 0.35F;
                this.famShots.add(var21);
             }
          }

          if (this.bossFight()) {
             if (this.bossIntroT <= 0.0F) {
                this.bossTime -= var1;
             }

             if (this.bossTime <= 0.0F) {
                this.bossFailed();
             }
          }

          int var16 = this.holdRow;
          if (var16 >= 0 && var2 - this.holdStart > 400L && var2 - this.lastHoldBuy > 110L) {
             if (this.tapRow(var16, true)) {
                this.lastHoldBuy = var2;
             } else {
                this.holdRow = -1;
             }
          }

          if (!this.scrollOn) {
             long var18 = this.scrollAt;
             if (var18 > 0L && var2 > var18 && this.W > 0 && this.bossFight()) {
                this.scrollAt = 0L;
                this.scrollOn = true;
                this.scrollT = 0.0F;
                this.sfx.play(6, 0.5F);
                this.showHint(8, "\ud83d\udcdc Свиток испытания! Пройди мини-игру — нанесёшь боссу огромный урон");
             }
          }

          if (this.scrollOn) {
             this.scrollT += var1;
             if (!this.bossFight()) {
                this.scrollOn = false;
             }
          }

          if (!this.fairyOn && var2 > this.nextFairy && this.W > 0) {
             this.fairyOn = true;
             this.fairyT = 0.0F;
             float var27;
             if (this.rnd.nextBoolean()) {
                var27 = 1.0F;
             } else {
                var27 = -1.0F;
             }

             this.fairyDir = var27;
             this.sfx.play(6, 0.6F);
          }

          if (this.fairyOn) {
             float var13 = this.fairyT + var1;
             this.fairyT = var13;
             float var28 = var13 / 9.0F;
             if (this.fairyDir > 0.0F) {
                float var14 = this.pad;
                var28 = -var14 * 4.0F + ((float)this.W + var14 * 8.0F) * var28;
             } else {
                var16 = this.W;
                float var49 = (float)var16;
                float var15 = this.pad;
                var28 = var49 + var15 * 4.0F - ((float)var16 + var15 * 8.0F) * var28;
             }

             this.fairyX = var28;
             var28 = this.battleT;
             this.fairyY = var28 + (this.battleB - var28) * 0.35F + (float)Math.sin((double)(var13 * 3.0F)) * (this.battleB - this.battleT) * 0.15F;
             if (this.fairyT > 9.0F) {
                this.fairyOn = false;
                this.nextFairy = this.fairyGap() + var2;
             }
          }

          if (var2 - this.lastAchCheck > 1000L) {
             this.checkAch();
             this.checkHints();
             this.checkComps();
             this.lastAchCheck = var2;
          }

          if (var2 - this.lastDayCheck > 5000L) {
             this.checkDay();
             this.lastDayCheck = var2;
          }

          this.princessSayT = Math.max(0.0F, this.princessSayT - var1);
          if (this.dailyPending && this.bannerT <= 0.5F) {
             this.overlay = 4;
             this.dailyShownAt = var2;
          } else if (this.pendingPrincess && this.bannerT <= 1.2F) {
             this.pendingPrincess = false;
             this.compStoryMask |= 1;
             this.storyCur = PRINCESS_STORY;
             this.storyTitle = "\ud83d\udc97 Спасение принцессы";
             this.storyPage = 0;
             this.overlay = 1;
             this.nextPrincessLine = 15000L + var2;
          } else if (this.bannerT <= 1.2F && !this.bossFight() && this.pendingCompStory() > 0) {
             this.playCompStory(this.pendingCompStory());
          } else {
             var16 = this.pendingChapter;
             if (var16 >= 0 && this.bannerT <= 1.2F) {
                this.storyCur = CH_TEXT[var16];
                this.storyTitle = CH_TITLE[var16];
                this.chapterMask |= 1 << var16;
                this.storyPage = 0;
                this.overlay = 1;
                this.pendingChapter = -1;
                this.save();
             }
          }

          if (var2 - this.lastSave > 5000L) {
             this.save();
             this.lastSave = var2;
          }
       }

       int var17 = this.overlay;
       int var53 = 3;
       if (var17 == 6) {
          float var31 = this.deathT + var1;
          this.deathT = var31;
          if (!this.deathCrash && var31 >= 1.2F) {
             this.deathCrash = true;
             this.shake = 1.0F;
             this.sfx.play(10, 1.0F);
             this.sfx.buzz(350L);
             this.burstAt(this.heroX, (this.heroTop() + this.groundY) / 2.0F, 40, ROBE[this.lifeStageIdx]);
          }

          if (this.deathT >= 3.8F) {
             this.reincarnate();
             this.overlay = 3;
          }
       }

       if (this.overlay == 9) {
          this.updateMini(var1);
       }

       this.stepCoins(var1);
       Sfx var55 = this.sfx;
       if (this.overlay != 15) {
          var53 = this.biome();
       }

       var55.setTrack(var53);
       this.BEAT = 60.0 / this.sfx.bpmNow();
       this.LOOP = this.sfx.loopSecNow();
       if (this.overlay == 15) {
          this.updateBeatFight(var1);
       } else {
          this.sfx.setDuck(1.0F);
       }

       this.judgeT = Math.max(0.0F, this.judgeT - var1);
       boolean var20;
       if (this.overlay == 0 && !(this.bossIntroT > 0.0F) && !(this.isekaiT >= 0.0F)) {
          var20 = false;
       } else {
          var20 = true;
       }

       if (var20) {
          this.lastJudgeAt += (double)(1000.0F * var1);
       }

       if (this.comboFrozen && !var20 && this.combo > 0) {
          this.judge("КОМБО СОХРАНЕНО", -6295297);
       }

       this.comboFrozen = var20;
       if (this.combo > 0 && !var20 && (double)SystemClock.uptimeMillis() - this.lastJudgeAt > this.BEAT * 1000.0 * 2.6) {
          if (this.combo >= 10) {
             this.judge("КОМБО ПОТЕРЯНО", -38294);
          }

          this.combo = 0;
       }

       float var32 = this.isekaiT;
       if (var32 >= 0.0F) {
          this.isekaiT = var32 + var1;
          float var45 = (float)this.bmTruck.getWidth();
          var32 = this.hu;
          if (!this.isekaiHit && this.truckX() + var45 * var32 * 1.6F / 2.0F >= this.mobX - this.mobR * 0.4F) {
             this.isekaiHitNow();
          }

          if (this.isekaiT > 1.3F) {
             this.isekaiT = -1.0F;
          }
       }

       var32 = this.victimT;
       if (var32 >= 0.0F) {
          var32 += var1;
          this.victimT = var32;
          if (var32 > 0.9F) {
             var32 = (float)this.W;
             float var46 = this.battleT;
             this.burstAt(var32 * 0.86F, var46 + (this.groundY - var46) * 0.14F, 25, -5213953);
             this.victimT = -1.0F;
          }
       }

       var32 = this.goldPulse;
       float var47 = 4.0F * var1;
       this.goldPulse = Math.max(0.0F, var32 - var47);
       var32 = this.bossIntroT;
       if (var32 > 0.0F) {
          var32 -= var1;
          this.bossIntroT = var32;
          if (!this.bossLanded && var32 <= 1.0F) {
             this.bossLanded = true;
             this.shake = Math.max(this.shake, 0.8F);
             this.sfx.play(10, 0.5F);
             this.sfx.buzz(90L);

             for (int var54 = 0; var54 < 18; var54++) {
                GameView.Fx var56 = new GameView.Fx();
                if (var54 % 2 == 0) {
                   var32 = 1.0F;
                } else {
                   var32 = -1.0F;
                }

                var56.x = this.mobX + this.mobR * var32 * (this.rnd.nextFloat() * 0.6F + 0.4F);
                var56.y = this.groundY - this.pad * 0.5F;
                float var50 = this.rnd.nextFloat();
                var56.vx = (float)this.W / 1080.0F * var32 * (var50 * 400.0F + 200.0F);
                var56.vy = -(this.rnd.nextFloat() * 250.0F + 150.0F) * ((float)this.W / 1080.0F);
                var56.max = this.rnd.nextFloat() * 0.4F + 0.6F;
                var56.size = this.pad * (this.rnd.nextFloat() * 0.6F + 0.5F);
                var56.color = -4675440;
                this.parts.add(var56);
             }
          }
       }

       this.portraitPop = Math.max(0.0F, this.portraitPop - 6.0F * var1);
       this.setMsgT = Math.max(0.0F, this.setMsgT - var1);
       if (this.overlay == 11) {
          this.updateSuper(var1);
       }

       if (this.overlay == 10) {
          var32 = this.relGetT + var1;
          this.relGetT = var32;
          if (var32 > 5.0F && !this.relFly) {
             this.relFly = true;
             this.sfx.play(4, 0.8F);
          }

          if (this.relFly) {
             var32 = this.relFlyT + var1;
             this.relFlyT = var32;
             if (var32 > 0.6F) {
                this.overlay = 0;
                this.burstAt(this.relicBtn.centerX(), this.relicBtn.centerY(), 20, REL_COLOR[this.relGetIdx]);
             }
          }
       }

       this.mobHit = Math.max(0.0F, this.mobHit - 5.0F * var1);
       this.mobSpawn = Math.min(1.0F, this.mobSpawn + var47);
       this.heroCast = Math.max(0.0F, this.heroCast - var47);
       this.shake = Math.max(0.0F, this.shake - 3.0F * var1);
       var32 = this.bannerT;
       if (var32 > 0.0F) {
          this.bannerT = var32 - var1;
       }

       this.stepFx(this.floats, var1, 0.0F);
       this.stepFx(this.parts, var1, (float)this.W / 1080.0F * 900.0F);
       Iterator var22 = this.chunks.iterator();

       while (var22.hasNext()) {
          GameView.Fx var57 = (GameView.Fx)var22.next();
          var57.life += var1;
          var57.x = var57.x + var57.vx * var1;
          var57.y = var57.y + var57.vy * var1;
          var57.vy = var57.vy + (float)this.W / 1080.0F * 2200.0F * var1;
          var47 = var57.y;
          var32 = this.groundY;
          if (var47 > var32) {
             var57.y = var32;
             var57.vy *= -0.32F;
             var57.vx *= 0.6F;
          }

          if (var57.life >= var57.max) {
             var22.remove();
          }
       }

       Iterator var58 = this.bolts.iterator();

       while (var58.hasNext()) {
          GameView.Fx var60 = (GameView.Fx)var58.next();
          var60.life += var1;
          if (var60.life >= var60.max) {
             var58.remove();
          }
       }

       Iterator var59 = this.famShots.iterator();

       while (var59.hasNext()) {
          GameView.Fx var61 = (GameView.Fx)var59.next();
          var61.life += var1;
          if (var61.life >= var61.max) {
             var59.remove();
          }
       }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void updateBeatFight(float var1) {
       this.bfTalkT = Math.max(0.0F, this.bfTalkT - var1);
       this.bfJudgeT = Math.max(0.0F, this.bfJudgeT - var1);
       float var8 = this.bfHurt;
       float var9 = 3.0F * var1;
       this.bfHurt = Math.max(0.0F, var8 - var9);
       this.bfBossHurt = Math.max(0.0F, this.bfBossHurt - var9);

       for (int var10 = 0; var10 < 3; var10++) {
          float[] var21 = this.bfLaneFlash;
          var21[var10] = Math.max(0.0F, var21[var10] - 4.0F * var1);
       }

       long var19 = SystemClock.uptimeMillis();
       int var29;
       if (this.optMusic && this.sfx.playingTrack() != 3 && var19 - this.bfOpenAt <= 4000L) {
          var29 = 0;
       } else {
          var29 = 1;
       }

       if (var29 == 0) {
          this.bfPrevPos = -1.0;
       } else {
          double var2 = this.bfBeats();
          this.bfNow = var2;
          if (this.bfStartBeat < 0.0) {
             var2 = Math.ceil(var2 / 4.0) * 4.0 + 4.0;
             this.bfStartBeat = var2;
             this.bfGenBeat = (int)var2;
          }

          if (this.bfState == 0 && this.bfNow >= this.bfStartBeat) {
             this.bfState = 1;
          }

          if (this.bfState <= 1) {
             var8 = this.bfBossHp;
             if (var8 > 40.0F) {
                var29 = (byte)0;
             } else if (var8 > 20.0F) {
                var29 = (byte)1;
             } else {
                var29 = (byte)2;
             }

             if (var29 != this.bfPhase) {
                this.bfPhase = var29;
                String var39;
                if (var29 == 1) {
                   var39 = "Ускоряемся! Не отставай!";
                } else {
                   var39 = "ДРОП!!! Двумя пальцами слабо?";
                }

                this.bfSay(var39);
                this.shake = Math.max(this.shake, 0.6F);
             }

             var29 = this.bfPhase;
             if (var29 == 0) {
                var2 = 3.0;
             } else if (var29 == 1) {
                var2 = 2.5;
             } else {
                var2 = 2.1;
             }

             while (true) {
                int var15 = this.bfGenBeat;
                if (!((double)var15 <= this.bfNow + 5.0)) {
                   for (GameView.Note var22 : this.notes) {
                      if (var22.state != 0) {
                         var22.hitT += var1;
                      } else if ((this.bfNow - var22.beat) * this.BEAT > 0.18) {
                         var22.state = 2;
                         if (var22.type == 0) {
                            this.bfHeroHp -= 7.0F;
                            this.bfHurt = 1.0F;
                            this.bfCombo = 0;
                            this.bfJudgeSet("ПРОПУСК", -38294);
                            this.sfx.play(8, 0.4F);
                            this.sfx.buzz(40L);
                            if (this.rnd.nextInt(3) == 0) {
                               String[] var43 = BF_TAUNT;
                               this.bfSay(var43[this.rnd.nextInt(var43.length)]);
                            }
                         }
                      }
                   }

                   Iterator var44 = this.notes.iterator();

                   while (var44.hasNext()) {
                      GameView.Note var42 = (GameView.Note)var44.next();
                      if (var42.state != 0 && var42.hitT > 0.5F) {
                         var44.remove();
                      }
                   }

                   if (this.bfHeroHp <= 0.0F && this.bfState == 1) {
                      this.bfState = 3;
                      this.bfSay("Возвращайся, когда разомнёшь пальцы!");
                      this.sfx.play(8, 1.0F);
                      this.notes.clear();
                      this.bfOpenAt = var19;
                   }

                   if (this.bfBossHp <= 0.0F && this.bfState == 1) {
                      this.beatFightWon();
                   }
                   break;
                }

                Random var40 = new Random((long)var15 * 7919L + 13L);
                double var4 = (double)var15;
                double var6 = this.bfStartBeat;
                int var32;
                if (var4 < var6 + 8.0) {
                   var32 = 1;
                } else {
                   var32 = 0;
                }

                if (var32 != 0) {
                   if ((var15 - (int)var6) % 2 == 0) {
                      this.bfAddNote(var4, 0, 3.2, 1);
                   }

                   this.bfGenBeat++;
                } else {
                   for (int var13 = 0; var13 < 2; var13++) {
                      if (var13 != 1 || this.bfPhase != 0) {
                         var32 = (var15 % 16 + 16) % 16 * 2 + var13;
                         byte var18 = BM_KICK[var32];
                         byte var17 = BM_SNARE[var32];
                         byte var16 = BM_HAT[var32];
                         byte var14 = -1;
                         int var34;
                         if (var17 >= 2) {
                            var34 = 1;
                         } else {
                            var34 = -1;
                         }

                         byte var11;
                         label203: {
                            var11 = (byte)var34;
                            if (var18 >= 2) {
                               if (var34 >= 0) {
                                  byte var12 = 0;
                                  var11 = (byte)var34;
                                  var34 = var12;
                                  break label203;
                               }

                               var11 = 0;
                            }

                            var34 = (byte)-1;
                         }

                         byte var38 = var11;
                         if (var16 >= 2) {
                            var38 = var11;
                            label194:
                            if (var11 < 0) {
                               if (var13 != 0) {
                                  var38 = var11;
                                  if (var16 != 3) {
                                     break label194;
                                  }
                               }

                               var38 = 2;
                            }
                         }

                         if (var13 == 1 && this.bfPhase == 1 && Math.max(var18, Math.max(var17, var16)) < 3) {
                            var11 = -1;
                            var34 = (byte)-1;
                         } else {
                            var11 = var38;
                         }

                         if (this.bfPhase < 2) {
                            var34 = var14;
                         } else if (var34 < 0 && var11 >= 0 && var11 != 2 && var16 >= 2 && var40.nextFloat() < 0.5F) {
                            var34 = (byte)2;
                         }

                         var6 = (double)var13 * 0.5 + var4;
                         if (var11 >= 0) {
                            this.bfAddNote(var6, 0, var2, var11);
                         }

                         if (var34 >= 0) {
                            this.bfAddNote(var6, 0, var2, var34);
                         }

                         if (var11 < 0) {
                            var34 = this.bfPhase;
                            if (var34 >= 1) {
                               if (var13 == 0) {
                                  var9 = var40.nextFloat();
                                  if (this.bfPhase == 1) {
                                     var8 = 0.6F;
                                  } else {
                                     var8 = 0.8F;
                                  }

                                  if (!(var9 < var8)) {
                                     continue;
                                  }
                               } else if (var34 != 2 || !(var40.nextFloat() < 0.25F)) {
                                  continue;
                               }

                               this.bfAddNote(var6, 1, var2, var40.nextInt(3));
                            }
                         }
                      }
                   }

                   this.bfGenBeat++;
                }
             }
          }

          this.bfSilent = false;
          this.sfx.setDuck(1.0F);
       }
    }

    void updateLifeStage(boolean z) {
        int i = OV_NONE;
        int i2 = OV_NONE;
        while (true) {
            int[] iArr = LIFE_AT;
            if (i >= iArr.length) {
                break;
            }
            if (this.stage >= iArr[i]) {
                i2 = i;
            }
            i++;
        }
        if (i2 > this.lifeStageIdx) {
            this.lifeStageIdx = i2;
            if (z) {
                showBanner("Новый этап жизни: " + LIFE_NAME[i2] + "!", -5720);
            }
        }
    }

    void updateMini(float f) {
        this.miniT += f;
        this.miniMsgT = Math.max(0.0f, this.miniMsgT - f);
        int i = this.miniPhase;
        if (i == 0) {
            if (this.miniT > 1.4f) {
                this.miniPhase = 1;
                miniRoundStart();
                return;
            }
            return;
        }
        if (i == 3) {
            if (this.miniT > 0.8f) {
                this.miniPhase = 1;
                miniRoundStart();
                return;
            }
            return;
        }
        if (i == 2) {
            if (this.miniT > 4.0f) {
                closeMini();
                return;
            }
            return;
        }
        int i2 = this.miniType;
        if (i2 == 0) {
            float f2 = this.swPos + (this.swDir * this.swSpeed * f);
            this.swPos = f2;
            if (f2 > 1.0f) {
                this.swPos = 1.0f;
                this.swDir = -1.0f;
            }
            if (this.swPos < 0.0f) {
                this.swPos = 0.0f;
                this.swDir = 1.0f;
            }
            if (this.miniT > 6.0f) {
                miniMsg("Слишком долго!", -30070);
                miniResult(false);
                return;
            }
            return;
        }
        if (i2 != 1) {
            float f3 = this.elTime - f;
            this.elTime = f3;
            if (f3 <= 0.0f) {
                miniMsg("Не успел!", -30070);
                miniResult(false);
                return;
            }
            return;
        }
        if (!this.runeShowing) {
            float max = Math.max(0.0f, this.runeLitT - f);
            this.runeLitT = max;
            if (max <= 0.0f) {
                this.runeLit = -1;
            }
            if (this.miniT > 8.0f) {
                miniMsg("Время вышло!", -30070);
                miniResult(false);
                return;
            }
            return;
        }
        float f4 = 0.62f - (this.miniRound * 0.05f);
        float f5 = this.miniT;
        int i3 = (int) (f5 / f4);
        float f6 = f5 - (i3 * f4);
        int i4 = this.runeLen;
        this.runeLit = (i3 >= i4 || f6 >= f4 * 0.58f) ? -1 : this.runeSeq[i3];
        this.runeShowIdx = Math.min(i3 + 1, i4);
        if (i3 >= this.runeLen) {
            this.runeShowing = false;
            this.runeLit = -1;
            this.miniT = 0.0f;
        }
    }

    void updateSuper(float f) {
        float f2 = this.superT;
        float f3 = f + f2;
        this.superT = f3;
        if (this.superType == 0) {
            for (int i = OV_NONE; i < this.superStars; i++) {
                float f4 = (i * 0.25f) + 0.55f;
                if (f2 < f4 && this.superT >= f4) {
                    this.sfx.play(1, 1.0f);
                    this.sfx.buzz(30L);
                    this.shake = Math.max(this.shake, 0.4f);
                    burst(10, -5720);
                }
            }
        } else if (f2 < 0.5f && f3 >= 0.5f) {
            this.sfx.play(5, 1.0f);
        }
        if (!this.superHit && this.superT >= SUPER_IMPACT) {
            this.superHit = true;
            this.shake = 1.0f;
            this.sfx.play(10, 1.0f);
            this.sfx.play(3, 0.9f);
            this.sfx.buzz(250L);
            int i2 = this.superType;
            burst(60, i2 == 0 ? -11430 : i2 == 1 ? -5213953 : EL_COLOR[((int) (this.time * 4.0f)) % 4]);
            burst(30, -1);
            double d = this.monsterHp;
            double d2 = this.superDmg;
            if (d - d2 > 0.0d) {
                this.monsterHp = d - d2;
            } else {
                this.superKill = true;
                this.monsterHp = this.monsterMax * 1.0E-4d;
            }
            this.mobHit = 1.0f;
        }
        if (this.superT >= SUPER_END) {
            this.overlay = OV_NONE;
            if (this.superKill) {
                damage(this.monsterHp + 1.0d, false);
            }
        }
    }

    double wallet(int i) {
        return UP_MANA[i] ? this.mana : this.gold;
    }
}
