## 限流方案

1. 集群方案：分布式缓存（redis+lua、db、consul）
2. 单机方案：本地缓存(guava、concurrentHashMap)


## 限流算法

1. 计数器
2. 令牌桶
3. 漏桶