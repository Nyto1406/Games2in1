# Hsiang Yu, Yang資訊專案開發紀錄 - Games2in1 Android 應用程式
## 專案概述
>Games2in1是一個結合了兩種經典遊戲的Android應用程式:Hangman和1A2B (Bulls And Cows)。
>
>> ### Hangman
>>
>>使用者可以自行輸入一個「介於4至12字母的單字」作為答案，或是讓系統從清單中隨機挑選一個字。
遊戲會顯示答案的長度，然後使用者就可以開始一個一個猜字母。
如果猜錯字母，就會像經典版的Hangman一樣，在畫面上畫下一筆。
使用者猜對答案，或使用者用完機會時，遊戲就會結束。
>
>
>>### 1A2B (Bulls And Cows)
>>
>>使用者可以自行輸入一個同時滿足「各位數字不重複」「介於3至6位」的數字作為答案，或是讓系統隨機產生一個數字。
畫面中會有一個鍵盤提供使用者輸入要猜的數字。
每進行一次猜測後，遊戲會輸出 **位置與數字皆正確的個數(Bulls)** 和 **數字正確位置錯誤的數字個數(Cows)** 。
當使用者猜對答案時，遊戲就會結束。



## 專案結構
>下圖為Games2in1的架構圖：
>
>>```
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
>>```
## 重要檔案
>1. `MainActivity.kt`: 主活動，負責啟動 Hangman 和 1A2B 。
>2. `HangmanGameScreen.kt`: 一個 DialogFragment，負責猜字遊戲的運作。
>4. `BullsAndCowsGameScreen.kt`: 一個 DialogFragment，負責數字遊戲的運作。
>5. `activity_main.xml`: 主活動的佈局檔案。
>6. `hangman_game_screen.xml`: Hangman 畫面的佈局檔案。
>7. `bulls_and_cows_game_screen.xml`: 1A2B 畫面的佈局檔案。
>8. `strings.xml`: 儲存 Hangman 中使用的字串資源的檔案。

## 開發流程
>>1. 設置專案架構和相依性。
>>2. 製作 MainActivity 和佈局，包含啟動遊戲的兩個按鈕。
>>3. 建立 HangmanGameScreen 類別和佈局，包含字串顯示、圖片和字母按鈕等UI元件。
>>4. 編寫 Hangman 的遊戲邏輯，包含答案設定、字母輸入和勝利/失敗條件。
>>5. 建立 BullsAndCowsGameScreen 類別和它的佈局,包含數字顯示和數字按鈕等UI元件。
>>6. 編寫 Hangman 的遊戲邏輯，包含答案設定、數字輸入和勝利條件。
>>7. 整合兩個遊戲的畫面到 MainActivity 中，讓使用者可以啟動並遊玩。
>>8. 重構程式碼、整理匯入的模組、將字串寫入檔案。
>>9. 測試應用程式，修正測試時發現的問題或錯誤。
>>10. 撰寫專案開發紀錄和README說明檔。

## 後期加強項目
>下面是 Games2in1 可能的後期加強項目：
>
>>1. 建立分數與帳戶機制，並增加分數排行榜。
>>2. 加入新遊戲[Heart](https://bicyclecards.com/how-to-play/hearts/)
>>3. 優化UI介面和遊戲設計。

## 總結
>Games2in1 讓使用者可以在 Android 裝置上遊玩 Hangman 與 1A2B。
