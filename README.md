Then..

To complete `DDD Template` by reference [DDD-TW java-ddd-commons](https://github.com/ddd-tw/java-ddd-commons/tree/master)

To implement `Mediator` by reference [PipelinR](https://github.com/sizovs/PipelinR#alternatives)

由於對於 Java 的非同步還並不熟悉，IMediator 還沒提供非同步的方法

---

案例描述
假設我們正在開發一個線上商店的訂單系統。在這個系統中，有以下元素：

訂單（Order） - 聚合根（Aggregate Root），它包含多個訂單項目（Order Items）和一個客戶資訊（Customer Information）。
訂單項目（Order Item） - 實體（Entity），它表示購買的單一產品及其數量。
客戶資訊（Customer Information） - 值對象（Value Object），包含客戶的姓名和地址。
設計
聚合根：訂單（Order）

包含多個訂單項目。
維護訂單狀態（例如：新建、已支付、已發貨）。
包含客戶資訊的值對象。
實體：訂單項目（Order Item）

產品ID、數量、單價。
可以計算項目總價。
值對象：客戶資訊（Customer Information）

客戶姓名、地址。
無唯一標識符，可不變。