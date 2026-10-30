# 1日限定のTODOリストアプリ (Spring Boot)

「今日できることだけに集中する」をコンセプトに設計した、当日中のみ有効なタスク管理 Web アプリケーションです。
日付が変わるとタスクが自動で初期化され、毎日リフレッシュした状態でタスク管理を行えます。

---

## 🌟 主な機能・特徴

### ■ 1日限定のタスクリスト
- 登録されたタスクは当日中のみ有効
- 日本時間の毎日深夜 0 時に自動リセット
- 「今日やること」に集中するためのシンプルな設計

### ■ 一日の残り時間の可視化
- 画面上部に「今日の残り時間（時・分・秒）」をリアルタイム表示
- 1日の終わりを意識し、タスクの消化ペースを高める仕様

### ■ 連番による直感的な表示
- データベース内部の ID ではなく、画面上では `1, 2, 3…` の連番で整理
- 直感的で視認性の高い UI を実現

### ■ タスクのステータス管理
- `Todo` / `Doing` / `Done` のステータス切り替え
- ステータスやキーワードによる検索・絞り込み機能

---

## 🛠 技術スタック・インフラ構成

| 区分 | 採用技術 |
| :--- | :--- |
| **バックエンド** | Java 17 / Spring Boot 3.1.2 |
| **ORM / マッパー** | MyBatis 3.5 |
| **ビルドツール** | Gradle |
| **データベース** | H2 Database（ファイルベース永続化） |
| **フロントエンド** | HTML5 / Thymeleaf / Bootstrap / JavaScript |
| **インフラ** | AWS Lightsail (Ubuntu 22.04 LTS) |
| **Web サーバー / リバースプロキシ** | Nginx |
| **SSL / TLS** | Let's Encrypt (Certbot) |
| **プロセス管理** | systemd (`todo.service`) |
| **定期実行・自動化** | Linux cron（毎日 0:00 JST 自動リセット） |

---

## 🏗 インフラアーキテクチャ

単一の AWS Lightsail サーバー上で、複数のポートフォリオアプリを安全かつ独立して稼働させるマルチテナント構成をとっています。

```text
ユーザー (HTTPS:443)
       │
       ▼
[ DuckDNS / DNS解決 ]
       │
       ▼
[ Nginx (リバースプロキシ / SSL終端) ]
 ├─ todo-portfolio.duckdns.org    ──> http://localhost:8081 (TODOアプリ)
 └─ 他ポートフォリオドメイン       ──> http://localhost:8080
       │
       ▼
[ Spring Boot (Embedded Tomcat: 8081) ]
       │
       ▼
[ H2 Database (./data/tododb) ]
       ▲
       │ (毎日 0:00 JST 自動初期化)
[ Linux cron (`reset-todo.sh`) ]
```

## ⚙️ 自動リセットの仕組み
外部 SaaS や CI/CD トリガーに依存せず、サーバー内で自己完結する高可用な定期リセット機構を構築しています。

cron スケジューラ:

サーバー（UTC）の 15:00（日本時間 JST 0:00）にリセットスクリプトを定期実行。

リセットスクリプト (/usr/local/bin/reset-todo.sh):

systemctl stop todo でアプリを安全に一時停止。

保存されている H2 DB ファイルをクリーンアップ。

systemctl start todo でサービスを再起動。

初期データ再生成:

Spring Boot 起動時に schema.sql および data.sql が自動適用され、初期タスク状態で翌日を迎えます。

## 🔗 公開 URL
公開サイト: https://todo-portfolio.duckdns.org/tasks
