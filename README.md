# 学習用です。</br>ハンズオンでTODOアプリ (Spring Boot)を作りました。

## 1. 学習目的
- Webアプリケーションの基礎を理解する
- Spring Bootを使ったCRUD処理の流れを体験する
- Thymeleaf + BootstrapでUIを整える

## 2. 学習内容まとめ
- CRUD操作
- Create: フォームからTODOを追加
- Read: 一覧ページでTODOを表示
- Update: 編集画面からTODOを更新
- Delete: 削除ボタンでTODOを削除
- スタイリング
- Bootstrapを導入し、フォームやボタンを見やすく整えた
- データ管理
- H2 Databaseを利用して簡易的に永続化

## 3. 実装の流れ（記録）
- Spring Initializrでプロジェクト作成
- Dependencies: Spring Web, Spring Data JPA, H2, Thymeleaf
- Todoエンティティを作成
- TodoRepositoryを作成し、JPAでCRUDを実装
- TodoControllerを作成し、一覧・追加・編集・削除を実装
- Thymeleafテンプレート (list.html, form.html) を作成
- Bootstrapを導入してUI改善

## 4. 学んだこと
- Spring Bootでは、Controller → Service → Repository → Entity の流れが基本
- ThymeleafはHTMLに近い感覚で書けるので、学習しやすい
- Bootstrapを使うと、最低限のCSS知識でも見栄えが良くなる
- CRUDを一通り作ると「Webアプリの基本構造」が理解できる

## 5. つまずいた点・解決方法
- エラー: Thymeleafテンプレートが見つからない
→ src/main/resources/templates 配置を確認
- エラー: DB接続ができない
→ application.properties の設定を見直し、H2コンソールで確認

## memo
使用ライブラリまとめ (Gradle管理)</br>
Spring Boot関連</br>
- spring-boot-starter-web</br>
→ Webアプリ開発の基本。REST APIやMVCをサポート</br>
- spring-boot-starter-thymeleaf</br>
→ サーバーサイドテンプレートエンジン。HTMLと連携してビューを生成</br>
- spring-boot-starter-validation</br>
→ 入力チェックやバリデーション機能を提供</br>
- spring-boot-devtools</br>
→ 開発効率化のためのホットリロードなど</br>
データアクセス</br>
- MyBatis Spring Boot Starter</br>
→ SQLベースのO/Rマッパー。Spring Bootと統合して簡単にDB操作</br>
- H2 Database</br>
→ 組み込み型の軽量データベース。学習やテストに便利</br>
開発支援</br>
- Lombok</br>
→ Getter/SetterやBuilderなどを自動生成し、コードを簡潔に</br>
- spring-boot-starter-test</br>
→ JUnitやMockMvcなど、テスト用ライブラリをまとめて提供</br>
フロントエンド</br>
- Bootstrap (WebJars)</br>
→ CSSフレームワーク。UIを簡単に整える</br>
- webjars-locator-core</br>
→ WebJarsのリソースを簡単に解決できる仕組み</br>
- Thymeleaf Layout Dialect</br>
→ Thymeleafでレイアウトを共通化するための拡張</br>

