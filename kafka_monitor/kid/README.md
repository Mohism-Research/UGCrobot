
# kafka-monitor

kafka消息监控

---

## 功能介绍

### 负责监控kafka topic消息监控，每晚定时12点备份

---

## 目录结构

  - kid
    - bin
      - core
      - monitor
    - plugins
      - monitor      
    - pub
    - Makefile
  - library

---

## 配置信息 ./bin/plugins/monitor/monitor_config.xml

```xml
{
	"mysql": [
		{
			"host" : "127.0.0.1",
			"port" : 3306,
			"user" : "root",
			"pass" : "dataservice2015",
			"name" : "crawler_test"
		}
	]
}
```

---

## 编译运行

将配置信息配置好后放入./bin/plugins/monitor文件夹下