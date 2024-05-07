# 30833資訊專案開發紀錄 - Games2in1 Android 應用程式
## 專案概述
>Games2in1是一個結合了兩種經典遊戲的Android應用程式:猜字遊戲和數字遊戲。這個應用程式提供了一個友善的介面,讓使用者可以在同一個應用程式中無縫地玩這兩種遊戲。
功能
>
>> ### 猜字遊戲
>>
>>使用者可以自行輸入一個字作為猜字遊戲的答案字,或是讓應用程式隨機挑選一個字。
遊戲會顯示答案字的長度,然後使用者就可以開始一個一個猜字母。
如果猜錯字母,就會在經典的吊人畫面上開始畫一筆。
遊戲會在使用者猜對答案字或用盡可用機會時結束。
>
>
>>### 數字遊戲
>>
>>使用者可以輸入一個3到6個不重複數字的數字作為答案,或是讓應用程式隨機產生一個數字。
遊戲會給予一個數字鍵盤供使用者進行猜測。
每一次猜測後,遊戲會回饋確實的數字個數(Bulls)和位置錯誤的數字個數(Cows)。
當使用者猜對答案時,遊戲就會結束。



## 專案結構
>這個專案遵循標準的Android專案結構,組織方式如下:
>
>```
>>Games2in1/
>>├── app/
>>│   ├── src/
>>│   │   ├── androidTest/
>>│   │   │   └── java/
>>│   │   │       └── com/example/myapplication/
>>│   │   │           └── ExampleInstrumentedTest.kt
>>│   │   ├── main/
>>│   │   │   ├── assets/
>>│   │   │   │   └── words.txt
>>│   │   │   ├── java/
>>│   │   │   │   └── com/example/myapplication/
>>│   │   │   │       ├── MainActivity.kt
>>│   │   │   │       ├── HangmanGameScreen.kt
>>│   │   │   │       ├── BullsAndCowsGameScreen.kt
>>│   │   │   │       ├── ui/
>>│   │   │   │       │   ├── theme/
>>│   │   │   │       │   │   ├── Color.kt
>>│   │   │   │       │   │   ├── Theme.kt
>>│   │   │   │       │   │   └── Type.kt
>>│   │   │   │       └── Extension1.kt
>>│   │   │   └── res/
>>│   │   │       ├── drawable/
>>│   │   │       │   ├── hangman_0.png
>>│   │   │       │   ├── hangman_1.png
>>│   │   │       │   ├── hangman_2.png
>>│   │   │       │   ├── hangman_3.png
>>│   │   │       │   ├── hangman_4.png
>>│   │   │       │   ├── hangman_5.png
>>│   │   │       │   └── hangman_6.png
>>│   │   │       ├── font/
>>│   │   │       │   └── butterfont.otf
>>│   │   │       ├── layout/
>>│   │   │       │   ├── activity_main.xml
>>│   │   │       │   ├── bulls_and_cows_game_screen.xml
>>│   │   │       │   └── hangman_game_screen.xml
>>│   │   │       ├── mipmap/
>>│   │   │       │   ├── ic_launcher.webp
>>│   │   │       │   └── ic_launcher_round.webp
>>│   │   │       ├── values/
>>│   │   │       │   ├── colors.xml
>>│   │   │       │   ├── strings.xml
>>│   │   │       │   └── themes.xml
>>│   │   │       └── xml/
>>│   │   │           ├── backup_rules.xml
>>│   │   │           └── data_extraction_rules.xml
>>│   │   └── test/
>>│   │       └── java/
>>│   │           └── com/example/myapplication/
>>│   │               └── ExampleUnitTest.kt
>>│   ├── build.gradle
>>│   └── ...
>>├── build.gradle
>>├── Gradle Scripts/
>>│   ├── build.gradle.kts (Project: My_Application)
>>│   ├── build.gradle.kts (Module: app)
>>│   ├── proguard-rules.pro (ProGuard Rules for ':app')
>>│   ├── project.properties (Project Properties)
>>│   ├── gradle-wrapper.properties (Gradle Version)
>>│   ├── libs.versions.toml (Version Catalog)
>>│   ├── local.properties (SDK Location)
>>│   └── settings.gradle.kts (Project Settings)
>>└── ...
```
## 關鍵檔案
>1. `MainActivity.kt`: 主要活動,負責啟動猜字遊戲和數字遊戲。
>2. `HangmanGameScreen.kt`: 一個 DialogFragment ,代表猜字遊戲的畫面。
>3. `BullsAndCowsGameScreen.kt`: 一個 DialogFragment ,代表數字遊戲的畫面。
>4. `activity_main.xml`: 主要活動的佈局檔案。
>5. `hangman_game_screen.xml`: 猜字遊戲畫面的佈局檔案。
>6. `bulls_and_cows_game_screen.xml`: 數字遊戲畫面的佈局檔案。
>7. `strings.xml`: 儲存應用程式中使用的字串資源的檔案。

## 開發流程
>遊戲2in1的開發採用循序漸進的方式,從最基本的架構開始,逐步加入功能。以下是開發大致的流程:
>
>>1. 設置專案架構和相依性。
>>2. 實作 MainActivity 和它的佈局,包含啟動兩個遊戲的按鈕。
>>3. 建立 HangmanGameScreen 類別和它的佈局,包含字串顯示、圖片和字母按鈕等UI元件。
>>4. 實作猜字遊戲的遊戲邏輯,包含選取字串、猜測字母和勝利/失敗條件。
>>5. 建立 BullsAndCowsGameScreen 類別和它的佈局,包含數字顯示和數字按鈕等UI元件。
>>6. 實作數字遊戲的遊戲邏輯,包含產生題目數字、猜測數字和勝利條件。
>>7. 將兩個遊戲的畫面整合到主要活動中,讓使用者可以啟動並遊玩兩種遊戲。
>>8. 重構程式碼、整理匯入的模組和將字串寫入資源檔,以提高可維護性和方便當地語系化。
>>9. 徹底測試應用程式,修正任何發現的問題或錯誤。
>>10. 撰寫專案開發過程文件和README說明檔。

## 未來加強項目
>以下是遊戲2in1可能的未來加強項目:
>
>>1. 實作使用者認證和記錄高分的排行榜。
>>2. 新增更多遊戲模式或現有遊戲的變體。
>>3. 優化使用者介面和遊戲體驗設計。
>>4. 支援更多語系和當地語系化。
>>5. 優化應用程式效能和記憶體使用。

## 結語
>遊戲2in1讓使用者可以在Android裝置上輕鬆遊玩兩種經典的字母和數字遊戲。它友善的介面和簡單的遊戲玩法,為各個年齡層的使用者帶來愉快的遊戲體驗。
