# seat_api
座席表のAPIです。

## ブランチ運用フロー
### 各ブランチ
* master: 本番環境にデプロイされているコード
* staging: staging環境(テストアップ確認環境)にデプロイされているコード
* develop: develop環境(開発時検証環境)にデプロイされているコード
### 通常時
- masterから作業ブランチを作成
- develop環境へのリリース
  - 作業ブランチ to developのPRを作成してマージ、リリース
- staging環境へのリリース
  - 作業ブランチ to stagingのPRを作成してマージ、リリース
- production環境へのリリース
  - 作業ブランチ to masterのPRを作成してマージ、リリース
- production環境へのリリース後
  - master to staging, master to develop でマージ

### コンフリクトが発生した場合
- 予定にない変更がproductionにリリースされないように作業ブランチの担当者間で都度調整してマージする

### 障害発生時の運用フロー
* masterからhotfixブランチを切る
* hotfixブランチをstagingにマージし、staging環境にデプロイしてテスト
* 問題なければhotfixブランチをmasterにマージ
* 障害対応終了後、developブランチにもマージ

## Issuesの運用方法
###Issueを立てる
- Issues → New Issue → テンプレート選択

## ローカル環境の作成方法

### tsushinのアプリ起動
```
cd Application/tsushin
sbt run
```
## codegenの方法
```
sbt "runMain generator.SlickModelGenerator"
```
