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
    long pausedAt;
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
    float wsX;
    float wsY;
    long wsUntil;
    long wsNext;
    long wsLife;
    int wsStreak;
    int wsBest;
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
        float fxBorn;
        int fxKind;
        float fxRot;

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
        if (i == -1 || i == -42406) {
            fxTapImpact(i == -42406);
        }
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

    // ===== fx: combat FX & feedback (cached paints, helpers) =====
    static Paint fxMk(boolean stroke) {
        Paint q = new Paint(1);
        if (stroke) {
            q.setStyle(Paint.Style.STROKE);
            q.setStrokeCap(Paint.Cap.ROUND);
            q.setStrokeJoin(Paint.Join.ROUND);
        }
        return q;
    }

    static Paint fxMkBmp() {
        Paint q = new Paint();
        q.setFilterBitmap(false);
        q.setDither(false);
        q.setAntiAlias(false);
        return q;
    }

    final Paint fxP = fxMk(false);
    final Paint fxS = fxMk(true);
    final Paint fxTxt = fxMk(false);
    final Paint fxTxtO = fxMk(true);
    final Paint fxBan = fxMk(false);
    final Paint fxBmp = fxMkBmp();
    final LightingColorFilter fxCoinBack = new LightingColorFilter(0xFFB08A60, 0x00100800);
    final RectF fxR = new RectF();
    final Path fxPath = new Path();
    final Random fxRnd = new Random();
    final ArrayList<Fx> fxSparks = new ArrayList<>();
    android.graphics.LinearGradient fxBanSh;
    float fxBanKeyT = -1.0f;
    float fxBanKeyB = -1.0f;
    String fxBanStr;
    float fxBanMax;
    float fxBanLast;

    static float fxClamp(float v) {
        return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
    }

    static float fxOut(float t) {
        float u = 1.0f - fxClamp(t);
        return 1.0f - (u * u * u);
    }

    static float fxBack(float t) {
        float u = fxClamp(t) - 1.0f;
        return 1.0f + (3.2f * u * u * u) + (2.2f * u * u);
    }

    static int fxShade(int c, float k) {
        int r = Math.min(255, (int) (((c >> 16) & 255) * k));
        int g = Math.min(255, (int) (((c >> 8) & 255) * k));
        int b = Math.min(255, (int) ((c & 255) * k));
        return (-16777216) | (r << 16) | (g << 8) | b;
    }

    static int fxMix(int c, int d, float t) {
        float s = fxClamp(t);
        int r = (int) ((((c >> 16) & 255) * (1.0f - s)) + (((d >> 16) & 255) * s));
        int g = (int) ((((c >> 8) & 255) * (1.0f - s)) + (((d >> 8) & 255) * s));
        int b = (int) (((c & 255) * (1.0f - s)) + ((d & 255) * s));
        return (-16777216) | (r << 16) | (g << 8) | b;
    }

    // kind 0 = glowing streak spark, 1 = shockwave ring, 2 = tap impact, 3 = crit impact
    void fxAddSpark(int kind, float x, float y, float vx, float vy, float max, float size, int color, float delay) {
        Fx fx = new Fx();
        fx.fxKind = kind;
        fx.x = x;
        fx.y = y;
        fx.vx = vx;
        fx.vy = vy;
        fx.max = max;
        fx.size = size;
        fx.color = color;
        fx.fxBorn = this.time + delay;
        fx.fxRot = fxRnd.nextFloat() * 6.2831855f;
        this.fxSparks.add(fx);
        while (this.fxSparks.size() > 140) {
            this.fxSparks.remove(OV_NONE);
        }
    }

    void fxStreaks(float x, float y, int n, int color, float speed, float delay) {
        float sc = this.W / 1080.0f;
        for (int k = OV_NONE; k < n; k++) {
            double a = fxRnd.nextDouble() * 6.283185307179586d;
            float sp = ((fxRnd.nextFloat() * 0.6f) + 0.5f) * speed * sc;
            int col = k % 3 == 0 ? -2338 : color;
            fxAddSpark(OV_NONE, x, y, ((float) Math.cos(a)) * sp, (((float) Math.sin(a)) * sp) - (220.0f * sc), (fxRnd.nextFloat() * 0.2f) + 0.28f, this.hu * ((fxRnd.nextFloat() * 0.5f) + 0.55f), col, delay);
        }
    }

    void fxTapImpact(boolean crit) {
        float jx = (fxRnd.nextFloat() - 0.5f) * this.mobR * 0.4f;
        float jy = (fxRnd.nextFloat() - 0.5f) * this.mobR * 0.4f;
        float x = this.mobX + jx;
        float y = this.mobY + jy;
        float r = this.pad * (crit ? 4.6f : 2.6f);
        fxAddSpark(crit ? 3 : 2, x, y, 0.0f, 0.0f, crit ? 0.42f : 0.26f, r, crit ? -11430 : -5720, 0.2f);
        fxStreaks(x, y, crit ? 9 : 4, crit ? -26051 : -5720, crit ? 1300.0f : 850.0f, 0.2f);
    }

    void fxDrawSparks(Canvas canvas) {
        if (this.fxSparks.isEmpty()) {
            return;
        }
        float now = this.time;
        float g = (this.W / 1080.0f) * 1500.0f;
        for (int k = this.fxSparks.size() - 1; k >= 0; k--) {
            Fx s = this.fxSparks.get(k);
            float t = now - s.fxBorn;
            if (t >= s.max || t < -2.0f || s.max <= 0.0f) {
                this.fxSparks.remove(k);
                continue;
            }
            if (t < 0.0f) {
                continue;
            }
            float u = t / s.max;
            float fade = 1.0f - u;
            if (s.fxKind == 0) {
                float e = (float) Math.exp(-3.0f * t);
                float d = (1.0f - e) / 3.0f;
                float px = s.x + (s.vx * d);
                float py = s.y + (s.vy * d) + (0.5f * g * t * t);
                float cvx = s.vx * e;
                float cvy = (s.vy * e) + (g * t);
                float tx = px - (cvx * 0.04f);
                float ty = py - (cvy * 0.04f);
                this.fxS.setColor(s.color);
                this.fxS.setAlpha((int) (80.0f * fade));
                this.fxS.setStrokeWidth(s.size * 2.6f);
                canvas.drawLine(tx, ty, px, py, this.fxS);
                this.fxS.setColor(fxMix(s.color, -1, 0.55f));
                this.fxS.setAlpha((int) (240.0f * fade));
                this.fxS.setStrokeWidth(s.size * fade + 1.0f);
                canvas.drawLine(tx, ty, px, py, this.fxS);
            } else if (s.fxKind == 1) {
                float e2 = fxOut(u);
                float rr = s.size * ((0.85f * e2) + 0.2f);
                if (u < 0.3f) {
                    float fl = 1.0f - (u / 0.3f);
                    this.fxP.setColor(s.color);
                    this.fxP.setAlpha((int) (90.0f * fl));
                    canvas.drawCircle(s.x, s.y, rr * 0.75f, this.fxP);
                    this.fxP.setColor(-1);
                    this.fxP.setAlpha((int) (200.0f * fl));
                    canvas.drawCircle(s.x, s.y, rr * 0.32f, this.fxP);
                }
                this.fxS.setColor(s.color);
                this.fxS.setAlpha((int) (110.0f * fade));
                this.fxS.setStrokeWidth((this.pad * 1.1f * fade) + 1.0f);
                canvas.drawCircle(s.x, s.y, rr, this.fxS);
                this.fxS.setColor(fxMix(s.color, -1, 0.6f));
                this.fxS.setAlpha((int) (220.0f * fade));
                this.fxS.setStrokeWidth((this.pad * 0.35f * fade) + 1.0f);
                canvas.drawCircle(s.x, s.y, rr, this.fxS);
            } else {
                boolean crit = s.fxKind == 3;
                float e3 = fxOut(u);
                float rr2 = s.size * ((0.9f * e3) + 0.15f);
                if (u < 0.35f) {
                    float fl2 = 1.0f - (u / 0.35f);
                    this.fxP.setColor(s.color);
                    this.fxP.setAlpha((int) (110.0f * fl2));
                    canvas.drawCircle(s.x, s.y, s.size * 0.55f * ((0.5f * e3) + 0.5f), this.fxP);
                    this.fxP.setColor(-1);
                    this.fxP.setAlpha((int) (235.0f * fl2));
                    canvas.drawCircle(s.x, s.y, s.size * 0.24f * ((0.6f * e3) + 0.4f), this.fxP);
                }
                int spikes = crit ? 8 : 6;
                float step = 6.2831855f / spikes;
                this.fxS.setColor(fxMix(s.color, -1, 0.5f));
                this.fxS.setAlpha((int) (230.0f * fade));
                this.fxS.setStrokeWidth((this.hu * (crit ? 1.3f : 0.9f) * fade) + 1.0f);
                for (int q = OV_NONE; q < spikes; q++) {
                    float a = s.fxRot + (q * step);
                    float ln = (q % 2 == 0 ? 1.0f : 0.62f);
                    float ca = (float) Math.cos(a);
                    float sa = (float) Math.sin(a);
                    float r0 = rr2 * 0.55f;
                    float r1 = rr2 * ((0.55f * ln) + 0.6f);
                    canvas.drawLine(s.x + (ca * r0), s.y + (sa * r0), s.x + (ca * r1), s.y + (sa * r1), this.fxS);
                }
                this.fxS.setColor(s.color);
                this.fxS.setAlpha((int) (200.0f * fade));
                this.fxS.setStrokeWidth((this.pad * (crit ? 0.6f : 0.4f) * fade) + 1.0f);
                canvas.drawCircle(s.x, s.y, rr2, this.fxS);
                if (crit && u > 0.15f) {
                    float u2 = (u - 0.15f) / 0.85f;
                    this.fxS.setColor(-42406);
                    this.fxS.setAlpha((int) (160.0f * (1.0f - u2)));
                    this.fxS.setStrokeWidth((this.pad * 0.3f * (1.0f - u2)) + 1.0f);
                    canvas.drawCircle(s.x, s.y, s.size * ((0.9f * fxOut(u2)) + 0.2f), this.fxS);
                }
            }
        }
    }

    void fxDrawBanner(Canvas canvas, float bt) {
        if (this.banner != this.fxBanStr || bt > this.fxBanLast + 0.05f) {
            this.fxBanStr = this.banner;
            this.fxBanMax = bt;
        }
        this.fxBanLast = bt;
        float el = Math.max(0.0f, this.fxBanMax - bt);
        float vis = Math.min(1.0f, bt) * Math.min(1.0f, el / 0.1f);
        if (vis <= 0.0f) {
            return;
        }
        float in = Math.min(1.0f, el / 0.42f);
        float pop = fxBack(in);
        float pd = this.pad;
        float bl = pd * 2.0f;
        float btp = this.battleT + pd;
        float br = this.W - (pd * 2.0f);
        float bb = this.battleT + (pd * 5.5f);
        float cx = this.W / 2.0f;
        float cy = (btp + bb) / 2.0f;
        int a = (int) (255.0f * vis);
        int bc = this.bannerColor;
        float pulse = (float) ((Math.sin(this.time * 4.0f) * 0.5d) + 0.5d);
        canvas.save();
        canvas.translate(0.0f, (1.0f - fxOut(in)) * (-pd) * 3.5f);
        canvas.scale((0.25f * pop) + 0.75f, (0.5f * pop) + 0.5f, cx, cy);
        // drop shadow
        this.fxP.setColor(-16777216);
        this.fxP.setAlpha((int) (110.0f * vis));
        this.fxR.set(bl, btp + (pd * 0.5f), br, bb + (pd * 0.5f));
        canvas.drawRoundRect(this.fxR, pd, pd, this.fxP);
        // outer glow in banner colour
        float gl = pd * ((0.25f * pulse) + 0.3f);
        this.fxP.setColor(bc);
        this.fxP.setAlpha((int) (((40.0f * pulse) + 35.0f) * vis));
        this.fxR.set(bl - gl, btp - gl, br + gl, bb + gl);
        canvas.drawRoundRect(this.fxR, pd + gl, pd + gl, this.fxP);
        // glossy gradient panel
        if (this.fxBanSh == null || this.fxBanKeyT != btp || this.fxBanKeyB != bb) {
            this.fxBanKeyT = btp;
            this.fxBanKeyB = bb;
            this.fxBanSh = new android.graphics.LinearGradient(0.0f, btp, 0.0f, bb, new int[]{-12961180, -14674632, -15594458}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
        }
        this.fxBan.setShader(this.fxBanSh);
        this.fxBan.setAlpha((int) (235.0f * vis));
        this.fxR.set(bl, btp, br, bb);
        canvas.drawRoundRect(this.fxR, pd, pd, this.fxBan);
        // tinted wash + gloss on upper half
        this.fxP.setColor(bc);
        this.fxP.setAlpha((int) (26.0f * vis));
        canvas.drawRoundRect(this.fxR, pd, pd, this.fxP);
        this.fxP.setColor(-1);
        this.fxP.setAlpha((int) (26.0f * vis));
        this.fxR.set(bl + (pd * 0.45f), btp + (pd * 0.35f), br - (pd * 0.45f), cy - (pd * 0.1f));
        canvas.drawRoundRect(this.fxR, pd * 0.6f, pd * 0.6f, this.fxP);
        // shine sweep (on entry, then every few seconds)
        float sh = el < 0.9f ? el / 0.9f : ((el - 0.9f) % 3.2f) / 0.9f;
        if (sh < 1.0f) {
            float sx = bl + (((br - bl) + (pd * 6.0f)) * sh) - (pd * 3.0f);
            canvas.save();
            canvas.clipRect(bl + (pd * 0.3f), btp + (pd * 0.2f), br - (pd * 0.3f), bb - (pd * 0.2f));
            this.fxPath.reset();
            this.fxPath.moveTo(sx, btp);
            this.fxPath.lineTo((pd * 1.4f) + sx, btp);
            this.fxPath.lineTo((pd * 1.4f) + sx - (pd * 2.0f), bb);
            this.fxPath.lineTo(sx - (pd * 2.0f), bb);
            this.fxPath.close();
            this.fxP.setColor(-1);
            this.fxP.setAlpha((int) (48.0f * vis * (1.0f - Math.abs((sh * 2.0f) - 1.0f))));
            canvas.drawPath(this.fxPath, this.fxP);
            canvas.restore();
        }
        // double border
        this.fxS.setStrokeWidth(pd * 0.32f);
        this.fxS.setColor(bc);
        this.fxS.setAlpha((int) (((60.0f * pulse) + 195.0f) * vis));
        this.fxR.set(bl + (pd * 0.16f), btp + (pd * 0.16f), br - (pd * 0.16f), bb - (pd * 0.16f));
        canvas.drawRoundRect(this.fxR, pd * 0.85f, pd * 0.85f, this.fxS);
        this.fxS.setStrokeWidth(pd * 0.12f);
        this.fxS.setColor(-16777216);
        this.fxS.setAlpha((int) (120.0f * vis));
        this.fxR.set(bl + (pd * 0.45f), btp + (pd * 0.45f), br - (pd * 0.45f), bb - (pd * 0.45f));
        canvas.drawRoundRect(this.fxR, pd * 0.6f, pd * 0.6f, this.fxS);
        // glowing side gems
        for (int side = OV_NONE; side < 2; side++) {
            float gx = side == 0 ? bl : br;
            this.fxP.setColor(bc);
            this.fxP.setAlpha((int) (((50.0f * pulse) + 40.0f) * vis));
            canvas.drawCircle(gx, cy, pd * ((0.4f * pulse) + 1.1f), this.fxP);
            canvas.save();
            canvas.rotate(45.0f, gx, cy);
            this.fxP.setColor(fxShade(bc, 0.55f));
            this.fxP.setAlpha(a);
            float gs = pd * 0.7f;
            canvas.drawRect(gx - gs, cy - gs, gx + gs, cy + gs, this.fxP);
            this.fxP.setColor(bc);
            this.fxP.setAlpha(a);
            float gs2 = pd * 0.45f;
            canvas.drawRect(gx - gs2, cy - gs2, gx + gs2, cy + gs2, this.fxP);
            this.fxP.setColor(-1);
            this.fxP.setAlpha((int) (200.0f * vis));
            canvas.drawRect(gx - gs2, cy - gs2, gx - (gs2 * 0.2f), cy - (gs2 * 0.2f), this.fxP);
            canvas.restore();
        }
        // text with soft drop shadow
        this.tp.setColor(bc);
        this.tp.setAlpha(a);
        this.tp.setTextSize(pd * 2.0f);
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setFakeBoldText(true);
        this.tp.setShadowLayer(pd * 0.3f, 0.0f, pd * 0.22f, ((int) (220.0f * vis)) << 24);
        String str = this.banner;
        drawFit(canvas, str, cx, this.battleT + (4.0f * pd), this.W - (pd * 6.0f));
        this.tp.clearShadowLayer();
        this.tp.setAlpha(255);
        this.tp.setFakeBoldText(false);
        canvas.restore();
    }

    void fxFloatText(Canvas canvas, String s, float x, float y, int band) {
        // band: 0 = solid, 1 = crit (white/yellow/orange), 2 = gold
        canvas.drawText(s, x, y, this.fxTxtO);
        if (band == 0) {
            canvas.drawText(s, x, y, this.fxTxt);
            return;
        }
        float ts = this.fxTxt.getTextSize();
        int al = this.fxTxt.getAlpha();
        float top = y - (ts * 0.78f);
        this.fxTxt.setColor(band == 1 ? -42406 : -3104256);
        this.fxTxt.setAlpha(al);
        canvas.drawText(s, x, y, this.fxTxt);
        canvas.save();
        canvas.clipRect(x - (this.W * 2.0f), top - ts, x + (this.W * 2.0f), top + (ts * 0.5f));
        this.fxTxt.setColor(band == 1 ? -16310 : -11430);
        this.fxTxt.setAlpha(al);
        canvas.drawText(s, x, y, this.fxTxt);
        canvas.restore();
        canvas.save();
        canvas.clipRect(x - (this.W * 2.0f), top - ts, x + (this.W * 2.0f), top + (ts * 0.2f));
        this.fxTxt.setColor(band == 1 ? -2338 : -4952);
        this.fxTxt.setAlpha(al);
        canvas.drawText(s, x, y, this.fxTxt);
        canvas.restore();
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
        if (i > 0) {
            fxAddSpark(1, f, f2, 0.0f, 0.0f, (Math.min(i, 60) * 0.004f) + 0.3f, this.pad * ((Math.min(i, 60) * 0.11f) + 2.6f), i2, 0.0f);
            fxStreaks(f, f2, Math.min(12, (i / 4) + 2), i2, 1050.0f, 0.0f);
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
        if (j > j2) {
            this.streak = j2 == j - 1 ? this.streak + 1 : 1;
            this.lastDay = j;
            this.dailyPending = true;
        }
        if (this.questDay < j) {
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
            RectF rectF = this.r;
            float f9 = this.pad;
            menuCard(canvas, rectF, f9, z ? -12963304 : -14805453, z ? -11430 : -12963760, false, 6, true);
            if (z) {
                // gold rim, periodic shine sweep and a twinkling star
                float pu = menuPulse(2.2f, i * 0.7f);
                this.menuLine.setStyle(Paint.Style.STROKE);
                this.menuLine.setStrokeWidth(Math.max(1.0f, f9 * 0.18f));
                this.menuLine.setColor(menuAlpha(-11430, (int) (110.0f + 110.0f * pu)));
                canvas.drawRoundRect(rectF, f9, f9, this.menuLine);
                menuShine(canvas, rectF, f9, ((this.time * 0.3f) + (i * 0.173f)) % 2.6f, 120);
                float tw = (float) Math.sin((this.time * 2.6f) + (i * 1.9f));
                if (tw > 0.0f) {
                    menuSpark(canvas, rectF.right - (f9 * 1.3f), rectF.top + (f9 * 1.1f), Math.max(1.0f, f9 * 0.2f), -11430, (int) (255.0f * tw));
                }
            } else {
                // locked: dimmed with a faint diagonal hatch
                this.menuPath.reset();
                this.menuPath.addRoundRect(rectF, f9, f9, Path.Direction.CW);
                canvas.save();
                canvas.clipPath(this.menuPath);
                this.menuLine.setStyle(Paint.Style.STROKE);
                this.menuLine.setStrokeWidth(Math.max(1.0f, f9 * 0.22f));
                this.menuLine.setColor(0x14FFFFFF);
                float hh = rectF.height();
                for (float hx = rectF.left - hh; hx < rectF.right; hx += f9 * 2.0f) {
                    canvas.drawLine(hx, rectF.bottom, hx + hh, rectF.top, this.menuLine);
                }
                canvas.restore();
            }
            this.tp.setTextAlign(Paint.Align.LEFT);
            this.tp.setTextSize(Math.min(this.pad * 1.7f, 0.3f * f4));
            this.tp.setFakeBoldText(true);
            this.tp.setColor(z ? -11430 : -7569238);
            if (z) {
                this.tp.setShadowLayer(1.0f, 0.0f, this.pad * 0.14f, 0xC0000000);
            }
            drawFit(canvas, (z ? "🏆 " : "🔒 ") + strArr[i], this.r.left + this.pad, this.r.top + (0.42f * f4), this.r.width() - (this.pad * 2.0f));
            this.tp.clearShadowLayer();
            this.tp.setFakeBoldText(false);
            this.tp.setTextSize(Math.min(this.pad * 1.3f, 0.24f * f4));
            this.tp.setColor(z ? -2570096 : -9543024);
            drawFit(canvas, ACH_DESC[i] + " · +5% урона", this.r.left + this.pad, this.r.top + (0.78f * f4), this.r.width() - (this.pad * 2.0f));
            i++;
        }
    }

    // ===== hud: HUD & battle-button rendering helpers (visual only) =====
    final Paint hudFill = new Paint(1);
    final Paint hudShadeP = new Paint(1);
    final Paint hudLine = new Paint(1);
    final RectF hudR = new RectF();
    final RectF hudR2 = new RectF();
    final RectF hudBtnR = new RectF();
    final Path hudPath = new Path();
    final Path hudClip = new Path();
    final Shader[] hudShadeC = new Shader[12];
    final float[] hudShadeK = new float[24];
    int hudShadeN;
    Shader hudHeadSh;
    Shader hudHeadDropSh;
    float hudHeadKey = -1.0f;
    float hudHeadKeyPad = -1.0f;
    int hudHeadBiome = -1;
    final Shader[] hudGlowSh = new Shader[3];
    float hudGlowKey = -1.0f;
    Shader hudGaugeSh;
    float hudGaugeK0 = -1.0f;
    float hudGaugeK1 = -1.0f;
    Shader hudBandSh;
    float hudBandK0 = -1.0f;
    float hudBandK1 = -1.0f;
    boolean hudBandTower;
    float hudTabX = -1.0f;
    float hudTabLast = -1.0f;
    double hudLastSouls = -1.0d;
    float hudSoulPulse;
    float hudPulseLast = -1.0f;

    static int hudMix(int i, int i2, float f) {
        float f2 = f < 0.0f ? 0.0f : f > 1.0f ? 1.0f : f;
        int a = (int) (((i >>> 24) & 255) + ((((i2 >>> 24) & 255) - ((i >>> 24) & 255)) * f2));
        int r = (int) (((i >> 16) & 255) + ((((i2 >> 16) & 255) - ((i >> 16) & 255)) * f2));
        int g = (int) (((i >> 8) & 255) + ((((i2 >> 8) & 255) - ((i >> 8) & 255)) * f2));
        int b = (int) ((i & 255) + (((i2 & 255) - (i & 255)) * f2));
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    static int hudBiomeTint(int i) {
        return i == 2 ? -38342 : i == 1 ? -7680769 : -9844646;
    }

    /** Cached vertical bevel overlay (light top, dark bottom) for a given vertical span. */
    Shader hudShade(float f, float f2) {
        for (int i = 0; i < this.hudShadeC.length; i++) {
            if (this.hudShadeC[i] != null && this.hudShadeK[i * 2] == f && this.hudShadeK[(i * 2) + 1] == f2) {
                return this.hudShadeC[i];
            }
        }
        int i2 = this.hudShadeN;
        this.hudShadeN = (i2 + 1) % this.hudShadeC.length;
        float f3 = f2 <= f ? f + 1.0f : f2;
        this.hudShadeC[i2] = new android.graphics.LinearGradient(0.0f, f, 0.0f, f3, new int[]{1728053247, 335544319, 0, 1291845632}, new float[]{0.0f, 0.42f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
        this.hudShadeK[i2 * 2] = f;
        this.hudShadeK[(i2 * 2) + 1] = f2;
        return this.hudShadeC[i2];
    }

    /** Beveled pixel panel: two-step drop shadow, body, gradient bevel, light top lip, dark bottom lip, dark outline. */
    void hudPanel(Canvas canvas, RectF rectF, float f, int i) {
        if (rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
            return;
        }
        int alpha = Color.alpha(i);
        float f2 = this.pad;
        this.hudFill.setShader(null);
        this.hudFill.setStyle(Paint.Style.FILL);
        this.hudFill.setColor(-16777216);
        this.hudFill.setAlpha((alpha * 55) / 255);
        this.hudR.set(rectF.left + (0.15f * f2), rectF.top + (0.6f * f2), rectF.right + (0.15f * f2), rectF.bottom + (0.75f * f2));
        float f3 = f * 1.15f;
        canvas.drawRoundRect(this.hudR, f3, f3, this.hudFill);
        this.hudFill.setAlpha((alpha * 120) / 255);
        this.hudR.set(rectF.left, rectF.top + (0.3f * f2), rectF.right, rectF.bottom + (0.35f * f2));
        canvas.drawRoundRect(this.hudR, f, f, this.hudFill);
        this.hudFill.setColor(i);
        canvas.drawRoundRect(rectF, f, f, this.hudFill);
        this.hudShadeP.setShader(hudShade(rectF.top, rectF.bottom));
        this.hudShadeP.setAlpha(alpha);
        canvas.drawRoundRect(rectF, f, f, this.hudShadeP);
        float f4 = 0.75f * f;
        this.hudFill.setColor(-1);
        this.hudFill.setAlpha((alpha * 85) / 255);
        this.hudR.set(rectF.left + f4, rectF.top + (0.16f * f2), rectF.right - f4, rectF.top + (0.4f * f2));
        canvas.drawRect(this.hudR, this.hudFill);
        this.hudFill.setColor(-16777216);
        this.hudFill.setAlpha((alpha * 75) / 255);
        this.hudR.set(rectF.left + f4, rectF.bottom - (0.42f * f2), rectF.right - f4, rectF.bottom - (0.16f * f2));
        canvas.drawRect(this.hudR, this.hudFill);
        this.hudLine.setStyle(Paint.Style.STROKE);
        this.hudLine.setStrokeWidth(Math.max(1.0f, 0.2f * f2));
        this.hudLine.setColor(hudMix(i, -16777216, 0.6f));
        this.hudLine.setAlpha(Math.min(255, alpha + 30));
        canvas.drawRoundRect(rectF, f, f, this.hudLine);
    }

    /** Soft stepped outer glow around a rect (strength 0..1). */
    void hudGlow(Canvas canvas, RectF rectF, float f, int i, float f2) {
        if (f2 <= 0.01f) {
            return;
        }
        float f3 = this.pad;
        this.hudLine.setStyle(Paint.Style.STROKE);
        this.hudLine.setStrokeWidth(0.5f * f3);
        for (int i2 = 0; i2 < 3; i2++) {
            float f4 = ((i2 * 0.5f) + 0.3f) * f3;
            this.hudLine.setColor(i);
            this.hudLine.setAlpha((int) (Math.min(1.0f, f2) * (150 - (i2 * 50))));
            this.hudR2.set(rectF.left - f4, rectF.top - f4, rectF.right + f4, rectF.bottom + f4);
            float f5 = f + f4;
            canvas.drawRoundRect(this.hudR2, f5, f5, this.hudLine);
        }
    }

    /** Expanding fading ring (phase wraps 0..1). */
    void hudPing(Canvas canvas, RectF rectF, float f, int i, float f2) {
        float f3 = f2 - ((float) Math.floor(f2));
        float f4 = this.pad * ((1.8f * f3) + 0.2f);
        this.hudLine.setStyle(Paint.Style.STROKE);
        this.hudLine.setStrokeWidth((this.pad * 0.35f * (1.0f - f3)) + 1.0f);
        this.hudLine.setColor(i);
        this.hudLine.setAlpha((int) ((1.0f - f3) * 170.0f));
        this.hudR2.set(rectF.left - f4, rectF.top - f4, rectF.right + f4, rectF.bottom + f4);
        float f5 = f + f4;
        canvas.drawRoundRect(this.hudR2, f5, f5, this.hudLine);
    }

    /** Diagonal glint sweeping across a rounded rect; phase outside 0..1 draws nothing. */
    void hudShine(Canvas canvas, RectF rectF, float f, float f2, int i) {
        if (f2 < 0.0f || f2 > 1.0f || rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
            return;
        }
        float height = rectF.height();
        float max = Math.max(this.pad * 1.4f, rectF.width() * 0.1f);
        float f3 = (rectF.left - height) - max;
        float f4 = f3 + ((((rectF.right + max) - f3) + (height * 0.6f)) * f2);
        canvas.save();
        this.hudClip.reset();
        this.hudClip.addRoundRect(rectF, f, f, Path.Direction.CW);
        canvas.clipPath(this.hudClip);
        this.hudFill.setShader(null);
        this.hudFill.setStyle(Paint.Style.FILL);
        this.hudFill.setColor(-1);
        this.hudFill.setAlpha(i);
        this.hudPath.reset();
        this.hudPath.moveTo(f4, rectF.bottom);
        this.hudPath.lineTo((0.6f * height) + f4, rectF.top);
        this.hudPath.lineTo((0.6f * height) + f4 + max, rectF.top);
        this.hudPath.lineTo(f4 + max, rectF.bottom);
        this.hudPath.close();
        canvas.drawPath(this.hudPath, this.hudFill);
        float f5 = (1.6f * max) + f4;
        float f6 = 0.3f * max;
        this.hudFill.setAlpha(i / 2);
        this.hudPath.reset();
        this.hudPath.moveTo(f5, rectF.bottom);
        this.hudPath.lineTo((0.6f * height) + f5, rectF.top);
        this.hudPath.lineTo((0.6f * height) + f5 + f6, rectF.top);
        this.hudPath.lineTo(f5 + f6, rectF.bottom);
        this.hudPath.close();
        canvas.drawPath(this.hudPath, this.hudFill);
        canvas.restore();
    }

    /** Text with a chunky pixel drop shadow, using tp's current size/align/typeface. */
    void hudText(Canvas canvas, String str, float f, float f2, int i, int i2) {
        float max = Math.max(1.0f, this.pad * 0.2f);
        this.tp.setColor(-15791588);
        this.tp.setAlpha((i2 * 200) / 255);
        canvas.drawText(str, f + max, f2 + max, this.tp);
        this.tp.setColor(i);
        this.tp.setAlpha(i2);
        canvas.drawText(str, f, f2, this.tp);
    }

    /** drawFit with a pixel drop shadow. */
    void hudFitText(Canvas canvas, String str, float f, float f2, float f3, int i, int i2) {
        float max = Math.max(1.0f, this.pad * 0.2f);
        this.tp.setColor(-15791588);
        this.tp.setAlpha((i2 * 200) / 255);
        drawFit(canvas, str, f + max, f2 + max, f3);
        this.tp.setColor(i);
        this.tp.setAlpha(i2);
        drawFit(canvas, str, f, f2, f3);
    }

    /** Tiny 4-point pixel sparkle. */
    void hudSparkle(Canvas canvas, float f, float f2, float f3, int i, int i2) {
        if (f3 <= 0.0f || i2 <= 0) {
            return;
        }
        this.hudFill.setShader(null);
        this.hudFill.setStyle(Paint.Style.FILL);
        this.hudFill.setColor(i);
        this.hudFill.setAlpha(Math.min(255, i2));
        float f4 = f3 * 0.34f;
        canvas.drawRect(f - f3, f2 - f4, f + f3, f2 + f4, this.hudFill);
        canvas.drawRect(f - f4, f2 - f3, f + f4, f2 + f3, this.hudFill);
    }

    /** Radial icon glow (cached shader per resource, keyed on pad). which: 0 gold, 1 mana, 2 souls. */
    void hudIconGlow(Canvas canvas, int i, float f, float f2, float f3, float f4) {
        if (f4 <= 0.01f || f3 <= 0.0f || i < 0 || i > 2) {
            return;
        }
        float max = Math.max(1.0f, this.pad * 3.0f);
        if (this.hudGlowKey != max) {
            this.hudGlowSh[0] = null;
            this.hudGlowSh[1] = null;
            this.hudGlowSh[2] = null;
            this.hudGlowKey = max;
        }
        if (this.hudGlowSh[i] == null) {
            int i2 = i == 0 ? -10944 : i == 1 ? -9077505 : -4243201;
            this.hudGlowSh[i] = new RadialGradient(0.0f, 0.0f, max, new int[]{i2, (16777215 & i2) | 1711276032, 16777215 & i2}, new float[]{0.0f, 0.45f, 1.0f}, Shader.TileMode.CLAMP);
        }
        canvas.save();
        canvas.translate(f, f2);
        float f5 = f3 / max;
        canvas.scale(f5, f5);
        this.hudFill.setStyle(Paint.Style.FILL);
        this.hudFill.setShader(this.hudGlowSh[i]);
        this.hudFill.setColor(-1);
        this.hudFill.setAlpha((int) (Math.min(1.0f, f4) * 255.0f));
        canvas.drawCircle(0.0f, 0.0f, max, this.hudFill);
        this.hudFill.setShader(null);
        canvas.restore();
    }

    /** Dark inset chip behind a resource counter. */
    void hudChip(Canvas canvas, float f, float f2, float f3, float f4, int i) {
        if (f3 - f < this.pad || f4 <= f2) {
            return;
        }
        float f5 = (f4 - f2) * 0.5f;
        this.hudFill.setShader(null);
        this.hudFill.setStyle(Paint.Style.FILL);
        this.hudFill.setColor(-1442638576);
        this.hudR.set(f, f2, f3, f4);
        canvas.drawRoundRect(this.hudR, f5, f5, this.hudFill);
        this.hudFill.setColor(1426063360);
        this.hudR.set((f5 * 0.5f) + f, f2 + (this.pad * 0.12f), f3 - (f5 * 0.5f), f2 + (this.pad * 0.42f));
        canvas.drawRect(this.hudR, this.hudFill);
        this.hudFill.setColor(-1);
        this.hudFill.setAlpha(26);
        this.hudR.set((f5 * 0.6f) + f, f4 - (this.pad * 0.3f), f3 - (f5 * 0.6f), f4 - (this.pad * 0.1f));
        canvas.drawRect(this.hudR, this.hudFill);
        this.hudR.set(f, f2, f3, f4);
        this.hudLine.setStyle(Paint.Style.STROKE);
        this.hudLine.setStrokeWidth(Math.max(1.0f, this.pad * 0.16f));
        this.hudLine.setColor(i);
        this.hudLine.setAlpha(110);
        canvas.drawRoundRect(this.hudR, f5, f5, this.hudLine);
    }

    void drawActions(Canvas canvas, long j) {
        float f = this.W / 3.0f;
        float f2 = this.pad * 0.6f;
        float f2r = this.pad * IMPACT;
        boolean z = j >= this.meteorReadyAt;
        double meteorCost = meteorCost();
        boolean z2 = z && this.mana >= meteorCost;
        // --- Meteor ---
        this.r.set(this.pad * 1.5f, this.actT, f - f2, this.actB);
        if (z2) {
            float sinR = (float) ((Math.sin(this.time * 4.0f) * 0.5d) + 0.5d);
            hudGlow(canvas, this.r, f2r, -30150, (0.45f * sinR) + 0.35f);
            hudPing(canvas, this.r, f2r, -21952, this.time * 0.8f);
        }
        hudPanel(canvas, this.r, f2r, z2 ? -4700630 : z ? -11916230 : -12964796);
        if (z2) {
            // rising embers inside the ready button
            for (int k = 0; k < 7; k++) {
                float ph = ((this.time * 0.55f) + (k * 0.37f)) % 1.0f;
                float ex = this.r.left + (this.r.width() * (((k * 0.29f) + 0.08f) % 0.9f + 0.05f)) + ((float) Math.sin((this.time * 3.0f) + k) * this.pad * 0.4f);
                float ey = this.r.bottom - (this.pad * 0.6f) - (ph * (this.r.height() - (this.pad * 1.2f)));
                this.hudFill.setShader(null);
                this.hudFill.setStyle(Paint.Style.FILL);
                this.hudFill.setColor(k % 2 == 0 ? -10944 : -30150);
                this.hudFill.setAlpha((int) ((1.0f - ph) * 200.0f));
                float es = this.pad * (k % 3 == 0 ? 0.4f : 0.28f);
                canvas.drawRect(ex - es, ey - es, ex + es, ey + es, this.hudFill);
            }
            hudShine(canvas, this.r, f2r, ((this.time % 2.6f) / 1.1f), 70);
        }
        if (!z) {
            float cd = (float) Math.max(1L, meteorCd());
            float rem = Math.max(0.0f, Math.min(1.0f, ((float) (this.meteorReadyAt - j)) / cd));
            // remaining-cooldown shade (clipped to the rounded button)
            canvas.save();
            this.hudClip.reset();
            this.hudClip.addRoundRect(this.r, f2r, f2r, Path.Direction.CW);
            canvas.clipPath(this.hudClip);
            this.hudFill.setShader(null);
            this.hudFill.setStyle(Paint.Style.FILL);
            this.hudFill.setColor(-1728053248);
            float edge = this.r.right - (this.r.width() * rem);
            canvas.drawRect(edge, this.r.top, this.r.right, this.r.bottom, this.hudFill);
            this.hudFill.setColor(-21952);
            this.hudFill.setAlpha(160);
            canvas.drawRect(edge - Math.max(1.0f, this.pad * 0.2f), this.r.top, edge, this.r.bottom, this.hudFill);
            canvas.restore();
            // cooldown clock with sweep arc
            float cr = Math.min(this.pad * 1.15f, this.r.height() * 0.2f);
            float ccx = this.r.left + (this.pad * 0.6f) + cr;
            float ccy = this.r.top + (this.pad * 0.6f) + cr;
            this.hudFill.setColor(-871296992);
            canvas.drawCircle(ccx, ccy, cr, this.hudFill);
            this.hudR2.set(ccx - (cr * 0.82f), ccy - (cr * 0.82f), ccx + (cr * 0.82f), ccy + (cr * 0.82f));
            this.hudFill.setColor(-21952);
            canvas.drawArc(this.hudR2, -90.0f, (1.0f - rem) * 360.0f, true, this.hudFill);
            this.hudLine.setStyle(Paint.Style.STROKE);
            this.hudLine.setStrokeWidth(Math.max(1.0f, this.pad * 0.18f));
            this.hudLine.setColor(-1);
            this.hudLine.setAlpha(150);
            canvas.drawCircle(ccx, ccy, cr, this.hudLine);
        } else {
            long since = j - this.meteorReadyAt;
            if (since >= 0 && since < 450) {
                float fl = 1.0f - (since / 450.0f);
                this.hudFill.setShader(null);
                this.hudFill.setStyle(Paint.Style.FILL);
                this.hudFill.setColor(-1);
                this.hudFill.setAlpha((int) (fl * 170.0f));
                canvas.drawRoundRect(this.r, f2r, f2r, this.hudFill);
                hudGlow(canvas, this.r, f2r, -1, fl);
            }
            if (!z2 && meteorCost > 0.0d) {
                // mana progress toward the cast cost
                float mf = (float) Math.max(0.0d, Math.min(1.0d, this.mana / meteorCost));
                float bl = this.r.left + (this.pad * 1.2f);
                float br = this.r.right - (this.pad * 1.2f);
                float bb = this.r.bottom - (this.pad * 0.7f);
                float bt = bb - (this.pad * 0.55f);
                this.hudFill.setColor(-1442638576);
                canvas.drawRect(bl, bt, br, bb, this.hudFill);
                this.hudFill.setColor(-9077505);
                canvas.drawRect(bl, bt, bl + ((br - bl) * mf), bb, this.hudFill);
                this.hudFill.setColor(-1);
                this.hudFill.setAlpha(90);
                canvas.drawRect(bl, bt, bl + ((br - bl) * mf), bt + (this.pad * 0.18f), this.hudFill);
            }
        }
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setTextSize(this.pad * 1.9f);
        this.tp.setFakeBoldText(true);
        hudText(canvas, "☄ Метеор", this.r.centerX(), this.r.centerY() - (this.pad * 0.2f), z ? -1 : -3355444, 255);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.35f);
        hudText(canvas, (z ? new StringBuilder().append(fmt(meteorCost)).append(" маны") : new StringBuilder().append("откат ").append(((this.meteorReadyAt - j) / 1000) + 1).append("с")).toString(), this.r.centerX(), this.r.centerY() + (this.pad * 1.8f), -2043649, 255);
        // --- Buy mode ---
        float f4 = f * 2.0f;
        this.r.set(f + f2, this.actT, f4 - f2, this.actB);
        hudPanel(canvas, this.r, f2r, -13746322);
        int nModes = BUY_LABEL.length;
        if (nModes > 0) {
            float ps = this.pad * 0.32f;
            float pgap = this.pad * 1.1f;
            float px0 = this.r.centerX() - (((nModes - 1) * pgap) / 2.0f);
            float py = this.r.bottom - (this.pad * 0.95f);
            for (int k = 0; k < nModes; k++) {
                boolean on = k == this.buyMode;
                float pr2 = on ? ps * 1.35f : ps;
                this.hudFill.setShader(null);
                this.hudFill.setStyle(Paint.Style.FILL);
                this.hudFill.setColor(on ? -11430 : 1442840575);
                float pxk = px0 + (k * pgap);
                canvas.drawRect(pxk - pr2, py - pr2, pxk + pr2, py + pr2, this.hudFill);
            }
        }
        this.tp.setTextSize(this.pad * 2.2f);
        this.tp.setFakeBoldText(true);
        hudText(canvas, "🛒 " + BUY_LABEL[this.buyMode], this.r.centerX(), this.r.centerY() - (this.pad * 0.1f), -1, 255);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.35f);
        hudText(canvas, "режим покупки", this.r.centerX(), this.r.centerY() + (this.pad * 1.8f), -4140801, 255);
        // --- Rebirth ---
        double soulsGain = soulsGain();
        this.r.set(f4 + f2, this.actT, this.W - (this.pad * 1.5f), this.actB);
        if (soulsGain > 0.0d) {
            float sin = (float) ((Math.sin(this.time * 3.0f) * 0.5d) + 0.5d);
            float f5 = 40.0f * sin;
            hudGlow(canvas, this.r, f2r, -7680769, (0.5f * sin) + 0.3f);
            hudPing(canvas, this.r, f2r, -4243201, (this.time * 0.6f) + 0.5f);
            hudPanel(canvas, this.r, f2r, Color.rgb((int) (BF_BOSS_MAX + f5), (int) ((sin * 50.0f) + 130.0f), (int) (f5 + 200.0f)));
            // drifting soul wisps
            for (int k = 0; k < 5; k++) {
                float ph = ((this.time * 0.35f) + (k * 0.21f)) % 1.0f;
                float wx = this.r.left + (this.r.width() * ((k * 0.19f) + 0.1f)) + ((float) Math.sin((this.time * 2.0f) + (k * 1.7f)) * this.pad * 0.8f);
                float wy = this.r.bottom - (this.pad * 0.5f) - (ph * (this.r.height() - this.pad));
                float ws = this.pad * 0.3f;
                this.hudFill.setShader(null);
                this.hudFill.setStyle(Paint.Style.FILL);
                this.hudFill.setColor(-1);
                this.hudFill.setAlpha((int) ((float) Math.sin(ph * 3.1415927f) * 170.0f));
                canvas.drawRect(wx - ws, wy - ws, wx + ws, wy + ws, this.hudFill);
                canvas.drawRect(wx - (ws * 0.5f), wy + ws, wx + (ws * 0.5f), wy + (ws * 2.2f), this.hudFill);
            }
            hudShine(canvas, this.r, f2r, (((this.time + 1.3f) % 3.0f) / 1.2f), 60);
        } else {
            hudPanel(canvas, this.r, f2r, -12964796);
        }
        this.tp.setTextSize(this.pad * 1.8f);
        this.tp.setFakeBoldText(true);
        hudText(canvas, "👻 Переродиться", this.r.centerX(), this.r.centerY() - (this.pad * 0.2f), soulsGain > 0.0d ? -1 : -3355444, 255);
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 1.35f);
        hudText(canvas, soulsGain > 0.0d ? "+" + fmt(soulsGain) + " душ" : "с этапа 30", this.r.centerX(), this.r.centerY() + (this.pad * 1.8f), -2043649, 255);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void drawAltar(Canvas var1) {
       this.menuBackdrop(var1);
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
          int menuMax = ALT_MAX[var5];
          this.menuRowBar(var1, menuMax > 0 ? (float)var8 / (float)menuMax : 0.0F, var6 ? -11430 : var7, menuMax <= 12 ? menuMax : 0, var5);
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
       float menuFy = (float)this.H * 0.992F - this.pad * 0.45F;
       float menuFw = Math.min(this.tp.measureText("Потраченные души больше не усиливают урон — выбирай с умом"), (float)this.W - this.pad * 6.0F) * 0.5F + this.pad * 1.2F;
       this.menuDiamond(var1, (float)this.W / 2.0F - menuFw, menuFy, this.pad * 0.3F, menuAlpha(-7712808, 170));
       this.menuDiamond(var1, (float)this.W / 2.0F + menuFw, menuFy, this.pad * 0.3F, menuAlpha(-7712808, 170));
       var1.drawText("Потраченные души больше не усиливают урон — выбирай с умом", (float)this.W / 2.0F, (float)this.H * 0.992F, this.tp);
    }

    // ===== gfx/bg: world & atmosphere (sky light, weather, grass, vignette) =====
    static final int[] bgStarCol = {0xFFFFF6E0, 0xFFC8B8FF, 0xFF9CECFF, 0xFFCFA9FF, 0xFFFFD6CC, 0xFFFFE6A0};
    static final int[] bgHazeCol = {0xFFC27AA8, 0xFF3E8099, 0xFFFF5A3A};
    static final int[] bgMistCol = {0xFFC8B8FF, 0xFF9CECFF, 0xFFFF8A70};
    static final int[] bgEdgeCol = {0xFF12062A, 0xFF030C16, 0xFF1C0004};
    static final int[] bgLightCol = {0xFFFFF0C8, 0xFF9CECFF, 0xFFFF5A48};
    static final int[] bgMoteCol = {0xFF5AE0FF, 0xFF5AFFB0, 0xFFB070FF, 0xFF9CECFF};
    static final int[] bgEmberCol = {0xFFFFD35A, 0xFFFF6A3A, 0xFFFF3A2A};
    static final int[] bgLeafCol = {0xFFC0803A, 0xFF5BBE4A, 0xFFAC6383};
    static final int[] bgFlowerCol = {0xFFFF8AC0, 0xFFFFD35A, 0xFFE8E0FF};
    // per-biome cloud palette: body, moon-lit top, underside
    static final int[] bgCloudCol = {
        0xFF6A4E96, 0xFFC8B4E8, 0xFF46306E,
        0xFF9CECFF, 0xFFC8F4FF, 0xFF5AA0C0,
        0xFF3A1020, 0xFFD04A40, 0xFF1A0A14};
    // per-biome grass palette: base, stem, upper, tip, foreground silhouette
    static final int[] bgGrassCol = {
        0xFF1F4A22, 0xFF2E6A2A, 0xFF5BBE4A, 0xFFA8E88A, 0xFF0B1A10,
        0xFF41587A, 0xFF8AB0D0, 0xFFC8E4F8, 0xFFFFFFFF, 0xFF121A2A,
        0xFF3A1414, 0xFF6A2A20, 0xFFB8503A, 0xFFFF9A5A, 0xFF16070A};
    // pixel cloud rows (x0,x1 in cloud pixels, top row first), two puffy shapes of 8 rows
    static final byte[] bgCloudRows = {
        8, 13, 5, 16, 4, 18, 2, 21, 1, 23, 0, 24, 1, 23, 3, 20,
        4, 9, 2, 12, 1, 17, 0, 22, 0, 28, 2, 30, 3, 29, 6, 25};
    // foreground decoration spots (fractions of W), kept clear of hero and monster
    static final float[] bgDecoX = {0.045f, 0.385f, 0.47f, 0.865f, 0.955f};
    final Paint bgP = new Paint();
    final Paint bgGlowP = new Paint(1);
    final Paint bgLineP = new Paint(1);
    final Paint bgBmpP = new Paint();
    final Paint bgSkyP = new Paint();
    final Paint bgFloorP = new Paint();
    final Paint bgHaloP = new Paint(1);
    final Paint bgRayP = new Paint(1);
    final Paint bgShaftP = new Paint(1);
    final Paint bgHazeP = new Paint();
    final Paint bgGroundP = new Paint();
    final Paint bgRimP = new Paint();
    final Paint bgTintP = new Paint();
    final Paint bgVigP = new Paint();
    final Paint bgTopP = new Paint();
    final Paint bgBotP = new Paint();
    final Paint bgFzP = new Paint();
    final Paint bgBossP = new Paint();
    final RectF bgR = new RectF();
    final Rect bgSrc = new Rect();
    final Path bgRayPath = new Path();
    final Path[] bgShaft = {new Path(), new Path(), new Path()};
    final float[] bgShaftO = new float[6];
    final LightingColorFilter[] bgBossCF = bgMakeCF(0xC4949C, 0x2A0008, 10);
    final LightingColorFilter[] bgFzCF = bgMakeCF(0xFFEAD0, 0x120400, 8);
    // what bgScan() learned from each biome's background art
    final Bitmap[] bgScanBmp = new Bitmap[3];
    int[] bgPix;
    final int[] bgCells = new int[2048];
    final int[] bgBw = {128, 128, 128};
    final int[] bgBh = {112, 112, 112};
    final int[] bgSkyC = {0xFF1E1440, 0xFF2E2A40, 0xFF12060E};
    final int[] bgFloorC = {0xFF2A1E16, 0xFF221E30, 0xFF2A2230};
    final float[] bgMoonU = new float[3];
    final float[] bgMoonV = new float[3];
    final float[] bgMoonR = new float[3];
    final int[] bgStarN = new int[3];
    final float[] bgStarU = new float[66];
    final float[] bgStarV = new float[66];
    final int[] bgGlintN = new int[3];
    final float[] bgGlintU = new float[60];
    final float[] bgGlintV = new float[60];
    final int[] bgSpotN = new int[3];
    final float[] bgSpotU = new float[72];
    final float[] bgSpotV = new float[72];
    final int[] bgSpotC = new int[72];
    // shader cache keys and per-frame visual state
    int bgKeyBi = -1;
    float bgKeyW = -1f, bgKeyT, bgKeyB, bgKeyG;
    int bgVKeyBi = -1;
    float bgVKeyW = -1f, bgVKeyT, bgVKeyB;
    float bgTop, bgBx = 1f, bgMX, bgMY, bgMR, bgHaloR, bgHazeT, bgHazeB;
    float bgFz, bgBossA, bgBeat, bgLastT;
    long bgFzOn;

    static LightingColorFilter[] bgMakeCF(int mul, int add, int n) {
        LightingColorFilter[] a = new LightingColorFilter[n];
        for (int i = 0; i < n; i++) {
            float f = (i + 1f) / n;
            int m = 0;
            int d = 0;
            for (int sh = 0; sh <= 16; sh += 8) {
                int mc = (mul >> sh) & 255;
                int ac = (add >> sh) & 255;
                m |= Math.round(255f + (mc - 255f) * f) << sh;
                d |= Math.round(ac * f) << sh;
            }
            a[i] = new LightingColorFilter(m, d);
        }
        return a;
    }

    static int bgA(int c, int a) {
        return (c & 0xFFFFFF) | ((a < 0 ? 0 : a > 255 ? 255 : a) << 24);
    }

    static int bgShade(int c, float f) {
        int r = Math.min(255, (int) (((c >> 16) & 255) * f));
        int g = Math.min(255, (int) (((c >> 8) & 255) * f));
        int b = Math.min(255, (int) ((c & 255) * f));
        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }

    static int bgAl(float a) {
        int v = (int) a;
        return v < 0 ? 0 : v > 255 ? 255 : v;
    }

    static float bgWrap(float v, float m) {
        if (!(m > 0f)) return 0f;
        float r = v % m;
        return r < 0f ? r + m : r;
    }

    static float bgHash(int n) {
        n = (n << 13) ^ n;
        n = n * (n * n * 15731 + 789221) + 1376312589;
        return (n & 0x7fffffff) / 2147483648f;
    }

    static float bgSin(float a) {
        return (float) Math.sin(a);
    }

    // wind for grass and flowers: idle sway + a gust wave rolling left to right + frenzy flutter
    float bgWind(float x, float t, int i) {
        float w = bgSin(t * 1.8f + i * 0.9f) * 0.7f;
        float g = bgSin(t * 0.55f - x / Math.max(1f, this.W) * 3.5f);
        if (g > 0f) w += g * g * g * 2.4f;
        return w + this.bgFz * bgSin(t * 11f + i * 1.7f) * 0.7f;
    }

    boolean bgNearMoon(int bi, float x, float y, float pad) {
        float r = this.bgMoonR[bi];
        if (r <= 0f) return false;
        float dx = x + 0.5f - this.bgMoonU[bi];
        float dy = y + 0.5f - this.bgMoonV[bi];
        return dx * dx + dy * dy < (r + pad) * (r + pad);
    }

    static boolean bgMoonPx(int c) {
        int r = (c >> 16) & 255;
        return r >= 200 && r - (c & 255) >= 40;
    }

    // one-off analysis of the background art: moon, painted stars, glowing spots, free sky for new stars
    void bgScan(Bitmap b, int bi) {
        this.bgScanBmp[bi] = b;
        this.bgMoonR[bi] = 0f;
        this.bgStarN[bi] = 0;
        this.bgGlintN[bi] = 0;
        this.bgSpotN[bi] = 0;
        int w = b.getWidth();
        int h = b.getHeight();
        if (w < 8 || h < 8) return;
        this.bgBw[bi] = w;
        this.bgBh[bi] = h;
        int n = w * h;
        if (this.bgPix == null || this.bgPix.length < n) this.bgPix = new int[n];
        int[] px = this.bgPix;
        try {
            b.getPixels(px, 0, w, 0, 0, w, h);
        } catch (RuntimeException e) {
            return;
        }
        this.bgSkyC[bi] = px[0] | 0xFF000000;
        this.bgFloorC[bi] = px[n - w] | 0xFF000000;
        // moon: densest 8x8 block of bright warm pixels in the upper sky, refined by centroid
        int skyRows = h * 45 / 100;
        int bestN = 0;
        int bestX = 0;
        int bestY = 0;
        for (int by = 0; by < skyRows; by += 8) {
            for (int bx = 0; bx < w; bx += 8) {
                int m = 0;
                for (int y = by; y < Math.min(skyRows, by + 8); y++) {
                    for (int x = bx; x < Math.min(w, bx + 8); x++) {
                        if (bgMoonPx(px[y * w + x])) m++;
                    }
                }
                if (m > bestN) {
                    bestN = m;
                    bestX = bx + 4;
                    bestY = by + 4;
                }
            }
        }
        if (bestN >= 6) {
            float cx = bestX;
            float cy = bestY;
            int cnt = 0;
            for (int it = 0; it < 3; it++) {
                float sx = 0f;
                float sy = 0f;
                int m = 0;
                for (int y = 0; y < skyRows; y++) {
                    for (int x = 0; x < w; x++) {
                        float dx = x - cx;
                        float dy = y - cy;
                        if (dx * dx + dy * dy < 256f && bgMoonPx(px[y * w + x])) {
                            sx += x;
                            sy += y;
                            m++;
                        }
                    }
                }
                if (m == 0) break;
                cx = sx / m;
                cy = sy / m;
                cnt = m;
            }
            if (cnt >= 12) {
                this.bgMoonU[bi] = cx + 0.5f;
                this.bgMoonV[bi] = cy + 0.5f;
                this.bgMoonR[bi] = (float) Math.sqrt(cnt / Math.PI);
            }
        }
        // painted stars (bright neutral pixels) get an occasional sparkle
        int lim = h * 6 / 10;
        int total = 0;
        for (int y = 0; y < lim; y++) {
            for (int x = 0; x < w; x++) {
                int c = px[y * w + x];
                int mn = Math.min((c >> 16) & 255, Math.min((c >> 8) & 255, c & 255));
                if (mn >= 0x90 && !bgNearMoon(bi, x, y, 3f)) total++;
            }
        }
        int stride = Math.max(1, (total + 19) / 20);
        int seen = 0;
        int gn = 0;
        for (int y = 0; y < lim && gn < 20; y++) {
            for (int x = 0; x < w && gn < 20; x++) {
                int c = px[y * w + x];
                int mn = Math.min((c >> 16) & 255, Math.min((c >> 8) & 255, c & 255));
                if (mn >= 0x90 && !bgNearMoon(bi, x, y, 3f)) {
                    if (seen % stride == 0) {
                        this.bgGlintU[bi * 20 + gn] = x;
                        this.bgGlintV[bi * 20 + gn] = y;
                        gn++;
                    }
                    seen++;
                }
            }
        }
        this.bgGlintN[bi] = gn;
        // glowing spots: saturated bright pixels (crystals, castle windows), clustered into cells
        int cs = 6;
        while ((w / cs + 1) * (h / cs + 1) * 4 > this.bgCells.length) cs++;
        int cw = w / cs + 1;
        int cells = cw * (h / cs + 1);
        int[] cl = this.bgCells;
        java.util.Arrays.fill(cl, 0, cells * 4, 0);
        for (int y = h / 4; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int c = px[y * w + x];
                int r = (c >> 16) & 255;
                int g = (c >> 8) & 255;
                int bb = c & 255;
                int mx = Math.max(r, Math.max(g, bb));
                int mn = Math.min(r, Math.min(g, bb));
                if (mx >= 0xC0 && mn < 0xA0 && !bgNearMoon(bi, x, y, 3f)) {
                    int ci = ((y / cs) * cw + x / cs) * 4;
                    cl[ci] += x;
                    cl[ci + 1] += y;
                    cl[ci + 2]++;
                    cl[ci + 3] = c;
                }
            }
        }
        int sn = 0;
        for (int ci = 0; ci < cells && sn < 24; ci++) {
            int m = cl[ci * 4 + 2];
            if (m >= 2) {
                this.bgSpotU[bi * 24 + sn] = (float) cl[ci * 4] / m + 0.5f;
                this.bgSpotV[bi * 24 + sn] = (float) cl[ci * 4 + 1] / m + 0.5f;
                this.bgSpotC[bi * 24 + sn] = cl[ci * 4 + 3] | 0xFF000000;
                sn++;
            }
        }
        this.bgSpotN[bi] = sn;
        // extra twinkling stars on flat stretches of sky (or cave wall)
        int y0 = bi == 1 ? h / 5 : 1;
        int y1 = bi == 1 ? h * 55 / 100 : h * 42 / 100;
        int s = 0x5EED + bi * 7919;
        int st = 0;
        for (int a = 0; a < 800 && st < 22; a++) {
            s = s * 1103515245 + 12345;
            int x = 1 + ((s >>> 8) % (w - 2));
            s = s * 1103515245 + 12345;
            int y = y0 + ((s >>> 8) % Math.max(1, y1 - y0));
            if (y < 1 || y >= h - 1) continue;
            int c = px[y * w + x];
            if (c != px[y * w] || c != px[y * w + x - 1] || c != px[y * w + x + 1]
                    || c != px[(y - 1) * w + x] || c != px[(y + 1) * w + x]) continue;
            if (bgNearMoon(bi, x, y, 4f)) continue;
            boolean dup = false;
            for (int k = 0; k < st; k++) {
                if (Math.abs(this.bgStarU[bi * 22 + k] - x) + Math.abs(this.bgStarV[bi * 22 + k] - y) < 5f) {
                    dup = true;
                    break;
                }
            }
            if (dup) continue;
            this.bgStarU[bi * 22 + st] = x;
            this.bgStarV[bi * 22 + st] = y;
            st++;
        }
        this.bgStarN[bi] = st;
    }

    // rebuilds cached shaders/paths only when size, layout or biome change
    void bgEnsure(Bitmap bmp, int bi) {
        if (bmp != null && this.bgScanBmp[bi] != bmp) {
            bgScan(bmp, bi);
            this.bgKeyBi = -1;
        }
        float u = this.bgS;
        float W = this.W;
        this.bgTop = this.groundY - u * 96f;
        this.bgBx = W / Math.max(1, this.bgBw[bi]);
        if (this.bgKeyBi == bi && this.bgKeyW == W && this.bgKeyT == this.battleT
                && this.bgKeyB == this.battleB && this.bgKeyG == this.groundY) return;
        this.bgKeyBi = bi;
        this.bgKeyW = W;
        this.bgKeyT = this.battleT;
        this.bgKeyB = this.battleB;
        this.bgKeyG = this.groundY;
        float top = this.bgTop;
        float T = this.battleT;
        float B = this.battleB;
        float G = this.groundY;
        Shader.TileMode cl = Shader.TileMode.CLAMP;
        int sky = this.bgSkyC[bi];
        int floor = this.bgFloorC[bi];
        this.bgSkyP.setShader(new android.graphics.LinearGradient(0f, T, 0f, Math.max(T + 1f, top),
                bgShade(sky, 0.5f), sky, cl));
        float bottom = top + this.bgBh[bi] * u;
        this.bgFloorP.setShader(new android.graphics.LinearGradient(0f, bottom, 0f, Math.max(bottom + 1f, B),
                floor, bgShade(floor, 0.45f), cl));
        int lc = bgLightCol[bi];
        float mr = this.bgMoonR[bi] * u;
        this.bgMR = mr;
        if (mr > 0f) {
            float mx = this.bgMoonU[bi] * this.bgBx;
            float my = top + this.bgMoonV[bi] * u;
            this.bgMX = mx;
            this.bgMY = my;
            float R = mr * (bi == 2 ? 6.5f : 5.5f);
            this.bgHaloR = R;
            float rr = Math.min(0.5f, mr / R);
            this.bgHaloP.setShader(new RadialGradient(mx, my, R,
                    new int[]{bgA(lc, 0x48), bgA(lc, 0x3C), bgA(lc, 0x12), bgA(lc, 0)},
                    new float[]{0f, rr, rr + (1f - rr) * 0.35f, 1f}, cl));
            float L = mr * 9f;
            this.bgRayP.setShader(new RadialGradient(0f, 0f, L,
                    new int[]{bgA(lc, 0x40), bgA(lc, 0x1A), bgA(lc, 0)}, new float[]{0f, 0.4f, 1f}, cl));
            Path rp = this.bgRayPath;
            rp.reset();
            for (int k = 0; k < 12; k++) {
                double a = k * Math.PI / 6.0 + ((k * 7) % 5) * 0.06;
                float len = L * (k % 2 == 0 ? 1f : 0.6f) * (k % 3 == 0 ? 1f : 0.85f);
                double h0 = 0.10;
                double h1 = 0.018;
                rp.moveTo((float) Math.cos(a - h0) * mr, (float) Math.sin(a - h0) * mr);
                rp.lineTo((float) Math.cos(a - h1) * len, (float) Math.sin(a - h1) * len);
                rp.lineTo((float) Math.cos(a + h1) * len, (float) Math.sin(a + h1) * len);
                rp.lineTo((float) Math.cos(a + h0) * mr, (float) Math.sin(a + h0) * mr);
                rp.close();
            }
        } else {
            this.bgHaloP.setShader(null);
            this.bgRayP.setShader(null);
            this.bgMX = W * 0.5f;
            this.bgMY = T;
        }
        for (int k = 0; k < 3; k++) this.bgShaft[k].reset();
        if (bi == 1) {
            // light falling through cracks in the cave ceiling
            float oy = top + 5f * u;
            for (int k = 0; k < 3; k++) {
                float ox = (0.17f + 0.33f * k + 0.04f * bgHash(k + 9)) * W;
                float dx = 0.09f * W;
                float w0 = (2f + k) * u * 0.5f;
                float w1 = (7f + 3f * k) * u * 0.5f;
                Path sp = this.bgShaft[k];
                sp.moveTo(ox - w0, oy);
                sp.lineTo(ox + w0, oy);
                sp.lineTo(ox + dx + w1, G);
                sp.lineTo(ox + dx - w1, G);
                sp.close();
                this.bgShaftO[k * 2] = ox;
                this.bgShaftO[k * 2 + 1] = oy;
            }
            this.bgShaftP.setShader(new android.graphics.LinearGradient(0f, oy, 0f, Math.max(oy + 1f, G),
                    new int[]{bgA(lc, 0x3A), bgA(lc, 0x12), bgA(lc, 0)}, new float[]{0f, 0.6f, 1f}, cl));
        } else if (mr > 0f) {
            // moonbeams slanting from the moon toward the middle of the field
            float ox = this.bgMX;
            float oy = this.bgMY;
            float dir = ox > W * 0.5f ? -1f : 1f;
            for (int k = 0; k < 3; k++) {
                float gx = ox + dir * (0.16f + 0.17f * k) * W;
                float vx = gx - ox;
                float vy = G - oy;
                float len = (float) Math.sqrt(vx * vx + vy * vy);
                this.bgShaftO[k * 2] = ox;
                this.bgShaftO[k * 2 + 1] = oy;
                if (len < 1f) continue;
                float nx = -vy / len;
                float ny = vx / len;
                float w0 = mr * (0.55f + 0.2f * k);
                float w1 = (5f + 3.5f * k) * u;
                float sx = ox + vx / len * mr * 1.3f;
                float sy = oy + vy / len * mr * 1.3f;
                Path sp = this.bgShaft[k];
                sp.moveTo(sx + nx * w0, sy + ny * w0);
                sp.lineTo(sx - nx * w0, sy - ny * w0);
                sp.lineTo(gx - nx * w1, G - ny * w1);
                sp.lineTo(gx + nx * w1, G + ny * w1);
                sp.close();
            }
            this.bgShaftP.setShader(new android.graphics.LinearGradient(ox, oy, ox + dir * 0.3f * W, Math.max(oy + 1f, G),
                    new int[]{bgA(lc, 0x30), bgA(lc, 0x12), bgA(lc, 0)}, new float[]{0f, 0.55f, 1f}, cl));
        } else {
            this.bgShaftP.setShader(null);
        }
        int hr = bi == 0 ? 62 : bi == 1 ? 66 : 74;
        this.bgHazeT = top + (hr - 16) * u;
        this.bgHazeB = top + (hr + 12) * u;
        int hc = bgHazeCol[bi];
        this.bgHazeP.setShader(new android.graphics.LinearGradient(0f, this.bgHazeT, 0f, Math.max(this.bgHazeT + 1f, this.bgHazeB),
                new int[]{bgA(hc, 0), bgA(hc, 0x4C), bgA(hc, 0)}, new float[]{0f, 0.6f, 1f}, cl));
        int ec = bgEdgeCol[bi];
        float g0 = G + 2f * u;
        this.bgGroundP.setShader(new android.graphics.LinearGradient(0f, g0, 0f, Math.max(g0 + 1f, B),
                new int[]{bgA(ec, 0x60), bgA(ec, 0x18), bgA(ec, 0x88)}, new float[]{0f, 0.2f, 1f}, cl));
        float lx = bi == 1 ? W * 0.5f : this.bgMX;
        this.bgRimP.setShader(new android.graphics.LinearGradient(lx - 0.75f * W, 0f, lx + 0.75f * W, 0f,
                new int[]{bgA(lc, 0), bgA(lc, 0x70), bgA(lc, 0)}, null, cl));
        this.bgTintP.setShader(new android.graphics.LinearGradient(0f, T, 0f, Math.max(T + 1f, B),
                new int[]{0x28FFC040, 0x0AFF8A30, 0x36FF2A10}, new float[]{0f, 0.5f, 1f}, cl));
    }

    // sky, background art (colour graded for boss/frenzy), atmosphere and frenzy tint
    void bgDrawBackdrop(Canvas c, Bitmap bmp, long now, boolean frenzy) {
        if (this.W <= 0 || this.H <= 0 || !(this.bgS > 0f)) return;
        int bi = biome();
        float t = this.time;
        if (frenzy) {
            if (this.bgFzOn == 0L || this.bgFzOn > now) this.bgFzOn = now;
            float in = (now - this.bgFzOn) / 450f;
            float out = (this.frenzyUntil - now) / 700f;
            this.bgFz = Math.max(0f, Math.min(1f, Math.min(in, out)));
        } else {
            this.bgFzOn = 0L;
            this.bgFz = 0f;
        }
        float dt = t - this.bgLastT;
        this.bgLastT = t;
        float target = bossFight() ? 1f : 0f;
        if (dt > 0.5f || dt < 0f) {
            this.bgBossA = target;
        } else {
            this.bgBossA += (target - this.bgBossA) * Math.min(1f, dt * 2.5f);
            if (Math.abs(target - this.bgBossA) < 0.003f) this.bgBossA = target;
        }
        float ph = bgWrap(t, 1.3f) / 1.3f;
        float d1 = Math.min(ph, 1f - ph) / 0.07f;
        float d2 = (ph - 0.21f) / 0.07f;
        this.bgBeat = Math.min(1f, (float) Math.exp(-d1 * d1) + 0.6f * (float) Math.exp(-d2 * d2));
        bgEnsure(bmp, bi);
        float top = this.bgTop;
        float bottom = top + this.bgBh[bi] * this.bgS;
        if (bmp == null) {
            this.bgP.setColor(this.bgSkyC[bi]);
            c.drawRect(0f, this.battleT, this.W, this.battleB, this.bgP);
        } else {
            if (top > this.battleT) c.drawRect(0f, this.battleT, this.W, top + 1f, this.bgSkyP);
            LightingColorFilter cf = null;
            if (this.bgBossA > 0.02f) {
                float lv = this.bgBossA * (0.72f + 0.28f * this.bgBeat);
                cf = this.bgBossCF[Math.max(0, Math.min(this.bgBossCF.length - 1, (int) (lv * this.bgBossCF.length)))];
            } else if (this.bgFz > 0.02f) {
                float lv = this.bgFz * (0.8f + 0.2f * bgSin(t * 6f));
                cf = this.bgFzCF[Math.max(0, Math.min(this.bgFzCF.length - 1, (int) (lv * this.bgFzCF.length)))];
            }
            this.bgBmpP.setFilterBitmap(false);
            this.bgBmpP.setColorFilter(cf);
            this.bgSrc.set(0, 0, bmp.getWidth(), bmp.getHeight());
            this.bgR.set(0f, top, this.W, bottom);
            c.drawBitmap(bmp, this.bgSrc, this.bgR, this.bgBmpP);
            if (bottom < this.battleB) c.drawRect(0f, bottom - 1f, this.W, this.battleB, this.bgFloorP);
        }
        drawAmbient(c);
        if (this.bgFz > 0.01f) {
            float pul = 0.62f + 0.22f * bgSin(t * 6f) + 0.16f * this.beatPulse;
            this.bgTintP.setAlpha(bgAl(255f * this.bgFz * pul));
            c.drawRect(0f, this.battleT, this.W, this.battleB, this.bgTintP);
        }
    }

    void bgPixel(Canvas c, float x, float y, float s, int col, int a) {
        this.bgP.setColor(col);
        this.bgP.setAlpha(a);
        c.drawRect(x, y, x + s, y + s, this.bgP);
    }

    void bgGlow(Canvas c, float x, float y, float r, int col, float a) {
        if (a < 3f) return;
        // three stepped discs read as a soft pixel-era bloom
        this.bgGlowP.setColor(col);
        this.bgGlowP.setAlpha(bgAl(a * 0.3f));
        c.drawCircle(x, y, r, this.bgGlowP);
        this.bgGlowP.setAlpha(bgAl(a * 0.45f));
        c.drawCircle(x, y, r * 0.62f, this.bgGlowP);
        this.bgGlowP.setAlpha(bgAl(a * 0.7f));
        c.drawCircle(x, y, r * 0.32f, this.bgGlowP);
    }

    // four-arm pixel sparkle around the pixel at (x,y)
    void bgSparkle(Canvas c, float x, float y, float s, int col, int a) {
        Paint q = this.bgP;
        q.setColor(col);
        q.setAlpha(a);
        c.drawRect(x - s, y, x, y + s, q);
        c.drawRect(x + s, y, x + 2f * s, y + s, q);
        c.drawRect(x, y - s, x + s, y, q);
        c.drawRect(x, y + s, x + s, y + 2f * s, q);
    }

    void bgCloud(Canvas c, float x, float y, float s, int bi, int a, int shape) {
        Paint q = this.bgP;
        int o = shape * 16;
        for (int row = 0; row < 8; row++) {
            int col = row < 2 ? bgCloudCol[bi * 3 + 1] : row >= 6 ? bgCloudCol[bi * 3 + 2] : bgCloudCol[bi * 3];
            q.setColor(col);
            q.setAlpha(row < 2 ? Math.min(255, a + a / 2) : a);
            c.drawRect(x + bgCloudRows[o + row * 2] * s, y + row * s, x + bgCloudRows[o + row * 2 + 1] * s, y + (row + 1) * s, q);
        }
    }

    void bgDrawMoon(Canvas c, int bi, float t) {
        if (this.bgMR <= 0f || this.bgHaloP.getShader() == null) return;
        float br = bi == 2 ? 0.78f + 0.22f * bgSin(t * 1.1f) : 0.86f + 0.14f * bgSin(t * 0.6f);
        if (this.bgBossA > 0f) br = Math.min(1f, br + 0.25f * this.bgBossA * this.bgBeat);
        this.bgHaloP.setAlpha(bgAl(255f * br));
        c.drawCircle(this.bgMX, this.bgMY, this.bgHaloR, this.bgHaloP);
        if (this.bgRayP.getShader() != null) {
            this.bgRayP.setAlpha(bgAl(255f * (0.6f + 0.4f * bgSin(t * 0.45f))));
            c.save();
            c.translate(this.bgMX, this.bgMY);
            c.rotate(bgWrap(t * 2.5f, 360f));
            c.drawPath(this.bgRayPath, this.bgRayP);
            c.restore();
        }
    }

    void bgDrawSky(Canvas c, int bi, float t, float u) {
        float top = this.bgTop;
        float bx = this.bgBx;
        float minY = this.battleT - u;
        // twinkling stars on clear sky
        int n = this.bgStarN[bi];
        int c1 = bgStarCol[bi * 2];
        int c2 = bgStarCol[bi * 2 + 1];
        for (int k = 0; k < n; k++) {
            float x = this.bgStarU[bi * 22 + k] * bx;
            float y = top + this.bgStarV[bi * 22 + k] * u;
            if (y < minY) continue;
            float tw = 0.5f + 0.5f * bgSin(t * (1.1f + (k % 5) * 0.33f) + k * 2.39f);
            tw *= tw;
            int col = k % 3 == 0 ? c2 : c1;
            float a = (bi == 1 ? 140f : 220f) * tw + 25f;
            bgPixel(c, x, y, u, col, bgAl(a));
            if (tw > 0.8f && k % 2 == 0) bgSparkle(c, x, y, u, col, bgAl((tw - 0.8f) * 5f * a * 0.6f));
        }
        // painted stars in the art flare now and then
        n = this.bgGlintN[bi];
        for (int k = 0; k < n; k++) {
            float f = bgSin(t * (0.7f + (k % 4) * 0.15f) + k * 1.7f);
            if (f < 0.72f) continue;
            float x = this.bgGlintU[bi * 20 + k] * bx;
            float y = top + this.bgGlintV[bi * 20 + k] * u;
            if (y < minY) continue;
            float a = (f - 0.72f) / 0.28f;
            bgSparkle(c, x, y, u, 0xFFFFFFFF, bgAl(170f * a));
            bgGlow(c, x + u * 0.5f, y + u * 0.5f, u * 2.4f, c1, 60f * a);
        }
        // shooting star (not inside the cave)
        if (bi != 1) {
            float per = bi == 0 ? 7.5f : 9.5f;
            int idx = (int) Math.floor(t / per);
            float p = (t - idx * per) / 0.9f;
            if (p >= 0f && p < 1f) {
                float sx = (0.35f + 0.6f * bgHash(idx * 7 + bi)) * this.W;
                float sy = Math.max(this.battleT + 2f * u, top + (4f + 26f * bgHash(idx * 13 + 5)) * u);
                float dx = -0.42f * this.W;
                float dy = 0.16f * this.W;
                float fade = bgSin(p * 3.1415927f);
                float hx = sx + dx * p;
                float hy = sy + dy * p;
                for (int s = 11; s >= 0; s--) {
                    float k = 1f - s / 12f;
                    float px = hx - dx * 0.022f * s;
                    float py = hy - dy * 0.022f * s;
                    float sz = s < 2 ? u : u * 0.7f;
                    bgPixel(c, px, py, sz, s < 3 ? 0xFFFFFFFF : c1, bgAl(255f * fade * k * k));
                }
                bgGlow(c, hx + u * 0.5f, hy + u * 0.5f, u * 3f, c1, 90f * fade);
            }
        }
    }

    void bgDrawClouds(Canvas c, int bi, float t, float u) {
        float W = this.W;
        float top = Math.max(this.bgTop, this.battleT - 2f * u);
        if (bi == 1) {
            // slow drifting banks of cave haze instead of clouds
            for (int k = 0; k < 4; k++) {
                float s = u * (1.3f + 0.3f * (k % 2));
                float wd = 31f * s;
                float x = bgWrap(bgHash(k * 9 + 2) * (W + wd) + t * (1.0f + 0.4f * k) * u * (k % 2 == 0 ? 1f : -1f), W + wd) - wd;
                float y = top + (26f + 9f * k) * u;
                bgCloud(c, x, y, s, 1, bgAl(14f + 8f * bgSin(t * 0.5f + k * 1.9f)), k % 2);
            }
            return;
        }
        int yMin = bi == 2 ? 2 : 4;
        int ySpan = bi == 2 ? 12 : 30;
        // far layer: small, slow, faint
        for (int k = 0; k < 3; k++) {
            float s = u * 0.9f;
            float wd = 31f * s;
            float x = bgWrap(bgHash(k * 3 + bi) * (W + wd) + t * (1.4f + 0.3f * k) * u, W + wd) - wd;
            float y = top + (yMin + bgHash(k * 11 + 4) * ySpan * 0.6f) * u;
            bgCloud(c, x, y, s, bi, bi == 2 ? 130 : 80, k % 2);
        }
        // near layer: bigger, faster, denser
        for (int k = 0; k < 2; k++) {
            float s = u * 1.4f;
            float wd = 31f * s;
            float x = bgWrap(bgHash(k * 5 + 17 + bi) * (W + wd) + t * (3.2f + 0.8f * k) * u, W + wd) - wd;
            float y = top + (yMin + ySpan * 0.45f + bgHash(k * 7 + 23) * ySpan * 0.5f) * u;
            bgCloud(c, x, y, s, bi, bi == 2 ? 170 : 120, (k + 1) % 2);
        }
    }

    void bgDrawShafts(Canvas c, int bi, float t) {
        if (this.bgShaftP.getShader() == null) return;
        float base = bi == 1 ? 0.7f : 0.55f;
        for (int k = 0; k < 3; k++) {
            float a = base + (1f - base) * bgSin(t * (0.5f + 0.13f * k) + k * 2.1f);
            this.bgShaftP.setAlpha(bgAl(255f * a * 0.42f));
            float rot = bgSin(t * 0.23f + k * 1.7f) * 1.6f;
            // three slightly fanned passes give the beam soft edges
            for (int e = -1; e <= 1; e++) {
                c.save();
                c.rotate(rot + e * 1.3f, this.bgShaftO[k * 2], this.bgShaftO[k * 2 + 1]);
                c.drawPath(this.bgShaft[k], this.bgShaftP);
                c.restore();
            }
        }
    }

    void bgDrawSpots(Canvas c, int bi, float t, float u) {
        if (bi == 0) return;
        int n = this.bgSpotN[bi];
        float top = this.bgTop;
        for (int k = 0; k < n; k++) {
            float x = this.bgSpotU[bi * 24 + k] * this.bgBx;
            float y = top + this.bgSpotV[bi * 24 + k] * u;
            int col = this.bgSpotC[bi * 24 + k];
            if (bi == 2) {
                // candle-lit castle windows flicker
                float f = 0.62f + 0.22f * bgSin(t * 6.3f + k * 2.1f) + 0.16f * bgSin(t * 15.7f + k * 4.7f);
                bgGlow(c, x, y, u * 2.6f, 0xFFFFB23E, 70f * f);
            } else {
                // crystals breathe light
                float f = 0.5f + 0.5f * bgSin(t * 1.4f + k * 1.3f);
                bgGlow(c, x, y, u * 3.4f, col, 30f + 45f * f);
                if (f > 0.93f && k % 2 == 0) bgSparkle(c, x - u * 0.5f, y - u * 1.5f, u * 0.7f, 0xFFFFFFFF, bgAl((f - 0.93f) / 0.07f * 220f));
            }
        }
    }

    void bgDrawForest(Canvas c, float t, float u, float sky) {
        float W = this.W;
        float G = this.groundY;
        // fireflies with soft glow, drifting and blinking
        for (int k = 0; k < 14; k++) {
            float bx = (0.04f + 0.92f * bgHash(k * 31 + 1)) * W;
            float by = G - 3f * u - bgHash(k * 17 + 2) * 0.5f * sky;
            float x = bx + bgSin(t * (0.31f + 0.07f * (k % 4)) + k * 1.9f) * 0.07f * W + bgSin(t * 1.13f + k) * 1.2f * u;
            float y = by + bgSin(t * (0.43f + 0.05f * (k % 3)) + k * 2.7f) * 5f * u;
            float b = 0.5f + 0.5f * bgSin(t * (1.6f + 0.25f * (k % 5)) + k * 2.3f);
            b = b * b * b;
            bgGlow(c, x, y, u * 4.2f, 0xFFD8FF60, 95f * b);
            bgPixel(c, x - u * 0.45f, y - u * 0.45f, u * 0.9f, 0xFFF0FFB0, bgAl(50f + 205f * b));
        }
        // leaves tumbling down
        for (int k = 0; k < 6; k++) {
            float per = 7f + 2.3f * k;
            float p = bgWrap(t + k * 3.7f, per) / per;
            float y = this.battleT - 2f * u + p * (G + 2f * u - this.battleT);
            float x = bgWrap(bgHash(k * 19 + 7) * W + t * (2f + k * 0.5f) * u + bgSin(t * 1.7f + k * 2f) * 3f * u, W + 4f * u) - 2f * u;
            int a = bgAl(210f * Math.min(1f, (1f - p) / 0.08f));
            int col = bgLeafCol[k % 3];
            this.bgP.setColor(col);
            this.bgP.setAlpha(a);
            float s = u * 0.75f;
            if (bgSin(t * 3.1f + k * 1.3f) > 0f) {
                c.drawRect(x, y, x + 2f * s, y + s, this.bgP);
                this.bgP.setColor(bgShade(col, 0.6f));
                this.bgP.setAlpha(a);
                c.drawRect(x + 2f * s, y + s * 0.5f, x + 2.5f * s, y + s, this.bgP);
            } else {
                c.drawRect(x + s * 0.5f, y - s * 0.5f, x + s * 1.5f, y + s * 1.5f, this.bgP);
            }
        }
    }

    void bgDrawCave(Canvas c, float t, float u, float sky) {
        float W = this.W;
        float G = this.groundY;
        // crystal motes rising
        float span = sky + 4f * u;
        for (int k = 0; k < 24; k++) {
            float sp = (2.5f + (k % 5) * 1.1f) * u;
            float d = bgWrap(k * 0.137f * span + t * sp, span);
            float y = G + 2f * u - d;
            float x = bgWrap(bgHash(k * 23 + 3) * W + bgSin(t * 0.7f + k * 1.3f) * 4f * u, W);
            float a = bgSin(d / span * 3.1415927f);
            float tw = 0.6f + 0.4f * bgSin(t * 3.3f + k * 1.7f);
            int col = bgMoteCol[k % 4];
            if (k % 3 == 0) {
                bgGlow(c, x, y, u * 2.4f, col, 80f * a * tw);
                bgPixel(c, x - u * 0.5f, y - u * 0.5f, u, col, bgAl(255f * a * tw));
                if (tw > 0.96f) bgSparkle(c, x - u * 0.5f, y - u * 0.5f, u, 0xFFFFFFFF, bgAl(160f * a));
            } else {
                bgPixel(c, x - u * 0.3f, y - u * 0.3f, u * 0.6f, col, bgAl(190f * a * tw));
            }
        }
        // water dripping from the stalactites, splashing on the floor
        float top = this.bgTop;
        for (int k = 0; k < 4; k++) {
            float x = (float) Math.floor((0.12f + 0.25f * k + 0.05f * bgHash(k + 40)) * W / u) * u;
            float y0 = Math.max(this.battleT + u, top + (10f + 4f * bgHash(k + 50)) * u);
            float per = 3.4f + 0.9f * k;
            float p = bgWrap(t + k * 1.1f, per) / per;
            if (p < 0.45f) {
                float g = p / 0.45f;
                float s = u * (0.3f + 0.6f * g);
                bgPixel(c, x + (u - s) * 0.5f, y0, s, 0xFF9CECFF, bgAl(210f * g));
            } else if (p < 0.72f) {
                float f = (p - 0.45f) / 0.27f;
                float y = y0 + (G - u - y0) * f * f;
                this.bgP.setColor(0xFF9CECFF);
                this.bgP.setAlpha(90);
                c.drawRect(x + u * 0.3f, y - 1.6f * u, x + u * 0.7f, y, this.bgP);
                bgPixel(c, x + u * 0.1f, y, u * 0.8f, 0xFFDCF8FF, 220);
            } else if (p < 0.88f) {
                float f = (p - 0.72f) / 0.16f;
                int a = bgAl(210f * (1f - f));
                for (int j = 0; j < 2; j++) {
                    float off = (1.2f + j * 1.4f) * f * 2f * u;
                    float sy = G - u - bgSin(f * 3.1415927f) * (1.5f + j) * u;
                    bgPixel(c, x - off, sy, u * 0.5f, 0xFF9CECFF, a);
                    bgPixel(c, x + u * 0.5f + off, sy, u * 0.5f, 0xFF9CECFF, a);
                }
                this.bgLineP.setStyle(Paint.Style.STROKE);
                this.bgLineP.setStrokeWidth(u * 0.3f);
                this.bgLineP.setColor(0xFF9CECFF);
                this.bgLineP.setAlpha(bgAl(150f * (1f - f)));
                float rw = (1f + 4f * f) * u;
                this.bgR.set(x + u * 0.5f - rw, G - u * 0.2f - rw * 0.25f, x + u * 0.5f + rw, G - u * 0.2f + rw * 0.25f);
                c.drawOval(this.bgR, this.bgLineP);
            }
        }
    }

    void bgDrawCastle(Canvas c, float t, float u, float sky) {
        float W = this.W;
        float G = this.groundY;
        // embers rising from the battlefield
        for (int k = 0; k < 22; k++) {
            float sp = (5f + (k % 4) * 2.5f) * u;
            float d = bgWrap(0.083f * k * sky + t * sp, sky);
            float y = G - d;
            float x = bgWrap(0.113f * k * W + bgSin(t * 1.3f + k) * 5f * u + bgSin(t * 0.37f + k * 2f) * 8f * u, W);
            float a = (1f - d / sky) * (0.6f + 0.4f * bgSin(t * 9f + k));
            int col = bgEmberCol[k % 3];
            if (k % 2 == 0) bgGlow(c, x, y, u * 2f, 0xFFFF6A3A, 90f * a);
            bgPixel(c, x - u * 0.4f, y - u * 0.4f, u * 0.8f, col, bgAl(240f * a));
        }
        // ash drifting down
        for (int k = 0; k < 10; k++) {
            float sp = (3f + (k % 3)) * u;
            float d = bgWrap(k * 0.29f * sky + t * sp, sky);
            float y = this.battleT + d;
            float x = bgWrap(bgHash(k * 41) * W + bgSin(t * 0.9f + k * 1.7f) * 6f * u - t * 2f * u, W);
            int a = bgAl(120f * bgSin(d / sky * 3.1415927f));
            this.bgP.setColor(0xFF8E686D);
            this.bgP.setAlpha(a);
            float s = u * 0.6f;
            if (bgSin(t * 2f + k) > 0f) c.drawRect(x, y, x + 1.6f * s, y + s, this.bgP);
            else c.drawRect(x, y, x + s, y + 1.6f * s, this.bgP);
        }
        // bats flapping across the blood moon
        Paint q = this.bgP;
        float top = this.bgTop;
        for (int k = 0; k < 3; k++) {
            float s = (0.55f + 0.2f * k) * u;
            float span = W + 16f * s;
            float x = bgWrap(bgHash(k * 53) * span + t * (9f + 4f * k) * u * (k == 1 ? -1f : 1f), span) - 8f * s;
            float y = top + (18f + 11f * k) * u + bgSin(t * 2.3f + k * 2f) * 2.5f * u + bgSin(t * 0.6f + k) * 4f * u;
            if (y < this.battleT) continue;
            boolean up = ((int) bgWrap(t * 9f + k * 3f, 2f)) == 0;
            q.setColor(0xFF0A0408);
            q.setAlpha(235);
            c.drawRect(x - s, y - s, x + s, y + s, q);
            c.drawRect(x - s, y - 2f * s, x - s * 0.4f, y - s, q);
            c.drawRect(x + s * 0.4f, y - 2f * s, x + s, y - s, q);
            if (up) {
                c.drawRect(x - 3f * s, y - 2f * s, x - s, y, q);
                c.drawRect(x + s, y - 2f * s, x + 3f * s, y, q);
                c.drawRect(x - 5f * s, y - 3f * s, x - 3f * s, y - s, q);
                c.drawRect(x + 3f * s, y - 3f * s, x + 5f * s, y - s, q);
            } else {
                c.drawRect(x - 3f * s, y, x - s, y + s, q);
                c.drawRect(x + s, y, x + 3f * s, y + s, q);
                c.drawRect(x - 5f * s, y + s, x - 3f * s, y + 2f * s, q);
                c.drawRect(x + 3f * s, y + s, x + 5f * s, y + 2f * s, q);
            }
        }
    }

    // frenzy: hot sparks shooting up from the battlefield
    void bgDrawHeat(Canvas c, float t, float u, float sky) {
        float W = this.W;
        float G = this.groundY;
        float fz = this.bgFz;
        for (int k = 0; k < 14; k++) {
            float sp = (14f + (k % 4) * 6f) * u;
            float d = bgWrap(k * 0.23f * sky + t * sp, sky);
            float y = G - d;
            float x = bgWrap(bgHash(k * 29 + 11) * W + bgSin(t * 2.1f + k) * 3f * u, W);
            float a = fz * (1f - d / sky);
            int col = k % 2 == 0 ? 0xFFFFD35A : 0xFFFF7A2A;
            if (k % 3 == 0) bgGlow(c, x, y, u * 2.2f, col, 110f * a);
            this.bgP.setColor(col);
            this.bgP.setAlpha(bgAl(255f * a));
            c.drawRect(x - u * 0.35f, y - u * 1.4f, x + u * 0.35f, y, this.bgP);
        }
    }

    void bgDrawMist(Canvas c, int bi, float t, float u) {
        float W = this.W;
        float G = this.groundY;
        Paint q = this.bgP;
        int col = bgMistCol[bi];
        float base = bi == 0 ? 30f : bi == 1 ? 34f : 40f;
        for (int k = 0; k < 7; k++) {
            float w = (24f + 14f * bgHash(k * 3 + bi)) * u;
            float sp = (1.5f + k * 0.6f) * u * (k % 2 == 0 ? 1f : -1f);
            float x = bgWrap(bgHash(k * 11 + bi * 5) * W + t * sp, W + w) - w;
            float cy = G - (0.5f + 2.5f * bgHash(k * 17 + 3)) * u;
            q.setColor(col);
            q.setAlpha(bgAl(base * (0.7f + 0.3f * bgSin(t * 0.5f + k))));
            c.drawRect(x, cy - u, x + w, cy + u, q);
            c.drawRect(x + 0.15f * w, cy - 2.5f * u, x + 0.85f * w, cy - u, q);
            c.drawRect(x + 0.32f * w, cy - 3.5f * u, x + 0.68f * w, cy - 2.5f * u, q);
        }
    }

    void drawAmbient(Canvas c) {
        float u = this.bgS;
        if (this.W <= 0 || this.H <= 0 || !(u > 0f)) return;
        int bi = biome();
        if (this.bgKeyBi != bi) bgEnsure(this.bmBg[bi], bi);
        float t = this.time;
        float sky = Math.max(u, this.groundY - this.battleT);
        bgDrawMoon(c, bi, t);
        bgDrawSky(c, bi, t, u);
        bgDrawClouds(c, bi, t, u);
        c.drawRect(0f, this.bgHazeT, this.W, this.bgHazeB, this.bgHazeP);
        bgDrawShafts(c, bi, t);
        bgDrawSpots(c, bi, t, u);
        if (bi == 0) {
            bgDrawForest(c, t, u, sky);
        } else if (bi == 1) {
            bgDrawCave(c, t, u, sky);
        } else {
            bgDrawCastle(c, t, u, sky);
        }
        if (this.bgFz > 0.01f) bgDrawHeat(c, t, u, sky);
        c.drawRect(0f, this.groundY + 2f * u, this.W, this.battleB, this.bgGroundP);
        this.bgRimP.setAlpha(bgAl(255f * (0.7f + 0.3f * bgSin(t * 0.8f))));
        c.drawRect(0f, this.groundY - u, this.W, this.groundY, this.bgRimP);
        bgDrawMist(c, bi, t, u);
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
        bgDrawBackdrop(canvas, bitmap, j, z);
        int i4 = this.compSel;
        if (i4 >= 0 && compUnlocked(i4)) {
            drawPrincess(canvas);
        }
        drawHero(canvas);
        drawFamiliar(canvas);
        drawMonster(canvas);
        drawWeakSpot(canvas);
        if (this.overlay == 6) {
            drawDeath(canvas);
        }
        drawBeatRing(canvas);
        drawIsekai(canvas);
        drawPrincessBubble(canvas);
        Iterator<Fx> it = this.famShots.iterator();
        while (it.hasNext()) {
            Fx next = it.next();
            if (next.max <= 0.0f) {
                continue;
            }
            float f3 = next.life / next.max;
            float famX = famX();
            float famY = famY();
            float fxHu = this.hu;
            // comet trail: fading, shrinking glow beads behind the shot
            for (int fxK = 6; fxK >= 1; fxK--) {
                float fxT = f3 - (fxK * 0.055f);
                if (fxT > 0.0f) {
                    float fxBx = famX + ((this.mobX - famX) * fxT);
                    float fxBy = famY + ((this.mobY - famY) * fxT) - (((float) Math.sin(fxT * 3.141592653589793d)) * fxHu * 3.0f);
                    float fxFd = 1.0f - (fxK / 7.0f);
                    this.fxP.setColor(-10829313);
                    this.fxP.setAlpha((int) (90.0f * fxFd));
                    canvas.drawCircle(fxBx, fxBy, fxHu * ((1.4f * fxFd) + 0.6f), this.fxP);
                    this.fxP.setColor(-2033409);
                    this.fxP.setAlpha((int) (200.0f * fxFd));
                    float fxQ = fxHu * 0.45f * fxFd;
                    canvas.drawRect(fxBx - fxQ, fxBy - fxQ, fxBx + fxQ, fxBy + fxQ, this.fxP);
                }
            }
            float f4 = famX + ((this.mobX - famX) * f3);
            float f5 = famY + ((this.mobY - famY) * f3) - (((float) Math.sin(f3 * 3.141592653589793d)) * fxHu * 3.0f);
            float fxPl = (float) ((Math.sin((this.time * 22.0f) + next.max) * 0.5d) + 0.5d);
            this.fxP.setColor(1717228031);
            canvas.drawCircle(f4, f5, fxHu * ((0.6f * fxPl) + 3.0f), this.fxP);
            this.fxP.setColor(-5517825);
            this.fxP.setAlpha(150);
            canvas.drawCircle(f4, f5, fxHu * 1.8f, this.fxP);
            // spinning crystal core
            canvas.save();
            canvas.rotate(this.time * 540.0f, f4, f5);
            this.fxP.setColor(-2033409);
            float f6 = fxHu * 1.15f;
            canvas.drawRect(f4 - f6, f5 - f6, f4 + f6, f5 + f6, this.fxP);
            this.fxP.setColor(-1);
            float fxC = fxHu * 0.5f;
            canvas.drawRect(f4 - fxC, f5 - fxC, f4 + fxC, f5 + fxC, this.fxP);
            canvas.restore();
            // orbiting twinkles
            for (int fxO = OV_NONE; fxO < 3; fxO++) {
                double fxAn = (this.time * 9.0f) + (fxO * 2.0943951023931953d);
                float fxOx = f4 + (((float) Math.cos(fxAn)) * fxHu * 2.6f);
                float fxOy = f5 + (((float) Math.sin(fxAn)) * fxHu * 2.6f);
                this.fxP.setColor(-2033409);
                this.fxP.setAlpha(210);
                float fxOs = fxHu * 0.35f;
                canvas.drawRect(fxOx - fxOs, fxOy - fxOs, fxOx + fxOs, fxOy + fxOs, this.fxP);
            }
            // arrival flash at the monster
            if (f3 > 0.8f) {
                float fxIm = (f3 - 0.8f) / 0.2f;
                this.fxS.setColor(-5517825);
                this.fxS.setAlpha((int) (220.0f * (1.0f - (fxIm * 0.6f))));
                this.fxS.setStrokeWidth((fxHu * 0.8f) + 1.0f);
                canvas.drawCircle(this.mobX, this.mobY, fxHu * ((5.0f * fxIm) + 1.5f), this.fxS);
                this.fxP.setColor(-1);
                this.fxP.setAlpha((int) (150.0f * fxIm));
                canvas.drawCircle(this.mobX, this.mobY, fxHu * 2.2f * fxIm, this.fxP);
            }
        }
        Iterator<Fx> it2 = this.bolts.iterator();
        while (it2.hasNext()) {
            Fx next2 = it2.next();
            if (next2.max <= 0.0f) {
                continue;
            }
            float f7 = next2.life / next2.max;
            float f8 = this.heroX;
            float f9 = this.hu;
            float f10 = f8 + (13.0f * f9);
            float f11 = this.groundY - (f9 * 26.0f);
            // tapered fire trail: soft orange halo + hot yellow core beads
            for (int fxK2 = 7; fxK2 >= 1; fxK2--) {
                float f13 = f7 - (0.045f * fxK2);
                if (f13 > 0.0f) {
                    float f14 = ((this.mobX - f10) * f13) + f10;
                    float sin = (f11 + ((this.mobY - f11) * f13)) - ((((float) Math.sin(f13 * 3.141592653589793d)) * this.mobR) * 0.6f);
                    float fxFd2 = 1.0f - (fxK2 / 8.0f);
                    this.fxP.setColor(-34243);
                    this.fxP.setAlpha((int) (95.0f * fxFd2));
                    canvas.drawCircle(f14, sin, f9 * ((2.6f * fxFd2) + 0.8f), this.fxP);
                    this.fxP.setColor(-5720);
                    this.fxP.setAlpha((int) (190.0f * fxFd2));
                    canvas.drawCircle(f14, sin, f9 * ((1.3f * fxFd2) + 0.3f), this.fxP);
                    if (fxK2 <= 3) {
                        // flickering pixel embers drifting off the trail
                        float fxEj = ((float) Math.sin((this.time * 47.0f) + (fxK2 * 1.7f))) * f9 * 1.6f;
                        float f15 = f9 * (1.1f - (fxK2 * 0.22f));
                        this.fxP.setColor(fxK2 == 1 ? -11430 : fxK2 == 2 ? -26051 : -2076614);
                        this.fxP.setAlpha(230 - (fxK2 * 40));
                        canvas.drawRect(f14 - f15, (sin + fxEj) - f15, f14 + f15, sin + fxEj + f15, this.fxP);
                    }
                }
            }
        }
        Iterator<Fx> it3 = this.bolts.iterator();
        while (it3.hasNext()) {
            Fx next3 = it3.next();
            if (next3.max <= 0.0f) {
                continue;
            }
            float f16 = next3.life / next3.max;
            float f17 = this.heroX;
            float f18 = this.hu;
            float f19 = f17 + (13.0f * f18);
            float f20 = this.groundY - (f18 * 26.0f);
            float f21 = f19 + ((this.mobX - f19) * f16);
            float sin2 = (f20 + ((this.mobY - f20) * f16)) - ((((float) Math.sin(f16 * 3.141592653589793d)) * this.mobR) * 0.6f);
            float fxFl = (float) ((Math.sin(this.time * 31.0f) * 0.5d) + 0.5d);
            // layered additive-looking glow around the fireball
            this.fxP.setColor(1157593661);
            canvas.drawCircle(f21, sin2, f18 * ((0.8f * fxFl) + 5.6f), this.fxP);
            this.fxP.setColor(-26051);
            this.fxP.setAlpha(90);
            canvas.drawCircle(f21, sin2, f18 * 3.6f, this.fxP);
            this.fxP.setColor(-5720);
            this.fxP.setAlpha(150);
            canvas.drawCircle(f21, sin2, f18 * ((0.4f * fxFl) + 2.0f), this.fxP);
            Bitmap bitmap2 = this.bmFire;
            if (bitmap2 != null) {
                float height = bitmap2.getHeight();
                float f22 = this.hu;
                blit(canvas, bitmap2, f21, sin2 + (height * f22 * 0.42f), f22 * 0.85f, 1.0f, 1.0f, this.bp);
            }
            // pre-impact flare on the monster
            if (f16 > 0.7f) {
                float fxIm2 = (f16 - 0.7f) / 0.3f;
                this.fxP.setColor(-11430);
                this.fxP.setAlpha((int) (120.0f * fxIm2));
                canvas.drawCircle(this.mobX, this.mobY, f18 * ((3.0f * fxIm2) + 2.0f), this.fxP);
            }
        }
        if (this.fairyOn) {
            drawFairy(canvas);
        }
        float f23 = 1.0f;
        if (this.scrollOn) {
            float scrollX = scrollX();
            float scrollY = scrollY();
            float f24 = (this.beatPulse * 0.18f) + 1.0f;
            // challenge scroll: rotating god-rays + cached radial aura + orbiting sparkles
            if (this.minisScrollSh == null || this.minisScrollKey != this.pad) {
                this.minisScrollKey = this.pad;
                this.minisScrollSh = new RadialGradient(0.0f, 0.0f, Math.max(1.0f, this.pad * 8.0f), new int[]{-1426072065, 1442827775, 16769279}, new float[]{0.0f, 0.45f, 1.0f}, Shader.TileMode.CLAMP);
            }
            minisRays(canvas, scrollX, scrollY, this.pad * 2.0f, this.pad * 9.0f * f24, 12, this.time * 0.7f, 0.42f, -2045697, (this.beatPulse * 50.0f) + 55.0f);
            minisRays(canvas, scrollX, scrollY, this.pad * 2.0f, this.pad * 6.5f * f24, 8, -this.time * 1.1f, 0.3f, -5720, (this.beatPulse * 40.0f) + 60.0f);
            canvas.save();
            canvas.translate(scrollX, scrollY);
            this.minisG.setStyle(Paint.Style.FILL);
            this.minisG.setShader(this.minisScrollSh);
            this.minisG.setAlpha(minisA((this.beatPulse * 80.0f) + 175.0f));
            canvas.drawCircle(0.0f, 0.0f, this.pad * 8.0f, this.minisG);
            this.minisG.setShader(null);
            this.minisG.setAlpha(255);
            canvas.restore();
            for (int minisK = 0; minisK < 5; minisK++) {
                double minisAng = (this.time * 1.6f) + (minisK * 1.2566370614359172d);
                float minisTw = ((float) Math.sin((this.time * 8.0f) + (minisK * 1.3f))) * 0.5f + 0.5f;
                minisStar(canvas, scrollX + (((float) Math.cos(minisAng)) * this.pad * 5.2f), scrollY + (((float) Math.sin(minisAng)) * this.pad * 3.6f), this.hu * ((minisTw * 1.2f) + 0.6f), minisSparkCols[minisK % 4], (minisTw * 140.0f) + 100.0f);
            }
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
            // the scroll sways like it is floating on magic
            canvas.save();
            canvas.rotate(((float) Math.sin(this.time * 2.3f)) * 9.0f, scrollX, scrollY);
            canvas.drawText("📜", scrollX, (this.pad * 1.6f) + scrollY, this.tp);
            canvas.restore();
            this.tp.setTypeface(this.pixTf);
            this.tp.setTextSize(this.pad * 1.1f);
            // plaque behind the label with a shimmering border
            float f25 = this.pad;
            float minisLw = this.tp.measureText("ИСПЫТАНИЕ!") * 0.5f + f25 * 0.9f;
            this.minisR.set(scrollX - minisLw, (f25 * 4.9f) + scrollY, scrollX + minisLw, (f25 * 6.75f) + scrollY);
            this.minisP.setStyle(Paint.Style.FILL);
            this.minisP.setColor(-15331286);
            this.minisP.setAlpha(190);
            canvas.drawRoundRect(this.minisR, f25 * 0.5f, f25 * 0.5f, this.minisP);
            this.minisP.setStyle(Paint.Style.STROKE);
            this.minisP.setStrokeWidth(Math.max(1.0f, this.hu * 0.6f));
            this.minisP.setColor(-2045697);
            this.minisP.setAlpha(minisA((((float) Math.sin(this.time * 6.0f)) * 70.0f) + 170.0f));
            canvas.drawRoundRect(this.minisR, f25 * 0.5f, f25 * 0.5f, this.minisP);
            this.minisP.setStyle(Paint.Style.FILL);
            this.tp.setColor(-15331286);
            canvas.drawText("ИСПЫТАНИЕ!", (0.15f * f25) + scrollX, (f25 * 6.35f) + scrollY, this.tp);
            this.tp.setColor(-2045697);
            canvas.drawText("ИСПЫТАНИЕ!", scrollX, scrollY + (this.pad * 6.2f), this.tp);
            // glint sweeping across the label
            float minisGl = ((this.time * 0.6f) % 1.0f) * 2.0f - 0.5f;
            if (minisGl >= 0.0f && minisGl <= 1.0f) {
                minisStar(canvas, (this.minisR.left + (this.minisR.width() * minisGl)), this.minisR.top + (f25 * 0.3f), this.hu * 1.6f, -1, 230.0f);
            }
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
            if (next5.max <= 0.0f) {
                continue;
            }
            float f27 = next5.life / next5.max;
            float fxCa = f27 < 0.65f ? 1.0f : Math.max(0.0f, (1.0f - f27) / 0.35f);
            float f28 = next5.size * 0.5f;
            // contact shadow grows as the chunk nears the ground
            float fxHg = this.groundY - next5.y;
            float fxSr = next5.size * 5.0f;
            if (fxHg < fxSr && fxHg > -next5.size) {
                float fxNs = 1.0f - Math.max(0.0f, fxHg / fxSr);
                this.fxP.setColor(-16777216);
                this.fxP.setAlpha((int) (90.0f * fxCa * fxNs));
                float fxSw = f28 * ((0.6f * fxNs) + 0.7f);
                canvas.drawRect(next5.x - fxSw, this.groundY - (f28 * 0.3f), next5.x + fxSw, this.groundY + (f28 * 0.3f), this.fxP);
            }
            // white-hot at the moment of shattering, cooling into the sprite colour
            int fxCc = next5.life < 0.14f ? fxMix(next5.color, -1, 1.0f - (next5.life / 0.14f)) : next5.color;
            this.fxP.setColor(fxCc);
            this.fxP.setAlpha((int) (fxCa * 255.0f));
            canvas.drawRect(next5.x - f28, next5.y - f28, next5.x + f28, next5.y + f28, this.fxP);
            // pixel bevel: darker lower edge for volume
            this.fxP.setColor(fxShade(fxCc, 0.6f));
            this.fxP.setAlpha((int) (fxCa * 255.0f));
            canvas.drawRect(next5.x - f28, next5.y + (f28 * 0.4f), next5.x + f28, next5.y + f28, this.fxP);
        }
        fxDrawSparks(canvas);
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
            float f34 = ((f30 - f31) * 0.18f) / 2.0f;
            float f35 = f32 - f34;
            float f36 = f32 + f34;
            boolean hudTw = this.towerFight;
            int hudAcc = hudTw ? -24512 : -2078616;
            if (this.hudBandSh == null || this.hudBandK0 != f35 || this.hudBandK1 != f36 || this.hudBandTower != hudTw) {
                int hudEdge = hudMix(0xEE16102A, hudAcc | 0xEE000000, 0.4f);
                this.hudBandSh = new android.graphics.LinearGradient(0.0f, f35, 0.0f, Math.max(f35 + 1.0f, f36), new int[]{hudEdge, 0xEE0E0A1C, 0xF0080612, 0xEE0E0A1C, hudEdge}, new float[]{0.0f, 0.28f, 0.5f, 0.72f, 1.0f}, Shader.TileMode.CLAMP);
                this.hudBandK0 = f35;
                this.hudBandK1 = f36;
                this.hudBandTower = hudTw;
            }
            this.hudFill.setStyle(Paint.Style.FILL);
            this.hudFill.setShader(this.hudBandSh);
            this.hudFill.setColor(-1);
            this.hudFill.setAlpha((int) (225.0f * min2));
            canvas.drawRect(0.0f, f35, this.W, f36, this.hudFill);
            this.hudFill.setShader(null);
            // outer glow just beyond the band edges
            this.hudFill.setColor(hudAcc);
            this.hudFill.setAlpha((int) (70.0f * min2));
            canvas.drawRect(0.0f, f35 - (this.pad * 0.5f), this.W, f35, this.hudFill);
            canvas.drawRect(0.0f, f36, this.W, f36 + (this.pad * 0.5f), this.hudFill);
            this.hudFill.setAlpha((int) (35.0f * min2));
            canvas.drawRect(0.0f, f35 - this.pad, this.W, f35 - (this.pad * 0.5f), this.hudFill);
            canvas.drawRect(0.0f, f36 + (this.pad * 0.5f), this.W, f36 + this.pad, this.hudFill);
            // white entry flash
            if (f29 < 0.22f) {
                this.hudFill.setColor(-1);
                this.hudFill.setAlpha((int) ((1.0f - (f29 / 0.22f)) * 200.0f));
                canvas.drawRect(0.0f, f35, this.W, f36, this.hudFill);
            }
            this.p.setColor(hudAcc);
            int i6 = (int) (min2 * 255.0f);
            this.p.setAlpha(i6);
            canvas.drawRect(-f33, f35, this.W - f33, f35 + (this.pad * 0.5f), this.p);
            canvas.drawRect(f33, f36 - (this.pad * 0.5f), this.W + f33, f36, this.p);
            // light pixel line on the rails
            this.p.setColor(-1);
            this.p.setAlpha((int) (120.0f * min2));
            canvas.drawRect(-f33, f35, this.W - f33, f35 + Math.max(1.0f, this.pad * 0.15f), this.p);
            canvas.drawRect(f33, f36 - (this.pad * 0.5f), this.W + f33, (f36 - (this.pad * 0.5f)) + Math.max(1.0f, this.pad * 0.15f), this.p);
            this.p.setColor(hudAcc);
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
            String str = hudTw ? "СТРАЖ!" : "БОСС!";
            float f40 = f33 * 0.5f;
            float f41 = this.pad;
            float hudTx = (this.W / 2.0f) + f40;
            float hudTy = f32 + (f41 * 0.3f);
            float hudPunch = 1.0f + (0.9f * (1.0f - min) * (1.0f - min)) + (((float) Math.sin(this.time * 14.0f)) * 0.025f * min2);
            canvas.save();
            canvas.scale(hudPunch, hudPunch, hudTx, hudTy - (f41 * 1.2f));
            // chunky pixel outline
            float hudO = Math.max(1.0f, f41 * 0.22f);
            this.tp.setColor(-15331286);
            this.tp.setAlpha(i6);
            canvas.drawText(str, hudTx + (f41 * 0.3f), hudTy + (f41 * 0.3f) + (f41 * 0.3f), this.tp);
            canvas.drawText(str, hudTx - hudO, hudTy, this.tp);
            canvas.drawText(str, hudTx + hudO, hudTy, this.tp);
            canvas.drawText(str, hudTx, hudTy - hudO, this.tp);
            canvas.drawText(str, hudTx, hudTy + hudO, this.tp);
            int hudTc = hudTw ? -16310 : -42390;
            if (((int) (this.time * 10.0f)) % 4 == 0 && f29 < 1.0f) {
                hudTc = hudMix(hudTc, -1, 0.55f);
            }
            this.tp.setColor(hudTc);
            this.tp.setAlpha(i6);
            canvas.drawText(str, hudTx, hudTy, this.tp);
            canvas.restore();
            this.tp.setTypeface(Typeface.DEFAULT);
            this.tp.setTextSize(this.pad * 1.7f);
            String mobName = mobName();
            int i9 = this.W;
            float f42 = this.pad;
            hudFitText(canvas, mobName, (i9 / 2.0f) - f40, f36 - (f42 * IMPACT), i9 - (f42 * 4.0f), -1, i6);
            this.tp.setAlpha(255);
        }
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setFakeBoldText(false);
        this.tp.setTypeface(this.pixTf);
        Iterator<Fx> it6 = this.floats.iterator();
        this.fxTxt.setTypeface(this.pixTf);
        this.fxTxtO.setTypeface(this.pixTf);
        this.fxTxt.setTextAlign(Paint.Align.CENTER);
        this.fxTxtO.setTextAlign(Paint.Align.CENTER);
        while (it6.hasNext()) {
            Fx next6 = it6.next();
            if (next6.max <= 0.0f || next6.text == null) {
                continue;
            }
            float f43 = fxClamp(next6.life / next6.max);
            int fxCol = next6.color;
            boolean fxCrit = fxCol == -42406 || fxCol == -26051;
            boolean fxGold = fxCol == -11430 || fxCol == -16310 || fxCol == -10496;
            boolean fxBig = fxCrit || next6.size > this.pad * 2.6f;
            // pop-in with overshoot, then a gentle shrink as it fades
            float fxPop = fxBack(f43 / (fxBig ? 0.2f : 0.15f));
            float fxSc = ((0.65f * fxPop) + 0.4f) * (1.0f - (Math.max(0.0f, f43 - 0.65f) * 0.35f));
            float fxTs = next6.size * (fxCrit ? 0.74f : 0.64f) * fxSc;
            if (fxTs < 1.0f) {
                continue;
            }
            // eased rise: fast launch, soft float at the top
            float fxY0 = next6.y - (next6.vy * next6.life);
            float fxY = fxY0 + (next6.vy * next6.max * fxOut(f43) * 1.15f);
            float fxX = next6.x;
            if (fxBig && f43 < 0.3f) {
                fxX += ((float) Math.sin(next6.life * 70.0f)) * this.pad * 0.3f * (1.0f - (f43 / 0.3f));
            }
            int min3 = (int) (Math.min(1.0f, (1.0f - f43) * 2.4f) * Math.min(1.0f, f43 / 0.04f + 0.3f) * 255.0f);
            // starburst flash behind crits
            if (fxCrit && f43 < 0.35f) {
                float fxFb = 1.0f - (f43 / 0.35f);
                float fxRc = fxY - (fxTs * 0.35f);
                this.fxS.setColor(-5720);
                this.fxS.setAlpha((int) (170.0f * fxFb));
                this.fxS.setStrokeWidth((this.hu * 0.9f) + 1.0f);
                for (int fxR8 = OV_NONE; fxR8 < 8; fxR8++) {
                    double fxAn = (fxR8 * 0.7853981633974483d) + (next6.life * 3.0f);
                    float fxL0 = fxTs * 0.5f;
                    float fxL1 = fxTs * ((1.4f * fxPop) + (fxR8 % 2 == 0 ? 0.6f : 0.1f));
                    float fxCo = (float) Math.cos(fxAn);
                    float fxSi = (float) Math.sin(fxAn);
                    canvas.drawLine(fxX + (fxCo * fxL0), fxRc + (fxSi * fxL0 * 0.6f), fxX + (fxCo * fxL1), fxRc + (fxSi * fxL1 * 0.6f), this.fxS);
                }
            }
            boolean fxTilt = fxBig && f43 < 0.4f;
            if (fxTilt) {
                canvas.save();
                canvas.rotate((((int) (next6.max * 1000.0f + next6.x)) % 2 == 0 ? -7.0f : 7.0f) * (1.0f - (f43 / 0.4f)), fxX, fxY);
            }
            this.fxTxt.setTextSize(fxTs);
            this.fxTxtO.setTextSize(fxTs);
            // drop shadow
            this.fxTxt.setColor(-15331286);
            this.fxTxt.setAlpha((min3 * 3) / 5);
            float fxSh = Math.max(1.0f, fxTs * 0.08f);
            canvas.drawText(next6.text, fxX + fxSh, fxY + (fxSh * 1.3f), this.fxTxt);
            // dark outline + fill (banded gradient for crits / gold)
            this.fxTxtO.setColor(-15331286);
            this.fxTxtO.setAlpha(min3);
            this.fxTxtO.setStrokeWidth(Math.max(1.5f, fxTs * 0.16f));
            this.fxTxt.setColor(fxCol);
            this.fxTxt.setAlpha(min3);
            fxFloatText(canvas, next6.text, fxX, fxY, fxCrit ? 1 : (fxGold ? 2 : 0));
            if (fxTilt) {
                canvas.restore();
            }
        }
        this.tp.setTypeface(Typeface.DEFAULT);
        this.tp.setAlpha(255);
        this.tp.setAlpha(255);
        this.tp.setFakeBoldText(false);
        canvas.restore();
        if (this.farming) {
            float hudBs = (float) ((Math.sin(this.time * 4.0f) * 0.5d) + 0.5d);
            RectF rectF = this.bossBtn;
            float f44 = this.pad;
            hudGlow(canvas, rectF, f44, -46502, (0.5f * hudBs) + 0.35f);
            hudPing(canvas, rectF, f44, -42390, this.time * 0.9f);
            hudPanel(canvas, rectF, f44, Color.rgb((int) ((hudBs * BF_BOSS_MAX) + 150.0f), 50, 60));
            hudShine(canvas, rectF, f44, (this.time % 2.2f) / 0.9f, 80);
            // tiny danger pips in the corners
            hudSparkle(canvas, rectF.left + (f44 * 1.0f), rectF.top + (f44 * 1.0f), f44 * 0.35f, -11430, (int) (hudBs * 230.0f));
            hudSparkle(canvas, rectF.right - (f44 * 1.0f), rectF.bottom - (f44 * 1.1f), f44 * 0.35f, -11430, (int) ((1.0f - hudBs) * 230.0f));
            this.tp.setTextSize(this.pad * 1.8f * ((0.05f * hudBs) + 1.0f));
            this.tp.setFakeBoldText(true);
            hudText(canvas, "⚔ К боссу", this.bossBtn.centerX(), this.bossBtn.centerY() + (this.pad * 0.65f), -1, 255);
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
            RectF rectF3 = this.towerBtn;
            float f46 = this.pad;
            float hudTs = (float) ((Math.sin(this.time * 4.0f) * 0.5d) + 0.5d);
            if (z2) {
                hudGlow(canvas, rectF3, f46, -24512, (0.5f * ((float) ((Math.sin(this.time * 9.0f) * 0.5d) + 0.5d))) + 0.3f);
            } else if (this.towerFloor == 0) {
                hudGlow(canvas, rectF3, f46, -16310, (0.45f * hudTs) + 0.25f);
                hudPing(canvas, rectF3, f46, -16310, (this.time * 0.7f) + 0.3f);
            }
            hudPanel(canvas, rectF3, f46, z2 ? -863348198 : this.towerFloor == 0 ? Color.rgb((int) ((Math.sin(this.time * 4.0f) * 50.0d) + 120.0d), 80, 40) : -869654456);
            if (!z2 && this.towerFloor == 0) {
                hudShine(canvas, rectF3, f46, ((this.time + 0.7f) % 2.8f) / 1.0f, 60);
            }
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setFakeBoldText(true);
            hudFitText(canvas, z2 ? "🏰 бой" : "🏰 " + Math.min(this.towerFloor, 999), this.towerBtn.centerX(), this.towerBtn.centerY() + (this.pad * 0.6f), this.towerBtn.width() - this.pad, -1, 255);
            this.tp.setFakeBoldText(false);
        }
        if (hasRelics() || this.hasPhones) {
            RectF rectF4 = this.relicBtn;
            float f47 = this.pad;
            if (this.relicNew) {
                float hudRs = (float) ((Math.sin(this.time * 5.0f) * 0.5d) + 0.5d);
                hudGlow(canvas, rectF4, f47, -10944, (0.5f * hudRs) + 0.3f);
                hudPing(canvas, rectF4, f47, -10944, this.time * 0.8f);
            }
            hudPanel(canvas, rectF4, f47, this.relicNew ? Color.rgb((int) ((Math.sin(this.time * 5.0f) * 60.0d) + 150.0d), 110, 40) : -869654456);
            if (this.relicNew) {
                hudShine(canvas, rectF4, f47, ((this.time + 1.1f) % 2.4f) / 0.9f, 70);
                float hudK1 = (float) Math.max(0.0d, Math.sin(this.time * 6.0f));
                float hudK2 = (float) Math.max(0.0d, Math.sin((this.time * 6.0f) + 2.4f));
                hudSparkle(canvas, rectF4.right - (f47 * 0.9f), rectF4.top + (f47 * 0.9f), f47 * 0.6f * hudK1, -1, (int) (hudK1 * 240.0f));
                hudSparkle(canvas, rectF4.left + (f47 * 1.1f), rectF4.bottom - (f47 * 1.0f), f47 * 0.5f * hudK2, -10944, (int) (hudK2 * 240.0f));
            }
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setFakeBoldText(true);
            int[] iArr = this.rel;
            int length = iArr.length;
            int i10 = OV_NONE;
            for (int i11 = OV_NONE; i11 < length; i11++) {
                i10 += iArr[i11];
            }
            hudFitText(canvas, "🎒 Реликвии " + i10, this.relicBtn.centerX(), this.relicBtn.centerY() + (this.pad * 0.6f), this.relicBtn.width() - this.pad, -1, 255);
            this.tp.setFakeBoldText(false);
        }
        if (this.taps >= 5 || this.overlay != 0) {
            return;
        }
        // tap ripple over the enemy
        float hudRp = (this.time * 1.1f) % 1.0f;
        if (this.mobR > 0.0f) {
            this.hudLine.setStyle(Paint.Style.STROKE);
            this.hudLine.setStrokeWidth(Math.max(1.0f, this.pad * 0.3f * (1.0f - hudRp)));
            this.hudLine.setColor(-1);
            this.hudLine.setAlpha((int) ((1.0f - hudRp) * 150.0f));
            canvas.drawCircle(this.mobX, this.mobY, this.mobR * ((0.9f * hudRp) + 0.5f), this.hudLine);
        }
        float hudBob = ((float) Math.sin(this.time * 4.0f)) * this.pad * 0.45f;
        this.tp.setTextSize(this.pad * 2.4f);
        hudText(canvas, "👆 Тапай по экрану!", this.W / 2.0f, (this.battleB - (this.pad * IMPACT)) + hudBob, i, (int) ((Math.sin(this.time * 5.0f) * 100.0d) + 150.0d));
        this.tp.setAlpha(255);
    }

    // ===== minis: visuals for rhythm fight, tower, minigames, fairy, challenge scroll =====
    final Paint minisP = new Paint(1);
    final Paint minisG = new Paint(1);
    final Paint minisT = new Paint(1);
    final Path minisPath = new Path();
    final RectF minisR = new RectF();
    static final int[] minisLaneCols = {-45149, -10825217, -11430};
    static final int[] minisBeamCols = {-45149, -10825217, -11430, -8592501};
    static final int[] minisEqCols = {-45149, -5213953, -10825217, -8592501, -11430};
    static final int[] minisSparkCols = {-5720, -26051, -10825217, -3416};
    Shader minisBgSh;
    int minisBgKey = -1;
    final Shader[] minisLaneSh = new Shader[3];
    final Shader[] minisBeamSh = new Shader[3];
    int minisLaneKey = -1;
    Shader minisHitSh;
    int minisHitKey = -1;
    Shader minisBarSh;
    int minisBarKey = -1;
    Shader minisFairySh;
    float minisFairyKey = -1.0f;
    Shader minisScrollSh;
    float minisScrollKey = -1.0f;
    Shader minisTowerSh;
    int minisTowerKey = -1;
    Shader minisSkySh;
    int minisSkyKey = -1;

    static float minisHash(int i) {
        int h = (i * 374761393) + 668265263;
        h = (h ^ (h >>> 13)) * 1274126177;
        h ^= h >>> 16;
        return (h & 65535) / 65535.0f;
    }

    static int minisA(float a) {
        if (a <= 0.0f || a != a) {
            return 0;
        }
        return a >= 255.0f ? 255 : (int) a;
    }

    /** Soft layered glow without blur filters: concentric translucent discs. */
    void minisHalo(Canvas c, float x, float y, float r, int col, float a) {
        if (r <= 0.0f || a <= 0.0f) {
            return;
        }
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(col);
        this.minisP.setAlpha(minisA(a * 0.22f));
        c.drawCircle(x, y, r, this.minisP);
        this.minisP.setAlpha(minisA(a * 0.28f));
        c.drawCircle(x, y, r * 0.7f, this.minisP);
        this.minisP.setAlpha(minisA(a * 0.36f));
        c.drawCircle(x, y, r * 0.42f, this.minisP);
    }

    /** Pixel-style four-point twinkle. */
    void minisStar(Canvas c, float x, float y, float r, int col, float a) {
        if (r <= 0.0f || a <= 0.0f) {
            return;
        }
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(col);
        this.minisP.setAlpha(minisA(a));
        float t = Math.max(1.0f, r * 0.26f);
        c.drawRect(x - r, y - t, x + r, y + t, this.minisP);
        c.drawRect(x - t, y - r, x + t, y + r, this.minisP);
        this.minisP.setColor(-1);
        this.minisP.setAlpha(minisA(a));
        c.drawRect(x - t, y - t, x + t, y + t, this.minisP);
    }

    /** Radiating light rays built into the cached path. */
    void minisRays(Canvas c, float x, float y, float r0, float r1, int n, float rot, float width, int col, float a) {
        if (n <= 0 || r1 <= 0.0f || a <= 0.0f) {
            return;
        }
        this.minisPath.reset();
        double half = (width * 3.141592653589793d) / n;
        for (int i = 0; i < n; i++) {
            double ang = rot + ((i * 6.283185307179586d) / n);
            this.minisPath.moveTo(x + (((float) Math.cos(ang)) * r0), y + (((float) Math.sin(ang)) * r0));
            this.minisPath.lineTo(x + (((float) Math.cos(ang - half)) * r1), y + (((float) Math.sin(ang - half)) * r1));
            this.minisPath.lineTo(x + (((float) Math.cos(ang + half)) * r1), y + (((float) Math.sin(ang + half)) * r1));
            this.minisPath.close();
        }
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(col);
        this.minisP.setAlpha(minisA(a));
        c.drawPath(this.minisPath, this.minisP);
    }

    /** Centered pixel-font text with a chunky dark outline and drop shadow. */
    void minisPopText(Canvas c, String s, float x, float y, float size, int col, float a) {
        if (s == null || s.length() == 0 || size <= 0.0f || a <= 0.0f) {
            return;
        }
        this.minisT.setTypeface(this.pixTf);
        this.minisT.setTextAlign(Paint.Align.CENTER);
        this.minisT.setTextSize(size);
        this.minisT.setStyle(Paint.Style.FILL);
        float o = Math.max(1.0f, size * 0.07f);
        this.minisT.setColor(-15331286);
        this.minisT.setAlpha(minisA(a * 0.85f));
        c.drawText(s, (o * 1.7f) + x, (o * 1.7f) + y, this.minisT);
        c.drawText(s, x - o, y, this.minisT);
        c.drawText(s, x + o, y, this.minisT);
        c.drawText(s, x, y - o, this.minisT);
        c.drawText(s, x, y + o, this.minisT);
        this.minisT.setColor(col);
        this.minisT.setAlpha(minisA(a));
        c.drawText(s, x, y, this.minisT);
    }

    /** Glossy bevel: shine strip on top, shade strip at the bottom. */
    void minisGloss(Canvas c, RectF rr, float rad, float a) {
        if (rr.width() <= rad || rr.height() <= 0.0f) {
            return;
        }
        this.minisP.setStyle(Paint.Style.FILL);
        float h = rr.height();
        float in = Math.min(rad * 0.6f, rr.width() * 0.25f);
        this.minisP.setColor(-1);
        this.minisP.setAlpha(minisA(70.0f * a));
        c.drawRect(rr.left + in, (h * 0.1f) + rr.top, rr.right - in, (h * 0.3f) + rr.top, this.minisP);
        this.minisP.setColor(-16777216);
        this.minisP.setAlpha(minisA(60.0f * a));
        c.drawRect(rr.left + in, rr.bottom - (h * 0.24f), rr.right - in, rr.bottom - (h * 0.07f), this.minisP);
    }

    void minisEnsureBeat() {
        int key = (this.H * 31) + this.W;
        if (this.minisBgSh == null || this.minisBgKey != key) {
            this.minisBgKey = key;
            this.minisBgSh = new android.graphics.LinearGradient(0.0f, 0.0f, 0.0f, Math.max(1, this.H), new int[]{-15071704, -14154440, -13432508, -15464400}, new float[]{0.0f, 0.35f, 0.7f, 1.0f}, Shader.TileMode.CLAMP);
        }
        if (this.minisLaneSh[0] == null || this.minisLaneKey != key) {
            this.minisLaneKey = key;
            float top = bfTopY();
            float hit = Math.max(top + 1.0f, bfHitY());
            for (int i = 0; i < 3; i++) {
                int c = minisLaneCols[i] & 16777215;
                this.minisLaneSh[i] = new android.graphics.LinearGradient(0.0f, top, 0.0f, hit, new int[]{c, 838860800 | c, 1828716544 | c}, new float[]{0.0f, 0.7f, 1.0f}, Shader.TileMode.CLAMP);
                this.minisBeamSh[i] = new android.graphics.LinearGradient(0.0f, top, 0.0f, hit, new int[]{c, 1342177280 | c, -1}, new float[]{0.0f, 0.75f, 1.0f}, Shader.TileMode.CLAMP);
            }
        }
        if (this.minisHitSh == null || this.minisHitKey != key) {
            this.minisHitKey = key;
            float hy = bfHitY();
            float band = (this.H * 0.05f) + 1.0f;
            this.minisHitSh = new android.graphics.LinearGradient(0.0f, hy - band, 0.0f, hy + band, new int[]{16777215, 1442840575, 16777215}, (float[]) null, Shader.TileMode.CLAMP);
        }
        if (this.minisBarSh == null || this.minisBarKey != key) {
            this.minisBarKey = key;
            this.minisBarSh = new android.graphics.LinearGradient(this.W * 0.12f, 0.0f, (this.W * 0.88f) + 1.0f, 0.0f, new int[]{-5242816, -45149, -26051}, (float[]) null, Shader.TileMode.CLAMP);
        }
    }

    /** Pixel equaliser bars behind the Beatmaker, pumping with the beat. */
    void minisEqualizer(Canvas c, float pulse) {
        float base = bfTopY() - (this.pad * 0.6f);
        float seg = Math.max(2.0f, this.pad * 0.55f);
        int n = 18;
        float bw = (this.W * 0.92f) / n;
        if (bw <= 0.0f) {
            return;
        }
        this.minisP.setStyle(Paint.Style.FILL);
        for (int i = 0; i < n; i++) {
            float x = (this.W * 0.04f) + (i * bw);
            float lv = Math.abs((float) Math.sin((this.time * (2.1f + ((i % 5) * 0.55f))) + (i * 1.37f)));
            float edge = Math.abs((i - ((n - 1) * 0.5f)) / (n * 0.5f));
            int blocks = (int) ((((lv * 0.55f) + (pulse * 0.45f)) * (3.0f + (edge * 6.0f))) + 1.0f);
            int col = minisEqCols[i % minisEqCols.length];
            for (int b = 0; b < blocks && b < 10; b++) {
                float y = base - (b * seg * 1.35f);
                this.minisP.setColor(b >= blocks - 1 ? -1 : col);
                this.minisP.setAlpha(minisA((b >= blocks - 1 ? 70.0f : 55.0f) + (pulse * 40.0f)));
                c.drawRect((bw * 0.18f) + x, y - seg, (bw * 0.82f) + x, y, this.minisP);
            }
        }
    }

    /** Drifting glowing motes in the rhythm arena. */
    void minisDust(Canvas c, float pulse) {
        this.minisP.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 26; i++) {
            float sp = (minisHash(i) * 0.05f) + 0.02f;
            float fy = 1.0f - (((this.time * sp) + minisHash(i + 77)) % 1.0f);
            float x = (minisHash(i + 31) * this.W) + (((float) Math.sin((this.time * 0.8f) + i)) * this.pad);
            float y = fy * this.H;
            float s = this.hu * ((minisHash(i + 9) * 0.8f) + 0.4f) * ((pulse * 0.5f) + 1.0f);
            this.minisP.setColor(minisEqCols[i % minisEqCols.length]);
            this.minisP.setAlpha(minisA(((float) Math.sin(fy * 3.141592653589793d)) * (50.0f + (pulse * 60.0f))));
            c.drawRect(x - s, y - s, x + s, y + s, this.minisP);
        }
    }

    /** Tiny pixel heart (5x4 cells) centered on x, top at y. */
    void minisHeart(Canvas c, float x, float y, float s, float a) {
        if (s <= 0.0f || a <= 0.0f) {
            return;
        }
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(-26051);
        this.minisP.setAlpha(minisA(a));
        float l = x - (2.5f * s);
        c.drawRect(l + s, y, l + (2.0f * s), y + s, this.minisP);
        c.drawRect(l + (3.0f * s), y, l + (4.0f * s), y + s, this.minisP);
        c.drawRect(l, y + s, l + (5.0f * s), y + (2.0f * s), this.minisP);
        c.drawRect(l + s, y + (2.0f * s), l + (4.0f * s), y + (3.0f * s), this.minisP);
        c.drawRect(l + (2.0f * s), y + (3.0f * s), l + (3.0f * s), y + (4.0f * s), this.minisP);
        this.minisP.setColor(-1);
        this.minisP.setAlpha(minisA(a * 0.8f));
        c.drawRect(l + s, y + s, l + (2.0f * s), y + (2.0f * s), this.minisP);
    }

    /** Waving pennant on a pole whose foot is at (x, y). */
    void minisFlag(Canvas c, float x, float y, float h, int col) {
        if (h <= 0.0f) {
            return;
        }
        float top = y - h;
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(-4609816);
        this.minisP.setAlpha(255);
        c.drawRect(x - (this.hu * 0.5f), top, (this.hu * 0.5f) + x, y, this.minisP);
        this.minisP.setColor(-11430);
        c.drawCircle(x, top, this.hu * 0.9f, this.minisP);
        float fw = h * 0.7f;
        float fh = h * 0.36f;
        this.minisPath.reset();
        this.minisPath.moveTo(x, top + (this.hu * 0.5f));
        for (int k = 1; k <= 5; k++) {
            float u = k / 5.0f;
            float w = ((float) Math.sin((this.time * 6.0f) - (k * 0.9f))) * fh * 0.22f * u;
            this.minisPath.lineTo((fw * u) + x, top + (this.hu * 0.5f) + w + (fh * 0.5f * u * 0.3f));
        }
        for (int k = 5; k >= 0; k--) {
            float u = k / 5.0f;
            float w = ((float) Math.sin((this.time * 6.0f) - (k * 0.9f))) * fh * 0.22f * u;
            this.minisPath.lineTo((fw * u) + x, top + (this.hu * 0.5f) + fh + w - (fh * 0.5f * u * 0.3f));
        }
        this.minisPath.close();
        this.minisP.setColor(col);
        c.drawPath(this.minisPath, this.minisP);
        this.minisP.setColor(-1);
        this.minisP.setAlpha(60);
        c.drawRect(x + this.hu, (this.hu * 0.9f) + top, (fw * 0.45f) + x, (this.hu * 0.9f) + top + (fh * 0.2f), this.minisP);
    }

    /** Night sky panel, moon, stars, clouds and foundation behind the tower. */
    void minisTowerBack(Canvas c, float cx, float tw, float baseY) {
        if (this.W <= 0 || this.H <= 0) {
            return;
        }
        float top = this.H * 0.15f;
        float bottom = (this.pad * 1.6f) + baseY;
        if (bottom <= top) {
            return;
        }
        int key = (this.H * 31) + this.W;
        if (this.minisSkySh == null || this.minisSkyKey != key) {
            this.minisSkyKey = key;
            this.minisSkySh = new android.graphics.LinearGradient(0.0f, top, 0.0f, bottom, new int[]{Color.rgb(12, 12, 38), Color.rgb(38, 22, 70), Color.rgb(96, 46, 84)}, new float[]{0.0f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
        }
        this.minisR.set(cx - (tw * 0.85f), top, (tw * 0.85f) + cx, bottom);
        this.minisG.setStyle(Paint.Style.FILL);
        this.minisG.setShader(this.minisSkySh);
        this.minisG.setAlpha(235);
        c.drawRoundRect(this.minisR, this.pad, this.pad, this.minisG);
        this.minisG.setShader(null);
        this.minisG.setAlpha(255);
        c.save();
        c.clipRect(this.minisR);
        float wd = this.minisR.width();
        float ht = this.minisR.height();
        for (int k = 0; k < 16; k++) {
            float sx = this.minisR.left + (minisHash(k + 400) * wd);
            float sy = (minisHash(k + 500) * ht * 0.55f) + top;
            float tw2 = (((float) Math.sin((this.time * ((minisHash(k) * 3.0f) + 1.5f)) + (k * 2.0f))) * 0.5f) + 0.5f;
            minisStar(c, sx, sy, this.hu * ((tw2 * 0.8f) + 0.4f), -1, (tw2 * 160.0f) + 50.0f);
        }
        float mx = this.minisR.right - (tw * 0.28f);
        float my = (this.pad * 2.6f) + top;
        minisHalo(c, mx, my, this.pad * 4.0f, -5720, 120.0f);
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(-3416);
        this.minisP.setAlpha(255);
        c.drawCircle(mx, my, this.pad * 1.3f, this.minisP);
        this.minisP.setColor(-4609816);
        this.minisP.setAlpha(150);
        c.drawRect(mx - (this.pad * 0.6f), my - (this.pad * 0.3f), mx - (this.pad * 0.2f), (this.pad * 0.1f) + my, this.minisP);
        c.drawRect((this.pad * 0.2f) + mx, (this.pad * 0.3f) + my, (this.pad * 0.6f) + mx, (this.pad * 0.6f) + my, this.minisP);
        // drifting pixel clouds
        for (int k = 0; k < 3; k++) {
            float cw = (minisHash(k + 600) * 0.3f + 0.35f) * wd;
            float u = (((this.time * ((k * 0.006f) + 0.012f)) + minisHash(k + 700)) % 1.0f);
            float clx = (this.minisR.left - cw) + ((wd + cw) * u);
            float cly = (((k * 0.17f) + 0.18f) * ht) + top;
            float ch = this.pad * 0.9f;
            this.minisP.setColor(-4609816);
            this.minisP.setAlpha(34);
            c.drawRect(clx, cly, clx + cw, cly + ch, this.minisP);
            c.drawRect((cw * 0.2f) + clx, cly - (ch * 0.7f), (cw * 0.7f) + clx, cly, this.minisP);
        }
        // ground and stone foundation
        this.minisP.setColor(-14009808);
        this.minisP.setAlpha(255);
        c.drawRect(this.minisR.left, baseY, this.minisR.right, bottom, this.minisP);
        this.minisP.setColor(-11880352);
        c.drawRect(this.minisR.left, baseY, this.minisR.right, (this.hu * 1.2f) + baseY, this.minisP);
        c.restore();
        float fl = cx - (tw * 0.62f);
        float fr = (tw * 0.62f) + cx;
        float fb = (this.pad * 1.2f) + baseY;
        this.minisP.setColor(-11184811);
        c.drawRect(fl, baseY - this.hu, fr, fb, this.minisP);
        this.minisP.setColor(-15331286);
        this.minisP.setAlpha(110);
        float bw = (fr - fl) / 6.0f;
        for (int k = 1; k < 6; k++) {
            float bx = (k * bw) + fl;
            c.drawRect(bx - (this.hu * 0.35f), baseY - this.hu, (this.hu * 0.35f) + bx, fb, this.minisP);
        }
        c.drawRect(fl, fb - (this.hu * 0.8f), fr, fb, this.minisP);
        this.minisP.setColor(-1);
        this.minisP.setAlpha(40);
        c.drawRect(fl, baseY - this.hu, fr, baseY - (this.hu * 0.2f), this.minisP);
    }

    /** Per-floor detail: side shading, brickwork, windows, current-floor markers. */
    void minisTowerFloor(Canvas c, RectF rr, int floorNo, boolean cleared, boolean current, float tw) {
        if (rr.width() <= 0.0f || rr.height() <= 0.0f) {
            return;
        }
        if (this.minisTowerSh == null || this.minisTowerKey != this.W) {
            this.minisTowerKey = this.W;
            this.minisTowerSh = new android.graphics.LinearGradient(rr.left, 0.0f, rr.right, 0.0f, new int[]{872415231, 16777215, 16777216, 1426063360}, new float[]{0.0f, 0.35f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
        }
        this.minisG.setStyle(Paint.Style.FILL);
        this.minisG.setShader(this.minisTowerSh);
        this.minisG.setAlpha(255);
        c.drawRect(rr, this.minisG);
        this.minisG.setShader(null);
        // brickwork: one mortar course + staggered joints
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(-16777216);
        this.minisP.setAlpha(45);
        float cy = rr.centerY();
        float jt = Math.max(1.0f, this.hu * 0.45f);
        c.drawRect(rr.left, cy - (jt * 0.5f), rr.right, (jt * 0.5f) + cy, this.minisP);
        float bw = rr.width() / 5.0f;
        for (int k = 0; k < 5; k++) {
            float jx = (k * bw) + rr.left + ((floorNo % 2) * bw * 0.5f);
            if (jx > rr.left + jt && jx < rr.right - jt) {
                c.drawRect(jx - (jt * 0.5f), rr.top, (jt * 0.5f) + jx, cy, this.minisP);
            }
            float jx2 = (k * bw) + rr.left + (((floorNo + 1) % 2) * bw * 0.5f);
            if (jx2 > rr.left + jt && jx2 < rr.right - jt) {
                c.drawRect(jx2 - (jt * 0.5f), cy, (jt * 0.5f) + jx2, rr.bottom, this.minisP);
            }
        }
        // arched windows: warm flicker when cleared, battle-red when current
        float ww = tw * 0.055f;
        float wh = rr.height() * 0.32f;
        float wy = (rr.height() * 0.08f) + cy;
        for (int s = -1; s <= 1; s += 2) {
            float wx = rr.centerX() + (s * tw * 0.37f);
            int col;
            float a;
            if (cleared) {
                col = -5720;
                a = (((float) Math.sin((this.time * 7.0f) + (floorNo * 2.1f) + s)) * 35.0f) + 220.0f;
                minisHalo(c, wx, wy, ww * 3.0f, -14246, a * 0.7f);
            } else if (current) {
                col = -24512;
                a = (((float) Math.sin(this.time * 5.0f)) * 60.0f) + 195.0f;
                minisHalo(c, wx, wy, ww * 3.2f, -38294, a * 0.8f);
            } else {
                col = -15331286;
                a = 230.0f;
            }
            this.minisP.setColor(col);
            this.minisP.setAlpha(minisA(a));
            c.drawRect(wx - ww, wy - wh, wx + ww, wy + wh, this.minisP);
            c.drawCircle(wx, wy - wh, ww, this.minisP);
            this.minisP.setColor(-15331286);
            this.minisP.setAlpha(160);
            c.drawRect(wx - (jt * 0.5f), (wy - wh) - ww, (jt * 0.5f) + wx, wy + wh, this.minisP);
        }
        if (current) {
            // pulsing outline + bobbing arrows pointing at the floor to storm
            float pu = (((float) Math.sin(this.time * 5.0f)) * 0.5f) + 0.5f;
            this.minisP.setStyle(Paint.Style.STROKE);
            this.minisP.setStrokeWidth(Math.max(1.0f, this.hu * 0.8f));
            this.minisP.setColor(-11430);
            this.minisP.setAlpha(minisA((pu * 150.0f) + 100.0f));
            c.drawRect(rr.left - this.hu, rr.top - this.hu, rr.right + this.hu, rr.bottom + this.hu, this.minisP);
            this.minisP.setStyle(Paint.Style.FILL);
            float bob = ((float) Math.sin(this.time * 6.0f)) * this.pad * 0.5f;
            float ah = rr.height() * 0.35f;
            float aw = this.pad * 1.1f;
            this.minisPath.reset();
            float lx = (rr.left - (this.pad * 0.6f)) - bob;
            this.minisPath.moveTo(lx, cy);
            this.minisPath.lineTo(lx - aw, cy - ah);
            this.minisPath.lineTo(lx - aw, cy + ah);
            this.minisPath.close();
            float rx = (this.pad * 0.6f) + rr.right + bob;
            this.minisPath.moveTo(rx, cy);
            this.minisPath.lineTo(rx + aw, cy - ah);
            this.minisPath.lineTo(rx + aw, cy + ah);
            this.minisPath.close();
            this.minisP.setColor(-11430);
            this.minisP.setAlpha(255);
            c.drawPath(this.minisPath, this.minisP);
        }
    }

    /** Ghost floors fading upward when the tower continues beyond view. */
    void minisTowerFade(Canvas c, float cx, float tw, float topY, float fh) {
        float hw = tw / 2.0f;
        for (int k = 0; k < 3; k++) {
            float b = topY - (k * fh);
            float t = (b - fh) + (this.pad * 0.25f);
            if (t < this.H * 0.15f) {
                break;
            }
            this.minisP.setStyle(Paint.Style.FILL);
            this.minisP.setColor(-12963760);
            this.minisP.setAlpha(110 - (k * 35));
            c.drawRect(cx - hw, t, cx + hw, b, this.minisP);
            this.minisP.setColor(-15331286);
            this.minisP.setAlpha(80 - (k * 25));
            c.drawRect(cx - (tw * 0.42f), ((b - t) * 0.3f) + t, cx - (tw * 0.32f), b - ((b - t) * 0.2f), this.minisP);
            c.drawRect((tw * 0.32f) + cx, ((b - t) * 0.3f) + t, (tw * 0.42f) + cx, b - ((b - t) * 0.2f), this.minisP);
        }
        float u = (this.time * 0.5f) % 1.0f;
        minisStar(c, cx + (((float) Math.sin(this.time * 1.3f)) * hw), (topY - (fh * 1.5f)) - (u * fh), this.hu * 1.2f, -5213953, ((float) Math.sin(u * 3.141592653589793d)) * 200.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x051d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void drawBeatFight(Canvas var1) {
       if (this.W <= 0 || this.H <= 0) {
          return;
       }
       this.minisEnsureBeat();
       this.minisG.setStyle(Style.FILL);
       this.minisG.setShader(this.minisBgSh);
       this.minisG.setAlpha(255);
       var1.drawRect(0.0F, 0.0F, (float)this.W, (float)this.H, this.minisG);
       this.minisG.setShader(null);

       double var2 = this.bfNow;
       double var4 = Math.floor(var2);
       float var28;
       if (this.bfState == 1) {
          var28 = (float)Math.max(0.0, 1.0 - (var2 - var4) / 0.25);
       } else {
          var28 = 0.0F;
       }
       final float minisPulse = var28;
       final float minisFrac = (float)(var2 - var4);
       // beat-synced stage glow behind the Beatmaker + floating motes + equaliser
       this.minisHalo(var1, (float)this.W * 0.5F, (float)this.H * 0.2F, (float)this.W * (0.42F + 0.06F * minisPulse), -5242816, 70.0F + 110.0F * minisPulse);
       this.minisDust(var1, minisPulse);
       this.minisEqualizer(var1, minisPulse);

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

          Path var21 = this.minisPath;
          var21.reset();
          float var11 = (float)this.H * 0.5F;
          float var10 = (float)this.W * 0.08F;
          var2 = (double)(var9 * 0.5F + var7);
          var7 = (float)Math.sin(var2) * var11 + var8;
          var9 = (float)this.H * 0.04F + (float)Math.cos(var2) * var11;
          var21.moveTo(var8, (float)this.H * 0.04F);
          var21.lineTo(var7 - var10, var9);
          var21.lineTo(var7 + var10, var9);
          var21.close();
          this.p.setColor(minisBeamCols[var93]);
          this.p.setAlpha((int)(40.0F * var28 + 22.0F));
          var1.drawPath(var21, this.p);
          // bright core of each spotlight + lamp at the rig
          this.p.setAlpha((int)(30.0F * var28 + 14.0F));
          var21.reset();
          var21.moveTo(var8, (float)this.H * 0.04F);
          var21.lineTo(var7 - var10 * 0.35F, var9);
          var21.lineTo(var7 + var10 * 0.35F, var9);
          var21.close();
          var1.drawPath(var21, this.p);
          this.minisHalo(var1, var8, (float)this.H * 0.04F, this.pad * (1.6F + var28), minisBeamCols[var93], 200.0F);
          this.minisP.setColor(-1);
          this.minisP.setAlpha(230);
          var1.drawCircle(var8, (float)this.H * 0.04F, this.pad * 0.45F, this.minisP);
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
          // speaker thump: shockwave ring on each beat
          this.minisP.setStyle(Style.STROKE);
          this.minisP.setStrokeWidth(this.pad * 0.25F);
          this.minisP.setColor(-45149);
          this.minisP.setAlpha(minisA(200.0F * minisPulse));
          var1.drawCircle(var77, var83, this.pad * (1.7F + (1.0F - minisPulse) * 2.2F), this.minisP);
          this.minisP.setStyle(Style.FILL);
          this.p.setColor(-15857128);
          var1.drawCircle(var77, var83, this.pad * (1.6F + 0.18F * minisPulse), this.p);
          this.minisP.setColor(-12963760);
          this.minisP.setAlpha(255);
          var1.drawCircle(var77, var83, this.pad * (1.05F + 0.25F * minisPulse), this.minisP);
          this.minisP.setColor(-15857128);
          var1.drawCircle(var77, var83, this.pad * (0.8F + 0.2F * minisPulse), this.minisP);
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
       int[] var151 = minisLaneCols;

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
          // lane depth: colour gradient brightening toward the hit line
          this.minisR.set(this.r.left, var83, this.r.right, var12);
          this.minisG.setShader(this.minisLaneSh[var96]);
          this.minisG.setAlpha(minisA(150.0F + 60.0F * minisPulse));
          var1.drawRect(this.minisR, this.minisG);
          // beat grid: marker lines scrolling toward the hit line, synced to the beat
          this.minisP.setStyle(Style.FILL);
          for (int minisK = 0; minisK < 4; minisK++) {
             float minisU = ((float)minisK + minisFrac) / 4.0F;
             float minisY = var83 + (var12 - var83) * minisU;
             this.minisP.setColor(var151[var96]);
             this.minisP.setAlpha(minisA(25.0F + 70.0F * minisU));
             var1.drawRect(this.r.left + var67 * 0.5F, minisY - this.hu * 0.5F, this.r.right - var67 * 0.5F, minisY + this.hu * 0.5F, this.minisP);
          }
          // lane edge rails
          this.minisP.setColor(var151[var96]);
          this.minisP.setAlpha(minisA(70.0F + 120.0F * this.bfLaneFlash[var96]));
          var1.drawRect(this.r.left, var83 + var67, this.r.left + this.hu * 0.8F, this.r.bottom - var67, this.minisP);
          var1.drawRect(this.r.right - this.hu * 0.8F, var83 + var67, this.r.right, this.r.bottom - var67, this.minisP);
          // tap flash: light column shooting up the lane
          float minisF = this.bfLaneFlash[var96];
          if (minisF > 0.02F) {
             this.minisG.setShader(this.minisBeamSh[var96]);
             this.minisG.setAlpha(minisA(255.0F * Math.min(1.0F, minisF)));
             float minisInset = var13 * 0.5F * (1.0F - Math.min(1.0F, minisF)) * 0.6F;
             var1.drawRect(this.r.left + minisInset, var83, this.r.right - minisInset, var12, this.minisG);
          }
          this.minisG.setShader(null);
          this.minisG.setAlpha(255);
       }

       RectF var127 = this.r;
       var51 = var51 * 0.075F / 2.0F;
       var67 = var12 - var51;
       var51 = var12 + var51;
       var127.set(var77, var67, var13 * 3.0F + var77, var51);
       // hit-zone glow band (cached gradient), swelling on the beat
       this.minisG.setShader(this.minisHitSh);
       this.minisG.setAlpha(minisA(140.0F + 115.0F * minisPulse));
       var1.drawRect(var77, var67 - (float)this.H * 0.05F, var13 * 3.0F + var77, var51 + (float)this.H * 0.05F, this.minisG);
       this.minisG.setShader(null);
       this.minisG.setAlpha(255);
       // sparks running along the hit line
       for (int minisK = 0; minisK < 6; minisK++) {
          float minisU = ((this.time * 0.35F) + (float)minisK / 6.0F) % 1.0F;
          float minisSx = var77 + var13 * 3.0F * minisU;
          this.minisStar(var1, minisSx, var12 + (minisK % 2 == 0 ? var67 - var12 : var51 - var12), this.hu * (0.9F + minisPulse * 1.2F), minisLaneCols[minisK % 3], 120.0F + 120.0F * minisPulse);
       }
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

          if (this.bfLaneFlash[var97] > 0.02F) {
             this.minisHalo(var1, this.r.centerX(), this.r.centerY(), this.r.width() * (0.55F + 0.25F * this.bfLaneFlash[var97]), var151[var97], 255.0F * Math.min(1.0F, this.bfLaneFlash[var97]));
          }
          this.panel(var1, var129, var28, var117);
          this.minisGloss(var1, this.r, this.pad, 1.0F);
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
             int minisNc = var23.type == 1 ? -46518 : var151[var23.lane];
             if (var23.state == 0) {
                // motion trail streaking behind the falling tile + soft glow
                for (int minisK = 3; minisK >= 1; minisK--) {
                   float minisTy = (float)minisK * var14 * 0.42F;
                   if (var88 - minisTy < var83) {
                      continue;
                   }
                   this.minisP.setStyle(Style.FILL);
                   this.minisP.setColor(minisNc);
                   this.minisP.setAlpha(minisA(var28 * (70.0F - (float)minisK * 18.0F)));
                   float minisSh = (float)minisK * var17 * 0.35F;
                   var1.drawRect(this.r.left + minisSh, this.r.top - minisTy, this.r.right - minisSh, this.r.bottom - minisTy, this.minisP);
                }
                this.minisHalo(var1, this.r.centerX(), this.r.centerY(), this.r.width() * (var98 != 0 ? 0.75F : 0.55F), minisNc, (var98 != 0 ? 220.0F : 110.0F) * var28);
             } else if (var23.state == 1) {
                // hit burst: expanding ring and radial sparks
                float minisB = Math.min(1.0F, var23.hitT / 0.3F);
                this.minisP.setStyle(Style.STROKE);
                this.minisP.setStrokeWidth(this.pad * 0.5F * (1.0F - minisB) + 1.0F);
                this.minisP.setColor(var23.type == 1 ? -46518 : -1);
                this.minisP.setAlpha(minisA(255.0F * (1.0F - minisB)));
                var1.drawCircle(this.r.centerX(), this.r.centerY(), var13 * (0.25F + 0.55F * minisB), this.minisP);
                this.minisP.setStyle(Style.FILL);
                for (int minisK = 0; minisK < 8; minisK++) {
                   double minisAng = (double)minisK * 0.7853981633974483 + (double)var23.lane;
                   float minisD = var13 * (0.2F + 0.7F * minisB);
                   this.minisStar(var1, this.r.centerX() + (float)Math.cos(minisAng) * minisD, this.r.centerY() + (float)Math.sin(minisAng) * minisD * 0.7F, this.hu * (1.6F - minisB), var151[var23.lane], 255.0F * (1.0F - minisB));
                }
             }
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
       if (this.r.width() > 0.0F) {
          // gradient fill, gloss, segment ticks and a hot leading edge
          this.minisG.setShader(this.minisBarSh);
          this.minisG.setAlpha(255);
          var1.drawRoundRect(this.r, var28 * 0.6F, var28 * 0.6F, this.minisG);
          this.minisG.setShader(null);
          this.minisGloss(var1, this.r, var28 * 0.6F, 1.0F);
          this.minisP.setStyle(Style.FILL);
          this.minisP.setColor(-15331286);
          this.minisP.setAlpha(90);
          for (int minisK = 1; minisK < 10; minisK++) {
             float minisX = (float)this.W * 0.12F + (float)this.W * 0.076F * (float)minisK;
             if (minisX < this.r.right - this.hu) {
                var1.drawRect(minisX - this.hu * 0.4F, this.r.top + this.hu, minisX + this.hu * 0.4F, this.r.bottom - this.hu, this.minisP);
             }
          }
          this.minisHalo(var1, this.r.right, this.r.centerY(), this.pad * (1.4F + this.bfBossHurt), -1, 120.0F + 135.0F * this.bfBossHurt);
       }
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
       if (this.r.width() > 0.0F) {
          this.minisGloss(var1, this.r, var28 * 0.5F, 1.0F);
          if (this.bfHeroHp <= 35.0F) {
             // low-health heartbeat glow on the player's bar
             float minisLow = (float)(Math.sin((double)(this.time * 9.0F)) * 0.5 + 0.5);
             this.minisP.setStyle(Style.STROKE);
             this.minisP.setStrokeWidth(this.pad * 0.3F);
             this.minisP.setColor(-38294);
             this.minisP.setAlpha(minisA(90.0F + 150.0F * minisLow));
             this.minisR.set(this.pad * 5.0F - this.hu, this.r.top - this.hu, (float)this.W * 0.6F + this.hu, this.r.bottom + this.hu);
             var1.drawRoundRect(this.minisR, var28 * 0.6F, var28 * 0.6F, this.minisP);
             this.minisP.setStyle(Style.FILL);
          }
       }
       if (this.bfHurt > 0.0F) {
          this.p.setColor(-65536);
          this.p.setAlpha((int)(this.bfHurt * 70.0F));
          var1.drawRect(0.0F, 0.0F, (float)this.W, (float)this.H, this.p);
          this.p.setAlpha(255);
          // red damage vignette hugging the screen edges
          this.minisP.setStyle(Style.STROKE);
          this.minisP.setColor(-65536);
          for (int minisK = 0; minisK < 3; minisK++) {
             float minisSw = this.pad * (1.2F + (float)minisK * 1.4F);
             this.minisP.setStrokeWidth(minisSw);
             this.minisP.setAlpha(minisA(this.bfHurt * (110.0F - (float)minisK * 30.0F)));
             var1.drawRect(minisSw * 0.5F, minisSw * 0.5F, (float)this.W - minisSw * 0.5F, (float)this.H - minisSw * 0.5F, this.minisP);
          }
          this.minisP.setStyle(Style.FILL);
       }

       if (this.bfCombo >= 2) {
          // combo counter glows hotter as the streak grows and kicks on the beat
          float minisCs = 1.0F + 0.12F * minisPulse + Math.min(0.25F, (float)this.bfCombo * 0.01F);
          float minisCx = (float)this.W - this.pad * 2.0F;
          float minisCy = (float)this.H * 0.935F;
          this.tp.setTextAlign(Align.RIGHT);
          float minisTs = this.tp.getTextSize();
          this.tp.setTextSize(minisTs * minisCs);
          String minisCt = "КОМБО " + this.bfCombo;
          float minisCw = this.tp.measureText(minisCt);
          this.minisHalo(var1, minisCx - minisCw * 0.5F, minisCy - minisTs * 0.4F, minisCw * 0.75F, this.bfCombo >= 10 ? -26051 : -11430, 70.0F + 90.0F * minisPulse);
          this.tp.setColor(-15331286);
          var1.drawText(minisCt, minisCx + this.hu, minisCy + this.hu, this.tp);
          this.tp.setColor(-11430);
          var1.drawText(minisCt, minisCx, minisCy, this.tp);
          this.tp.setTextSize(minisTs);
       }

       if (this.bfJudgeT > 0.0F) {
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setTextSize(this.pad * 2.0F);
          // judgement pop: overshoot scale, rise, chunky outline
          float minisAge = Math.max(0.0F, 0.6F - this.bfJudgeT);
          float minisSc = minisAge < 0.08F ? 0.6F + minisAge / 0.08F * 0.75F : 1.35F - Math.min(0.35F, (minisAge - 0.08F) * 2.5F);
          float minisJy = var67 - this.pad * 1.5F - minisAge * this.pad * 3.0F;
          float minisJa = Math.min(1.0F, this.bfJudgeT * 3.0F) * 255.0F;
          if (minisAge < 0.25F) {
             this.minisRays(var1, (float)this.W / 2.0F, minisJy - this.pad * 0.7F, this.pad * 1.5F, this.pad * (3.0F + minisAge * 14.0F), 12, this.time, 0.35F, this.bfJudgeCol, 110.0F * (1.0F - minisAge / 0.25F));
          }
          this.minisPopText(var1, this.bfJudge, (float)this.W / 2.0F, minisJy, this.pad * 2.0F * minisSc, this.bfJudgeCol, minisJa);
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

          if (var144.length() <= 2 || var94 <= 0 && var2 >= 0.0) {
             // countdown beats punch in with a burst behind them
             float minisK2 = 1.0F - minisFrac;
             var28 *= 1.0F + 0.35F * minisK2 * minisK2;
             this.minisRays(var1, (float)this.W / 2.0F, (float)this.H * 0.5F - var51 * 1.5F, var51 * 2.0F, var51 * (6.0F + 3.0F * minisK2), 14, this.time * 0.8F, 0.4F, -45149, 40.0F + 90.0F * minisK2);
             this.minisHalo(var1, (float)this.W / 2.0F, (float)this.H * 0.5F - var51 * 1.5F, var51 * (5.0F + 2.0F * minisK2), -5242816, 120.0F + 100.0F * minisK2);
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
          // victory: spinning god-rays, glow and orbiting sparkles around the trophy
          this.minisRays(var1, (float)var94 / 2.0F, var28, (float)var94 * 0.08F, (float)var94 * 0.62F, 16, this.time * 0.4F, 0.45F, -11430, 55.0F);
          this.minisRays(var1, (float)var94 / 2.0F, var28, (float)var94 * 0.08F, (float)var94 * 0.45F, 10, -this.time * 0.6F, 0.3F, -45149, 70.0F);
          this.minisHalo(var1, (float)var94 / 2.0F, var28, (float)var94 * 0.26F, -1, 120.0F);
          for (int minisK = 0; minisK < 10; minisK++) {
             double minisAng = (double)(this.time * 1.2F) + (double)minisK * 0.6283185307179586;
             float minisRr = (float)var94 * (0.24F + 0.04F * (float)Math.sin((double)(this.time * 3.0F + (float)minisK)));
             this.minisStar(var1, (float)var94 / 2.0F + (float)Math.cos(minisAng) * minisRr, var28 + (float)Math.sin(minisAng) * minisRr * 0.8F, this.hu * (1.2F + 0.8F * (float)Math.abs(Math.sin((double)(this.time * 5.0F + (float)minisK)))), minisSparkCols[minisK % 4], 230.0F);
          }
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

          this.tp.setColor(-15331286);
          var1.drawText(var147, (float)this.W / 2.0F + this.hu * 1.2F, (float)this.H * 0.6F + this.hu * 1.2F, this.tp);
          this.tp.setColor(-11430);
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
          this.minisGloss(var1, this.bfExit, this.pad * 1.5F, 0.8F);
          this.minisGloss(var1, this.bfRetry, this.pad * 1.5F, 0.8F);
          this.minisP.setStyle(Style.STROKE);
          this.minisP.setStrokeWidth(this.pad * 0.3F);
          this.minisP.setColor(-11430);
          this.minisP.setAlpha(minisA((float)(Math.sin((double)(this.time * 5.0F)) * 90.0 + 150.0)));
          var1.drawRoundRect(this.bfRetry, this.pad * 1.5F, this.pad * 1.5F, this.minisP);
          this.minisP.setStyle(Style.FILL);
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
            // beat window glow filling the target ring
            this.p.setStyle(Paint.Style.FILL);
            minisHalo(canvas, this.mobX, this.mobY, f * 1.15f, i2, 110.0f * max);
            this.p.setStyle(Paint.Style.STROKE);
            this.p.setColor(i2);
            this.p.setAlpha((int) ((150.0f * max) + 55.0f));
            this.p.setStrokeWidth(this.pad * ((max * 0.4f) + 0.3f));
            canvas.drawCircle(this.mobX, this.mobY, f, this.p);
            // rotating rhythm ticks around the target ring (spin faster with combo)
            this.minisP.setStyle(Paint.Style.STROKE);
            this.minisP.setStrokeWidth(Math.max(1.0f, this.pad * 0.28f));
            this.minisP.setColor(i2);
            this.minisP.setAlpha(minisA((120.0f * max) + 70.0f));
            float minisRot = this.time * (0.6f + (Math.min(30, this.combo) * 0.05f));
            for (int minisK = 0; minisK < 12; minisK++) {
                double minisAng = minisRot + (minisK * 0.5235987755982988d);
                float minisC = (float) Math.cos(minisAng);
                float minisS = (float) Math.sin(minisAng);
                float minisL = (minisK % 3 == 0 ? 0.16f : 0.08f) * f;
                canvas.drawLine(this.mobX + (minisC * (f + (this.pad * 0.5f))), this.mobY + (minisS * (f + (this.pad * 0.5f))), this.mobX + (minisC * (f + (this.pad * 0.5f) + minisL)), this.mobY + (minisS * (f + (this.pad * 0.5f) + minisL)), this.minisP);
            }
            // echo of the approach ring for a sense of speed
            this.minisP.setStrokeWidth(this.pad * 0.2f);
            this.minisP.setAlpha(minisA((1.0f - floor) * 80.0f));
            canvas.drawCircle(this.mobX, this.mobY, f2 * 1.1f, this.minisP);
            this.minisP.setStyle(Paint.Style.FILL);
            this.p.setAlpha((int) (((1.0f - floor) * 170.0f) + 40.0f));
            this.p.setStrokeWidth(this.pad * 0.35f);
            canvas.drawCircle(this.mobX, this.mobY, f2, this.p);
            this.p.setStyle(Paint.Style.FILL);
            this.p.setAlpha(255);
            if (max > 0.05f) {
                // on-beat sparkles flare at the compass points
                for (int minisK = 0; minisK < 4; minisK++) {
                    double minisAng = (minisK * 1.5707963267948966d) + 0.7853981633974483d;
                    minisStar(canvas, this.mobX + (((float) Math.cos(minisAng)) * f), this.mobY + (((float) Math.sin(minisAng)) * f), this.hu * ((max * 2.2f) + 0.4f), i2, 255.0f * max);
                }
            }
            if (this.combo >= 10) {
                // hot combo: embers orbiting the ring
                int minisN = this.combo >= 25 ? 8 : 5;
                for (int minisK = 0; minisK < minisN; minisK++) {
                    double minisAng = (this.time * 2.2f) + ((minisK * 6.283185307179586d) / minisN);
                    float minisR0 = f * (((float) Math.sin((this.time * 4.0f) + minisK)) * 0.06f + 1.0f);
                    float minisEx = this.mobX + (((float) Math.cos(minisAng)) * minisR0);
                    float minisEy = this.mobY + (((float) Math.sin(minisAng)) * minisR0);
                    minisHalo(canvas, minisEx, minisEy, this.hu * 3.0f, i2, 160.0f);
                    this.minisP.setColor(-1);
                    this.minisP.setAlpha(220);
                    canvas.drawRect(minisEx - (this.hu * 0.7f), minisEy - (this.hu * 0.7f), (this.hu * 0.7f) + minisEx, (this.hu * 0.7f) + minisEy, this.minisP);
                }
            }
            if (this.judgeT > 0.0f) {
                // judgement pop: overshoot, rise and outline
                float minisAge = Math.max(0.0f, 0.5f - this.judgeT);
                float minisSc = minisAge < 0.07f ? (minisAge / 0.07f) * 0.7f + 0.65f : 1.35f - Math.min(0.3f, (minisAge - 0.07f) * 2.0f);
                float minisA0 = Math.min(1.0f, this.judgeT * 3.0f) * 255.0f;
                minisPopText(canvas, this.judgeTxt, this.mobX, ((this.mobY - f) - (this.pad * 0.8f)) - (minisAge * this.pad * 2.5f), this.pad * 1.5f * minisSc, this.judgeCol, minisA0);
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

          var19 = this.r;
          var6 = this.pad;
          this.menuCell.set(var19);
          this.menuCard(var1, var19, var6, var13, var16 ? -6295297 : (var15 ? -7569238 : -12963760), false, 6, true);
          Bitmap var20 = this.beastBmp(var14);
          float var9 = var5 * 0.34F;
          if (var20 == null || var20.getWidth() <= 0 || var20.getHeight() <= 0) {
             continue;
          }
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

          float menuBx = var6 + var11 + var10 * 0.4F;
          float menuBy = var8 - var12;
          if (var16) {
             // studied: soft cyan aura behind the sprite
             this.menuGlowAt(var1, 47, menuBx, menuBy - (float)var20.getHeight() * var7 * 0.5F, var3 * 0.5F, -6295297, (int)(120.0F + 80.0F * this.menuPulse(2.4F, var14 * 0.6F)));
          }
          if (var15) {
             // ground shadow under the sprite
             this.menuFill.setStyle(Style.FILL);
             this.menuFill.setColor(0x60000000);
             float menuSw = var9 * 0.36F;
             this.menuArc.set(menuBx - menuSw, menuBy - this.pad * 0.35F, menuBx + menuSw, menuBy + this.pad * 0.2F);
             var1.drawOval(this.menuArc, this.menuFill);
          }
          this.blit(var1, var20, menuBx, menuBy, var7, 1.0F, 1.0F, var34);
          if (var16) {
             float menuTw = (float)Math.sin(this.time * 2.8F + var14 * 1.37F);
             this.menuSpark(var1, this.menuCell.right - this.pad * 1.1F, this.menuCell.top + this.pad * 1.0F, Math.max(1.0F, this.pad * 0.18F), -6295297, menuTw > 0.0F ? (int)(255.0F * menuTw) : 0);
             this.menuLine.setStyle(Style.STROKE);
             this.menuLine.setStrokeWidth(Math.max(1.0F, this.pad * 0.16F));
             this.menuLine.setColor(menuAlpha(-6295297, (int)(90.0F + 90.0F * this.menuPulse(2.4F, var14 * 0.6F))));
             var1.drawRoundRect(this.menuCell, this.pad, this.pad, this.menuLine);
          }
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
          if (var15 && !var16) {
             int menuNeed = this.beastNeed(var14);
             float menuY1 = this.menuCell.bottom - this.pad * 0.45F;
             float menuY0 = menuY1 - this.pad * 0.35F;
             if (menuNeed > 0 && menuY0 >= var4 + 0.72F * var3 + this.pad * 0.3F) {
                this.menuBar(var1, var7, menuY0, var7 + var6, menuY1, (float)this.bk[var14] / (float)menuNeed, -7569238, 0, var14);
             }
          }
       }
    }

    void drawCoins(Canvas canvas) {
        if (this.bmCoin == null || this.coins.isEmpty()) {
            return;
        }
        float f = this.hu * 0.42f;
        float fxCw = this.bmCoin.getWidth() * f;
        float fxCh = this.bmCoin.getHeight() * f;
        float fxTx = this.pad * 3.0f;
        float fxTy = this.headerB * 0.55f;
        Iterator<Fx> it = this.coins.iterator();
        while (it.hasNext()) {
            Fx next = it.next();
            float fxCos = (float) Math.cos((next.life * 9.0f) + next.size);
            float fxSx = Math.max(0.12f, Math.abs(fxCos));
            float fxHome = next.life > next.max ? Math.min(1.0f, (next.life - next.max) * 3.2f) : 0.0f;
            // motion streak while flying to the gold counter
            if (fxHome > 0.0f) {
                float fxDx = fxTx - next.x;
                float fxDy = fxTy - next.y;
                float fxLen = (float) Math.hypot(fxDx, fxDy);
                if (fxLen > 1.0f) {
                    float fxTl = this.pad * 3.2f * fxHome;
                    float fxEx = next.x - ((fxDx / fxLen) * fxTl);
                    float fxEy = next.y - ((fxDy / fxLen) * fxTl);
                    this.fxS.setColor(-11430);
                    this.fxS.setAlpha((int) (70.0f * fxHome));
                    this.fxS.setStrokeWidth(fxCh * 0.8f);
                    canvas.drawLine(fxEx, fxEy, next.x, next.y, this.fxS);
                    this.fxS.setColor(-4952);
                    this.fxS.setAlpha((int) (170.0f * fxHome));
                    this.fxS.setStrokeWidth(fxCh * 0.25f);
                    canvas.drawLine(fxEx, fxEy, next.x, next.y, this.fxS);
                }
            }
            // warm glow halo
            this.fxP.setColor(-11430);
            this.fxP.setAlpha((int) ((40.0f * fxHome) + 45.0f));
            canvas.drawCircle(next.x, next.y, Math.max(fxCw, fxCh) * 0.85f, this.fxP);
            // spin: back face rendered darker
            Paint fxCp = this.bp;
            if (fxCos < 0.0f) {
                this.fxBmp.setColorFilter(this.fxCoinBack);
                fxCp = this.fxBmp;
            }
            blit(canvas, this.bmCoin, next.x, (fxCh / 2.0f) + next.y, f, fxSx, 1.0f, fxCp);
            // edge-on glint
            if (fxSx < 0.35f) {
                this.fxP.setColor(-2338);
                this.fxP.setAlpha((int) ((1.0f - (fxSx / 0.35f)) * 230.0f));
                float fxGw = Math.max(1.0f, this.hu * 0.25f);
                canvas.drawRect(next.x - fxGw, next.y - (fxCh * 0.5f), next.x + fxGw, next.y + (fxCh * 0.5f), this.fxP);
            }
            // twinkling 4-point sparkle
            float fxTw = (float) Math.sin((next.life * 11.0f) + (next.size * 3.0f));
            if (fxTw > 0.3f) {
                float fxK = (fxTw - 0.3f) / 0.7f;
                float fxSxp = next.x + (fxCw * 0.35f);
                float fxSyp = next.y - (fxCh * 0.35f);
                float fxSl = this.pad * 0.9f * fxK;
                this.fxS.setColor(-1);
                this.fxS.setAlpha((int) (230.0f * fxK));
                this.fxS.setStrokeWidth(Math.max(1.0f, this.hu * 0.3f));
                canvas.drawLine(fxSxp - fxSl, fxSyp, fxSxp + fxSl, fxSyp, this.fxS);
                canvas.drawLine(fxSxp, fxSyp - fxSl, fxSxp, fxSyp + fxSl, this.fxS);
            }
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
                ovlGlow(canvas, f7 - f5, this.groundY - (f10 * 9.0f), f10 * 14.0f, -5720, (int) ((Math.sin(f3 * 50.0f) * 25.0d) + 150.0d));
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
            if (f20 >= 0.0f && f20 < 0.9f) {
                float ovlQ = f20 / 0.9f;
                float ovlQe = 1.0f - ((1.0f - ovlQ) * (1.0f - ovlQ));
                float ovlIx = this.heroX;
                float ovlIy = this.groundY - (this.hu * 12.0f);
                float ovlU = this.hu;
                ovlShock(canvas, ovlIx, ovlIy, ovlU * ((ovlQe * 46.0f) + 6.0f), ((1.0f - ovlQ) * ovlU * 2.4f) + 1.0f, -11430, (int) ((1.0f - ovlQ) * 230.0f));
                ovlBurst(canvas, ovlIx, ovlIy, ovlU * ((ovlQe * 22.0f) + 8.0f), ovlU * ((ovlQe * 50.0f) + 14.0f), 16, 5.0f, -1, (int) ((1.0f - ovlQ) * 220.0f), ((1.0f - ovlQ) * ovlU * 1.4f) + 1.0f);
                this.ovlP.setStyle(Paint.Style.FILL);
                for (int ovlK = 0; ovlK < 10; ovlK++) {
                    float ovlDir = ovlK < 5 ? -1.0f : 1.0f;
                    float ovlSp = (ovlHash(ovlK, 81) * 0.7f) + 0.5f;
                    float ovlDx = ovlDir * ovlQe * ovlSp * ovlU * 34.0f;
                    float ovlDy = ((float) Math.sin(ovlQ * 3.141592653589793d)) * ovlU * ((ovlHash(ovlK, 82) * 10.0f) + 4.0f);
                    float ovlS1 = ovlU * ((ovlHash(ovlK, 83) * 2.5f) + 2.0f) * ((ovlQ * 0.8f) + 0.6f);
                    this.ovlP.setColor(ovlK % 2 == 0 ? -4609816 : -7569238);
                    this.ovlP.setAlpha((int) ((1.0f - ovlQ) * 170.0f));
                    canvas.drawRect((ovlIx + ovlDx) - ovlS1, (this.groundY - ovlDy) - ovlS1, ovlIx + ovlDx + ovlS1, this.groundY - ovlDy, this.ovlP);
                }
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
                int ovlBa = (int) (Math.min(f, f22) * 255.0f);
                this.tp.setAlpha(ovlBa);
                float f25 = this.battleT;
                this.tp.setShadowLayer(this.pad * 1.2f, 0.0f, 0.0f, (((ovlBa * 230) / 255) << 24) | 16737843);
                canvas.drawText(strArr[this.deathKind], this.W / 2.0f, f25 + ((this.battleB - f25) * 0.4f), this.tp);
                this.tp.clearShadowLayer();
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
        float minisHu = this.hu;
        float minisDir = this.fairyDir < 0.0f ? -1.0f : 1.0f;
        float minisBreath = (((float) Math.sin(this.time * 3.0f)) * 0.08f) + 1.0f;
        // soft shadow on the ground gives the fairy height and depth
        float minisSpan = Math.max(1.0f, this.battleB - this.battleT);
        float minisNear = Math.max(0.0f, 1.0f - (Math.abs(this.groundY - f2) / minisSpan));
        if (minisNear > 0.0f) {
            this.minisP.setStyle(Paint.Style.FILL);
            this.minisP.setColor(-16777216);
            this.minisP.setAlpha(minisA(70.0f * minisNear));
            float minisSw = minisHu * ((3.0f * minisNear) + 2.0f);
            this.minisR.set(f - minisSw, this.groundY - (minisHu * 0.9f), f + minisSw, this.groundY + (minisHu * 0.9f));
            canvas.drawOval(this.minisR, this.minisP);
        }
        // sparkle trail streaming behind her flight path
        for (int minisK = 0; minisK < 8; minisK++) {
            float minisD = minisHu * ((minisK * 2.6f) + 3.5f);
            float minisWob = ((float) Math.sin((this.time * 7.0f) - (minisK * 0.8f))) * minisHu * ((minisK * 0.35f) + 1.0f);
            float minisTw = ((float) Math.sin((this.time * 14.0f) + (minisK * 1.9f))) * 0.5f + 0.5f;
            minisStar(canvas, f - (minisDir * minisD), f2 + minisWob + (minisK * minisHu * 0.5f), minisHu * (((1.0f - (minisK / 8.0f)) * 1.4f) + 0.3f) * ((minisTw * 0.6f) + 0.6f), minisSparkCols[minisK % 4], (1.0f - (minisK / 8.0f)) * 230.0f * ((minisTw * 0.5f) + 0.5f));
        }
        // warm radial aura (cached gradient, rebuilt only when the pixel unit changes)
        if (this.minisFairySh == null || this.minisFairyKey != minisHu) {
            this.minisFairyKey = minisHu;
            this.minisFairySh = new RadialGradient(0.0f, 0.0f, Math.max(1.0f, minisHu * 13.0f), new int[]{-1996492120, 1442837160, 587193000, 16773800}, new float[]{0.0f, 0.3f, 0.65f, 1.0f}, Shader.TileMode.CLAMP);
        }
        canvas.save();
        canvas.translate(f, f2);
        canvas.scale(minisBreath, minisBreath);
        this.minisG.setStyle(Paint.Style.FILL);
        this.minisG.setShader(this.minisFairySh);
        this.minisG.setAlpha(255);
        canvas.drawCircle(0.0f, 0.0f, minisHu * 13.0f, this.minisG);
        this.minisG.setShader(null);
        canvas.restore();
        // translucent glowing wings, flapping fast
        float minisFlap = (float) Math.sin(this.time * 26.0f);
        for (int minisSide = -1; minisSide <= 1; minisSide += 2) {
            for (int minisW = 0; minisW < 2; minisW++) {
                float minisWx = f + (minisSide * minisHu * (minisW == 0 ? 3.6f : 2.8f));
                float minisWy = f2 - (minisHu * (minisW == 0 ? 2.4f : -0.6f));
                float minisRw = minisHu * (minisW == 0 ? 3.4f : 2.3f);
                float minisRh = minisHu * (minisW == 0 ? 2.2f : 1.5f) * ((Math.abs(minisFlap) * 0.75f) + 0.25f);
                canvas.save();
                canvas.rotate(minisSide * (minisW == 0 ? -28.0f : 22.0f) + (minisSide * minisFlap * 12.0f), minisWx, minisWy);
                this.minisR.set(minisWx - minisRw, minisWy - minisRh, minisWx + minisRw, minisWy + minisRh);
                this.minisP.setStyle(Paint.Style.FILL);
                this.minisP.setColor(minisW == 0 ? -5242881 : -18751);
                this.minisP.setAlpha(110);
                canvas.drawOval(this.minisR, this.minisP);
                this.minisP.setStyle(Paint.Style.STROKE);
                this.minisP.setStrokeWidth(Math.max(1.0f, minisHu * 0.45f));
                this.minisP.setColor(-1);
                this.minisP.setAlpha(170);
                canvas.drawOval(this.minisR, this.minisP);
                this.minisP.setStyle(Paint.Style.FILL);
                canvas.restore();
            }
        }
        Bitmap bitmap = this.bmFairy[((int) (this.time * 10.0f)) % 2];
        if (bitmap != null) {
            canvas.save();
            if (this.fairyDir < 0.0f) {
                canvas.scale(-1.0f, 1.0f, f, f2);
            }
            float height = bitmap.getHeight();
            float f3 = this.hu;
            blit(canvas, bitmap, f, f2 + (height * f3 * 0.55f), f3 * 1.1f, 1.0f, 1.0f, this.bp);
            canvas.restore();
        }
        // orbiting twinkles
        for (int minisK = 0; minisK < 3; minisK++) {
            double minisAng = (this.time * 2.4f) + (minisK * 2.0943951023931953d);
            float minisTw = ((float) Math.sin((this.time * 9.0f) + (minisK * 2.0f))) * 0.5f + 0.5f;
            minisStar(canvas, f + (((float) Math.cos(minisAng)) * minisHu * 8.0f), f2 + (((float) Math.sin(minisAng)) * minisHu * 4.5f), minisHu * ((minisTw * 0.9f) + 0.5f), minisSparkCols[(minisK + 1) % 4], (minisTw * 150.0f) + 80.0f);
        }
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
        int light = heroMix(i, -1, 0.55f);
        int dark = heroMix(i, -15331286, 0.35f);
        float t = this.time;
        float fr = heroFrenzyK();
        // freshest familiar shot -> recoil + muzzle flash
        float shot = 0.0f;
        for (int k = 0; k < this.famShots.size(); k++) {
            Fx fx = this.famShots.get(k);
            if (fx != null && fx.life < 0.14f) {
                shot = Math.max(shot, 1.0f - (Math.max(0.0f, fx.life) / 0.14f));
            }
        }
        float dx = this.mobX - famX;
        float dy = this.mobY - famY;
        float len = (float) Math.sqrt((dx * dx) + (dy * dy));
        float ux = len > 1.0f ? dx / len : 1.0f;
        float uy = len > 1.0f ? dy / len : 0.0f;
        float bx = famX - (ux * shot * 1.5f * f);
        float by = famY - (uy * shot * 1.5f * f);
        // soft shadow on the ground, smaller and fainter the higher it floats
        float alt = (this.groundY - famY) / Math.max(1.0f, this.hu * 25.0f);
        float sk = Math.max(0.3f, 1.6f - (0.7f * alt));
        heroShadow(canvas, famX, this.groundY + (f * 0.2f), 4.0f * f * sk, f * sk, (int) (110.0f * sk));
        // halo
        float pulse = (float) Math.sin(t * 5.0f);
        float hr = ((0.7f * pulse) + 6.5f + (3.0f * shot)) * f;
        heroGlow(canvas, 3, i, bx, by, hr, hr, (int) ((25.0f * pulse) + 120.0f + (110.0f * shot)));
        // fairy-dust trail shed from the tail (spawn point = where the familiar was when it was shed)
        for (int k2 = 0; k2 < 7; k2++) {
            float ph = ((t * 0.85f) + (k2 / 7.0f)) % 1.0f;
            float t0 = t - (ph / 0.85f);
            float px = (this.heroX - (this.hu * 9.0f)) + (((float) Math.sin(t0 * 1.3f)) * this.hu * 2.0f) + (((ph * 4.5f) + 1.5f) * f) + (((float) Math.sin((t0 * 7.0f) + k2)) * f * 0.8f);
            float py = (this.groundY - (this.hu * 25.0f)) + (((float) Math.sin(t0 * SUPER_END)) * this.hu * 2.5f) + (((ph * 6.0f) + 1.5f) * f);
            float s = f * 0.85f * (1.0f - ph);
            int a = (int) ((1.0f - ph) * 230.0f * ((((float) Math.sin((t * 14.0f) + (k2 * 2.1f))) * 0.4f) + 0.6f));
            if ((k2 & 1) == 0) {
                heroSpark(canvas, px, py, s * 1.3f, light, a);
            } else {
                this.heroPixP.setColor(light);
                this.heroPixP.setAlpha(a);
                canvas.drawRect(px - (s * 0.4f), py - (s * 0.4f), px + (s * 0.4f), py + (s * 0.4f), this.heroPixP);
            }
        }
        // wispy tail, 4 waving segments
        for (int i2 = 4; i2 >= 1; i2--) {
            float f2 = i2;
            float f3 = f2 * f * 0.85f;
            this.heroFxP.setColor(i);
            this.heroFxP.setAlpha(255 - (i2 * 48));
            canvas.drawCircle(bx + (1.3f * f3) + (((float) Math.sin((t * 6.0f) + (f2 * 0.9f))) * f * 0.6f), by + (f3 * 0.9f) + (((float) Math.cos((t * 5.0f) + f2)) * f * 0.25f), (2.35f - (f2 * 0.42f)) * f, this.heroFxP);
        }
        // translucent fairy wings, flapping
        float flap = (float) Math.sin(t * 17.0f);
        this.heroLineP.setStrokeWidth(f * 0.35f);
        for (int side = -1; side <= 1; side += 2) {
            this.heroPath.reset();
            this.heroPath.moveTo(bx + (side * 2.0f * f), by - (0.6f * f));
            this.heroPath.lineTo(bx + (side * 6.2f * f), by - ((3.4f - (flap * 2.0f)) * f));
            this.heroPath.lineTo(bx + (side * 5.6f * f), by + (((flap * 0.6f) + 0.4f) * f));
            this.heroPath.lineTo(bx + (side * 2.2f * f), by + f);
            this.heroPath.close();
            this.heroFxP.setColor(light);
            this.heroFxP.setAlpha(120);
            canvas.drawPath(this.heroPath, this.heroFxP);
            this.heroLineP.setColor(-1);
            this.heroLineP.setAlpha(190);
            canvas.drawPath(this.heroPath, this.heroLineP);
        }
        // body squash & stretch: stretches with vertical speed, squashes on recoil
        float vel = Math.abs((float) Math.cos(t * SUPER_END));
        canvas.save();
        canvas.scale((1.0f - (0.05f * vel)) + (0.16f * shot), ((0.07f * vel) + 1.0f) - (0.12f * shot), bx, by);
        // ears (outlined, inner shade, one twitches now and then)
        boolean tw = t % 3.1f < 0.16f;
        float tdx = tw ? 0.7f * f : 0.0f;
        float tdy = tw ? 0.9f * f : 0.0f;
        float f4 = SUPER_END * f;
        float f6 = by - (1.6f * f);
        float f7 = 2.2f * f;
        float f8 = by - (4.4f * f);
        float f9 = 0.6f * f;
        float f10 = by - (2.8f * f);
        this.heroPath.reset();
        this.heroPath.moveTo(bx - f4, f6);
        this.heroPath.lineTo((bx - f7) + tdx, f8 + tdy);
        this.heroPath.lineTo(bx - f9, f10);
        this.heroPath.close();
        this.heroPath.moveTo(bx + f4, f6);
        this.heroPath.lineTo(bx + f7, f8);
        this.heroPath.lineTo(bx + f9, f10);
        this.heroPath.close();
        this.heroLineP.setStrokeWidth(0.6f * f);
        this.heroLineP.setColor(-15331286);
        canvas.drawPath(this.heroPath, this.heroLineP);
        this.heroFxP.setColor(i);
        canvas.drawPath(this.heroPath, this.heroFxP);
        this.heroPath.reset();
        this.heroPath.moveTo(bx - (2.0f * f), by - (2.1f * f));
        this.heroPath.lineTo((bx - (2.05f * f)) + (tdx * 0.8f), (by - (3.6f * f)) + (tdy * 0.8f));
        this.heroPath.lineTo(bx - (1.15f * f), by - (2.7f * f));
        this.heroPath.close();
        this.heroPath.moveTo(bx + (2.0f * f), by - (2.1f * f));
        this.heroPath.lineTo(bx + (2.05f * f), by - (3.6f * f));
        this.heroPath.lineTo(bx + (1.15f * f), by - (2.7f * f));
        this.heroPath.close();
        this.heroFxP.setColor(dark);
        canvas.drawPath(this.heroPath, this.heroFxP);
        // body: dark rim, shaded crescent, main tone, soft + hard highlight
        this.heroFxP.setColor(-15331286);
        canvas.drawCircle(bx, by, 3.3f * f, this.heroFxP);
        this.heroFxP.setColor(dark);
        canvas.drawCircle(bx, by, 3.0f * f, this.heroFxP);
        this.heroFxP.setColor(i);
        canvas.drawCircle(bx - (0.3f * f), by - (0.4f * f), 2.6f * f, this.heroFxP);
        this.heroFxP.setColor(1442840575);
        float f11 = 1.1f * f;
        canvas.drawCircle(bx - f11, by - (IMPACT * f), f11, this.heroFxP);
        this.heroPixP.setColor(-1);
        this.heroPixP.setAlpha(225);
        canvas.drawRect(bx - (1.75f * f), by - (1.95f * f), bx - (1.15f * f), by - (1.35f * f), this.heroPixP);
        // blush
        this.heroFxP.setColor(-32856);
        this.heroFxP.setAlpha(115);
        this.heroR.set(bx - (2.45f * f), by + (0.65f * f), bx - (1.35f * f), by + (1.15f * f));
        canvas.drawOval(this.heroR, this.heroFxP);
        this.heroR.set(bx + (1.35f * f), by + (0.65f * f), bx + (2.45f * f), by + (1.15f * f));
        canvas.drawOval(this.heroR, this.heroFxP);
        // eyes: blink / squint on shot, happy ^^ in frenzy, otherwise glancing at the monster
        boolean z = t % 3.5f < 0.12f;
        this.heroFxP.setColor(-15331286);
        if (z || shot > 0.35f) {
            float f12 = by - (0.1f * f);
            float f13 = f * 0.5f;
            float f14 = by + (f * 0.35f);
            float f5 = 1.6f * f;
            canvas.drawRect(bx - f5, f12, bx - f13, f14, this.heroFxP);
            canvas.drawRect(bx + f13, f12, bx + f5, f14, this.heroFxP);
        } else if (fr > 0.0f) {
            this.heroLineP.setStrokeWidth(0.42f * f);
            this.heroLineP.setColor(-15331286);
            for (int side2 = -1; side2 <= 1; side2 += 2) {
                float ex = bx + (side2 * 1.05f * f);
                this.heroPath.reset();
                this.heroPath.moveTo(ex - (0.6f * f), by + (0.25f * f));
                this.heroPath.lineTo(ex, by - (0.35f * f));
                this.heroPath.lineTo(ex + (0.6f * f), by + (0.25f * f));
                canvas.drawPath(this.heroPath, this.heroLineP);
            }
        } else {
            float gx = ux * 0.18f * f;
            float gy = uy * 0.12f * f;
            float f15 = 1.05f * f;
            canvas.drawCircle((bx - f15) + gx, by + gy, f9, this.heroFxP);
            canvas.drawCircle(bx + f15 + gx, by + gy, f9, this.heroFxP);
            this.heroFxP.setColor(-1);
            float f16 = (by - (0.25f * f)) + gy;
            float f17 = 0.22f * f;
            canvas.drawCircle((bx - (0.85f * f)) + gx, f16, f17, this.heroFxP);
            canvas.drawCircle(bx + (f * 1.25f) + gx, f16, f17, this.heroFxP);
        }
        if (shot > 0.2f) {
            this.heroFxP.setColor(-15331286);
            canvas.drawCircle(bx + (ux * 0.3f * f), by + (1.15f * f), ((0.35f * shot) + 0.12f) * f, this.heroFxP);
        }
        canvas.restore();
        // tier ornaments: halo at tier 2+, orbiting star at tier 3
        if (min >= 2) {
            float hy = (by - (5.0f * f)) + (((float) Math.sin(t * 3.0f)) * 0.25f * f);
            this.heroR.set(bx - (2.0f * f), hy - (0.55f * f), bx + (2.0f * f), hy + (0.55f * f));
            this.heroLineP.setStrokeWidth(0.5f * f);
            this.heroLineP.setColor(0xFFFFE27A);
            this.heroLineP.setAlpha(((int) (pulse * 25.0f)) + 205);
            canvas.drawOval(this.heroR, this.heroLineP);
        }
        if (min >= 3) {
            double oa = t * 2.4d;
            heroSpark(canvas, bx + (((float) Math.cos(oa)) * 5.2f * f), (by + (((float) Math.sin(oa)) * 1.8f * f)) - (0.6f * f), 0.9f * f, 0xFFFFE27A, 235);
        }
        // muzzle flash toward the monster
        if (shot > 0.0f) {
            float mx = bx + (ux * 3.6f * f);
            float my = by + (uy * 3.6f * f);
            float mr = ((4.0f * shot) + 2.5f) * f;
            heroGlow(canvas, 5, -1, mx, my, mr, mr, (int) (240.0f * shot));
            this.heroLineP.setStrokeWidth(0.45f * f);
            this.heroLineP.setColor(light);
            this.heroLineP.setAlpha((int) (220.0f * shot));
            canvas.drawCircle(mx, my, (((1.0f - shot) * 5.0f) + 1.5f) * f, this.heroLineP);
        }
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
        float u = this.bgS;
        if (this.W <= 0 || this.H <= 0 || !(u > 0f)) return;
        int bi = biome();
        float t = this.time;
        float W = this.W;
        float base = this.groundY + 2.2f * u;
        Paint q = this.bgP;
        int o = bi * 5;
        int cBase = bgGrassCol[o];
        int cStem = bgGrassCol[o + 1];
        int cUp = bgGrassCol[o + 2];
        int cTip = bgGrassCol[o + 3];
        int cSil = bgGrassCol[o + 4];
        // biome props tucked into the grass (flowers / crystals / brambles)
        for (int d = 0; d < bgDecoX.length; d++) {
            float x = (float) Math.floor(bgDecoX[d] * W / u) * u;
            if (bi == 0) {
                int col = bgFlowerCol[d % 3];
                float sw = bgWind(x, t, d * 5 + 3) * u * 0.6f;
                float sh = (3f + d % 2) * u;
                float hx = x + sw;
                float hy = base - sh - u;
                bgGlow(canvas, hx + u * 0.5f, hy + u * 0.5f, u * 2.6f, col, 26f + 22f * bgSin(t * 1.2f + d * 1.9f));
                q.setColor(cStem);
                q.setAlpha(255);
                canvas.drawRect(x, base - sh * 0.5f, x + u, base, q);
                canvas.drawRect(x + sw * 0.5f, base - sh, x + u + sw * 0.5f, base - sh * 0.5f, q);
                q.setColor(col);
                canvas.drawRect(hx - u, hy, hx, hy + u, q);
                canvas.drawRect(hx + u, hy, hx + 2f * u, hy + u, q);
                canvas.drawRect(hx, hy - u, hx + u, hy, q);
                canvas.drawRect(hx, hy + u, hx + u, hy + 2f * u, q);
                q.setColor(0xFFFFF0A0);
                canvas.drawRect(hx, hy, hx + u, hy + u, q);
            } else if (bi == 1) {
                int col = bgMoteCol[d % 4];
                float h = (3f + d % 3) * u;
                float f = 0.5f + 0.5f * bgSin(t * 1.7f + d * 2.3f);
                bgGlow(canvas, x + u, base - h * 0.6f, u * 3.4f, col, 34f + 40f * f);
                q.setColor(bgShade(col, 0.6f));
                q.setAlpha(255);
                canvas.drawRect(x + 2f * u, base - h * 0.5f, x + 3f * u, base, q);
                canvas.drawRect(x - u, base - h * 0.35f, x, base, q);
                q.setColor(col);
                canvas.drawRect(x, base - h, x + 2f * u, base, q);
                canvas.drawRect(x + u, base - h - u, x + 2f * u, base - h, q);
                q.setColor(0xFFE8FCFF);
                q.setAlpha(200);
                canvas.drawRect(x, base - h + u, x + u, base - u, q);
                if (f > 0.9f) bgSparkle(canvas, x + u, base - h - 2f * u, u * 0.7f, 0xFFFFFFFF, bgAl((f - 0.9f) * 10f * 230f));
            } else {
                float sw = bgWind(x, t, d * 5 + 3) * u * 0.35f;
                q.setColor(cSil);
                q.setAlpha(255);
                canvas.drawRect(x, base - 2f * u, x + u, base, q);
                canvas.drawRect(x + u + sw * 0.4f, base - 4f * u, x + 2f * u + sw * 0.4f, base - 2f * u, q);
                canvas.drawRect(x + sw, base - 6f * u, x + u + sw, base - 4f * u, q);
                canvas.drawRect(x - u, base - 3f * u, x, base - 2f * u, q);
                canvas.drawRect(x + 2f * u + sw * 0.6f, base - 5f * u, x + 3f * u + sw * 0.6f, base - 4f * u, q);
                float f = 0.6f + 0.25f * bgSin(t * 5.3f + d * 2.9f) + 0.15f * bgSin(t * 13.1f + d);
                bgGlow(canvas, x + sw + u * 0.5f, base - 6.5f * u, u * 2.2f, 0xFFFF6A3A, 90f * f);
                bgPixel(canvas, x + sw, base - 7f * u, u, 0xFFFFB23E, bgAl(255f * f));
            }
        }
        // swaying blades: staircase bend, darker roots, moon-lit tips
        int n = 26;
        float fz = this.bgFz;
        for (int i = 0; i < n; i++) {
            float x = (i + 0.5f) * W / n + (((i * 37) % 7) - 3) * u * 0.7f;
            int segs = 3 + (i * 5) % 4;
            float sway = bgWind(x, t, i) * u;
            for (int s = 0; s < segs; s++) {
                float k = (s + 1f) / segs;
                float off = sway * k * k;
                q.setColor(s == segs - 1 ? cTip : s == 0 ? cBase : s >= segs - 2 ? cUp : cStem);
                q.setAlpha(255);
                canvas.drawRect(x - u + off, base - (s + 1) * u, x + off, base - s * u, q);
            }
            if (fz > 0.01f) {
                q.setColor(0xFFFFD35A);
                q.setAlpha(bgAl(255f * fz * (0.55f + 0.45f * bgSin(t * 8f + i))));
                canvas.drawRect(x - u + sway, base - segs * u, x + sway, base - (segs - 1) * u, q);
            }
            if (i % 2 == 0) {
                // shorter companion blade leaning the other way
                int s2 = segs - 1;
                for (int s = 0; s < s2; s++) {
                    float k = (s + 1f) / s2;
                    float off = (sway * 0.6f + u * 0.8f) * k * k;
                    q.setColor(s == s2 - 1 ? cUp : cStem);
                    q.setAlpha(255);
                    canvas.drawRect(x + off, base - (s + 1) * u, x + u + off, base - s * u, q);
                }
            }
        }
        // dark foreground tufts framing both edges for depth
        for (int side = 0; side < 2; side++) {
            float dir = side == 0 ? -1f : 1f;
            for (int j = 0; j < 3; j++) {
                float x = side == 0 ? (0.5f + j * 1.7f) * u : W - (1.5f + j * 1.7f) * u;
                int segs = 6 + (j * 3 + side) % 4;
                float sway = bgWind(x, t, 40 + j + side * 3) * u * 0.8f + dir * (2.4f - j * 0.7f) * u;
                q.setColor(cSil);
                q.setAlpha(255);
                for (int s = 0; s < segs; s++) {
                    float k = (s + 1f) / segs;
                    float off = sway * k * k;
                    canvas.drawRect(x - u + off, base + u - (s + 1) * u, x + off + (s < 2 ? u : 0f), base + u - s * u, q);
                }
            }
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
                float hudCp = (this.beatPulse * 0.18f) + 1.0f;
                boolean hudHot = this.combo >= 25;
                this.tp.setTextAlign(Paint.Align.LEFT);
                this.tp.setTextSize(this.pad * 1.25f * hudCp);
                this.tp.setColor(-15331286);
                String str = "КОМБО " + this.combo;
                float f7 = this.pad;
                canvas.drawText(str, 1.7f * f7, f6 - (f7 * 0.2f), this.tp);
                this.tp.setColor(-1);
                float f8 = this.pad;
                canvas.drawText(str, f8 * 1.5f, f6 - (f8 * 0.4f), this.tp);
                this.tp.setTextSize(this.pad * 1.6f * hudCp);
                String str3 = "x" + String.format(Locale.US, "%.2f", Double.valueOf(comboMult()));
                float f9 = this.pad;
                float hudMx = f9 * 1.5f;
                float hudMy = (f9 * 1.8f) + f6;
                if (hudHot) {
                    // flickering heat aura around a hot multiplier
                    float hudFl = (float) ((Math.sin(this.time * 13.0f) * 0.5d) + 0.5d);
                    this.tp.setColor(-38342);
                    this.tp.setAlpha((int) ((hudFl * 60.0f) + 50.0f));
                    float hudO = f9 * 0.28f;
                    canvas.drawText(str3, hudMx - hudO, hudMy, this.tp);
                    canvas.drawText(str3, hudMx + hudO, hudMy, this.tp);
                    canvas.drawText(str3, hudMx, hudMy - hudO, this.tp);
                }
                this.tp.setColor(-15331286);
                this.tp.setAlpha(220);
                canvas.drawText(str3, hudMx + (f9 * 0.22f), hudMy + (f9 * 0.22f), this.tp);
                this.tp.setColor(hudHot ? -26051 : -11430);
                canvas.drawText(str3, hudMx, hudMy, this.tp);
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
                    int hudDc = z ? i == 0 ? -11430 : -1 : 1442840575;
                    if (z) {
                        f13 *= (this.beatPulse * 0.6f) + f10;
                        // beat halo
                        this.hudFill.setShader(null);
                        this.hudFill.setStyle(Paint.Style.FILL);
                        this.hudFill.setColor(i == 0 ? -10944 : -3355393);
                        this.hudFill.setAlpha((int) ((this.beatPulse * 110.0f) + 40.0f));
                        canvas.drawCircle(f12, f6, f13 * 2.3f, this.hudFill);
                        this.hudFill.setAlpha((int) ((this.beatPulse * 80.0f) + 30.0f));
                        canvas.drawCircle(f12, f6, f13 * 1.6f, this.hudFill);
                    }
                    // pixel drop shadow
                    float hudSh = Math.max(1.0f, f11 * 0.22f);
                    this.hudFill.setShader(null);
                    this.hudFill.setStyle(Paint.Style.FILL);
                    this.hudFill.setColor(-16777216);
                    this.hudFill.setAlpha(110);
                    canvas.drawRect((f12 - f13) + hudSh, (f6 - f13) + hudSh, f12 + f13 + hudSh, f13 + f6 + hudSh, this.hudFill);
                    this.p.setColor(hudDc);
                    canvas.drawRect(f12 - f13, f6 - f13, f12 + f13, f13 + f6, this.p);
                    if (z) {
                        // bevel highlight pixel
                        this.hudFill.setColor(-1);
                        this.hudFill.setAlpha(200);
                        float hudHs = f13 * 0.45f;
                        canvas.drawRect(f12 - f13, f6 - f13, (f12 - f13) + hudHs, (f6 - f13) + hudHs, this.hudFill);
                    }
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
                hudGlow(canvas, this.isekaiBtn, this.pad, -24512, (0.5f * sin) + 0.35f);
                hudPing(canvas, this.isekaiBtn, this.pad, -10944, this.time * 1.2f);
                hudPanel(canvas, this.isekaiBtn, this.pad, Color.rgb((int) ((50.0f * sin) + 200.0f), (int) ((sin * 40.0f) + 120.0f), 40));
                hudShine(canvas, this.isekaiBtn, this.pad, (this.time % 1.8f) / 0.8f, 85);
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setTextSize(this.pad * 1.5f);
                float centerX = this.isekaiBtn.centerX();
                float f14 = this.pad;
                hudText(canvas, "ИСЕКАЙ!", centerX + (1.5f * f14), (f14 * 0.7f) + f6, -1, 255);
                float f15 = this.pad * 0.11f;
                canvas.save();
                canvas.scale(-1.0f, f, this.isekaiBtn.left + (this.pad * SUPER_END), 0.0f);
                blit(canvas, this.bmTruck, this.isekaiBtn.left + (this.pad * SUPER_END), f6 + ((this.bmTruck.getHeight() * f15) / 2.0f), f15, 1.0f, 1.0f, this.bp);
                canvas.restore();
            } else if (this.isekaiGauge > 0.0f || this.taps > 30) {
                float f16 = this.isekaiBtn.left + (this.pad * 4.0f);
                float f17 = this.isekaiBtn.right;
                float f18 = this.pad * 1.1f;
                float f19 = f18 / 2.0f;
                float f20 = f6 - f19;
                float f21 = f6 + f19;
                float f22 = f17 - f16;
                float hudG = Math.max(0.0f, Math.min(100.0f, this.isekaiGauge)) / 100.0f;
                float hudFe = f16 + (f22 * hudG);
                float hudB = Math.max(1.0f, this.pad * 0.22f);
                this.hudFill.setShader(null);
                this.hudFill.setStyle(Paint.Style.FILL);
                // dark pixel frame + drop shadow
                this.hudFill.setColor(-16777216);
                this.hudFill.setAlpha(90);
                canvas.drawRect(f16 - hudB, f20 + hudB, f17 + (hudB * 2.0f), f21 + (hudB * 2.5f), this.hudFill);
                this.hudFill.setColor(-15791588);
                canvas.drawRect(f16 - hudB, f20 - hudB, f17 + hudB, f21 + hudB, this.hudFill);
                this.p.setColor(-1441394646);
                canvas.drawRect(f16, f20, f17, f21, this.p);
                if (f22 > 0.0f) {
                    if (this.hudGaugeSh == null || this.hudGaugeK0 != f16 || this.hudGaugeK1 != f17) {
                        this.hudGaugeSh = new android.graphics.LinearGradient(f16, 0.0f, f17, 0.0f, new int[]{-2080736, -24512, -11430}, (float[]) null, Shader.TileMode.CLAMP);
                        this.hudGaugeK0 = f16;
                        this.hudGaugeK1 = f17;
                    }
                    this.hudFill.setShader(this.hudGaugeSh);
                    this.hudFill.setColor(-1);
                    canvas.drawRect(f16, f20, hudFe, f21, this.hudFill);
                    this.hudFill.setShader(null);
                    if (hudG > 0.8f) {
                        this.hudFill.setColor(-1);
                        this.hudFill.setAlpha((int) (((Math.sin(this.time * 10.0f) * 0.5d) + 0.5d) * 70.0d * ((hudG - 0.8f) / 0.2f)));
                        canvas.drawRect(f16, f20, hudFe, f21, this.hudFill);
                    }
                    this.p.setColor(1442840575);
                    canvas.drawRect(f16, f20, hudFe, f20 + (this.pad * 0.3f), this.p);
                    this.hudFill.setColor(-16777216);
                    this.hudFill.setAlpha(70);
                    canvas.drawRect(f16, f21 - (this.pad * 0.25f), hudFe, f21, this.hudFill);
                    // travelling shine inside the filled part
                    float hudSx = f16 + ((((this.time * 0.6f) % 1.3f) - 0.15f) * f22);
                    float hudSe = Math.min(hudFe, (this.pad * 1.1f) + hudSx);
                    float hudSs = Math.max(f16, hudSx);
                    if (hudSe > hudSs) {
                        this.hudFill.setColor(-1);
                        this.hudFill.setAlpha(95);
                        canvas.drawRect(hudSs, f20, hudSe, f21, this.hudFill);
                    }
                    // segment ticks
                    this.hudFill.setColor(-15791588);
                    this.hudFill.setAlpha(130);
                    float hudTw = Math.max(1.0f, this.pad * 0.14f);
                    for (int k = 1; k < 10; k++) {
                        float hudTx = f16 + ((f22 * k) / 10.0f);
                        canvas.drawRect(hudTx - (hudTw * 0.5f), f20 + (k % 5 == 0 ? 0.0f : f18 * 0.35f), hudTx + (hudTw * 0.5f), f21, this.hudFill);
                    }
                    // flickering leading spark
                    if (hudG > 0.0f && hudG < 1.0f) {
                        float hudSp = this.pad * 0.3f;
                        this.hudFill.setColor(-1);
                        this.hudFill.setAlpha((int) ((Math.sin(this.time * 20.0f) * 50.0d) + 200.0d));
                        canvas.drawRect(hudFe - hudSp, f20 - (hudSp * 0.5f), hudFe + (hudSp * 0.3f), f21 + (hudSp * 0.5f), this.hudFill);
                    }
                }
                float f23 = this.pad * 0.09f;
                canvas.save();
                canvas.scale(-1.0f, f, this.isekaiBtn.left + (this.pad * 2.0f), 0.0f);
                blit(canvas, this.bmTruck, this.isekaiBtn.left + (this.pad * 2.0f), f6 + ((this.bmTruck.getHeight() * f23) / 2.0f), f23, 1.0f, 1.0f, this.bp);
                canvas.restore();
            }
            this.tp.setTypeface(Typeface.DEFAULT);
            this.tp.setAlpha(255);
        }
    }

    void drawHeader(Canvas canvas, long j) {
        float hb = this.headerB;
        float d = this.pad;
        int bi = biome();
        int hudAcc = hudBiomeTint(bi);
        // ---- background: biome-tinted vertical gradient, scanlines, bevel edge and drop shadow ----
        if (this.hudHeadSh == null || this.hudHeadKey != hb || this.hudHeadKeyPad != d || this.hudHeadBiome != bi) {
            float hbk = Math.max(1.0f, hb);
            this.hudHeadSh = new android.graphics.LinearGradient(0.0f, 0.0f, 0.0f, hbk, new int[]{hudMix(-14077872, hudAcc, 0.16f), -14937037, -15462874}, new float[]{0.0f, 0.55f, 1.0f}, Shader.TileMode.CLAMP);
            this.hudHeadDropSh = new android.graphics.LinearGradient(0.0f, hbk, 0.0f, hbk + Math.max(1.0f, d * 1.6f), -2013265920, 0, Shader.TileMode.CLAMP);
            this.hudHeadKey = hb;
            this.hudHeadKeyPad = d;
            this.hudHeadBiome = bi;
        }
        this.hudFill.setStyle(Paint.Style.FILL);
        this.hudFill.setColor(-1);
        this.hudFill.setShader(this.hudHeadSh);
        canvas.drawRect(0.0f, 0.0f, this.W, hb, this.hudFill);
        this.hudFill.setShader(this.hudHeadDropSh);
        canvas.drawRect(0.0f, hb, this.W, hb + (d * 1.6f), this.hudFill);
        this.hudFill.setShader(null);
        if (d > 0.0f) {
            this.hudFill.setColor(-16777216);
            this.hudFill.setAlpha(22);
            float hudStep = d * 0.6f;
            for (float hudY = hudStep * 0.5f; hudY < hb; hudY += hudStep) {
                canvas.drawRect(0.0f, hudY, this.W, hudY + Math.max(1.0f, d * 0.12f), this.hudFill);
            }
        }
        this.hudFill.setColor(hudAcc);
        this.hudFill.setAlpha(150);
        canvas.drawRect(0.0f, hb - Math.max(1.0f, d * 0.25f), this.W, hb, this.hudFill);
        this.hudFill.setColor(-16777216);
        this.hudFill.setAlpha(200);
        canvas.drawRect(0.0f, hb, this.W, hb + Math.max(1.0f, d * 0.15f), this.hudFill);
        // ---- stage progress pips (10 per boss cycle) along the bottom edge ----
        if (this.W > 0 && d > 0.0f) {
            int hudSeg = ((this.stage - 1) % 10 + 10) % 10;
            float hudL = d * 2.0f;
            float hudRr = this.W - (d * 2.0f);
            float hudGap = d * 0.35f;
            float hudSw = ((hudRr - hudL) - (hudGap * 9.0f)) / 10.0f;
            float hudPb = hb - (d * 0.45f);
            float hudPt = hudPb - (d * 0.4f);
            if (hudSw > 0.0f) {
                for (int k = 0; k < 10; k++) {
                    float x0 = hudL + (k * (hudSw + hudGap));
                    boolean boss = k == 9;
                    int c;
                    int a;
                    if (k < hudSeg) {
                        c = hudAcc;
                        a = 200;
                    } else if (k == hudSeg) {
                        c = boss ? -46502 : -1;
                        a = (int) ((Math.sin(this.time * 5.0f) * 70.0d) + 170.0d);
                    } else {
                        c = boss ? -2078616 : -1;
                        a = boss ? 110 : 40;
                    }
                    this.hudFill.setColor(c);
                    this.hudFill.setAlpha(a);
                    canvas.drawRect(x0, hudPt, x0 + hudSw, hudPb, this.hudFill);
                }
            }
        }
        // ---- top row: life stage, life/stage text, icon buttons ----
        this.tp.setTextAlign(Paint.Align.LEFT);
        this.tp.setTextSize(d * 2.2f);
        this.tp.setFakeBoldText(true);
        hudText(canvas, LIFE_NAME[this.lifeStageIdx], d * 2.0f, hb * 0.33f, -1, 255);
        this.tp.setFakeBoldText(false);
        this.tp.setTextAlign(Paint.Align.RIGHT);
        this.tp.setTextSize(d * 1.7f);
        hudText(canvas, "Жизнь №" + this.lifeNo + " · Этап " + this.stage, this.W - (d * 13.5f), hb * 0.33f, -4609816, 255);
        float hudBy = (hb * 0.34f) - (d * 0.85f);
        float hudBh = Math.min(d * 2.2f, hb * 0.2f);
        float hudBw = Math.min(d * 2.4f, (this.muteBtn.width() * 0.5f) - (d * 0.6f));
        if (hudBw > 0.0f && hudBh > 0.0f) {
            this.hudBtnR.set(this.muteBtn.centerX() - hudBw, hudBy - hudBh, this.muteBtn.centerX() + hudBw, hudBy + hudBh);
            hudPanel(canvas, this.hudBtnR, d * 0.6f, -14016440);
            this.hudBtnR.set(this.partyBtn.centerX() - hudBw, hudBy - hudBh, this.partyBtn.centerX() + hudBw, hudBy + hudBh);
            hudPanel(canvas, this.hudBtnR, d * 0.6f, -14016440);
        }
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setTextSize(d * 2.4f);
        this.tp.setColor(-1);
        canvas.drawText("⚙", this.muteBtn.centerX(), hb * 0.34f, this.tp);
        canvas.drawText("👭", this.partyBtn.centerX(), hb * 0.34f, this.tp);
        if (Integer.bitCount(this.compMask) > 0 && this.compSel < 0) {
            float f = this.partyBtn.right;
            float hudDx = f - d;
            float hudDy = 1.4f * d;
            float hudPh = (this.time * 1.2f) % 1.0f;
            this.hudLine.setStyle(Paint.Style.STROKE);
            this.hudLine.setStrokeWidth(Math.max(1.0f, d * 0.25f));
            this.hudLine.setColor(-46502);
            this.hudLine.setAlpha((int) ((1.0f - hudPh) * 200.0f));
            canvas.drawCircle(hudDx, hudDy, d * ((1.4f * hudPh) + 0.6f), this.hudLine);
            this.p.setColor(-46502);
            canvas.drawCircle(hudDx, hudDy, d * 0.6f * ((((float) Math.sin(this.time * 6.0f)) * 0.15f) + 1.0f), this.p);
            this.p.setColor(-1);
            this.p.setAlpha(170);
            canvas.drawRect(hudDx - (d * 0.3f), hudDy - (d * 0.3f), hudDx - (d * 0.05f), hudDy - (d * 0.05f), this.p);
            this.p.setAlpha(255);
        }
        // ---- resource row: inset chips, glowing icons, pulsing counters ----
        float f3 = hb * 0.66f;
        float f5 = f3 + (0.25f * d);
        float f6 = d * 0.3f;
        float hudCt = f5 - (d * 3.3f);
        float hudCb = f5 + (d * 0.5f);
        this.tp.setTypeface(this.pixTf);
        this.tp.setFakeBoldText(false);
        this.tp.setTextAlign(Paint.Align.LEFT);
        this.tp.setTextSize(d * 1.75f);
        String hudGold = fmt(this.gold);
        String hudMana = fmt(this.mana);
        String hudSouls = fmt(this.souls);
        float hudManaIconW = this.bmMana != null ? (this.bmMana.getWidth() * f6) / 2.0f : d * 2.7f;
        float hudSoulIconW = this.bmSoul != null ? (this.bmSoul.getWidth() * f6) / 2.0f : d * 2.7f;
        float hudManaX = this.W * 0.38f;
        float hudManaTx = hudManaX + hudManaIconW + (d * 0.6f);
        float hudSoulsW = this.tp.measureText(hudSouls);
        float hudSoulX = ((this.W - (d * SUPER_END)) - hudSoulsW) - hudSoulIconW;
        float hudSoulL = (hudSoulX - hudSoulIconW) - (d * 0.5f);
        float hudGoldR = Math.min((d * 5.3f) + this.tp.measureText(hudGold) + (d * 0.8f), (hudManaX - hudManaIconW) - (d * 0.6f));
        float hudManaR = Math.min(hudManaTx + this.tp.measureText(hudMana) + (d * 0.8f), hudSoulL - (d * 0.5f));
        hudChip(canvas, d * 1.0f, hudCt, hudGoldR, hudCb, -11430);
        hudChip(canvas, (hudManaX - hudManaIconW) - (d * 0.5f), hudCt, hudManaR, hudCb, -4609793);
        hudChip(canvas, hudSoulL, hudCt, this.W - (d * 1.0f), hudCb, -8395009);
        // souls pulse when the count grows (visual-only tracking)
        float hudDt = this.hudPulseLast < 0.0f ? 0.0f : Math.max(0.0f, Math.min(0.1f, this.time - this.hudPulseLast));
        this.hudPulseLast = this.time;
        this.hudSoulPulse = Math.max(0.0f, this.hudSoulPulse - (hudDt * 1.6f));
        if (this.hudLastSouls >= 0.0d && this.souls > this.hudLastSouls) {
            this.hudSoulPulse = 1.0f;
        }
        this.hudLastSouls = this.souls;
        float gp = this.goldPulse;
        float hudIconCy = f5 - (d * 1.5f);
        boolean hudMeteorOk = j >= this.meteorReadyAt && this.mana >= meteorCost();
        hudIconGlow(canvas, 0, d * 3.0f, hudIconCy, d * ((gp * 1.6f) + 2.8f), (((float) Math.sin(this.time * 2.0f)) * 0.12f) + 0.35f + (gp * 0.6f));
        hudIconGlow(canvas, 1, hudManaX, hudIconCy, d * (hudMeteorOk ? 3.4f : 2.8f), hudMeteorOk ? (((float) Math.sin(this.time * 4.0f)) * 0.2f) + 0.6f : 0.28f);
        hudIconGlow(canvas, 2, hudSoulX, hudIconCy, d * ((this.hudSoulPulse * 1.6f) + 2.8f), (((float) Math.sin((this.time * 1.6f) + 1.0f)) * 0.1f) + 0.3f + (this.hudSoulPulse * 0.6f));
        float hudCoinBob = gp > 0.0f ? -gp * d * 0.5f : 0.0f;
        if (this.bmCoin != null) {
            blit(canvas, this.bmCoin, d * 3.0f, f5 + hudCoinBob, f6, 1.0f, 1.0f, this.bp);
        }
        if (gp > 0.05f) {
            // sparkle burst around the coin while gold pulses
            for (int k = 0; k < 4; k++) {
                double hudAng = (this.time * 3.0f) + (k * 1.5707963267948966d);
                float hudRad = d * ((1.0f - gp) * 1.6f + 1.6f);
                hudSparkle(canvas, (d * 3.0f) + (((float) Math.cos(hudAng)) * hudRad), hudIconCy + (((float) Math.sin(hudAng)) * hudRad * 0.8f), d * 0.45f * gp, k % 2 == 0 ? -1 : -10944, (int) (gp * 255.0f));
            }
        }
        int hudGc = gp > 0.0f ? Color.rgb(255, ((int) (44.0f * gp)) + 211, ((int) (gp * 165.0f)) + 90) : -11430;
        this.tp.setTextSize(d * 1.75f * ((gp * 0.18f) + 1.0f));
        hudText(canvas, hudGold, d * 5.3f, f3, hudGc, 255);
        this.tp.setTextSize(d * 1.75f);
        if (this.bmMana != null) {
            blit(canvas, this.bmMana, hudManaX, f5, f6, 1.0f, 1.0f, this.bp);
        }
        hudText(canvas, hudMana, hudManaTx, f3, -4609793, 255);
        this.tp.setTextAlign(Paint.Align.RIGHT);
        if (this.hudSoulPulse > 0.0f) {
            this.tp.setTextSize(d * 1.75f * ((this.hudSoulPulse * 0.15f) + 1.0f));
        }
        hudText(canvas, hudSouls, this.W - (d * 2.0f), f3, hudMix(-8395009, -1, this.hudSoulPulse * 0.7f), 255);
        this.tp.setTextSize(d * 1.75f);
        if (this.bmSoul != null) {
            blit(canvas, this.bmSoul, hudSoulX, f5 - (this.hudSoulPulse * d * 0.4f), f6, 1.0f, 1.0f, this.bp);
        }
        this.tp.setFakeBoldText(false);
        this.tp.setTypeface(Typeface.DEFAULT);
        // ---- stats line, with a burning band during frenzy ----
        this.tp.setTextSize(d * SUPER_IMPACT);
        this.tp.setTextAlign(Paint.Align.LEFT);
        boolean z = j < this.frenzyUntil;
        String str = "+" + fmt(autoDps()) + " урона/с   +" + fmt(manaSec()) + " маны/с   сила ×" + fmt(soulMult() * achMult()) + (this.stars > 0 ? "  ✦" + this.stars : "");
        if (z) {
            str = "🔥 ЯРОСТЬ ×3 (" + (((this.frenzyUntil - j) / 1000) + 1) + "с)   " + str;
            float hudFz = (float) ((Math.sin(this.time * 9.0f) * 0.5d) + 0.5d);
            float hudFy = 0.9f * hb;
            this.hudFill.setShader(null);
            this.hudFill.setStyle(Paint.Style.FILL);
            this.hudFill.setColor(-38342);
            this.hudFill.setAlpha((int) ((hudFz * 30.0f) + 30.0f));
            canvas.drawRect(0.0f, hudFy - (d * 1.6f), this.W, (d * 0.55f) + hudFy, this.hudFill);
            this.hudFill.setColor(-10944);
            this.hudFill.setAlpha((int) ((hudFz * 60.0f) + 60.0f));
            canvas.drawRect(0.0f, hudFy - (d * 1.6f), this.W, (hudFy - (d * 1.6f)) + Math.max(1.0f, d * 0.15f), this.hudFill);
            // flickering ember pixels along the band
            for (int k = 0; k < 8; k++) {
                float hudEp = ((this.time * 0.9f) + (k * 0.31f)) % 1.0f;
                float hudEx = ((((k * 0.137f) + 0.05f) % 1.0f) * this.W) + (((float) Math.sin((this.time * 3.0f) + k)) * d);
                float hudEy = ((d * 0.55f) + hudFy) - (hudEp * d * 2.2f);
                this.hudFill.setColor(k % 2 == 0 ? -10944 : -38342);
                this.hudFill.setAlpha((int) ((1.0f - hudEp) * 200.0f));
                float hudEs = d * 0.22f;
                canvas.drawRect(hudEx - hudEs, hudEy - hudEs, hudEx + hudEs, hudEy + hudEs, this.hudFill);
            }
        }
        float f8 = this.pad;
        hudFitText(canvas, str, f8 * 2.0f, 0.9f * this.headerB, this.W - (f8 * 4.0f), z ? -25990 : -7569238, 255);
    }

    // ===== gfx(hero): caches & helpers for the hero / familiar / princess (all allocation happens here, never per frame) =====
    final Paint heroGlowP = new Paint(1);
    final Paint heroShP = heroShaderPaint(new RadialGradient(0.0f, 0.0f, 100.0f,
            new int[]{0xA8000000, 0x70000000, 0x2A000000, 0x00000000}, new float[]{0.0f, 0.4f, 0.75f, 1.0f}, Shader.TileMode.CLAMP));
    final Paint heroFxP = new Paint(1);
    final Paint heroPixP = new Paint();
    final Paint heroLineP = heroStrokePaint();
    final Paint heroSilP = heroBitmapPaint();
    final Path heroPath = new Path();
    final Path heroRays = heroRayPath(12, 0.065f);
    final RectF heroR = new RectF();
    final RadialGradient[] heroGrad = new RadialGradient[8];
    final int[] heroGradKey = new int[8];
    final PorterDuffColorFilter[] heroCfs = new PorterDuffColorFilter[6];
    final int[] heroCfKey = new int[6];
    static final int[] heroCompGlow = {0xFFFF6FB5, 0xFF9FD4FF, 0xFFFFB04A, 0xFF86E08F, 0xFFFFD36B};
    static final float[] heroDirX = {1.0f, 0.5f, -0.5f, -1.0f, -0.5f, 0.5f};
    static final float[] heroDirY = {0.0f, 0.866f, 0.866f, 0.0f, -0.866f, -0.866f};
    float heroBubbleLast;
    float heroBubbleBorn = -99.0f;

    static Paint heroShaderPaint(Shader shader) {
        Paint paint = new Paint(1);
        paint.setShader(shader);
        return paint;
    }

    static Paint heroStrokePaint() {
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }

    static Paint heroBitmapPaint() {
        Paint paint = new Paint();
        paint.setFilterBitmap(false);
        return paint;
    }

    static Path heroRayPath(int n, float half) {
        Path path = new Path();
        for (int k = 0; k < n; k++) {
            double a = (k * 6.283185307179586d) / n;
            path.moveTo(0.0f, 0.0f);
            path.lineTo((float) (Math.cos(a - half) * 100.0d), (float) (Math.sin(a - half) * 100.0d));
            path.lineTo((float) (Math.cos(a + half) * 100.0d), (float) (Math.sin(a + half) * 100.0d));
            path.close();
        }
        return path;
    }

    static float heroHash(int n) {
        int h = n * 374761393 + 668265263;
        h = (h ^ (h >>> 13)) * 1274126177;
        h ^= h >>> 16;
        return (h & 16777215) / 16777216.0f;
    }

    static int heroMix(int a, int b, float k) {
        int ar = (a >> 16) & 255;
        int ag = (a >> 8) & 255;
        int ab = a & 255;
        return -16777216 | (((int) (ar + ((((b >> 16) & 255) - ar) * k))) << 16) | (((int) (ag + ((((b >> 8) & 255) - ag) * k))) << 8) | ((int) (ab + (((b & 255) - ab) * k)));
    }

    static float heroBackOut(float x) {
        float y = x - 1.0f;
        return 1.0f + (2.70158f * y * y * y) + (1.70158f * y * y);
    }

    static float heroDashK(float t) {
        if (t < 0.3f) {
            return 0.0f;
        }
        if (t < 0.45f) {
            return (t - 0.3f) / 0.15f;
        }
        if (t < 1.75f) {
            return 1.0f;
        }
        return Math.max(0.0f, 1.0f - ((t - 1.75f) / 0.4f));
    }

    static float heroLungeK(float c) {
        if (!(c > 0.0f)) {
            return 0.0f;
        }
        float x = Math.min(1.0f, c);
        return x * x * (3.0f - (2.0f * x));
    }

    float heroFrenzyK() {
        long left = this.frenzyUntil - SystemClock.uptimeMillis();
        if (left <= 0) {
            return 0.0f;
        }
        float k = Math.min(1.0f, left / 500.0f);
        if (left < 2000 && (((int) (this.time * 8.0f)) & 1) == 0) {
            k *= 0.5f;
        }
        return k;
    }

    int heroStaffColor(float fr) {
        if (fr > 0.0f) {
            return 0xFFFF7A1E;
        }
        int i = this.lifeStageIdx;
        return i == 0 ? -5720 : i >= 4 ? -5213953 : -19906;
    }

    RadialGradient heroGradient(int slot, int color) {
        if (this.heroGrad[slot] == null || this.heroGradKey[slot] != color) {
            int rgb = color & 16777215;
            this.heroGrad[slot] = new RadialGradient(0.0f, 0.0f, 100.0f, new int[]{-16777216 | rgb, -1728053248 | rgb, 939524096 | rgb, rgb}, new float[]{0.0f, 0.35f, 0.7f, 1.0f}, Shader.TileMode.CLAMP);
            this.heroGradKey[slot] = color;
        }
        return this.heroGrad[slot];
    }

    void heroGlow(Canvas c, int slot, int color, float x, float y, float rx, float ry, int alpha) {
        if (!(rx > 0.5f) || !(ry > 0.5f) || alpha <= 0) {
            return;
        }
        this.heroGlowP.setShader(heroGradient(slot, color));
        this.heroGlowP.setAlpha(Math.min(255, alpha));
        c.save();
        c.translate(x, y);
        c.scale(rx / 100.0f, ry / 100.0f);
        c.drawCircle(0.0f, 0.0f, 100.0f, this.heroGlowP);
        c.restore();
    }

    void heroShadow(Canvas c, float x, float y, float rx, float ry, int alpha) {
        if (!(rx > 0.5f) || !(ry > 0.5f) || alpha <= 0) {
            return;
        }
        this.heroShP.setAlpha(Math.min(255, alpha));
        c.save();
        c.translate(x, y);
        c.scale(rx / 100.0f, ry / 100.0f);
        c.drawCircle(0.0f, 0.0f, 100.0f, this.heroShP);
        c.restore();
        this.heroFxP.setColor(-16777216);
        this.heroFxP.setAlpha(Math.min(255, alpha) / 4);
        this.heroR.set(x - (rx * 0.5f), y - (ry * 0.5f), x + (rx * 0.5f), y + (ry * 0.5f));
        c.drawOval(this.heroR, this.heroFxP);
    }

    void heroSilhouette(Canvas c, Bitmap b, float x, float y, float s, float sx, float sy, int slot, int color, int alpha) {
        if (b == null || alpha <= 0) {
            return;
        }
        if (this.heroCfs[slot] == null || this.heroCfKey[slot] != color) {
            this.heroCfs[slot] = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
            this.heroCfKey[slot] = color;
        }
        this.heroSilP.setColorFilter(this.heroCfs[slot]);
        this.heroSilP.setAlpha(Math.min(255, alpha));
        blit(c, b, x, y, s, sx, sy, this.heroSilP);
    }

    void heroSpark(Canvas c, float x, float y, float s, int color, int alpha) {
        if (!(s > 0.3f) || alpha <= 0) {
            return;
        }
        int a = Math.min(255, alpha);
        float t = Math.max(1.0f, s * 0.36f) * 0.5f;
        this.heroPixP.setColor(color);
        this.heroPixP.setAlpha(a);
        c.drawRect(x - s, y - t, x + s, y + t, this.heroPixP);
        c.drawRect(x - t, y - s, x + t, y - t, this.heroPixP);
        c.drawRect(x - t, y + t, x + t, y + s, this.heroPixP);
        this.heroPixP.setColor(-1);
        this.heroPixP.setAlpha(a);
        c.drawRect(x - t, y - t, x + t, y + t, this.heroPixP);
    }

    void heroHeartShape(Canvas c, float x, float y, float u, Paint paint) {
        c.drawRect(x - (2.5f * u), y - (3.0f * u), x - (0.5f * u), y - (2.0f * u), paint);
        c.drawRect(x + (0.5f * u), y - (3.0f * u), x + (2.5f * u), y - (2.0f * u), paint);
        c.drawRect(x - (3.5f * u), y - (2.0f * u), x + (3.5f * u), y, paint);
        c.drawRect(x - (2.5f * u), y, x + (2.5f * u), y + u, paint);
        c.drawRect(x - (1.5f * u), y + u, x + (1.5f * u), y + (2.0f * u), paint);
        c.drawRect(x - (0.5f * u), y + (2.0f * u), x + (0.5f * u), y + (3.0f * u), paint);
    }

    void heroHeart(Canvas c, float x, float y, float u, int color, int alpha) {
        if (!(u > 0.2f) || alpha <= 0) {
            return;
        }
        int a = Math.min(255, alpha);
        this.heroPixP.setColor(-15331286);
        this.heroPixP.setAlpha((a * 3) / 5);
        heroHeartShape(c, x + u, y + u, u, this.heroPixP);
        this.heroPixP.setColor(color);
        this.heroPixP.setAlpha(a);
        heroHeartShape(c, x, y, u, this.heroPixP);
        this.heroPixP.setColor(-1);
        this.heroPixP.setAlpha(a);
        c.drawRect(x - (2.5f * u), y - (2.0f * u), x - (1.5f * u), y - u, this.heroPixP);
    }

    void heroGround(Canvas c, float f, float x, float lift, float shA, float shW) {
        float g = this.groundY;
        float h = Math.min(1.0f, lift / Math.max(1.0f, this.hu * 16.0f));
        float k = 1.0f - (h * 0.55f);
        float cast = this.heroCast;
        float fr = heroFrenzyK();
        heroShadow(c, x, g + (f * 0.2f), 11.5f * f * shW * k, 2.5f * f * k, (int) (255.0f * shA * (1.0f - (h * 0.6f))));
        float lk = heroLungeK(cast);
        float pool = ((cast * 120.0f) + 75.0f + (((float) Math.sin(this.time * 5.0f)) * 18.0f)) * (1.0f - h) * shA;
        heroGlow(c, 1, heroStaffColor(fr), x + ((11.0f + (lk * 2.6f)) * f), g + (f * 0.3f), ((cast * 3.0f) + 12.0f) * f, ((cast * 0.6f) + 2.4f) * f, (int) pool);
        if (fr > 0.0f) {
            heroGlow(c, 2, 0xFFFF6A1A, x, g + (f * 0.3f), (((float) Math.sin(this.time * 11.0f)) + 15.0f) * f, 3.2f * f, (int) (180.0f * fr * (1.0f - h)));
        }
        if (rhythmOn() && this.beatPulse > 0.01f) {
            float b = this.beatPulse;
            float rx = (((1.0f - b) * 9.0f) + 7.0f) * f;
            this.heroR.set(x - rx, g - (rx * 0.2f), x + rx, g + (rx * 0.2f));
            this.heroLineP.setStrokeWidth(f * 0.6f);
            this.heroLineP.setColor(-45149);
            this.heroLineP.setAlpha((int) (b * 170.0f));
            c.drawOval(this.heroR, this.heroLineP);
        }
    }

    void heroDashTrail(Canvas c, Bitmap b, float f, float span, float d0) {
        int gc = heroMix(ROBE[this.lifeStageIdx], -1, 0.35f);
        for (int k = 4; k >= 1; k--) {
            float dk = heroDashK(this.superT - (k * 0.035f));
            if (Math.abs(dk - d0) >= 0.01f) {
                heroSilhouette(c, b, this.heroX + (span * dk), this.groundY + f, f, 1.0f, 1.0f, 2, gc, (int) ((1.0f - (k / 5.0f)) * 170.0f));
            }
        }
        float d1 = heroDashK(this.superT - 0.14f);
        if (Math.abs(d1 - d0) > 0.02f) {
            float xa = this.heroX + (span * Math.min(d0, d1));
            float xb = this.heroX + (span * Math.max(d0, d1));
            for (int k2 = 0; k2 < 6; k2++) {
                float ly = this.groundY - (((k2 * 3.6f) + 2.5f) * f);
                float in = (xb - xa) * 0.35f * heroHash((k2 * 7) + 3);
                this.heroPixP.setColor(k2 % 2 == 0 ? -1 : gc);
                this.heroPixP.setAlpha(170 - (k2 * 12));
                c.drawRect(xa + in, ly, xb - (in * 0.3f), ly + (f * 0.55f), this.heroPixP);
            }
        }
    }

    void heroFlames(Canvas c, float cx, float base, float u, float bh, float fr) {
        if (!(u > 0.0f) || !(bh > 0.0f)) {
            return;
        }
        int n = 15;
        float colW = u * 1.75f;
        float x0 = cx - ((n * colW) * 0.5f);
        for (int layer = 0; layer < 3; layer++) {
            float hs = layer == 0 ? 1.0f : layer == 1 ? 0.74f : 0.5f;
            float lim = layer == 0 ? 1.01f : layer == 1 ? 0.8f : 0.58f;
            this.heroPixP.setColor(layer == 0 ? 0xFFE8361E : layer == 1 ? 0xFFFF8A1F : 0xFFFFD84A);
            this.heroPixP.setAlpha((int) ((layer == 0 ? 150 : layer == 1 ? 175 : 200) * fr));
            for (int i = 0; i < n; i++) {
                float rel = (((i + 0.5f) / n) * 2.0f) - 1.0f;
                if (Math.abs(rel) <= lim) {
                    float flick = (float) Math.sin((this.time * ((heroHash((i * 7) + layer) * 7.0f) + 9.0f)) + (i * 1.9f) + layer);
                    float hgt = ((bh * 0.5f) + (bh * 0.8f * (1.0f - (rel * rel))) + (flick * u * 2.6f)) * hs;
                    hgt = Math.round(hgt / u) * u;
                    if (hgt > 0.0f) {
                        c.drawRect(x0 + (i * colW), base - hgt, x0 + ((i + 1) * colW), base, this.heroPixP);
                    }
                }
            }
        }
        for (int k = 0; k < 9; k++) {
            float ph = ((this.time * ((heroHash((k * 13) + 5) * 0.6f) + 0.7f)) + heroHash((k * 31) + 1)) % 1.0f;
            float ex = cx + ((heroHash((k * 17) + 3) - 0.5f) * bh * 1.15f) + (((float) Math.sin((this.time * 3.0f) + k)) * u * 1.5f);
            float ey = (base - (bh * 0.3f)) - ((ph * bh) * 1.4f);
            float es = u * (1.15f - (ph * 0.8f)) * 0.5f;
            this.heroPixP.setColor(ph < 0.45f ? 0xFFFFE27A : 0xFFFF7A2A);
            this.heroPixP.setAlpha((int) ((1.0f - ph) * 235.0f * fr));
            c.drawRect(ex - es, ey - es, ex + es, ey + es, this.heroPixP);
        }
    }

    void heroOrbMotes(Canvas c, float x, float y, float f, int sc, boolean front) {
        int lc = heroMix(sc, -1, 0.55f);
        for (int k = 0; k < 3; k++) {
            double a = (this.time * 3.2d) + (k * 2.0944d);
            float sn = (float) Math.sin(a);
            if ((sn >= 0.0f) == front) {
                float mx = x + (((float) Math.cos(a)) * 4.4f * f);
                float my = (y + ((sn * 1.6f) * f)) - (0.4f * f);
                float s = f * (front ? 0.55f : 0.4f);
                this.heroPixP.setColor(lc);
                this.heroPixP.setAlpha(front ? 240 : 150);
                c.drawRect(mx - s, my - s, mx + s, my + s, this.heroPixP);
            }
        }
    }

    void heroStaff(Canvas c, float f, float f10, float cast, int sc, float fr) {
        float x = this.heroX + (10.0f * f) + (f10 * 1.3f);
        float y = (this.groundY - (24.0f * f)) - ((cast * f) * 2.0f);
        float g = this.groundY;
        float t = this.time;
        float h15 = f * 1.5f;
        float h05 = f * 0.5f;
        Paint q = this.heroPixP;
        q.setColor(-15331286);
        c.drawRect(x - h15, y, x + h15, g + f, q);
        q.setColor(-7644629);
        c.drawRect(x - h05, y, x + h05, g, q);
        q.setColor(0xFFB98048);
        c.drawRect(x - h05, y + (f * 4.0f), x + h05, y + (f * 5.0f), q);
        c.drawRect(x - h05, y + (f * 11.0f), x + h05, y + (f * 12.0f), q);
        q.setColor(0xFF5A3020);
        c.drawRect(x - h05, g - (f * 9.0f), x + h05, g - (f * 8.0f), q);
        c.drawRect(x - h05, g - (f * 7.0f), x + h05, g - (f * 6.0f), q);
        q.setColor(0xFFE8B83A);
        c.drawRect(x - h15, y + (f * 2.5f), x + h15, y + (f * 3.5f), q);
        q.setColor(0xFFFFF0B0);
        c.drawRect(x - h15, y + (f * 2.5f), x - h05, y + (f * 3.5f), q);
        float pulse = (float) Math.sin(t * 5.0f);
        float rr = ((cast * 5.0f) + 7.0f + (0.6f * pulse)) * f;
        heroGlow(c, 1, sc, x, y, rr, rr, (int) ((cast * 100.0f) + 140.0f + (25.0f * pulse)));
        heroOrbMotes(c, x, y, f, sc, false);
        if (fr > 0.0f) {
            float fh = (((float) Math.sin(t * 17.0f)) + 2.2f) * f;
            float fy = y - (2.5f * f);
            q.setColor(0xFFE8361E);
            q.setAlpha((int) (220.0f * fr));
            c.drawRect(x - h15, fy - fh, x + h15, fy, q);
            q.setColor(0xFFFFD84A);
            q.setAlpha((int) (240.0f * fr));
            c.drawRect(x - h05, (fy - (fh * 0.6f)) - (((float) Math.sin(t * 23.0f)) * f * 0.5f), x + h05, fy, q);
        }
        float h25 = f * 2.5f;
        q.setColor(-15331286);
        c.drawRect(x - h25, y - h25, x + h25, y + h25, q);
        q.setColor(sc);
        c.drawRect(x - h15, y - h15, x + h15, y + h15, q);
        q.setColor(heroMix(sc, -15331286, 0.35f));
        c.drawRect(x + h05, y + h05, x + h15, y + h15, q);
        c.drawRect(x - h15, y + h05, x - h05, y + h15, q);
        q.setColor(-1);
        c.drawRect(x - h15, y - h15, x - h05, y - h05, q);
        q.setColor(-1);
        q.setAlpha(Math.min(255, (int) ((pulse * 70.0f) + 90.0f + (cast * 165.0f))));
        c.drawRect(x - h05, y - h05, x + h05, y + h05, q);
        heroOrbMotes(c, x, y, f, sc, true);
        if (cast > 0.0f) {
            float pr = 1.0f - cast;
            this.heroLineP.setStrokeWidth(((1.3f * cast) + 0.5f) * f);
            this.heroLineP.setColor(sc);
            this.heroLineP.setAlpha((int) (235.0f * cast));
            c.drawCircle(x, y, ((12.0f * pr) + 3.0f) * f, this.heroLineP);
            this.heroLineP.setStrokeWidth(0.45f * f);
            this.heroLineP.setColor(-1);
            this.heroLineP.setAlpha((int) (200.0f * cast));
            c.drawCircle(x, y, ((8.0f * pr) + 2.0f) * f, this.heroLineP);
            for (int k = 0; k < 6; k++) {
                float d = ((11.0f * pr) + 2.5f) * f;
                float px = x + (heroDirX[k] * d);
                float py = y + (heroDirY[k] * d * 0.8f);
                float s = ((0.6f * cast) + 0.4f) * f * 0.5f;
                q.setColor(k % 2 == 0 ? -1 : sc);
                q.setAlpha((int) (255.0f * cast));
                c.drawRect(px - s, py - s, px + s, py + s, q);
            }
            float fl = ((4.0f * cast) + 3.0f) * f;
            heroGlow(c, 5, -1, x, y, fl, fl, (int) (210.0f * cast));
        }
        if (cast > 0.35f) {
            float f31 = f * ((cast * 9.0f) + 3.0f);
            float f32 = f * 0.7f;
            q.setColor(-1);
            q.setAlpha((int) (Math.min(1.0f, (cast - 0.35f) * 2.2f) * 255.0f));
            c.drawRect(x - f31, y - f32, x + f31, y + f32, q);
            c.drawRect(x - f32, y - f31, x + f32, y - f32, q);
            c.drawRect(x - f32, y + f32, x + f32, y + f31, q);
            q.setColor(sc);
            q.setAlpha((int) (cast * 200.0f));
            float f33 = f31 * 0.5f;
            float f36 = f32 * 2.0f;
            c.drawRect(x - f33, y - f33, (x - f33) + f36, (y - f33) + f36, q);
            c.drawRect((x + f33) - f36, (y + f33) - f36, x + f33, y + f33, q);
        }
    }
    void drawHero(Canvas var1) {
       if (this.bmHero == null || this.bmHero[this.lifeStageIdx] == null) {
          return;
       }

       Bitmap heroBmp = this.bmHero[this.lifeStageIdx];
       float heroF = this.hu * ((float)this.lifeStageIdx * 0.05F + 0.8F);
       float heroLift = 0.0F;
       if (this.isekaiT >= 0.0F && this.bmTruck != null) {
          float var2 = (float)this.bmTruck.getWidth();
          float var3 = this.hu;
          var2 = Math.abs(this.truckX() - this.heroX) / (var2 * var3 * 1.6F * 0.9F);
          if (var2 < 1.0F) {
             heroLift = (float)Math.cos((double)var2 * Math.PI / 2.0) * this.hu * 16.0F;
          }
       }

       boolean heroDashing = this.overlay == 11 && this.superType == 0;
       float heroDash = heroDashing ? heroDashK(this.superT) : 0.0F;
       float heroSpan = this.mobX - this.heroX - this.mobR * 1.6F;
       float heroShA = 1.0F;
       float heroShW = 1.0F;
       if (this.overlay == 6 && this.deathCrash) {
          float var6 = this.deathT - 1.2F;
          if (this.deathKind == 0) {
             heroShA = Math.max(0.0F, 1.0F - var6 * 4.0F);
          } else {
             heroShW = 1.0F + 0.7F * Math.max(0.0F, Math.min(1.0F, var6 * 8.0F));
          }
       }

       // ground layer stays on the ground: soft shadow, staff light pool, frenzy scorch, beat ring
       this.heroGround(var1, heroF, this.heroX + heroSpan * heroDash, heroLift, heroShA, heroShW);
       if (heroDashing) {
          this.heroDashTrail(var1, heroBmp, heroF, heroSpan, heroDash);
       }

       var1.save();
       if (heroLift != 0.0F) {
          var1.translate(0.0F, -heroLift);
       }

       if (heroDashing) {
          var1.translate(heroSpan * heroDash, 0.0F);
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
          // "!" pops in with an overshoot, jitters and gets a dark drop shadow
          float heroPop = Math.max(0.05F, heroBackOut(Math.min(1.0F, (this.deathT - 0.4F) / 0.18F)));
          float heroEx = this.heroX + (float)Math.sin((double)(this.time * 47.0F)) * this.pad * 0.12F;
          float heroEy = this.heroTop() - this.pad * 2.0F;
          var1.save();
          var1.scale(heroPop, heroPop, heroEx, heroEy);
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setTextSize(this.pad * 4.0F);
          this.tp.setFakeBoldText(true);
          this.tp.setColor(-15331286);
          this.tp.setAlpha(190);
          var1.drawText("!", heroEx + this.pad * 0.3F, heroEy + this.pad * 0.3F, this.tp);
          this.tp.setColor(-46518);
          var1.drawText("!", heroEx, heroEy, this.tp);
          this.tp.setFakeBoldText(false);
          var1.restore();
       }
    }

    void drawHeroInner(Canvas canvas) {
        int stage = this.lifeStageIdx;
        Bitmap bitmap = this.bmHero[stage];
        if (bitmap == null) {
            return;
        }
        float f = this.hu * ((stage * 0.05f) + 0.8f);
        float round = ((Math.round(Math.sin(this.time * 3.0f) * 1.2000000476837158d) * f) * 0.5f) - ((this.beatPulse * f) * IMPACT);
        float cast = this.heroCast;
        float fr = heroFrenzyK();
        float lk = heroLungeK(cast);
        float bh = bitmap.getHeight() * f;
        // breathing (faster & heavier while enraged) on top of the old cast squash
        float br = (float) Math.sin(this.time * (fr > 0.0f ? 4.6f : 2.2f));
        float sx = ((0.06f * cast) + 1.0f) * (1.0f - (0.014f * br));
        float sy = (1.0f - (0.06f * cast)) * ((0.024f * br) + 1.0f);
        float f10 = lk * f * 2.0f;
        float cx = this.heroX + f10;
        float foot = this.groundY + f + round;
        float mid = this.groundY - (bh * 0.45f);
        int sc = heroStaffColor(fr);
        // 1. life-stage aura: soft halo, plus rotating god-rays for the late stages
        if (stage >= 3) {
            int rc = heroMix(ROBE[stage], -1, 0.3f);
            float pulse = (float) Math.sin(this.time * 2.4f);
            if (stage >= 4) {
                float rr = (bh * ((0.06f * pulse) + 1.15f)) / 100.0f;
                this.heroGlowP.setShader(heroGradient(0, rc));
                this.heroGlowP.setAlpha(stage >= 5 ? 150 : 125);
                canvas.save();
                canvas.translate(cx, mid);
                canvas.scale(rr, rr);
                canvas.rotate(this.time * 16.0f);
                canvas.drawPath(this.heroRays, this.heroGlowP);
                if (stage >= 5) {
                    canvas.rotate(15.0f - (this.time * 40.0f));
                    canvas.scale(0.72f, 0.72f);
                    canvas.drawPath(this.heroRays, this.heroGlowP);
                }
                canvas.restore();
            }
            float ar = bh * ((0.05f * pulse) + 0.82f);
            heroGlow(canvas, 0, rc, cx, mid, ar, ar, (int) ((40.0f * pulse) + 205.0f));
        }
        // 2. frenzy: fire glow + pixel flame wall + rising embers behind the hero
        if (fr > 0.0f) {
            heroGlow(canvas, 2, 0xFFFF6A1A, cx, this.groundY - (bh * 0.5f), bh * 1.05f, bh, (int) (190.0f * fr));
            heroFlames(canvas, cx, this.groundY + (f * 0.5f), f, bh, fr);
        }
        // lean into the swing (and a permanent forward stoop while enraged)
        float lean = (0.11f * lk) + (0.05f * fr);
        canvas.save();
        if (lean > 0.0f) {
            canvas.translate(cx, this.groundY);
            canvas.skew(-lean, 0.0f);
            canvas.translate(-cx, -this.groundY);
        }
        // 3. outlines: flickering fire outline in frenzy, rim light from the staff orb always
        if (fr > 0.0f) {
            int oa = (int) (((((float) Math.sin(this.time * 15.0f)) * 70.0f) + 170.0f) * fr);
            heroSilhouette(canvas, bitmap, cx - f, foot, f, sx, sy, 0, 0xFFFF7A1E, oa);
            heroSilhouette(canvas, bitmap, cx + f, foot, f, sx, sy, 0, 0xFFFF7A1E, oa);
            heroSilhouette(canvas, bitmap, cx, foot - f, f, sx, sy, 0, 0xFFFF7A1E, oa);
        }
        int rimA = (int) ((175.0f * cast) + 55.0f + (((float) Math.sin(this.time * 5.0f)) * 25.0f));
        heroSilhouette(canvas, bitmap, cx + (0.75f * f), foot, f, sx, sy, 1, sc, rimA);
        heroSilhouette(canvas, bitmap, cx + (0.4f * f), foot - (0.6f * f), f, sx, sy, 1, sc, rimA / 2);
        // 4. the sprite itself
        blit(canvas, bitmap, cx, foot, f, sx, sy, this.bp);
        // face: periodic blink, angry brows while enraged (every hero_N.png has its eyes at pixels (6,9) and (11,9))
        if (bitmap.getWidth() == 18 && bitmap.getHeight() == 22) {
            float pw = f * sx;
            float ph = f * sy;
            float left = cx - (9.0f * pw);
            float top = foot - (22.0f * ph);
            Paint q = this.heroPixP;
            if (this.time % 4.3f < 0.13f) {
                q.setColor(0xFFF2D2B6);
                canvas.drawRect(left + (6.0f * pw), top + (9.0f * ph), left + (7.0f * pw), top + (10.0f * ph), q);
                canvas.drawRect(left + (11.0f * pw), top + (9.0f * ph), left + (12.0f * pw), top + (10.0f * ph), q);
                q.setColor(0xFF1A1020);
                canvas.drawRect(left + (5.8f * pw), top + (9.55f * ph), left + (7.2f * pw), top + (9.95f * ph), q);
                canvas.drawRect(left + (10.8f * pw), top + (9.55f * ph), left + (12.2f * pw), top + (9.95f * ph), q);
            }
            if (fr > 0.0f) {
                q.setColor(0xFF1A1020);
                q.setAlpha((int) (255.0f * Math.min(1.0f, fr * 2.0f)));
                canvas.drawRect(left + (7.0f * pw), top + (8.0f * ph), left + (8.0f * pw), top + (9.0f * ph), q);
                canvas.drawRect(left + (10.0f * pw), top + (8.0f * ph), left + (11.0f * pw), top + (9.0f * ph), q);
                canvas.drawRect(left + (6.0f * pw), top + (8.0f * ph), left + (7.0f * pw), top + (8.45f * ph), q);
                canvas.drawRect(left + (11.0f * pw), top + (8.0f * ph), left + (12.0f * pw), top + (8.45f * ph), q);
            }
        }
        if (fr > 0.0f) {
            heroSilhouette(canvas, bitmap, cx, foot, f, sx, sy, 0, 0xFFFF7A1E, (int) (((((float) Math.sin(this.time * 9.0f)) * 22.0f) + 34.0f) * fr));
        }
        if (this.overlay == 6 && !this.deathCrash && this.deathT > 0.4f && (((int) (this.time * 12.0f)) & 1) == 0) {
            heroSilhouette(canvas, bitmap, cx, foot, f, sx, sy, 4, 0xFFFF3B3B, 120);
        }
        // 5. headphones (rhythm mode); cups flash on the beat
        if (rhythmOn()) {
            float height = foot - (bh * sy);
            Paint q = this.heroPixP;
            q.setColor(-3921286);
            float f13 = f * 8.0f;
            float f14 = f * 6.0f;
            float f15 = height + f14;
            float f16 = height + (11.0f * f);
            canvas.drawRect(cx - f13, f15, cx - f14, f16, q);
            canvas.drawRect(cx + f14, f15, cx + f13, f16, q);
            q.setColor(heroMix(-45149, -1, this.beatPulse * 0.6f));
            float f17 = f * 7.5f;
            float f18 = height + (f * 7.0f);
            float f19 = f * 6.5f;
            float f20 = height + (f * 10.0f);
            canvas.drawRect(cx - f17, f18, cx - f19, f20, q);
            canvas.drawRect(cx + f19, f18, cx + f17, f20, q);
            if (this.beatPulse > 0.05f) {
                q.setColor(-1);
                q.setAlpha((int) (this.beatPulse * 220.0f));
                canvas.drawRect(cx - f13, f15, cx - f17, f15 + f, q);
                canvas.drawRect(cx + f17, f15, cx + f13, f15 + f, q);
            }
        }
        // 6. staff: wood grain, grip wrap, gold collar, glowing orb with orbiting motes, cast shockwave
        heroStaff(canvas, f, f10, cast, sc, fr);
        canvas.restore();
    }

    void drawIsekai(Canvas var1) {
       float var2 = this.victimT;
       if (var2 >= 0.0F) {
          var2 /= 0.9F;
          float var3 = var2 * var2 * (3.0F - var2 * 2.0F);
          float var5 = (float)this.W * 0.86F;
          float var4 = this.battleT;
          var4 += (this.groundY - var4) * 0.14F;
          float ovlPo = Math.min(1.0F, var2 * 3.0F) * Math.min(1.0F, Math.max(0.0F, (1.25F - var2) * 3.0F));
          this.ovlGlow(var1, var5, var4, this.pad * 10.0F * (0.4F + 0.6F * ovlPo), -8395009, (int)(150.0F * ovlPo));
          this.ovlRays(var1, var5, var4, this.pad * 9.0F * ovlPo, 12, this.time * -160.0F, 0.08F, -5213953, (int)(60.0F * ovlPo));
          this.ovlP.setStyle(Style.FILL);
          this.ovlP.setColor(-15069648);
          this.ovlP.setAlpha((int)(200.0F * ovlPo));
          this.ovlR.set(var5 - this.pad * 2.6F * ovlPo, var4 - this.pad * 1.95F * ovlPo, var5 + this.pad * 2.6F * ovlPo, var4 + this.pad * 1.95F * ovlPo);
          var1.drawOval(this.ovlR, this.ovlP);
          for (int ovlK = 0; ovlK < 12; ovlK++) {
             float ovlPh = (this.time * 0.9F + ovlHash(ovlK, 91)) % 1.0F;
             double ovlAng = (double)ovlK * 0.5235987755982988 + (double)(ovlPh * 5.0F) + (double)this.time;
             float ovlRad = this.pad * 7.0F * (1.0F - ovlPh) * ovlPo;
             this.ovlSpark(var1, var5 + (float)Math.cos(ovlAng) * ovlRad, var4 + (float)Math.sin(ovlAng) * ovlRad * 0.75F, this.pad * 0.5F * (0.4F + ovlPh), ovlK % 2 == 0 ? -5213953 : -8395009, (int)(230.0F * ovlPo * ovlPh));
          }

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
          this.ovlGlow(var1, var15 + var20 / 2.0F, this.groundY - 9.0F * var23, var23 * 14.0F, -5720, (int)(Math.sin((double)(this.isekaiT * 50.0F)) * 25.0 + 150.0));
          this.p.setColor(1442837160);
          var1.drawCircle(var15 + var20 / 2.0F, this.groundY - 9.0F * var23, var23 * 5.0F, this.p);
       }
    }

    void drawMini(Canvas canvas) {
        int i;
        String str;
        StringBuilder append;
        int i2;
        if (this.W <= 0 || this.H <= 0) {
            return;
        }
        // arena backdrop: slow rotating rays and drifting motes behind the minigame
        minisRays(canvas, this.W / 2.0f, this.H * 0.48f, this.W * 0.05f, this.W * 0.75f, 14, this.time * 0.15f, 0.5f, -11430, 16.0f);
        for (int minisK = 0; minisK < 16; minisK++) {
            float minisU = ((this.time * ((minisHash(minisK) * 0.04f) + 0.02f)) + minisHash(minisK + 50)) % 1.0f;
            float minisMx = (minisHash(minisK + 20) * this.W) + (((float) Math.sin((this.time * 0.9f) + minisK)) * this.pad);
            float minisMs = this.hu * ((minisHash(minisK + 3) * 0.7f) + 0.5f);
            this.minisP.setStyle(Paint.Style.FILL);
            this.minisP.setColor(EL_COLOR[minisK % 4]);
            this.minisP.setAlpha(minisA(((float) Math.sin(minisU * 3.141592653589793d)) * 90.0f));
            float minisMy = this.H * (0.95f - (minisU * 0.8f));
            canvas.drawRect(minisMx - minisMs, minisMy - minisMs, minisMx + minisMs, minisMy + minisMs, this.minisP);
        }
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setTextSize(this.pad * 3.0f);
        this.tp.setFakeBoldText(true);
        // title with a chunky drop shadow and a warm glow
        minisHalo(canvas, this.W / 2.0f, (this.H * 0.17f) - (this.pad * 1.0f), this.pad * 7.0f, -11430, 70.0f);
        this.tp.setColor(-15331286);
        canvas.drawText(MINI_NAME[this.miniType], (this.W / 2.0f) + (this.hu * 1.3f), (this.H * 0.17f) + (this.hu * 1.3f), this.tp);
        this.tp.setColor(-11430);
        canvas.drawText(MINI_NAME[this.miniType], this.W / 2.0f, this.H * 0.17f, this.tp);
        this.tp.setFakeBoldText(false);
        this.tp.setColor(-4609816);
        this.tp.setTextSize(this.pad * 1.8f);
        canvas.drawText(MINI_RULE[this.miniType], this.W / 2.0f, this.H * 0.215f, this.tp);
        int i3 = OV_NONE;
        while (i3 < 3) {
            float f = (this.W / 2.0f) + ((i3 - 1) * this.pad * 4.0f);
            boolean minisDone = i3 < this.miniRound;
            if (minisDone) {
                minisHalo(canvas, f, this.H * 0.25f, this.pad * 2.2f, -11430, 150.0f);
            }
            this.minisP.setStyle(Paint.Style.FILL);
            this.minisP.setColor(-15331286);
            this.minisP.setAlpha(150);
            canvas.drawCircle(f + (this.hu * 0.8f), (this.H * 0.25f) + (this.hu * 0.8f), this.pad * 1.0f, this.minisP);
            this.p.setColor(minisDone ? -11430 : -12963760);
            canvas.drawCircle(f, this.H * 0.25f, this.pad * 1.0f, this.p);
            this.minisP.setColor(-1);
            this.minisP.setAlpha(minisDone ? 200 : 60);
            canvas.drawRect(f - (this.pad * 0.5f), (this.H * 0.25f) - (this.pad * 0.6f), f - (this.pad * 0.1f), (this.H * 0.25f) - (this.pad * 0.2f), this.minisP);
            if (i3 == this.miniRound && this.miniPhase == 1) {
                // current round: pulsing ring
                this.minisP.setStyle(Paint.Style.STROKE);
                this.minisP.setStrokeWidth(Math.max(1.0f, this.pad * 0.25f));
                this.minisP.setColor(-11430);
                this.minisP.setAlpha(minisA((((float) Math.sin(this.time * 6.0f)) * 90.0f) + 150.0f));
                canvas.drawCircle(f, this.H * 0.25f, this.pad * ((((float) Math.sin(this.time * 6.0f)) * 0.15f) + 1.45f), this.minisP);
                this.minisP.setStyle(Paint.Style.FILL);
            }
            i3++;
        }
        int i4 = this.miniPhase;
        if (i4 == 0) {
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 3.5f);
            this.tp.setFakeBoldText(true);
            if (this.miniT >= 0.7f) {
                // "go!" slam: scale pop, shockwave rings, burst rays
                float minisGo = Math.min(1.0f, (this.miniT - 0.7f) * 3.0f);
                this.tp.setTextSize(this.pad * 3.5f * ((0.6f * (1.0f - minisGo) * (1.0f - minisGo)) + 1.0f));
                minisRays(canvas, this.W / 2.0f, (this.H * 0.5f) - (this.pad * 1.2f), this.pad * 2.0f, this.W * ((0.3f * minisGo) + 0.25f), 16, this.time * 0.5f, 0.4f, -11430, 120.0f * (1.0f - (minisGo * 0.6f)));
                this.minisP.setStyle(Paint.Style.STROKE);
                for (int minisK = 0; minisK < 2; minisK++) {
                    float minisGr = Math.min(1.0f, minisGo + (minisK * 0.25f));
                    this.minisP.setStrokeWidth(Math.max(1.0f, this.pad * 0.6f * (1.0f - minisGr)));
                    this.minisP.setColor(minisK == 0 ? -1 : -11430);
                    this.minisP.setAlpha(minisA(220.0f * (1.0f - minisGr)));
                    canvas.drawCircle(this.W / 2.0f, (this.H * 0.5f) - (this.pad * 1.2f), this.W * 0.45f * minisGr, this.minisP);
                }
                this.minisP.setStyle(Paint.Style.FILL);
            } else {
                // "get ready": gentle breathing
                this.tp.setAlpha(minisA((((float) Math.sin(this.time * 6.0f)) * 60.0f) + 195.0f));
            }
            canvas.drawText(this.miniT < 0.7f ? "Приготовься..." : "Начали!", this.W / 2.0f, this.H * 0.5f, this.tp);
            this.tp.setAlpha(255);
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
            // result stars pop in one by one, earned ones glow under spinning rays
            float minisStarW = this.tp.measureText("★");
            float minisSy = (this.H * 0.47f) - (this.pad * 1.8f);
            if (this.miniScore > 0) {
                minisRays(canvas, this.W / 2.0f, minisSy, this.pad * 2.0f, this.W * 0.42f, 12, this.time * 0.5f, 0.45f, -11430, (this.miniScore * 22.0f) + 20.0f);
            }
            int i5 = OV_NONE;
            while (i5 < 3) {
                float minisAge = this.miniT - (i5 * 0.18f);
                if (minisAge > 0.0f) {
                    boolean minisGot = i5 < this.miniScore;
                    float minisPop = minisAge < 0.12f ? (minisAge / 0.12f) * 1.4f : 1.4f - Math.min(0.4f, (minisAge - 0.12f) * 3.0f);
                    float minisSx = (this.W / 2.0f) + ((i5 - 1) * minisStarW);
                    if (minisGot) {
                        minisHalo(canvas, minisSx, minisSy, minisStarW * 0.9f * minisPop, -11430, 200.0f);
                        float minisTw = ((float) Math.sin((this.time * 5.0f) + (i5 * 2.0f))) * 0.5f + 0.5f;
                        minisStar(canvas, (minisStarW * 0.45f) + minisSx, minisSy - (minisStarW * 0.4f), this.hu * ((minisTw * 1.5f) + 0.5f), -1, minisTw * 230.0f);
                    }
                    this.tp.setTextSize(this.pad * 5.0f * minisPop);
                    this.tp.setColor(-15331286);
                    canvas.drawText(minisGot ? "★" : "☆", (this.hu * 1.4f) + minisSx, (this.H * 0.47f) + (this.hu * 1.4f), this.tp);
                    this.tp.setColor(minisGot ? -11430 : -7569238);
                    canvas.drawText(minisGot ? "★" : "☆", minisSx, this.H * 0.47f, this.tp);
                }
                i5++;
            }
            this.tp.setTextSize(this.pad * 5.0f);
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
            // bar frame: drop shadow, bevelled track, tick marks
            this.minisR.set(f3 - (this.pad * 0.4f), f8 - (this.pad * 0.4f), f3 + f4 + (this.pad * 0.4f), f9 + (this.pad * 0.7f));
            this.minisP.setStyle(Paint.Style.FILL);
            this.minisP.setColor(-15331286);
            this.minisP.setAlpha(170);
            canvas.drawRoundRect(this.minisR, this.pad * 1.3f, this.pad * 1.3f, this.minisP);
            this.p.setColor(-14016440);
            RectF rectF = this.r;
            float f10 = this.pad;
            canvas.drawRoundRect(rectF, f10, f10, this.p);
            this.minisP.setColor(-16777216);
            this.minisP.setAlpha(70);
            canvas.drawRect(f3 + f10, f8, (f3 + f4) - f10, (f6 * 0.22f) + f8, this.minisP);
            this.minisP.setColor(-1);
            this.minisP.setAlpha(40);
            for (int minisK = 1; minisK < 10; minisK++) {
                float minisX = (f4 * minisK * 0.1f) + f3;
                canvas.drawRect(minisX - (this.hu * 0.4f), f9 - (f6 * 0.25f), (this.hu * 0.4f) + minisX, f9 - (f6 * 0.08f), this.minisP);
            }
            if (this.miniPhase == 1) {
                RectF rectF2 = this.r;
                float f11 = this.zoneL;
                rectF2.set((f4 * f11) + f3, f8, ((f11 + this.zoneW) * f4) + f3, f9);
                // target zone glows and breathes
                float minisZp = (((float) Math.sin(this.time * 7.0f)) * 0.5f) + 0.5f;
                minisHalo(canvas, this.r.centerX(), this.r.centerY(), Math.max(this.r.width(), f6) * 0.9f, -12673970, (minisZp * 80.0f) + 90.0f);
                this.p.setColor(-12673970);
                canvas.drawRect(this.r, this.p);
                minisGloss(canvas, this.r, 0.0f, 0.8f);
                float f12 = ((this.zoneL + (this.zoneW / 2.0f)) * f4) + f3;
                minisHalo(canvas, f12, f5, f6 * 0.6f, -11430, (minisZp * 120.0f) + 80.0f);
                this.p.setColor(-11430);
                float f13 = 0.035f * f4;
                i2 = -1;
                canvas.drawRect(f12 - f13, f8, f13 + f12, f9, this.p);
                minisStar(canvas, f12, f8 - (this.pad * 0.6f), this.hu * ((minisZp * 1.2f) + 1.0f), -11430, 230.0f);
                float f14 = f3 + (f4 * this.swPos);
                // cursor: glow halo, pointer tips and a shine
                float f16 = f6 * 0.85f;
                minisHalo(canvas, f14, f5, f6 * 0.9f, -1, 140.0f);
                this.p.setColor(-1);
                float f15 = this.pad;
                canvas.drawRect(f14 - (f15 * 0.45f), f5 - f16, f14 + (f15 * 0.45f), f5 + f16, this.p);
                this.minisPath.reset();
                this.minisPath.moveTo(f14 - (f15 * 1.1f), (f5 - f16) - (f15 * 0.9f));
                this.minisPath.lineTo((f15 * 1.1f) + f14, (f5 - f16) - (f15 * 0.9f));
                this.minisPath.lineTo(f14, f5 - f16);
                this.minisPath.close();
                this.minisPath.moveTo(f14 - (f15 * 1.1f), f5 + f16 + (f15 * 0.9f));
                this.minisPath.lineTo((f15 * 1.1f) + f14, f5 + f16 + (f15 * 0.9f));
                this.minisPath.lineTo(f14, f5 + f16);
                this.minisPath.close();
                this.minisP.setStyle(Paint.Style.FILL);
                this.minisP.setColor(-1);
                this.minisP.setAlpha(255);
                canvas.drawPath(this.minisPath, this.minisP);
            } else {
                i2 = -1;
            }
            // floating, gleaming sword
            float minisBob = ((float) Math.sin(this.time * 2.5f)) * this.pad * 0.6f;
            minisHalo(canvas, this.W / 2.0f, ((this.H * 0.4f) - (this.pad * 2.0f)) + minisBob, this.pad * 5.0f, -10825217, 110.0f);
            this.tp.setTextSize(this.pad * 6.0f);
            this.tp.setColor(i2);
            canvas.drawText("🗡", this.W / 2.0f, (this.H * 0.4f) + minisBob, this.tp);
            float minisGt = ((this.time * 0.8f) % 1.0f);
            if (minisGt < 0.3f) {
                minisStar(canvas, (this.W / 2.0f) + (this.pad * 1.5f), ((this.H * 0.4f) - (this.pad * 4.0f)) + minisBob, this.hu * 2.5f * ((float) Math.sin((minisGt / 0.3f) * 3.141592653589793d)), -1, 240.0f);
            }
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
                if (this.elFoe >= 0 && this.elFoe < EL_COLOR.length) {
                    // menacing aura around the enemy element
                    float minisFp = (((float) Math.sin(this.time * 5.0f)) * 0.5f) + 0.5f;
                    minisRays(canvas, this.W / 2.0f, (this.H * 0.37f) - (this.pad * 1.7f), this.pad * 1.5f, this.pad * ((minisFp * 1.5f) + 5.5f), 10, -this.time * 0.9f, 0.35f, EL_COLOR[this.elFoe], 90.0f);
                    minisHalo(canvas, this.W / 2.0f, (this.H * 0.37f) - (this.pad * 1.7f), this.pad * ((minisFp * 0.8f) + 4.0f), EL_COLOR[this.elFoe], 190.0f);
                }
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
                // timer: gloss, burning fuse tip, alarm blink when low
                this.minisR.set(i12 * 0.2f, i13 * 0.39f, (i12 * 0.2f) + (i12 * 0.6f * Math.min(1.0f, max)), (i13 * 0.39f) + this.pad);
                minisGloss(canvas, this.minisR, 0.0f, 1.0f);
                if (max > 0.0f) {
                    minisHalo(canvas, this.minisR.right, this.minisR.centerY(), this.pad * 1.6f, max > 0.35f ? -10825217 : -42406, 200.0f);
                    minisStar(canvas, this.minisR.right, this.minisR.centerY(), this.hu * ((((float) Math.sin(this.time * 20.0f)) * 0.6f) + 1.6f), -1, 230.0f);
                }
                if (max <= 0.35f) {
                    this.minisP.setStyle(Paint.Style.STROKE);
                    this.minisP.setStrokeWidth(Math.max(1.0f, this.pad * 0.25f));
                    this.minisP.setColor(-42406);
                    this.minisP.setAlpha(minisA((((float) Math.sin(this.time * 14.0f)) * 100.0f) + 140.0f));
                    canvas.drawRect((i12 * 0.2f) - this.hu, (i13 * 0.39f) - this.hu, (i12 * 0.8f) + this.hu, (i13 * 0.39f) + this.pad + this.hu, this.minisP);
                    this.minisP.setStyle(Paint.Style.FILL);
                }
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
                // pad depth: drop shadow, lit-rune glow + ripple
                this.minisR.set(this.r.left, this.r.top + (this.pad * 0.6f), this.r.right, this.r.bottom + (this.pad * 0.6f));
                this.minisP.setStyle(Paint.Style.FILL);
                this.minisP.setColor(-15331286);
                this.minisP.setAlpha(160);
                canvas.drawRoundRect(this.minisR, this.pad * 2.0f, this.pad * 2.0f, this.minisP);
                if (z3) {
                    minisHalo(canvas, padCX, padCY, this.W * 0.24f, EL_COLOR[i15], 255.0f);
                    float minisRp = (this.time * 2.5f) % 1.0f;
                    this.minisP.setStyle(Paint.Style.STROKE);
                    this.minisP.setStrokeWidth(Math.max(1.0f, this.pad * 0.4f * (1.0f - minisRp)));
                    this.minisP.setColor(EL_COLOR[i15]);
                    this.minisP.setAlpha(minisA(230.0f * (1.0f - minisRp)));
                    this.minisR.set(this.r.left - (this.pad * 2.0f * minisRp), this.r.top - (this.pad * 2.0f * minisRp), this.r.right + (this.pad * 2.0f * minisRp), this.r.bottom + (this.pad * 2.0f * minisRp));
                    canvas.drawRoundRect(this.minisR, this.pad * 2.0f, this.pad * 2.0f, this.minisP);
                    this.minisP.setStyle(Paint.Style.FILL);
                }
                this.p.setColor(z3 ? -1 : EL_COLOR[i15]);
                if (!z2 && !z3) {
                    this.p.setAlpha(150);
                }
                RectF rectF4 = this.r;
                float f17 = this.pad;
                canvas.drawRoundRect(rectF4, f17 * 2.0f, f17 * 2.0f, this.p);
                this.p.setAlpha(255);
                minisGloss(canvas, this.r, f17 * 2.0f, z3 ? 0.6f : 1.0f);
                // inner rim in the element colour
                this.minisP.setStyle(Paint.Style.STROKE);
                this.minisP.setStrokeWidth(Math.max(1.0f, this.hu * 0.8f));
                this.minisP.setColor(z3 ? EL_COLOR[i15] : -1);
                this.minisP.setAlpha(z3 ? 255 : 70);
                this.minisR.set(this.r.left + this.hu * 1.5f, this.r.top + this.hu * 1.5f, this.r.right - this.hu * 1.5f, this.r.bottom - this.hu * 1.5f);
                canvas.drawRoundRect(this.minisR, f17 * 1.6f, f17 * 1.6f, this.minisP);
                this.minisP.setStyle(Paint.Style.FILL);
                this.tp.setTextSize(this.pad * 4.5f);
                this.tp.setColor(-1);
                float minisIb = ((float) Math.sin((this.time * 3.0f) + (i15 * 1.6f))) * this.pad * 0.35f;
                canvas.drawText(EL_ICON[i15], padCX, (this.pad * IMPACT) + padCY + minisIb, this.tp);
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
            // feedback message slams in with an overshoot and a dark outline
            float minisMa = Math.max(0.0f, 0.9f - this.miniMsgT);
            float minisMs = minisMa < 0.08f ? (minisMa / 0.08f) * 0.8f + 0.6f : 1.4f - Math.min(0.4f, (minisMa - 0.08f) * 3.0f);
            this.tp.setTextSize(this.pad * 3.0f * minisMs);
            this.tp.setFakeBoldText(true);
            int minisMalpha = (int) (Math.min(1.0f, this.miniMsgT * 2.0f) * 255.0f);
            minisHalo(canvas, this.W / 2.0f, (this.H * 0.88f) - (this.pad * 1.0f), this.pad * 6.0f * minisMs, this.miniMsgColor, minisMalpha * 0.5f);
            this.tp.setColor(-15331286);
            this.tp.setAlpha(minisMalpha);
            canvas.drawText(this.miniMsg, (this.W / 2.0f) + (this.hu * 1.3f), (this.H * 0.88f) + (this.hu * 1.3f), this.tp);
            this.tp.setColor(this.miniMsgColor);
            this.tp.setAlpha(minisMalpha);
            canvas.drawText(this.miniMsg, this.W / 2.0f, this.H * 0.88f, this.tp);
            this.tp.setAlpha(255);
            this.tp.setFakeBoldText(false);
        }
    }

    // ===== gfx(mon): monster & boss rendering — every member below is prefixed "mon" =====
    // idle styles: 0 breathe, 1 hopper, 2 flyer, 3 heavy brute, 4 rattling bones, 5 engine rumble
    static final int[] monMobKind = {1, 1, 0, 4, 2, 3, 3, 3, 2, 2};
    static final int[] monBossKind = {1, 3, 3, 2, 3, 3, 2, 3, 3, 5};
    static final float[] monGlowStops = {0.0f, 0.42f, 1.0f};
    static final int[] monWsCols = {0xB4FFEC8A, 0x52FFB000, 0x00FF8000};
    static final float[] monGlintX = {-0.3f, 0.32f, 0.04f};
    static final float[] monGlintY = {0.7f, 0.42f, 0.97f};
    final int[] monGlowCols = new int[3];
    final Paint monFillP = monMk(false);
    final Paint monLineP = monMk(true);
    final Paint monGlowP = monMk(false);
    final Paint monWsGlowP = monMk(false);
    final Paint monWhiteP = monMkSil(0xFFFFFFFF);
    final Paint monRedP = monMkSil(0xFFFF2A3A);
    final Paint monRimBossP = monMkSil(0xFFFF4D6D);
    final Paint monRimTowerP = monMkSil(0xFFFFA040);
    final Paint monRimGoldP = monMkSil(0xFFFFD35A);
    final RectF monRect = new RectF();
    final Path monPath = new Path();
    final Random monRnd = new Random();
    int monGlowR = -1;
    int monGlowC;
    int monWsR = -1;
    float monPrevSpawn = 1.0f;
    long monSpawnAt = -100000L;
    float monHpTrail = 1.0f;
    long monHpMs;
    String monWsStr = "";
    int monWsStrN = -1;
    Bitmap monDrawnBmp;
    float monDrawnX;
    float monDrawnB;
    float monDrawnS;
    float monDrawnSx = 1.0f;
    float monDrawnSy = 1.0f;
    Bitmap monDeadBmp;
    float monDeadX;
    float monDeadB;
    float monDeadS;
    float monDeadSx = 1.0f;
    float monDeadSy = 1.0f;
    long monDeadAt = -100000L;
    int monDeadCol = -1;
    boolean monDeadBoss;

    static Paint monMk(boolean stroke) {
        Paint pt = new Paint(Paint.ANTI_ALIAS_FLAG);
        pt.setStyle(stroke ? Paint.Style.STROKE : Paint.Style.FILL);
        return pt;
    }

    // crisp single-colour silhouette of a sprite (outline, flash, tint)
    static Paint monMkSil(int color) {
        Paint pt = new Paint();
        pt.setFilterBitmap(false);
        pt.setAntiAlias(false);
        pt.setDither(false);
        pt.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        return pt;
    }

    static float monClamp(float v) {
        if (!(v > 0.0f)) {
            return 0.0f;
        }
        return v > 1.0f ? 1.0f : v;
    }

    static int monA(float a) {
        return (int) (monClamp(a) * 255.0f);
    }

    static int monShade(int c, float f) {
        int r = Math.min(255, (int) (((c >> 16) & 255) * f));
        int g = Math.min(255, (int) (((c >> 8) & 255) * f));
        int b = Math.min(255, (int) ((c & 255) * f));
        return (c & 0xFF000000) | (r << 16) | (g << 8) | b;
    }

    static int monLerpC(int a, int b, float t) {
        t = monClamp(t);
        int aa = (int) (((a >>> 24) & 255) + ((((b >>> 24) & 255) - ((a >>> 24) & 255)) * t));
        int ar = (int) (((a >> 16) & 255) + ((((b >> 16) & 255) - ((a >> 16) & 255)) * t));
        int ag = (int) (((a >> 8) & 255) + ((((b >> 8) & 255) - ((a >> 8) & 255)) * t));
        int ab = (int) ((a & 255) + (((b & 255) - (a & 255)) * t));
        return (aa << 24) | (ar << 16) | (ag << 8) | ab;
    }

    // elastic (ease-out-back) pop-in, same 0.3 -> 1.0 range as the old linear spawn scale
    float monSpawnK() {
        float q = monClamp(this.mobSpawn) - 1.0f;
        return 0.3f + 0.7f * (1.0f + 2.9f * q * q * q + 1.9f * q * q);
    }

    int monKind(Bitmap b) {
        if (b.getWidth() > 24) {
            return 5;
        }
        for (int i = 0; i < this.bmBoss.length && i < monBossKind.length; i++) {
            if (this.bmBoss[i] == b) {
                return monBossKind[i];
            }
        }
        if (b == this.bmMimic) {
            return 1;
        }
        for (int i = 0; i < this.bmMob.length && i < monMobKind.length; i++) {
            if (this.bmMob[i] == b) {
                return monMobKind[i];
            }
        }
        return 0;
    }

    void monEnsureGlow(int rad, int col) {
        rad = Math.max(1, rad);
        if (rad == this.monGlowR && col == this.monGlowC && this.monGlowP.getShader() != null) {
            return;
        }
        this.monGlowR = rad;
        this.monGlowC = col;
        int rgb = col & 0xFFFFFF;
        this.monGlowCols[0] = 0xB4000000 | rgb;
        this.monGlowCols[1] = 0x5C000000 | rgb;
        this.monGlowCols[2] = rgb;
        this.monGlowP.setShader(new RadialGradient(0.0f, 0.0f, rad, this.monGlowCols, monGlowStops, Shader.TileMode.CLAMP));
    }

    void monGlow(Canvas c, float cx, float cy, float sx, float sy, int alpha) {
        if (alpha <= 0 || this.monGlowR <= 0) {
            return;
        }
        this.monGlowP.setAlpha(alpha);
        c.save();
        c.translate(cx, cy);
        c.scale(sx, sy);
        c.drawCircle(0.0f, 0.0f, this.monGlowR, this.monGlowP);
        c.restore();
    }

    // perspective summoning circle on the ground; back half is drawn behind the boss, front half over its feet
    void monDrawRing(Canvas c, boolean front, int col, float cx, float gy, float rx, float ry, float a) {
        if (a <= 0.01f || rx <= 0.0f) {
            return;
        }
        float rot = (this.time * 32.0f) % 360.0f;
        int lite = monShade(col, 1.45f);
        this.monLineP.setColor(col);
        this.monLineP.setStrokeWidth(this.hu * 0.5f);
        this.monLineP.setAlpha(monA(a * (front ? 0.62f : 0.36f)));
        this.monRect.set(cx - rx, gy - ry, cx + rx, gy + ry);
        c.drawArc(this.monRect, front ? 0.0f : 180.0f, 180.0f, false, this.monLineP);
        float ox = rx * 1.16f;
        float oy = ry * 1.16f;
        this.monRect.set(cx - ox, gy - oy, cx + ox, gy + oy);
        this.monLineP.setColor(lite);
        this.monLineP.setStrokeWidth(this.hu * 0.8f);
        this.monLineP.setAlpha(monA(a * (front ? 0.85f : 0.45f)));
        for (int i = 0; i < 12; i++) {
            float st = rot + (i * 30.0f);
            if ((((st + 8.0f) % 360.0f) < 180.0f) == front) {
                c.drawArc(this.monRect, st, 16.0f, false, this.monLineP);
            }
        }
        float ix = rx * 0.74f;
        float iy = ry * 0.74f;
        this.monRect.set(cx - ix, gy - iy, cx + ix, gy + iy);
        this.monLineP.setColor(col);
        this.monLineP.setStrokeWidth(this.hu * 0.45f);
        this.monLineP.setAlpha(monA(a * (front ? 0.7f : 0.4f)));
        float rot2 = 360.0f - ((rot * 1.5f) % 360.0f);
        for (int i = 0; i < 6; i++) {
            float st = (rot2 + (i * 60.0f)) % 360.0f;
            if ((((st + 17.0f) % 360.0f) < 180.0f) == front) {
                c.drawArc(this.monRect, st, 34.0f, false, this.monLineP);
            }
        }
        float g = this.hu * 0.5f;
        this.monFillP.setColor(lite);
        for (int i = 0; i < 8; i++) {
            double ang = Math.toRadians((rot * 0.7f) + (i * 45.0f));
            float sn = (float) Math.sin(ang);
            if ((sn > 0.0f) != front) {
                continue;
            }
            float gx = cx + ((float) Math.cos(ang) * rx);
            float gyy = ((gy + (sn * ry)) - (g * 4.0f)) + ((float) Math.sin((this.time * 3.0f) + i) * g);
            float tw = 0.55f + (0.45f * (float) Math.sin((this.time * 4.0f) + (i * 1.7f)));
            this.monFillP.setAlpha(monA(a * tw * (front ? 0.95f : 0.5f)));
            int shape = i % 3;
            if (shape == 0) {
                c.drawRect(gx - (g * 1.5f), gyy - (g * 1.5f), gx + (g * 1.5f), gyy - (g * 0.5f), this.monFillP);
                c.drawRect(gx - (g * 0.5f), gyy - (g * 0.5f), gx + (g * 0.5f), gyy + (g * 1.5f), this.monFillP);
            } else if (shape == 1) {
                c.drawRect(gx - (g * 0.5f), gyy - (g * 1.5f), gx + (g * 0.5f), gyy + (g * 1.5f), this.monFillP);
                c.drawRect(gx - (g * 1.5f), gyy - (g * 0.5f), gx + (g * 1.5f), gyy + (g * 0.5f), this.monFillP);
            } else {
                c.drawRect(gx - (g * 1.5f), gyy - (g * 1.5f), gx - (g * 0.5f), gyy + (g * 1.5f), this.monFillP);
                c.drawRect(gx - (g * 0.5f), gyy + (g * 0.5f), gx + (g * 1.5f), gyy + (g * 1.5f), this.monFillP);
                c.drawRect(gx + (g * 0.5f), gyy - (g * 1.5f), gx + (g * 1.5f), gyy - (g * 0.5f), this.monFillP);
            }
        }
    }

    // boss entrance: speed streaks + landing reticle while falling, double shockwave after touchdown
    void monDrawIntroFx(Canvas c, float px, float bottom, float sprW, float sprH, int col) {
        float it = this.bossIntroT;
        if (it > 1.0f) {
            float q = monClamp(1.0f - ((1.6f - it) / 0.6f));
            float top = bottom - sprH;
            this.monLineP.setStrokeWidth(this.hu * 0.7f);
            for (int i = 0; i < 6; i++) {
                float lx = px + ((i - 2.5f) * sprW * 0.17f);
                float len = this.mobR * (0.9f + (0.5f * (i % 3))) * (0.4f + q);
                float off = this.mobR * 0.15f * (i % 2);
                this.monLineP.setColor(i % 2 == 0 ? -1 : monShade(col, 1.3f));
                this.monLineP.setAlpha(monA(0.65f - (0.2f * q)));
                c.drawLine(lx, top - off, lx, (top - off) - len, this.monLineP);
            }
            float tr = this.mobR * (0.6f + (1.4f * q));
            this.monRect.set(this.mobX - tr, this.groundY - (tr * 0.28f), this.mobX + tr, this.groundY + (tr * 0.28f));
            this.monLineP.setColor(col);
            this.monLineP.setStrokeWidth(this.hu * 0.6f);
            this.monLineP.setAlpha(monA(0.5f + (0.4f * (float) Math.sin(this.time * 20.0f))));
            c.drawOval(this.monRect, this.monLineP);
        } else if (it > 0.4f) {
            float k = monClamp((1.0f - it) / 0.6f);
            float e = 1.0f - ((1.0f - k) * (1.0f - k));
            for (int j = 0; j < 2; j++) {
                float kk = Math.max(0.0f, e - (j * 0.18f));
                float rx = this.mobR * (0.5f + (2.6f * kk));
                this.monRect.set(this.mobX - rx, this.groundY - (rx * 0.28f), this.mobX + rx, this.groundY + (rx * 0.28f));
                this.monLineP.setStrokeWidth(Math.max(1.0f, this.pad * 0.9f * (1.0f - k)));
                this.monLineP.setColor(j == 0 ? 0xFFFFEBC8 : col);
                this.monLineP.setAlpha(monA((1.0f - k) * 0.85f));
                c.drawOval(this.monRect, this.monLineP);
            }
        }
    }

    // summon pillar, ground ring and dust puffs when a new monster pops in
    void monDrawSpawnFx(Canvas c, long now, boolean boss) {
        float k = (now - this.monSpawnAt) / 500.0f;
        if (k < 0.0f || k >= 1.0f || (boss && this.bossIntroT > 0.0f)) {
            return;
        }
        float inv = 1.0f - k;
        float e = 1.0f - (inv * inv);
        float gx = this.mobX;
        float gy = this.groundY;
        float pw = this.mobR * 0.85f * inv;
        float top = gy - (this.mobR * 2.7f);
        this.monFillP.setColor(0xFFFFF6E0);
        this.monFillP.setAlpha(monA(inv * inv * 0.4f));
        c.drawRect(gx - pw, top, gx + pw, gy, this.monFillP);
        this.monFillP.setAlpha(monA(inv * inv * 0.7f));
        c.drawRect(gx - (pw * 0.35f), top, gx + (pw * 0.35f), gy, this.monFillP);
        float rx = this.mobR * (0.35f + (1.5f * e));
        this.monRect.set(gx - rx, gy - (rx * 0.28f), gx + rx, gy + (rx * 0.28f));
        this.monLineP.setStrokeWidth(Math.max(1.0f, this.pad * 0.5f * inv));
        this.monLineP.setColor(monShade(mobColor(), 1.3f));
        this.monLineP.setAlpha(monA(inv * 0.8f));
        c.drawOval(this.monRect, this.monLineP);
        this.monFillP.setColor(0xFFE9E2D2);
        this.monFillP.setAlpha(monA(inv * 0.85f));
        float d = this.mobR * (0.3f + (1.15f * e));
        for (int i = 0; i < 10; i++) {
            double a = Math.toRadians((i * 36.0f) + 12.0f);
            float x = gx + ((float) Math.cos(a) * d);
            float y = ((gy - (this.hu * 1.2f)) + ((float) Math.sin(a) * d * 0.3f)) - (e * this.mobR * 0.12f * (i % 3));
            float sz = this.hu * (1.7f - (1.1f * k)) * (i % 2 == 0 ? 1.0f : 0.7f);
            c.drawRect(x - sz, y - sz, x + sz, y + sz, this.monFillP);
        }
    }

    // death: white ghost expanding out of the shattered sprite, flash, ground shockwave (+ light rays for bosses)
    void monDrawGhost(Canvas c, long now) {
        Bitmap b = this.monDeadBmp;
        if (b == null) {
            return;
        }
        float k = (now - this.monDeadAt) / (this.monDeadBoss ? 850.0f : 480.0f);
        if (k < 0.0f || k >= 1.0f) {
            return;
        }
        float inv = 1.0f - k;
        float e = 1.0f - (inv * inv);
        float cy = this.monDeadB - (b.getHeight() * this.monDeadS * this.monDeadSy * 0.5f);
        this.monFillP.setColor(-1);
        this.monFillP.setAlpha(monA(inv * inv * inv * 0.5f));
        c.drawCircle(this.monDeadX, cy, this.mobR * (0.5f + (1.2f * e)), this.monFillP);
        float gs = this.monDeadS * (1.0f + (0.7f * e));
        this.monWhiteP.setAlpha(monA(inv * inv * inv * 0.7f));
        blit(c, b, this.monDeadX, cy + (b.getHeight() * gs * this.monDeadSy * 0.5f), gs, this.monDeadSx, this.monDeadSy, this.monWhiteP);
        float rx = this.mobR * (0.4f + ((this.monDeadBoss ? 2.8f : 2.0f) * e));
        this.monRect.set(this.monDeadX - rx, this.groundY - (rx * 0.28f), this.monDeadX + rx, this.groundY + (rx * 0.28f));
        this.monLineP.setStrokeWidth(Math.max(1.0f, this.pad * 0.8f * inv));
        this.monLineP.setColor(monShade(this.monDeadCol, 1.25f));
        this.monLineP.setAlpha(monA(inv * 0.9f));
        c.drawOval(this.monRect, this.monLineP);
        if (this.monDeadBoss) {
            this.monLineP.setColor(0xFFFFF2C0);
            this.monLineP.setStrokeWidth(Math.max(1.0f, this.hu * 1.4f * inv));
            this.monLineP.setAlpha(monA(inv * 0.8f));
            for (int i = 0; i < 10; i++) {
                double a = Math.toRadians((i * 36.0f) + 9.0f + (k * 20.0f));
                float ca = (float) Math.cos(a);
                float sa = (float) Math.sin(a);
                float r0 = this.mobR * (0.35f + (1.3f * e));
                float r1 = r0 + (this.mobR * (0.4f + inv) * (i % 2 == 0 ? 1.0f : 0.6f));
                c.drawLine(this.monDeadX + (ca * r0), cy + (sa * r0), this.monDeadX + (ca * r1), cy + (sa * r1), this.monLineP);
            }
        }
    }

    // twinkling treasure glints on the mimic
    void monDrawGlints(Canvas c, float px, float bottom, float w, float h) {
        for (int i = 0; i < 3; i++) {
            float ph = ((this.time * 0.7f) + (i * 0.37f)) % 1.0f;
            if (ph > 0.3f) {
                continue;
            }
            float q = (float) Math.sin((ph / 0.3f) * Math.PI);
            float gx = px + (w * monGlintX[i]);
            float gy = bottom - (h * monGlintY[i]);
            float len = (this.hu * 2.4f * q) + (this.hu * 0.3f);
            float th = this.hu * 0.4f;
            this.monFillP.setColor(0xFFFFF3B8);
            this.monFillP.setAlpha(monA(q));
            c.drawRect(gx - len, gy - th, gx + len, gy + th, this.monFillP);
            c.drawRect(gx - th, gy - len, gx + th, gy + len, this.monFillP);
            this.monFillP.setColor(-1);
            this.monFillP.setAlpha(monA(q));
            c.drawRect(gx - (th * 1.5f), gy - (th * 1.5f), gx + (th * 1.5f), gy + (th * 1.5f), this.monFillP);
        }
    }

    // Truck-kun's headlight: stepped light cone aimed at the hero (front=false) and a lens flare (front=true)
    void monDrawBeam(Canvas c, boolean front, float px, float bottom, float cw, float ch, Bitmap b) {
        float hx = (px - (b.getWidth() * cw * 0.5f)) + (2.0f * cw);
        float hy = bottom - ((b.getHeight() - 11.0f) * ch);
        float fl = 0.85f + (0.15f * (float) Math.sin(this.time * 23.0f));
        if (front) {
            this.monFillP.setColor(0xFFFFF6C8);
            this.monFillP.setAlpha(monA(0.3f * fl));
            c.drawCircle(hx - (cw * 0.5f), hy, ch * 1.7f, this.monFillP);
            this.monFillP.setAlpha(monA(0.55f * fl));
            c.drawCircle(hx - (cw * 0.5f), hy, ch * 0.9f, this.monFillP);
            return;
        }
        float len = this.mobR * 2.4f;
        float spread = this.mobR * 0.42f;
        this.monFillP.setColor(0xFFFFE45A);
        for (int i = 0; i < 3; i++) {
            float k = 1.0f - (i * 0.3f);
            this.monFillP.setAlpha(monA((0.1f + (i * 0.05f)) * fl));
            this.monPath.reset();
            this.monPath.moveTo(hx, hy - (ch * 0.6f));
            this.monPath.lineTo(hx - (len * k), hy - (spread * k * 0.7f));
            this.monPath.lineTo(hx - (len * k), hy + (spread * k));
            this.monPath.lineTo(hx, hy + (ch * 0.6f));
            this.monPath.close();
            c.drawPath(this.monPath, this.monFillP);
        }
    }

    // bevel, segment ticks, gloss sweep and edge glint over a filled bar (clipped to the fill)
    void monBarGloss(Canvas c, float l, float tp0, float fillR, float barW, float barH, boolean boss) {
        if (fillR <= l + this.pad) {
            return;
        }
        c.save();
        c.clipRect(l, tp0, fillR, tp0 + barH);
        this.monFillP.setColor(-1);
        this.monFillP.setAlpha(80);
        c.drawRect(l + (this.pad * 0.5f), tp0 + (barH * 0.16f), fillR - (this.pad * 0.5f), tp0 + (barH * 0.38f), this.monFillP);
        this.monFillP.setColor(-16777216);
        this.monFillP.setAlpha(60);
        c.drawRect(l, tp0 + (barH * 0.72f), fillR, tp0 + barH, this.monFillP);
        this.monFillP.setAlpha(70);
        float tw = Math.max(1.0f, this.hu * 0.22f);
        for (int i = 1; i < 10; i++) {
            float x = l + ((barW * i) / 10.0f);
            if (x >= fillR) {
                break;
            }
            c.drawRect(x - tw, tp0, x + tw, tp0 + barH, this.monFillP);
        }
        float sw = ((this.time * 0.5f) % 1.6f) - 0.3f;
        float gx = l + (sw * barW);
        this.monFillP.setColor(-1);
        this.monFillP.setAlpha(boss ? 95 : 60);
        c.drawRect(gx - (this.pad * 0.6f), tp0, gx + (this.pad * 0.6f), tp0 + barH, this.monFillP);
        this.monFillP.setAlpha(boss ? 50 : 30);
        c.drawRect(gx + (this.pad * 0.9f), tp0, gx + (this.pad * 1.2f), tp0 + barH, this.monFillP);
        c.restore();
        this.monFillP.setColor(-1);
        this.monFillP.setAlpha(monA(0.5f + (0.4f * (float) Math.sin(this.time * 6.0f))));
        c.drawRect(fillR - (this.pad * 0.6f), tp0 + (barH * 0.22f), fillR - (this.pad * 0.36f), tp0 + (barH * 0.78f), this.monFillP);
    }

    void monEnsureWsGlow(int rad) {
        rad = Math.max(1, rad);
        if (rad == this.monWsR && this.monWsGlowP.getShader() != null) {
            return;
        }
        this.monWsR = rad;
        this.monWsGlowP.setShader(new RadialGradient(0.0f, 0.0f, rad, monWsCols, monGlowStops, Shader.TileMode.CLAMP));
    }

    // weak-spot target: halo, countdown arc, closing ring, spinning crosshair, lock-on ticks, core
    void monDrawWeak(Canvas c, float cx, float cy, float base, float pulse, float k, long now) {
        k = monClamp(k);
        float age = Math.max(0.0f, (this.wsLife - (this.wsUntil - now)) / 1000.0f);
        float intro = monClamp(age / 0.22f);
        float zoom = 1.0f + ((1.0f - intro) * (1.0f - intro) * 1.8f);
        boolean urgent = k < 0.3f;
        float blink = urgent ? 0.55f + (0.45f * (float) Math.sin(this.time * 32.0f)) : 1.0f;
        int warm = monLerpC(0xFFFF4A4A, -10496, k);
        this.monEnsureWsGlow((int) (base * 3.0f));
        this.monWsGlowP.setAlpha(monA(intro * (0.55f + (0.25f * (float) Math.sin(this.time * 8.0f))) * (urgent ? 0.6f + (0.4f * blink) : 1.0f)));
        c.save();
        c.translate(cx, cy);
        c.drawCircle(0.0f, 0.0f, this.monWsR, this.monWsGlowP);
        c.restore();
        this.monFillP.setColor(-16777216);
        this.monFillP.setAlpha(monA(intro * 0.45f));
        c.drawCircle(cx, cy, base * 1.2f, this.monFillP);
        float ar = base * 1.6f;
        this.monRect.set(cx - ar, cy - ar, cx + ar, cy + ar);
        this.monLineP.setStrokeWidth(this.pad * 0.55f);
        this.monLineP.setColor(-16777216);
        this.monLineP.setAlpha(monA(intro * 0.5f));
        c.drawOval(this.monRect, this.monLineP);
        this.monLineP.setColor(warm);
        this.monLineP.setAlpha(monA(intro * blink));
        c.drawArc(this.monRect, -90.0f, 360.0f * k, false, this.monLineP);
        double ha = Math.toRadians((360.0f * k) - 90.0f);
        this.monFillP.setColor(-1);
        this.monFillP.setAlpha(monA(intro * blink));
        c.drawCircle(cx + ((float) Math.cos(ha) * ar), cy + ((float) Math.sin(ha) * ar), this.pad * 0.42f, this.monFillP);
        this.monLineP.setStrokeWidth(this.pad * 0.3f);
        this.monLineP.setColor(-42406);
        this.monLineP.setAlpha(monA(intro * (0.35f + (0.65f * (1.0f - k)))));
        c.drawCircle(cx, cy, base * ((2.2f * k) + 1.0f), this.monLineP);
        float cr = base * pulse * zoom * 0.95f;
        this.monRect.set(cx - cr, cy - cr, cx + cr, cy + cr);
        this.monLineP.setStrokeWidth(this.pad * 0.45f);
        this.monLineP.setColor(-10496);
        this.monLineP.setAlpha(monA(intro * blink));
        float rot = (this.time * 150.0f) % 360.0f;
        for (int i = 0; i < 4; i++) {
            c.drawArc(this.monRect, rot + (i * 90.0f) + 20.0f, 50.0f, false, this.monLineP);
        }
        c.save();
        c.rotate(-((this.time * 60.0f) % 360.0f), cx, cy);
        float r1 = base * 1.95f * zoom;
        float r2 = base * 2.5f * zoom;
        float w = this.pad * 0.22f;
        this.monFillP.setColor(urgent ? 0xFFFF6A6A : -1);
        this.monFillP.setAlpha(monA(intro * 0.9f * blink));
        c.drawRect(cx + r1, cy - w, cx + r2, cy + w, this.monFillP);
        c.drawRect(cx - r2, cy - w, cx - r1, cy + w, this.monFillP);
        c.drawRect(cx - w, cy - r2, cx + w, cy - r1, this.monFillP);
        c.drawRect(cx - w, cy + r1, cx + w, cy + r2, this.monFillP);
        c.restore();
        this.monFillP.setColor(-1);
        c.drawCircle(cx, cy, base * 0.32f * ((0.18f * (float) Math.sin(this.time * 20.0f)) + 1.0f), this.monFillP);
        this.monFillP.setColor(warm);
        c.drawCircle(cx, cy, base * 0.15f, this.monFillP);
    }

    void drawMonster(Canvas var1) {
        if (this.W <= 0 || this.H <= 0) {
            return;
        }
        Canvas c = var1;
        boolean boss = this.bossFight();
        Bitmap bmp = this.mobBitmap();
        long now = SystemClock.uptimeMillis();
        float t = this.time;
        float s0 = this.mu * (boss ? 1.3f : 1.0f);
        if (bmp != null && bmp.getWidth() > 24) {
            s0 *= 0.62f;
        }
        float spawnK = this.monSpawnK();
        if (this.mobSpawn + 1.0E-4f < this.monPrevSpawn) {
            this.monSpawnAt = now;
        }
        this.monPrevSpawn = this.mobSpawn;

        // HP ratio and delayed "damage trail" (purely visual smoothing)
        double hpR = this.monsterMax > 0.0d ? this.monsterHp / this.monsterMax : 0.0d;
        float hpK = Double.isNaN(hpR) ? 0.0f : (float) Math.max(0.0d, Math.min(1.0d, hpR));
        float dt = this.monHpMs == 0L ? 0.0f : Math.max(0.0f, Math.min(0.1f, (now - this.monHpMs) / 1000.0f));
        this.monHpMs = now;
        if (hpK >= this.monHpTrail) {
            this.monHpTrail = hpK;
        } else {
            this.monHpTrail = Math.max(hpK, (this.monHpTrail - ((this.monHpTrail - hpK) * Math.min(1.0f, dt * 3.2f))) - (dt * 0.04f));
        }

        // boss drop from the sky (same curve as before)
        float span = Math.max(1.0f, this.groundY - this.battleT);
        float drop = 0.0f;
        float introQ = 0.0f;
        if (boss && this.bossIntroT > 1.0f) {
            introQ = 1.0f - ((1.6f - this.bossIntroT) / 0.6f);
            drop = -(this.groundY - this.battleT) * introQ * introQ;
        }

        // per-species idle motion
        int kind = bmp != null ? this.monKind(bmp) : 0;
        boolean danger = hpK > 0.0f && hpK < 0.3f && this.monsterHp > 0.0d;
        float sq;
        float lift = 0.0f;
        float tilt = 0.0f;
        float jx = 0.0f;
        switch (kind) {
            case 1: {
                float ph = (t / (boss ? 1.5f : 1.05f)) % 1.0f;
                sq = 0.0f;
                if (ph < 0.42f) {
                    float hh = (float) Math.sin((ph / 0.42f) * Math.PI);
                    lift = hh * this.mobR * (boss ? 0.08f : 0.13f);
                    sq = -0.07f * hh;
                } else if (ph < 0.62f) {
                    float q = 1.0f - ((ph - 0.42f) / 0.2f);
                    sq = 0.11f * q * q;
                } else if (ph > 0.84f) {
                    sq = ((ph - 0.84f) / 0.16f) * 0.06f;
                }
                break;
            }
            case 2:
                lift = this.mobR * (0.11f + (0.06f * (float) Math.sin(t * 2.6f)));
                sq = ((float) Math.sin(t * 10.4f)) * 0.035f;
                tilt = ((float) Math.sin(t * 1.3f)) * 2.5f;
                break;
            case 3:
                sq = ((float) Math.sin(t * (danger ? 4.0f : 2.2f))) * 0.05f;
                break;
            case 4:
                sq = ((float) Math.sin(t * 4.0f)) * 0.03f;
                tilt = ((float) Math.sin(t * 1.9f)) * 2.0f;
                if ((t * 0.6f) % 1.0f < 0.14f) {
                    jx = ((float) Math.sin(t * 80.0f)) * this.hu * 0.5f;
                }
                break;
            case 5:
                lift = Math.abs((float) Math.sin(t * 36.0f)) * this.hu * 0.35f;
                sq = ((float) Math.sin(t * 18.0f)) * 0.012f;
                break;
            default:
                sq = ((float) Math.sin(t * (danger ? 7.0f : 4.0f))) * 0.04f;
                break;
        }
        if (danger) {
            jx += ((float) Math.sin(t * 55.0f)) * this.hu * 0.3f;
        }

        // hit reaction: knockback away from the hero, backward tilt, impact jitter
        float hit = monClamp(this.mobHit);
        float h2 = hit * hit;
        float recoil = h2 * this.mobR * (boss ? 0.12f : 0.22f);
        tilt += h2 * (boss ? 3.0f : 6.5f);
        if (hit > 0.5f) {
            jx += ((float) Math.sin(t * 95.0f)) * this.hu * 0.6f * hit;
        }
        float v = sq + (hit * 0.14f) + (this.beatPulse * 0.07f);
        float sx = 1.0f + v;
        float sy = 1.0f - v;
        float scale = s0 * spawnK;
        float px = this.mobX + recoil + jx;
        float bottom = ((this.groundY + s0) + drop) - lift;
        float bwPx = bmp != null ? bmp.getWidth() : 18.0f;
        float bhPx = bmp != null ? bmp.getHeight() : 18.0f;
        float sprW = bwPx * scale * sx;
        float sprH = bhPx * scale * sy;
        int bossCol = this.towerFight ? -24512 : -2078616;
        float ringA = boss ? (drop < 0.0f ? monClamp(1.0f - introQ) : 1.0f) : 0.0f;
        float rrx = Math.max(this.mobR * 1.35f, sprW * 0.62f);
        float rry = rrx * 0.28f;

        // --- ground layer: light pool, back half of the rune circle, rising embers
        if (boss) {
            this.monEnsureGlow((int) (this.mobR * 1.8f), bossCol);
            float pul = 0.5f + (0.5f * (float) Math.sin(t * 3.2f));
            this.monGlow(c, this.mobX, this.groundY, 1.25f, 0.3f, monA(ringA * (0.55f + (0.25f * pul))));
            this.monDrawRing(c, false, bossCol, this.mobX, this.groundY, rrx, rry, ringA);
            for (int i = 0; i < 12; i++) {
                float ph = (float) (((t * 0.5d) + (i / 12.0d)) % 1.0d);
                float ex = this.mobX + ((float) Math.sin((i * 2.4d) + t) * this.mobR * 0.9f);
                float ey = (this.groundY - (this.mobR * ph * 2.6f)) + drop;
                float es = this.hu * (1.6f - (1.1f * ph));
                this.p.setColor(this.towerFight ? -24512 : -5234614);
                this.p.setAlpha((int) ((1.0f - ph) * 150.0f));
                c.drawRect(ex - es, ey - es, ex + es, ey + es, this.p);
                float cs = es * 0.45f;
                this.p.setColor(0xFFFFE0B0);
                this.p.setAlpha((int) ((1.0f - ph) * (1.0f - ph) * 200.0f));
                c.drawRect(ex - cs, ey - cs, ex + cs, ey + cs, this.p);
            }
            this.p.setAlpha(255);
        } else if (this.chest) {
            this.monEnsureGlow((int) (this.mobR * 1.5f), -11430);
            this.monGlow(c, this.mobX, this.groundY, 1.2f, 0.3f, monA(0.35f + (0.15f * (float) Math.sin(t * 4.0f))));
        }

        // --- stepped soft shadow that shrinks while the monster is airborne
        float fallK = drop < 0.0f ? Math.max(0.2f, (drop / span) + 1.0f) : 1.0f;
        float liftK = 1.0f - Math.min(0.45f, (lift / Math.max(1.0f, this.mobR)) * 1.6f);
        float shw = Math.max(s0 * 8.0f, bwPx * s0 * 0.46f) * spawnK * fallK * liftK * (1.0f + (v * 0.5f));
        float shh = s0 * (0.8f + (0.2f * liftK));
        float shx = this.mobX + (recoil * 0.4f);
        this.monFillP.setColor(0x26000000);
        this.monRect.set(shx - (shw * 1.22f), this.groundY - (shh * 1.45f), shx + (shw * 1.22f), this.groundY + (shh * 1.45f));
        c.drawOval(this.monRect, this.monFillP);
        this.monFillP.setColor(0x38000000);
        this.monRect.set(shx - shw, this.groundY - shh, shx + shw, this.groundY + shh);
        c.drawOval(this.monRect, this.monFillP);
        this.monFillP.setColor(0x40000000);
        this.monRect.set(shx - (shw * 0.62f), this.groundY - (shh * 0.65f), shx + (shw * 0.62f), this.groundY + (shh * 0.65f));
        c.drawOval(this.monRect, this.monFillP);

        // --- sprite (aura, rim light, body, tints, flash)
        if (bmp != null) {
            float midY = bottom - (sprH * 0.5f);
            if (boss) {
                float pz = 1.0f + (0.07f * (float) Math.sin(t * 3.2f)) + (this.beatPulse * 0.12f) + (h2 * 0.1f);
                float gr = Math.max(1.0f, this.monGlowR);
                this.monGlow(c, px, midY, Math.max(1.45f, sprW / gr) * pz, Math.max(1.45f, sprH / gr) * pz, monA(ringA * (0.7f + (0.3f * (float) Math.sin(t * 3.2f)))));
            }
            c.save();
            c.rotate(tilt, px, bottom);
            if (kind == 5) {
                this.monDrawBeam(c, false, px, bottom, scale * sx, scale * sy, bmp);
            }
            Paint rim = boss ? (this.towerFight ? this.monRimTowerP : this.monRimBossP) : (this.chest ? this.monRimGoldP : null);
            if (rim != null) {
                float ra = boss ? 0.38f + (0.22f * (float) Math.sin(t * 3.2f)) + (0.2f * this.beatPulse) : 0.32f + (0.26f * (float) Math.sin(t * 4.0f));
                rim.setAlpha(monA(ra));
                float ox = scale * sx;
                blit(c, bmp, px - ox, bottom, scale, sx, sy, rim);
                blit(c, bmp, px + ox, bottom, scale, sx, sy, rim);
                blit(c, bmp, px, bottom - (scale * sy), scale, sx, sy, rim);
            }
            blit(c, bmp, px, bottom, scale, sx, sy, hit > 0.7f ? this.flashP : this.bp);
            if (danger) {
                this.monRedP.setAlpha(monA(0.16f + (0.16f * (float) Math.sin(t * 9.0f))));
                blit(c, bmp, px, bottom, scale, sx, sy, this.monRedP);
            }
            float hq = hit > 0.55f ? (hit - 0.55f) / 0.45f : 0.0f;
            float fl = Math.max(hq * hq * 0.75f, 1.0f - monClamp(this.mobSpawn));
            if (fl > 0.01f) {
                this.monWhiteP.setAlpha(monA(fl));
                blit(c, bmp, px, bottom, scale, sx, sy, this.monWhiteP);
            }
            if (kind == 5) {
                this.monDrawBeam(c, true, px, bottom, scale * sx, scale * sy, bmp);
            }
            c.restore();
            this.monDrawnBmp = bmp;
            this.monDrawnX = px;
            this.monDrawnB = bottom;
            this.monDrawnS = scale;
            this.monDrawnSx = sx;
            this.monDrawnSy = sy;
            if (this.chest && !boss) {
                this.monDrawGlints(c, px, bottom, sprW, sprH);
            }
        }

        // --- foreground fx
        if (boss) {
            this.monDrawRing(c, true, bossCol, this.mobX, this.groundY, rrx, rry, ringA);
            this.monDrawIntroFx(c, px, bottom, sprW, sprH, bossCol);
        }
        this.monDrawSpawnFx(c, now, boss);
        this.monDrawGhost(c, now);

        // --- name plate, HP bar, boss timer (positions and texts unchanged)
        if (this.overlay != 11 || this.superHit) {
            if (!boss || !(this.bossIntroT > 0.25F)) {
                float barW = (float) this.W * 0.42F;
                float barL = this.mobX - (barW / 2.0F);
                float barT = this.battleT + (this.pad * 8.0F);
                float barH = this.pad * 1.6F;
                this.tp.setTextAlign(Align.CENTER);
                this.tp.setTextSize(this.pad * 2.0F);
                this.tp.setFakeBoldText(true);
                String name = new StringBuilder().append(boss ? "👑 " : "").append(this.mobName()).toString();
                this.tp.setColor(-872415232);
                c.drawText(name, this.mobX + (0.25F * this.pad), barT - (this.pad * 0.55F), this.tp);
                this.tp.setColor(boss ? -38278 : (this.chest ? -11430 : -1));
                c.drawText(name, this.mobX, barT - (this.pad * 0.8F), this.tp);
                this.tp.setFakeBoldText(false);
                this.p.setColor(-16777216);
                this.r.set(barL, barT, barL + barW, barT + barH);
                c.drawRoundRect(this.r, this.pad * 0.8F, this.pad * 0.8F, this.p);
                if (this.monHpTrail > hpK + 0.002f) {
                    this.monFillP.setColor(0xFFFFF1C4);
                    this.monFillP.setAlpha(monA(0.75f + (0.25f * (float) Math.sin(t * 18.0f))));
                    this.monRect.set(barL, barT, barL + (this.monHpTrail * barW), barT + barH);
                    c.drawRoundRect(this.monRect, this.pad * 0.8F, this.pad * 0.8F, this.monFillP);
                }
                int fillC = boss ? -2078616 : -10822550;
                if (hpK < 0.25f) {
                    fillC = monShade(fillC, 1.0f + (0.35f * (0.5f + (0.5f * (float) Math.sin(t * 10.0f)))));
                }
                this.p.setColor(fillC);
                this.r.set(barL, barT, (hpK * barW) + barL, barT + barH);
                c.drawRoundRect(this.r, this.pad * 0.8F, this.pad * 0.8F, this.p);
                this.monBarGloss(c, barL, barT, barL + (hpK * barW), barW, barH, boss);
                this.monLineP.setStrokeWidth(Math.max(1.0f, this.hu * 0.3f));
                this.monLineP.setColor(boss ? 0x99FFD35A : 0x55FFFFFF);
                this.monRect.set(barL, barT, barL + barW, barT + barH);
                c.drawRoundRect(this.monRect, this.pad * 0.8F, this.pad * 0.8F, this.monLineP);
                this.tp.setTextSize(this.pad * 1.3F);
                String hpTxt = fmt(Math.max(0.0d, this.monsterHp)) + " / " + fmt(this.monsterMax);
                this.tp.setColor(0xB4000000);
                c.drawText(hpTxt, this.mobX + (this.hu * 0.25f), (this.pad * 1.2F) + barT + (this.hu * 0.25f), this.tp);
                this.tp.setColor(-1);
                c.drawText(hpTxt, this.mobX, (this.pad * 1.2F) + barT, this.tp);
                if (boss) {
                    float tm = this.bossTimeMax();
                    float tk = tm > 0.0f ? Math.max(0.0F, this.bossTime / tm) : 0.0f;
                    if (Float.isNaN(tk)) {
                        tk = 0.0f;
                    }
                    float y2 = barT + (this.pad * 2.2F);
                    float tH = this.pad * 0.9F;
                    boolean hurry = tk < 0.3F;
                    this.p.setColor(-16777216);
                    this.r.set(barL, y2, barL + barW, tH + y2);
                    c.drawRoundRect(this.r, this.pad * 0.45F, this.pad * 0.45F, this.p);
                    float hb = 0.5f + (0.5f * (float) Math.sin(t * 14.0f));
                    this.p.setColor(hurry ? monShade(-46518, 0.8f + (0.4f * hb)) : -16310);
                    float tR = Math.min(barW, barW * tk) + barL;
                    this.r.set(barL, y2, tR, tH + y2);
                    c.drawRoundRect(this.r, this.pad * 0.45F, this.pad * 0.45F, this.p);
                    if (tR - barL > this.pad) {
                        this.monFillP.setColor(-1);
                        this.monFillP.setAlpha(75);
                        c.drawRect(barL + (this.pad * 0.4f), y2 + (tH * 0.14f), tR - (this.pad * 0.4f), y2 + (tH * 0.4f), this.monFillP);
                    }
                    if (hurry) {
                        float o = this.hu * 0.45f;
                        this.monLineP.setStrokeWidth(Math.max(1.0f, this.hu * 0.4f));
                        this.monLineP.setColor(-46518);
                        this.monLineP.setAlpha(monA(0.3f + (0.6f * hb)));
                        this.monRect.set(barL - o, y2 - o, barL + barW + o, tH + y2 + o);
                        c.drawRoundRect(this.monRect, this.pad * 0.6F, this.pad * 0.6F, this.monLineP);
                    }
                    this.tp.setTextSize(this.pad * 1.4F);
                    this.tp.setColor(hurry ? -38294 : -8032);
                    StringBuilder tsb = new StringBuilder().append("⏳ ").append((int) Math.ceil((double) Math.max(0.0F, this.bossTime))).append("с");
                    String trTxt = this.trait() > 0 ? "  " + TRAIT_SHORT[this.trait()] : "";
                    c.drawText(tsb.append(trTxt).toString(), this.mobX, (this.pad * 2.5F) + y2, this.tp);
                    if (this.storyBoss()) {
                        this.tp.setTextSize(this.pad * 1.45F);
                        this.tp.setFakeBoldText(true);
                        float tw = this.tp.measureText("📖 Сюжетный босс");
                        float pd = this.pad;
                        float by = y2 + (3.6F * pd);
                        float half = (tw + (pd * 2.0F)) / 2.0F;
                        this.r.set(this.mobX - half, by, this.mobX + half, (pd * 2.4F) + by);
                        this.p.setColor(-583390704);
                        c.drawRoundRect(this.r, this.pad * 1.2F, this.pad * 1.2F, this.p);
                        this.p.setStyle(Style.STROKE);
                        this.p.setStrokeWidth(this.pad * 0.18F);
                        this.p.setColor(-11430);
                        this.p.setAlpha((int) ((Math.sin((double) (this.time * 3.0F)) * 85.0d) + 170.0d));
                        c.drawRoundRect(this.r, this.pad * 1.2F, this.pad * 1.2F, this.p);
                        this.p.setStyle(Style.FILL);
                        this.p.setAlpha(255);
                        this.tp.setColor(-11430);
                        c.drawText("📖 Сюжетный босс", this.mobX, by + (this.pad * 1.7F), this.tp);
                        this.tp.setFakeBoldText(false);
                    }
                }
            }
        }
    }

    // ===== ovl: overlay & dialog rendering helpers (visual only) =====
    static final float[] ovlGlowPos = {0.0f, 0.3f, 1.0f};
    static final String[] ovlStarStr = {"☆☆☆", "★☆☆", "★★☆", "★★★"};
    static final String[] ovlHolyGlyph = {"✦", "◆", "✧", "❖", "✦", "◇", "✧", "❖"};
    final Paint ovlP = new Paint(1);
    final Paint ovlS = new Paint(1);
    final Paint ovlGlowP = new Paint(1);
    final Paint ovlBackP = new Paint();
    final Path ovlPath = new Path();
    final Path ovlPath2 = new Path();
    final PathMeasure ovlPM = new PathMeasure();
    final float[] ovlTip = new float[2];
    final RectF ovlR = new RectF();
    final RectF ovlR2 = new RectF();
    final RadialGradient[] ovlGlowSh = new RadialGradient[12];
    final int[] ovlGlowKey = new int[12];
    int ovlGlowNext;
    Shader ovlBackSh;
    Shader ovlVigSh;
    int ovlBackKey = -1;
    Shader ovlDeathSh;
    int ovlDeathKey = -1;
    final float[] ovlSw = new float[4];
    float ovlSwT = -1.0f;

    static float ovlHash(int k, int salt) {
        int h = (k * 374761393) + (salt * 668265263);
        h = (h ^ (h >>> 13)) * 1274126177;
        return ((h ^ (h >>> 16)) & 65535) / 65535.0f;
    }

    static int ovlMix(int a, int b, float t) {
        if (t <= 0.0f) {
            return a;
        }
        if (t >= 1.0f) {
            return b;
        }
        return Color.argb((int) (Color.alpha(a) + ((Color.alpha(b) - Color.alpha(a)) * t)), (int) (Color.red(a) + ((Color.red(b) - Color.red(a)) * t)), (int) (Color.green(a) + ((Color.green(b) - Color.green(a)) * t)), (int) (Color.blue(a) + ((Color.blue(b) - Color.blue(a)) * t)));
    }

    static int ovlClampA(float a) {
        return a <= 0.0f ? 0 : a >= 255.0f ? 255 : (int) a;
    }

    /** 0..1 progress since the current overlay opened. */
    float ovlOpenT(float ms) {
        long d = SystemClock.uptimeMillis() - this.overlayAt;
        if (d < 0 || ms <= 0.0f) {
            return 1.0f;
        }
        return Math.min(1.0f, d / ms);
    }

    static float ovlEaseBack(float t) {
        float u = t - 1.0f;
        return (2.70158f * u * u * u) + 1.0f + (1.70158f * u * u);
    }

    /** Soft radial glow; one cached unit gradient per colour. */
    void ovlGlow(Canvas c, float x, float y, float rad, int color, int alpha) {
        if (rad < 1.0f || alpha <= 0) {
            return;
        }
        int rgb = color | (-16777216);
        RadialGradient sh = null;
        for (int k = 0; k < 12; k++) {
            if (this.ovlGlowSh[k] != null && this.ovlGlowKey[k] == rgb) {
                sh = this.ovlGlowSh[k];
                break;
            }
        }
        if (sh == null) {
            int k2 = this.ovlGlowNext;
            this.ovlGlowNext = (k2 + 1) % 12;
            int base = rgb & 16777215;
            sh = new RadialGradient(0.0f, 0.0f, 1.0f, new int[]{rgb, 1711276032 | base, base}, ovlGlowPos, Shader.TileMode.CLAMP);
            this.ovlGlowSh[k2] = sh;
            this.ovlGlowKey[k2] = rgb;
        }
        this.ovlGlowP.setShader(sh);
        this.ovlGlowP.setAlpha(Math.min(255, alpha));
        c.save();
        c.translate(x, y);
        c.scale(rad, rad);
        c.drawRect(-1.0f, -1.0f, 1.0f, 1.0f, this.ovlGlowP);
        c.restore();
    }

    /** Rotating light rays (one path, one draw). */
    void ovlRays(Canvas c, float x, float y, float len, int n, float rotDeg, float halfW, int color, int alpha) {
        if (len < 1.0f || alpha <= 0 || n <= 0) {
            return;
        }
        this.ovlPath.reset();
        double rot = Math.toRadians(rotDeg);
        for (int k = 0; k < n; k++) {
            double a = rot + ((k * 6.283185307179586d) / n);
            float l2 = len * ((k % 2 == 0) ? 1.0f : 0.72f);
            this.ovlPath.moveTo(x, y);
            this.ovlPath.lineTo(x + (((float) Math.cos(a - halfW)) * l2), y + (((float) Math.sin(a - halfW)) * l2));
            this.ovlPath.lineTo(x + (((float) Math.cos(a + halfW)) * l2), y + (((float) Math.sin(a + halfW)) * l2));
            this.ovlPath.close();
        }
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlP.setColor(color);
        this.ovlP.setAlpha(Math.min(255, alpha));
        c.drawPath(this.ovlPath, this.ovlP);
    }

    /** Pixel 4-point sparkle. */
    void ovlSpark(Canvas c, float x, float y, float s, int color, int alpha) {
        if (alpha <= 0 || s <= 0.0f) {
            return;
        }
        float w = Math.max(1.0f, s * 0.2f);
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlP.setColor(color);
        this.ovlP.setAlpha(Math.min(255, alpha));
        c.drawRect(x - s, y - w, x + s, y + w, this.ovlP);
        c.drawRect(x - w, y - s, x + w, y + s, this.ovlP);
        this.ovlP.setColor(-1);
        this.ovlP.setAlpha(Math.min(255, alpha));
        float w2 = w * 1.4f;
        c.drawRect(x - w2, y - w2, x + w2, y + w2, this.ovlP);
    }

    void ovlGem(Canvas c, float x, float y, float s, int color, int alpha) {
        this.ovlP.setStyle(Paint.Style.FILL);
        float o = s * 1.45f;
        this.ovlPath.reset();
        this.ovlPath.moveTo(x, y - o);
        this.ovlPath.lineTo(x + o, y);
        this.ovlPath.lineTo(x, y + o);
        this.ovlPath.lineTo(x - o, y);
        this.ovlPath.close();
        this.ovlP.setColor(-15724524);
        this.ovlP.setAlpha(alpha);
        c.drawPath(this.ovlPath, this.ovlP);
        this.ovlPath.reset();
        this.ovlPath.moveTo(x, y - s);
        this.ovlPath.lineTo(x + s, y);
        this.ovlPath.lineTo(x, y + s);
        this.ovlPath.lineTo(x - s, y);
        this.ovlPath.close();
        this.ovlP.setColor(color);
        this.ovlP.setAlpha(alpha);
        c.drawPath(this.ovlPath, this.ovlP);
        this.ovlP.setColor(-1);
        this.ovlP.setAlpha((alpha * 200) / 255);
        float h = s * 0.32f;
        c.drawRect((x - (s * 0.42f)) - h, (y - (s * 0.42f)) - h, (x - (s * 0.42f)) + h, (y - (s * 0.42f)) + h, this.ovlP);
    }

    /** Ornate dialog panel: drop shadow, two-tone fill, double border, corner gems, travelling glint. */
    void ovlFrame(Canvas c, float l, float t, float r, float b, int accent, float a) {
        if (r - l < 8.0f || b - t < 8.0f) {
            return;
        }
        int A = ovlClampA(a * 255.0f);
        if (A == 0) {
            return;
        }
        float rad = this.pad * 1.6f;
        float px = Math.max(1.0f, this.pad * 0.22f);
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlR.set(l, t + (px * 2.5f), r, b + (px * 2.5f));
        this.ovlP.setColor(-16777216);
        this.ovlP.setAlpha((A * 120) / 255);
        c.drawRoundRect(this.ovlR, rad, rad, this.ovlP);
        this.ovlR.set(l, t, r, b);
        this.ovlP.setColor(-15069648);
        this.ovlP.setAlpha((A * 215) / 255);
        c.drawRoundRect(this.ovlR, rad, rad, this.ovlP);
        this.ovlR2.set(l + px, t + px, r - px, t + Math.min((b - t) * 0.42f, this.pad * 14.0f));
        this.ovlP.setColor(accent);
        this.ovlP.setAlpha((A * 16) / 255);
        c.drawRoundRect(this.ovlR2, rad, rad, this.ovlP);
        this.ovlR2.set(l + px, b - (this.pad * 2.5f), r - px, b - px);
        this.ovlP.setColor(-16777216);
        this.ovlP.setAlpha((A * 50) / 255);
        c.drawRoundRect(this.ovlR2, rad, rad, this.ovlP);
        this.ovlS.setStyle(Paint.Style.STROKE);
        this.ovlS.setStrokeWidth(px * 1.6f);
        this.ovlS.setColor(ovlMix(accent, -16777216, 0.45f));
        this.ovlS.setAlpha(A);
        c.drawRoundRect(this.ovlR, rad, rad, this.ovlS);
        this.ovlS.setStrokeWidth(px * 0.8f);
        this.ovlS.setColor(accent);
        this.ovlS.setAlpha((A * 230) / 255);
        c.drawRoundRect(this.ovlR, rad, rad, this.ovlS);
        float in = px * 3.4f;
        this.ovlR2.set(l + in, t + in, r - in, b - in);
        this.ovlS.setStrokeWidth(px * 0.6f);
        this.ovlS.setAlpha((A * 90) / 255);
        c.drawRoundRect(this.ovlR2, rad * 0.6f, rad * 0.6f, this.ovlS);
        float span = (r - l) - (rad * 2.0f);
        if (span > 0.0f) {
            float ph = (this.time * 0.22f) % 1.0f;
            float gx = l + rad + (span * ph);
            float fade = (float) Math.sin(ph * 3.141592653589793d);
            ovlGlow(c, gx, t, this.pad * 2.2f, -1, (int) (A * 0.55f * fade));
            ovlGlow(c, r - (rad + (span * ph)), b, this.pad * 1.8f, accent, (int) (A * 0.45f * fade));
        }
        float cr = rad * 0.3f;
        float gs = this.pad * 0.55f;
        ovlGem(c, l + cr, t + cr, gs, accent, A);
        ovlGem(c, r - cr, t + cr, gs, accent, A);
        ovlGem(c, l + cr, b - cr, gs, accent, A);
        ovlGem(c, r - cr, b - cr, gs, accent, A);
        float cx = (l + r) / 2.0f;
        this.ovlP.setColor(accent);
        this.ovlP.setAlpha((A * 200) / 255);
        c.drawRect(cx - (this.pad * 3.2f), t - (px * 0.4f), cx + (this.pad * 3.2f), t + (px * 0.4f), this.ovlP);
        ovlGem(c, cx, t, this.pad * 0.85f, accent, A);
        ovlGlow(c, cx, t, this.pad * 2.4f, accent, (int) (A * (0.3f + (((float) Math.sin(this.time * 3.0f)) * 0.15f))));
    }

    /** Ornamental divider line with centre gem. */
    void ovlDivider(Canvas c, float cx, float y, float halfW, int color, int alpha) {
        if (halfW < 2.0f || alpha <= 0) {
            return;
        }
        float h = Math.max(1.0f, this.pad * 0.12f);
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlP.setColor(color);
        for (int k = 0; k < 3; k++) {
            float w0 = halfW * (1.0f - (k * 0.3f));
            this.ovlP.setAlpha((alpha * (60 + (k * 60))) / 255);
            c.drawRect(cx - w0, y - h, cx + w0, y + h, this.ovlP);
        }
        ovlGem(c, cx, y, this.pad * 0.5f, color, alpha);
    }

    /** Beveled glossy button drawn over an existing rect (rect untouched). */
    void ovlButton(Canvas c, RectF rr, int col, boolean hot) {
        float rad = this.pad * 1.5f;
        float px = Math.max(1.0f, this.pad * 0.22f);
        if (hot) {
            ovlGlow(c, rr.centerX(), rr.centerY(), rr.width() * 0.62f, col, (int) ((Math.sin(this.time * 4.0f) * 35.0d) + 75.0d));
        }
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlR.set(rr.left, rr.top + (px * 2.2f), rr.right, rr.bottom + (px * 2.2f));
        this.ovlP.setColor(-1946157056);
        c.drawRoundRect(this.ovlR, rad, rad, this.ovlP);
        this.ovlR.set(rr.left, rr.top + px, rr.right, rr.bottom + px);
        this.ovlP.setColor(ovlMix(col, -16777216, 0.45f));
        c.drawRoundRect(this.ovlR, rad, rad, this.ovlP);
        this.ovlP.setColor(col);
        c.drawRoundRect(rr, rad, rad, this.ovlP);
        this.ovlR.set(rr.left + px, rr.top + px, rr.right - px, rr.top + (rr.height() * 0.5f));
        this.ovlP.setColor(-1);
        this.ovlP.setAlpha(34);
        c.drawRoundRect(this.ovlR, rad * 0.8f, rad * 0.8f, this.ovlP);
        this.ovlP.setAlpha(110);
        c.drawRect(rr.left + rad, rr.top + (px * 0.6f), rr.right - rad, rr.top + (px * 1.3f), this.ovlP);
        this.ovlS.setStyle(Paint.Style.STROKE);
        this.ovlS.setStrokeWidth(px * 0.8f);
        this.ovlS.setColor(ovlMix(col, -1, 0.35f));
        this.ovlS.setAlpha(hot ? 230 : 150);
        c.drawRoundRect(rr, rad, rad, this.ovlS);
        if (hot) {
            float ph = (this.time * 0.55f) % 1.7f;
            if (ph < 1.0f) {
                float h = rr.height();
                float x0 = (rr.left - h) + ((rr.width() + (h * 2.0f)) * ph);
                float sw = h * 0.45f;
                c.save();
                c.clipRect(rr);
                this.ovlPath.reset();
                this.ovlPath.moveTo(x0, rr.bottom);
                this.ovlPath.lineTo((h * 0.6f) + x0, rr.top);
                this.ovlPath.lineTo((h * 0.6f) + x0 + sw, rr.top);
                this.ovlPath.lineTo(x0 + sw, rr.bottom);
                this.ovlPath.close();
                this.ovlP.setColor(-1);
                this.ovlP.setAlpha(55);
                c.drawPath(this.ovlPath, this.ovlP);
                c.restore();
            }
        }
    }

    /** Title text with a dark drop shadow and a coloured glow (shadow layer on text only). */
    void ovlTitle(Canvas c, String s, float x, float y, float maxW, int glow) {
        int col = this.tp.getColor();
        float off = this.pad * 0.3f;
        this.tp.setColor(-15725032);
        this.tp.setAlpha(Color.alpha(col));
        if (maxW > 0.0f) {
            drawFit(c, s, x + off, y + off, maxW);
        } else {
            c.drawText(s, x + off, y + off, this.tp);
        }
        this.tp.setColor(col);
        this.tp.setShadowLayer(this.pad * 1.1f, 0.0f, 0.0f, (glow & 16777215) | (((Color.alpha(glow) * Color.alpha(col)) / 255) << 24));
        if (maxW > 0.0f) {
            drawFit(c, s, x, y, maxW);
        } else {
            c.drawText(s, x, y, this.tp);
        }
        this.tp.clearShadowLayer();
    }

    /** Builds the cached backdrop shaders when the view size changes. */
    void ovlEnsureBack() {
        int key = (this.W * 8191) + this.H;
        if (this.ovlBackSh == null || this.ovlBackKey != key) {
            this.ovlBackKey = key;
            int w = this.W;
            int h = this.H;
            this.ovlBackSh = new android.graphics.LinearGradient(0.0f, 0.0f, 0.0f, h, new int[]{-233173458, -301332972, -200998128}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
            this.ovlVigSh = new RadialGradient(w / 2.0f, h * 0.45f, Math.max(1.0f, ((float) Math.hypot(w, h)) * 0.6f), new int[]{0, 0, -1442840576}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
        }
    }

    /** Dim backdrop: cached vertical gradient + radial vignette + drifting motes. */
    void ovlBackdrop(Canvas c, float a) {
        int w = this.W;
        int h = this.H;
        if (w <= 0 || h <= 0) {
            return;
        }
        ovlEnsureBack();
        int A = ovlClampA(a * 255.0f);
        this.ovlBackP.setShader(this.ovlBackSh);
        this.ovlBackP.setAlpha(A);
        c.drawRect(0.0f, 0.0f, w, h, this.ovlBackP);
        this.ovlBackP.setShader(this.ovlVigSh);
        this.ovlBackP.setAlpha(A);
        c.drawRect(0.0f, 0.0f, w, h, this.ovlBackP);
        this.ovlBackP.setShader(null);
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlP.setColor(-5720);
        for (int k = 0; k < 22; k++) {
            float sp = (ovlHash(k, 3) * 0.035f) + 0.015f;
            float y = h - ((((this.time * sp) + ovlHash(k, 2)) % 1.0f) * h);
            float x = (ovlHash(k, 1) * w) + (((float) Math.sin((this.time * 0.6f) + (k * 1.7f))) * this.pad * 1.2f);
            float s = this.pad * ((ovlHash(k, 4) * 0.2f) + 0.12f);
            this.ovlP.setAlpha((int) (A * ((((float) Math.sin((this.time * 2.0f) + k)) * 0.1f) + 0.14f)));
            c.drawRect(x - s, y - s, x + s, y + s, this.ovlP);
        }
    }

    /** Rising wisps (souls / embers) inside a box. */
    void ovlWisps(Canvas c, float l, float t, float r, float b, int n, int color, float a, int salt) {
        if (r <= l || b <= t || a <= 0.0f) {
            return;
        }
        float hh = b - t;
        this.ovlP.setStyle(Paint.Style.FILL);
        for (int k = 0; k < n; k++) {
            float sp = (ovlHash(k, salt) * 0.12f) + 0.06f;
            float ph = ((this.time * sp) + ovlHash(k, salt + 1)) % 1.0f;
            float y = b - (ph * hh);
            float x = l + (ovlHash(k, salt + 2) * (r - l)) + (((float) Math.sin((this.time * 1.3f) + (k * 2.1f))) * this.pad * 1.0f);
            float s = this.pad * ((ovlHash(k, salt + 3) * 0.2f) + 0.15f);
            float fa = ((float) Math.sin(ph * 3.141592653589793d)) * a;
            this.ovlP.setColor(color);
            this.ovlP.setAlpha((int) (fa * 150.0f));
            c.drawRect(x - s, y - s, x + s, y + s, this.ovlP);
            this.ovlP.setAlpha((int) (fa * 60.0f));
            c.drawRect(x - (s * 0.6f), y + s, x + (s * 0.6f), (s * 3.0f) + y, this.ovlP);
        }
    }

    /** Comet dot travelling around a rounded rect perimeter. */
    void ovlComet(Canvas c, RectF rr, float ph, int color, int alpha) {
        float w = rr.width();
        float h = rr.height();
        float per = (w + h) * 2.0f;
        if (per <= 0.0f) {
            return;
        }
        for (int k = 0; k < 6; k++) {
            float d = ((((ph - (k * 0.012f)) % 1.0f) + 1.0f) % 1.0f) * per;
            float x;
            float y;
            if (d < w) {
                x = rr.left + d;
                y = rr.top;
            } else if (d < w + h) {
                x = rr.right;
                y = rr.top + (d - w);
            } else if (d < (2.0f * w) + h) {
                x = rr.right - (d - (w + h));
                y = rr.bottom;
            } else {
                x = rr.left;
                y = rr.bottom - (d - ((2.0f * w) + h));
            }
            float s = this.pad * (0.42f - (k * 0.055f));
            this.ovlP.setStyle(Paint.Style.FILL);
            this.ovlP.setColor(k == 0 ? -1 : color);
            this.ovlP.setAlpha((alpha * (6 - k)) / 6);
            c.drawRect(x - s, y - s, x + s, y + s, this.ovlP);
        }
    }

    /** Animated pill toggle. pos 0 = off, 1 = on. */
    void ovlSwitch(Canvas c, RectF rr, float pos) {
        float h = rr.height();
        float rad = h / 2.0f;
        int track = ovlMix(-11911072, -12673970, pos);
        this.ovlP.setStyle(Paint.Style.FILL);
        if (pos > 0.05f) {
            ovlGlow(c, rr.centerX(), rr.centerY(), rr.width() * 0.75f, -12673970, (int) (pos * 70.0f));
        }
        this.ovlR.set(rr.left, rr.top + (h * 0.08f), rr.right, rr.bottom + (h * 0.08f));
        this.ovlP.setColor(-2013265920);
        c.drawRoundRect(this.ovlR, rad, rad, this.ovlP);
        this.ovlP.setColor(track);
        c.drawRoundRect(rr, rad, rad, this.ovlP);
        this.ovlR.set(rr.left + (h * 0.08f), rr.top + (h * 0.06f), rr.right - (h * 0.08f), rr.top + (h * 0.36f));
        this.ovlP.setColor(-16777216);
        this.ovlP.setAlpha(55);
        c.drawRoundRect(this.ovlR, rad * 0.5f, rad * 0.5f, this.ovlP);
        this.ovlS.setStyle(Paint.Style.STROKE);
        this.ovlS.setStrokeWidth(Math.max(1.0f, h * 0.06f));
        this.ovlS.setColor(ovlMix(track, -16777216, 0.4f));
        c.drawRoundRect(rr, rad, rad, this.ovlS);
        float m = h * 0.1f;
        this.ovlP.setColor(-1);
        this.ovlP.setAlpha((int) (pos * 200.0f));
        c.drawRect((rr.left + rad) - (m * 0.35f), rr.centerY() - (h * 0.2f), rr.left + rad + (m * 0.35f), rr.centerY() + (h * 0.2f), this.ovlP);
        this.ovlS.setColor(-1);
        this.ovlS.setAlpha((int) ((1.0f - pos) * 160.0f));
        c.drawCircle((rr.right - rad) + (h * 0.04f), rr.centerY(), h * 0.16f, this.ovlS);
        float kr = h * 0.4f;
        float kx = (rr.left + rad) + ((rr.width() - (rad * 2.0f)) * pos);
        float ky = rr.centerY();
        this.ovlP.setColor(-2013265920);
        c.drawCircle(kx, (h * 0.08f) + ky, kr, this.ovlP);
        this.ovlP.setColor(-3158065);
        c.drawCircle(kx, ky, kr, this.ovlP);
        this.ovlP.setColor(-1);
        c.drawCircle(kx, ky - (kr * 0.12f), kr * 0.86f, this.ovlP);
        this.ovlP.setColor(ovlMix(-7569238, -12673970, pos));
        this.ovlP.setAlpha(200);
        c.drawCircle(kx, ky, kr * 0.22f, this.ovlP);
    }

    /** Expanding shockwave ring. */
    void ovlShock(Canvas c, float x, float y, float rad, float width, int color, int alpha) {
        if (alpha <= 0 || rad <= 0.0f) {
            return;
        }
        this.ovlS.setStyle(Paint.Style.STROKE);
        this.ovlS.setStrokeWidth(Math.max(1.0f, width));
        this.ovlS.setColor(color);
        this.ovlS.setAlpha(Math.min(255, alpha));
        c.drawCircle(x, y, rad, this.ovlS);
        this.ovlS.setStrokeWidth(Math.max(1.0f, width * 0.35f));
        this.ovlS.setColor(-1);
        this.ovlS.setAlpha(Math.min(255, alpha));
        c.drawCircle(x, y, rad, this.ovlS);
    }

    /** Radial impact burst lines. */
    void ovlBurst(Canvas c, float x, float y, float r0, float r1, int n, float rotDeg, int color, int alpha, float width) {
        if (alpha <= 0 || r1 <= r0) {
            return;
        }
        this.ovlS.setStyle(Paint.Style.STROKE);
        this.ovlS.setStrokeCap(Paint.Cap.ROUND);
        this.ovlS.setStrokeWidth(Math.max(1.0f, width));
        this.ovlS.setColor(color);
        this.ovlS.setAlpha(Math.min(255, alpha));
        double rot = Math.toRadians(rotDeg);
        for (int k = 0; k < n; k++) {
            double a = rot + ((k * 6.283185307179586d) / n);
            float ca = (float) Math.cos(a);
            float sa = (float) Math.sin(a);
            float rr1 = r1 * ((k % 3 == 0) ? 1.0f : 0.75f);
            c.drawLine((ca * r0) + x, (sa * r0) + y, (ca * rr1) + x, (sa * rr1) + y, this.ovlS);
        }
        this.ovlS.setStrokeCap(Paint.Cap.BUTT);
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
            if (max > 0.0f && this.W > 0 && this.H > 0) {
                int dkey = (this.W * 8191) + this.H;
                if (this.ovlDeathSh == null || this.ovlDeathKey != dkey) {
                    this.ovlDeathKey = dkey;
                    this.ovlDeathSh = new RadialGradient(this.W / 2.0f, this.H * 0.47f, Math.max(1.0f, ((float) Math.hypot(this.W, this.H)) * 0.58f), new int[]{0, 805961744, -1056569320}, new float[]{0.0f, 0.55f, 1.0f}, Shader.TileMode.CLAMP);
                }
                this.ovlBackP.setShader(this.ovlDeathSh);
                this.ovlBackP.setAlpha((int) (max * 255.0f));
                canvas.drawRect(0.0f, 0.0f, this.W, this.H, this.ovlBackP);
                this.ovlBackP.setShader(null);
                ovlGlow(canvas, this.W / 2.0f, this.H * 0.47f, this.W * 0.55f, -7395302, (int) (max * 70.0f));
                ovlWisps(canvas, 0.0f, this.H * 0.15f, this.W, this.H, 26, -32704, max, 11);
            }
            float f = this.deathT;
            if (f > 2.5f) {
                float min = Math.min(1.0f, (f - 2.5f) * 3.0f);
                this.tp.setTextAlign(Paint.Align.CENTER);
                this.tp.setTextSize(this.pad * 2.8f * ((1.0f - min) * 0.25f + 1.0f));
                this.tp.setFakeBoldText(true);
                this.tp.setColor(-1);
                int i4 = (int) (min * 255.0f);
                this.tp.setAlpha(i4);
                String str = this.deathCap;
                int i5 = this.W;
                ovlTitle(canvas, str, i5 / 2.0f, 0.45f * this.H, i5 - (this.pad * 4.0f), -2672576);
                this.tp.setTextSize(this.pad * 2.8f);
                float ovlEase = 1.0f - ((1.0f - min) * (1.0f - min));
                ovlDivider(canvas, this.W / 2.0f, this.H * 0.475f, this.W * 0.3f * ovlEase, -2672576, i4);
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
        float ovlA = ovlOpenT(220.0f);
        ovlBackdrop(canvas, (ovlA * 0.45f) + 0.55f);
        this.tp.setTextAlign(Paint.Align.CENTER);
        int i6 = this.overlay;
        int ovlSave = canvas.save();
        if (i6 != 9 && i6 != 12) {
            float ovlS0 = (ovlEaseBack(ovlOpenT(320.0f)) * 0.1f) + 0.9f;
            canvas.scale(ovlS0, ovlS0, this.W / 2.0f, this.H / 2.0f);
        }
        try {
        if (i6 == 1) {
            ovlFrame(canvas, this.pad * 1.2f, this.H * 0.12f, this.W - (this.pad * 1.2f), this.H * 0.9f, -3624896, ovlA);
            ovlGlow(canvas, this.W / 2.0f, this.H * 0.42f, this.W * 0.42f, -11430, (int) ((Math.sin(this.time * 1.2f) * 8.0d) + 26.0d));
            if (this.storyTitle != null) {
                this.tp.setColor(-11430);
                this.tp.setTextSize(this.pad * 2.4f);
                this.tp.setFakeBoldText(true);
                String str2 = this.storyTitle;
                int i7 = this.W;
                i2 = -7569238;
                ovlTitle(canvas, str2, i7 / 2.0f, 0.2f * this.H, i7 - (this.pad * 4.0f), -2058456);
                this.tp.setFakeBoldText(false);
                ovlDivider(canvas, this.W / 2.0f, (this.H * 0.2f) + (this.pad * 1.6f), this.W * 0.28f, -3624896, (int) (ovlA * 255.0f));
            } else {
                i2 = -7569238;
            }
            String[] strArr = this.storyCur;
            String[] split = strArr[Math.min(this.storyPage, strArr.length - 1)].split("\n");
            long uptimeMillis = SystemClock.uptimeMillis();
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * SUPER_END);
            float ovlPg = Math.min(1.0f, Math.max(0.0f, (uptimeMillis - this.storyPageAt) / 400.0f));
            this.tp.setAlpha((int) (ovlPg * 255.0f));
            this.tp.setShadowLayer(this.pad * 0.35f, 0.0f, this.pad * 0.25f, ((int) (ovlPg * 200.0f)) << 24);
            float length = ((this.H * 0.42f) - ((split.length * this.pad) * 2.0f)) + ((1.0f - ovlPg) * (1.0f - ovlPg) * this.pad * 1.5f);
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
            this.tp.clearShadowLayer();
            this.tp.setAlpha(255);
            this.tp.setTextSize(this.pad * 1.7f);
            this.tp.setColor(i2);
            boolean ovlReady = storyTapReady(uptimeMillis);
            int ovlHintA = ovlReady ? (int) ((Math.sin(this.time * 4.0f) * 100.0d) + 150.0d) : OV_NONE;
            this.tp.setAlpha(ovlHintA);
            canvas.drawText("тап — дальше   (" + (this.storyPage + 1) + "/" + this.storyCur.length + ")", this.W / 2.0f, this.H * 0.85f, this.tp);
            this.tp.setAlpha(255);
            if (ovlReady) {
                float ovlBob = ((float) Math.abs(Math.sin(this.time * 4.0f))) * this.pad * 0.5f;
                float ovlTx = this.W / 2.0f;
                float ovlTy = (this.H * 0.85f) + (this.pad * 1.0f) + ovlBob;
                this.ovlPath.reset();
                this.ovlPath.moveTo(ovlTx - (this.pad * 0.7f), ovlTy);
                this.ovlPath.lineTo((this.pad * 0.7f) + ovlTx, ovlTy);
                this.ovlPath.lineTo(ovlTx, (this.pad * 0.7f) + ovlTy);
                this.ovlPath.close();
                this.ovlP.setStyle(Paint.Style.FILL);
                this.ovlP.setColor(-11430);
                this.ovlP.setAlpha(ovlHintA);
                canvas.drawPath(this.ovlPath, this.ovlP);
            }
            int ovlN = this.storyCur.length;
            if (ovlN > 1 && ovlN < 30) {
                float ovlDx = this.pad * 1.4f;
                float ovlX0 = (this.W / 2.0f) - (((ovlN - 1) * ovlDx) / 2.0f);
                for (int ovlK = 0; ovlK < ovlN; ovlK++) {
                    boolean ovlCur = ovlK == this.storyPage;
                    ovlGem(canvas, (ovlK * ovlDx) + ovlX0, this.H * 0.885f, this.pad * (ovlCur ? 0.38f : 0.24f), ovlK <= this.storyPage ? -11430 : -12963760, (int) (ovlA * 255.0f));
                }
            }
            return;
        }
        if (i6 == 2) {
            ovlFrame(canvas, this.pad * 1.5f, this.H * 0.2f, this.W - (this.pad * 1.5f), this.H * 0.745f, -8395009, ovlA);
            ovlWisps(canvas, this.pad * 3.0f, this.H * 0.22f, this.W - (this.pad * 3.0f), this.H * 0.58f, 14, -8395009, ovlA * 0.8f, 21);
            ovlGlow(canvas, this.W / 2.0f, this.H * 0.27f, this.W * 0.3f, -8395009, (int) ((Math.sin(this.time * 2.0f) * 12.0d) + 40.0d));
            this.tp.setColor(-8395009);
            this.tp.setTextSize(this.pad * 3.2f);
            this.tp.setFakeBoldText(true);
            ovlTitle(canvas, "Реинкарнация", this.W / 2.0f, this.H * 0.28f, 0.0f, -11563047);
            this.tp.setFakeBoldText(false);
            ovlDivider(canvas, this.W / 2.0f, (this.H * 0.28f) + (this.pad * 1.8f), this.W * 0.26f, -8395009, (int) (ovlA * 255.0f));
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
            ovlButton(canvas, this.r, -12963760, false);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.2f);
            canvas.drawText("Ещё поживу", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            RectF rectF3 = this.r;
            int i11 = this.W;
            float f6 = this.pad;
            int i12 = this.H;
            rectF3.set((i11 / 2.0f) + (f6 * 1.5f), i12 * 0.6f, i11 - (f6 * 3.0f), i12 * 0.7f);
            ovlButton(canvas, this.r, -11563047, true);
            this.tp.setColor(-1);
            canvas.drawText("Переродиться", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            return;
        }
        if (i6 == 5) {
            ovlFrame(canvas, this.pad * 0.8f, this.H * 0.085f, this.W - (this.pad * 0.8f), this.H * 0.965f, -3624896, ovlA);
            this.tp.setColor(-11430);
            this.tp.setTextSize(this.pad * 3.2f);
            this.tp.setFakeBoldText(true);
            ovlTitle(canvas, "🎒 Реликвии", this.W / 2.0f, this.H * 0.14f, 0.0f, -2058456);
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
                if (z) {
                    panel(canvas, rectF5, f13 * IMPACT, -13753520);
                    ovlGlow(canvas, this.r.centerX(), (f9 * 0.3f) + f12, 0.36f * f9, REL_COLOR[i13], (int) ((Math.sin((this.time * 2.2f) + i13) * 30.0d) + 120.0d));
                    ovlRays(canvas, this.r.centerX(), (f9 * 0.3f) + f12, 0.34f * f9, 8, (this.time * 18.0f) + (i13 * 20), 0.12f, REL_COLOR[i13], 40);
                    this.ovlS.setStyle(Paint.Style.STROKE);
                    this.ovlS.setStrokeWidth(Math.max(1.0f, this.pad * 0.2f));
                    this.ovlS.setColor(REL_COLOR[i13]);
                    this.ovlS.setAlpha(this.rel[i13] >= 10 ? (int) ((Math.sin(this.time * 4.0f) * 60.0d) + 180.0d) : 130);
                    canvas.drawRoundRect(this.r, this.pad * IMPACT, this.pad * IMPACT, this.ovlS);
                    float ovlPh = ((this.time * 0.35f) + (i13 * 0.37f)) % 2.0f;
                    if (ovlPh < 1.0f) {
                        float ovlX = (this.r.left - (f9 * 0.4f)) + ((f8 + (f9 * 0.8f)) * ovlPh);
                        canvas.save();
                        canvas.clipRect(this.r);
                        this.ovlPath.reset();
                        this.ovlPath.moveTo(ovlX, this.r.bottom);
                        this.ovlPath.lineTo((f9 * 0.35f) + ovlX, this.r.top);
                        this.ovlPath.lineTo((f9 * 0.47f) + ovlX, this.r.top);
                        this.ovlPath.lineTo((f9 * 0.12f) + ovlX, this.r.bottom);
                        this.ovlPath.close();
                        this.ovlP.setStyle(Paint.Style.FILL);
                        this.ovlP.setColor(-1);
                        this.ovlP.setAlpha(26);
                        canvas.drawPath(this.ovlPath, this.ovlP);
                        canvas.restore();
                    }
                    if (this.rel[i13] >= 10) {
                        ovlSpark(canvas, this.r.right - (this.pad * 1.4f), this.r.top + (this.pad * 1.4f), this.pad * ((((float) Math.sin((this.time * 5.0f) + i13)) * 0.25f) + 0.75f), -5720, 230);
                        ovlSpark(canvas, this.r.left + (this.pad * 1.4f), this.r.bottom - (this.pad * 1.4f), this.pad * ((((float) Math.sin((this.time * 5.0f) + i13 + 2.0f)) * 0.25f) + 0.6f), -5720, 200);
                    }
                } else {
                    canvas.drawRoundRect(rectF5, f13 * IMPACT, f13 * IMPACT, this.p);
                    this.ovlS.setStyle(Paint.Style.STROKE);
                    this.ovlS.setStrokeWidth(Math.max(1.0f, this.pad * 0.15f));
                    this.ovlS.setColor(-13753520);
                    this.ovlS.setAlpha(255);
                    this.ovlR.set(this.r.left + (this.pad * 0.5f), this.r.top + (this.pad * 0.5f), this.r.right - (this.pad * 0.5f), this.r.bottom - (this.pad * 0.5f));
                    canvas.drawRoundRect(this.ovlR, this.pad * 0.8f, this.pad * 0.8f, this.ovlS);
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
                if (this.phonesOn) {
                    ovlGlow(canvas, this.phonesBtn.centerX(), this.phonesBtn.centerY(), this.phonesBtn.width() * 0.6f, -7722390, (int) ((Math.sin(this.time * 5.0f) * 30.0d) + 70.0d));
                }
                panel(canvas, this.phonesBtn, this.pad * IMPACT, this.phonesOn ? -7722390 : -13753520);
                if (this.phonesOn) {
                    for (int ovlK = 0; ovlK < 3; ovlK++) {
                        float ovlPh = ((this.time * 0.8f) + (ovlK / 3.0f)) % 1.0f;
                        this.ovlP.setStyle(Paint.Style.FILL);
                        this.ovlP.setColor(-20264);
                        this.ovlP.setAlpha((int) ((1.0f - ovlPh) * 200.0f));
                        float ovlNx = this.phonesBtn.left + (this.pad * (2.0f + (ovlK * 1.6f)));
                        float ovlNy = this.phonesBtn.top - (ovlPh * this.pad * 2.5f);
                        canvas.drawRect(ovlNx, ovlNy - (this.pad * 0.9f), (this.pad * 0.2f) + ovlNx, ovlNy, this.ovlP);
                        canvas.drawRect(ovlNx - (this.pad * 0.45f), ovlNy - (this.pad * 0.2f), ovlNx + (this.pad * 0.2f), (this.pad * 0.2f) + ovlNy, this.ovlP);
                    }
                }
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
            ovlFrame(canvas, this.pad * 1.0f, this.H * 0.175f, this.W - (this.pad * 1.0f), this.H * 0.875f, -3624896, ovlA);
            ovlRays(canvas, this.W / 2.0f, this.H * 0.235f, this.W * 0.32f, 14, this.time * 12.0f, 0.09f, -11430, 22);
            this.tp.setColor(-11430);
            this.tp.setTextSize(this.pad * 3.2f);
            this.tp.setFakeBoldText(true);
            ovlTitle(canvas, "🎁 Ежедневный подарок", this.W / 2.0f, this.H * 0.25f, 0.0f, -2058456);
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
                if (i16 == dailyDay) {
                    this.r.offset(0.0f, (-((float) Math.abs(Math.sin(this.time * 3.0f)))) * this.pad * 0.5f);
                    ovlGlow(canvas, this.r.centerX(), this.r.centerY(), f15 * 1.2f, -11430, (int) ((Math.sin(this.time * 3.0f) * 30.0d) + 110.0d));
                    ovlRays(canvas, this.r.centerX(), this.r.centerY(), f15 * 1.1f, 10, this.time * 40.0f, 0.12f, -5720, 50);
                }
                int ovlDc = i16 == dailyDay ? -4158934 : i16 < dailyDay ? -14015424 : -14411203;
                this.p.setColor(ovlDc);
                RectF rectF8 = this.r;
                float f17 = this.pad;
                panel(canvas, rectF8, f17, ovlDc);
                if (i16 == 7 && i16 != dailyDay) {
                    this.ovlS.setStyle(Paint.Style.STROKE);
                    this.ovlS.setStrokeWidth(Math.max(1.0f, this.pad * 0.18f));
                    this.ovlS.setColor(-8395009);
                    this.ovlS.setAlpha((int) ((Math.sin(this.time * 2.5f) * 50.0d) + 120.0d));
                    canvas.drawRoundRect(this.r, f17, f17, this.ovlS);
                }
                if (i16 < dailyDay) {
                    this.ovlP.setStyle(Paint.Style.FILL);
                    this.ovlP.setColor(-8592501);
                    this.ovlP.setAlpha(40);
                    canvas.drawRoundRect(this.r, f17, f17, this.ovlP);
                }
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
            ovlButton(canvas, this.r, -4158934, true);
            for (int ovlK = 0; ovlK < 4; ovlK++) {
                double ovlA2 = (this.time * 1.4f) + (ovlK * 1.5707963267948966d);
                ovlSpark(canvas, this.r.centerX() + (((float) Math.cos(ovlA2)) * this.r.width() * 0.56f), this.r.centerY() + (((float) Math.sin(ovlA2)) * this.r.height() * 0.75f), this.pad * ((((float) Math.sin((this.time * 6.0f) + ovlK)) * 0.25f) + 0.55f), -5720, 200);
            }
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
            ovlFrame(canvas, this.pad * 1.5f, this.H * 0.19f, this.W - (this.pad * 1.5f), this.H * 0.795f, -5720, ovlA);
            for (int ovlK = 0; ovlK < 14; ovlK++) {
                float ovlTw = (float) Math.sin((this.time * 2.5f) + (ovlK * 1.9f));
                if (ovlTw > 0.2f) {
                    ovlSpark(canvas, (this.pad * 3.0f) + (ovlHash(ovlK, 31) * (this.W - (this.pad * 6.0f))), (this.H * 0.2f) + (ovlHash(ovlK, 32) * this.H * 0.44f), this.pad * 0.5f * ovlTw, -5720, (int) (ovlTw * 120.0f * ovlA));
                }
            }
            ovlRays(canvas, this.W / 2.0f, this.H * 0.25f, this.W * 0.36f, 16, this.time * -10.0f, 0.07f, -5720, 26);
            ovlGlow(canvas, this.W / 2.0f, this.H * 0.25f, this.W * 0.3f, -5720, (int) ((Math.sin(this.time * 2.0f) * 15.0d) + 45.0d));
            this.tp.setColor(-5720);
            this.tp.setTextSize(this.pad * 3.4f);
            this.tp.setFakeBoldText(true);
            ovlTitle(canvas, "✦ Вознесение", this.W / 2.0f, this.H * 0.26f, 0.0f, -7712808);
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
            ovlButton(canvas, this.r, -12963760, false);
            this.tp.setColor(-1);
            this.tp.setTextSize(this.pad * 2.2f);
            canvas.drawText("Не сейчас", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            RectF rectF14 = this.r;
            int i26 = this.W;
            float f24 = this.pad;
            int i27 = this.H;
            rectF14.set((i26 / 2.0f) + (f24 * 1.5f), i27 * 0.66f, i26 - (f24 * 3.0f), i27 * 0.76f);
            ovlButton(canvas, this.r, -7712808, true);
            this.tp.setColor(-1);
            canvas.drawText("Вознестись", this.r.centerX(), this.r.centerY() + (this.pad * 0.8f), this.tp);
            return;
        }
        if (i6 != 8) {
            if (i6 == 3) {
                ovlWisps(canvas, 0.0f, this.H * 0.1f, this.W, this.H, 30, -8395009, ovlA, 41);
                float ovlBr = (((float) Math.sin(this.time * 1.6f)) * 0.08f) + 0.92f;
                ovlRays(canvas, this.W / 2.0f, this.H * 0.49f, this.W * 0.62f * ovlBr * ovlA, 18, this.time * 8.0f, 0.06f, -8395009, 24);
                ovlGlow(canvas, this.W / 2.0f, this.H * 0.49f, this.W * 0.45f * ovlBr, -8395009, (int) (70.0f * ovlA));
                ovlGlow(canvas, this.W / 2.0f, this.H * 0.49f, this.W * 0.16f * ovlBr, -1, (int) (60.0f * ovlA));
                this.tp.setColor(-1);
                this.tp.setTextSize(this.pad * 2.4f);
                canvas.drawText("Тьма... тишина...", this.W / 2.0f, this.H * 0.36f, this.tp);
                canvas.drawText("И снова — первый вдох.", this.W / 2.0f, this.H * 0.41f, this.tp);
                this.tp.setColor(-8395009);
                this.tp.setTextSize(this.pad * 3.0f);
                this.tp.setFakeBoldText(true);
                ovlTitle(canvas, "Жизнь №" + this.lifeNo, this.W / 2.0f, this.H * 0.5f, 0.0f, -11563047);
                ovlDivider(canvas, this.W / 2.0f, (this.H * 0.5f) + (this.pad * 1.6f), this.W * 0.22f * ovlA, -8395009, (int) (ovlA * 255.0f));
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
        ovlRays(canvas, this.W / 2.0f, this.H * 0.475f, this.W * 0.7f * ovlA, 20, this.time * 12.0f, 0.07f, -5720, 34);
        ovlGlow(canvas, this.W / 2.0f, this.H * 0.475f, this.W * 0.5f, -11430, (int) ((Math.sin(this.time * 2.0f) * 15.0d) + 65.0d));
        ovlWisps(canvas, 0.0f, 0.0f, this.W, this.H, 18, -5720, ovlA, 51);
        for (int i28 = OV_NONE; i28 < 40; i28++) {
            double d = i28;
            float sin = (((float) (((Math.sin(12.9898d * d) * 43758.5453d) % 1.0d) + 1.0d)) % 1.0f) * this.W;
            float sin2 = (((float) (((Math.sin(d * 78.233d) * 12345.678d) % 1.0d) + 1.0d)) % 1.0f) * this.H;
            this.p.setColor(-5720);
            this.p.setAlpha((int) ((Math.sin((this.time * 3.0f) + i28) * 120.0d) + 120.0d));
            canvas.drawCircle(sin, sin2, this.pad * 0.3f, this.p);
            if (i28 % 4 == 0) {
                float ovlTw = (float) Math.sin((this.time * 3.0f) + i28);
                if (ovlTw > 0.5f) {
                    ovlSpark(canvas, sin, sin2, this.pad * 1.1f * ovlTw, -5720, (int) ((ovlTw - 0.5f) * 400.0f));
                }
            }
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
        this.tp.setShadowLayer(this.pad * 1.2f, 0.0f, 0.0f, -11430);
        canvas.drawText(append2.append((i29 % 10 != 1 || i29 % 100 == 11) ? (i29 % 10 < 2 || i29 % 10 > 4 || (i29 % 100 >= 12 && i29 % 100 <= OV_SETTINGS)) ? "звёзд" : "звезды" : "звезда").toString(), this.W / 2.0f, this.H * 0.49f, this.tp);
        this.tp.clearShadowLayer();
        this.tp.setFakeBoldText(false);
        this.tp.setTextSize(this.pad * 2.0f);
        this.tp.setColor(-11430);
        canvas.drawText("Всё сильнее в ×" + fmt(starMult()) + " раз", this.W / 2.0f, this.H * 0.55f, this.tp);
        this.tp.setTextSize(this.pad * 1.7f);
        this.tp.setColor(-7569238);
        canvas.drawText("тап — начать заново, но уже легендой", this.W / 2.0f, this.H * 0.85f, this.tp);
        } finally {
            canvas.restoreToCount(ovlSave);
        }
    }

    void drawParty(Canvas var1) {
       float ovlA = this.ovlOpenT(220.0F);
       this.ovlFrame(var1, this.pad * 0.8F, (float)this.H * 0.05F, (float)this.W - this.pad * 0.8F, (float)this.H * 0.895F, -25909, ovlA);
       this.tp.setTextAlign(Align.CENTER);
       this.tp.setColor(-25909);
       this.tp.setTextSize(this.pad * 3.2F);
       this.tp.setFakeBoldText(true);
       this.ovlTitle(var1, "\ud83d\udc6d Спутницы", (float)this.W / 2.0F, (float)this.H * 0.1F, 0.0F, -45149);
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

          if (var11) {
             this.ovlGlow(var1, var15.centerX(), var15.centerY(), var15.width() * 0.62F, -25909, (int)(Math.sin((double)(this.time * 3.0F)) * 20.0 + 60.0));
          }

          this.panel(var1, var15, var2 * 1.4F, var9);
          if (var11) {
             this.ovlS.setStyle(Style.STROKE);
             this.ovlS.setStrokeWidth(Math.max(1.0F, this.pad * 0.25F));
             this.ovlS.setColor(-11430);
             this.ovlS.setAlpha((int)(Math.sin((double)(this.time * 4.0F)) * 50.0 + 190.0));
             var1.drawRoundRect(var15, this.pad * 1.4F, this.pad * 1.4F, this.ovlS);
             this.ovlComet(var1, var15, this.time * 0.18F, -11430, 230);
             this.ovlComet(var1, var15, this.time * 0.18F + 0.5F, -25909, 200);
          } else if (!var14) {
             this.ovlP.setStyle(Style.FILL);
             this.ovlP.setColor(-16777216);
             this.ovlP.setAlpha(60);
             var1.drawRoundRect(var15, this.pad * 1.4F, this.pad * 1.4F, this.ovlP);
          }

          Bitmap var16 = this.bmComp[var13];
          if (var16 == null) {
             var10++;
             var2 = var3;
             continue;
          }

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
          float ovlCx = var5 + var8 * 5.0F;
          float ovlFoot = var6 - var2 * 0.8F;
          float ovlSh = (float)var16.getWidth() * var7 * 0.45F;
          this.ovlP.setStyle(Style.FILL);
          this.ovlP.setColor(-16777216);
          this.ovlP.setAlpha(90);
          this.ovlR.set(ovlCx - ovlSh, ovlFoot - this.pad * 0.35F, ovlCx + ovlSh, ovlFoot + this.pad * 0.35F);
          var1.drawOval(this.ovlR, this.ovlP);
          float ovlBob = 0.0F;
          if (var14) {
             this.ovlGlow(var1, ovlCx, ovlFoot - (float)var16.getHeight() * var7 * 0.5F, var4 * 0.5F, var11 ? -11430 : -25909, var11 ? 110 : 45);
          }

          if (var11) {
             ovlBob = (float)Math.abs(Math.sin((double)(this.time * 3.0F))) * this.pad * 0.6F;
             for (int ovlK = 0; ovlK < 3; ovlK++) {
                float ovlTw = (float)Math.sin((double)(this.time * 4.0F + (float)ovlK * 2.1F));
                if (ovlTw > 0.0F) {
                   this.ovlSpark(var1, ovlCx + (float)(ovlK - 1) * this.pad * 2.6F, ovlFoot - var4 * (0.45F + 0.2F * (float)(ovlK % 2)), this.pad * 0.7F * ovlTw, -5720, (int)(ovlTw * 220.0F));
                }
             }
          }

          this.blit(var1, var16, ovlCx, ovlFoot - ovlBob, var7, 1.0F, 1.0F, var32);
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
        float f5 = f2 + (f / 2.0f);
        float ovlIn = ovlOpenT(500.0f);
        float ovlInE = 1.0f - ((1.0f - ovlIn) * (1.0f - ovlIn) * (1.0f - ovlIn));
        ovlRays(canvas, f4, f5, f * 0.85f * ovlInE, 16, this.time * 9.0f, 0.07f, -25909, 34);
        ovlRays(canvas, f4, f5, f * 0.7f * ovlInE, 10, this.time * -14.0f, 0.05f, -1, 22);
        ovlGlow(canvas, f4, f5, f * 0.78f, -45149, (int) ((Math.sin(this.time * 2.0f) * 25.0d) + 95.0d));
        this.ovlS.setStyle(Paint.Style.STROKE);
        this.ovlS.setStrokeWidth(Math.max(1.0f, this.pad * 0.2f));
        this.ovlS.setColor(-25909);
        this.ovlS.setAlpha((int) (90.0f * ovlInE));
        canvas.drawCircle(f4, f5, f * 0.56f, this.ovlS);
        this.ovlS.setAlpha((int) (45.0f * ovlInE));
        canvas.drawCircle(f4, f5, f * 0.6f, this.ovlS);
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlP.setColor(-16777216);
        this.ovlP.setAlpha(80);
        this.ovlR.set(f4 - (f * 0.32f), (f2 + f) - (this.pad * 0.6f), (f * 0.32f) + f4, f2 + f + (this.pad * 0.6f));
        canvas.drawOval(this.ovlR, this.ovlP);
        float f6 = f2 + f;
        blit(canvas, bitmap, f4, sin + f6 + (((f3 - 1.0f) * f) / 2.0f), (f / bitmap.getWidth()) * f3, 1.0f, 1.0f, this.bp);
        for (int i = OV_NONE; i < 6; i++) {
            double d = (this.time * 0.7d) + ((i * 3.141592653589793d) / 3.0d);
            float cos = (((float) Math.cos(d)) * f * 0.56f) + f4;
            float sin2 = (((float) Math.sin(d)) * f * 0.56f) + f5;
            float ovlTw = (float) Math.sin((this.time * 4.0f) + i);
            ovlSpark(canvas, cos, sin2, this.pad * ((ovlTw * 0.3f) + 0.75f), i % 2 == 0 ? -25909 : -5720, (int) ((ovlTw * 100.0f) + 150.0f));
        }
        this.p.setAlpha(255);
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setColor(-25909);
        this.tp.setTextSize(this.pad * 3.0f);
        this.tp.setFakeBoldText(true);
        ovlTitle(canvas, COMP_NAME[this.portraitWho], f4, f6 + (this.pad * 5.0f), 0.0f, -45149);
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
        RectF rectF = this.r;
        float f11 = this.pad;
        this.ovlP.setStyle(Paint.Style.FILL);
        this.ovlP.setColor(-16777216);
        this.ovlP.setAlpha(110);
        this.ovlR.set(rectF.left, rectF.top + (f11 * 0.4f), rectF.right, rectF.bottom + (f11 * 0.4f));
        canvas.drawRoundRect(this.ovlR, f11 * 1.5f, f11 * 1.5f, this.ovlP);
        this.p.setColor(-1);
        canvas.drawRoundRect(rectF, f11 * 1.5f, f11 * 1.5f, this.p);
        this.ovlS.setStyle(Paint.Style.STROKE);
        this.ovlS.setStrokeWidth(Math.max(1.0f, f11 * 0.18f));
        this.ovlS.setColor(-25909);
        this.ovlS.setAlpha(200);
        canvas.drawRoundRect(rectF, f11 * 1.5f, f11 * 1.5f, this.ovlS);
        Path path = this.ovlPath2;
        path.reset();
        path.moveTo(f4 - this.pad, (this.pad * 0.2f) + f9);
        path.lineTo(this.pad + f4, (this.pad * 0.2f) + f9);
        path.lineTo(f4, f9 - (this.pad * 1.3f));
        path.close();
        canvas.drawPath(path, this.p);
        this.ovlP.setColor(-25909);
        this.ovlP.setAlpha(70);
        this.ovlR.set(rectF.left + (f11 * 0.3f), rectF.bottom - (f11 * 0.7f), rectF.right - (f11 * 0.3f), rectF.bottom - (f11 * 0.25f));
        canvas.drawRoundRect(this.ovlR, f11 * 0.4f, f11 * 0.4f, this.ovlP);
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
        int sel = this.compSel;
        if (this.bmComp == null || sel < 0 || sel >= this.bmComp.length || this.bmComp[sel] == null) {
            return;
        }
        Bitmap bmp = this.bmComp[sel];
        float f = this.hu * 0.6f;
        float princessX = princessX();
        float t = this.time;
        float fr = heroFrenzyK();
        // little idle hop (bouncier while the hero is enraged), squash on landing, breathing, gentle sway
        float hop = Math.abs((float) Math.sin(t * (fr > 0.0f ? 3.4f : 2.0f)));
        float abs = hop * f * 0.6f;
        float bh = bmp.getHeight() * f;
        float bw = bmp.getWidth() * f;
        int gc = heroCompGlow[sel % heroCompGlow.length];
        float land = 1.0f - hop;
        float land4 = land * land * land * land;
        float sx = (0.045f * land4) + 1.0f;
        float sy = (1.0f - (0.04f * land4)) + (((float) Math.sin(t * 2.3f)) * 0.012f);
        heroShadow(canvas, princessX, this.groundY + (f * 0.2f), 10.0f * f * (1.0f - (0.12f * hop)), 2.3f * f * (1.0f - (0.12f * hop)), (int) ((1.0f - (0.2f * hop)) * 235.0f));
        float pulse = (float) Math.sin(t * 1.7f);
        float ar = bh * ((0.03f * pulse) + 0.62f);
        heroGlow(canvas, 4, gc, princessX, (this.groundY - (bh * 0.5f)) - abs, ar, ar, (int) ((30.0f * pulse) + 95.0f + (60.0f * fr)));
        float foot = (this.groundY + f) - abs;
        canvas.save();
        canvas.translate(princessX, this.groundY);
        canvas.skew(((float) Math.sin(t * 1.25f)) * 0.035f, 0.0f);
        canvas.translate(-princessX, -this.groundY);
        int ra = (int) ((20.0f * pulse) + 62.0f);
        heroSilhouette(canvas, bmp, princessX - f, foot, f, sx, sy, 3, gc, ra);
        heroSilhouette(canvas, bmp, princessX + f, foot, f, sx, sy, 3, gc, ra);
        heroSilhouette(canvas, bmp, princessX, foot - f, f, sx, sy, 3, gc, ra);
        blit(canvas, bmp, princessX, foot, f, sx, sy, this.bp);
        canvas.restore();
        // twinkles popping around her
        for (int k = 0; k < 3; k++) {
            float tt = (t + (k * 0.63f)) / 1.9f;
            int cyc = (int) Math.floor(tt);
            float ph = tt - cyc;
            if (ph < 0.6f) {
                float e = (float) Math.sin((ph / 0.6f) * 3.141592653589793d);
                float px = princessX + ((heroHash((cyc * 3) + (k * 101)) - 0.5f) * bw * 1.5f);
                float py = ((this.groundY - (bh * ((heroHash(((cyc * 7) + (k * 53)) + 11) * 0.85f) + 0.2f))) - abs);
                heroSpark(canvas, px, py, f * 1.5f * e, heroMix(gc, -1, 0.5f), (int) (230.0f * e));
            }
        }
        // a heart floats up every few seconds
        float hp = (t % 5.0f) / 1.6f;
        if (hp < 1.0f) {
            float ha = hp < 0.15f ? hp / 0.15f : 1.0f - ((hp - 0.15f) / 0.85f);
            heroHeart(canvas, princessX + (bw * 0.25f) + (((float) Math.sin(hp * 9.0f)) * f * 1.2f), (((this.groundY - bh) - (f * 2.0f)) - ((hp * f) * 12.0f)) - abs, f * 0.75f, gc, (int) (235.0f * ha));
        }
    }

    void drawPrincessBubble(Canvas canvas) {
        int i = this.compSel;
        if (i < 0 || !compUnlocked(i)) {
            return;
        }
        float f2 = this.princessSayT;
        // remember when a new line started, for the pop-in (pure draw-side bookkeeping)
        if (f2 > this.heroBubbleLast + 0.001f) {
            this.heroBubbleBorn = this.time;
        }
        this.heroBubbleLast = f2;
        float f = this.hu * 0.6f;
        float princessX = princessX();
        if (f2 <= 0.0f || this.overlay != 0 || this.bmPrincess == null || this.princessLine == null) {
            return;
        }
        float min = Math.min(1.0f, f2 * 3.0f);
        float age = this.time - this.heroBubbleBorn;
        if (age < 0.0f) {
            age = 1.0f;
        }
        float sc = Math.max(0.05f, heroBackOut(Math.min(1.0f, age / 0.3f))) * ((0.12f * min) + 0.88f);
        this.tp.setTextSize(this.pad * 1.5f);
        this.tp.setFakeBoldText(true);
        float measureText = this.tp.measureText(this.princessLine);
        float f3 = this.pad;
        float f4 = measureText + (2.0f * f3);
        float max = Math.max(f3, princessX - (0.3f * f4));
        float height = this.groundY - (this.bmPrincess.getHeight() * f);
        float f5 = this.pad;
        float f6 = height - (4.2f * f5);
        RectF rectF = this.heroR;
        rectF.set(max, f6, f4 + max, (f5 * 2.8f) + f6);
        float rad = this.pad * IMPACT;
        float tipY = rectF.bottom + (this.pad * IMPACT);
        float tl = princessX - (this.pad * 0.5f);
        float trr = princessX + (this.pad * 0.8f);
        float lw = this.pad * 0.22f;
        canvas.save();
        canvas.translate(0.0f, ((float) Math.sin(this.time * 2.4f)) * this.pad * 0.18f);
        canvas.scale(sc, sc, princessX, tipY);
        // drop shadow (body + tail)
        float so = this.pad * 0.3f;
        this.heroFxP.setColor(-16777216);
        this.heroFxP.setAlpha((int) (70.0f * min));
        rectF.offset(so, so);
        canvas.drawRoundRect(rectF, rad, rad, this.heroFxP);
        rectF.offset(-so, -so);
        this.heroPath.reset();
        this.heroPath.moveTo(tl + so, rectF.bottom + so);
        this.heroPath.lineTo(trr + so, rectF.bottom + so);
        this.heroPath.lineTo(princessX + so, tipY + so);
        this.heroPath.close();
        canvas.drawPath(this.heroPath, this.heroFxP);
        // body + soft lower shade + outline
        this.heroFxP.setColor(-1);
        this.heroFxP.setAlpha((int) (240.0f * min));
        canvas.drawRoundRect(rectF, rad, rad, this.heroFxP);
        this.heroFxP.setColor(heroCompGlow[i % heroCompGlow.length]);
        this.heroFxP.setAlpha((int) (34.0f * min));
        canvas.drawRect(rectF.left + rad, rectF.bottom - (this.pad * 0.7f), rectF.right - rad, rectF.bottom - lw, this.heroFxP);
        this.heroLineP.setStrokeWidth(lw);
        this.heroLineP.setColor(-14017990);
        this.heroLineP.setAlpha((int) (230.0f * min));
        canvas.drawRoundRect(rectF, rad, rad, this.heroLineP);
        // tail: the fill hides the outline where it joins, then its two sides get outlined
        this.heroPath.reset();
        this.heroPath.moveTo(tl, rectF.bottom - lw);
        this.heroPath.lineTo(trr, rectF.bottom - lw);
        this.heroPath.lineTo(princessX, tipY);
        this.heroPath.close();
        this.heroFxP.setColor(-1);
        this.heroFxP.setAlpha((int) (240.0f * min));
        canvas.drawPath(this.heroPath, this.heroFxP);
        this.heroPath.reset();
        this.heroPath.moveTo(tl, rectF.bottom);
        this.heroPath.lineTo(princessX, tipY);
        this.heroPath.lineTo(trr, rectF.bottom);
        canvas.drawPath(this.heroPath, this.heroLineP);
        // heart badge on the corner
        heroHeart(canvas, rectF.right - (this.pad * 0.2f), rectF.top + (this.pad * 0.15f), this.pad * 0.2f * ((((float) Math.sin(this.time * 6.0f)) * 0.12f) + 1.0f), heroCompGlow[i % heroCompGlow.length], (int) (255.0f * min));
        this.tp.setTextAlign(Paint.Align.LEFT);
        this.tp.setColor(-14017990);
        this.tp.setAlpha((int) (min * 255.0f));
        String str = this.princessLine;
        float f8 = this.pad;
        canvas.drawText(str, max + f8, f6 + (f8 * 1.95f), this.tp);
        canvas.restore();
        this.tp.setAlpha(255);
        this.tp.setFakeBoldText(false);
        this.tp.setTextAlign(Paint.Align.CENTER);
    }

    void drawQuests(Canvas var1) {
       this.menuBackdrop(var1);
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
             this.menuRowBar(var1, var22 != 0 ? 1.0F : Integer.bitCount(this.questClaimed & 7) / 3.0F, var22 != 0 ? -8592501 : -8395009, 3, 3);
             long var12 = System.currentTimeMillis();
             var12 = 86400000L - (var12 + (long)TimeZone.getDefault().getOffset(var12)) % 86400000L;
             this.tp.setTextAlign(Align.CENTER);
             this.tp.setTextSize(this.pad * 1.7F);
             this.tp.setColor(-7569238);
             float var3 = this.upT;
             float var2 = this.rowH;
             var2 = var3 + var2 * 4.0F + var2 * 0.4F;
             var1.drawText("Новые задания через " + var12 / 3600000L + "ч " + var12 / 60000L % 60L + "м", (float)this.W / 2.0F, var2, this.tp);
             // weekly event scroll: framed card with gold ornaments and a slow shine
             float menuEp = this.pad;
             this.menuCell.set(menuEp * 1.5F, var2 + menuEp * 0.9F, (float)this.W - menuEp * 1.5F, Math.min((float)this.H - menuEp * 0.3F, var2 + menuEp * 6.0F));
             this.menuCard(var1, this.menuCell, menuEp * 1.2F, -14411203, -11430, false, 7, false);
             this.menuShine(var1, this.menuCell, menuEp * 1.2F, ((this.time * 0.22F) % 2.4F) / 1.0F, 70);
             this.menuDiamond(var1, this.menuCell.left + menuEp * 1.3F, this.menuCell.centerY(), menuEp * 0.35F, menuAlpha(-11430, 210));
             this.menuDiamond(var1, this.menuCell.right - menuEp * 1.3F, this.menuCell.centerY(), menuEp * 0.35F, menuAlpha(-11430, 210));
             float menuSt = this.menuPulse(2.5F, 0.0F);
             this.menuSpark(var1, this.menuCell.right - menuEp * 1.2F, this.menuCell.top + menuEp * 0.75F, Math.max(1.0F, menuEp * 0.2F), -11430, (int)(220.0F * menuSt));
             this.menuSpark(var1, this.menuCell.left + menuEp * 1.2F, this.menuCell.bottom - menuEp * 0.75F, Math.max(1.0F, menuEp * 0.2F), -11430, (int)(220.0F * (1.0F - menuSt)));
             this.tp.setColor(-11430);
             this.tp.setTextSize(this.pad * 1.8F);
             this.tp.setFakeBoldText(true);
             var14 = "Событие недели: " + EVENT_NAME[this.eventIdx()];
             var22 = this.W;
             float var4 = (float)var22 / 2.0F;
             var3 = this.pad;
             this.tp.setShadowLayer(1.0F, 0.0F, var3 * 0.16F, 0xC0000000);
             this.drawFit(var1, var14, var4, 2.6F * var3 + var2, (float)var22 - var3 * 4.0F);
             this.tp.clearShadowLayer();
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
          this.menuRowBar(var1, var6 ? 1.0F : (var30[var8] > 0 ? (float)this.questProg(var5) / (float)var30[var8] : 0.0F), var6 ? -8592501 : (var11 ? -8592501 : var7), 4, var5);
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
       if (this.W > 0 && this.H > 0) {
          this.ovlEnsureBack();
          this.ovlBackP.setShader(this.ovlVigSh);
          this.ovlBackP.setAlpha((int)(var9 * var7 * 1.25F * 255.0F));
          var1.drawRect(0.0F, 0.0F, (float)this.W, (float)this.H, this.ovlBackP);
          this.ovlBackP.setShader(null);
       }

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
          this.ovlRays(var1, var9, var7, this.pad * 22.0F * var8 * var10, 18, this.time * -12.0F, 0.05F, -1, (int)(34.0F * var10));
          this.ovlGlow(var1, var9, var7, this.pad * 18.0F * var8, var17, (int)(120.0F * var10));
          Path var18 = this.ovlPath2;
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

       if (!this.relFly && var13 > 0.75F && var13 < 1.5F) {
          float ovlK = (var13 - 0.75F) / 0.75F;
          float ovlE = 1.0F - (1.0F - ovlK) * (1.0F - ovlK);
          this.ovlShock(var1, var9, var7, this.pad * (4.0F + 20.0F * ovlE), this.pad * 0.9F * (1.0F - ovlK) + 1.0F, var17, (int)(230.0F * (1.0F - ovlK)));
          this.ovlBurst(var1, var9, var7, this.pad * (5.0F + 10.0F * ovlE), this.pad * (8.0F + 16.0F * ovlE), 16, 11.0F, -1, (int)(200.0F * (1.0F - ovlK)), this.pad * 0.35F);
          if (ovlK < 0.2F) {
             this.ovlGlow(var1, var9, var7, this.pad * 14.0F, -1, (int)(220.0F * (1.0F - ovlK / 0.2F)));
          }
       }

       if (this.relFly) {
          for (int ovlT = 1; ovlT <= 5; ovlT++) {
             float ovlQ = Math.max(0.0F, var6 - (float)ovlT * 0.06F);
             float ovlQe = 1.0F - (1.0F - ovlQ) * (1.0F - ovlQ);
             float ovlTx = (float)this.W / 2.0F + (this.relicBtn.centerX() - (float)this.W / 2.0F) * ovlQe;
             float ovlTy = (float)this.H * 0.36F + (this.relicBtn.centerY() - (float)this.H * 0.36F) * ovlQe;
             this.ovlSpark(var1, ovlTx, ovlTy, this.pad * (1.2F - (float)ovlT * 0.17F), var17, (int)((1.0F - var6) * (float)(255 - ovlT * 40)));
          }
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
             float ovlSx = (float)Math.cos(var19) * var6 + var9;
             float ovlSy = (float)Math.sin(var19) * var6 + var7;
             if (var38 % 3 == 0) {
                this.ovlSpark(var1, ovlSx, ovlSy, this.pad * (0.6F + 0.3F * (float)Math.sin((double)(this.time * 6.0F + (float)var38))), -3392, (int)(Math.min(1.0F, var13 * 2.0F) * 230.0F));
             } else {
                var1.drawCircle(ovlSx, ovlSy, this.pad * 0.35F, this.p);
             }
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

          this.tp.setTextSize(this.pad * 3.0F * (1.0F + 0.35F * (1.0F - var6) * (1.0F - var6)));
          this.ovlTitle(var1, var44, (float)this.W / 2.0F, (float)this.H * 0.18F, 0.0F, -26051);
          this.ovlDivider(var1, (float)this.W / 2.0F, (float)this.H * 0.18F + this.pad * 1.8F, (float)this.W * 0.3F * var6, -11430, var39);
          this.tp.setTextSize(this.pad * 2.8F);
          this.tp.setColor(var17);
          this.tp.setAlpha(var39);
          this.ovlTitle(var1, REL_NAME[this.relGetIdx], (float)this.W / 2.0F, (float)this.H * 0.5F, 0.0F, var17);
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
        float ovlA = ovlOpenT(220.0f);
        ovlFrame(canvas, this.pad * 1.4f, this.H * 0.07f, this.W - (this.pad * 1.4f), this.H * 0.925f, -7569238, ovlA);
        this.tp.setTextAlign(Paint.Align.CENTER);
        this.tp.setColor(-1);
        float f = 3.2f;
        this.tp.setTextSize(this.pad * 3.2f);
        this.tp.setFakeBoldText(true);
        float ovlGx = (this.W / 2.0f) - (this.tp.measureText("⚙ Настройки") / 2.0f) + (this.tp.getTextSize() * 0.45f);
        ovlTitle(canvas, "⚙ Настройки", this.W / 2.0f, this.H * 0.13f, 0.0f, -8395009);
        this.tp.setFakeBoldText(false);
        ovlGlow(canvas, ovlGx, (this.H * 0.13f) - (this.pad * 1.1f), this.pad * 2.5f, -8395009, (int) ((Math.sin(this.time * 2.0f) * 15.0d) + 35.0d));
        ovlDivider(canvas, this.W / 2.0f, (this.H * 0.13f) + (this.pad * 1.6f), this.W * 0.25f, -7569238, (int) (ovlA * 255.0f));
        String[] strArr = {"🎵 Музыка", "🔊 Звуки", "📳 Вибрация", "🔢 Цифры урона", "📤 Скопировать код сохранения", "📥 Загрузить сохранение", "🗑 Сбросить прогресс"};
        int i = 4;
        boolean[] zArr = {this.optMusic, this.optSfx, this.optVib, this.optNum};
        float ovlDt = this.ovlSwT < 0.0f ? 1.0f : Math.max(0.0f, Math.min(1.0f, (this.time - this.ovlSwT) * 14.0f));
        if (this.ovlSwT < 0.0f || this.time < this.ovlSwT || this.time - this.ovlSwT > 0.5f) {
            ovlDt = 1.0f;
        }
        this.ovlSwT = this.time;
        for (int ovlK = 0; ovlK < 4; ovlK++) {
            this.ovlSw[ovlK] += ((zArr[ovlK] ? 1.0f : 0.0f) - this.ovlSw[ovlK]) * ovlDt;
        }
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
            int ovlRc = i3 == 6 ? -11920854 : i3 >= i ? -13753520 : -14411203;
            RectF rectF2 = this.setRows[i3];
            float f6 = this.pad;
            if (i3 == 6) {
                ovlGlow(canvas, rectF2.centerX(), rectF2.centerY(), rectF2.width() * 0.55f, -4703670, (int) ((Math.sin(this.time * 2.5f) * 15.0d) + 35.0d));
            }
            panel(canvas, rectF2, f6 * 1.4f, ovlRc);
            this.ovlS.setStyle(Paint.Style.STROKE);
            this.ovlS.setStrokeWidth(Math.max(1.0f, f6 * 0.14f));
            this.ovlS.setColor(i3 == 6 ? -30070 : i3 < i ? (zArr[i3] ? -12673970 : -11911072) : -8395009);
            this.ovlS.setAlpha(i3 == 6 ? (int) ((Math.sin(this.time * 2.5f) * 40.0d) + 120.0d) : 90);
            canvas.drawRoundRect(rectF2, f6 * 1.4f, f6 * 1.4f, this.ovlS);
            if (i3 < i) {
                this.ovlP.setStyle(Paint.Style.FILL);
                this.ovlP.setColor(zArr[i3] ? -12673970 : -11911072);
                this.ovlP.setAlpha(230);
                canvas.drawRect(rectF2.left + (f6 * 0.5f), rectF2.top + (f6 * 1.0f), rectF2.left + (f6 * 0.85f), rectF2.bottom - (f6 * 1.0f), this.ovlP);
            }
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
                ovlSwitch(canvas, this.r, Math.max(0.0f, Math.min(1.0f, this.ovlSw[i3])));
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
            int ovlAc = this.setAsk == 1 ? -8395009 : -30070;
            ovlGlow(canvas, this.r.centerX(), this.r.centerY(), this.r.width() * 0.7f, ovlAc, (int) ((Math.sin(this.time * 2.5f) * 12.0d) + 40.0d));
            ovlFrame(canvas, this.r.left, this.r.top, this.r.right, this.r.bottom, ovlAc, 1.0f);
            if (this.setAsk != 1) {
                float ovlWx = this.W / 2.0f;
                float ovlWy = (this.H * 0.3f) + (this.pad * 2.6f);
                float ovlWs = this.pad * ((((float) Math.sin(this.time * 6.0f)) * 0.08f) + 1.0f);
                this.ovlPath.reset();
                this.ovlPath.moveTo(ovlWx, ovlWy - (ovlWs * 1.3f));
                this.ovlPath.lineTo((ovlWs * 1.4f) + ovlWx, (ovlWs * 1.1f) + ovlWy);
                this.ovlPath.lineTo(ovlWx - (ovlWs * 1.4f), (ovlWs * 1.1f) + ovlWy);
                this.ovlPath.close();
                this.ovlP.setStyle(Paint.Style.FILL);
                this.ovlP.setColor(-30070);
                this.ovlP.setAlpha(230);
                canvas.drawPath(this.ovlPath, this.ovlP);
                this.ovlP.setColor(-15069648);
                canvas.drawRect(ovlWx - (ovlWs * 0.14f), ovlWy - (ovlWs * 0.5f), (ovlWs * 0.14f) + ovlWx, (ovlWs * 0.4f) + ovlWy, this.ovlP);
                canvas.drawRect(ovlWx - (ovlWs * 0.14f), (ovlWs * 0.6f) + ovlWy, (ovlWs * 0.14f) + ovlWx, (ovlWs * 0.85f) + ovlWy, this.ovlP);
            }
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
            ovlButton(canvas, this.askNo, -12963760, false);
            ovlButton(canvas, this.askYes, this.setAsk == 1 ? -12673970 : -4703670, true);
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

       float ovlIn = Math.min(1.0F, var8 * 4.0F);
       float ovlOut = Math.max(0.0F, Math.min(1.0F, (SUPER_END - var8) * 3.0F));
       float ovlE = ovlIn * ovlOut;
       int ovlEc = this.superType == 0 ? -11430 : (this.superType == 1 ? -5213953 : EL_COLOR[(int)(this.time * 6.0F) % 4]);
       this.ovlP.setStyle(Style.FILL);
       for (int ovlK = 0; ovlK < 4; ovlK++) {
          float ovlH = this.pad * 0.35F * (float)(ovlK + 1);
          this.ovlP.setColor(ovlEc);
          this.ovlP.setAlpha((int)(ovlE * (float)(110 - ovlK * 25)));
          var1.drawRect(0.0F, this.battleT, (float)this.W, this.battleT + ovlH, this.ovlP);
          var1.drawRect(0.0F, this.battleB - ovlH, (float)this.W, this.battleB, this.ovlP);
       }

       this.ovlP.setColor(-1);
       this.ovlP.setAlpha((int)(ovlE * 200.0F));
       var1.drawRect(0.0F, this.battleT, (float)this.W, this.battleT + Math.max(1.0F, this.pad * 0.15F), this.ovlP);
       var1.drawRect(0.0F, this.battleB - Math.max(1.0F, this.pad * 0.15F), (float)this.W, this.battleB, this.ovlP);
       if (var16 < 0) {
          float ovlBh = this.battleB - this.battleT;
          for (int ovlK = 0; ovlK < 16; ovlK++) {
             float ovlSp = 1.6F + ovlHash(ovlK, 61) * 1.6F;
             float ovlLen = (float)this.W * (0.12F + ovlHash(ovlK, 62) * 0.22F);
             float ovlX = (float)this.W + ovlLen - (((this.time * ovlSp) + ovlHash(ovlK, 63)) % 1.0F) * ((float)this.W + ovlLen * 2.0F);
             float ovlY = this.battleT + ovlBh * (0.06F + ovlHash(ovlK, 64) * 0.88F);
             float ovlTh = Math.max(1.0F, this.pad * (0.1F + ovlHash(ovlK, 65) * 0.18F));
             this.ovlP.setColor(ovlK % 3 == 0 ? ovlEc : -1);
             this.ovlP.setAlpha((int)(ovlIn * (40.0F + 60.0F * ovlHash(ovlK, 66))));
             var1.drawRect(ovlX, ovlY - ovlTh, ovlX + ovlLen, ovlY + ovlTh, this.ovlP);
          }

          float ovlHx = this.heroX + 13.0F * this.hu;
          float ovlHy = this.groundY - 26.0F * this.hu;
          float ovlCh = Math.min(1.0F, var8 / 1.2F);
          this.ovlGlow(var1, ovlHx, ovlHy, this.pad * (4.0F + 6.0F * ovlCh) * (1.0F + 0.08F * (float)Math.sin((double)(this.time * 30.0F))), ovlEc, (int)(ovlIn * 150.0F));
          this.ovlRays(var1, ovlHx, ovlHy, this.pad * (5.0F + 9.0F * ovlCh), 10, this.time * 220.0F, 0.06F, -1, (int)(ovlIn * 60.0F));
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
                Path var17 = this.ovlPath;
                var17.reset();
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
                PathMeasure var18 = this.ovlPM;
                var18.setPath(var17, false);
                var17 = this.ovlPath2;
                var17.reset();
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

                var84.setStrokeWidth(var6 * var4 * 1.9F);
                int ovlSa = this.p.getAlpha();
                this.p.setAlpha(ovlSa / 3);
                var1.drawPath(var17, this.p);
                this.p.setAlpha(ovlSa);
                var84.setStrokeWidth(var6 * var4);
                var1.drawPath(var17, this.p);
                this.p.setColor(-1);
                this.p.setAlpha((int)(var5 * 255.0F));
                float ovlFade = var5;
                Paint var85 = this.p;
                var5 = this.pad;
                if (var75) {
                   var4 = 1.3F;
                } else {
                   var4 = 0.8F;
                }

                var85.setStrokeWidth(var5 * var4);
                var1.drawPath(var17, this.p);
                if (var18.getPosTan(var18.getLength() * var9, this.ovlTip, null)) {
                   this.ovlSpark(var1, this.ovlTip[0], this.ovlTip[1], this.pad * (var75 ? 2.4F : 1.6F), var75 ? -26051 : -11430, (int)(255.0F * ovlFade));
                }
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
                ovlHolyGlyph[var69],
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
             this.ovlGlow(var1, this.mobX, this.groundY, var5 * 2.6F, -5213953, (int)(160.0F * var4));
             for (int ovlK = 0; ovlK < 10; ovlK++) {
                float ovlPh = (this.time * (0.9F + ovlHash(ovlK, 71)) + ovlHash(ovlK, 72)) % 1.0F;
                float ovlPx = this.mobX + (ovlHash(ovlK, 73) - 0.5F) * var5 * 1.8F;
                float ovlPy = this.groundY - ovlPh * (this.groundY - this.battleT);
                this.ovlSpark(var1, ovlPx, ovlPy, this.pad * 0.7F * (1.0F - ovlPh), -2045697, (int)(220.0F * var4 * (1.0F - ovlPh)));
             }

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

          float ovlTs = (float)Math.sin((double)(this.time * 30.0F)) * this.pad * 0.15F * (1.0F - var4);
          this.tp.setColor(-15331286);
          var4 = (float)this.W / 2.0F;
          var5 = this.pad;
          var1.drawText(var80, var4 + var5 * 0.3F + ovlTs, this.battleT + var5 * 6.3F, this.tp);
          this.tp.setColor(-11430);
          this.tp.setShadowLayer(this.pad * 1.3F, 0.0F, 0.0F, -26051);
          var1.drawText(var80, (float)this.W / 2.0F + ovlTs, this.battleT + this.pad * 6.0F, this.tp);
          this.tp.clearShadowLayer();
          this.tp.setTextSize(this.pad * 2.4F);
          this.tp.setShadowLayer(this.pad * 0.8F, 0.0F, 0.0F, -26051);
          var1.drawText(ovlStarStr[Math.max(0, Math.min(3, this.superStars))], (float)this.W / 2.0F, this.battleT + this.pad * 9.0F, this.tp);
          this.tp.clearShadowLayer();
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

          if (var5 >= 0.0F && var5 < 0.7F) {
             float ovlQ = var5 / 0.7F;
             float ovlQe = 1.0F - (1.0F - ovlQ) * (1.0F - ovlQ);
             int ovlHc = this.superType == 0 ? -11430 : (this.superType == 1 ? -5213953 : EL_COLOR[(int)(var5 * 20.0F) % 4]);
             this.ovlGlow(var1, this.mobX, this.mobY, this.mobR * (2.0F + 2.0F * ovlQe), ovlHc, (int)(200.0F * (1.0F - ovlQ)));
             this.ovlShock(var1, this.mobX, this.mobY, this.mobR * (0.6F + 3.2F * ovlQe), this.pad * 1.2F * (1.0F - ovlQ) + 1.0F, ovlHc, (int)(240.0F * (1.0F - ovlQ)));
             if (ovlQ > 0.15F) {
                float ovlQ2 = (ovlQ - 0.15F) / 0.85F;
                this.ovlShock(var1, this.mobX, this.mobY, this.mobR * (0.4F + 2.2F * ovlQ2), this.pad * 0.6F * (1.0F - ovlQ2) + 1.0F, -1, (int)(180.0F * (1.0F - ovlQ2)));
             }

             this.ovlBurst(var1, this.mobX, this.mobY, this.mobR * (0.8F + 1.6F * ovlQe), this.mobR * (1.4F + 2.8F * ovlQe), 14, 7.0F, ovlHc, (int)(230.0F * (1.0F - ovlQ)), this.pad * 0.5F * (1.0F - ovlQ) + 1.0F);
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
        menuBackdrop(canvas);
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
            RectF rectF2 = this.r;
            float f7 = this.pad;
            menuCard(canvas, rectF2, f7, z ? -11912576 : -14805453, z ? -11430 : -11912576, false, 5, false);
            if (z) {
                // glowing selection underline
                float pu = menuPulse(3.5f, 0.0f);
                this.menuFill.setStyle(Paint.Style.FILL);
                this.menuFill.setColor(menuAlpha(-11430, (int) (60.0f + 60.0f * pu)));
                canvas.drawRect(rectF2.left + (f7 * 1.2f), rectF2.bottom - (f7 * 0.75f), rectF2.right - (f7 * 1.2f), rectF2.bottom - (f7 * 0.2f), this.menuFill);
                this.menuFill.setColor(menuAlpha(-5720, (int) (170.0f + 85.0f * pu)));
                canvas.drawRect(rectF2.left + (f7 * 2.0f), rectF2.bottom - (f7 * 0.6f), rectF2.right - (f7 * 2.0f), rectF2.bottom - (f7 * 0.35f), this.menuFill);
                menuDiamond(canvas, rectF2.centerX(), rectF2.bottom - (f7 * 0.47f), f7 * 0.32f, -11430);
            } else {
                this.menuFill.setStyle(Paint.Style.FILL);
                this.menuFill.setColor(0x40000000);
                canvas.drawRoundRect(rectF2, f7, f7, this.menuFill);
            }
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 1.6f);
            this.tp.setFakeBoldText(z);
            this.tp.setColor(z ? -1 : -7569238);
            String str = strArr[i];
            float centerX = this.r.centerX();
            float centerY = this.r.centerY();
            float f8 = this.pad;
            if (z) {
                this.tp.setShadowLayer(1.0f, 0.0f, f8 * 0.16f, 0xC0000000);
            }
            drawFit(canvas, str, centerX, centerY + (0.55f * f8), f3 - f8);
            this.tp.clearShadowLayer();
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
        float hudD = this.pad;
        // recessed tray behind the tab strip
        this.hudFill.setShader(null);
        this.hudFill.setStyle(Paint.Style.FILL);
        this.hudFill.setColor(-15595243);
        this.hudR.set(hudD * 1.2f, this.tabT - (hudD * 0.3f), this.W - (hudD * 1.2f), this.tabB + (hudD * 0.3f));
        canvas.drawRoundRect(this.hudR, hudD * 1.2f, hudD * 1.2f, this.hudFill);
        this.hudFill.setColor(-16777216);
        this.hudFill.setAlpha(110);
        this.hudR.set(hudD * 1.8f, this.tabT - (hudD * 0.3f), this.W - (hudD * 1.8f), this.tabT - (hudD * 0.05f));
        canvas.drawRect(this.hudR, this.hudFill);
        // sliding active-tab highlight (eases toward the selected tab)
        float hudTarget = (hudD * 1.5f) + (this.tab * f) + (hudD * 0.3f);
        float hudDt = this.hudTabLast < 0.0f ? 1.0f : Math.max(0.0f, Math.min(0.1f, this.time - this.hudTabLast));
        this.hudTabLast = this.time;
        if (this.hudTabX < 0.0f || Math.abs(this.hudTabX - hudTarget) > this.W || hudDt >= 1.0f) {
            this.hudTabX = hudTarget;
        } else {
            this.hudTabX += (hudTarget - this.hudTabX) * Math.min(1.0f, hudDt * 14.0f);
            if (Math.abs(hudTarget - this.hudTabX) < 0.5f) {
                this.hudTabX = hudTarget;
            }
        }
        int i4 = OV_NONE;
        while (i4 < 4) {
            RectF rectF = this.r;
            float f2 = this.pad;
            int i5 = i4 + 1;
            rectF.set((f2 * 1.5f) + (i4 * f) + (f2 * 0.3f), this.tabT, ((1.5f * f2) + (i5 * f)) - (f2 * 0.3f), this.tabB);
            if (this.tab != i4) {
                // inactive: flat, sunken, dimmed
                this.hudFill.setShader(null);
                this.hudFill.setStyle(Paint.Style.FILL);
                this.hudFill.setColor(-14805453);
                RectF rectF2 = this.r;
                float f3 = this.pad;
                canvas.drawRoundRect(rectF2, f3, f3, this.hudFill);
                this.hudFill.setColor(-16777216);
                this.hudFill.setAlpha(80);
                this.hudR.set(this.r.left + (f3 * 0.7f), this.r.top + (f3 * 0.12f), this.r.right - (f3 * 0.7f), this.r.top + (f3 * 0.45f));
                canvas.drawRect(this.hudR, this.hudFill);
                this.hudFill.setColor(-1);
                this.hudFill.setAlpha(14);
                this.hudR.set(this.r.left + (f3 * 0.7f), this.r.bottom - (f3 * 0.3f), this.r.right - (f3 * 0.7f), this.r.bottom - (f3 * 0.1f));
                canvas.drawRect(this.hudR, this.hudFill);
            }
            i4 = i5;
        }
        // active highlight panel at its eased position
        float hudTw = f - (hudD * 0.6f);
        if (hudTw > 0.0f) {
            this.hudBtnR.set(this.hudTabX, this.tabT, this.hudTabX + hudTw, this.tabB);
            hudGlow(canvas, this.hudBtnR, hudD, -7712808, 0.35f);
            hudPanel(canvas, this.hudBtnR, hudD, -11912582);
            // gold underline with glow
            float hudUl = this.hudBtnR.left + (hudD * 1.2f);
            float hudUr = this.hudBtnR.right - (hudD * 1.2f);
            float hudUb = this.hudBtnR.bottom - (hudD * 0.45f);
            float hudUs = (float) ((Math.sin(this.time * 3.0f) * 0.5d) + 0.5d);
            this.hudFill.setShader(null);
            this.hudFill.setStyle(Paint.Style.FILL);
            this.hudFill.setColor(-11430);
            this.hudFill.setAlpha((int) ((hudUs * 40.0f) + 50.0f));
            canvas.drawRect(hudUl - (hudD * 0.3f), hudUb - (hudD * 0.85f), hudUr + (hudD * 0.3f), hudUb + (hudD * 0.2f), this.hudFill);
            this.hudFill.setAlpha(255);
            canvas.drawRect(hudUl, hudUb - (hudD * 0.4f), hudUr, hudUb, this.hudFill);
            this.hudFill.setColor(-1);
            this.hudFill.setAlpha(180);
            canvas.drawRect(hudUl, hudUb - (hudD * 0.4f), hudUr, (hudUb - (hudD * 0.4f)) + Math.max(1.0f, hudD * 0.12f), this.hudFill);
            // pixel notch pointing down into the panel below
            float hudNx = this.hudBtnR.centerX();
            float hudNs = hudD * 0.45f;
            this.hudFill.setColor(-11430);
            this.hudFill.setAlpha(255);
            canvas.drawRect(hudNx - hudNs, this.hudBtnR.bottom - (hudD * 0.05f), hudNx + hudNs, this.hudBtnR.bottom + (hudNs * 0.6f), this.hudFill);
            canvas.drawRect(hudNx - (hudNs * 0.45f), this.hudBtnR.bottom + (hudNs * 0.6f), hudNx + (hudNs * 0.45f), this.hudBtnR.bottom + (hudNs * 1.2f), this.hudFill);
        }
        i4 = OV_NONE;
        while (i4 < 4) {
            RectF rectF = this.r;
            float f2 = this.pad;
            int i5 = i4 + 1;
            rectF.set((f2 * 1.5f) + (i4 * f) + (f2 * 0.3f), this.tabT, ((1.5f * f2) + (i5 * f)) - (f2 * 0.3f), this.tabB);
            boolean hudOn = this.tab == i4;
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 1.8f);
            this.tp.setFakeBoldText(hudOn);
            if (hudOn) {
                hudFitText(canvas, strArr[i4], this.r.centerX(), (this.r.centerY() + (this.pad * 0.65f)) - (this.pad * 0.15f), this.r.width() - this.pad, -1, 255);
            } else {
                this.tp.setColor(-7569238);
                this.tp.setAlpha(210);
                drawFit(canvas, strArr[i4], this.r.centerX(), this.r.centerY() + (this.pad * 0.65f), this.r.width() - this.pad);
                this.tp.setAlpha(255);
            }
            if (i4 == 2 && i3 > 0) {
                float f6 = this.pad;
                float f4 = this.r.right - (f6 * 1.1f);
                float hudBb = Math.abs((float) Math.sin(this.time * 4.0f)) * f6 * 0.35f;
                float f5 = (this.r.top + (f6 * 1.1f)) - hudBb;
                float hudPh = (this.time * 1.1f) % 1.0f;
                this.hudLine.setStyle(Paint.Style.STROKE);
                this.hudLine.setStrokeWidth(Math.max(1.0f, f6 * 0.25f));
                this.hudLine.setColor(-46502);
                this.hudLine.setAlpha((int) ((1.0f - hudPh) * 200.0f));
                canvas.drawCircle(f4, f5, f6 * ((hudPh * 1.3f) + 1.1f), this.hudLine);
                this.hudFill.setShader(null);
                this.hudFill.setStyle(Paint.Style.FILL);
                this.hudFill.setColor(-16777216);
                this.hudFill.setAlpha(100);
                canvas.drawCircle(f4 + (f6 * 0.15f), f5 + (f6 * 0.25f), f6 * 1.1f, this.hudFill);
                this.p.setColor(-46502);
                canvas.drawCircle(f4, f5, f6 * 1.1f, this.p);
                this.p.setColor(-1);
                this.p.setAlpha(150);
                canvas.drawRect(f4 - (f6 * 0.6f), f5 - (f6 * 0.6f), f4 - (f6 * 0.3f), f5 - (f6 * 0.3f), this.p);
                this.p.setAlpha(255);
                this.tp.setColor(-1);
                this.tp.setTextSize(this.pad * 1.3f);
                this.tp.setFakeBoldText(true);
                canvas.drawText(String.valueOf(i3), f4, f5 + (f6 * 0.45f), this.tp);
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
        minisTowerBack(canvas, f, f2, f4);
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
            if (z4) {
                minisHalo(canvas, f, this.r.centerY(), f2 * 0.95f, -24512, (((float) Math.sin(this.time * 5.0f)) * 60.0f) + 140.0f);
            }
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
            minisTowerFloor(canvas, this.r, i6, z3, z4, f2);
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
            // tower keeps rising: ghost floors fading into the night
            minisTowerFade(canvas, f8, f2, f9, f3);
        } else {
            Path path = this.minisPath;
            path.reset();
            float f10 = f2 * 0.6f;
            // flag on the roof peak (drawn first so the roof overlaps the pole base)
            minisFlag(canvas, f8, f9 - (2.4f * f3), f3 * 1.5f, -26051);
            path.reset();
            path.moveTo(f8 - f10, f9 + (this.pad * 0.3f));
            path.lineTo(f8, f9 - (2.4f * f3));
            path.lineTo(f8 + f10, f9 + (this.pad * 0.3f));
            path.close();
            this.p.setColor(-7722422);
            canvas.drawPath(path, this.p);
            // roof shading: lit left slope, tile rows
            path.reset();
            path.moveTo(f8 - f10, f9 + (this.pad * 0.3f));
            path.lineTo(f8, f9 - (2.4f * f3));
            path.lineTo(f8, f9 + (this.pad * 0.3f));
            path.close();
            this.minisP.setStyle(Paint.Style.FILL);
            this.minisP.setColor(-1);
            this.minisP.setAlpha(36);
            canvas.drawPath(path, this.minisP);
            this.minisP.setColor(-16777216);
            this.minisP.setAlpha(50);
            for (int minisK = 1; minisK < 4; minisK++) {
                float minisU = minisK / 4.0f;
                float minisRy = (f9 + (this.pad * 0.3f)) - ((((2.4f * f3) + (this.pad * 0.3f)) * minisU));
                float minisHw = f10 * (1.0f - minisU);
                canvas.drawRect(f8 - minisHw, minisRy - (this.hu * 0.4f), f8 + minisHw, (this.hu * 0.4f) + minisRy, this.minisP);
            }
            float f11 = this.hu * 0.55f;
            this.p.setColor(-5720);
            this.p.setAlpha((int) ((Math.sin(this.time * 3.0f) * 50.0d) + 90.0d));
            canvas.drawCircle(f8, f9 - (0.9f * f3), 9.0f * f11, this.p);
            this.p.setAlpha(255);
            minisHalo(canvas, f8, f9 - (0.9f * f3), 14.0f * f11, -26051, 110.0f);
            i = -4609816;
            if (this.bmPrincess != null) {
                blit(canvas, this.bmPrincess, f8, f9 - (f3 * 0.1f), f11, 1.0f, 1.0f, this.bp);
            }
            // little pixel hearts drifting up from the princess
            for (int minisK = 0; minisK < 4; minisK++) {
                float minisU = ((this.time * 0.4f) + (minisK * 0.25f)) % 1.0f;
                float minisHx = f8 + (((float) Math.sin((this.time * 2.0f) + (minisK * 1.7f))) * f11 * 8.0f);
                float minisHy = (f9 - (f3 * 0.8f)) - (minisU * f3 * 2.5f);
                minisHeart(canvas, minisHx, minisHy, f11 * 0.9f, ((float) Math.sin(minisU * 3.141592653589793d)) * 220.0f);
            }
            this.tp.setTextSize(this.pad * 2.0f);
            canvas.drawText("💗", f8 + (f11 * 10.0f), f9 - (f3 * 1.6f), this.tp);
        }
        float f12 = this.W * 0.71f;
        this.tp.setTextSize(this.pad * 1.7f);
        this.tp.setColor(-7569238);
        canvas.drawText("Этаж " + i3 + (z5 ? "" : " из 20"), f12, this.H * 0.25f, this.tp);
        // boss preview: ominous rays, glow, pedestal shadow and a breathing idle
        float minisBp = (((float) Math.sin(this.time * 2.2f)) * 0.5f) + 0.5f;
        minisRays(canvas, f12, this.H * 0.33f, this.pad * 2.0f, this.W * 0.2f, 10, this.time * 0.3f, 0.35f, -30070, 50.0f + (minisBp * 30.0f));
        minisHalo(canvas, f12, this.H * 0.33f, this.W * 0.15f, -38294, 110.0f + (minisBp * 40.0f));
        this.minisP.setStyle(Paint.Style.FILL);
        this.minisP.setColor(-16777216);
        this.minisP.setAlpha(110);
        this.minisR.set(f12 - (this.W * 0.1f), (this.H * 0.37f) - (this.pad * 0.6f), (this.W * 0.1f) + f12, (this.H * 0.37f) + (this.pad * 0.6f));
        canvas.drawOval(this.minisR, this.minisP);
        float minisBreath = minisBp * 0.04f;
        blit(canvas, this.bmBoss[towerSprite(i3)], f12, this.H * 0.37f, this.hu * IMPACT, 1.0f - (minisBreath * 0.5f), 1.0f + minisBreath, this.bp);
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
        minisGloss(canvas, this.towerClose, f14 * 1.5f, 1.0f);
        this.tp.setColor(-1);
        this.tp.setTextSize(this.pad * 2.2f);
        canvas.drawText("Назад", this.towerClose.centerX(), this.towerClose.centerY() + (this.pad * 0.8f), this.tp);
        RectF rectF3 = this.towerGo;
        int i11 = this.W;
        float f15 = this.pad;
        int i12 = this.H;
        rectF3.set((i11 / 2.0f) + (f15 * 1.5f), i12 * 0.74f, i11 - (f15 * 3.0f), i12 * 0.82f);
        // assault button: pulsing war-glow, gloss and a sweeping glint
        float minisGp = (((float) Math.sin(this.time * 4.0f)) * 0.5f) + 0.5f;
        minisHalo(canvas, this.towerGo.centerX(), this.towerGo.centerY(), (this.towerGo.width() * 0.62f) + (minisGp * this.pad), -4688342, (minisGp * 90.0f) + 90.0f);
        this.p.setColor(-4688342);
        RectF rectF4 = this.towerGo;
        float f16 = this.pad;
        canvas.drawRoundRect(rectF4, f16 * 1.5f, f16 * 1.5f, this.p);
        minisGloss(canvas, this.towerGo, f16 * 1.5f, 1.0f);
        this.minisP.setStyle(Paint.Style.STROKE);
        this.minisP.setStrokeWidth(Math.max(1.0f, f16 * 0.3f));
        this.minisP.setColor(-11430);
        this.minisP.setAlpha(minisA((minisGp * 140.0f) + 90.0f));
        canvas.drawRoundRect(this.towerGo, f16 * 1.5f, f16 * 1.5f, this.minisP);
        this.minisP.setStyle(Paint.Style.FILL);
        float minisSw = ((this.time * 0.5f) % 1.0f) * 1.6f;
        if (minisSw <= 1.0f) {
            float minisSx = this.towerGo.left + (f16 * 1.5f) + ((this.towerGo.width() - (f16 * 3.0f)) * minisSw);
            this.minisP.setColor(-1);
            this.minisP.setAlpha(minisA(((float) Math.sin(minisSw * 3.141592653589793d)) * 90.0f));
            this.minisPath.reset();
            this.minisPath.moveTo(minisSx, this.towerGo.top + (f16 * 0.3f));
            this.minisPath.lineTo((f16 * 1.2f) + minisSx, this.towerGo.top + (f16 * 0.3f));
            this.minisPath.lineTo(minisSx - (f16 * 0.3f), this.towerGo.bottom - (f16 * 0.3f));
            this.minisPath.lineTo(minisSx - (f16 * 1.5f), this.towerGo.bottom - (f16 * 0.3f));
            this.minisPath.close();
            canvas.drawPath(this.minisPath, this.minisP);
        }
        this.tp.setFakeBoldText(true);
        canvas.drawText("⚔ Штурм!", this.towerGo.centerX(), this.towerGo.centerY() + (this.pad * 0.8f), this.tp);
        this.tp.setFakeBoldText(false);
    }

    // ===== gfx(menu): bottom panel lists — cached paints, shaders and helpers =====
    static final int[] menuGlossC = {0x3CFFFFFF, 0x14FFFFFF, 0x00FFFFFF, 0x00000000, 0x4C000000};
    static final float[] menuGlossP = {0f, 0.4f, 0.5f, 0.62f, 1f};
    static final int[] menuShineC = {0x00FFFFFF, 0x50FFFFFF, 0xB4FFFFFF, 0x50FFFFFF, 0x00FFFFFF};
    static final float[] menuShineP = {0f, 0.35f, 0.5f, 0.65f, 1f};
    static final float[] menuGlowP = {0f, 0.45f, 1f};
    final Paint menuFill = new Paint(1);
    final Paint menuLine = new Paint(1);
    final Paint menuSh = new Paint(1);
    final Paint menuTexP = new Paint();
    final RectF menuR = new RectF();
    final RectF menuR2 = new RectF();
    final RectF menuCell = new RectF();
    final RectF menuArc = new RectF();
    final RectF menuRow = new RectF();
    final RectF menuFace = new RectF();
    final Path menuPath = new Path();
    final android.graphics.Matrix menuMtx = new android.graphics.Matrix();
    final Shader[] menuGloss = new Shader[10];
    final float[] menuGlossH = new float[10];
    final Shader[] menuGlowSh = new Shader[48];
    final int[] menuGlowCol = new int[48];
    final float[] menuGlowRad = new float[48];
    Shader menuShineSh;
    Shader menuBgSh;
    int menuBgBiome = -1;
    float menuBgTop = -1f;
    int menuBgH = -1;
    Bitmap menuTexBmp;
    Shader menuTexSh;
    float menuTexKey = -1f;

    static int menuMix(int a, int b, float t) {
        if (!(t > 0f)) {
            t = 0f;
        } else if (t > 1f) {
            t = 1f;
        }
        int aa = a >>> 24, ar = (a >> 16) & 255, ag = (a >> 8) & 255, ab = a & 255;
        int ba = b >>> 24, br = (b >> 16) & 255, bg = (b >> 8) & 255, bb = b & 255;
        int oa = (int) (aa + (ba - aa) * t);
        int or = (int) (ar + (br - ar) * t);
        int og = (int) (ag + (bg - ag) * t);
        int ob = (int) (ab + (bb - ab) * t);
        return (oa << 24) | (or << 16) | (og << 8) | ob;
    }

    static int menuAlpha(int c, int a) {
        if (a < 0) {
            a = 0;
        } else if (a > 255) {
            a = 255;
        }
        return (c & 0xFFFFFF) | (a << 24);
    }

    float menuPulse(float speed, float phase) {
        return 0.5f + 0.5f * (float) Math.sin(this.time * speed + phase);
    }

    Shader menuGlossFor(int slot, float h) {
        if (this.menuGloss[slot] == null || this.menuGlossH[slot] != h) {
            this.menuGloss[slot] = new android.graphics.LinearGradient(0f, 0f, 0f, Math.max(1f, h), menuGlossC, menuGlossP, Shader.TileMode.CLAMP);
            this.menuGlossH[slot] = h;
        }
        return this.menuGloss[slot];
    }

    // Vertical gloss (light top, dark bottom) over a rounded rect; shader cached per slot+height.
    void menuGlossRR(Canvas c, RectF rr, float rad, int slot, int alpha) {
        float h = rr.height();
        if (!(h > 0f) || rr.width() <= 0f) {
            return;
        }
        this.menuSh.setShader(menuGlossFor(slot, h));
        this.menuSh.setAlpha(alpha);
        c.save();
        c.translate(0f, rr.top);
        this.menuR2.set(rr.left, 0f, rr.right, h);
        c.drawRoundRect(this.menuR2, rad, rad, this.menuSh);
        c.restore();
        this.menuSh.setShader(null);
    }

    void menuGlossCircle(Canvas c, float cx, float cy, float rad, int slot, int alpha) {
        if (!(rad > 0f)) {
            return;
        }
        this.menuSh.setShader(menuGlossFor(slot, rad * 2f));
        this.menuSh.setAlpha(alpha);
        c.save();
        c.translate(0f, cy - rad);
        c.drawCircle(cx, rad, rad, this.menuSh);
        c.restore();
        this.menuSh.setShader(null);
    }

    // Soft radial glow of a colour; shader cached per slot (colour + radius key).
    void menuGlowAt(Canvas c, int slot, float cx, float cy, float rad, int color, int alpha) {
        if (!(rad > 0f) || slot < 0 || slot >= this.menuGlowSh.length) {
            return;
        }
        if (this.menuGlowSh[slot] == null || this.menuGlowCol[slot] != color || this.menuGlowRad[slot] != rad) {
            this.menuGlowSh[slot] = new RadialGradient(0f, 0f, rad, new int[]{menuAlpha(color, 210), menuAlpha(color, 80), menuAlpha(color, 0)}, menuGlowP, Shader.TileMode.CLAMP);
            this.menuGlowCol[slot] = color;
            this.menuGlowRad[slot] = rad;
        }
        this.menuSh.setShader(this.menuGlowSh[slot]);
        this.menuSh.setAlpha(alpha);
        c.save();
        c.translate(cx, cy);
        c.drawCircle(0f, 0f, rad, this.menuSh);
        c.restore();
        this.menuSh.setShader(null);
    }

    // Diagonal light band sweeping across a rounded rect; phase 0..1 = left..right, outside = hidden.
    void menuShine(Canvas c, RectF rr, float rad, float phase, int alpha) {
        if (!(phase >= 0f) || phase > 1f) {
            return;
        }
        float h = rr.height();
        float w = rr.width();
        if (h <= 0f || w <= 0f) {
            return;
        }
        float sw = Math.max(this.pad * 2.2f, h * 0.8f);
        if (this.menuShineSh == null) {
            // unit-width band, stretched by the canvas scale below (built once)
            this.menuShineSh = new android.graphics.LinearGradient(0f, 0f, 1f, 0f, menuShineC, menuShineP, Shader.TileMode.CLAMP);
        }
        float k = 0.5f;
        this.menuPath.reset();
        this.menuPath.addRoundRect(rr, rad, rad, Path.Direction.CW);
        c.save();
        c.clipPath(this.menuPath);
        c.translate(rr.left - sw + phase * (w + sw + k * h), rr.top);
        c.skew(-k, 0f);
        c.scale(sw, 1f);
        this.menuSh.setShader(this.menuShineSh);
        this.menuSh.setAlpha(alpha);
        c.drawRect(0f, 0f, 1f, h, this.menuSh);
        this.menuSh.setShader(null);
        c.restore();
    }

    // Pixel "plus" sparkle.
    void menuSpark(Canvas c, float x, float y, float s, int color, int alpha) {
        if (alpha <= 0 || !(s > 0f)) {
            return;
        }
        this.menuFill.setStyle(Style.FILL);
        this.menuFill.setColor(menuAlpha(color, alpha));
        c.drawRect(x - s * 1.5f, y - s * 0.5f, x + s * 1.5f, y + s * 0.5f, this.menuFill);
        c.drawRect(x - s * 0.5f, y - s * 1.5f, x + s * 0.5f, y + s * 1.5f, this.menuFill);
        this.menuFill.setColor(menuAlpha(-1, alpha));
        c.drawRect(x - s * 0.5f, y - s * 0.5f, x + s * 0.5f, y + s * 0.5f, this.menuFill);
    }

    // Small 45-degree diamond ornament.
    void menuDiamond(Canvas c, float x, float y, float s, int color) {
        this.menuFill.setStyle(Style.FILL);
        this.menuFill.setColor(color);
        c.save();
        c.translate(x, y);
        c.rotate(45f);
        c.drawRect(-s, -s, s, s, this.menuFill);
        this.menuFill.setColor(menuAlpha(-1, (color >>> 24) / 2));
        c.drawRect(-s, -s, 0f, 0f, this.menuFill);
        c.restore();
    }

    // Card: drop shadow, optional pulsing glow, tinted base, gloss, accent strip, bevel border.
    void menuCard(Canvas c, RectF rr, float rad, int base, int accent, boolean hot, int slot, boolean strip) {
        if (rr.width() <= 0f || rr.height() <= 0f) {
            return;
        }
        float pd = this.pad;
        this.menuFill.setStyle(Style.FILL);
        this.menuFill.setColor(0x70000000);
        this.menuR.set(rr.left + pd * 0.12f, rr.top + pd * 0.4f, rr.right + pd * 0.12f, rr.bottom + pd * 0.45f);
        c.drawRoundRect(this.menuR, rad, rad, this.menuFill);
        this.menuFill.setColor(0x38000000);
        this.menuR.set(rr.left - pd * 0.1f, rr.top + pd * 0.15f, rr.right + pd * 0.3f, rr.bottom + pd * 0.75f);
        c.drawRoundRect(this.menuR, rad * 1.2f, rad * 1.2f, this.menuFill);
        this.menuLine.setStyle(Style.STROKE);
        if (hot) {
            float pu = menuPulse(5.5f, 0f);
            int glow = menuMix(accent, -1, 0.25f);
            this.menuLine.setStrokeWidth(pd * 0.55f);
            this.menuLine.setColor(menuAlpha(glow, (int) (35f + 45f * pu)));
            this.menuR.set(rr.left - pd * 0.4f, rr.top - pd * 0.4f, rr.right + pd * 0.4f, rr.bottom + pd * 0.4f);
            c.drawRoundRect(this.menuR, rad + pd * 0.4f, rad + pd * 0.4f, this.menuLine);
            this.menuLine.setStrokeWidth(pd * 0.25f);
            this.menuLine.setColor(menuAlpha(glow, (int) (90f + 110f * pu)));
            this.menuR.set(rr.left - pd * 0.14f, rr.top - pd * 0.14f, rr.right + pd * 0.14f, rr.bottom + pd * 0.14f);
            c.drawRoundRect(this.menuR, rad + pd * 0.14f, rad + pd * 0.14f, this.menuLine);
        }
        this.menuFill.setColor(menuMix(base, accent, hot ? 0.16f : 0.09f));
        c.drawRoundRect(rr, rad, rad, this.menuFill);
        menuGlossRR(c, rr, rad, slot, 255);
        if (strip) {
            this.menuPath.reset();
            this.menuPath.addRoundRect(rr, rad, rad, Path.Direction.CW);
            c.save();
            c.clipPath(this.menuPath);
            this.menuFill.setColor(menuAlpha(accent, 200));
            c.drawRect(rr.left, rr.top, rr.left + pd * 0.5f, rr.bottom, this.menuFill);
            this.menuFill.setColor(menuAlpha(menuMix(accent, -1, 0.5f), 150));
            c.drawRect(rr.left, rr.top, rr.left + pd * 0.5f, rr.top + pd * 0.35f, this.menuFill);
            this.menuFill.setColor(0x40000000);
            c.drawRect(rr.left + pd * 0.5f, rr.top, rr.left + pd * 0.7f, rr.bottom, this.menuFill);
            c.restore();
        }
        float sw = Math.max(1f, pd * 0.14f);
        this.menuLine.setStrokeWidth(sw);
        this.menuLine.setColor(hot ? menuMix(accent, -1, 0.35f) : menuAlpha(menuMix(base, -1, 0.22f), 210));
        this.menuR.set(rr.left + sw * 0.5f, rr.top + sw * 0.5f, rr.right - sw * 0.5f, rr.bottom - sw * 0.5f);
        c.drawRoundRect(this.menuR, rad, rad, this.menuLine);
        float hl = Math.max(1f, this.hu * 0.3f);
        this.menuFill.setColor(0x34FFFFFF);
        c.drawRect(rr.left + rad, rr.top + sw + hl * 0.5f, rr.right - rad, rr.top + sw + hl * 1.5f, this.menuFill);
        this.menuFill.setColor(0x30000000);
        c.drawRect(rr.left + rad, rr.bottom - sw - hl * 1.5f, rr.right - rad, rr.bottom - sw - hl * 0.5f, this.menuFill);
        // pixel rivets in the right corners
        float rv = Math.max(1f, pd * 0.28f);
        this.menuFill.setColor(menuAlpha(menuMix(accent, -1, 0.3f), 120));
        c.drawRect(rr.right - rad * 0.9f - rv, rr.top + rad * 0.9f - rv, rr.right - rad * 0.9f, rr.top + rad * 0.9f, this.menuFill);
        c.drawRect(rr.right - rad * 0.9f - rv, rr.bottom - rad * 0.9f, rr.right - rad * 0.9f, rr.bottom - rad * 0.9f + rv, this.menuFill);
    }

    // Raised 3D buy button: shadow, darker lip, glossy face, glow + shine when affordable, gold when maxed.
    void menuButton(Canvas c, RectF b, float rad, int col, boolean on, boolean maxed, int idx) {
        if (b.width() <= 0f || b.height() <= 0f) {
            return;
        }
        float pd = this.pad;
        float lip = pd * 0.32f;
        this.menuFill.setStyle(Style.FILL);
        this.menuFill.setColor(0x80000000);
        this.menuR.set(b.left, b.top + pd * 0.3f, b.right, b.bottom + pd * 0.35f);
        c.drawRoundRect(this.menuR, rad, rad, this.menuFill);
        this.menuLine.setStyle(Style.STROKE);
        if (on) {
            float pu = menuPulse(6f, idx * 0.9f);
            this.menuLine.setStrokeWidth(pd * 0.45f);
            this.menuLine.setColor(menuAlpha(menuMix(col, -1, 0.4f), (int) (40f + 70f * pu)));
            this.menuR.set(b.left - pd * 0.3f, b.top - pd * 0.3f, b.right + pd * 0.3f, b.bottom + pd * 0.3f);
            c.drawRoundRect(this.menuR, rad + pd * 0.3f, rad + pd * 0.3f, this.menuLine);
        }
        this.menuFill.setColor(menuMix(col, 0xFF000000, 0.42f));
        c.drawRoundRect(b, rad, rad, this.menuFill);
        this.menuFace.set(b.left, b.top, b.right, b.bottom - lip);
        RectF face = this.menuFace;
        this.menuFill.setColor(on ? menuMix(col, -1, 0.06f * menuPulse(6f, idx * 0.9f)) : col);
        c.drawRoundRect(face, rad, rad, this.menuFill);
        menuGlossRR(c, face, rad, 1, on ? 255 : 150);
        float sw = Math.max(1f, pd * 0.14f);
        this.menuLine.setStrokeWidth(sw);
        int border = maxed ? menuAlpha(-11430, (int) (150f + 100f * menuPulse(3f, 0f))) : (on ? menuMix(col, -1, 0.5f) : menuMix(col, -1, 0.14f));
        this.menuR.set(b.left + sw * 0.5f, b.top + sw * 0.5f, b.right - sw * 0.5f, b.bottom - sw * 0.5f);
        c.drawRoundRect(this.menuR, rad, rad, this.menuLine);
        float hl = Math.max(1f, this.hu * 0.3f);
        this.menuFill.setColor(on ? 0x55FFFFFF : 0x22FFFFFF);
        c.drawRect(face.left + rad * 0.8f, face.top + sw + hl * 0.4f, face.right - rad * 0.8f, face.top + sw + hl * 1.4f, this.menuFill);
        if (on) {
            float ph = ((this.time * 0.55f + idx * 0.27f) % 1.9f) / 1.1f;
            menuShine(c, face, rad, ph, 230);
        }
        if (maxed) {
            for (int k = 0; k < 3; k++) {
                float tw = (float) Math.sin(this.time * 3.2f + k * 2.1f + idx);
                if (tw > 0.2f) {
                    float sx = k == 0 ? b.left + rad * 0.9f : (k == 1 ? b.right - rad * 0.9f : b.centerX() + b.width() * 0.32f);
                    float sy = k == 2 ? b.bottom - lip - rad * 0.6f : b.top + rad * 0.7f;
                    menuSpark(c, sx, sy, Math.max(1f, pd * 0.22f), -11430, (int) (255f * (tw - 0.2f) / 0.8f));
                }
            }
        }
    }

    // Progress bar with gradient fill, tick marks, a pulsing leading edge and a shine when full.
    void menuBar(Canvas c, float x0, float y0, float x1, float y1, float frac, int color, int ticks, int idx) {
        if (!(x1 > x0) || !(y1 > y0)) {
            return;
        }
        if (!(frac > 0f)) {
            frac = 0f;
        } else if (frac > 1f) {
            frac = 1f;
        }
        float pd = this.pad;
        float rad = (y1 - y0) * 0.5f;
        float sw = Math.max(1f, this.hu * 0.25f);
        this.menuFill.setStyle(Style.FILL);
        this.menuFill.setColor(0x8C000000);
        this.menuR.set(x0 - sw, y0 - sw, x1 + sw, y1 + sw);
        c.drawRoundRect(this.menuR, rad + sw, rad + sw, this.menuFill);
        if (frac > 0f) {
            float fx = x0 + Math.max(rad * 2f, (x1 - x0) * frac);
            if (fx > x1) {
                fx = x1;
            }
            this.menuFill.setColor(menuMix(color, 0xFF000000, 0.25f));
            this.menuR.set(x0, y0, fx, y1);
            c.drawRoundRect(this.menuR, rad, rad, this.menuFill);
            this.menuFill.setColor(color);
            this.menuR.set(x0, y0, fx, y1 - (y1 - y0) * 0.3f);
            c.drawRoundRect(this.menuR, rad, rad, this.menuFill);
            this.menuR.set(x0, y0, fx, y1);
            menuGlossRR(c, this.menuR, rad, 4, 255);
            if (frac < 1f) {
                this.menuFill.setColor(menuAlpha(-1, (int) (90f + 150f * menuPulse(7f, idx))));
                c.drawRect(fx - Math.max(1f, this.hu * 0.4f), y0, fx, y1, this.menuFill);
            } else {
                menuShine(c, this.menuR, rad, ((this.time * 0.45f + idx * 0.31f) % 1.6f) / 1.0f, 255);
            }
        }
        if (ticks > 1) {
            this.menuFill.setColor(0x70000000);
            float tw = Math.max(1f, this.hu * 0.22f);
            for (int t = 1; t < ticks; t++) {
                float tx = x0 + (x1 - x0) * t / ticks;
                c.drawRect(tx - tw * 0.5f, y0, tx + tw * 0.5f, y1, this.menuFill);
            }
        }
        this.menuLine.setStyle(Style.STROKE);
        this.menuLine.setStrokeWidth(sw);
        this.menuLine.setColor(0x38FFFFFF);
        this.menuR.set(x0 - sw * 0.5f, y0 - sw * 0.5f, x1 + sw * 0.5f, y1 + sw * 0.5f);
        c.drawRoundRect(this.menuR, rad + sw * 0.5f, rad + sw * 0.5f, this.menuLine);
    }

    // Bar along the bottom of the last rowBox card (menuRow holds the card, this.btn the buy button).
    void menuRowBar(Canvas c, float frac, int color, int ticks, int idx) {
        float pd = this.pad;
        float cy = this.menuRow.centerY();
        float y1 = this.menuRow.bottom - pd * 0.55f;
        float y0 = y1 - pd * 0.42f;
        if (y0 < cy + pd * 2.25f) {
            return;
        }
        menuBar(c, pd * 8.5f, y0, this.btn.left - pd * 1.4f, y1, frac, menuMix(color, -1, 0.15f), ticks, idx);
    }

    // Panel backdrop: biome-tinted gradient, pixel brick texture, ornament divider and drifting motes.
    void menuBackdrop(Canvas c) {
        if (this.W <= 0 || this.H <= 0) {
            return;
        }
        float pd = this.pad;
        float top = this.upT - pd * 0.25f;
        float bot = this.H;
        if (!(bot > top)) {
            return;
        }
        int b = biome();
        if (this.menuBgSh == null || this.menuBgBiome != b || this.menuBgTop != top || this.menuBgH != this.H) {
            int tint = b == 0 ? 0xFF1C3A38 : (b == 1 ? 0xFF3E2C1A : 0xFF28224E);
            this.menuBgSh = new android.graphics.LinearGradient(0f, top, 0f, bot,
                new int[]{menuMix(-15594462, tint, 0.55f), menuMix(-15594462, tint, 0.18f), menuMix(-15594462, 0xFF000000, 0.45f)},
                new float[]{0f, 0.3f, 1f}, Shader.TileMode.CLAMP);
            this.menuBgBiome = b;
            this.menuBgTop = top;
            this.menuBgH = this.H;
        }
        this.menuSh.setShader(this.menuBgSh);
        this.menuSh.setAlpha(255);
        c.drawRect(0f, top, this.W, bot, this.menuSh);
        this.menuSh.setShader(null);
        float hs = Math.max(1f, this.hu);
        if (this.menuTexSh == null || this.menuTexKey != hs) {
            if (this.menuTexBmp == null) {
                int[] px = new int[64];
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        int v = 0;
                        if (y == 0 || y == 4) {
                            v = 0x26000000;
                        } else if ((y < 4 && x == 0) || (y > 4 && x == 4)) {
                            v = 0x22000000;
                        } else if (y == 1 || y == 5) {
                            v = 0x0CFFFFFF;
                        } else if (((x * 5 + y * 3) % 11) == 0) {
                            v = 0x0AFFFFFF;
                        }
                        px[y * 8 + x] = v;
                    }
                }
                this.menuTexBmp = Bitmap.createBitmap(px, 8, 8, Bitmap.Config.ARGB_8888);
            }
            android.graphics.BitmapShader bs = new android.graphics.BitmapShader(this.menuTexBmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            this.menuMtx.setScale(hs, hs);
            bs.setLocalMatrix(this.menuMtx);
            this.menuTexSh = bs;
            this.menuTexKey = hs;
        }
        this.menuTexP.setShader(this.menuTexSh);
        this.menuTexP.setFilterBitmap(false);
        c.drawRect(0f, top, this.W, bot, this.menuTexP);
        // stepped-fade gold divider under the tabs
        float ly = this.upT - pd * 0.45f;
        float lh = Math.max(1f, pd * 0.16f);
        float cx = this.W * 0.5f;
        int gold = -11430;
        this.menuFill.setStyle(Style.FILL);
        this.menuFill.setColor(menuAlpha(gold, 34));
        c.drawRect(cx - this.W * 0.46f, ly, cx + this.W * 0.46f, ly + lh, this.menuFill);
        this.menuFill.setColor(menuAlpha(gold, 60));
        c.drawRect(cx - this.W * 0.33f, ly, cx + this.W * 0.33f, ly + lh, this.menuFill);
        this.menuFill.setColor(menuAlpha(gold, 105));
        c.drawRect(cx - this.W * 0.18f, ly, cx + this.W * 0.18f, ly + lh, this.menuFill);
        menuDiamond(c, cx, ly + lh * 0.5f, pd * 0.42f, menuAlpha(gold, 200));
        menuDiamond(c, cx - this.W * 0.2f, ly + lh * 0.5f, pd * 0.2f, menuAlpha(gold, 120));
        menuDiamond(c, cx + this.W * 0.2f, ly + lh * 0.5f, pd * 0.2f, menuAlpha(gold, 120));
        // drifting motes (biome tinted)
        int mote = b == 0 ? 0xFFB8FFC8 : (b == 1 ? 0xFFFFD890 : 0xFFC8B8FF);
        float span = bot - top;
        for (int k = 0; k < 12; k++) {
            float sp = 0.03f + 0.025f * (((k * 37) % 10) / 10f);
            float ph = (this.time * sp + k * 0.137f) % 1f;
            if (ph < 0f) {
                ph += 1f;
            }
            float my = bot - ph * span;
            float mx = ((k * 0.618034f + 0.11f) % 1f) * this.W + (float) Math.sin(this.time * 0.8f + k * 1.7f) * pd * 0.9f;
            int a = (int) (85f * Math.sin(ph * Math.PI));
            float ms = hs * (0.45f + (k % 3) * 0.2f);
            this.menuFill.setColor(menuAlpha(mote, a));
            c.drawRect(mx, my, mx + ms, my + ms, this.menuFill);
        }
    }

    void drawUpgrades(Canvas canvas) {
        menuBackdrop(canvas);
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
            // progress to the next x2 milestone (every 25 levels)
            menuRowBar(canvas, (this.lvl[i] % 25) / 25.0f, i3, 5, i);
            i++;
        }
    }

    void drawVignette(Canvas canvas) {
        float T = this.battleT;
        float B = this.battleB;
        if (this.W <= 0 || this.H <= 0 || !(B > T)) return;
        int bi = biome();
        float W = this.W;
        if (this.bgVKeyBi != bi || this.bgVKeyW != W || this.bgVKeyT != T || this.bgVKeyB != B) {
            this.bgVKeyBi = bi;
            this.bgVKeyW = W;
            this.bgVKeyT = T;
            this.bgVKeyB = B;
            float cx = W / 2f;
            float cy = (T + B) / 2f;
            float rad = Math.max(W, B - T) * 0.78f;
            Shader.TileMode cl = Shader.TileMode.CLAMP;
            int ec = bgEdgeCol[bi];
            this.bgVigP.setShader(new RadialGradient(cx, cy, rad,
                    new int[]{bgA(ec, 0), bgA(ec, 0), bgA(ec, 0x48), bgA(ec, 0xA8)},
                    new float[]{0f, 0.5f, 0.8f, 1f}, cl));
            float eh = Math.max(1f, Math.min(this.pad * 4f, (B - T) * 0.25f));
            this.bgTopP.setShader(new android.graphics.LinearGradient(0f, T, 0f, T + eh,
                    new int[]{0x66000000, 0x1A000000, 0}, new float[]{0f, 0.35f, 1f}, cl));
            this.bgBotP.setShader(new android.graphics.LinearGradient(0f, B - eh, 0f, B,
                    0, 0x55000000, cl));
            this.bgFzP.setShader(new RadialGradient(cx, cy, rad,
                    new int[]{0x00FFC040, 0x00FFC040, 0x80FFB030, 0xE8FF5020},
                    new float[]{0f, 0.42f, 0.74f, 1f}, cl));
            this.bgBossP.setShader(new RadialGradient(cx, cy, rad,
                    new int[]{0x00600010, 0x00600010, 0x80600010, 0xF02A0006},
                    new float[]{0f, 0.42f, 0.8f, 1f}, cl));
        }
        float eh = Math.max(1f, Math.min(this.pad * 4f, (B - T) * 0.25f));
        canvas.drawRect(0f, T, W, B, this.bgVigP);
        canvas.drawRect(0f, T, W, T + eh, this.bgTopP);
        canvas.drawRect(0f, B - eh, W, B, this.bgBotP);
        float t = this.time;
        if (this.bgFz > 0.01f) {
            float pul = 0.55f + 0.25f * bgSin(t * 7f) + 0.2f * this.beatPulse;
            this.bgFzP.setAlpha(bgAl(255f * this.bgFz * pul));
            canvas.drawRect(0f, T, W, B, this.bgFzP);
        }
        if (this.bgBossA > 0.01f) {
            this.bgBossP.setAlpha(bgAl(255f * this.bgBossA * (0.45f + 0.55f * this.bgBeat)));
            canvas.drawRect(0f, T, W, B, this.bgBossP);
        }
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
       this.wsBest = var12.getInt("wsBest", 0);
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
            // decaying multi-frequency sine shake: punchy start, smooth settle
            float fxAmp = ((this.shake * this.shake * 0.8f) + (this.shake * 0.35f)) * this.pad;
            float fxSt = this.time;
            canvas.translate(fxAmp * ((((float) Math.sin(fxSt * 53.0f)) * 0.75f) + (((float) Math.sin((fxSt * 97.0f) + 1.3f)) * 0.35f)), fxAmp * ((((float) Math.cos(fxSt * 47.0f)) * 0.7f) + (((float) Math.sin((fxSt * 83.0f) + 0.7f)) * 0.3f)));
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
            fxDrawBanner(canvas, f);
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
            if (z && weakTap(x, y)) {
                return true;
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
        this.pausedAt = System.currentTimeMillis();
        save();
    }

    void grantOffline(long j) {
        double d = Math.min((System.currentTimeMillis() - j) / 1000.0d, 28800.0d);
        if (d <= 60.0d) {
            return;
        }
        double autoDps = autoDps() * d / hpFor(this.stage) * goldFor(this.stage) * offlineRate();
        double manaSec = manaSec() * d * offlineRate();
        if (autoDps + manaSec > 0.0d) {
            this.gold += autoDps;
            this.mana += manaSec;
            this.lifetimeGold += autoDps;
            showBanner("Пока тебя не было: +" + fmt(autoDps) + " \ud83e\ude99  +" + fmt(manaSec) + " \ud83d\udca7", -5720);
        }
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
        long j = this.pausedAt;
        this.pausedAt = 0L;
        if (j > 0) {
            grantOffline(j);
            checkDay();
        }
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
       int menuIdx = var2;
       int menuSlot = Math.max(0, Math.min(3, this.tab)) * 12 + Math.max(0, Math.min(11, var2));
       float var15 = this.upT;
       float var13 = (float)var2;
       float var14 = this.rowH;
       var13 = var15 + var13 * var14;
       var15 = this.pad;
       var14 = var14 + var13 - 0.7F * var15;
       this.r.set(var15 * 1.5F, var13, (float)this.W - var15 * 1.5F, var14);
       this.menuRow.set(this.r);
       RectF var20 = this.r;
       var15 = this.pad;
       if (var3) {
          var2 = -12964514;
       } else {
          var2 = -14411203;
       }

       this.menuCard(var1, var20, var15 * 1.2F, var2, var4, var3, 0, true);
       float var16 = this.pad * 5.0F;
       var15 = (var13 + var14) / 2.0F;
       if (this.rowIcon != null) {
          float var17 = this.rowH * 0.3F;
          // medallion: soft glow, rotating rays when affordable, dark disc and ring
          float menuPu = this.menuPulse(3.0F, menuIdx * 1.3F);
          this.menuGlowAt(var1, menuSlot, var16, var15, var17 * 1.55F, var4, var9 ? (int)(150.0F + 90.0F * menuPu) : 110);
          if (var9) {
             this.menuFill.setStyle(Style.FILL);
             this.menuFill.setColor(menuAlpha(menuMix(var4, -1, 0.45F), 46));
             float menuRr = var17 * 1.5F;
             this.menuArc.set(var16 - menuRr, var15 - menuRr, var16 + menuRr, var15 + menuRr);
             for (int menuK = 0; menuK < 6; menuK++) {
                var1.drawArc(this.menuArc, this.time * 28.0F + menuK * 60.0F + menuIdx * 17.0F, 13.0F, true, this.menuFill);
             }
          }
          this.menuFill.setStyle(Style.FILL);
          this.menuFill.setColor(menuAlpha(menuMix(var4, 0xFF000000, 0.62F), 190));
          var1.drawCircle(var16, var15, var17 * 0.95F, this.menuFill);
          this.menuGlossCircle(var1, var16, var15, var17 * 0.95F, 3, 150);
          this.menuLine.setStyle(Style.STROKE);
          this.menuLine.setStrokeWidth(Math.max(1.0F, this.pad * 0.16F));
          this.menuLine.setColor(menuAlpha(menuMix(var4, -1, 0.2F), var9 ? 230 : 150));
          var1.drawCircle(var16, var15, var17 * 0.95F, this.menuLine);
          float var18 = 1.75F * var17 / (float)this.rowIcon.getWidth();
          Bitmap var53 = this.rowIcon;
          float menuBob = var9 ? (float)Math.sin(this.time * 3.2F + menuIdx * 0.8F) * this.pad * 0.2F : 0.0F;
          // tiny ground shadow under the icon
          this.menuFill.setColor(0x50000000);
          this.menuArc.set(var16 - var17 * 0.55F, var15 + var17 * 0.48F, var16 + var17 * 0.55F, var15 + var17 * 0.68F);
          var1.drawOval(this.menuArc, this.menuFill);
          this.blit(var1, var53, var16, (float)var53.getHeight() * var18 / 2.0F + var15 - 0.12F * var17 + menuBob, var18, 1.0F, 1.0F, this.bp);
          this.tp.setTypeface(this.pixTf);
          this.tp.setFakeBoldText(false);
          this.tp.setTextSize(this.pad * 1.05F);
          var18 = Math.max(this.pad * 2.2F, this.tp.measureText(var5) + this.pad * 0.9F);
          var20 = this.pr;
          var18 /= 2.0F;
          var17 = var15 + 0.55F * var17;
          var20.set(var16 - var18, var17, var18 + var16, this.pad * 1.7F + var17);
          // level badge: shadowed glossy pill with coloured rim
          this.menuFill.setColor(0x80000000);
          this.menuArc.set(this.pr.left, this.pr.top + this.pad * 0.25F, this.pr.right, this.pr.bottom + this.pad * 0.25F);
          var1.drawRoundRect(this.menuArc, this.pad * 0.6F, this.pad * 0.6F, this.menuFill);
          this.menuFill.setColor(menuMix(-15331286, var4, 0.22F));
          var1.drawRoundRect(this.pr, this.pad * 0.6F, this.pad * 0.6F, this.menuFill);
          this.menuGlossRR(var1, this.pr, this.pad * 0.6F, 2, 220);
          this.menuLine.setStrokeWidth(this.pad * 0.18F);
          this.menuLine.setColor(var9 ? menuMix(var4, -1, 0.3F) : var4);
          var1.drawRoundRect(this.pr, this.pad * 0.6F, this.pad * 0.6F, this.menuLine);
          this.tp.setTextAlign(Align.CENTER);
          this.tp.setColor(-1);
          this.tp.setShadowLayer(1.0F, 0.0F, this.pad * 0.14F, 0xE0000000);
          var1.drawText(var5, this.pr.centerX(), this.pr.centerY() + this.pad * 0.5F, this.tp);
          this.tp.clearShadowLayer();
          this.tp.setTypeface(Typeface.DEFAULT);
          this.tp.setFakeBoldText(true);
          this.rowIcon = null;
       } else {
          float menuRc = this.rowH * 0.28F;
          boolean menuLive = var9 || var3;
          this.menuGlowAt(var1, menuSlot, var16, var15, menuRc * 1.6F, var4, menuLive ? (int)(140.0F + 100.0F * this.menuPulse(4.0F, menuIdx)) : 90);
          if (menuLive) {
             // radar ping ring
             float menuPh = (this.time * 0.9F + menuIdx * 0.33F) % 1.0F;
             this.menuLine.setStyle(Style.STROKE);
             this.menuLine.setStrokeWidth(Math.max(1.0F, this.pad * 0.2F));
             this.menuLine.setColor(menuAlpha(menuMix(var4, -1, 0.4F), (int)(200.0F * (1.0F - menuPh))));
             var1.drawCircle(var16, var15, menuRc * (1.0F + 0.55F * menuPh), this.menuLine);
          }
          this.menuFill.setStyle(Style.FILL);
          this.menuFill.setColor(0x60000000);
          var1.drawCircle(var16, var15 + this.pad * 0.3F, menuRc, this.menuFill);
          this.menuFill.setColor(menuMix(var4, 0xFF000000, 0.3F));
          var1.drawCircle(var16, var15, menuRc, this.menuFill);
          this.menuFill.setColor(var4);
          var1.drawCircle(var16, var15 - menuRc * 0.08F, menuRc * 0.9F, this.menuFill);
          this.menuGlossCircle(var1, var16, var15, menuRc, 8, 255);
          this.menuLine.setStyle(Style.STROKE);
          this.menuLine.setStrokeWidth(Math.max(1.0F, this.pad * 0.16F));
          this.menuLine.setColor(menuAlpha(menuMix(var4, -1, 0.55F), 200));
          var1.drawCircle(var16, var15, menuRc, this.menuLine);
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
       this.tp.setShadowLayer(1.0F, 0.0F, var16 * 0.16F, 0xC0000000);
       this.drawFit(var1, var6, var16 * 8.5F, var15 - var16 * 0.3F, (float)this.W * 0.5F);
       this.tp.clearShadowLayer();
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

       this.menuButton(var1, var27, var13, var10, var9, "МАКС".equals(var8), menuIdx);
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
       } else if ("МАКС".equals(var8)) {
          var2 = -11430;
       } else {
          var2 = -7569238;
       }

       var29.setColor(var2);
       this.tp.setTextSize(this.pad * 1.8F);
       this.tp.setFakeBoldText(true);
       if (var9) {
          this.tp.setShadowLayer(1.0F, 0.0F, this.pad * 0.16F, 0xB0000000);
       }
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

       this.tp.clearShadowLayer();
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
        edit.putInt("wsBest", this.wsBest);
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
        if (mobBitmap == null) {
            return;
        }
        boolean boss = bossFight();
        float f = this.mu * (boss ? 1.3f : 1.0f);
        if (mobBitmap.getWidth() > 24) {
            f *= 0.62f;
        }
        // debris starts exactly where the sprite was last drawn (incl. hop, recoil, squash)
        float f2;
        float cellW;
        float cellH;
        float bx;
        float bb;
        if (this.monDrawnBmp == mobBitmap && this.monDrawnS > 0.0f) {
            f2 = this.monDrawnS;
            cellW = f2 * this.monDrawnSx;
            cellH = f2 * this.monDrawnSy;
            bx = this.monDrawnX;
            bb = this.monDrawnB;
        } else {
            f2 = f * monSpawnK();
            cellW = f2;
            cellH = f2;
            bx = this.mobX;
            bb = this.groundY + f2;
        }
        float width = bx - ((mobBitmap.getWidth() * cellW) / 2.0f);
        float height = bb - (mobBitmap.getHeight() * cellH);
        int i = mobBitmap.getWidth() > 24 ? 3 : 2;
        float f3 = this.W / 1080.0f;
        float boost = boss ? 1.15f : 1.0f;
        for (int i2 = OV_NONE; i2 < mobBitmap.getHeight(); i2 += i) {
            for (int i3 = OV_NONE; i3 < mobBitmap.getWidth(); i3 += i) {
                int pixel = mobBitmap.getPixel(i3, i2);
                if ((pixel >>> 24) >= 128) {
                    Fx fx = new Fx();
                    float f4 = i;
                    float f5 = f4 / 2.0f;
                    fx.x = ((i3 + f5) * cellW) + width;
                    fx.y = ((i2 + f5) * cellH) + height;
                    fx.vx = (((fx.x - this.mobX) * 4.0f) + ((this.rnd.nextFloat() - 0.5f) * 420.0f * f3)) * boost + (90.0f * f3);
                    fx.vy = (((fx.y - this.mobY) * 2.5f) - (((this.rnd.nextFloat() * 520.0f) + 380.0f) * f3)) * boost;
                    fx.size = f4 * f2 * (0.8f + (this.monRnd.nextFloat() * 0.45f));
                    float shade = this.monRnd.nextFloat();
                    fx.color = shade < 0.22f ? monShade(pixel, 1.35f) : shade < 0.38f ? monShade(pixel, 0.7f) : pixel;
                    fx.max = (this.rnd.nextFloat() * 0.6f) + 0.9f;
                    this.chunks.add(fx);
                }
            }
        }
        // hot sparks flying out of the core
        int mc = mobColor();
        float cxm = bx;
        float cym = bb - (mobBitmap.getHeight() * cellH * 0.5f);
        int n = boss ? 28 : 12;
        for (int s = OV_NONE; s < n; s++) {
            Fx fx = new Fx();
            double a = this.monRnd.nextDouble() * Math.PI * 2.0d;
            float ca = (float) Math.cos(a);
            float sa = (float) Math.sin(a);
            float sp = ((this.monRnd.nextFloat() * 900.0f) + 450.0f) * f3 * boost;
            fx.x = cxm + (ca * this.mobR * 0.2f);
            fx.y = cym + (sa * this.mobR * 0.2f);
            fx.vx = ca * sp;
            fx.vy = (sa * sp) - (260.0f * f3);
            fx.size = f2 * (0.45f + (this.monRnd.nextFloat() * 0.4f));
            int m3 = s % 3;
            fx.color = m3 == 0 ? -1 : m3 == 1 ? 0xFFFFF0A0 : monShade(mc, 1.35f);
            fx.max = (this.monRnd.nextFloat() * 0.35f) + 0.35f;
            this.chunks.add(fx);
        }
        while (this.chunks.size() > 450) {
            this.chunks.remove(OV_NONE);
        }
        this.monDeadBmp = mobBitmap;
        this.monDeadX = bx;
        this.monDeadB = bb;
        this.monDeadS = f2;
        this.monDeadSx = f2 > 0.0f ? cellW / f2 : 1.0f;
        this.monDeadSy = f2 > 0.0f ? cellH / f2 : 1.0f;
        this.monDeadAt = SystemClock.uptimeMillis();
        this.monDeadCol = mc;
        this.monDeadBoss = boss;
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

    void drawWeakSpot(Canvas canvas) {
        long now = SystemClock.uptimeMillis();
        if (this.overlay != 0 || this.towerFight || this.mobR <= 0.0f) {
            this.wsUntil = 0L;
            this.wsNext = Math.max(this.wsNext, now + 3000);
            return;
        }
        if (this.wsUntil == 0L) {
            if (this.wsNext == 0L) {
                this.wsNext = now + 6000;
            }
            if (now < this.wsNext) {
                return;
            }
            double a = this.rnd.nextDouble() * Math.PI * 2.0d;
            float r = this.mobR * (0.15f + (this.rnd.nextFloat() * 0.4f));
            this.wsX = (float) Math.cos(a) * r;
            this.wsY = (float) Math.sin(a) * r;
            this.wsLife = Math.max(900L, 1800L - (this.wsStreak * 90L));
            this.wsUntil = now + this.wsLife;
            if (this.wsBest == 0 && this.wsStreak == 0) {
                showBanner("🎯 Слабое место! Успей тапнуть по мишени", -10496);
            }
            return;
        }
        if (now > this.wsUntil) {
            if (this.wsStreak >= 3) {
                showBanner("Серия слабых мест прервана: " + this.wsStreak, -30070);
            }
            this.wsStreak = 0;
            this.wsUntil = 0L;
            this.wsNext = now + 4000 + this.rnd.nextInt(4000);
            return;
        }
        float k = (this.wsUntil - now) / (float) this.wsLife;
        float cx = this.mobX + this.wsX;
        float cy = this.mobY + this.wsY;
        float base = this.pad * 2.2f;
        float pulse = (float) Math.sin(this.time * 14.0f) * 0.15f + 1.0f;
        monDrawWeak(canvas, cx, cy, base, pulse, k, now);
        if (this.wsStreak > 1) {
            if (this.monWsStrN != this.wsStreak) {
                this.monWsStrN = this.wsStreak;
                this.monWsStr = "x" + this.wsStreak;
            }
            this.tp.setTextAlign(Paint.Align.CENTER);
            this.tp.setTextSize(this.pad * 2.0f);
            float ty = cy - (base * 2.75f);
            this.tp.setColor(0xCC000000);
            canvas.drawText(this.monWsStr, cx + (this.pad * 0.22f), ty + (this.pad * 0.22f), this.tp);
            this.tp.setColor(-10496);
            canvas.drawText(this.monWsStr, cx, ty, this.tp);
        }
    }

    boolean weakTap(float f, float f2) {
        if (this.wsUntil == 0L || this.overlay != 0) {
            return false;
        }
        float cx = this.mobX + this.wsX;
        float cy = this.mobY + this.wsY;
        if (Math.hypot(f - cx, f2 - cy) > this.pad * 5.0f) {
            return false;
        }
        long now = SystemClock.uptimeMillis();
        this.wsStreak++;
        this.taps++;
        double d = tapDmg() * comboMult() * 10.0d * ((Math.min(this.wsStreak - 1, 8) * 0.25d) + 1.0d);
        this.mana += manaTap() * 5.0d;
        addFloat(cx, cy - this.pad * 2.0f, "СЛАБОЕ МЕСТО! " + fmt(d), -10496, this.pad * 2.8f);
        burstAt(cx, cy, 14, -10496);
        this.heroCast = 1.0f;
        this.sfx.play(1, 0.8f);
        this.sfx.buzz(40L);
        if (this.wsStreak % 5 == 0) {
            this.frenzyUntil = Math.max(this.frenzyUntil, now) + 5000;
            showBanner("🎯 Серия " + this.wsStreak + "! Ярость +5с", -10496);
        }
        if (this.wsStreak > this.wsBest) {
            this.wsBest = this.wsStreak;
            if (this.wsBest >= 5) {
                showBanner("🎯 Рекорд серии слабых мест: " + this.wsBest, -10496);
            }
        }
        this.wsUntil = 0L;
        this.wsNext = now + 1500 + this.rnd.nextInt(2500);
        damage(d, true);
        return true;
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
        return Math.pow(1.15d, (i * 5) + 4) * 26.0d * 8.0d * 1.5d;
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
