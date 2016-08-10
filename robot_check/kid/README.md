
# 机器人客户端发帖检测程序

---

## 功能介绍

用于检测机器人客户端发帖是否被删除

---

## 目录结构

 - kid
    - bin
      - plugins
          - core
          - robot_check
    - plugins
      - robot_check      
    - pub
    - Makefile
  - library

---

## 配置信息 ./bin/plugins/robot_check/robot_check_config.xml
```json
{
	"mysql": [
		{
			"host" : "127.0.0.1",
			"port" : 3306,
			"user" : "root",
			"pass" : "dataservice2015",
			"name" : "crawler_robot"
		}
	]
    ,"check_date":"2016-07-22"                 #检测日期
}
```

## 编译运行

将配置信息放入 ./bin/plugins/robot_check目录下