## Reference:
[PipelinR](https://github.com/sizovs/PipelinR#alternatives)

[DDD-TW java-ddd-commons](https://github.com/ddd-tw/java-ddd-commons/tree/master)

### TODO:
- 加入 Validation，使 Request 傳入時即可驗證 Value Object 的 Domain Rule
- 加入 Outbox Pattern
- 加入 RabbitMq 的使用
- 加入 Authorization 與 Authentication

### Pending:
- 加入 Result Pattern
- 加入 Specification Pattern

---

### Problem Detail:

假設我們正在開發一個線上商店的訂單系統。在這個系統中，有以下元素：

- 訂單（Order）
    - 聚合根（Aggregate Root），它包含多個訂單項目（Order Items）和一個客戶資訊（Customer Information）。
- 訂單項目（Order Item）
    - 實體（Entity），它表示購買的單一產品及其數量。
- 客戶資訊（Customer Information）
    - 值對象（Value Object），包含客戶的姓名和地址。

---
### NOTE:
使用 Liquibase 進行 migrations 參考：

[Liquibase-Hibernate](https://github.com/liquibase/liquibase-hibernate)

[Spring boot liquibase. Execute DIFF between entities and database](https://stackoverflow.com/questions/60991196/spring-boot-liquibase-execute-diff-between-entities-and-database)

步驟 1：設定 Liquibase
首先，確保你的專案中已經引入了 Liquibase 依賴。這可以通過 Maven 或 Gradle 來完成。例如，使用 Maven，你可以在 pom.xml
文件中添加如下依賴：

```
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
    <version>[適當的版本]</version>
</dependency>

```

步驟 2：設定資料庫連接
在 src/main/resources 目錄下建立一個 liquibase.properties 文件，並配置你的資料庫連接資訊：

```
driver: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://[資料庫地址]/[資料庫名稱]
username: [資料庫用戶名]
password: [資料庫密碼]
changeLogFile: src/main/resources/db/changelog/db.changelog-master.xml
```

步驟 3：生成遷移腳本
使用 Liquibase 的 diff 命令來比較現有資料庫與 JPA 實體之間的差異，並生成遷移腳本。這可以通過命令行來執行。例如：

```
liquibase --changeLogFile=src/main/resources/db/changelog/changelog-001.xml diff
```

這條命令會生成一個名為 changelog-001.xml 的檔案，其中包含了現有資料庫與你的 JPA 實體之間的差異。