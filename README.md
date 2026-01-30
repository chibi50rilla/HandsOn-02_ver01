# ↓こちらのハンズオンアプリに少し機能を付け加えました
[学習用です。ハンズオンでTODOアプリ (Spring Boot)を作りました。](https://github.com/chibi50rilla/HandsOn-02)

## 追加機能（今回新たに実装した内容）
### ■ 1日限定のタスクリスト
- 登録されたタスクは 当日中のみ有効
- 日付が変わると自動的に削除される
- 「今日できることだけに集中する」というコンセプトで設計

### ■ 一日の残り時間の表示
- 画面上に 「今日の残り時間」 を表示
- 1日の終わりが視覚的にわかり、タスク管理の意識が高まる

### ■ データベースを H2 → Supabase に変更
- 開発用の H2 Database から、クラウド DB の Supabase に移行
- 永続化・外部アクセス・本番運用を意識した構成にアップグレード

### ■ Supabase × GitHub Actions による自動削除
- 毎日 0 時に Supabase のタスクを削除する SQL を
GitHub Actions で自動実行
- サーバー側で cron を使わず、CI/CD で管理できる構成に

### ■ ID 表示 → 連番表示に変更
- DB の ID（UUID や主キー）ではなく、
画面上では 1, 2, 3… の連番で表示
- ユーザーにとって見やすく、操作しやすい UI に改善

## 追加機能（URL）

### 公開アプリURL  
  [ToDoリストアプリ](http://43.206.147.251:8080)

### Supabase（DB管理）  
  [Supabase API URL](https://vcagtgmiyzootvxqfnhb.supabase.co)


### GitHub Actions（自動削除ワークフロー）  
  [.github/workflows/delete-tasks.yml](./.github/workflows/delete-tasks.yml)

